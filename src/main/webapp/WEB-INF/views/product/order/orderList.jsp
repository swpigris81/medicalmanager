<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	var isPay={"Y":"是","N":"否"};
		$(function(){
			//表格
			$('#OrderTable').datagrid({ 
		       // url: context + "/order/queryOrderList.do",
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
		        	{title:'时间',field:'orderTime',width:180}, 
	        		{title:'品名',field:'OrderName', width:180},
	        		{title:'货号',field:'tblProduct',width:180, formatter:function(value,rowData,rowIndex){
	                	return value.productNo;
	        		}},
			        {title:'用户名称',field:'userId',width:160},
			        {title:'手机号码',field:'phoneNumber',width:160},
			        {title:'收货地址',field:'receiveAddress',width:160},
			        {title:'数量',field:'number',width:160},
			         {title:'是否付款',field:'isPay',width:140, formatter:function(value,rowData,rowIndex){
	                	return isPay[value];
	        		}},
			        {title:'是否发货',field:'isDelivery',width:140, formatter:function(value,rowData,rowIndex){
			        	if(value=="Y"){
			        		return isPay[value];
			        	}else{
			        		return "<a href='#' style='color:blue;' onclick='deliverProduct("+rowIndex+")'>"+isPay[value]+"</a>";
			        	}
	        		}}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditOrderSaveButton").click(function() {
				$.messager.progress();
				$("#addEditOrderForm").form("submit", {
					url : context + "/order/deliverProduct.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditOrderForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditOrderForm").form('clear');
						if (data.success) {
							$('#addEditOrderWindow').window('close');
							$("#OrderTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditOrderCancelButton").click(function() {
				$('#addEditOrderWindow').window('close');
			});
			
			//保存品牌
			$("#addEditExpressSaveButton").click(function() {
				$.messager.progress();
				$("#addEditExpressForm").form("submit", {
					url : context + "/order/saveExpress.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditExpressForm").form('validate');
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
							var express = document.getElementById("express");
							var expressName=$("#expressName").val();
							var option= new Option(expressName,expressName );
                			express.options.add(option);	
                			$('#addEditExpressWindow').window('close');
						}
						$("#addEditExpressForm").form('clear');
					}
				});
			});
			//取消
			$("#addEditExpressCancelButton").click(function() {
				$('#addEditExpressWindow').window('close');
			});
			//searchOrder();
		});
		function addOrder() {
			$("#addEditOrderForm").form('clear');
			$('#addEditOrderWindow').window({
				title : "新增商品"
			});
			$('#addEditOrderWindow').window('open');
		}
		function addExpress() {
			$("#addEditExpressForm").form('clear');
			$('#addEditExpressWindow').window({
				title : "新增快递"
			});
			$('#addEditExpressWindow').window('open');
		}
		function deliverProduct(index) {
			var row = $('#OrderTable').datagrid('getData').rows[index];
			$("#addEditOrderForm").form('clear');
			$('#addEditOrderWindow').window({
				title : "发货"
			});
			$('#addEditOrderWindow').window('open');
			var date=new Date();
			$('#addEditOrderForm').form("load", {
				deliverTime : DateUtil.dateToStr('yyyy-MM-dd HH:mm:ss', date),
				receiveAddress : row.receiveAddress,
				phoneNumber : row.phoneNumber,
				userName : row.userId,
				orderNumber : row.orderNumber,
				number : row.number,
				productNo : row.productNo
			});
		}
		function deleteOrder(){
			var row = $('#OrderTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteProduct.do";
					var data = {};
					data.productId = row.productId;
					ajaxCall("#OrderTable", url, data, function(res) {
						$("#OrderTable").datagrid("reload");
					});
				}
			});
		}
		function searchOrder(){
			var url = context + "/order/queryOrderList.do";
			var data = {};
			data.beginDate = $("#beginDateOrder").datebox('getValue');
			data.endDate=$("#endDateOrder").datebox('getValue');
			data.isPay=$("#isPayOrder").val();
			data.isDelivery=$("#isDeliveryOrder").val();
			$('#OrderTable').datagrid({url:url,queryParams:data});
		}
		
		
		
	</script>
	<div id="tbOrder" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                时间段：<input class="easyui-datebox" name="beginDateOrder" id="beginDateOrder"/>&nbsp;至&nbsp;
                                <input class="easyui-datebox" name="endDateOrder" id="endDateOrder"/>
                            </td>
                            <td>
                                <label >是否付款：</label>
                                <select name="isPayOrder" id="isPayOrder" style="width: 80px;">
                                	<option value="">全部</option>
                                	<option value="Y">是</option>
                                	<option value="N">否</option>
                                </select>
                            </td>
                             <td>
                                <label >是否发货：</label>
                                <select name="isDeliveryOrder" id="isDeliveryOrder" style="width: 80px;">
                                	<option value="">全部</option>
                                	<option value="Y">是</option>
                                	<option value="N" selected="selected">否</option>
                                </select>
                            </td>
                            <td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchOrder();">查询</a>
                            </td>
                          </tr>
                          <!-- <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAdd" iconcls="icon-add" onclick="addOrder()">详情/修改状态</a>&nbsp;&nbsp;
                            </td>
                          </tr> -->
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="订单列表" id="OrderTable" toolbar="#tbOrder"></table>
	
    <div id="addEditOrderWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:350px;height:320px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditOrderForm" method="post">
            <input class="easyui-textbox" type="hidden" name="orderNumber"/>
            <input class="easyui-textbox" type="hidden" name="productNo"/>
                <table width="100%">
                    <tr>
                        <td align="center" width="20%">
                            发货当前时间：
                        </td>
                        <td width="30%">
                            <input name="deliverTime" readonly="readonly"/>
                        </td>
                    </tr>
                     <tr>
                        <td align="center" width="20%">
                            收货地址：
                        </td>
                        <td>
                            <input name="receiveAddress" readonly="readonly"/>
                        </td>
                    </tr>
                     <tr>
                        <td align="center" width="20%">
                            用户名称：
                        </td>
                        <td width="30%">
                            <input name="userName" readonly="readonly"/>
                        </td>
                    </tr>
                     <tr>
                        <td align="center" width="20%">
                            数量：
                        </td>
                        <td width="30%">
                            <input name="number" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" width="15%">
                            手机号码：
                        </td>
                        <td>
                            <input name="phoneNumber" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" width="20%">
                            发货备注：
                        </td>
                        <td width="30%">
                            <input class="easyui-validatebox textbox" type="text" name="deliverRemark" id="deliverRemark" data-options="validType:['deliverRemark','length[1,30]']"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center" width="20%">
                            快递公司：
                        </td>
                        <td width="30%">
                            <select id="express" name="express" style="width: 120px;">
                            	<c:forEach items="${expresses }" var="express1">
                                    <option value="${express1.expressName }">${express1.expressName }</option>
                                </c:forEach>
                            </select>
                            <label><a href="#" onclick="addExpress();" style="color: blue;">新增快递</a></label>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center" width="20%">
                            快递单号：
                        </td>
                        <td width="30%">
                            <input class="easyui-validatebox textbox" type="text" name="expressNo" id="expressNo" data-options="required:true,validType:['freight','length[1,20]']"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <a id="addEditOrderSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认发货</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="addEditExpressWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:270px;height:120px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditExpressForm" method="post">
                <table width="100%">
                    <tr>
                        <td>
                            快递名称：
                        </td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="expressName" id="expressName" data-options="required:true,validType:['expressName','length[1,20]']"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td colspan="3" style="padding:15px;text-align:right;">
                            <a id="addEditExpressSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
                            <a id="addEditExpressCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!-- <script type="text/javascript">
        var heightMargin = $("#toolbar").height() + 164;
        var widthMargin = $(document.body).width() - $("#tbOrder").width();
        // 第一次加载时和当窗口大小发生变化时，自动变化大小
        $('#OrderTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        $(window).resize(function () {
            $('#OrderTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        });
</script> -->
</body>
</html>
