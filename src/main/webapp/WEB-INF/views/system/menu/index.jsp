<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body>
	<script type="text/javascript">
		var menuLevel = {"0":"菜单", "1":"按钮"};
		var menuHide = {"0":"否", "1":"是", "":"未知", "null":"未知"};
		$(function(){
			//表格
			$('#menuTable').treegrid({ 
		        url: context + "/menu/menuList.do",
		        idField:'id',
		        treeField:'text',
		        animate:"true",
		        rownumbers:"true",
		        fit:true,
		        //pagination:true,
		        //pageSize:5,
		        columns:[[ 
					{title:'菜单名称',field:'text',formatter:function(value,rowData,rowIndex){
						return " " + rowData.text;
					},width:180},
	        		{title:'菜单请求路径',field:'url',formatter:function(value,rowData,rowIndex){
		                	return " " + rowData.attributes.menuUrl;
		        		},width:180},
			        {title:'菜单级别',field:'menuLevel',formatter:function(value,rowData,rowIndex){
		                	return " " + menuLevel[rowData.attributes.menuLevel];
		        		},width:80},
				    {title:'是否显示',field:'isShow',formatter:function(value,rowData,rowIndex){
		                	return " " + menuHide[rowData.attributes.isShow];
		        		},width:80},
	        		{title:'创建人',field:'createOper',formatter:function(value,rowData,rowIndex){
		                	return " " + rowData.attributes.createOper;
		        		},width:80},
	        		{title:'创建日期',field:'createDate',formatter:function(value,rowData,rowIndex){
		                	return " " + rowData.attributes.createDate;
		        		},width:80},
	        		{title:'创建时间',field:'createTime',formatter:function(value,rowData,rowIndex){
			                return " " + rowData.attributes.createTime;
		        		},width:80},
	        		{title:'修改人',field:'modifyOper',formatter:function(value,rowData,rowIndex){
		                	return " " + rowData.attributes.modifyOper;
		        		},width:80},
	        		{title:'修改日期',field:'modifyDate',formatter:function(value,rowData,rowIndex){
		                	return " " + rowData.attributes.modifyDate;
		        		},width:80},
	        		{title:'修改时间',field:'modifyTime',formatter:function(value,rowData,rowIndex){
			                return " " + rowData.attributes.modifyTime;
		        		},width:80},
	        		{title:'备注',field:'remark',formatter:function(value,rowData,rowIndex){
			                return " " + rowData.attributes.remark;
		        		},width:80},
		        	{title:'上级菜单',field:'parentId', hidden:true, formatter:function(value,rowData,rowIndex){
			                return " " + rowData.attributes.parentId;
		        		}}
		        ]],
		        toolbar:[{
		        	text:"新增菜单",
		        	iconCls:"icon-add",
		        	handler:function(){
		        		$("#menuForm").form('clear');
		        		$('#menuWindow').window({title:"新增菜单"});
		        		$('#menuWindow').window('open');
		        	}
		        },"-",{
		        	text:"修改菜单",
		        	iconCls:"icon-edit",
		        	handler:function(){
		        		var row = $('#menuTable').datagrid('getSelected');
		        		if(!row){
		        			$.messager.alert("系统提示", "请选择一条记录！");
		        			return;
		        		}
		        		if(!row.attributes.parentId){
		        			$.messager.alert("系统提示", "此菜单不能修改！");
		        			return;
		        		}
		        		$("#menuForm").form('clear');
		        		$('#menuWindow').window({title:"修改菜单"});
		        		$('#menuWindow').window('open');
		        		$('#menuForm').form("load",{
		        			menuName:row.text,
		        			menuId:row.id,
		        			menuUrl:row.attributes.menuUrl,
		        			parentId:row.attributes.parentId,
		        			menuLevel:row.attributes.menuLevel,
		        			isShow:row.attributes.isShow
		        		});
		        	}
		        },"-",{
		        	text:"删除菜单",
		        	iconCls:"icon-remove",
		        	handler:function(){
		        		var row = $('#menuTable').datagrid('getSelected');
		        		if(!row){
		        			$.messager.alert("系统提示", "请选择一条记录！");
		        			return;
		        		}
		        		$.messager.confirm('系统提示', '确定删除菜单?', function(r){
		        			if(r){
		        				var url = context + "/menu/deleteMenu.do";
		        				var data = {};
		        				data.menuId = row.id;
		        				ajaxCall("#menuTable", url, data, function(res){
				        			$("#menuTable").treegrid("reload");
				        			$("#menuParentTree").combotree('reload');
				        		});
		        			}
		        		});
		        	}
		        },"-",{
		        	text:"刷新列表",
		        	iconCls:"icon-reload",
		        	handler:function(){
		        		$("#menuTable").treegrid("reload");
		        		$("#menuParentTree").combotree('reload');
		        	}
		        }]
		    });
			
			//查询
			$("#searchButton").click(function(){
				var param = {};
				var menuName = $("#menuName").val();
				//第几页
				param.pageNumber=$('#menuTable').datagrid('options').pageNumber;
				//每页显示数量
				param.pageSize = $('#menuTable').datagrid('options').pageSize;
				param.menuName = menuName;
				$("#menuTable").treegrid("load",param);
			});
			//保存
			$("#menuSaveButton").click(function(){
				$.messager.progress();
				var menuName = $(":text[name='menuName']").val();
				var menuLevel = $("#menuLevelSelector").val();
				var isShow = $("#isShowSelector").val();
				var parentTree = $("#menuParentTree").combotree('tree');
				var node = parentTree.tree('getSelected');
				if(!node){
					$.messager.alert("系统提示", "请选择上级菜单！");
					$.messager.progress('close');
				}
				var parentId = node.id;
				$("#menuForm").form("submit",{
					url: context + "/menu/addMenu.do",
					onSubmit:function(){
						var isValid = $("#menuForm").form('validate');
						if (!isValid){
							$.messager.progress('close');
						}
						return isValid;
					},
					success: function(result){
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						if(data.success){
							$('#menuWindow').window('close');
							$("#menuTable").treegrid("reload");
							$("#menuParentTree").combotree('reload');
							$("#menuForm").form('clear');
						}
					}
				});
			});
			//取消
			$("#menuCancelButton").click(function(){
				$("#menuWindow").window("close");
			});
		});
	</script>
	<table title="菜单列表" id="menuTable"></table>
	
	<div id="menuWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:300px;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
			<div>
				<form id="menuForm" method="post">
            		<table width="100%">
            			<tr>
            				<td style="width: 75px">
            					菜单名称：
            				</td>
            				<td colspan="3">
            					<input class="easyui-validatebox textbox" type="text" name="menuName" style="width: 300px" data-options="required:true"/>
            					<input class="easyui-textbox" type="hidden" name="menuId"/>
            				</td>
            			</tr>
            			<tr>
            				<td>
            					菜单请求路径：
            				</td>
            				<td colspan="3">
            					<input class="easyui-validatebox textbox" type="text" name="menuUrl" style="width: 300px" data-options="required:true"/>
            				</td>
            			</tr>
            			<tr id="parentMenuTr">
            				<td>
            					上级菜单：
            				</td>
            				<td colspan="3">
            					<select id="menuParentTree" class="easyui-validatebox easyui-combotree" data-options="url:'<%=context %>/menu/menuList.do',required:true" name="parentId" style="width: 305px"></select>
            				</td>
            			</tr>
            			<tr>
            				<td>
            					菜单级别：
            				</td>
            				<td style="width: 115px" >
            					<select id="menuLevelSelector" class="easyui-combobox" style="width: 115px" data-options="required:true,editable:false" name="menuLevel">
            						<option value="0" selected="selected">菜单</option>
            						<option value="1">按钮</option>
            					</select>
            				</td>
            				<td style="width: 75px" >
            					是否显示：
            				</td>
            				<td style="width: 115px" >
            					<select id="isShowSelector" class="easyui-combobox" style="width: 115px;" data-options="required:true,editable:false" name="isShow">
            						<option value="0" selected="selected">否</option>
            						<option value="1">是</option>
            					</select>
            				</td>
            			</tr>
            			<tr>
            				<td>
            					备注：
            				</td>
            				<td colspan="3">
            					<textarea rows=5 cols="45" name="remark" class="textarea easyui-validatebox" ></textarea>
            				</td>
            			</tr>
            			<tr>
            				<td>
            					&nbsp;
            				</td>
            				<td colspan="3" style="padding:15px;text-align:right;">
            					<a id="menuSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
            					<a id="menuCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
            				</td>
            			</tr>
            		</table>
            	</form>
			</div>
		</div>
	</div>
	<!-- <div id="tt">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增菜单</a>
	</div> -->
	
	<!-- <div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增菜单</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改菜单</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除菜单</a>
		</div>
		
		<div>
			菜单名称: <input class="easyui-textbox" id="menuName" style="width:120px">
			<a id="searchButton" href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div> -->
</body>
</html>
