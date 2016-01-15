<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
		$(function(){
			setOption($('#cusType'), customType, "", "请选择");
			//表格
			$('#gridBasicInfoTable').datagrid({ 
		        url: context + "/customer/basic/queryBasicInfoList.do",
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
			        {title:'服务到期时间',field:'serviceExpireTime',width:240},
	        		{title:'信用积分',field:'creditScore', width:140},
	        		{title:'最后登录IP',field:'lastLoginIp',width:180},
			        {title:'最后登录时间',field:'lastLoginTime',width:240},
		        ]],
		        toolbar:"#searchToolbarBasicInfo",
		        onDblClickRow: function (rowIndex, rowData) {
		        	view();
		        }
		    });
			
			// 保存客户信息
			$("#updateWindowSaveButton").click(function() {
				$.messager.progress();
				$("#updateWindowForm").form("submit", {
					url : context + "/customer/basic/saveOrUpdate.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#updateWindowForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#updateWindowForm").form('clear');
						if (data.success) {
							$('#updateWindow').window('close');
							$("#gridBasicInfoTable").datagrid("reload");
						}
					}
				});
			});
			// 查看详情取消
			$("#viewWindowCancelButton").click(function() {
				$('#viewWindow').window('close');
			});
			// 修改信息取消
			$("#updateWindowCancelButton").click(function() {
				$('#updateWindow').window('close');
			});
			//省市级联查询-查询
			var _userCityCombo = $("#city").combobox();
			$("#province").combobox({
				onSelect: function (record) {
					$("#city").combobox({
						url: context + '/administrative/searchCityByProvice.do?provinceid=' + record.value
					}).combobox('clear');
				}
			});
			//省市级联查询-修改  
			$("#update_province").combobox({
				onSelect: function (record) {
					$("#update_city").combobox({
						url: context + '/administrative/searchCityByProvice.do?provinceid=' + record.value
					}).combobox('clear');
				}
			});
		});
		
		// 查看详情
		function view() {
			var row = $('#gridBasicInfoTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#viewWindowForm").form('clear');
			$('#viewWindow').window({
				title : "查看详情"
			});
			
			$("#view_province").append("<option value='" + row.province + "'>" + row.provinceName + "</option>");
			$("#view_city").append("<option value='" + row.city + "'>" + row.cityName + "</option>");
			setOption($('#view_cusType'), customType, "", "请选择");
			
			$('#viewWindow').window('open');
			$('#viewWindowForm').form("load", {
				phone : row.phone,
				nickname : row.nickname,
// 				idCardNo : row.idCardNo,
				realName : row.realName,
				province : row.province,
				city : row.city,
				cusType : row.cusType,
				creditScore : row.creditScore,
				registerTime : row.registerTime,
				serviceExpireTime : row.serviceExpireTime,
				lastLoginIp : row.lastLoginIp,
				lastLoginTime : row.lastLoginTime
			});
		}
		
		// 修改信息
		function update() {
			var row = $('#gridBasicInfoTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#updateWindowForm").form('clear');
			$('#updateWindow').window({
				title : "修改信息"
			});
			
			var _updateCity = $("#update_city").combobox();			
			_updateCity.combobox({
				url: context + '/administrative/searchCityByProvice.do?provinceid=' + row.province
			}).combobox('clear');
			
			setOption($('#update_cusType'), customType);
// 			setOption($('#update_cusType'), customType, "", "请选择");
			
			$('#updateWindow').window('open');
			$('#updateWindowForm').form("load", {
				nickname : row.nickname,
				realName : row.realName,
				phone : row.phone,
// 				idCardNo : row.idCardNo,				
				province : row.province,
				city : row.city,
				cusType : row.cusType,
				creditScore : row.creditScore,
				registerTime : row.registerTime,
				serviceExpireTime : row.serviceExpireTime,
				lastLoginIp : row.lastLoginIp,
				lastLoginTime : row.lastLoginTime,
				id : row.id
			});
		}
		
		function searchInfo(){
			var url = context + "/customer/basic/queryBasicInfoList.do";
			var data = {};
			var frm = $("#conditionSearch");
			var params = frm.serialize();
			
			if (params) {
				var pArr= new Array();
				pArr = params.split("&");
				if (pArr) {
					for (i = 0; i < pArr.length; i++) {
						name = pArr[i].split("=")[0];
						value = pArr[i].split("=")[1];
						var temp = name + ":'" + value + "'";
						if (i < pArr.length - 1) {
							temp += ",";
						}
						data[name] = value;
					}
				}
			}
			$('#gridBasicInfoTable').datagrid({url:url,queryParams:data});
		}
	</script>
	
	<!-- 表格 -->
	<table title="用户基本信息管理" id="gridBasicInfoTable"></table>
	
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarBasicInfo" style="padding:5px;height:auto">
        <fieldset>
            <legend>信息查询</legend>
            <form id="conditionSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>手机号：</label>
                            	<input type="text" id="phone" name="phone" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>真实姓名：</label>
                            	<input type="text" id="realName" name="realName" style="width:100px"  />
                            </td>
                            <td style="width: 160px;"><label>省份：</label>
           						<select id="province" class="easyui-combobox" name="province" data-options="required:true,editable:false" style="width:100px">
									<option value="">请选择</option>
									<c:if test="${provinceList != null && fn:length(provinceList) > 0}">
										<c:forEach items="${provinceList }" var="province" varStatus="status">
											<option value="${province.provinceid }">${province.province }</option>
										</c:forEach>
									</c:if>
								</select>
                            </td>
                            <td style="width: 150px;"><label>市：</label>
	           					<input class="easyui-combobox" id="city" style="width:100px"  name="city"
	                                 data-options="required:true, valueField:'cityid', textField:'city', panelHeight:'auto'" >
                            </td>
                            <td style="width: 160px;"><label>类型：</label>
					        	<select id="cusType" name="cusType" style="width: 100px"></select>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearch" onclick="searchInfo();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td colspan="6">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnEdit" iconcls="icon-edit" onclick="update()">修改</a>&nbsp;&nbsp;
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAdd" iconcls="icon-tip" onclick="view()">查看</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
	<!-- 查看详情窗口 -->
    <div id="viewWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="viewWindowForm" method="post">
				<table width="100%">
				    <tr>
				        <td>用户名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="nickname" id="view_nickname" disabled="disabled"/>
				        </td>
				        <td>真实姓名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="realName" id="view_realName" disabled="disabled"/>
				        </td>				        
				    </tr>
				    <tr>
				        <td>手机号：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="phone" id="view_phone" disabled="disabled"/>
				        </td>
<!-- 				        <td>身份证：</td> -->
<!-- 				        <td> -->
<!-- 				            <input class="easyui-validatebox textbox" type="text" name="idCardNo" id="view_idCardNo" disabled="disabled"/> -->
<!-- 				        </td> -->
				    </tr>
				    <tr>
				        <td>省份：</td>
				        <td>
       						<select id="view_province" name="province" disabled="disabled" style="width: 137px">
								<option value="">请选择</option>
							</select> 				        
				        </td>
				        <td>市：</td>
				        <td>
       						<select id="view_city" name="city" disabled="disabled" style="width: 137px">
       							<option value="">请选择</option>
                            </select>        
				        </td>
				    </tr>
				    <tr>
				        <td>类型：</td>
				        <td>
				        	<select id="view_cusType" name="cusType" disabled="disabled" style="width: 137px"></select>
				        </td>
				        <td>信用积分：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="creditScore" id="view_creditScore" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>注册时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="registerTime" id="view_registerTime" disabled="disabled"/>
				        </td>
				        <td>服务到期时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="serviceExpireTime" id="view_serviceExpireTime" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>最后登录ip：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="lastLoginIp" id="view_lastLoginIp" disabled="disabled"/>
				        </td>
				        <td>最后登录时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="lastLoginTime" id="view_lastLoginTime" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>
				            &nbsp;
				        </td>
				        <td colspan="3" style="padding:15px;text-align:right;">
				            <a id="viewWindowCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>
    </div>
    
	<!-- 修改窗口 -->
    <div id="updateWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:250px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="updateWindowForm" method="post">
				<input class="easyui-textbox" type="hidden" name="id" id="update_id"/>
				<table width="100%">
				    <tr>
				        <td>用户名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" maxlength="20" type="text" name="nickname" id="update_nickname" data-options="required:true"/>
				        </td>
				        <td>真实姓名：</td>
				        <td>
				            <input class="easyui-validatebox textbox" maxlength="20" type="text" name="realName" id="update_realName" disabled="disabled"/>
<!-- 				            <input class="easyui-validatebox textbox" maxlength="20" type="text" name="realName" id="update_realName" data-options="required:true"/> -->
				        </td>
				    </tr>
				    <tr>
				        <td>手机号：</td>
				        <td>
				            <input class="easyui-numberbox textbox" maxlength="11" type="text" name="phone" id="update_phone" disabled="disabled"/>
<!-- 				            <input class="easyui-numberbox textbox" maxlength="11" type="text" name="phone" id="update_phone" data-options="required:true,validType:['length[11,11]']"/> -->
				        </td>
<!-- 				        <td>身份证：</td> -->
<!-- 				        <td> -->
<!-- 				            <input class="easyui-numberbox textbox" maxlength="18" type="text" name="idCardNo" id="update_idCardNo" data-options="required:true,validType:['length[18,18]']"/> -->
<!-- 				        </td> -->
				    </tr>
				    <tr>
				        <td>省份：</td>
				        <td>
							<select class="easyui-combobox" id="update_province" name="province" style="width:137px" data-options="required:true,editable:false">
								<option value="">请选择</option>
								<c:if test="${provinceList != null && fn:length(provinceList) > 0}">
									<c:forEach items="${provinceList }" var="province" varStatus="status">
										<option value="${province.provinceid }">${province.province }</option>
									</c:forEach>
								</c:if>
							</select>				        
				        </td>
				        <td>市：</td>
				        <td>
							<input class="easyui-combobox" id="update_city" name="city" style="width:137px" data-options="required:true, valueField:'cityid', textField:'city', panelHeight:'auto'" >				        
				        </td>
				    </tr>
				    <tr>
				        <td>类型：</td>
				        <td>
				        	<select id="update_cusType" name="cusType" style="width: 137px"></select>
<!-- 				        	<select id="update_cusType" name="cusType" disabled="disabled" style="width: 137px"></select> -->
				        </td>
				        <td>信用积分：</td>
				        <td>
				            <input class="easyui-numberbox textbox" maxlength="10" type="text" name="creditScore" id="update_creditScore" data-options="required:true"/>
				        </td>
				    </tr>
				    <tr>
				        <td>注册时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="registerTime" id="update_registerTime" disabled="disabled"/>
				        </td>
				        <td>服务到期时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="serviceExpireTime" id="update_serviceExpireTime" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>最后登录ip：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="lastLoginIp" id="update_lastLoginIp" disabled="disabled"/>
				        </td>
				        <td>最后登录时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="lastLoginTime" id="update_lastLoginTime" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>
				            &nbsp;
				        </td>
				        <td colspan="3" style="padding:15px;text-align:right;">
                            <a id="updateWindowSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存</a>
				            <a id="updateWindowCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>
    </div>
</body>
</html>
