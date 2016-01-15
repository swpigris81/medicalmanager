<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	var checkStatus={"00":"待审核","01":"审核通过","02":"审核拒绝"};
		$(function(){
			//表格
			$('#phoneChangeTable').datagrid({ 
		        //url: conatext + "/authentiction/queryPhoneChangeApplyList.do",
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
		        	{title:'真实姓名',field:'realName',width:180}, 
		        	{title:'用户名',field:'userName',width:180,hidden:true}, 
		        	{title:'userId',field:'userId',width:180,hidden:true}, 
	        		{title:'原手机号',field:'originalPhone', width:180},
	        		{title:'申请手机号',field:'newPhone',width:180},
			        {title:'充值总额',field:'rechargeAmount',width:160},
			        {title:'申请时间',field:'applyTime',width:160},
			        {title:'id',field:'id',width:160,hidden:true},
			         {title:'状态',field:'applyStatus',width:140, formatter:function(value,rowData,rowIndex){
	                	return checkStatus[value];
	        		}}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditPhoneChangeSaveButton").click(function() {
				$.messager.progress();
				$("#addEditPhoneChangeForm").form("submit", {
					url : context + "/authentiction/checkPhoneChangeApply.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditPhoneChangeForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditPhoneChangeForm").form('clear');
						if (data.success) {
							$('#addEditPhoneChangeWindow').window('close');
							$("#phoneChangeTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditPhoneChangeCancelButton").click(function() {
				$('#addEditPhoneChangeWindow').window('close');
			});
			//searchPhoneChange();
		});
		function addPhoneChange(index) {
			var row = $('#phoneChangeTable').datagrid('getSelected');
			$("#addEditPhoneChangeForm").form('clear');
			$('#addEditPhoneChangeWindow').window({
				title : "查看详细"
			});
			$('#addEditPhoneChangeWindow').window('open');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#userName").html(row.userName);
			$("#originalPhone").html(row.originalPhone);
			$("#applyTime").html(row.applyTime);
			$("#applyReason").html(row.applyReason);
			$("#newPhone").html(row.newPhone);
			if(row.applyStatus!='00'){
				$("#addEditPhoneChangeSaveButton").hide();
			}else{
				$("#addEditPhoneChangeSaveButton").show();
			}
			$('#addEditPhoneChangeForm').form("load", {
				applyStatus : row.applyStatus,
				applyReason:row.applyReason,
				userId:row.userId,
				newPhone:row.newPhone,
				id:row.id
			});
		}
		function deletePhoneChange(){
			var row = $('#phoneChangeTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteProduct.do";
					var data = {};
					data.productId = row.productId;
					ajaxCall("#phoneChangeTable", url, data, function(res) {
						$("#phoneChangeTable").datagrid("reload");
					});
				}
			});
		}
		function searchPhoneChange(){
			var url = context + "/authentiction/queryPhoneChangeApplyList.do";
			var data = {};
			data.beginDate = $("#beginDatePhoneChange").datebox('getValue');
			data.endDate=$("#endDatePhoneChange").datebox('getValue');
			data.phoneNumber=$("#phoneNumberPhoneChange").val();
			data.checkStatus=$("#checkStatusPhoneChange").val();
			$('#phoneChangeTable').datagrid({url:url,queryParams:data});
		}
		
		
		
	</script>
	<div id="tbPhoneChange" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                时间段：<input class="easyui-datebox" name="beginDatePhoneChange" id="beginDatePhoneChange"/>&nbsp;至&nbsp;
                                <input class="easyui-datebox" name="endDatePhoneChange" id="endDatePhoneChange"/>
                            </td>
                            <td>
                                <label >手机号：</label>
                                <input class="easyui-textbox" name="phoneNumberPhoneChange" id="phoneNumberPhoneChange"/>
                            </td>
                             <td>
                                <label >状态：</label>
                                <select name="checkStatusPhoneChange" id="checkStatusPhoneChange" style="width: 80px;">
                                	<option value="">全部</option>
                                	<option value="00" selected="selected">待审核</option>
                                	<option value="01">审核通过</option>
                                    <option value="02">审核拒绝</option>
                                </select>
                            </td>
                            <td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchPhoneChange();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAdd" iconcls="icon-tip" onclick="addPhoneChange()">查看明细</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="手机变更列表" id="phoneChangeTable" toolbar="#tbPhoneChange"></table>
	
    <div id="addEditPhoneChangeWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:340px;height:320px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditPhoneChangeForm" method="post">
            <input class="easyui-textbox" type="hidden" name="id"/>
            <input class="easyui-textbox" type="hidden" name="userId"/>
            <input class="easyui-textbox" type="hidden" name="newPhone"/>
                <table width="100%">
                    <tr>
                        <td align="right" style="width: 60px;">
                            用户名：
                        </td>
                        <td >
                            <label id="userName"></label>
                        </td>
                    </tr>
                     <tr>
                        <td align="right" style="width: 60px;">
                            原手机号：
                        </td>
                        <td>
                            <label id="originalPhone"></label>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width: 60px;">
                            现手机号：
                        </td>
                        <td>
                            <label id="newPhone"></label>
                        </td>
                    </tr>
                     <tr>
                        <td align="right" style="width: 60px;">
                            申请时间：
                        </td>
                        <td >
                            <label id="applyTime"></label>
                        </td>
                    </tr>
                     <tr>
                        <td align="right" style="width: 60px;">
                            申请原因：
                        </td>
                        <td >
                            <label id="applyReason"></label>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width: 60px;">
                            审核状态：
                        </td>
                        <td>
                            <input type="radio" name="applyStatus" value="01"/>成功&nbsp;
                            <input type="radio" name="applyStatus" value="02"/>失败&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td align="right" style="width: 60px;">
                            审核原因：
                        </td>
                        <td >
                            <textarea rows="3" cols="30" class="textarea easyui-validatebox" name="applyReason"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <a id="addEditPhoneChangeSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">提交</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
