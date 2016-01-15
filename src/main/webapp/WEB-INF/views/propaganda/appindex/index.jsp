<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var userTypeStore = {"00":"亲属"};
		var typeStore = {"00":"幻灯片"};
		$(function(){
			//表格
			$('#appSlideTable').datagrid({ 
		        //url: context + "/user/userList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:false,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
					{field:'ck', title:'请选择', checkbox:true},
	        		{title:'图片',field:'imageLocation', width:180, formatter:function(value,rowData,rowIndex){
	                	return '<img src="'+ rowData.imageUrl +'" height="80px"></img>';
	        		}},
	        		{title:'类型',field:'type',width:80, formatter:function(value,rowData,rowIndex){
	                	return typeStore[value];
	        		}},
			        {title:'URL地址',field:'imageUrl',width:550},
			        {title:'用户类型',field:'userType',width:80, formatter:function(value,rowData,rowIndex){
	                	return userTypeStore[value];
	        		}},
	        		{title:'上传日期',field:'createDate',width:80},
	        		{title:'上传时间',field:'createTime',width:80},
	        		{title:'主键',field:'id',width:80, hidden:true}
		        ]],
		        toolbar:"#searchAppSlideToolbar"
		    });
			//保存用户
			$("#addEditAppSlideSaveButton").click(function(){
				$.messager.progress();
				//异步上传图片
				$.ajaxFileUpload({  
					url : context + '/appindex/uploadImg.do',// servlet请求路径
					secureuri : false,  
		            fileElementId : 'formAppSlideImg',// 上传控件的id  
		            dataType : 'json',  
		            success : function(data, status) { 
		            	if(data.success){
		            		$("#formHideAppSlideImg").val(data.imageLocation);
		            		$("#imageUrl").val(data.imageUrl);
		            		$("#addEditAppSlideForm").form("submit", {
		    					url:context + "/appindex/saveSlide.do",
		    					dataType:"json",
		    					//data:param,
		    					onSubmit:function(){
		    						var isValid = $("#addEditAppSlideForm").form('validate');
		    						if (!isValid){
		    							$.messager.progress('close');
		    						}
		    						return isValid;
		    					},
		    					success: function(result){
		    						var data = $.parseJSON(result);
		    						$.messager.alert("系统提示", data.msg);
		    						$.messager.progress('close');
		    						$("#addEditAppSlideForm").form('clear');
		    						if(data.success){
		    							$('#addEditAppSlideWindow').window('close');
		    							searchAppSlide();
		    						}
		    					}
		    				});
		            	}else{
		            		$.messager.alert("系统提示", data.msg);
							$.messager.progress('close');
		            	}
		            },
		            error : function(data, status, e) {  
		            	console.log(e);
		            	$.messager.alert("系统提示", data.msg);
		                $.messager.progress('close');
		            } 
				});
			});
			//取消
			$("#addEditAppSlideCancelButton").click(function(){
				$("#addEditAppSlideWindow").window("close");
			});
		});
		
		//新增
		function addNewAppSlide(){
			$("#addEditAppSlideForm").form('clear');
    		$('#addEditAppSlideWindow').window({title:"新增幻灯片"});
    		$('#addEditAppSlideWindow').window('open');
    		$("#imageUrl").attr("readonly",true);
		}
		//修改
		function updateAppSlide(){
    		var checkedItems = $('#appSlideTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
    		$("#addEditAppSlideForm").form('clear');
    		$('#addEditAppSlideWindow').window({title:"修改幻灯片"});
    		$('#addEditAppSlideWindow').window('open');
    		$('#addEditAppSlideForm').form("load",{
    			type:row.type,
    			userType:row.userType,
    			imageLocation:row.imageLocation,
    			imageUrl:row.imageUrl,
    			id:row.id
    		});
    		$("#imageUrl").attr("readonly",true);
		}
		//删除
		function deleteAppSlide(){
    		var checkedItems = $('#appSlideTable').datagrid('getChecked');
    		if(!checkedItems || checkedItems.length < 1){
				$.messager.alert("系统提示", "请选择至少一条记录！");
    			return;
			}
			var userId = [];
    		$.each(checkedItems, function(index, item){
    			userId.push(item.id);
    		});
    		$.messager.confirm('系统提示', '确定删除幻灯片?', function(r){
    			if(r){
    				var url = context + "/appindex/deleteSlide.do";
    				var data = {};
    				data.slideIds = userId.join(",");
    				ajaxCall("#appSlideTable", url, data, function(res){
	        			$("#appSlideTable").datagrid("reload");
	        		});
    			}
    		});
		}
		//查询
		function searchAppSlide(){
			var type = $("#searchImageTypeCombo").combo("getValue");
			var userType = $("#searchUserTypeCombo").combo("getValue");
			var queryParams = $('#appSlideTable').datagrid('options').queryParams;
			queryParams.type = type;
			queryParams.userType = userType;
			$('#appSlideTable').datagrid('options').queryParams = queryParams;
			$('#appSlideTable').datagrid('options').url = context + "/appindex/slideList.do";
			$('#appSlideTable').datagrid("load");
		}
		
	</script>
	<table title="幻灯片列表" id="appSlideTable"></table>
	
	<div id="addEditAppSlideWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditAppSlideForm" method="post">
				<table width="100%">
					<tr>
           				<td>
           					图片类型：
           				</td>
           				<td>
           					<select id="imageTypeCombo" class="easyui-combobox" name="type" data-options="required:true,editable:false" style="width: 305px">
								<option value="00">幻灯片</option>
           					</select>
           					<input type="hidden" name="id"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					用户类型：
           				</td>
           				<td>
           					<select id="userTypeCombo" class="easyui-combobox" name="userType" data-options="required:true,editable:false" style="width: 305px">
								<option value="00">亲属</option>
           					</select>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					图片：
           				</td>
           				<td>
           					<input type="file" name="image" id="formAppSlideImg" style="width: 230px;"/>
           					<!-- 绝对路径 -->
           					<input class="easyui-textbox" name="imageLocation" id="formHideAppSlideImg" style="width: 230px;" type="hidden"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					URL地址：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="imageUrl" id="imageUrl" style="width: 305px;"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditAppSlideSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
           					<a id="addEditAppSlideCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- Toolbar -->
	<div id="searchAppSlideToolbar" style="padding:5px;height:100px">
		<fieldset>
            <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				图片类型: <select id="searchImageTypeCombo" class="easyui-combobox" data-options="editable:false" style="width:100px">
							<option value="">全部</option>
							<option value="00">幻灯片</option>
						</select>
		                          用户类型: 
	              	<select id="searchUserTypeCombo" class="easyui-combobox" name="roleId" data-options="required:true,editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">亲属</option>
					</select>
		        <a href="javascript:searchAppSlide()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
		    
			<div>
		        <div style="float: left;"><a href="javascript:addNewAppSlide()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增幻灯片</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:updateAppSlide()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改幻灯片</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:deleteAppSlide()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除幻灯片</a></div>
		    </div>
	    </fieldset>
	</div>
</body>
</html>
