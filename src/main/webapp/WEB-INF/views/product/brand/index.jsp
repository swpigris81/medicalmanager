<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
		var currentStatus = {"00":"正常", "09":"禁用"};
		$(function(){
			//表格
			$('#brandTable').datagrid({ 
		        url: context + "/product/queryBrandList.do",
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
	        		{title:'品牌编号',field:'no', width:180},
	        		{title:'品牌名称名称',field:'brandName',width:180},
			        {title:'备注',field:'remark',width:180}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditBrandSaveButton").click(function() {
				$.messager.progress();
				$("#addEditBrandForm").form("submit", {
					url : context + "/product/saveOrUpdateBrand.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditBrandForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditBrandForm").form('clear');
						if (data.success) {
							$('#addEditBrandWindow').window('close');
							$("#brandTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditBrandCancelButton").click(function() {
				$('#addEditBrandWindow').window('close');
			});
		});
		function addBrand() {
			$("#addEditBrandForm").form('clear');
			$('#addEditBrandWindow').window({
				title : "新增品牌"
			});
			$('#addEditBrandWindow').window('open');
		}
		function editBrand() {
			var row = $('#brandTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#addEditBrandForm").form('clear');
			$('#addEditBrandWindow').window({
				title : "修改品牌"
			});
			$('#addEditBrandWindow').window('open');
			$('#addEditBrandForm').form("load", {
				brandName : row.brandName,
				remark : row.remark,
				no : row.no
			});
		}
		function deleteBrand(){
			var row = $('#brandTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteBrand.do";
					var data = {};
					data.no = row.no;
					ajaxCall("#brandTable", url, data, function(res) {
						$("#brandTable").datagrid("reload");
					});
				}
			});
		}
		function searchBrand(){
			var url = context + "/product/queryBrandList.do";
			var data = {};
			data.brandName = $("#brandName").val();
			$('#brandTable').datagrid({url:url,queryParams:data});
		}
	</script>
	<div id="tb" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <label for="txtProvinceName">品牌名称：</label><input type="text" id="brandName" name="brandName" style="width:100px"  />
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchBrand();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAdd" iconcls="icon-add" onclick="addBrand()">新增</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-edit" onclick="editBrand()">修改</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnDelete" iconcls="icon-remove" onclick="deleteBrand()">删除</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="品牌列表" id="brandTable" toolbar="#tb"></table>
	
    <div id="addEditBrandWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditBrandForm" method="post">
                <table width="100%">
                    <tr>
                        <td>
                            品牌名称：
                        </td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="brandName" id="brandName" data-options="required:true,validType:['brandName','length[1,20]']"/>
                            <input class="easyui-textbox" type="hidden" name="no"/>
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
                            <a id="addEditBrandSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
                            <a id="addEditBrandCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!-- <script type="text/javascript">
        var heightMargin = $("#toolbar").height() + 164;
        var widthMargin = $(document.body).width() - $("#tb").width();
        // 第一次加载时和当窗口大小发生变化时，自动变化大小
        $('#brandTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        $(window).resize(function () {
            $('#brandTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        });
</script> -->
</body>
</html>
