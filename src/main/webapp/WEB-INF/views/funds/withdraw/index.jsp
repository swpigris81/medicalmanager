<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var statusStore = {"00":"未提现", "01":"已提现"};
		$(function(){
			//表格
			$('#withdrawTable').datagrid({ 
		        //url: context + "/withdraw/withdrawList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:true,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
			        {title:'手机号',field:'custMobile',width:120},
			        {title:'姓名',field:'name',width:120},
			        {title:'提现账号',field:'withdrawAccount',width:180},
	        		{title:'提现银行',field:'withdrawBank',width:120},
	        		{title:'支行',field:'branchBank',width:240},
	        		{title:'提现金额',field:'withdrawAmount',width:120},
	        		{title:'提现时间',field:'withdrawTime',width:120},
	        		{title:'状态',field:'withdrawStatus',width:120, formatter:function(value,rowData,rowIndex){
	                	return statusStore[value];
	        		}},
	        		{title:'主键',field:'id',width:80, hidden:true},
	        		{title:'用户名',field:'custName',width:80, hidden:true}
		        ]],
		        toolbar:"#searchwithdrawToolbar"
		    });
			//保存
			$("#addEditwithdrawSaveButton").click(function(){
				$.messager.progress();
				$("#addEditwithdrawForm").form("submit", {
					url:context + "/withdraw/saveWithdraw.do",
					dataType:"json",
					onSubmit:function(){
						var isValid = $("#addEditwithdrawForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditwithdrawForm").form('clear');
						if(data.success){
							$('#addEditwithdrawWindow').window('close');
							searchwithdraw();
						}
					}
				});
			});
			//取消
			$("#addEditwithdrawCancelButton").click(function(){
				$("#addEditwithdrawWindow").window("close");
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
		});
		
		//明细
		function withdrawDetail(){
			var row = $('#withdrawTable').datagrid('getSelected');
    		if(!row){
    			$.messager.alert("系统提示", "请选择一条记录！");
    			return;
    		}
    		$("#addEditwithdrawForm").form('clear');
    		$('#addEditwithdrawWindow').window({title:"提现明细"});
    		$('#addEditwithdrawWindow').window('open');
    		$('#addEditwithdrawForm').form("load",{
    			custMobile:row.custMobile,
    			custName:row.custName,
    			name:row.name,
    			withdrawBank:row.withdrawBank,
    			branchBank:row.branchBank,
    			withdrawAccount:row.withdrawAccount,
    			withdrawAmount:row.withdrawAmount,
    			withdrawTime:row.withdrawTime,
    			withdrawStatus:row.withdrawStatus,
    			id:row.id
    		});
		}
		//查询
		function searchwithdraw(){
			var custMobile = $("#searchUserMobile").val();
			var beginDate = $("#searchBeginDate").datebox("getValue");
			var endDate = $("#searchEndDate").datebox("getValue");
			var withdrawStatus = $("#searchWithdrawStatusCombo").combo("getValue");
			var queryParams = $('#withdrawTable').datagrid('options').queryParams;
			queryParams.custMobile = custMobile;
			queryParams.beginDate = beginDate;
			queryParams.endDate = endDate;
			queryParams.withdrawStatus = withdrawStatus;
			$('#withdrawTable').datagrid('options').queryParams = queryParams;
			$('#withdrawTable').datagrid('options').url = context + "/withdraw/withdrawList.do";
			$('#withdrawTable').datagrid("load");
		}
		//导出
		function withdrawExport(){
			var url = context + "/withdraw/withdrawExport.do";
			var custMobile = $("#searchUserMobile").val();
			var beginDate = $("#searchBeginDate").datebox("getValue");
			var endDate = $("#searchEndDate").datebox("getValue");
			var withdrawStatus = $("#searchWithdrawStatusCombo").combo("getValue");
			$("#searchExportUserMobile").val(custMobile);
			$("#searchExportBeginDate").val(beginDate);
			$("#searchExportEndDate").val(endDate);
			$("#searchExportWithdrawStatusCombo").combo("setValue", withdrawStatus);
			
			document.getElementById("excelWithdrawForm").action = url;
			document.getElementById("excelWithdrawForm").target = "_blank";
			document.getElementById("excelWithdrawForm").submit();
		}
	</script>
	<table title="提现列表" id="withdrawTable"></table>
	
	<div id="addEditwithdrawWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:230px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditwithdrawForm" method="post">
				<table width="100%">
					<tr>
           				<td align="right">
           					手机号：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="custMobile" style="width: 135px" readonly="true"/>
           					<input class="easyui-textbox" type="hidden" name="id"/>
           					<input class="easyui-textbox" type="hidden" name="custName"/>
           				</td>
           				<td align="right">
           					姓名：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="name" style="width: 135px" readonly="true"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					提现银行：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="withdrawBank" style="width: 135px" readonly="true"/>
           				</td>
           				<td align="right">
           					支行：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="branchBank" style="width: 135px" readonly="true"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					银行账号：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="withdrawAccount" style="width: 135px" readonly="true"/>
           				</td>
           				<td align="right">
           					提现金额：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="withdrawAmount" style="width: 135px" readonly="true"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					提现时间：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="withdrawTime" style="width: 135px" readonly="true"/>
           				</td>
           				<td align="right">
           					提现状态：
           				</td>
           				<td>
           					<select id="withdrawStatusCombo" class="easyui-combobox" name="withdrawStatus" data-options="editable:false" style="width: 135px">
								<option value="00">未提现</option>
								<option value="01">已提现</option>
							</select>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           					<input type="hidden" id="newOrUpdate"/>
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditwithdrawSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
           					<a id="addEditwithdrawCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div style="display: none" id="downloadExcelDiv">
		<form method="post" id="excelWithdrawForm">
			 手机号: <input class="easyui-textbox" id="searchExportUserMobile" name="custMobile" style="width:100px">
				 提现时间: <input id="searchExportBeginDate" style="width:100px" name="beginDate" >
				 至：<input id="searchExportEndDate" style="width:100px" name="endDate" >
				 状态:<select id="searchExportWithdrawStatusCombo" class="easyui-combobox" name="withdrawStatus"  data-options="editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">未提现</option>
						<option value="01">已提现</option>
					</select>
		</form>
	</div>
	
	<!-- Toolbar -->
	<div id="searchwithdrawToolbar" style="padding:5px;height:100px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				 手机号: <input class="easyui-textbox" id="searchUserMobile" style="width:100px">
				 提现时间: <input id="searchBeginDate" style="width:100px">
				 至：<input id="searchEndDate" style="width:100px">
				 状态:<select id="searchWithdrawStatusCombo" class="easyui-combobox" data-options="editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">未提现</option>
						<option value="01">已提现</option>
					</select>
		        <a href="javascript:searchwithdraw()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
			<div>
		        <div style="float: left;"><a href="javascript:withdrawDetail()" class="easyui-linkbutton" iconCls="icon-tip" plain="true">查看明细</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:withdrawExport()" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a></div>
		        <div class="datagrid-btn-separator"></div>
		    </div>
	    </fieldset>
	</div>
</body>
</html>
