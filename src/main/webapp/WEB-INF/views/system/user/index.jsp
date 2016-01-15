<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var currentStatus = {"00":"正常", "09":"禁用"};
		$(function(){
			//表格
			$('#userTable').datagrid({ 
		        //url: context + "/user/userList.do",
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
	        		{title:'管理员账号',field:'userCode', width:180},
	        		{title:'管理组名称',field:'userRole',width:180, formatter:function(value,rowData,rowIndex){
	                	return "" + value.role.roleName;
	        		}},
			        {title:'真实姓名',field:'userName',width:180},
			        {title:'手机号',field:'userMobile',width:120},
	        		{title:'上次登录时间',field:'lastLoginTime',width:180},
	        		{title:'上次登录IP',field:'lastLoginIp',width:180},
	        		{title:'当前状态',field:'currentStatus',width:80, formatter:function(value,rowData,rowIndex){
	                	return "" + currentStatus[value];
	        		}},
	        		{title:'主键',field:'id',width:80, hidden:true}
		        ]],
		        toolbar:"#searchUserToolbar"
		    });
			//保存用户
			$("#addEditUserSaveButton").click(function(){
				var userRoleId = $("#userRoleCombo").combo("getValue");
				var newOrUpdate = $("#newOrUpdate").val();
				var param = {};
				if(newOrUpdate == "new"){
					var userPassword = $("#userPassword").val();
					var comfirmPass = $("#userConfirmPassword").val();
					if(!$.trim(userPassword)){
						$.messager.alert("系统提示", "请输入密码！");
						return;
					}
					if(!$.trim(comfirmPass)){
						$.messager.alert("系统提示", "请输入确认密码！");
						return;
					}
					if($.trim(comfirmPass) != $.trim(userPassword)){
						$.messager.alert("系统提示", "两次输入密码不一致！");
						return;
					}
					param.userPassword = $.trim(userPassword);
				}
				param.userRoleId = userRoleId;
				$.messager.progress();
				$("#addEditUserForm").form("submit", {
					url:context + "/user/saveUser.do",
					dataType:"json",
					data:param,
					onSubmit:function(){
						var isValid = $("#addEditUserForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditUserForm").form('clear');
						if(data.success){
							$('#addEditUserWindow').window('close');
							$("#userTable").datagrid("reload");
						}
					}
				});
			});
			//保存角色
			$("#userRoleSaveButton").click(function(){
				$.messager.progress();
				$("#userRoleForm").form("submit", {
					url:context + "/user/saveUserRole.do",
					dataType:"json",
					onSubmit:function(){
						var isValid = $("#userRoleForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						//console.log(result);
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#userRoleForm").form('clear');
						if(data.success){
							$('#userRoleWindow').window('close');
							$("#userTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#userRoleCancelButton").click(function(){
				$("#userRoleWindow").window("close");
			});
			
			//取消
			$("#addEditUserCancelButton").click(function(){
				$("#addEditUserWindow").window("close");
			});
			
			//省市级联查询
			$("#userProvince").combobox({
				onSelect: function (record) {
					_userCityCombo.combobox({
						url: context + '/administrative/searchCityByProvice.do?provinceid=' + record.value
					}).combobox('clear');
				}
			});
			var _userCityCombo = $("#userCity").combobox();
		});
		
		//新增用户
		function addNewUser(){
			$("#addEditUserForm").form('clear');
    		$('#addEditUserWindow').window({title:"新增用户"});
    		$('#addEditUserWindow').window('open');
    		$("#addEditUserCode").attr("readonly",false);
    		$("#newOrUpdate").val("new");
		}
		//修改用户
		function updateUser(){
    		var checkedItems = $('#userTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length != 1){
				$.messager.alert("系统提示", "请选择一条记录！");
    			return;
			}
			var row = checkedItems[0];
    		$("#addEditUserForm").form('clear');
    		$('#addEditUserWindow').window({title:"修改用户"});
    		$('#addEditUserWindow').window('open');
    		$('#addEditUserForm').form("load",{
    			userProvince:row.userProvince,
    			userCity:row.userCity,
    			userUnit:row.userUnit,
    			userCode:row.userCode,
    			userName:row.userName,
    			userMobile:row.userMobile,
    			id:row.id
    		});
    		$("#userRoleCombo").combobox('setValue', row.userRole.role.roleId);
    		$("#addEditUserCode").attr("readonly",true);
    		$("#newOrUpdate").val("update");
		}
		//禁用用户
		function invalidUser(){
			var checkedItems = $('#userTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length < 1){
				$.messager.alert("系统提示", "请选择至少一条记录！");
    			return;
			}
			var userId = [];
    		$.each(checkedItems, function(index, item){
    			userId.push(item.id);
    		});
    		$.messager.confirm('系统提示', '确定禁用所选管理员?', function(r){
    			if(r){
    				var url = context + "/user/invalidUser.do";
    				var data = {};
    				data.userIds = userId.join(",");
    				ajaxCall("#userTable", url, data, function(res){
	        			$("#userTable").datagrid("reload");
	        		});
    			}
    		});
		}
		//启用管理员
		function validUser(){
			var checkedItems = $('#userTable').datagrid('getChecked');
			if(!checkedItems || checkedItems.length < 1){
				$.messager.alert("系统提示", "请选择至少一条记录！");
    			return;
			}
			var userId = [];
    		$.each(checkedItems, function(index, item){
    			userId.push(item.id);
    		});
    		$.messager.confirm('系统提示', '确定启用所选管理员?', function(r){
    			if(r){
    				var url = context + "/user/validUser.do";
    				var data = {};
    				data.userIds = userId.join(",");
    				ajaxCall("#userTable", url, data, function(res){
	        			$("#userTable").datagrid("reload");
	        		});
    			}
    		});
		}
		
		//删除用户
		function deleteUser(){
			/* var row = $('#userTable').datagrid('getSelected');
    		if(!row){
    			$.messager.alert("系统提示", "请选择一条记录！");
    			return;
    		} */
    		var checkedItems = $('#userTable').datagrid('getChecked');
    		if(!checkedItems || checkedItems.length < 1){
				$.messager.alert("系统提示", "请选择至少一条记录！");
    			return;
			}
			var userId = [];
    		$.each(checkedItems, function(index, item){
    			userId.push(item.id);
    		});
    		$.messager.confirm('系统提示', '确定删除用户?', function(r){
    			if(r){
    				var url = context + "/user/deleteUser.do";
    				var data = {};
    				data.userIds = userId.join(",");
    				ajaxCall("#userTable", url, data, function(res){
	        			$("#userTable").datagrid("reload");
	        		});
    			}
    		});
		}
		//分配角色
		function updateUserRole(){
			var row = $('#userTable').datagrid('getSelected');
    		if(!row){
    			$.messager.alert("系统提示", "请选择一条记录！");
    			return;
    		}
    		$(":text[name='userCode']").val(row.userCode);
    		$(":hidden[name='userId']").val(row.id);
    		//读取当前角色的权限信息
    		var url = context + "/user/userRoleList.do";
    		var data = {"userId" : row.id};
    		ajaxCallNoMsg("#userTable", url, data, function(res){
    			var data = $.parseJSON(res.msg);
    			var userRole = "";
    			var id = "";
    			for(i=0; i<data.length; i++){
    				userRole = data[0].roleId;
    				id = data[0].id;
    				break;
    			}
    			$("#userRoleId").val(id);
    			$("#userRoleCombo").combobox('setValue', userRole);
    		});
    		$('#userRoleWindow').window({title:"角色分配"});
    		$('#userRoleWindow').window('open');
		}
		//查询用户
		function searchUser(){
			var userCode = $("#searchUserCode").val();
			var userRoleId = $("#searchUserRoleId").combo("getValue");
			var currentStatus = $("#searchProductStatusCombo").combo("getValue");
			var queryParams = $('#userTable').datagrid('options').queryParams;
			queryParams.userCode = userCode;
			queryParams.roleId = userRoleId;
			queryParams.currentStatus = currentStatus;
			$('#userTable').datagrid('options').queryParams = queryParams;
			$('#userTable').datagrid('options').url = context + "/user/userList.do";
			$('#userTable').datagrid("load");
		}
		
	</script>
	<table title="管理员列表" id="userTable"></table>
	
	<div id="addEditUserWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<form id="addEditUserForm" method="post">
				<table width="100%">
					<tr>
           				<td>
           					管理组：
           				</td>
           				<td colspan="3">
           					<select id="userRoleCombo" class="easyui-combobox" name="userRoleId" data-options="required:true,editable:false" style="width: 305px">
           						<c:if test="${roleList != null && fn:length(roleList) > 0}">
           							<c:forEach items="${roleList }" var="role" varStatus="status">
           								<option value="${role.roleId }">${role.roleName }</option>
           							</c:forEach>
           						</c:if>
           					</select>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					省份：
           				</td>
           				<td>
           					<!-- <input class="easyui-validatebox textbox" type="text" name="userProvince" data-options="required:true"/> -->
           					<select id="userProvince" class="easyui-combobox" name="userProvince" data-options="required:true,editable:false" style="width:135px">
								<option value="">请选择</option>
								<c:if test="${provinceList != null && fn:length(provinceList) > 0}">
									<c:forEach items="${provinceList }" var="province" varStatus="status">
										<option value="${province.provinceid }">${province.province }</option>
									</c:forEach>
								</c:if>
							</select>
           				</td>
           				<td>
           					市：
           				</td>
           				<td>
           					<!-- <input class="easyui-validatebox textbox" type="text" name="userCity" data-options="required:true"/> -->
           					<input class="easyui-combobox" id="userCity" style="width:135px"  name="userCity"
                                 data-options="required:true, valueField:'cityid', textField:'city', panelHeight:'auto'" >
           				</td>
           			</tr>
           			<tr>
           				<td>
           					单位：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="userUnit" data-options="required:true"/>
           				</td>
           			</tr>
					<tr>
           				<td>
           					管理员账号：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="userCode" id="addEditUserCode" data-options="required:true,validType:['userCode','length[6,20]']"/>
           					<input class="easyui-textbox" type="hidden" name="id"/>
           				</td>
           				<td>
           					密码：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="password" name="userPassword" id="userPassword" data-options="validType:'safepass'"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					确认密码：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="password" name="userConfirmPassword" 
           						id="userConfirmPassword"
           						validType="equalTo['#userPassword']" invalidMessage="两次输入密码不匹配"/>
           				</td>
           				<td>
           					真实姓名：
           				</td>
           				<td>
           					<input class="easyui-validatebox textbox" type="text" name="userName" data-options="required:true"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					手机号：
           				</td>
           				<td colspan="3">
           					<input class="easyui-validatebox textbox" type="text" name="userMobile" data-options="required:true,validType:'mobile'"/>
           				</td>
           			</tr>
           			<tr>
           				<td>
           					&nbsp;
           					<input type="hidden" id="newOrUpdate"/>
           				</td>
           				<td colspan="3" style="padding:15px;text-align:right;">
           					<a id="addEditUserSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
           					<a id="addEditUserCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           				</td>
           			</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- Toolbar -->
	<div id="searchUserToolbar" style="padding:5px;height:100px">
		<fieldset>
	        <legend>信息查询</legend>
			<div style="margin-bottom: 5px;">    
				  管理员账号: <input class="easyui-textbox" id="searchUserCode" style="width:100px">
		                          管理组: 
	              	<select id="searchUserRoleId" class="easyui-combobox" name="roleId" data-options="required:true,editable:false" style="width: 100px">
						<option value="">全部</option>
						<c:if test="${roleList != null && fn:length(roleList) > 0}">
							<c:forEach items="${roleList }" var="role" varStatus="status">
								<option value="${role.roleId }">${role.roleName }</option>
							</c:forEach>
						</c:if>
					</select>
		          当前状态: <select id="searchProductStatusCombo" class="easyui-combobox" data-options="editable:false" style="width:100px">
					<option value="">全部</option>
					<option value="09">已禁用</option>
					<option value="00">正常</option>
				</select>
		        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search">查询</a>    
		    </div>
			<div>
		        <div style="float: left;"><a href="javascript:addNewUser()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增管理员</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:updateUser()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改管理员</a></div>
		        <div class="datagrid-btn-separator"></div>    
		        <div style="float: left;"><a href="javascript:invalidUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">禁用管理员</a></div>
		        <div class="datagrid-btn-separator"></div>    
		        <div style="float: left;"><a href="javascript:validUser()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">启用管理员</a></div>
		        <div class="datagrid-btn-separator"></div>
		        <div style="float: left;"><a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除管理员</a></div>
		    </div>
		</fieldset>
	</div>
</body>
</html>
