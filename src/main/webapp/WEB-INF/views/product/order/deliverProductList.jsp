<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

 <head>
    <script type="text/javascript" charset="utf-8" src="ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="lang/zh-cn/zh-cn.js"></script>
 </head>
<body class="easyui-layout">
	<script type="text/javascript">
	var isPay={"Y":"是","N":"否"};
		$(function(){
			//表格
			$('#deliverProductTable').datagrid({ 
		        url: context + "/order/queryDeliverProductList.do",
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
		        	{title:'订单号',field:'orderNumber',width:180}, 
	        		{title:'货号',field:'productNo', width:180},
	        		{title:'数量',field:'number',width:180},
			        {title:'用户名称',field:'userName',width:160},
			        {title:'手机号码',field:'phoneNumber',width:160},
			        {title:'发货备注',field:'deliverRemark',width:160},
			        {title:'快递公司',field:'express',width:160},
			        {title:'快递单号',field:'expressNo',width:160},
			        {title:'发货时间',field:'deliverTime',width:140}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditdeliverProductSaveButton").click(function() {
				$.messager.progress();
				$("#addEditdeliverProductForm").form("submit", {
					url : context + "/order/deliverProduct.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditdeliverProductForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditdeliverProductForm").form('clear');
						if (data.success) {
							$('#addEditdeliverProductWindow').window('close');
							$("#deliverProductTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditdeliverProductCancelButton").click(function() {
				$('#addEditdeliverProductWindow').window('close');
			});
			  
		});
		function adddeliverProduct() {
			$("#addEditdeliverProductForm").form('clear');
			$('#addEditdeliverProductWindow').window({
				title : "新增商品"
			});
			$('#addEditdeliverProductWindow').window('open');
		}
		function deliverProduct(index) {
			var row = $('#deliverProductTable').datagrid('getData').rows[index];
			$("#addEditdeliverProductForm").form('clear');
			$('#addEditdeliverProductWindow').window({
				title : "发货"
			});
			$('#addEditdeliverProductWindow').window('open');
			var date=new Date();
			$('#addEditdeliverProductForm').form("load", {
				deliverTime : DateUtil.dateToStr('yyyy-MM-dd HH:mm:ss', date),
				receiveAddress : row.receiveAddress,
				phoneNumber : row.phoneNumber,
				userName : row.userId,
				orderNumber : row.orderNumber,
				number : row.number,
				productNo : row.productNo
			});
		}
		function deletedeliverProduct(){
			var row = $('#deliverProductTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteProduct.do";
					var data = {};
					data.productId = row.productId;
					ajaxCall("#deliverProductTable", url, data, function(res) {
						$("#deliverProductTable").datagrid("reload");
					});
				}
			});
		}
		function searchdeliverProduct(){
			var url = context + "/order/queryDeliverProductList.do";
			var data = {};
			data.beginDate = $("#beginDateDeliverProduct").datebox('getValue');
			data.endDate=$("#endDateDeliverProduct").datebox('getValue');
			data.phoneNumber=$("#phoneNumberDeliverProduct").val();
			data.expressNo=$("#expressNoDeliverProduct").val();
			$('#deliverProductTable').datagrid({url:url,queryParams:data});
		}
		
		
		
	</script>
	<div id="tbDeliverProduct" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                时间段：<input class="easyui-datebox" name="beginDateDeliverProduct" id="beginDateDeliverProduct"/>&nbsp;至&nbsp;
                                <input class="easyui-datebox" name="endDateDeliverProduct" id="endDateDeliverProduct"/>
                            </td>
                            <td>
                                <label >手机号码：</label>
                                <input class="easyui-textbox" name="phoneNumberDeliverProduct" id="phoneNumberDeliverProduct"/>
                            </td>
                             <td>
                                <label >快递单号：</label>
                                <input class="easyui-textbox" name="expressNoDeliverProduct" id="expressNoDeliverProduct"/>
                            </td>
                            <td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchdeliverProduct();">查询</a>
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="发货信息列表" id="deliverProductTable" toolbar="#tbDeliverProduct"></table>
	
    <div id="addEditdeliverProductWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:350px;height:320px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditdeliverProductForm" method="post">
            <input class="easyui-textbox" type="hidden" name="orderNumber"/>
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
                            	<option value="申通快递">申通快递</option>
                            	<option value="圆通快递">圆通快递</option>
                            </select>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="center" width="20%">
                            快递单号：
                        </td>
                        <td width="30%">
                            <input class="easyui-validatebox textbox" type="text" name="expressNo" id="expressNo" data-options="required:true,validType:['expressNo','length[1,20]']"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <a id="addEditdeliverProductSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认发货</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!-- <script type="text/javascript">
        var heightMargin = $("#toolbar").height() + 164;
        var widthMargin = $(document.body).width() - $("#tbDeliverProduct").width();
        // 第一次加载时和当窗口大小发生变化时，自动变化大小
        $('#deliverProductTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        $(window).resize(function () {
            $('#deliverProductTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        });
</script> -->
</body>
</html>
