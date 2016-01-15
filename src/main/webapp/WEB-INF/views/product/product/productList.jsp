<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>
<body class="easyui-layout">
    <script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/ueditor.all.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=context%>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
	var isInvoice={"Y":"有","N":"无"};
		$(function(){
			//表格
			$('#productTable').datagrid({ 
		        url: context + "/product/queryProductList.do",
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
	        		{title:'品号',field:'tblBrand', width:180, formatter:function(value,rowData,rowIndex){
	                	return "" + value.brandName;
	        		}},
	        		{title:'货号',field:'productNo',width:180},
			        {title:'库存数量',field:'inventoryNum',width:160},
			        {title:'市场价格',field:'marketPrice',width:160},
			        {title:'优惠价格',field:'discountPrice',width:160},
			        {title:'运费',field:'freight',width:160},
			        {title:'发票',field:'isInvoice',width:140, formatter:function(value,rowData,rowIndex){
	                	return isInvoice[value];
	        		}}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditProductSaveButton").click(function() {
				if(UE.getEditor('contentEditor').hasContents()){
	    			$("#introduction").val(UE.getEditor('contentEditor').getContent());
	    		}
	    		if(UE.getEditor('imgEditor').hasContents()){
	    			$("#brandImg").val(UE.getEditor('imgEditor').getContent());
	    		}
				console.log($("#introduction").val());
				console.log($("#brandImg").val());
				$.messager.progress();
				$("#addEditProductForm").form("submit", {
					url : context + "/product/saveOrUpdateProduct.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditProductForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditProductForm").form('clear');
						if (data.success) {
							$('#addEditProductWindow').window('close');
							$("#productTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditProductCancelButton").click(function() {
				$('#addEditProductWindow').window('close');
			});
			$("#addEditExpress1SaveButton").click(function() {
				$.messager.progress();
				$("#addEditExpress1Form").form("submit", {
					url : context + "/product/saveOrUpdateBrand.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditExpress1Form").form('validate');
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
							var _useCountyCombo = $("#brandNoProduct1").combobox();
							_useCountyCombo.combobox({
    							url: context + '/product/queryAllBrandList.do'
        					}).combobox('clear');
                			$('#addEditExpress1Window').window('close');
						}
						$("#addEditExpress1Form").form('clear');
					}
				});
			});
			//取消
			$("#addEditExpress1CancelButton").click(function() {
				$('#addEditExpress1Window').window('close');
			});
			  
		});
		function addProduct() {
			$("#addEditProductForm").form('clear');
			$('#addEditProductWindow').window({
				title : "新增商品"
			});
			$('#addEditProductWindow').window('open');
		}
		function addBrand1() {
			$("#addEditBrand1Form").form('clear');
			$('#addEditExpress1Window').window({
				title : "新增品牌"
			});
			$('#addEditExpress1Window').window('open');
		}
		function editProduct() {
			var row = $('#productTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#addEditProductForm").form('clear');
			$('#addEditProductWindow').window({
				title : "修改商品"
			});
			$('#addEditProductWindow').window('open');
			$("#introduction").val(row.introduction);
			$("#brandImg").val(row.brandImg);
			$('#addEditProductForm').form("load", {
				brandNo : row.brandNo,
				productNo : row.productNo,
				inventoryNum : row.inventoryNum,
				discountPrice : row.discountPrice,
				marketPrice : row.marketPrice,
				loadCapacity : row.loadCapacity,
				freight : row.freight,
				isInvoice:row.isInvoice,
				introduction : row.introduction,
				productId : row.productId
			});
		}
		function deleteProduct(){
			var row = $('#productTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteProduct.do";
					var data = {};
					data.productId = row.productId;
					ajaxCall("#productTable", url, data, function(res) {
						$("#productTable").datagrid("reload");
					});
				}
			});
		}
		function searchProduct(){
			var url = context + "/product/queryProductList.do";
			var data = {};
			data.productName = $("#brandNoProduct").val();
			data.productNo=$("#productNoProduct").val();
			$('#productTable').datagrid({url:url,queryParams:data});
		}
		//导出
		function productExport(){
			var url = context + "/product/productExport.do";
			$("#exportBrandNo").val($("#brandNoProduct").val());
			$("#exportProductNo").val($("#productNoProduct").val());
			document.getElementById("excelProductForm").action = url;
			document.getElementById("excelProductForm").target = "_blank";
			document.getElementById("excelProductForm").submit();
		}
		
		
	</script>
	<div id="tbProduct" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <label>品牌：</label>
                                <select id="brandNoProduct" name="brandNoProduct" style="width: 125px;">
                                    <option value="">全部</option>
                                    <c:forEach items="${tblBrands}" var="tblBrand">
                                        <option value="${tblBrand.no }">${tblBrand.brandName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label >货号：</label><input class="easyui-validatebox textbox" type="text" name="productNoProduct" id="productNoProduct" data-options="validType:['productNo','length[1,20]']"/>
                            </td>
                            <td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchProduct();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAdd" iconcls="icon-add" onclick="addProduct()">新增</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-edit" onclick="editProduct()">修改</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnDelete" iconcls="icon-remove" onclick="deleteProduct()">删除</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnDelete" iconcls="icon-print" onclick="productExport()">导出</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="商品列表" id="productTable" toolbar="#tbProduct"></table>
	
    <div id="addEditProductWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:840px;height:610px;padding:10px;">
        <div class="easyui-layout" >
            <form id="addEditProductForm" method="post">
            <input class="easyui-textbox" type="hidden" name="productId"/>
                <table width="100%">
                    <tr >
                        <td colspan="5">注意：填错商品属性信息，可能引起宝贝下架，影响您的正常销售。请认真准确填写。</td>
                    </tr>
                    <tr>
                        <td align="right" width="100px;">
                            品牌：
                        </td>
                        <td width="125px;">
                            <select class="easyui-combobox" name="brandNo" id="brandNoProduct1" style="width: 125px;" data-options="required:true, valueField:'no', textField:'brandName', panelHeight:'auto'">
                                <c:forEach items="${tblBrands}" var="tblBrand">
                                    <option value="${tblBrand.no }">${tblBrand.brandName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5">如果没有您需要的品牌，您也可以点此<a href="#" onclick="addBrand1();" style="color: blue;">申请添加品牌</a></td>
                    </tr>
                    <tr>
                        <td align="right" width="100px;">
                            货号：
                        </td>
                        <td width="125px;">
                            <input class="easyui-validatebox textbox" type="text" name="productNo" data-options="required:true,validType:['productNo','length[1,20]']"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="right" width="100px;">
                            库存数量：
                        </td>
                        <td width="125px;">
                            <input class="easyui-validatebox textbox" type="text" name="inventoryNum" id="inventoryNum" data-options="required:true,validType:['inventoryNum','number[10]']"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="right" width="100px;">
                            承载重量：
                        </td>
                        <td width="125px;">
                            <input class="easyui-validatebox textbox" type="text" name="loadCapacity" id="loadCapacity" data-options="required:true,validType:['loadCapacity','length[1,20]']"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="right" width="100px;">
                            运费：
                        </td>
                        <td width="125px;">
                            <input class="easyui-validatebox textbox" type="text" name="freight" id="freight" data-options="required:true,validType:['freight','length[1,20]']"/>
                        </td>
                        <td align="right" width="100px;">
                            发票：
                        </td>
                        <td width="125px;">
                            <input type="radio" value="Y" name="isInvoice" checked="checked"/>有&nbsp;&nbsp;<input type="radio" value="N" name="isInvoice"/>无
                        </td>
                        <td width="125px;"><input type="checkbox" value="Y" name="isMinusInventory" checked="checked"/>付款减库存</td>
                    </tr>
                    <tr>
                        <td align="right" width="100px;">
                            优惠价格：
                        </td>
                        <td width="125px;"> 
                            <input class="easyui-validatebox textbox" type="text" name="discountPrice" id="discountPrice" data-options="required:true,validType:['discountPrice','length[1,20]']"/>
                        </td>
                        <td align="right" width="100px;">
                            市场价格：
                        </td>
                        <td  width="125px;">
                            <input class="easyui-validatebox textbox" type="text" name="marketPrice" id="marketPrice" data-options="required:true,validType:['marketPrice','length[1,20]']"/>
                        </td>
                    </tr>
                     <tr>
                        <td align="right" width="100px;">
                            商品图片：
                        </td>
                        <td colspan="4">
                            <script id="imgEditor" type="text/plain" style="height:250px;"></script>
                            <input type="hidden" name="brandImg" id="brandImg"/>
                            
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="100px;">
                            商品介绍：
                        </td>
                        <td colspan="4">
                            <script id="contentEditor" type="text/plain" style="height:250px;"></script>
           					<input type="hidden" name="introduction" id="introduction"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td colspan="4" style="padding:15px;text-align:right;">
                            <a id="addEditProductSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
                            <a id="addEditProductCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="addEditExpress1Window" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditExpress1Form" method="post">
                <table width="100%">
                    <tr>
                        <td>
                            品牌名称：
                        </td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="brandName" id="brandName" data-options="required:true,validType:['brandName','length[1,20]']"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            备注：
                        </td>
                        <td colspan="3">
                            <textarea rows="5" cols="35" class="textarea easyui-validatebox" name="remark"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td colspan="3" style="padding:15px;text-align:right;">
                            <a id="addEditExpress1SaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
                            <a id="addEditExpress1CancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div style="display: none" id="downloadExcelDiv">
        <form method="post" id="excelProductForm">
             <input type="text" name="brandNo" id="exportBrandNo"/>
             <input type="text" name="productNo" id="exportProductNo"/>
        </form>
    </div>
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
    UE.getEditor('imgEditor',{
    	toolbars: [
           ['imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
            'simpleupload', 'insertimage'
			]
       	],
    });
</script>

</body>
</html>

