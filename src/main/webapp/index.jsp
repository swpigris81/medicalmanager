<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/common/include.jsp" flush="true"/>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
		<title>后台管理系统</title>
		<link rel="stylesheet" type="text/css" href="css/denglu.css" />
		<link rel="stylesheet" type="text/css" href="css/public.css" />
		<script type="text/javascript" src="js/jquery.SuperSlide.2.1.js"></script>
		
		<style>
			.carousel{width: 750px; height: 430px; float: left;}				
			/* 本例子css */
			.focusBox{ margin:0 auto;  position:relative; width:750px; height:330px; margin-top: 50px; padding:5px; background:#f5f5f5; overflow:hidden; }
			.focusBox .hd{ height:22px; position:absolute; z-index:1; bottom:10px; right:10px; overflow:hidden;   }
			.focusBox .hd ul{ float:right;  overflow:hidden;  }
			.focusBox .hd li{ width:22px; height:22px; line-height:22px; text-align:center; background:#999; color:#fff; font-family:Arial; float:left; margin:0 1px; display:inline;
				-webkit-border-radius:22px; -moz-border-radius:22px; border-radius:22px; cursor:pointer;
			}
			.focusBox .hd li.on{ background:#c00; }
			.focusBox .bd li{ vertical-align:middle; }
			.focusBox .bd li img{ width:750px; height:330px; display:block;  background:url(img/denglu/loading.gif) center center no-repeat;  }
		</style>
		
		<script type="text/javascript">
		function doLogin(){
			if(!$("#agree_checkbox").is(":checked")){
				alert("请同意《用户使用协议》！");
				return;
			}
			var name = $("#name").val();
			var pass = $("#password").val();
			if(!$.trim(name)){
				alert("登录用户名不能为空！");
				return;
			}
			if(!$.trim(pass)){
				alert("登录密码不能为空！");
				return;
			}
			var url = "./login/doLogin.do";
			var param = {};
			param.userCode = name;
			param.passWord = pass;
			ajaxCall(null, url, param, function(data){
				//location.href = "./user/index.do";
				location.href = "./main/index.do";
			});
		}
		$(document).ready(function(){
			$("#name").keyup(function(e){
				var curKey = e.which;
				if(curKey == 13){
					$("#password").focus();
					return;
				}
			});
			
			$("#password").keyup(function(e){
				var curKey = e.which;
				if(curKey == 13){
					doLogin();
					return;
				}
			});
		});
		</script>
		
	</head>
	<body>
		<!-- 整体内容开始 -->
		<div class="denglu">
			<!--第一部分内容开始-->
			<div class="denglu-first1">
				<div class="denglu-first1-content">
					<div class="denglu-first1-content-left">
						<div class="logo">
							<img src="img/denglu/logo.png"  />
						</div>
						<div class="biaoti">
							<p>3000多个实力品牌强势入驻</p>
						</div>
					</div>
					<div class="denglu-first1-content-right">
						<p class="jiamengrexian">快速代理加盟热线</p>
						<p class="dianhua">400-9955-520</p>
					</div>
				</div>
			</div>
			<!--第一部分内容结束-->	
			<!--第二部分内容开始-->
			<div class="denglu-frist2">
				<div class="denglu-first2-content">
					<div class="denglu-first2-content-top">
						<div class="carousel">
							<div class="focusBox">
								<div class="bd">
									<ul>
										<li><a  target="_blank" href="#"><img _src="img/denglu/bg.jpg" src="img/denglu/3.png" /></a></li>
										<li><a  target="_blank" href="#"><img _src="img/denglu/bg.jpg" src="img/denglu/3.png" /></a></li>
										<li><a  target="_blank" href="#"><img _src="img/denglu/bg.jpg" src="img/denglu/3.png" /></a></li>
										<li><a  target="_blank" href="#"><img _src="img/denglu/bg.jpg" src="img/denglu/3.png" /></a></li>
									</ul>
								</div>
								<div class="hd">
									<ul></ul>
								</div>
							</div>
							<script type="text/javascript">
							jQuery(".focusBox").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"top", autoPlay:true, autoPage:true, switchLoad:"_src" });
							</script>
						</div>
						
						<div class="denglu-top-contents">
							<div class="denglu-top-contents-top">
								<p><span>最新公告：</span><a href="#">最新农家店加盟</a>&nbsp;&nbsp;合肥市肥东县**&nbsp;&nbsp;恭喜李小姐加盟 !</p>
							</div>
							<div class="denglu-top-contents-bottom">
								<div class="contents-bottom-neirong">
									<div class="contents-bottom-neirong-first1">
										<div class="hasMoreTab" style="margin:0 auto">
											<div class="hd">
												<ul>
													<li class="li1">管理员登录</li>
													<!-- <li>商家用户登录入口</li> -->
												</ul>
											</div>
											<div class="bd">
												<div class="conWrap">
													<div class="con">
														<div class="yonghuming">
															<input type="text" id="name" name="name" value="" placeholder="用户名" class="shurukuang"/>
														</div>
														<div class="yonghuming-mima">
															<input type="password" id="password" name="password" value="" placeholder="密码" class="shurukuang"/>
														</div>
														<div class="xieyi">
															<p>同意<span>《用户使用协议》</span></p>
															<input type="checkbox" checked="checked" class="danxuan" id="agree_checkbox"/>
														</div>
														<a href="javascript:doLogin()">
															<div class="denglu1">
																<p>登录</p>
															</div>
														</a>
														<div class="zhuce">											
															<p>
																还没有登录账号？
																<a href="zhuce.html">
																	<span>点击注册</span>
																</a>
															</p>
														</div>
														<!-- 
														<div class="qqdenglu">
															<div class="qqdenglu-left">												
															</div>
															<a href="#">
																<div class="qqdenglu-middle">
																	使用第三方账号登录
																</div>
															</a>
															<div class="qqdenglu-right">												
															</div>
														</div>
														 -->
													</div>
													<!-- <div class="con">
														<div class="yonghuming">
															<input type="text" id="name" name="name" value="" placeholder="手机号" class="shurukuang"/>
														</div>										
														<div class="yonghuming_mima1">
															<div class="yonghuming_mima">
																<input type="text" id="name" name="name" value="" placeholder="验证码" class="shurukuang"/>
															</div>
															<a href="#">
																<div class="yzmdx">
																	发送验证码
																</div>
															</a>
														</div>
														<div class="nicheng">
															<input type="text" id="name" name="name" value="" placeholder="昵称" class="shurukuang"/>
														</div>
														<div class="mima">
															<input type="password" id="name" name="name" value="" placeholder="密码" class="shurukuang"/>
														</div>
														<div class="dizhi">
															<p>安徽</p>
															<form>
																<select>
																	<option>合肥市</option>
																	<option>安庆市</option>
																	<option>巢湖市</option>
																	<option>六安市</option>
																</select>
																<select>
																	<option>高新区</option>
																	<option>蜀山区</option>
																	<option>瑶海区</option>
																	<option>包河区</option>
																	<option>政务区</option>
																</select>
															</form>
														</div>
														<a href="#">
															<div class="denglu1">
																<p>登录</p>
															</div>
														</a>
														<div class="xieyi">											
															<p>同意<span>《用户使用协议》</span></p>
															<input type="checkbox" class="danxuan" />
														</div>
														<div class="qqdenglu">
															<div class="qqdenglu-left">												
															</div>
															<a href="#">
																<div class="qqdenglu-middle">
																	使用第三方账号登录
																</div>
															</a>
															<div class="qqdenglu-right">												
															</div>
														</div>
													</div> -->
												</div>
											</div>
										</div>
										<script type="text/javascript">
										jQuery(".hasMoreTab").slide({ mainCell:".conWrap", targetCell:".more a", effect:"fold"});
										</script>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="denglu-first2-content-bottom">
						<div class="bottom-left">
							<div class="bottom-left-top">
								<p>安全保障</p>
							</div>
							<div class="bottom-left-footer">
								<p>
									**********************
								</p>
							</div>
						</div>
						<div class="bottom-middle">
							<div class="bottom-left-top">
								<p>一键登录</p>
							</div>
							<div class="bottom-left-footer">
								<p>
									支持邮箱、手机号、昵称等多种方式登录，方便快捷
								</p>
							</div>
						</div>
						<div class="bottom-right">
							<div class="bottom-left-top">
								<p>贴心客服</p>
							</div>
							<div class="bottom-left-footer">
								<p>
									客服热线：400-9955-520
								</p>
								<p>
									客服邮箱：********@qq.com
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--第二部分内容结束-->
		</div>
		<!--登录尾部内容开始-->
		<div class="denglu-footer">
			<div class="denglu-footer-content">
				*****有限公司 版权所有 2015-2016  蜀ICP备1111111111111号&nbsp;&nbsp;
				<a href="#">关于我们</a>&nbsp; |&nbsp;
				<a href="#">帮助中心</a> &nbsp;|&nbsp;
				<a href="#">网站合作</a>&nbsp;|&nbsp;
				<a href="#">版权说明</a>&nbsp;|&nbsp;
				<a href="#">诚聘英才</a>&nbsp;|&nbsp;
				<a href="#">联系我们</a>
			</div>
		</div>
		<!--登录尾部内容结束-->
	</body>
</html>
