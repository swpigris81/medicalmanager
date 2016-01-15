<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/global.jsp" %>
<html>
<jsp:include page="/common/include.jsp" flush="true"/>
<head>
	<title>后台管理系统</title>
	<script type="text/javascript" src="<%= context %>/js/system/menu/mainMenu.js"></script>
	<script type="text/javascript">
	var pc;
	$.parser.onComplete = function(){
		if(pc) clearTimeout(pc);
		pc = setTimeout(changeToClose, 1000);
	};
	function openEditPassWin(){
		$('#w').window({
            title: '修改密码',
            width: 300,
            modal: true,
            shadow: true,
            closed: true,
            height: 200,
            resizable:false
        });
	}
	function closePwd() {
        $('#w').window('close');
    }
	
	//修改密码
	function doEditPassword(){
		var newpass = $("#txtNewPass").val();
		var newRepass = $("#txtRePass").val();
		var oldPass = $("#txtOldPass").val();
		
		if(!$("#editPasswordForm").form("validate")){
			$.messager.alert("修改密码", "输入有误，请重新输入！");
			return;
		}
		if(!$.trim(oldPass)){
			$.messager.alert("修改密码", "原密码不能为空！");
			return;
		}
		if(!($.trim(newpass))){
			$.messager.alert("修改密码", "新密码不能为空！");
			return;
		}
		if(!($.trim(newRepass))){
			$.messager.alert("修改密码", "确认密码不能为空！");
			return;
		}
		if($.trim(newpass) != $.trim(newRepass)){
			$.messager.alert("修改密码", "两次输入的密码不一致！");
			return;
		}
		var url = context + "/user/modifyPassword.do";
		var data = {};
		data.oldPass = $.trim(oldPass);
		data.newPass = $.trim(newpass);
		ajaxCall(null, url, data, function(){
			closePwd();
			$("#txtNewPass").val("");
			$("#txtRePass").val("");
			$("#txtOldPass").val("");
		});
	}
	//打开菜单
	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="' + context + "" + url + '" style="width:100%;height:100%;"></iframe>';
		return s;
	}
	
	//在右边center区域打开菜单，新增tab
    function openUrl(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
        	changeToBusy2("#tabs");
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
                //content结合iframe可以跨域访问
                //content : createFrame(url)
                //href不能跨域访问
                href:context + "" + url
            });
        }
    }
	//右键菜单的具体实现
	function closeTab(menu, type){
		var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");
        if (type === "mm-tabclose") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        
        var homeOpt = allTabs[0].panel("options");
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "mm-tabcloseother") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "mm-tabcloseall") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "mm-tabcloseright") {
            	//关闭当前右侧的tab
            	var nextall = $('.tabs-selected').nextAll();
            	if(nextall.length > 0){
            		nextall.each(function(i, n) {
            			var t = $('a:eq(0) span', $(n)).text();
            			if(t == homeOpt.title && homeOpt.closable){
            				closeTabsTitle.push(t);
            			}else if(t != homeOpt.title){
            				closeTabsTitle.push(t);
            			}
            		});
            	}
            	
            } else if (opt.closable && type === "mm-tabcloseleft"){
            	//关闭当前左侧的tab
            	var prevAll = $('.tabs-selected').prevAll();
            	if(prevAll.length > 0){
            		prevAll.each(function(i, n) {
            			var t = $('a:eq(0) span', $(n)).text();
            			if(t == homeOpt.title && homeOpt.closable){
            				closeTabsTitle.push(t);
            			}else if(t != homeOpt.title){
            				closeTabsTitle.push(t);
            			}
            		});
            	}
            }
        });
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
	}
	$(function(){
		openEditPassWin();
		//修改密码
		$('#editpass').click(function() {
	        $('#w').window('open');
	    });
		//取消按钮
		$('#btnCancel').click(function(){
			closePwd();
		});
		//确认按钮
		$('#btnEp').click(function() {
            doEditPassword();
        });
		
		//绑定右键菜单
		$("#tabs").tabs({
	        onContextMenu : function (e, title) {
	            e.preventDefault();
	            $('#mm').menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            }).data("tabTitle", title);
	        }
	    });
		
		//绑定右键菜单的onClick事件
	    $("#mm").menu({
	        onClick : function (item) {
	            closeTab(this, item.id);
	        }
	    });
	});
	
	</script>
    <script>
        <%-- KindEditor.ready(function(K) {
            editor = K.create('textarea[name="content"]', {
                    allowFileManager : true,
                    cssPath : '<%= context %>/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%= context %>/js/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%= context %>/js/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
					});
				}
			});
			prettyPrint();
                }); --%>
    </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<!-- 上面部分begin -->
	<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(<%=context %>/img/main/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:20px;" class="head">欢迎 ${user.userName} <a href="javascript:void(0)" id="editpass">修改密码</a> <a href="<%=context %>/login/doLogout.do" id="loginOut">安全退出</a></span>
		<span style="padding-left:10px; font-size: 16px; float:left;"><img src="<%=context %>/img/main/blocks.gif" width="20" height="20" align="absmiddle" />后台管理系统</span>
	</div>
	<!-- 上面部分end -->
	
	<!-- 下面部分begin -->
	<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By 小代 Email:<a href="mailto:swpigris81@126.com">swpigris81@126.com</a> </div>
    </div>
	<!-- 下面部分end -->
	
	<!-- 左面部分begin -->
	<div region="west" hide="true" split="true" title="导航菜单" style="width:200px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
			<!-- 菜单 -->
			<c:forEach items="${menuList}" var="menu" varStatus="status">
				<%-- <div title="${menu.menuName}" data-options="href:'<%=context %>/menu/queryChildMenu.do?menuId=${menu.menuId}'">${menu.menuName}</div> --%>
				<div title="${menu.menuName}">
					<ul id="tree_${status.index}">
					<script type="text/javascript">
						$("#tree_${status.index}").tree({
							//data:data,
							url:"<%=context %>/menu/queryChildMenu.do?menuId=${menu.menuId}",
							lines : true,
							onClick :function(node){
								//alert(node.text);
								//alert(node.attributes.url);
								var tree = $(this).tree;
								var isLeaf = tree('isLeaf', node.target);
								if(isLeaf){
									//叶子节点跳转
									var url = node.attributes.menuUrl;
									openUrl(node.text, url);
								}
							}
						});
					</script>
					</ul>
				</div>
			</c:forEach>
		</div>
    </div>
	<!-- 左面部分end -->
	
	<!-- 主窗口部分begin -->
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="首&nbsp;&nbsp;页" style="padding:20px;overflow:hidden;" id="home">
			<h1>欢迎您登录后台管理系统！</h1>
            <hr style="width: 100%;"/>
            <div style="float: left;margin:20px 20px; ">
                <fieldset>
                    <legend>费用管理</legend>
                    <table>
                        <tr>
                            <td>费用到期预通知：(<a id="" href="#" style="color: red;" onclick="openUrl('用户资金管理','/funds/index.do')">${costExpirePreNotice }</a>)条 </td>
                        </tr>
                        <tr>
                            <td>费用到期通知：(<a id="" href="#" style="color: red;"onclick="openUrl('用户资金管理','/funds/index.do')">${costExpireNotice }</a>)条 </td>
                        </tr>
                        <tr>
                            <td>未处理订单：(<a id="" href="#" style="color: red;" onclick="openUrl('订单管理','/order/orderIndex.do')">${untreatedOrder }</a>)条 </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
            <div style="float: left;margin-top: 20px; ">
                <fieldset>
                    <legend>认证管理</legend>
                    <table>
                        <tr>
                            <td>急救车认证信息审核：(<a id="" href="#" style="color: red;"onclick="openUrl('基本资料审核','/authentiction/cusBasicInfoIndex.do')">${ambulanceAuthentication }</a>)条 </td>
                        </tr>
                        <tr>
                            <td>120中心认证信息审核：(<a id="" href="#" style="color: red;" onclick="openUrl('基本资料审核','/authentiction/cusBasicInfoIndex.do')">${a120Authentication }</a>)条 </td>
                        </tr>
                        <tr>
                            <td>手机号变更申请：(<a id="" href="#" style="color: red;"onclick="openUrl('手机变更申请','/authentiction/phoneChangeApplyIndex.do')">${phoneChangeCheck }</a>)条 </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
            <div style="float: left;margin:20px 20px; ">
                <fieldset>
                    <legend>资金管理</legend>
                    <table>
                        <tr>
                            <td>等待审核的提现：(<a id="" href="#" style="color: red;"onclick="openUrl('提现管理','/withdraw/index.do')">${waitWithdrawals }</a>)条 </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
            <!-- <textarea name="content" style="width:800px;height:400px;">KindEditor</textarea> -->
			</div>
            
		</div>
        
    </div>
	<!-- 主窗口部分end -->
	
	<!-- 其他部分begin -->
	<!-- 修改密码 -->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 180px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <form id="editPasswordForm">
	                <table cellpadding=3>
                		<tr>
	                        <td>原密码：</td>
	                        <td><input id="txtOldPass" type="password" class="easyui-validatebox" required="true" /></td>
	                    </tr>
	                    <tr>
	                        <td>新密码：</td>
	                        <td><input id="txtNewPass" type="password" class="easyui-validatebox" required="true" validType="length[6,20]" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input id="txtRePass" type="password" class="easyui-validatebox" required="true" validType="length[6,20]"/></td>
	                    </tr>
                	</table>
                </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a>
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
    
    <div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<!-- 其他部分end -->
</body>
</html>

