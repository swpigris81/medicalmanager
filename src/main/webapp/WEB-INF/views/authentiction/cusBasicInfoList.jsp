<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	var checkStatus={"00":"待审核","01":"审核通过","02":"审核拒绝"};
		$(function(){
			//表格
			$('#cusBasicTable').datagrid({ 
		        url: context + "/authentiction/queryCusBasicExpandList.do",
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
		        	{title:'手机号码',field:'phoneNumber', width:180, formatter:function(value,rowData,rowIndex){
	                	return rowData['cusBasicInfo']['phone'];
	        		}},
		        	{title:'真实姓名',field:'realName',width:180, formatter:function(value,rowData,rowIndex){
	                	return rowData['cusBasicInfo']['realName'];
	        		}},
		        	{title:'单位名称',field:'unitName',width:180}, 
	        		{title:'注册时间',field:'registerTime', width:180, formatter:function(value,rowData,rowIndex){
	                	return rowData['cusBasicInfo']['registerTime'];
	        		}},
	        		{title:'基本信息',field:'basicInfo',width:180, formatter:function(value,rowData,rowIndex){
	                	return "基本信息完整";
	        		}},
			        {title:'审核人',field:'audit',width:160, formatter:function(value,rowData,rowIndex){
			        	if(value==null || value==""){
			        		return "未分配";
			        	}
	                	return value;
	        		}},
	        		{title:'单位地址',field:'unitAddress',width:180,hidden:true}, 
	        		{title:'固定电话',field:'fixTelephone',width:180,hidden:true}, 
	        		{title:'绑定电话',field:'bindingPhone',width:180,hidden:true}, 
	        		{title:'技术负责人',field:'technicalPerson',width:180,hidden:true}, 
	        		{title:'属于收费级别',field:'tollLevel',width:180,hidden:true}, 
	        		{title:'使用省份',field:'useProvince',width:180,hidden:true}, 
	        		{title:'使用市级',field:'useCity',width:180,hidden:true}, 
	        		{title:'使用区县',field:'useCounty',width:180,hidden:true}, 
	        		{title:'cusId',field:'cusId',width:180,hidden:true},
			         {title:'状态',field:'checkStatus',width:140, formatter:function(value,rowData,rowIndex){
	                	return checkStatus[value];
	        		}}
		        ]],
		        
		    });
			//保存品牌
			$("#addEditCusbasicSaveButton").click(function() {
				$.messager.progress();
				$("#addEditCusbasicForm").form("submit", {
					url : context + "/authentiction/saveOrUpdateCusBasicExpand.do",
					dataType : "json",
					onSubmit : function() {
						var isValid = $("#addEditCusbasicForm").form('validate');
						if (!isValid) {
							$.messager.progress('close');
						}
						return isValid;
					},
					success : function(result) {
						var data = $.parseJSON(result);
						$.messager.alert("系统提示", data.msg);
						$.messager.progress('close');
						$("#addEditCusbasicForm").form('clear');
						if (data.success) {
							$('#addEditCusbasicWindow').window('close');
							$("#cusBasicTable").datagrid("reload");
						}
					}
				});
			});
			//取消
			$("#addEditBrandCancelButton").click(function() {
				$('#addEditCusbasicWindow').window('close');
			});
			//省市区级联查询
			$("#useProvince").combobox({
				onSelect: function (record) {
					$("#useCity").combobox({
						url: context + '/administrative/searchCityByProvice.do?provinceid=' + record.value
					}).combobox('clear');
				}
			});
			//var _useCityCombo = $("#useCity").combobox();
			$("#useCity").combobox({
				onSelect: function (record) {
					$("#useCounty").combobox({
						url: context + '/administrative/searchCountyByCity.do?cityid=' + record.cityid
					}).combobox('clear');
				}
			});
			//var _useCountyCombo = $("#useCounty").combobox();
		});
		function addBrandBasic(index) {
			var row = $('#cusBasicTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#addEditCusbasicForm").form('clear');
			$('#addEditCusbasicWindow').window({
				title : "查看详细"
			});
			$('#addEditCusbasicWindow').window('open');
			var bindingPhone=row.phoneNumber;
			if(row.bindingPhone!=null && row.bindingPhone!=''){
				bindingPhone=row.bindingPhone;
			}
			var useCity = $("#useCity").combobox();			
			useCity.combobox({
				url: context + '/administrative/searchCityByProvice.do?provinceid=' + row.useProvince
			}).combobox('clear');
			var useCounty = $("#useCounty").combobox();			
			useCounty.combobox({
				url: context + '/administrative/searchCountyByCity.do?cityid=' + row.useCity
			}).combobox('clear');
			$('#addEditCusbasicForm').form("load", {
				bindingPhone : bindingPhone,
				useProvince:row.useProvince,
				useCity:row.useCity,
				useCounty:row.useCounty,
				tollLevel:row.tollLevel,
				unitName:row.unitName,
				unitAddress:row.unitAddress,
				fixTelephone:row.fixTelephone,
				technicalPerson:row.technicalPerson,
				cusId:row.cusId
			});
		}
		
		function searchBrandBasic(){
			var url = context + "/authentiction/queryCusBasicExpandList.do";
			var data = {};
			data.beginDate = $("#beginDateBasic").datebox('getValue');
			data.endDate=$("#endDateBasic").datebox('getValue');
			data.phoneNumber=$("#phoneNumberBasic").val();
			data.checkStatus=$("#checkStatusBasic").combo("getValue");
			data.realName=$("#realNameBasic").val();
			$('#cusBasicTable').datagrid({url:url,queryParams:data});
		}
		
		function checkCus(status){
			var isValid = $("#addEditCusbasicForm").form('validate');
			if(!isValid){
				return;
			}
			if($("#cusId").val()==null || $("#cusId").val()==''){
				$.messager.alert("系统提示","请先保存再修改");
			}
			var url = context + "/authentiction/checkCusBasicExpand.do";
			var data = {};
			data.cusId= $("#cusId").val();
			data.checkStatus=status;
			ajaxCall("#cusBasicTable", url, data, function(res) {
				$('#addEditCusbasicWindow').window('close');
				$("#cusBasicTable").datagrid("reload");
			});
		}
	</script>
	<div id="tbCusBasic" style="padding:5px;height:auto">
	<!-------------------------------搜索框----------------------------------->
        <fieldset>
            <legend>信息查询</legend>
            <form id="ffSearch" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td align="right">
                                <label >手机号：</label>
                                <input class="easyui-textbox" name="phoneNumberBasic" id="phoneNumberBasic"/>
                            </td>
                            <td>
                                <label >真实姓名：</label>
                                <input class="easyui-textbox" name="realNameBasic" id="realNameBasic"/>
                            </td>
                            <td>
                                时间段：<input class="easyui-datebox" name="beginDateBasic" id="beginDateBasic"/>&nbsp;至&nbsp;
                                <input class="easyui-datebox" name="endDateBasic" id="endDateBasic"/>
                            </td>
                         </tr>
                         <tr>
                             <td align="left">
                                <label >&nbsp;&nbsp;&nbsp;状态：</label>
                                 <select name="checkStatusBasic" id="checkStatusBasic" style="width: 100%;"class="easyui-combobox">
                                    <option value="">全部</option>
                                    <option value="00">待审核</option>
                                    <option value="01">审核通过</option>
                                    <option value="02">审核拒绝</option>
                                </select>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnSearchBasic" onclick="searchBrandBasic();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td>
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddBasic" iconcls="icon-tip" onclick="addBrandBasic()">查看明细</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
        </div>
	<table title="订单列表" id="cusBasicTable" toolbar="#tbCusBasic"></table>
	
    <div id="addEditCusbasicWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:560px;height:320px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
            <form id="addEditCusbasicForm" method="post">
            <input class="easyui-textbox" type="hidden" name="cusId" id="cusId"/>
                <table width="100%">
                    <tr>
                        <td align="right" width="20%">
                            使用单位：
                        </td>
                        <td>
           					<select id="useProvince" class="easyui-combobox" name="useProvince" data-options="required:true,editable:false" style="width:120px">
								<option value="">请选择</option>
								<c:if test="${provinceList != null && fn:length(provinceList) > 0}">
									<c:forEach items="${provinceList }" var="province" varStatus="status">
										<option value="${province.provinceid }">${province.province }</option>
									</c:forEach>
								</c:if>
							</select>
           				</td>
           				<td>
           					<select class="easyui-combobox" id="useCity" style="width:120px"  name="useCity"
                                 data-options="required:true, valueField:'cityid', textField:'city', panelHeight:'auto'" >
                                 </select>
                            &nbsp;<input class="easyui-combobox" id="useCounty" style="width:120px"  name="useCounty"
                                 data-options="required:true, valueField:'areaid', textField:'area', panelHeight:'auto'" >
           				</td>
                    </tr>
                     <tr>
                        <td align="right" width="20%">
                            属于收费级别：
                        </td>
                        <td>
                            <select name="tollLevel" class="easyui-combobox" style="width: 120px;" data-options="required:true">
                                <option value="00">省级</option>
                                <option value="01">市级</option>
                                <option value="02">县级</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="20%">
                            单位名称：
                        </td>
                        <td>
                            <input name="unitName" class="easyui-textbox" />
                        </td>
                    </tr>
                     <tr>
                        <td align="right" width="20%">
                            固定电话：
                        </td>
                        <td width="30%">
                            <input name="fixTelephone" class="easyui-textbox"/>
                        </td>
                    </tr>
                     <tr>
                        <td align="right" width="20%">
                            绑定手机号码：
                        </td>
                        <td width="30%">
                            <input name="bindingPhone" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="15%">
                            技术负责人：
                        </td>
                        <td>
                            <input name="technicalPerson" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="20%">
                            单位地址：
                        </td>
                        <td width="30%">
                            <input name="unitAddress" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <a id="addEditCusbasicSaveButton" href="#" class="easyui-linkbutton" icon="icon-ok">保存修改</a>&nbsp;
                            <a id="" href="#" class="easyui-linkbutton" icon="icon-ok" onclick="checkCus('01')">审核通过</a>&nbsp;
                            <a id="" href="#" class="easyui-linkbutton" icon="icon-ok" onclick="checkCus('02')">审核不通过</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
