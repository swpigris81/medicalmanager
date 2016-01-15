<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>
<head>
    <style>
       td{
            width: 20%;
        }
    </style>
</head>
<body>
<script type="text/javascript">
	$(function(){
	$("#addEditSysReceivablesSaveButton").click(function() {
			$.messager.progress();
			$("#addEditSysReceivablesForm").form("submit", {
				url : context + "/systemset/saveOrUpdateSysReceivables.do",
				dataType : "json",
				onSubmit : function() {
					var isValid = $("#addEditSysReceivablesForm").form('validate');
					if (!isValid) {
						$.messager.progress('close');
					}
					return isValid;
				},
				success : function(result) {
					var data = $.parseJSON(result);
					$.messager.alert("系统提示", data.msg);
					$.messager.progress('close');
					if (data.success) {
						$("#id").val("1");
					}
				}
			});
		});
	});
</script>
	
	
	<div id="addEditUserWindow" style="padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditSysReceivablesForm" method="post">
                <input type="hidden" name="id" value="${tblSysReceivables.id }" id="id"/>
                <div >
                <fieldset style="width: 30%">
                    <legend>支付宝收款账号</legend>
				<table style="width: 100%">
                    <tr>
                        <td width="25%">账号名称：</td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text"maxlength="20" data-options="required:true" name="alipayName" value="${tblSysReceivables.alipayName }" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%">账号：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" maxlength="20" data-options="required:true,min:0" name="alipayAccount"value="${tblSysReceivables.alipayAccount }" data-options="required:true"/>
                        </td>
                    </tr>
                    
                </table>
                </fieldset>
                </div>
                <div style="margin-top: 20px;">
                <fieldset style="width: 30%">
                    <legend>对公账号</legend>
                <table style="width: 100%">
                    <tr>
                        <td width="25%">公司全称：</td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text"maxlength="20"  name="publicAccountCompany"value="${tblSysReceivables.publicAccountCompany }" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%">账号：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="publicAccount" maxlength="20" data-options="required:true,min:0" value="${tblSysReceivables.publicAccount }" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%">开户行：</td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="publicAccountBank"value="${tblSysReceivables.publicAccountBank }" data-options="required:true"/>
                        </td>
                    </tr>
                </table>
                </fieldset>
                </div>
                <div style="margin-top: 20px;">
                <fieldset style="width: 30%">
                    <legend>个人账号</legend>
                <table style="width: 100%">
                    <tr>
                        <td width="25%">用户名：</td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="personName" maxlength="20" value="${tblSysReceivables.personName }" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%">账号：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" maxlength="20" data-options="required:true,min:0" name="personAccount"value="${tblSysReceivables.personAccount }" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%">开户行：</td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" maxlength="20"  name="personAccountBank" maxlength="20" value="${tblSysReceivables.personAccountBank }" data-options="required:true"/>
                        </td>
                    </tr>
                </table>
                </fieldset>
                </div>
                <div style="margin-top: 20px;margin-left: 10%;">
                    <input type="button" class="easyui-button" value="提交" id="addEditSysReceivablesSaveButton" style="width:100px;"/>
                </div>
			</form>
		</div>
	</div>
</body>
</html>
