<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>
<body>
	<script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/ueditor.all.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
		
	<script type="text/javascript">
		var currentStatus = {"00":"未读", "01":"已读"};
		$(function(){
			//表格
			$('#messageTable').datagrid({ 
		        //url: context + "/message/messageList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:true,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
					{field:'ck', title:'请选择', checkbox:true},
	        		{title:'标题',field:'title', width:180},
			        {title:'发送人',field:'sendOper',width:120},
			        {title:'接收人',field:'receiveOper',width:120},
			        {title:'接收省',field:'provinceName',width:120},
			        {title:'接收市',field:'cityName',width:120},
	        		{title:'内容',field:'content',width:380, formatter:function(value,rowData,rowIndex){
	                	return "<a href='javascript:readMessage()'><font color='green'>点击阅读信息内容</font></a>"
	        		}},
	        		/*
	        		{title:'当前状态',field:'status',width:80, formatter:function(value,rowData,rowIndex){
	                	return "" + currentStatus[value];
	        		}},
	        		*/
	        		{title:'主键',field:'id',width:80, hidden:true},
	        		{title:'主键',field:'province',width:80, hidden:true},
	        		{title:'主键',field:'city',width:80, hidden:true}
		        ]],
		        toolbar:"#searchmessageToolbar"
		    });
			//发送
			$("#addEditmessageSaveButton").click(function(){
				if(UE.getEditor('contentEditor').hasContents()){
	    			$("#contentEditorValue").val(UE.getEditor('contentEditor').getContent());
	    		}else{
	    			$.messager.alert("系统提示", "站内消息不能为空！");
	    			return;
	    		}
				console.log($("#contentEditorValue").val());
				var provinceName = $("#messageProvince").combobox("getText");
				var cityName = $("#messageCity").combobox("getText");
				$("#provinceNameValue").val(provinceName);
				$("#cityNameValue").val(cityName);
				
				$.messager.progress();
				$("#addEditmessageForm").form("submit", {
					url:context + "/messaging/sendMessage.do",
					dataType:"json",
					onSubmit:function(){
						var isValid = $("#addEditmessageForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditmessageForm").form('clear');
						$("#sendOper").val("${user.userName}");
						if(data.success){
							$('#addEditmessageWindow').window('close');
							searchMessage();
						}
					}
				});
			});
			//取消
			$("#addEditmessageCancelButton").click(function(){
				$("#addEditmessageWindow").window("close");
			});
			
			//查询日期
			$("#searchBeginDate").datebox({
				formatter: function (date) { 
					var y = date.getFullYear();  
                    var m = date.getMonth() + 1;  
                    var d = date.getDate();  
                    return y + "" + (m < 10 ? ("0" + m) : m) + "" + (d < 10 ? ("0" + d) : d) + "";  
				}
			});
			$("#searchEndDate").datebox({
				formatter: function (date) { 
					var y = date.getFullYear();  
                    var m = date.getMonth() + 1;  
                    var d = date.getDate();  
                    return y + "" + (m < 10 ? ("0" + m) : m) + "" + (d < 10 ? ("0" + d) : d) + "";  
				}
			});
			
			//省市级联查询
			$("#messageProvince").combobox({
				onSelect: function (record) {
					_userCityCombo.combobox({
						url: context + '/administrative/searchCityByProvice.do?provinceid=' + record.value
					}).combobox('clear');
				}
			});
			var _userCityCombo = $("#messageCity").combobox();
		});
		
		//发送信息
		function addNewMessage(){
			$("#addEditmessageForm").form('clear');
    		$('#addEditmessageWindow').window({title:"发送信息"});
    		$('#addEditmessageWindow').window('open');
    		$("#sendOper").val("${user.userName}");
    		$("#addEditmessageSaveButton").show();
		}
		//修改信息
		function updateMessage(){
			$("#addEditmessageSaveButton").show();
    		var checkedItems = $('#messageTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
			/* var status = row.status;
			if(status != "00"){
				$.messager.alert("系统提示", "该信息已被阅读，无法修改！");
    			return;
			} */
    		$("#addEditmessageForm").form('clear');
    		$('#addEditmessageWindow').window({title:"修改信息"});
    		$('#addEditmessageWindow').window('open');
    		$('#addEditmessageForm').form("load",{
    			title:row.title,
    			sendOper:row.sendOper,
    			receiveOper:row.receiveOper,
    			province:row.province,
    			city:row.city,
    			content:row.content,
    			id:row.id
    		});
    		UE.getEditor('contentEditor').setContent(row.content);
		}
		
		function showMessage(){
			var checkedItems = $('#messageTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
			
    		$("#addEditmessageForm").form('clear');
    		$('#addEditmessageWindow').window({title:"信息详情"});
    		$('#addEditmessageWindow').window('open');
    		$('#addEditmessageForm').form("load",{
    			title:row.title,
    			sendOper:row.sendOper,
    			receiveOper:row.receiveOper,
    			province:row.province,
    			city:row.city,
    			content:row.content,
    			id:row.id
    		});
    		UE.getEditor('contentEditor').setContent(row.content);
    		$("#addEditmessageSaveButton").hide();
		}
		
		//阅读
		function readMessage(){
    		var checkedItems = $('#messageTable').datagrid('getChecked');
    		if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
			var sendOper = "${user.userName}";
			var canRead = true;
			if(row.receiveOper != sendOper){
				//当前不是接收人
				canRead = false;
			}else{
				canRead = true;
			}
			var province = "${user.userProvince}";
			if(row.province != province){
				canRead = false;
			}else{
				canRead = true;
			}
			var userCity = "${user.userCity}";
			if(row.city != userCity){
				canRead = false;
			}else{
				canRead = true;
			}
			if(!canRead){
				console.log("不是接收人，不设置阅读");
				showMessage();
    			return;
			}
			
			showMessage();
			var status = row.status;
			/* if(status != "00"){
				console.log("信息已阅读，无需再次发起请求");
    			return;
			} */
			var url = context + "/messaging/readMessage.do";
			var data = {};
			data.id = row.id;
			ajaxCall("#messageTable", url, data, function(res){
    			$("#messageTable").datagrid("reload");
    		});
		}
		//查询
		function searchMessage(){
			var messageUserCode = $("#searchMessageUserCode").val();
			var beginDate = $("#searchBeginDate").datebox("getValue");
			var endDate = $("#searchEndDate").datebox("getValue");
			//var status = $("#searchStatusCombo").combo("getValue");
			var sendOper = "${user.userName}";
			
			var queryParams = $('#messageTable').datagrid('options').queryParams;
			queryParams.sendOper = messageUserCode;
			queryParams.beginDate = beginDate;
			queryParams.endDate = endDate;
			//只能查询接受者的信息
			queryParams.receiveOper = sendOper;
			
			//queryParams.status = status;
			//queryParams.sendOper = status;
			$('#messageTable').datagrid('options').queryParams = queryParams;
			$('#messageTable').datagrid('options').url = context + "/messaging/messageList.do";
			$('#messageTable').datagrid("load");
		}
		//重新来一张
		function resetCode(){
			document.getElementById("verifyCodeImg").src= context + "/main/showVerifyCode.do?"+Math.random();
		}
	</script>
	<table title="站内信列表" id="messageTable"></table>
	
	<div id="addEditmessageWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:840px;height:610px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditmessageForm" method="post">
				<table width="100%">
					<tr>
           				<td>
           					发件人：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" readonly="true" type="text" name="sendOper" id="sendOper" style="width:305px" value="${user.userName}"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					收件人：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="receiveOper" style="width:305px"/>
           					<input class="easyui-validatebox textbox" type="hidden" name="id" style="width:305px"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					省份：
           				</td>
           				<td>
           					<!-- <input class="easyui-validatebox textbox" type="text" name="messageProvince" data-options="required:true"/> -->
           					<select id="messageProvince" class="easyui-combobox" name="province" data-options="required:true,editable:false" style="width:135px">
								<option value="">请选择</option>
								<c:if test="${provinceList != null && fn:length(provinceList) > 0}">
									<c:forEach items="${provinceList }" var="province" varStatus="status">
										<option value="${province.provinceid }">${province.province }</option>
									</c:forEach>
								</c:if>
							</select>
							<input type="hidden" name="provinceName" id="provinceNameValue"/>
           				</td>
           				<td>
           					市：
           				</td>
           				<td>
           					<input class="easyui-combobox" id="messageCity" style="width:135px"  name="city"
                                 data-options="required:true, valueField:'cityid', textField:'city', panelHeight:'auto'" >
                            <input type="hidden" name="cityName" id="cityNameValue"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					标题：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="title" data-options="required:true" style="width:305px"/>
           				</td>
           			</tr>
					<tr>
           				<td colspan="4">
           					<!-- 富文本编辑器 -->
           					<script id="contentEditor" type="text/plain" style="width:800px;height:250px;"></script>
           					<input type="hidden" name="content" id="contentEditorValue"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           				</td>
           				<td colspan="3" align="right" valign="top" height="30px">
           					验证码：
           					<input class="easyui-validatebox textbox" type="text" name="validCode" data-options="required:true" style="width:50px" validType="length[1,4]"/>
           					<img src="<%=context%>/main/showVerifyCode.do" id="verifyCodeImg" alt="" width="80px" onclick="javascript:resetCode()"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditmessageSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
           					<a id="addEditmessageCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- Toolbar -->
	<div id="searchmessageToolbar" style="padding:5px;height:100px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				  发送人: <input class="easyui-textbox" id="searchMessageUserCode" style="width:100px">
		                          发送时间: <input id="searchBeginDate" style="width:100px">
		           	至：<input id="searchEndDate" style="width:100px">
		                          <!-- 状态: 
	              	<select id="searchStatusCombo" class="easyui-combobox" style="width: 100px">
						<option value="">全部</option>
						<option value="00">未读</option>
						<option value="01">已读</option>
					</select> -->
		        <a href="javascript:searchMessage()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
			<div>
		        <div style="float: left;"><a href="javascript:addNewMessage()" class="easyui-linkbutton" iconCls="icon-add" plain="true">发布信息</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:updateMessage()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改信息</a></div>
		    </div>
	    </fieldset>
	</div>
	<!-- 富文本编辑器 -->
	<script type="text/javascript">
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('contentEditor',{
    	toolbars: [
           ['fullscreen', 'source', '|', 'undo', 'redo', '|',
            'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
            'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
            'directionalityltr', 'directionalityrtl', 'indent', '|',
            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
            'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
            'simpleupload', 'insertimage', 'emotion', 'scrawl', 'webapp', 'pagebreak', 'template', 'background', '|',
            'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
            'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
            'print', 'preview', 'searchreplace', 'help', 'drafts'
			]
       	],
    });
	</script>
</body>
</html>
