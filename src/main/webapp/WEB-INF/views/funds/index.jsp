<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var typeStore = {"00":"急救车", "01":"120中心", "02":"其他"};
		$(function(){
			//表格
			$('#fundsTable').datagrid({ 
		        //url: context + "/funds/fundsList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:true,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
	        		{title:'用户名',field:'custName', width:180},
			        {title:'手机号',field:'custMobile',width:180},
			        {title:'单位',field:'rechargeUnit',width:120},
	        		{title:'类型',field:'rechargeType',width:120, formatter:function(value,rowData,rowIndex){
	                	return typeStore[value];
	        		}},
	        		{title:'总金额',field:'rechargeAmount',width:120},
	        		{title:'到期时间',field:'expireDate',width:120},
	        		{title:'主键',field:'id',width:80, hidden:true}
		        ]],
		        toolbar:"#searchfundsToolbar"
		    });
		});
		
		//查询
		function searchFunds(){
			var userName = $("#searchUserName").val();
			var userMobile = $("#searchUserMobile").val();
			var rechargeType = $("#searchRechargeTypeCombo").combo("getValue");
			var queryParams = $('#fundsTable').datagrid('options').queryParams;
			queryParams.custName = userName;
			queryParams.custMobile = userMobile;
			queryParams.rechargeType = rechargeType;
			$('#fundsTable').datagrid('options').queryParams = queryParams;
			$('#fundsTable').datagrid('options').url = context + "/funds/fundsList.do";
			$('#fundsTable').datagrid("load");
		}
		
	</script>
	<table title="用户资金明细列表" id="fundsTable"></table>
	
	<!-- Toolbar -->
	<div id="searchfundsToolbar" style="padding:5px;height:80px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				  用户名: <input class="easyui-textbox" id="searchUserName" style="width:100px">
				   手机号: <input class="easyui-textbox" id="searchUserMobile" style="width:100px">
				    充值类型:<select id="searchRechargeTypeCombo" class="easyui-combobox" data-options="editable:false" style="width: 100px">
						<option value="">全部</option>
						<option value="00">急救车</option>
						<option value="01">120中心</option>
						<option value="02">其他</option>
					</select>
		        <a href="javascript:searchFunds()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
	    </fieldset>
	</div>
</body>
</html>
