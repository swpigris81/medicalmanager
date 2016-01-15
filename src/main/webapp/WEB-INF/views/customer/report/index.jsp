<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
		var reportQueryListUrl = context + "/customer/report/queryReportList.do";
// 		急救车00、120省级01、120市级02、120县级03、医院04、其他机构05、普通用户06
		var _reportType = {
				'00' : '急救车',
				'01' : '120省级',
				'02' : '120市级',
				'03' : '120县级',
				'04' : '医院',
				'05' : '其他机构',
				'06' : '普通用户'
			};
		var _dealStatus = {
				'Y' : '已处理',
				'N' : '未处理'
			};
		
		$(function(){
			setOption($("#dealStatusSearch"), _dealStatus, "", "请选择");
			//表格
			$('#gridTableReport').datagrid({ 
		        url: reportQueryListUrl,
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
	        		{title:'举报人',field:'informants', width:140},
	        		{title:'被举报人',field:'reported',width:140},
			        {title:'举报类型',field:'reportType',width:100,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, _reportType);
	        			}
			        },
	        		{title:'举报内容',field:'reportInfo', width:260},
	        		{title:'举报时间',field:'reportTime',width:140},
			        {title:'状态',field:'dealStatus',width:100,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, _dealStatus);
	        			}	
			        }
		        ]],
		        onDblClickRow: function (rowIndex, rowData) {
		        	viewReport();
		        },
		        toolbar:"#searchToolbarReport"
		    });
			
			// 查看详情取消
			$("#viewWindowCancelButton").click(function() {
				$('#viewWindowReport').window('close');
			});
		});
		
		// 查看详情
		function viewReport() {
			
			setOption($("#view_reportType"), _reportType, "", "请选择");
			setOption($("#view_dealStatus"), _dealStatus, "", "请选择");
			
			var row = $('#gridTableReport').datagrid('getSelected');
			if (!row) {
				$.messager.alert("系统提示", "请选择一条记录！");
				return;
			}
			$("#viewWindowReportForm").form('clear');
			$('#viewWindowReport').window({
				title : "查看详情"
			});
			$('#viewWindowReport').window('open');
			$('#viewWindowReportForm').form("load", {
				id : row.id,
				informants : row.informants,
				reported : row.reported,
				reportType : row.reportType,
				reportInfo : row.reportInfo,
				reportTime : row.reportTime,
				dealStatus : row.dealStatus,
				dealUser : row.dealUser,
				dealTime : row.dealTime,
				dealInfo : row.dealInfo,
				remark : row.remark
			});
		}
		
		// 搜索
		function searchReport(){
			var data = {};
			data.informants = $("#informantsSearch").val();
			data.reported = $("#reportedSearch").val();
			data.dealStatus = $("#dealStatusSearch").val();
			
			$('#gridTableReport').datagrid({url:reportQueryListUrl,queryParams:data});
		}
	</script>
	
	<!-- 表格 -->
	<table title="用户举报管理" id="gridTableReport"></table>	
	
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarReport" style="padding:5px;height:auto">
        <fieldset>
            <legend>信息查询</legend>
            <form id="conditionSearchReport" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>举报人：</label>
                            	<input type="text" name="informants" id="informantsSearch" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>被举报人：</label>
                            	<input type="text" name="reported" id="reportedSearch" style="width:100px"  />
                            </td>
                            <td style="width: 160px;"><label>状态：</label>
					        	<select name="dealStatus" id="dealStatusSearch" style="width: 100px"></select>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" id="btnReportSearch" onclick="searchReport();">查询</a>
                            </td>
                          </tr>
                          <tr><td>&nbsp;</td></tr>
                          <tr>
                            <td colspan="6">
                                <a href="javascript:void(0)" class="easyui-linkbutton" id="btnReportView" iconcls="icon-tip" onclick="viewReport()">查看</a>&nbsp;&nbsp;
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
	<!-- 查看详情窗口 -->
    <div id="viewWindowReport" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:300px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="viewWindowReportForm" method="post">
				<table width="100%">
				    <tr>
				        <td>举报人：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="informants" id="view_informants" disabled="disabled"/>
				        </td>
				        <td>被举报人：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="reported" id="view_reported" disabled="disabled"/>
				        </td>				        
				    </tr>
				    <tr>
				        <td>举报类型：</td>
				        <td>
				        	<select id="view_reportType" name="reportType" disabled="disabled" style="width: 137px">
				        	</select>
				        </td>
				        <td>举报时间：</td>
				        <td>
				            <input class="easyui-validatebox textbox" type="text" name="reportTime" id="view_reportTime" disabled="disabled"/>
				        </td>
				    </tr>
				    <tr>
				        <td>状态：</td>
				        <td>
				        	<select id="view_dealStatus" name="dealStatus" disabled="disabled" style="width: 137px">
				        	</select>
				        </td>
<!-- 				        <td>处理人：</td> -->
<!-- 				        <td> -->
<!-- 				            <input class="easyui-validatebox textbox" type="text" name="dealUser" id="view_dealUser" disabled="disabled"/> -->
<!-- 				        </td> -->
				    </tr>
				    <tr>
				        <td>举报内容：</td>
				        <td colspan="3">
				            <input class="easyui-validatebox textbox" type="text" name="reportInfo" id="view_reportInfo" disabled="disabled" style="width: 370px;height: 50px;"/>
				        </td>
				    </tr>
<!-- 				    <tr> -->
<!-- 				        <td>处理信息：</td> -->
<!-- 				        <td colspan="3"> -->
<!-- 				            <input class="easyui-validatebox textbox" type="text" name="dealInfo" id="view_dealInfo" disabled="disabled" style="width: 370px;height: 50px;"/> -->
<!-- 				        </td> -->
<!-- 				    </tr> -->
				    <tr>
				        <td colspan="2">
				        </td>
				        <td colspan="2" style="padding:15px;text-align:right;" align="center">
				            <a id="viewWindowCancelButton" href="#" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				        </td>
				    </tr>
				</table>
			</form>
        </div>
    </div>
</body>
</html>
