<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="source/images/logo-b.png" />
<link rel="stylesheet" href="source/css/iconfont.css">
<link rel="stylesheet" href="source/css/global.css">
<link rel="stylesheet" href="source/css/bootstrap.css">
<link rel="stylesheet" href="source/css/bootstrap-theme.css">
<link rel="stylesheet" href="source/css/login.css">


<script src="source/js/jquery.min.js" charset="UTF-8"></script>
<script src="source/js/bootstrap.min.js" charset="UTF-8"></script>
<script src="source/js/jquery.form.js" charset="UTF-8"></script>
<script src="source/js/global.js" charset="UTF-8"></script>
<script src="source/js/login.js" charset="UTF-8"></script>

<title>云通-登录</title>
</head>
<body>
	<div class="public-head-layout container">
		<a class="logo" href="getIndexPage.do" style="margin-left: -130px;margin-top:-20px;"><img
			src="source/images/logo.png"></a>
	</div>
		<div
		style="background: url(source/images/login.jpg) no-repeat left top; margin-top: -10px;">
		<!-- 登录 -->
		<div class="login-layout container">
			<div class="form-box login">
				<div class="tabs-nav">
					<h2>欢迎登录云通</h2>
				</div>
				<div class="tabs_container">
					<form class="tabs_form" action="" method="post" id="login_form">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
								</div>
								<input class="form-control phone" name="phone" id="login_phone"
									required placeholder="手机号" maxlength="11" autocomplete="off"
									type="text">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</div>
								<input class="form-control password" name="password"
									id="login_pwd" placeholder="请输入密码" autocomplete="off"
									type="password">
								<div class="input-group-addon pwd-toggle" title="显示密码">
									<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						<div class="checkbox">
							<label> <input checked="" id="login_checkbox"
								type="checkbox"><i></i> 30天内免登录
							</label> <a href="javascript:;" style="text-decoration: none;"
								class="pull-right" id="resetpwd">忘记密码？</a>
						</div>
						<!-- 错误信息 -->
						<div class="form-group">
							<div class="error_msg" id="login_error">
								<!-- 错误信息 范例html
								<div class="alert alert-warning alert-dismissible fade in" role="alert">
									<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<strong>密码错误</strong> 请重新输入密码
								</div>
								 -->
							</div>
						</div>
						<button class="btn btn-large btn-info btn-lg btn-block submit"style="width:140px;float:left;margin-right:20px;"
							id="login_submit" type="button">登录</button>
						<a href="faceRecognition.jsp" style="	text-decoration: none;cursor:pointer;"><button class="btn btn-large btn-warning btn-lg btn-block"style="width:140px;"
                                   id="" type="button">刷脸登录</button></a>
						<br>
						<p class="text-center">
							没有账号？<a href="javascript:;" id="register"
								style="text-decoration: none;color:#1997d7">免费注册</a>
						</p>
					</form>
					<div class="tabs_div">
						<div class="success-box">
							<div class="success-msg">
								<i class="success-icon"></i>
								<p class="success-text">登录成功</p>
							</div>
						</div>
						<div class="option-box">
							<div class="buts-title">现在您可以</div>
							<div class="buts-box">
								<a role="button" href="index.jsp"
									class="btn btn-block btn-lg btn-info">进入管理系统</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 注册 -->
			<div class="form-box register">
				<div class="tabs-nav">
					<h2>
						欢迎注册<a href="javascript:;" style="text-decoration: none;"
							class="pull-right fz16" id="reglogin">返回登录</a>
					</h2>
				</div>
				<div class="tabs_container">
					<form class="tabs_form" action="" method="post" id="register_form">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
								</div>
								<input class="form-control phone" name="phone"
									id="register_phone" required placeholder="手机号" maxlength="11"
									autocomplete="off" type="text">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</div>
								<input class="form-control password" name="password"
									id="register_pwd" placeholder="请输入密码" autocomplete="off"
									type="password">
								<div class="input-group-addon pwd-toggle" title="显示密码">
									<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input class="form-control smscode" name="smscode"
									id="register_sms" placeholder="输入验证码" maxlength="6" type="text">
								<span class="input-group-btn">
									<button class="btn btn-info getsms" type="button">发送短信验证码</button>
								</span>
							</div>
						</div>
						<div class="checkbox">
							<label> <input class="register_checkbox" checked="" han="true"  id="register_checkbox"
								type="checkbox"><i></i> 同意
								<a style="text-decoration: none;" href="#">云通用户协议</a>
							</label>
						</div>
						<!-- 错误信息 -->
						<div class="form-group">
							<div class="error_msg" id="register_error"></div>
						</div>
						<button class="btn btn-large btn-info btn-lg btn-block submit"
							id="register_submit" type="button">注册</button>
					</form>
					<div class="tabs_div r">
						<div class="success-box">
							<div class="success-msg">
								<i class="success-icon"></i>
								<p class="success-text">注册成功</p>
							</div>
						</div>
						<div class="option-box">
							<div class="buts-title">现在您可以</div>
							<div class="buts-box">
								 <a role="button" href="index.jsp"
									class="btn btn-block btn-lg btn-info">进入管理系统</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 忘记密码 -->
			<div class="form-box resetpwd">
				<div class="tabs-nav clearfix">
					<h2>
						找回密码<a href="javascript:;" style="text-decoration: none;" class="pull-right fz16" id="pwdlogin">返回登录</a>
					</h2>
				</div>
				<div class="tabs_container">
					<form class="tabs_form"
						action="https://rpg.blue/member.php?mod=logging&action=login"
						method="post" id="resetpwd_form">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
								</div>
								<input class="form-control phone" name="phone"
									id="resetpwd_phone" required placeholder="手机号" maxlength="11"
									autocomplete="off" type="text">
							</div>
						</div>
						
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</div>
								<input class="form-control password" name="password"
									id="resetpwd_pwd" placeholder="新的密码" autocomplete="off"
									type="password">
								<div class="input-group-addon pwd-toggle" title="显示密码">
									<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<input class="form-control sms" name="sms" id="resetpwd_sms"
									placeholder="输入验证码" type="text"> <span
									class="input-group-btn">
									<button class="btn btn-info getsms getresms" type="button">发送短信验证码</button>
								</span>
							</div>
						</div>
						<!-- 错误信息 -->
						<div class="form-group">
							<div class="error_msg" id="resetpwd_error"></div>
						</div>
						<button class="btn btn-large btn-info btn-lg btn-block submit"
							id="resetpwd_submit" type="button">重置密码</button>
					</form>
					<div class="tabs_div">
						<div class="success-box">
							<div class="success-msg">
								<i class="success-icon"></i>
								<p class="success-text">密码重置成功</p>
							</div>
						</div>
						<div class="option-box">
							<div class="buts-title">现在您可以</div>
							<div class="buts-box">
<a role="button" href="login.jsp" class="btn btn-block btn-lg btn-info">返回登录</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script>
				$(document).ready(function() {
					// 判断直接进入哪个页面 例如 login.jsp?p=register
					switch($.getUrlParam('p')) {
						case 'register': $('.register').show(); break;
						case 'resetpwd': $('.resetpwd').show(); break;
						default: $('.login').show();
					};
					// 发送验证码事件(注册模块)
					$('.getsms').click(function() {
						var phone = $(this).parents('form').find('input.phone');
						var error = $(this).parents('form').find('.error_msg');
						switch(phone.validatemobile()) {
							case 0:
								// 短信验证码的请求,先检测手机号是否已经注册
								switch(phone.validatePhone()){
								case 0://若没有注册，发送验证码
									switch($(this).sentCode(phone)){
									case 0:
										error.html(msgtemp('验证码 <strong>已发送</strong>','alert-success'));
										$(this).rewire(60);
										break;
									default:
										error.html(msgtemp('验证码 <strong>发送失败</strong>','alert-warning'));
									    break;
									}
									break;
								default: //若已经注册，则提醒手机号码已经注册，请进行登录
								    error.html(msgtemp('<strong>手机号码已注册</strong> 请直接登录',  'alert-warning')); return; break;
								    break;	
								}
							break;
							case 1: error.html(msgtemp('<strong>手机号码为空</strong> 请输入手机号码',    'alert-warning')); break;
							case 2: error.html(msgtemp('<strong>手机号码错误</strong> 请输入11位数的号码','alert-warning')); break;
							case 3: error.html(msgtemp('<strong>手机号码错误</strong> 请输入正确的号码',  'alert-warning')); break;
						}
					});
					
					// 发送验证码事件(忘记密码模块)
					$('.getresms').click(function() {
						var phone = $(this).parents('form').find('input.phone');
						var error = $(this).parents('form').find('.error_msg');
						switch(phone.validatemobile()) {
							case 0:
								// 短信验证码的请求,先检测手机号是否已经注册
								switch(phone.validatePhone()){
								case 0://未注册
									error.html(msgtemp('<strong>手机号码未注册</strong> 请进行注册',  'alert-warning'));
								    break;
								default: //若已经注册，则发送验证码
								    switch($(this).sentCode(phone)){
									case 0:
										error.html(msgtemp('验证码 <strong>已发送</strong>','alert-success'));
										$(this).rewire(60);
										break;
									default:
										error.html(msgtemp('验证码 <strong>发送失败</strong>','alert-warning'));
									    break;
									}
									break;
								}
							break;
							case 1: error.html(msgtemp('<strong>手机号码为空</strong> 请输入手机号码',    'alert-warning')); break;
							case 2: error.html(msgtemp('<strong>手机号码错误</strong> 请输入11位数的号码','alert-warning')); break;
							case 3: error.html(msgtemp('<strong>手机号码错误</strong> 请输入正确的号码',  'alert-warning')); break;
						}
					});
					// 登录按钮
					$('#login_submit').click(function() {
						var form = $(this).parents('form');
						var phone = form.find('input.phone');
						var pwd = form.find('input.password');
						var error = form.find('.error_msg');
						var success = form.siblings('.tabs_div');
						var options = {
							beforeSubmit: function () {
							},
							success: function (data) {
								console.log(data)
								alert(data)
							}
						}
						// 验证手机号参考这个
						switch(phone.validatemobile()) {
							case 1: error.html(msgtemp('<strong>手机号码为空</strong> 请输入手机号码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>手机号码错误</strong> 请输入11位数的号码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>手机号码错误</strong> 请输入正确的号码',  'alert-warning')); return; break;
						}
						// 验证密码复杂度参考这个
						switch(pwd.validatepwd()) {
							case 1: error.html(msgtemp('<strong>密码不能为空</strong> 请输入密码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>密码过短</strong> 请输入6位以上的密码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>密码过于简单</strong><br>密码需为字母、数字或特殊字符组合',  'alert-warning')); return; break;
						}
						switch(phone.validateUser(pwd)){
						    case 1: error.html(msgtemp('<strong>用户名或密码错误</strong> 请重新输入','alert-warning')); return; break;
						}
						form.ajaxForm(options);
						 form.fadeOut(150,function() {
						 	success.fadeIn(150);
						 });
					})
					var isChecked = false;
					$("#register_checkbox").click(function(){
						if(isChecked){
							$(this).attr("han","true")
							isChecked = false;
						}else{
							$(this).attr("han","false")
							isChecked = true;
						}
					})
					
					// 注册按钮
					$("#register_submit").click(function() {
						var form = $(this).parents('form');
						var phone = form.find('input.phone');
						var smscode = form.find('input.smscode');
						var pwd = form.find('input.password');
						var error = form.find('.error_msg');
						var success = form.siblings('.tabs_div');
						var register_checkbox = form.find("input.register_checkbox").attr("han");
						var options = {
							beforeSubmit: function () {
								console.log('喵喵喵')
							},
							success: function (data) {
								console.log(data)
							}
						}
						// 验证手机号
						switch(phone.validatemobile()) {
							case 1: error.html(msgtemp('<strong>手机号码为空</strong> 请输入手机号码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>手机号码错误</strong> 请输入11位数的号码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>手机号码错误</strong> 请输入正确的号码',  'alert-warning')); return; break;
						}
						//验证手机号码唯一性
						switch(phone.validatePhone()){
						case 1: error.html(msgtemp('<strong>手机号码已注册</strong> 请直接登录',  'alert-warning')); return; break;
						}
						// 验证密码复杂度
						switch(pwd.validatepwd()) {
							case 1: error.html(msgtemp('<strong>密码不能为空</strong> 请输入密码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>密码过短</strong> 请输入6位以上的密码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>密码过于简单</strong><br>密码需为字母、数字或特殊字符组合',  'alert-warning')); return; break;
						}
						//验证码校验
					 	switch(smscode.validateCode()){
						    case 1: error.html(msgtemp('<strong>验证码错误</strong> 请重新输入','alert-warning')); return; break;
						    case 2: error.html(msgtemp('<strong>验证码为空</strong> 请输入','alert-warning')); return; break;
						} 
						if(register_checkbox!='true'){
							error.html(msgtemp('<strong>未同意爱购协议</strong> 请同意本协议','alert-warning'));
						}else{
						//成功
						 switch(phone.save(pwd)){
						 case 1: error.html(msgtemp('<strong>网络错误</strong> 请重新注册','alert-warning')); return; break;
						 default: 
							 form.fadeOut(150,function() {
						 	    success.fadeIn(150);
						     });
						     break;
						 }
						}
						 
					})
					
					
					// 忘记密码按钮
					$("#resetpwd_submit").click(function() {
						var form = $(this).parents('form');
						var phone = form.find('input.phone');
						var smscode = form.find('input.sms');
						var pwd = form.find('input.password');
						var error = form.find('.error_msg');
						var success = form.siblings('.tabs_div');
						var options = {
							beforeSubmit: function () {
								console.log('喵喵喵')
							},
							success: function (data) {
								console.log(data)
							}
						}
						// 验证手机号
						switch(phone.validatemobile()) {
							case 1: error.html(msgtemp('<strong>手机号码为空</strong> 请输入手机号码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>手机号码错误</strong> 请输入11位数的号码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>手机号码错误</strong> 请输入正确的号码',  'alert-warning')); return; break;
						}
						//验证手机号码唯一性
						switch(phone.validatePhone()){
						case 0: error.html(msgtemp('<strong>手机号码不存在</strong> 请直接注册',  'alert-warning')); return; break;
						}
						// 验证密码复杂度
						switch(pwd.validatepwd()) {
							case 1: error.html(msgtemp('<strong>密码不能为空</strong> 请输入密码',    'alert-warning')); return; break;
							case 2: error.html(msgtemp('<strong>密码过短</strong> 请输入6位以上的密码','alert-warning')); return; break;
							case 3: error.html(msgtemp('<strong>密码过于简单</strong><br>密码需为字母、数字或特殊字符组合',  'alert-warning')); return; break;
						}
						//验证码校验
					 	switch(smscode.validateCode()){
						    case 1: error.html(msgtemp('<strong>验证码错误</strong> 请重新输入','alert-warning')); return; break;
						    case 2: error.html(msgtemp('<strong>验证码为空</strong> 请输入','alert-warning')); return; break;
						}  
						//成功
						 switch(phone.update(pwd)){
						 case 1: error.html(msgtemp('<strong>网络错误</strong> 请重新操作','alert-warning')); return; break;
						 default: 
							 form.fadeOut(150,function() {
						 	    success.fadeIn(150);
						     });
						     break;
						 }
						 
					})
				});
			</script>
		</div>
	</div>
	<div class="footer-login container clearfix" style="margin-top: 20px;">
		<ul class="links">
			<a style="text-decoration: none;" href=""><li>关于我们</li></a>
			<a style="text-decoration: none;" href=""><li>技术支持</li></a>
			<a style="text-decoration: none;" href=""><li>联系我们</li></a>
			<a style="text-decoration: none;" href=""><li>帮助中心</li></a>
			<a style="text-decoration: none;" href=""><li>意见反馈</li></a>
		</ul>
		<!-- 版权 -->
		<p class="copyright">
			© 2015-2019 云通WMS 版权所有，并保留所有权利<br>
			ICP备案证书号：鲁ICP备18010745号&nbsp;&nbsp;&nbsp;&nbsp;青岛市黄岛区嘉陵江东路777号青岛理工大学&nbsp;&nbsp;&nbsp;&nbsp;Tel:
			13256825958&nbsp;&nbsp;&nbsp;&nbsp;E-mail: 1368308653@qq.com
		</p>
	</div>
</body>
</html>