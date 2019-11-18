$(function() {
	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
		$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    }) 
	
	$("#loginBtn").click(function() {
		console.log("login");
		var username = $("input[name=username]").val();
		var password = $("input[name=password]").val();//hex_md5( hex_md5($("input[name=password]").val()) );
		var yzmpassword = $("input[name=yzmpassword]").val();
		var user = {
			"uname" : username,
			"upassword" : password,
			"yzmpassword" : yzmpassword
		};
		alert(staticPath);
		alert(username);
		alert(password);
		alert(yzmpassword);
		$.ajax({
			type : "post",
			dataType : "json",
			data : user,
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : staticPath + '/a/login',
			// 同步async: false为同步，这个方法中的Ajax请求将整个浏览器锁死，
			async : false,
			success : function(data) {
				if (false == data.loginResult) {
					alert("用户名或者密码错误，请重新登录！");
				} else if (true == data.loginResult) {
					alert("登录成功！");
					var indexUrl = window.location.protocol+"//"+window.location.host+window.location.pathname+"html/index.html";
					window.location = indexUrl;
				}
			},
			error : function() {
				alert("服务器发生故障，请尝试重新登录！");
			}
		});
	});

	//验证码
	$("#changeImg").click(function() {
		$("#changeImg").attr("src",staticPath+'/kaptcha.jpg?data='+Math.random());
	});
	
});
