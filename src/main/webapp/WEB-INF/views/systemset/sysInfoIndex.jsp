<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>
<head>
    <style>
       td{
            width: 20%;
        }
    </style>
</head>
<body >
<script type="text/javascript">
	$(function(){
	$("#addEditSysInfoSaveButton").click(function() {
			$.messager.progress();
			$("#addEditSysInfoForm").form("submit", {
				url : context + "/systemset/saveOrUpdateSysInfo.do",
				dataType : "json",
				onSubmit : function() {
					var isValid = $("#addEditSysInfoForm").form('validate');
					if (!isValid) {
						$.messager.progress('close');
					}
					return isValid;
				},
				success : function(result) {
					var data = $.parseJSON(result);
					$.messager.alert("系统提示", data.msg);
					$.messager.progress('close');
				}
			});
		});
		var url = context + "/systemset/initSysInfo.do";
		var data = {};
		ajaxCallNoMsg("#SysInfoTable", url, data, function(res) {
			//alert("=="+res);
			var data = $.parseJSON(res);
 			$.each(data,function(i,val) { 
				//alert(val+"=="+i);
				$("input[name='"+val.paramType+"']").val(val.paramValue);
			}); 
		});
	});
</script>
	<table id="SysInfoTable"></table>
	
	<div id="addEditUserWindow" style="padding:10px;">
			<form id="addEditSysInfoForm" method="post">
                <div style="width: 36%;float: left;">
				<table>
                    <tr>
                        <td align="right">老人端注册送：</td>
                        <td >
                            <input class="easyui-numberbox textbox" type="text" name="a01" data-options="required:true,min:0,precision:2,max:9999999" style="width: 50px;"/> &nbsp;元
                        </td>
                        <td width="110px" align="right">老人端邀请送：</td>
                        <td >
                            <input class="easyui-numberbox textbox" type="text" name="a02"  data-options="required:true,min:0,precision:2,max:9999999" style="width: 50px"/> &nbsp;元
                        </td>
                    </tr>
                    <tr>
                        <td width="110px"align="right">亲属端注册送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a03"style="width: 50px"  data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                        <td width="110px"align="right">亲属端邀请送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a04" style="width: 50px" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                    <tr>
                        <td width="110px"align="right">急救车注册送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a05"style="width: 50px"  data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                        <td width="110px"align="right">急救车邀请送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a06"style="width: 50px"  data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                    <tr>
                        <td width="110px"align="right">120中心注册送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a07" style="width: 50px" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                        <td width="110px"align="right">120中心邀请送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a08"  style="width: 50px"data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                    <tr><td>&nbsp;</td> </tr>
                    <tr><td>&nbsp;</td> </tr>
                    <tr><td>&nbsp;</td> </tr>
                    <tr>
                        <td colspan="3"align="right">普通用户端被邀请人购买多少个产品给邀请人返现：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a09"style="width: 50px"  data-options="required:true,min:0,max:9999999"/> &nbsp;个
                        </td>
                    </tr>
                    <!-- <tr>
                        <td colspan="3"align="right">亲属端被邀请人购买多少个产品给邀请人返现：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a10" style="width: 50px" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;个
                        </td>
                    </tr> -->
                    <tr>
                        <td colspan="3"align="right">急救车被邀请人充值多少给邀请人返现：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a11" style="width: 50px" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3"align="right">120中心被邀请人充值多少给邀请人返现：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a12" style="width: 50px" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                </table>
                </div>
                <div style="width: 2%;float: left;">
                    <table style="height:220px;border-color:000000;border-left-style:solid;border-width:1px"><tr><td valign="top"></td></tr></table>
                </div>
                <div style="width: 45%;float: left;">
                <table>
                    <tr>
                        <td width="130px" align="right">急救车月使用费：</td>
                        <td width="80px">
                            <input class="easyui-numberbox textbox" type="text" name="a13" style="width: 50px;"  data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td width="100px">
                            <input class="easyui-numberbox textbox" type="text" name="a1301" style="width: 50px;"  data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1302" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr>
                        <td width="130px"align="right">120中心省级月使用费：</td>
                        <td >
                            <input class="easyui-numberbox textbox" type="text" name="a14" style="width: 50px;" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1401" style="width: 50px;"  data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1402" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr>
                        <td width="130px"align="right">120中心市级使用费：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a15" style="width: 50px;" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1501" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1502" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr>
                        <td width="130px"align="right">120中心县级使用费：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a16" style="width: 50px;" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1601"  style="width: 50px;"data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1602" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr>
                        <td width="130px"align="right">医院使用费：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a17" style="width: 50px;" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1701"  style="width: 50px;"data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a1702" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr>
                        <td width="130px"align="right">老人端月使用费：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a21" style="width: 50px;" data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元,
                        </td>
                        <td width="50px">充值：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a2101"  style="width: 50px;"data-options="required:true,min:0,max:9999999"/> &nbsp;月免
                        </td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a2102" style="width: 50px;" data-options="required:true,min:0,max:9999999"/> &nbsp;月
                        </td>
                    </tr>
                    <tr><td>&nbsp;</td> </tr>
                    <tr><td>&nbsp;</td> </tr>
                    <tr>
                        <td align="right">最低提现金额：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a18" style="width: 50px;"  data-options="required:true,min:0,precision:2,max:9999999"/> &nbsp;元
                        </td>
                    </tr>
                    <tr>
                        <td align="right">新用户注册送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a19"style="width: 50px;"  data-options="required:true,min:0,max:9999999"/> &nbsp;天
                        </td>
                    </tr>
                    <tr>
                        <td align="right">老人端注册送：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a22"style="width: 50px;"  data-options="required:true,min:0,max:9999999"/> &nbsp;天
                        </td>
                    </tr>
                    <tr>
                        <td align="right">费用到期预通知：</td>
                        <td>
                            <input class="easyui-numberbox textbox" type="text" name="a20"style="width: 50px;"  data-options="required:true,min:0,max:9999999"/> &nbsp;天
                        </td>
                    </tr>
                </table>
                </div>
                <div align="center" style="float: left;width: 80%;margin-top: 40px;">
                    <input type="button" value="确定修改" style="width: 80px;margin-right: 10%;" id="addEditSysInfoSaveButton"/>
                </div>
			</form>
		</div>
</body>
</html>
