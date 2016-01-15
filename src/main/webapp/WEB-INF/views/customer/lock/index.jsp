<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		$(function(){
			//表格
			$('#gridTableLock').datagrid({ 
		        url: context + "/customer/lock/queryLockList.do",
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
			        {title:'手机号码',field:'phone',width:180},
	        		{title:'身份证',field:'idCardNo', width:260},
	        		{title:'类型',field:'cusType',width:140,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, customType);
	        			}
	        		},	        		
			        {title:'省份',field:'provinceName',width:140},
	        		{title:'市',field:'cityName', width:140},
	        		{title:'注册时间',field:'registerTime',width:240},
	        		{title:'锁定时间',field:'lockTime',width:240},
			        {title:'锁定到期时间',field:'lockExpireTime',width:240}
		        ]],
		        toolbar:"#searchToolbarLock"
		    });
			// 确认对话框确认
			$("#confirmWinLockOkButton").click(function() {
				$.messager.progress();
				$("#confirmWinLockForm").form("submit", {
					url : context + "/customer/lock/saveOrUpdate.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#confirmWinLockForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#confirmWinLockForm").form('clear');
						if (data.success) {
							$('#confirmWinLock').window('close');
							$("#gridTableLock").datagrid("reload");
						}
					}
				});
			});	
			
			// 确认对话框取消
			$("#confirmWinLockCancelButton").click(function() {
				$('#confirmWinLock').window('close');
			});
		});
		
		// 修改信息
		function updateLock() {
			var rows = $('#gridTableLock').datagrid('getSelections');
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
			$("#confirmWinLockForm").form('clear');
			$('#confirmWinLock').window({
				title : "确认锁定"
			});
			$('#confirmWinLock').window('open');
			$('#confirmWinLockForm').form("load", {
				remark : ids
			});
		}
		
		function searchLock(){
			var url = context + "/customer/lock/queryLockList.do";
			var registerTimeStart = $('#registerTimeStartLockS').datetimebox('getValue');
			var registerTimeEnd = $('#registerTimeEndLockS').datetimebox('getValue');
			if (registerTimeStart && registerTimeEnd && (registerTimeStart > registerTimeEnd)) {
				alert("开始时间不能大于结束时间，请重输入！");
				return;
			}
			
			var data = {};
			var phone = $("#phoneLockS").val();
			var realName = $("#realNameLockS").val();
			data.phone = phone;
			data.realName = realName;
			data.registerTimeStart = registerTimeStart;
			data.registerTimeEnd = registerTimeEnd;
			
			$('#gridTableLock').datagrid({url:url,queryParams:data});
		}
	</script>
	
	<!-- 表格 -->
	<table title="锁定用户管理" id="gridTableLock"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarLock" style="padding:5px;height:auto">
        <fieldset>
            <legend>信息查询</legend>
            <form id="searchLock" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>手机号：</label>
                            	<input type="text" id="phoneLockS" name="phone" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>真实姓名：</label>
                            	<input type="text" id="realNameLockS" name="realName" style="width:100px"  />
                            </td>
                            <td style="width: 400px;"><label>注册时间：</label>
                            	<input class="easyui-datetimebox" type="text" id="registerTimeStartLockS" name="registerTimeStart" data-options="showSeconds:false" style="width:140px"/>&nbsp;至&nbsp;
                            	<input class="easyui-datetimebox" type="text" id="registerTimeEndLockS" name="registerTimeEnd" data-options="showSeconds:false" style="width:140px"/>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearchLock" onclick="searchLock();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td colspan="4">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-edit" onclick="updateLock()">锁定选中</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
    <!-- 确认对话框 -->
    <div id="confirmWinLock" class="easyui-window" style="width:250px;height:150px" data-options="modal:true,closed:true">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="confirmWinLockForm" method="post">
				<input class="easyui-textbox" type="hidden" name="remark" id="temp_ids"/>
				<table width="100%">
				    <tr style="height: 70px;">
				        <td colspan="3" align="center">锁定&nbsp;<input class="easyui-numberbox textbox" maxlength="4" type="text" name="creditScore" id="temp_lock_day" style="width: 100px;" data-options="required:true,validType:['length[1,4]']"/>&nbsp;天</td>
				    </tr>
				    <tr>
				        <td align="right" width="70px;">
	                        <a id="confirmWinLockOkButton" href="#" class="easyui-linkbutton" icon="icon-ok">确认</a>
				        </td>
				        <td align="left" width="30px;">
				        </td>
				        <td align="left" width="70px;">
				            <a id="confirmWinLockCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>            
    </div>    
</body>
</html>
