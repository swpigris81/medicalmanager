<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>

<body class="easyui-layout">
	<script type="text/javascript">
	
		var queryStatLoginUrl = context + "/stat/login/queryStatLoginList.do";
		$(function(){
			//表格
			$('#gridTableLoginStat').datagrid({ 
		        url: queryStatLoginUrl,
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
	        		{title:'最后登录IP',field:'lastLoginIp',width:240},
			        {title:'最后登录时间',field:'lastLoginTime',width:240},
	        		{title:'登录次数',field:'loginCount', width:140}
		        ]],
		        toolbar:"#searchToolbarLoginStat"
		    });
		});
		
		// 查询
		function searchLoginStat(){
			var lastLoginTimeStart = $('#lastLoginTimeStartLoginStat').datetimebox('getValue');
			var lastLoginTimeEnd = $('#lastLoginTimeEndLoginStat').datetimebox('getValue');
			if (lastLoginTimeStart && lastLoginTimeEnd && (lastLoginTimeStart > lastLoginTimeEnd)) {
				alert("开始时间不能大于结束时间，请重输入！");
				return;
			}
			
			var data = {};
			var nickname = $("#nicknameLoginStat").val();
			var realName = $("#realNameLoginStat").val();
			data.nickname = nickname;
			data.realName = realName;
			data.lastLoginTimeStart = lastLoginTimeStart;
			data.lastLoginTimeEnd = lastLoginTimeEnd;
			$('#gridTableLoginStat').datagrid({url:queryStatLoginUrl,queryParams:data});
		}
	</script>
	
	<!-- 表格 -->
	<table title="登录统计" id="gridTableLoginStat"></table>
		
	<!-------------------------------搜索框----------------------------------->
	<div id="searchToolbarLoginStat" style="padding:5px;height:auto">
        <fieldset>
            <legend>统计条件</legend>
            <form id="searchLoginStat" method="post">
                <div id="toolbar">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td style="width: 170px;"><label>用户名：</label>
                            	<input type="text" id="nicknameLoginStat" name="nickname" style="width:100px"  />
                            </td>
                            <td style="width: 180px;"><label>真实姓名：</label>
                            	<input type="text" id="realNameLoginStat" name="realName" style="width:100px"  />
                            </td>
                            <td style="width: 400px;"><label>最后登录时间：</label>
                            	<input class="easyui-datetimebox" type="text" id="lastLoginTimeStartLoginStat" name="lastLoginTimeStart" style="width:140px"/>&nbsp;至&nbsp;
                            	<input class="easyui-datetimebox" type="text" id="lastLoginTimeEndLoginStat" name="lastLoginTimeEnd" style="width:140px"/>
                            </td>
                            <td>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-sum'" id="btnSearch" onclick="searchLoginStat();">统计</a>
                            </td>
                          </tr>
                    </table>
                </div>
            </form>
        </fieldset>
	</div>
</body>
</html>
