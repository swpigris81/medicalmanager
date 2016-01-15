<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var currentStatus = {"00":"正常", "09":"禁用"};
		$(function(){
			//表格
			$('#blackListTable').datagrid({ 
		        //url: context + "/blackList/blackListList.do",
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:false,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        pagination:true,
		        pageSize:10,
		        columns:[[ 
					{field:'ck', title:'请选择', checkbox:true},
	        		{title:'用户名',field:'userName', width:180},
			        {title:'手机号',field:'userMobile',width:180},
			        {title:'类型',field:'typeName',width:120, hidden:true},
	        		{title:'备注',field:'remark',width:480},
	        		{title:'主键',field:'id',width:80, hidden:true}
		        ]],
		        toolbar:"#searchblackListToolbar"
		    });
			//保存
			$("#addEditblackListSaveButton").click(function(){
				$.messager.progress();
				$("#addEditblackListForm").form("submit", {
					url:context + "/blacklist/saveBlacklist.do",
					dataType:"json",
					onSubmit:function(){
						var isValid = $("#addEditblackListForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditblackListForm").form('clear');
						if(data.success){
							$('#addEditblackListWindow').window('close');
							searchblackList();
						}
					}
				});
			});
			//取消
			$("#addEditblackListCancelButton").click(function(){
				$("#addEditblackListWindow").window("close");
			});
		});
		
		//新增
		function addNewblackList(){
			$("#addEditblackListForm").form('clear');
    		$('#addEditblackListWindow').window({title:"新增黑名单"});
    		$('#addEditblackListWindow').window('open');
		}
		//修改
		function updateblackList(){
    		var checkedItems = $('#blackListTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
    		$("#addEditblackListForm").form('clear');
    		$('#addEditblackListWindow').window({title:"修改黑名单"});
    		$('#addEditblackListWindow').window('open');
    		$('#addEditblackListForm').form("load",{
    			userName:row.userName,
    			userMobile:row.userMobile,
    			remark:row.remark,
    			id:row.id
    		});
		}
		//删除
		function deleteblackList(){
    		var checkedItems = $('#blackListTable').datagrid('getChecked');
    		if(!checkedItems || checkedItems.length < 1){
				$.messager.alert("系统提示", "请选择至少一条记录！");
    			return;
			}
			var blackListId = [];
    		$.each(checkedItems, function(index, item){
    			blackListId.push(item.id);
    		});
    		$.messager.confirm('系统提示', '确定删除黑名单?', function(r){
    			if(r){
    				var url = context + "/blacklist/deleteBlacklist.do";
    				var data = {};
    				data.blackIds = blackListId.join(",");
    				ajaxCall("#blackListTable", url, data, function(res){
	        			$("#blackListTable").datagrid("reload");
	        		});
    			}
    		});
		}
		//查询
		function searchblackList(){
			var userName = $("#searchUserName").val();
			var queryParams = $('#blackListTable').datagrid('options').queryParams;
			queryParams.userName = userName;
			$('#blackListTable').datagrid('options').queryParams = queryParams;
			$('#blackListTable').datagrid('options').url = context + "/blacklist/blackList.do";
			$('#blackListTable').datagrid("load");
		}
		
	</script>
	<table title="黑名单列表" id="blackListTable"></table>
	
	<div id="addEditblackListWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditblackListForm" method="post">
				<table width="100%">
           			<tr>
           				<td align="right">
           					用户名：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="userName" data-options="required:true" style="width: 305px"/>
           				</td>
           			</tr>
					<tr>
           				<td align="right">
           					手机号：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="userMobile" style="width: 305px" data-options="required:true,validType:['mobile']"/>
           					<input class="easyui-textbox" type="hidden" name="id"/>
           				</td>
           			</tr>
           			<tr>
           				<td align="right">
           					备注：
           				</td>
           				<td colspan="3">
           					<textarea rows="4" cols="40" class="textarea easyui-validatebox" name="remark"></textarea>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           					<input type="hidden" id="newOrUpdate"/>
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditblackListSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
           					<a id="addEditblackListCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- Toolbar -->
	<div id="searchblackListToolbar" style="padding:5px;height:100px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				  用户名: <input class="easyui-textbox" id="searchUserName" style="width:100px">
		        <a href="javascript:searchblackList()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
			<div>
		        <div style="float: left;"><a href="javascript:addNewblackList()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增黑名单</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:updateblackList()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改黑名单</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:deleteblackList()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除黑名单</a></div>
		    </div>
	    </fieldset>
	</div>
</body>
</html>
