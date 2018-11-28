/*
 * JavaScript code For Login
 * 
 * @author : VIPArcher
 * @Email : VIPArcher@sina.com
 * @date : 20170527
 *
 *  Copyright 2017 VIPArcher
 */


function msgtemp(msg,className) {
	return `<div class="alert ${className} alert-dismissible fade in" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span></button>${msg}</div>`
};
(function($){
	$.fn.extend({
		/* 重置验证码发送按钮 */
		rewire: function (time){
			var $this = $(this);
			var time = time || 60;
			time -= 1;
			if (time == 0) {
				$this.removeAttr("disabled");
				$this.html("获取验证码");
			} else {
				$this.prop("disabled", true);
				$this.html("重新发送（{0}）".format(time));
				setTimeout(function() { $this.rewire(time) }, 1000);
			}
		},
		sentCode: function (phone){//发送验证码
			var code = 1;
			$.ajax({
			    url : "sentCode.do", //(默认: 当前页地址) 发送请求的地址
				type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
				async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
				data: {
					username:phone.val(),
		        },
				dataType:'json',
				success: function(json, textStatus){
					console.log(textStatus);
					var flag = json.flag;
				    if(flag == 'true'){
				    	code=0;
					}
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
			         console.log(textStatus);
			    }   
			});
			return code;
		},
		validateCode: function(){//验证码校验
			var code = $(this).val();
			if(code==null||code==''){
				return 2;
			}else{
			var isCode = 1;
			$.ajax({
			    url : "validateCode.do", //(默认: 当前页地址) 发送请求的地址
				type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
				async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
				data: {
					code:code,
		        },
				dataType:'json',
				success: function(json, textStatus){
					console.log(textStatus);
					var flag = json.flag;
				    if(flag == 'true'){
				    	isCode=0;
					}
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
			         console.log(textStatus);
			    }   
			});
			return isCode;
			}
		},
		/*
		 * 验证手机号码
		 * 
		 * @return 0,1,2,3
		 *		0:验证成功; 1:内容为空;  2长度不为11位; 3:格式不对;。
		 */
		validatemobile: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1;
			} else if (num.length != 11) {
				$(this)[0].focus();
				return 2;
			}else {
				var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				if(!reg.test(num)) {
					$(this)[0].focus();
					return 3;
				} else {
					return 0;
				}
			}
		},
		//手机号码唯一性检测
		validatePhone: function(){
			var isPhone = 1;
			var phone = $(this).val();
			$.ajax({
			    url : "validatePhone.do", //(默认: 当前页地址) 发送请求的地址
				type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
				async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
				data: {
					username:phone,
		        },
				dataType:'json',
				success: function(json, textStatus){
					console.log(textStatus);
					var flag = json.flag;
				    if(flag == 'true'){
				    	isPhone=0;
					}
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
			         console.log(textStatus);
			    }   
			});
			return isPhone;
		},
		/*
		 * 验证密码 $phone 被验证的输入框jQ对象
		 * 字母+数字，字母+特殊字符，数字+特殊字符，至少6位
		 * @return 0,1,2,3
		 *		0:验证成功; 1:内容为空; 2:长度过短; 3:格式不对。
		 */
		validatepwd: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1
			} else if (num.length < 6) {
				$(this)[0].focus();
				return 2
			} else {
				var  reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
				if(!reg.test(num)) {
					$(this)[0].focus();
					return 3;
				} else {
					return 0;
				}
			}
		},
		//注册用户(电话号码、密码)
		save:function(pwd){
			var isSave = 1;
			$.ajax({
			    url : "saveUser.do", //(默认: 当前页地址) 发送请求的地址
				type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
				async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
				data: {
					username:$(this).val(),
					password:pwd.val()
		        },
				dataType:'json',
				success: function(json, textStatus){
					console.log(textStatus);
					var flag = json.flag;
				    if(flag == 'true'){
				    	isSave=0;
					}
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
			         console.log(textStatus);
			    }   
			});
			return isSave;
		},
		//忘记密码(根据电话号码更新密码)
		update:function(pwd){
			var isUpdate = 1;
			$.ajax({
			    url : "updateUser.do", //(默认: 当前页地址) 发送请求的地址
				type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
				async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
				data: {
					username:$(this).val(),
					password:pwd.val()
		        },
				dataType:'json',
				success: function(json, textStatus){
					console.log(textStatus);
					var flag = json.flag;
				    if(flag == 'true'){
				    	isUpdate = 0;
					}
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
			         console.log(textStatus);
			    }   
			});
			return isUpdate;
		},
		//用户名密码验证
		validateUser: function(pwd){
			var user = 1;
				$.ajax({
				    url : "login.do", //(默认: 当前页地址) 发送请求的地址
					type: "post", //(默认: "get") 请求方式 ("post" 或 "get")， 默认为 "get"。注意：其它 http请求方法，如 put和 delete也可以使用，但仅部分浏览器支持。
					async:false,//(默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
					contentType:"application/x-www-form-urlencoded",//(默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数应用场合。
					data: {
						username:$(this).val(),
						password:pwd.val()
			        },
					dataType:'json',
					success: function(json, textStatus){
						console.log(textStatus);
						var flag = json.flag;
					    if(flag == 'true'){
					    	user=0;
						}
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
				         alert("网络错误")
				    }   
				});
				return user;
		},

	});
})(jQuery);
$(document).ready(function() {
	// 隐藏/显示密码切换
	$('.pwd-toggle').on('click',function() {
		var icon = $(this).find('.glyphicon');
		if (icon.hasClass('glyphicon-eye-open')) {
			$(this).attr("title", "隐藏密码").siblings('input').prop('type', 'text');
			icon.removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');
		} else if (icon.hasClass('glyphicon-eye-close')) {
			$(this).attr("title", "显示密码").siblings('input').prop('type', 'password');
			icon.removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');
		}
	})
	$('#register').click(function() {$('.login').fadeOut(150,function() {$('.register').fadeIn(150)})});
	$('#resetpwd').click(function() {$('.login').fadeOut(150,function() {$('.resetpwd').fadeIn(150)})});
	$('#reglogin').click(function() {$('.register').fadeOut(150,function() {$('.login').fadeIn(150)})});
	$('#pwdlogin').click(function() {$('.resetpwd').fadeOut(150,function() {$('.login').fadeIn(150)})});
});