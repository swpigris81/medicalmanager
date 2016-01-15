<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		var queryStatAreaAlarmUrl = context + "/stat/alarm/queryStatAreaAlarmList.do";
		var exportAreaAlarmStatUrl = context + "/stat/alarm/exportStatAreaAlarm.do";
// 		var _alarmCenter = {
// 				'00' : '120中心',
// 				'01' : '110中心'
// 			};
		var _diseaseType = {
				'00' : '急性',
				'01' : '慢性突发'
			};
		
		$(function(){
			
// 			setOption($("#searchAreaAlarmStatAlarmCenter"), _alarmCenter, "", "请选择")
			//表格
			$('#gridTableAreaAlarmStat').datagrid({ 
		        url: queryStatAreaAlarmUrl,
		        animate:"true",
		        rownumbers:"true",
		        singleSelect:false,
		        selectOnCheck: true,
		        checkOnSelect: true,
		        fit:true,
		        fitColumns:true,
		        pagination:true,
		        pageSize:10,
		        showFooter:true,
		        columns:[[ 
	        		{title:'报警中心',field:'alarmCenter', width:180,
// 	        			formatter: function(value,row,index){
// 	        				return getValueByKey(value, _alarmCenter);
// 	        			}
	        		},
	        		{title:'报警时间',field:'alarmTime',width:180},
	        		{title:'调度员',field:'scheduleUser', width:180},
	        		{title:'电话号码',field:'phone',width:180},
	        		{title:'通话时长',field:'callDuration', width:180},
	        		{title:'调度车辆',field:'scheduleVehicle',width:180},
	        		{title:'病情类型',field:'diseaseType', width:180,
	        			formatter: function(value,row,index){
	        				return getValueByKey(value, _diseaseType);
	        			}
	        		},
	        		{title:'收治医院',field:'hospital',width:180},
	        		{title:'跟踪情况',field:'trackInfo', width:180}
		        ]],
		        toolbar:"#searchToolbarAreaAlarmStat"
		    });
		});
		
		// 统计
		function searchAreaAlarmStat(){
			var __radioCheckded = getAreaAlarmStatRadioChecked();
			var __timeEnd = $('#generalStatTimeEnd').datetimebox('getValue');
			var __timeStart = $('#generalStatTimeStart').datetimebox('getValue');
			var __alarmCenter = $('#searchAreaAlarmStatAlarmCenter').val();
			var data = {};
			data.statTimeEnd = __timeEnd;
			data.statTimeStart = __timeStart;
			data.alarmCenter = __alarmCenter;
			data.statTimeOption = __radioCheckded;
			$('#gridTableAreaAlarmStat').datagrid({url:queryStatAreaAlarmUrl,queryParams:data});
		}
		
		
		// 导出
		function exportAreaAlarmStat() {
			var __radioCheckded = getAreaAlarmStatRadioChecked();
			var __timeEnd = $('#generalStatTimeEnd').datetimebox('getValue');
			var __timeStart = $('#generalStatTimeStart').datetimebox('getValue');
			var __alarmCenter = $('#searchAreaAlarmStatAlarmCenter').val();
			
			$("#ExportAreaAlarmStatTimeEnd").val(__timeEnd);
			$("#ExportAreaAlarmStatTimeStart").val(__timeStart);
			$("#ExportAreaAlarmStatTimeCustom").val(__radioCheckded);
			$("#searchAreaAlarmStatAlarmCenter").val(__alarmCenter);
			
			document.getElementById("ExportAreaAlarmStatForm").action = exportAreaAlarmStatUrl;
			document.getElementById("ExportAreaAlarmStatForm").target = "_blank";
			document.getElementById("ExportAreaAlarmStatForm").submit();			
		}		
		
		function getAreaAlarmStatRadioChecked() {
			var selectedRadio = $("input[name='searchAreaAlarmStatRadioTime']:checked").val(); 
			if (!selectedRadio) {
				alert("请选择统计条件！");
				return;
			}
			return selectedRadio;
		}
		
		function setAreaAlarmStatTimeValue(value) {
			if (value != '3') {
				$('#generalStatTimeStart').datetimebox('setValue', null);
				$('#generalStatTimeEnd').datetimebox('setValue', null);
			}
		}
	</script>
	
	<!-- 表格 -->
	<table title="辖区报警数据统计" id="gridTableAreaAlarmStat"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarAreaAlarmStat" style="padding:5px;height:auto">
        <fieldset>
            <legend>统计条件</legend>
            <form id="searchAreaAlarmStatForm" method="post">
                <div id="toolbar123">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
<!--                             <td style="width: 200px;"><label>报警中心：</label> -->
<!-- 					        	<select id="searchAreaAlarmStatAlarmCenter" name="alarmCenter" style="width: 120px"></select> -->
<!--                             </td> -->
                            <td style="width: 200px;"><label>报警中心：</label>
           						<select id="searchAreaAlarmStatAlarmCenter" class="easyui-combobox" name="alarmCenter" style="width: 120px">
									<option value="">请选择</option>
									<c:if test="${alarmCenterList != null && fn:length(alarmCenterList) > 0}">
										<c:forEach items="${alarmCenterList}" var="alarmCenter" varStatus="status">
											<option value="${alarmCenter.id}">${alarmCenter.unitName}</option>
										</c:forEach>
									</c:if>
								</select>
                            </td>                            
                        	<td>
                            	<input type="radio" name="searchAreaAlarmStatRadioTime" value="0" id="searchAreaAlarmStatRadioTimeDay" onclick="setAreaAlarmStatTimeValue(value);" checked="checked"/>当日
                           	</td>
                           	<td>
                            	<input type="radio" name="searchAreaAlarmStatRadioTime" value="1" id="searchAreaAlarmStatRadioTimeMonth" onclick="setAreaAlarmStatTimeValue(value);"/>当月
                            </td>
                            <td>
                            	<input type="radio" name="searchAreaAlarmStatRadioTime" value="2" id="searchAreaAlarmStatRadioTimeYear" onclick="setAreaAlarmStatTimeValue(value);"/>当年
                            </td>
                            <td>
                            	<input type="radio" name="searchAreaAlarmStatRadioTime" value="3" id="searchAreaAlarmStatRadioTimeOther" onclick="setAreaAlarmStatTimeValue(value);"/>其他时间段
							</td>
							<td>
                            	&nbsp;<input class="easyui-datetimebox" type="text" id="generalStatTimeStart" name="statTimeStart" style="width:145px"/>&nbsp;至&nbsp;
                            	<input class="easyui-datetimebox" type="text" id="generalStatTimeEnd" name="statTimeEnd" style="width:145px"/>
							</td>
    						<td>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-sum'" id="searchAreaAlarmStatBtn" onclick="searchAreaAlarmStat();">统计</a>
                                &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'" id="exportAreaAlarmStatBtn" onclick="exportAreaAlarmStat();">导出EXCEL</a>
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
    <div id="ExportAreaAlarmStatWindow" class="easyui-window" data-options="modal:true,closed:true" style="width:500px;height:180px;padding:10px;">
        <div class="easyui-layout" data-options="fit:true">
        	<form id="ExportAreaAlarmStatForm" method="post">
        		<div>
					<input type="text" id="ExportAreaAlarmStatTimeStart" name="statTimeStart"/>
					<input type="text" id="ExportAreaAlarmStatTimeEnd" name="statTimeEnd"/>
					<input type="text" id="ExportAreaAlarmStatTimeCustom" name="statTimeOption"/>
        		</div>
			</form>
        </div>
    </div>	
	
</body>
</html>
