<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
		var useStatus = {"Y":"已分配", "N":"未分配"};
		$(function(){
			//表格
			$('#MachineUseTable').datagrid({ 
		        url: context + "/machineCode/queryMachineUseList.do",
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
	        		{title:'序号',field:'id', width:180},
	        		{title:'开通时间',field:'openTime',width:180},
	        		{title:'用户名',field:'userName',width:180},
			        {title:'手机号',field:'phoneNumber',width:180},
			        {title:'机器码',field:'machineCode',width:180},
			        {title:'备注',field:'remark',width:180}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditMachineUseSaveButton").click(function() {
				$.messager.progress();
				$("#addEditMachineUseForm").form("submit", {
					url : context + "/machineCode/produceMachineCodes.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditMachineUseForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditMachineUseForm").form('clear');
						if (data.success) {
							$('#addEditMachineUseWindow').window('close');
							$("#MachineUseTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditMachineUseCancelButton").click(function() {
				$('#addEditMachineUseWindow').window('close');
			});
		});
		function addMachineUse() {
			$("#addEditMachineUseForm").form('clear');
			$('#addEditMachineUseWindow').window({
				title : "批量生成机器码"
			});
			$('#addEditMachineUseWindow').window('open');
		}
		function editMachineUse() {
			var row = $('#MachineUseTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#addEditMachineUseForm").form('clear');
			$('#addEditMachineUseWindow').window({
				title : "修改品牌"
			});
			$('#addEditMachineUseWindow').window('open');
			$('#addEditMachineUseForm').form("load", {
				MachineUseName : row.MachineUseName,
				remark : row.remark,
				no : row.no
			});
		}
		function deleteMachineUse(){
			var row = $('#MachineUseTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$.messager.confirm('系统提示', '确定删除?', function(r) {
				if (r) {
					var url = context + "/product/deleteMachineUse.do";
					var data = {};
					data.no = row.no;
					ajaxCall("#MachineUseTable", url, data, function(res) {
						$("#MachineUseTable").datagrid("reload");
					});
				}
			});
		}
		function searchMachineUse(){
			var url = context + "/machineCode/queryMachineUseList.do";
			var data = {};
			data.phoneNumber = $("#phoneNumberMachineUse").val();
			$('#MachineUseTable').datagrid({url:url,queryParams:data});
		}
	</script>
	<div id="tbMachineUse" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <label for="txtProvinceName">手机号码：</label>
                                <input class="easyui-validatebox textbox" type="text" name="phoneNumberMachineUse" id="phoneNumberMachineUse" data-options="required:true,validType:['phoneNumber','length[10,12]']"/>
                            </td>
                            <td>&nbsp;</td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchMachineUse();">查询</a>
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="品牌列表" id="MachineUseTable" toolbar="#tbMachineUse"></table>
	
    <div id="addEditMachineUseWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:270px;height:120px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditMachineUseForm" method="post">
                <table width="100%">
                    <tr>
                        <td>
                            机器码数量：
                        </td>
                        <td>
                            <input class="easyui-validatebox textbox" type="text" name="number" id="number" data-options="required:true,validType:['number','number']"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td colspan="3" style="padding:15px;text-align:right;">
                            <a id="addEditMachineUseSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
                            <a id="addEditMachineUseCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!-- <script type="text/javascript">
        var heightMargin = $("#toolbar").height() + 164;
        var widthMargin = $(document.body).width() - $("#tbMachineUse").width();
        // 第一次加载时和当窗口大小发生变化时，自动变化大小
        $('#MachineUseTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        $(window).resize(function () {
            $('#MachineUseTable').resizeDataGrid(heightMargin, widthMargin, 0, 0);
        });
</script> -->
</body>
</html>
