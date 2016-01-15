<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		var queryInactiveListUrl = context + "/customer/inactive/queryInactiveList.do";
		$(function(){
			//表格
			$('#gridTableInactive').datagrid({ 
		        url: queryInactiveListUrl,
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:true,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        fitColumns:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
	        		{title:'用户名',field:'nickname', width:180},
	        		{title:'真实姓名',field:'realName',width:180},
			        {title:'手机号码',field:'phone',width:180},
	        		{title:'身份证',field:'idCardNo', width:260},
	        		{title:'类型',field:'cusType',width:140,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, customType);
	        			}
	        		},	        		
			        {title:'省份',field:'provinceName',width:140},
	        		{title:'市',field:'cityName', width:140},
	        		{title:'注册时间',field:'registerTime',width:240},
			        {title:'服务到期时间',field:'serviceExpireTime',width:240},
	        		{title:'信用积分',field:'creditScore', width:140}
		        ]],
		        toolbar:"#searchToolbarInactive"
		    });
			// 确认对话框确认
			$("#confirmWinInactiveOkButton").click(function() {
				$.messager.progress();
				$("#confirmWinInactiveForm").form("submit", {
					url : context + "/customer/inactive/saveOrUpdate.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#confirmWinInactiveForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#confirmWinInactiveForm").form('clear');
						if (data.success) {
							$('#confirmWinInactive').window('close');
							$("#gridTableInactive").datagrid("reload");
						}
					}
				});
			});	
			
			// 确认对话框取消
			$("#confirmWinInactiveCancelButton").click(function() {
				$('#confirmWinInactive').window('close');
			});
		});
		
		// 修改信息
		function updateInactive() {
			var row = $('#gridTableInactive').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			
			$("#confirmWinInactiveForm").form('clear');
			$('#confirmWinInactive').window({
				title : "确认激活"
			});
			$('#confirmWinInactive').window('open');
			$('#confirmWinInactiveForm').form("load", {
				id : row.id
			});
		}
		
		function searchInactive(){
			var registerTimeStart = $('#registerTimeStartInactiveS').datetimebox('getValue');
			var registerTimeEnd = $('#registerTimeEndInactiveS').datetimebox('getValue');
			if (registerTimeStart && registerTimeEnd && (registerTimeStart > registerTimeEnd)) {
				alert("开始时间不能大于结束时间，请重输入！");
				return;
			}
			
			var data = {};
			var phone = $("#phoneInactiveS").val();
			var realName = $("#realNameInactiveS").val();
			data.phone = phone;
			data.realName = realName;
			data.registerTimeStart = registerTimeStart;
			data.registerTimeEnd = registerTimeEnd;
			$('#gridTableInactive').datagrid({url:queryInactiveListUrl,queryParams:data});
		}
	</script>
	
	<!-- 表格 -->
	<table title="未激活用户管理" id="gridTableInactive"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarInactive" style="padding:5px;height:auto">
        <fieldset>
            <legend>信息查询</legend>
            <form id="searchInactive" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>手机号：</label>
                            	<input type="text" id="phoneInactiveS" name="phone" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>真实姓名：</label>
                            	<input type="text" id="realNameInactiveS" name="realName" style="width:100px"  />
                            </td>
                            <td style="width: 400px;"><label>注册时间：</label>
                            	<input class="easyui-datetimebox" type="text" id="registerTimeStartInactiveS" name="registerTimeStart" style="width:140px"/>&nbsp;至&nbsp;
                            	<input class="easyui-datetimebox" type="text" id="registerTimeEndInactiveS" name="registerTimeEnd" style="width:140px"/>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearchInactive" onclick="searchInactive();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td colspan="4">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-edit" onclick="updateInactive()">激活用户</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
    <!-- 确认对话框 -->
    <div id="confirmWinInactive" class="easyui-window" style="width:250px;height:150px" data-options="modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="confirmWinInactiveForm" method="post">
				<input class="easyui-textbox" type="hidden" name="id" id="confirm_id"/>
				<table width="100%">
				    <tr style="height: 70px;">
				        <td colspan="3" align="center">确认要激活该用户吗？</td>
				    </tr>
				    <tr>
				        <td align="right" width="70px;">
	                        <a id="confirmWinInactiveOkButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认</a>
				        </td>
				        <td align="left" width="30px;">
				        </td>
				        <td align="left" width="70px;">
				            <a id="confirmWinInactiveCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>            
    </div>    
</body>
</html>
