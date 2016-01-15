<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var typeStore = {"00":"急救车", "01":"120中心", "02":"其他"};
		var statusStore = {"00":"成功", "01":"失败"};
		$(function(){
			//表格
			$('#rechargeTable').datagrid({ 
		        //url: context + "/recharge/rechargeList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:true,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
	        		{title:'用户名',field:'custName', width:180, hidden:true},
			        {title:'手机号',field:'custMobile',width:180, hidden:true},
			        {title:'充值方式',field:'rechargeMode',width:180, hidden:true},
			        {title:'备注',field:'rechargeRemark',width:180, hidden:true},
			        {title:'单位',field:'rechargeUnit',width:120},
	        		{title:'充值类型',field:'rechargeType',width:120, formatter:function(value,rowData,rowIndex){
	                	return typeStore[value];
	        		}},
	        		{title:'充值金额',field:'rechargeAmount',width:120},
	        		{title:'收款账户',field:'receiptBankAccount',width:120},
	        		{title:'充值时间',field:'rechargeTime',width:120},
	        		{title:'状态',field:'rechargeStatus',width:120, formatter:function(value,rowData,rowIndex){
	                	return statusStore[value];
	        		}},
	        		{title:'主键',field:'id',width:80, hidden:true}
		        ]],
		        toolbar:"#searchrechargeToolbar"
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
			
			//取消
			$("#addEditrechargeCancelButton").click(function(){
				$("#addEditrechargeWindow").window("close");
			});
			
		});
		
		//查询
		function searchrecharge(){
			var userName = $("#searchUserName").val();
			var beginDate = $("#searchBeginDate").datebox("getValue");
			var endDate = $("#searchEndDate").datebox("getValue");
			var rechargeType = $("#searchRechargeTypeCombo").combo("getValue");
			var rechargeStatus = $("#searchRechargeStatusCombo").combo("getValue");
			var queryParams = $('#rechargeTable').datagrid('options').queryParams;
			queryParams.custName = userName;
			queryParams.beginDate = beginDate;
			queryParams.endDate = endDate;
			queryParams.rechargeType = rechargeType;
			queryParams.rechargeStatus = rechargeStatus;
			$('#rechargeTable').datagrid('options').queryParams = queryParams;
			$('#rechargeTable').datagrid('options').url = context + "/recharge/rechargeList.do";
			$('#rechargeTable').datagrid("load");
		}
		//详情
		function rechargeDetail(){
			var row = $('#rechargeTable').datagrid('getSelected');
    		if(!row){
    			$.messager.alert("系统提示", "请选择一条记录！");
    			return;
    		}
    		$("#addEditrechargeForm").form('clear');
    		$('#addEditrechargeWindow').window({title:"详细信息"});
    		$('#addEditrechargeWindow').window('open');
    		$('#addEditrechargeForm').form("load",{
    			rechargeUnit:row.rechargeUnit,
    			custMobile:row.custMobile,
    			custName:row.custName,
    			rechargeType:row.rechargeType,
    			rechargeAmount:row.rechargeAmount,
    			receiptBankAccount:row.receiptBankAccount,
    			rechargeTime:row.rechargeTime,
    			rechargeStatus:row.rechargeStatus,
    			rechargeMode:row.rechargeMode,
    			rechargeRemark:row.rechargeRemark,
    			id:row.id
    		});
		}
		//导出Excel
		function rechargeExport(){
			var url = context + "/recharge/rechargeExport.do";
			var userName = $("#searchUserName").val();
			var beginDate = $("#searchBeginDate").datebox("getValue");
			var endDate = $("#searchEndDate").datebox("getValue");
			var rechargeType = $("#searchRechargeTypeCombo").combo("getValue");
			var rechargeStatus = $("#searchRechargeStatusCombo").combo("getValue");
			$("#searchExcelUserName").val(userName);
			$("#searchExcelBeginDate").val(beginDate);
			$("#searchExcelEndDate").val(endDate);
			$("#searchExcelRechargeTypeCombo").combo("setValue", rechargeType);
			$("#searchExcelRechargeStatusCombo").combo("setValue", rechargeStatus);
			
			document.getElementById("excelForm").action = url;
			document.getElementById("excelForm").target = "_blank";
			document.getElementById("excelForm").submit();
		}
	</script>
	<table title="用户充值列表" id="rechargeTable"></table>
	<div id="addEditrechargeWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:300px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditrechargeForm" method="post">
				<table width="100%">
					<tr>
           				<td align="right">
           					单位：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="rechargeUnit" style="width: 135px" readonly="true"/>
           					<input class="easyui-textbox" type="hidden" name="id"/>
           					<input class="easyui-textbox" type="hidden" name="custName"/>
           					<input class="easyui-textbox" type="hidden" name="custMobile"/>
           				</td>
           				<td align="right">
           					充值类型：
           				</td>
           				<td>
           					<select id="rechargeTypeCombo" class="easyui-combobox" name="rechargeType" data-options="editable:false" readonly="true" style="width: 135px">
								<option value="00">急救车</option>
								<option value="01">120中心</option>
								<option value="02">其他</option>
							</select>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					充值金额：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="rechargeAmount" style="width: 135px" readonly="true"/>
           				</td>
           				<td align="right">
           					收款账户：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="receiptBankAccount" style="width: 135px" readonly="true"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					充值时间：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="rechargeTime" style="width: 135px" readonly="true"/>
           				</td>
           				<td align="right">
           					状态：
           				</td>
           				<td>
           					<select id="rechargeStatusCombo" class="easyui-combobox" name="rechargeStatus" data-options="editable:false" readonly="true" style="width: 135px">
								<option value="00">成功</option>
								<option value="01">失败</option>
							</select>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					充值方式：
           				</td>
           				<td colspan="3">
           					<select id="rechargeModeCombo" class="easyui-combobox" name="rechargeMode" data-options="editable:false" readonly="true" style="width: 135px">
								<option value="00">手工充值</option>
								<option value="01">自动充值</option>
							</select>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					备注：
           				</td>
           				<td colspan="3">
           					<textarea rows="4" cols="40" class="textarea easyui-validatebox" name="rechargeRemark" readonly="true"></textarea>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           					<input type="hidden" id="newOrUpdate"/>
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditrechargeCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">返回</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div style="display: none" id="downloadExcelDiv">
		<form method="post" id="excelForm">
			 用户名: <input class="easyui-textbox" id="searchExcelUserName" name="custName" style="width:100px">
			  充值时间: <input id="searchExcelBeginDate" style="width:100px" name="beginDate">
			 至：<input id="searchExcelEndDate" style="width:100px" name="endDate">
			    充值类型:<select id="searchExcelRechargeTypeCombo" name="rechargeType" class="easyui-combobox" data-options="editable:false" style="width: 100px">
					<option value="">全部</option>
					<option value="00">急救车</option>
					<option value="01">120中心</option>
					<option value="02">其他</option>
				</select>
			 充值状态:<select id="searchExcelRechargeStatusCombo" name="rechargeStatus" class="easyui-combobox" data-options="editable:false" style="width: 100px">
					<option value="">全部</option>
					<option value="00">成功</option>
					<option value="01">失败</option>
				</select>
		</form>
	</div>
	
	<!-- Toolbar -->
	<div id="searchrechargeToolbar" style="padding:5px;height:100px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				  用户名: <input class="easyui-textbox" id="searchUserName" style="width:100px">
				  充值时间: <input id="searchBeginDate" style="width:100px">
				 至：<input id="searchEndDate" style="width:100px">
				    充值类型:<select id="searchRechargeTypeCombo" class="easyui-combobox" data-options="editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">急救车</option>
						<option value="01">120中心</option>
						<option value="02">其他</option>
					</select>
				 充值状态:<select id="searchRechargeStatusCombo" class="easyui-combobox" data-options="editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">成功</option>
						<option value="01">失败</option>
					</select>
		        <a href="javascript:searchrecharge()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
		    <div>
		        <div style="float: left;"><a href="javascript:rechargeDetail()" class="easyui-linkbutton" iconCls="icon-tip" plain="true">查看详情</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:rechargeExport()" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a></div>
		        <div class="datagrid-btn-separator"></div>
		    </div>
	    </fieldset>
	</div>
</body>
</html>
