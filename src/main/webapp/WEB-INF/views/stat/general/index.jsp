<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		var queryStatGeneralUrl = context + "/stat/general/queryGeneralStatList.do";
		var exportGeneralStatUrl = context + "/stat/general/exportGeneralStat.do";
		$(function(){
			//表格
			$('#gridTableGeneralStat').datagrid({ 
		        url: queryStatGeneralUrl,
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:false,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        fitColumns:true,
		        pagination:true,
		        pageSize:20,
		        columns:[[ 
	        		{title:'统计项',field:'generalStatItem', width:180},
	        		{title:'统计值',field:'generalStatValue',width:180}
		        ]],
		        toolbar:"#searchToolbarGeneralStat"
		    });
		});
		
		// 统计
		function searchGeneralStat(){
			var _radioCheckded = getGeneralStatRadioChecked();
			var _timeEnd = $('#generalStatTimeEnd').datetimebox('getValue');
			var _timeStart = $('#generalStatTimeStart').datetimebox('getValue');
			var data = {};
			data.statTimeEnd = _timeEnd;
			data.statTimeStart = _timeStart;
			data.statTimeOption = _radioCheckded;
			$('#gridTableGeneralStat').datagrid({url:queryStatGeneralUrl,queryParams:data});
		}
		
		
		// 导出
		function exportGeneralStat() {
			var _radioCheckded = getGeneralStatRadioChecked();
			var _timeEnd = $('#generalStatTimeEnd').datetimebox('getValue');
			var _timeStart = $('#generalStatTimeStart').datetimebox('getValue');
			$("#ExportGeneralStatTimeEnd").val(_timeEnd);
			$("#ExportGeneralStatTimeStart").val(_timeStart);
			$("#ExportGeneralStatTimeCustom").val(_radioCheckded);
			
			document.getElementById("ExportGeneralStatForm").action = exportGeneralStatUrl;
			document.getElementById("ExportGeneralStatForm").target = "_blank";
			document.getElementById("ExportGeneralStatForm").submit();			
		}		
		
		function getGeneralStatRadioChecked() {
			var selectedRadio = $("input[name='searchGeneralStatRadioTime']:checked").val(); 
			if (!selectedRadio) {
				alert("请选择统计条件！");
				return;
			}
			if (selectedRadio == '3') {
				var _timeEnd = $('#generalStatTimeEnd').datetimebox('getValue');
				var _timeStart = $('#generalStatTimeStart').datetimebox('getValue');
				if (!_timeEnd && !_timeStart) {
					alert("请输入输入自定义时间区间！");
					return;
				}
			}
			
			return selectedRadio;
		}
		
		function setGeneralStatTimeValue(value) {
			$('#generalStatTimeStart').datetimebox('setValue', null);
			$('#generalStatTimeEnd').datetimebox('setValue', null);
		}
	</script>
	
	<!-- 表格 -->
	<table title="综合统计" id="gridTableGeneralStat"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarGeneralStat" style="padding:5px;height:auto">
        <fieldset>
            <legend>统计条件</legend>
            <form id="searchGeneralStatForm" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                        	<td>
                            	<input type="radio" name="searchGeneralStatRadioTime" value="0" id="searchGeneralStatRadioTimeDay" onclick="setGeneralStatTimeValue(value);" checked="checked"/>当日
                           	</td>
                           	<td>
                            	<input type="radio" name="searchGeneralStatRadioTime" value="1" id="searchGeneralStatRadioTimeMonth" onclick="setGeneralStatTimeValue(value);"/>当月
                            </td>
                            <td>
                            	<input type="radio" name="searchGeneralStatRadioTime" value="2" id="searchGeneralStatRadioTimeYear" onclick="setGeneralStatTimeValue(value);"/>当年
                            </td>
                            <td>
                            	<input type="radio" name="searchGeneralStatRadioTime" value="3" id="searchGeneralStatRadioTimeOther"/>其他时间段
							</td>
							<td>
                            	&nbsp;<input class="easyui-datetimebox" type="text" id="generalStatTimeStart" name="statTimeStart" style="width:145px"/>&nbsp;至&nbsp;
                            	<input class="easyui-datetimebox" type="text" id="generalStatTimeEnd" name="statTimeEnd" style="width:145px"/>
							</td>
    						<td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-sum'" id="searchGeneralStatBtn" onclick="searchGeneralStat();">统计</a>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" id="exportGeneralStatBtn" onclick="exportGeneralStat();">导出EXCEL</a>
                            </td>
						</tr>
						<tr>
						</tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
	
	<!-- 导出EXCEL窗口 -->
    <div id="ExportGeneralStatWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:180px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="ExportGeneralStatForm" method="post">
        		<div>
					<input type="text" id="ExportGeneralStatTimeStart" name="statTimeStart"/>
					<input type="text" id="ExportGeneralStatTimeEnd" name="statTimeEnd"/>
					<input type="text" id="ExportGeneralStatTimeCustom" name="statTimeOption"/>
        		</div>
			</form>
        </div>
    </div>	
	
</body>
</html>
