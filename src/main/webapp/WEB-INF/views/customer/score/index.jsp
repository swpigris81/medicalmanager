<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		var updateScoreUrl = context + "/customer/score/saveOrUpdate.do";
		var searchScoreUrl = context + "/customer/score/queryScoreList.do";
		var exportScoreUrl = context + "/customer/score/creditScoreExport.do";
		var add = '1';
		var sub = '2';
		
		$(function(){
			setOption($('#cusTypeScoreS'), customType, "", "请选择");
			//表格
			$('#gridTableScore').datagrid({ 
		        url: searchScoreUrl,
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:false,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        fitColumns:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
		            {field:'ck', width:100, checkbox:true},      
	        		{title:'用户名',field:'nickname', width:180},
	        		{title:'真实姓名',field:'realName',width:180},
	        		{title:'类型',field:'cusType',width:140,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, customType);
	        			}
	        		},
	        		{title:'信用积分',field:'creditScore', width:140}
		        ]],
		        toolbar:"#searchToolbarScore"
		    });
			// 批量扣减信用积分对话框
			$("#confirmWinScoreSubOkButton").click(function() {
				$.messager.progress();
				$("#confirmWinScoreSubForm").form("submit", {
					url : updateScoreUrl,
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#confirmWinScoreSubForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#confirmWinScoreSubForm").form('clear');
						if (data.success) {
							$('#confirmWinScoreSub').window('close');
							$("#gridTableScore").datagrid("reload");
						}
					}
				});
			});	
			// 增加信用积分对话框
			$("#confirmWinScoreAddOkButton").click(function() {
				$.messager.progress();
				$("#confirmWinScoreAddForm").form("submit", {
					url : updateScoreUrl,
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#confirmWinScoreAddForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#confirmWinScoreAddForm").form('clear');
						if (data.success) {
							$('#confirmWinScoreAdd').window('close');
							$("#gridTableScore").datagrid("reload");
						}
					}
				});
			});	
			
			// 减少信用积分对话框取消
			$("#confirmWinScoreSubCancelButton").click(function() {
				$('#confirmWinScoreSub').window('close');
			});
			// 增加信用积分对话框取消
			$("#confirmWinScoreAddCancelButton").click(function() {
				$('#confirmWinScoreAdd').window('close');
			});
			// 查看详情取消
			$("#viewWindowScoreCancelButton").click(function() {
				$('#viewWindowScore').window('close');
			});			
		});
		
		// 减少信用积分
		function updateScoreSub() {
			var rows = $('#gridTableScore').datagrid('getSelections');
			if (!rows || rows.length == 0) {
				$.messager.alert("系统提示", "请至少选择一条记录！");
				return;
			}
			var ids = "";
			for (var index in rows) {
				var row = rows[index];
				ids += row.id;
				ids += ",";
			}
			$("#confirmWinScoreSubForm").form('clear');
			$('#confirmWinScoreSub').window({
				title : "批量扣减信用积分"
			});
			$('#confirmWinScoreSub').window('open');
			$('#confirmWinScoreSubForm').form("load", {
				ids : ids,
				updateType : sub
			});
		}
		
		// 增加信用积分
		function updateScoreAdd() {
			var rows = $('#gridTableScore').datagrid('getSelections');
			if (!rows || rows.length == 0) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			if (rows.length > 1) {
				$.messager.alert("系统提示", "只能选择一条记录！");
				return;
			}
			var ids = "";
			for (var index in rows) {
				var row = rows[index];
				ids += row.id;
				ids += ",";
			}
			$("#confirmWinScoreAddForm").form('clear');
			$('#confirmWinScoreAdd').window({
				title : "增加信用积分"
			});
			$('#confirmWinScoreAdd').window('open');
			$('#confirmWinScoreAddForm').form("load", {
				ids : ids,
				nickname : row.nickname,
				realName : row.realName,
				creditScoreNow : row.creditScore,
				updateType : add
			});
		}
		
		// 查看详情
		function viewScore() {
			setOption($('#view_score_cusType'), customType, "", "请选择");
			var row = $('#gridTableScore').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#viewWindowScoreForm").form('clear');
			$('#viewWindowScore').window({
				title : "信用积分明细"
			});
			$('#viewWindowScore').window('open');
			$('#viewWindowScoreForm').form("load", {
				nickname : row.nickname,
				realName : row.realName,
				cusType : row.cusType,
				creditScore : row.creditScore
			});
		}
		
		// 查询
		function searchScore(){
			var creditScoreEnd = $('#creditScoreEndScoreS').val();
			var creditScoreStart = $('#creditScoreStartScoreS').val();
			if (creditScoreStart && creditScoreEnd && (creditScoreStart > creditScoreEnd)) {
				alert("信用积分开始值不能大于结束值，请重输入！");
				return;
			}
			
			var data = {};
			var cusType = $("#cusTypeScoreS").val();
			var nickname = $("#nicknameScoreS").val();
			var creditScore = $("#creditScoreScoreS").val();
			data.cusType = cusType;
			data.nickname = nickname;
			data.creditScore = creditScore;
			data.creditScoreStart = creditScoreStart;
			data.creditScoreEnd = creditScoreEnd;
			$('#gridTableScore').datagrid({url:searchScoreUrl,queryParams:data});
		}
		
		//导出Excel
		function exportScore() {
			var creditScoreEnd = $('#creditScoreEndScoreS').val();
			var creditScoreStart = $('#creditScoreStartScoreS').val();
			if (creditScoreStart && creditScoreEnd && (creditScoreStart > creditScoreEnd)) {
				alert("信用积分开始值不能大于结束值，请重输入！");
				return;
			}
			var cusType = $("#cusTypeScoreS").val();
			var nickname = $("#nicknameScoreS").val();
			var creditScore = $("#creditScoreScoreS").val();
			
			$("#cusTypeScoreExport").val(cusType);
			$("#nicknameScoreExport").val(nickname);
			$("#creditScoreScoreExport").val(creditScore);
			$("#creditScoreEndScoreExport").val(creditScoreEnd);
			$("#creditScoreStartScoreExport").val(creditScoreStart);
			
			document.getElementById("ExportScoreForm").action = exportScoreUrl;
			document.getElementById("ExportScoreForm").target = "_blank";
			document.getElementById("ExportScoreForm").submit();
		}
	</script>
	
	<!-- 表格 -->
	<table title="用户积分管理" id="gridTableScore"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarScore" style="padding:5px;height:auto">
        <fieldset>
            <legend>信息查询</legend>
            <form id="searchScore" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>用户名：</label>
                            	<input type="text" id="nicknameScoreS" name="nickname" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>信用积分：</label>
                            	<input class="easyui-numberbox" type="text" id="creditScoreScoreS" name="creditScore" style="width:100px" maxlength="10"  />
                            </td>
                            <td style="width: 160px;"><label>类型：</label>
					        	<select id="cusTypeScoreS" name="cusType" style="width: 100px"></select>
                            </td>                            
                            <td style="width: 300px;"><label>信用积分范围：</label>
                            	<input class="easyui-numberbox" type="text" id="creditScoreStartScoreS" name="creditScoreStart" maxlength="10" style="width:80px"/>&nbsp;至&nbsp;
                            	<input class="easyui-numberbox" type="text" id="creditScoreEndScoreS" name="creditScoreEnd" maxlength="10" style="width:80px"/>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchScore();">查询</a>&nbsp;&nbsp;
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" id="btnExport" onclick="exportScore();">&nbsp;导出EXCEL</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td colspan="4">
                                &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-remove" onclick="updateScoreSub()">批量扣减信用积分</a>&nbsp;&nbsp;
                                &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-add" onclick="updateScoreAdd()">添加信用积分</a>&nbsp;&nbsp;
                                &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-tip" onclick="viewScore()">信用积分明细</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
	<!-- 导出EXCEL窗口 -->
    <div id="ExportScoreWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:180px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="ExportScoreForm" method="post">
        		<div>
					<input type="text" id="cusTypeScoreExport" name="cusType"/>
					<input type="text" id="nicknameScoreExport" name="nickname"/>
					<input type="text" id="creditScoreScoreExport" name="creditScore"/>
					<input type="text" id="creditScoreEndScoreExport" name="creditScoreEnd"/>
					<input type="text" id="creditScoreStartScoreExport" name="creditScoreStart"/>
        		</div>
			</form>
        </div>
    </div>
    
	<!-- 查看详情窗口 -->
    <div id="viewWindowScore" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:180px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="viewWindowScoreForm" method="post">
				<table width="100%">
				    <tr>
				        <td>用户名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="nickname" id="view_score_nickname" disabled="disabled"/>
				        </td>
				        <td>真实姓名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="realName" id="view_score_realName" disabled="disabled"/>
				        </td>				        
				    </tr>
				    <tr>
				        <td>类型：</td>
				        <td>
				        	<select id="view_score_cusType" name="cusType" disabled="disabled" style="width: 137px"></select>
				        </td>
				        <td>信用积分：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="creditScore" id="view_score_creditScore" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr style="height: 80px;">
				        <td colspan="2">
				            &nbsp;
				        </td>
				        <td colspan="2" style="padding:15px;text-align:center;">
				            <a id="viewWindowScoreCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>
    </div>	
	
    <!-- 批量扣减信用积分对话框 -->
    <div id="confirmWinScoreSub" class="easyui-window" style="width:250px;height:150px" data-options="modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="confirmWinScoreSubForm" method="post">
				<input class="easyui-textbox" type="hidden" name="ids" id="sub_ids"/>
				<input class="easyui-textbox" type="hidden" name="updateType" id="sub_updateType"/>
				<table width="100%">
				    <tr style="height: 70px;">
				        <td colspan="3" align="center">批量扣减&nbsp;<input class="easyui-numberbox textbox" maxlength="10" type="text" name="creditScore" id="creditScoreSub" style="width: 120px;" data-options="required:true"/>&nbsp;积分</td>
				    </tr>
				    <tr>
				        <td align="right" width="70px;">
	                        <a id="confirmWinScoreSubOkButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认</a>
				        </td>
				        <td align="left" width="30px;">
				        </td>
				        <td align="left" width="70px;">
				            <a id="confirmWinScoreSubCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>            
    </div>
    
    <!-- 批量扣减信用积分对话框 -->
    <div id="confirmWinScoreSub" class="easyui-window" style="width:250px;height:150px" data-options="modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="confirmWinScoreSubForm" method="post">
				<input class="easyui-textbox" type="hidden" name="ids" id="sub_ids"/>
				<input class="easyui-textbox" type="hidden" name="updateType" id="sub_updateType"/>
				<table width="100%">
				    <tr style="height: 70px;">
				        <td colspan="3" align="center">批量扣减&nbsp;<input class="easyui-numberbox textbox" maxlength="10" type="text" name="creditScore" id="creditScoreSub" style="width: 120px;" data-options="required:true"/>&nbsp;积分</td>
				    </tr>
				    <tr>
				        <td align="right" width="70px;">
	                        <a id="confirmWinScoreSubOkButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认</a>
				        </td>
				        <td align="left" width="30px;">
				        </td>
				        <td align="left" width="70px;">
				            <a id="confirmWinScoreSubCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>            
    </div>
    
    <!-- 增加信用积分对话框 -->
    <div id="confirmWinScoreAdd" class="easyui-window" style="width:500px;height:200px;padding:10px;" data-options="modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="confirmWinScoreAddForm" method="post">
				<input class="easyui-textbox" type="hidden" name="ids" id="add_ids"/>
				<input class="easyui-textbox" type="hidden" name="updateType" id="add_updateType"/>
				<table width="100%">
				    <tr>
				        <td>用户名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="nickname" id="add_nickname" disabled="disabled"/>
				        </td>
				        <td>真实姓名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="realName" id="add_realName" disabled="disabled"/>
				        </td>
				    </tr>
					<tr>
				        <td>信用积分：</td>
				        <td>
				            <input class="easyui-numberbox textbox" type="text" name="creditScoreNow" id="add_creditScorezNow" disabled="disabled"/>
				        </td>
					</tr>
				    <tr>
				        <td>添加信用积分：</td>
				        <td colspan="3">
				        	<input class="easyui-numberbox textbox" maxlength="10" type="text" name="creditScore" id="add_creditScore" data-options="required:true"/>&nbsp;积分</td>
				        </td>
				    </tr>
				    <tr style="height: 35px;"></tr>
				    <tr>
				        <td align="center" colspan="2">
	                        <a id="confirmWinScoreAddOkButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认</a>
				        </td>
				        <td align="center" colspan="2">
				            <a id="confirmWinScoreAddCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>            
    </div>    
</body>
</html>
