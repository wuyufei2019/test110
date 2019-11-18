<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<%--项目路径 --%>
<c:set var="ctx" value="${ctx}" />
<%
String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
request.setAttribute("error", error);
%>

<!DOCTYPE HTML >
<html>
<head>
<title>化工企业安全生产信息化管理平台身份认证</title>
<meta name="renderer" content="webkit">
<link rel="shortcut icon" href="${ctx}/static/model/images/favicon.ico" />
<script src="${ctx}/static/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/static/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctx}/static/bootstrap/3.3.4/css_default/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/model/js/login/login.js" type="text/javascript"></script>
<link href="${ctx}/static/model/css/login/login.css" rel="stylesheet" type="text/css" />
<script>
	var ctx="${ctx}";
//执行键盘按键命令
function keyDown(e){ 
 var keycode = 0;
 keycode = e.which;
 if (keycode == 13 ) //回车键是13
 {
 	if($("#username").val()!="" && $("#password").val()!="" && $("#captcha").val()!=""){
 		$("#loginlForm").submit();
 	}
 }
}
//判断访问者的浏览器是否为火狐或者谷歌浏览器
function CheckBrowserIs(){
	var result = false;
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var is = userAgent.indexOf("Firefox") > -1 || userAgent.indexOf("Chrome") > -1 ; 
	if(is)
		result = true;
	return result;
}

function openwin() {//判断浏览器加载事件
	if(!CheckBrowserIs()){
		showDiv();
	 }
}

function showDiv() {
	var Idiv = document.getElementById("index_top_ts");
	Idiv.style.display = "block";
}
function closeDiv() {//关闭弹出层
	var Idiv=document.getElementById("index_top_ts");
	Idiv.style.display="none";
}

// 如果在框架或在对话框中，则弹出提示并跳转到首页
if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
	alert('未登录或登录超时。请重新登录，谢谢！');
	top.location = "${ctx}";
}
</script> 

<style type="text/css">
#index_top_nr a:HOVER {
	color: #FFFFFF;
	text-decoration: underline;
}

* {
	margin: 0;
	padding: 0;
}

html,body {
	height: 100%;
}

#bd__ {
	height: 100%;
	background: url(${ctx}/static/model/images/login/bj5.jpg);
	background-color:#3796e8;
	background-size: cover;
	background-position: center;
	margin: 0;
	background-repeat: no-repeat;
	min-width: 1200px; overflow: hidden;
}

@font-face {
	font-family: 'neo';
	/*src: url("font/NEOTERICc.ttf");*/
}

input:focus {
	outline: none;
}

.form i {
	font-size: 14px;
	color: #ababab;
	margin: 8px;
	position:absolute;
}
.form.c__ i {
	font-size: 14px;
	color: #ababab;
	position:absolute;
	margin: 16px 8px 0;
}

#validateCode {
    float: right;
    margin: -3px 0 0 0;
    position: absolute;
    right: 0;
}

.form input {
	width: 300px;
	height: 30px;
	font-size: 16px;
	background: none;
	border: none;
	border-bottom: 1px solid #ababab;
	color: #333;
	margin-bottom: 20px;
	padding-left: 30px;
}

.form input::placeholder {
	color: rgba(255, 255, 255, 0.8);
	font-size: 18px;
	font-family: "neo";
}

.confirm {
	height: 0;
	overflow: hidden;
	transition: .25s;
}

.btn {
	width: 140px;
	height: 40px;
	border: 1px solid #118bfc;
	background: #118bfc;
	font-size: 20px;
	color: #fff;
	cursor: pointer;
	margin-top: 25px;
	font-family: "neo";
	transition: .25s;
}

.btn:hover {
	background: rgba(255, 255, 255, .25);
}

#login_wrap {
	width: 980px;
	height: 500px;
	font-family: "neo";
	/*overflow: hidden;*/
	box-shadow: 0px 0px 120px rgba(0, 0, 0, 0.3);
	position: fixed;
	top: 50%;
	right: 50%;
	margin-top: -250px;
	margin-right: -490px;
}

.form>input::-webkit-input-placeholder{
            color: #ababab;
            font-size: 15px;
        }
        input::-moz-placeholder{   /* Mozilla Firefox 19+ */
            color: #ababab;
            font-size: 15px;
        }
        input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
            color: #ababab;
            font-size: 15px;
        }
        input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
            color: #ababab;font-size: 15px;}

#login {
	width: 50%;
	height: 100%;
	height: 500px;
	background: linear-gradient(45deg, #ffffff, #ffffff);
	position: relative;
	float: right;
}

#login #status {
width: 110px;
    height: 35px;
    margin: 40px auto;
    color: #118bfc;
    font-size: 26px;
    font-weight: 600;
    position: relative;
    overflow: hidden;
}

#login #status i {
	font-style: normal;
	position: absolute;
	transition: .5s
}

#login span {
	text-align: center;
	position: absolute;
	left: 50%;
	margin-left: -150px;
	top: 52%;
	margin-top: -140px;
}

#login span a {
	text-decoration: none;
	color: #fff;
	display: block;
	margin-top: 80px;
	font-size: 18px;
}

#bg {
	background: linear-gradient(45deg, #211136, #bf5853);
	height: 100%;
}
/*绘图*/
#login_img {
	width: 50%;
	height: 500px;
	background-color:#ffffff3b;
	background-size: cover;
	background-position: center;
	float: left;
	position: relative;
}
#loginbtn{
color:white
}
#loginbtn:hover{
color:#118bfc
}
#login_img_ {
	width: 100%;
	height: 100%;
	background: url(${ctx}/static/model/images/login/bj6.png);
	background-color:rgba(56, 56, 56, 0.788235294117647);
	background-size: cover;
	background-position: center;
	float: left;
	position: relative;
}

#login_img span {
	position: absolute;
	display: none;
}

#login_img .circle {
	width: 200px;
	height: 200px;
	border-radius: 50%;
	background: linear-gradient(45deg, #df5555, #ef907a);
	top: 70px;
	left: 50%;
	margin-left: -100px;
	overflow: hidden;
	display: none;
}

#login_img .circle span {
	width: 150px;
	height: 40px;
	border-radius: 50px;
	position: absolute;
}

#login_img .circle span:nth-child(1) {
	top: 30px;
	left: -38px;
	background: #c55c59;
}

#login_img .circle span:nth-child(2) {
	bottom: 20px;
	right: -35px;
	background: #934555;
}

#login_img .star span {
	background: radial-gradient(#fff 10%, #fff 20%, rgba(72, 34, 64, 0));
	border-radius: 50%;
	box-shadow: 0 0 7px #fff;
}

#login_img .star span:nth-child(1) {
	width: 15px;
	height: 15px;
	top: 50px;
	left: 30px;
}

#login_img .star span:nth-child(2) {
	width: 10px;
	height: 10px;
	left: 360px;
	top: 80px;
}

#login_img .star span:nth-child(3) {
	width: 5px;
	height: 5px;
	top: 400px;
	left: 80px;
}

#login_img .star span:nth-child(4) {
	width: 8px;
	height: 8px;
	top: 240px;
	left: 60px;
}

#login_img .star span:nth-child(5) {
	width: 4px;
	height: 4px;
	top: 20px;
	left: 200px;
}

#login_img .star span:nth-child(6) {
	width: 4px;
	height: 4px;
	top: 460px;
	left: 410px;
}

#login_img .star span:nth-child(7) {
	width: 6px;
	height: 6px;
	top: 250px;
	left: 350px;
}

#login_img .fly_star span {
	width: 90px;
	height: 3px;
	background: -webkit-linear-gradient(left, rgba(255, 255, 255, 0.67),
		rgba(255, 255, 255, 0));
	background: -o-linear-gradient(left, rgba(255, 255, 255, 0.67),
		rgba(255, 255, 255, 0));
	background: linear-gradient(to right, rgba(255, 255, 255, 0.67),
		rgba(255, 255, 255, 0));
	transform: rotate(-45deg);
}

#login_img .fly_star span:nth-child(1) {
	top: 60px;
	left: 80px;
}

#login_img .fly_star span:nth-child(2) {
	top: 210px;
	left: 332px;
	opacity: 0.6;
}

#login_img p {
	text-align: center;
	color: #af4b55;
	font-weight: 600;
	margin-top: 365px;
	font-size: 25px;
	display: none;
}

#login_img p i {
	font-style: normal;
	margin-right: 45px;
}

#login_img p i:nth-last-child(1) {
	margin-right: 0;
}
/*提示*/
#hint {
	width: 100%;
	line-height: 70px;
	background: linear-gradient(-90deg, #9b494d, #bf5853);
	text-align: center;
	font-size: 25px;
	color: #fff;
	box-shadow: 0 0 20px #733544;
	display: none;
	opacity: 0;
	transition: .5s;
	position: absolute;
	top: 0;
	z-index: 999;
}

.copyright {
text-align: center;
    line-height: 40px;
    position: fixed;
    bottom: 20px;
    width: 100%;
    font-size: 16px;
    color: #ececec;
}

.copyright2 {
text-align: center;
    line-height: 20px;
    position: fixed;
    bottom: 60px;
    width: 100%;
    font-size: 16px;
    color: #ececec;
}

#login span.re {
    text-align: unset;
    position: unset;
    left: 0;
    margin-left: 0;
    top: 0;
    margin-top: 0;
}

.c__{
    display: flex;
    justify-content: space-between;}
.c__ input{
    margin-bottom: 0px;
    margin-top: 8px;
}

.login_main_errortip{
    margin-top: 20px;
    font-size: 14px;
    color: red;
    clear: both;
    background: #ffe2e0;
    border-radius: 4px;
    line-height: 30px;
}


#tit__ {
    font-size: 36px;
    color: white;
    font-weight: 600;
    margin: 0 auto;
    text-align: center;
        margin-top: -100px;
        position: absolute;
        width: 100%;
}

.container-fluid{
    z-index: 99999999;
    position: relative;
}

.w3lsg-bubbles {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
}
.w3lsg-bubbles li {
	position: absolute;
	list-style: none;
	display: block;
	width: 40px;
	height: 40px;
	background-color: rgba(255, 255, 255, 0.15);
	bottom: -160px;
	-webkit-animation: square 25s infinite;
	-moz-animation: square 25s infinite;
	-o-animation: square 25s infinite;
	-ms-animation: square 25s infinite;
	animation: square 25s infinite;
	-webkit-transition-timing-function: linear;
	-moz-transition-timing-function: linear;
	-o-transition-timing-function: linear;
	-ms-transition-timing-function: linear;
	transition-timing-function: linear; 
}
.w3lsg-bubbles li:nth-child(1) {
	left: 10%;
}
.w3lsg-bubbles li:nth-child(2) {
	left: 20%;
	width: 80px;
	height: 80px;
	-webkit-animation-delay: 2s;
	-moz-animation-delay: 2s;
	-o-animation-delay: 2s;
	-ms-animation-delay: 2s;
	animation-delay: 2s;
	-webkit-animation-duration: 17s;
	-moz-animation-duration: 17s;
	-o-animation-duration: 17s;
	animation-duration: 17s;
}
.w3lsg-bubbles li:nth-child(3) {
	left: 25%;
	-webkit-animation-delay: 4s;
	-moz-animation-delay: 4s;
	-o-animation-delay: 4s;
	-ms-animation-delay: 4s;
	animation-delay: 4s;
}
.w3lsg-bubbles li:nth-child(4) {
	left: 40%;
	width: 60px;
	height: 60px;
	-webkit-animation-duration: 22s;
	-moz-animation-duration: 22s;
	-o-animation-duration: 22s;
	-ms-animation-duration: 22s;
	animation-duration: 22s;
	background-color: rgba(255, 255, 255, 0.25);
}
.w3lsg-bubbles li:nth-child(5) {
	left: 70%;
}
.w3lsg-bubbles li:nth-child(6) {
	left: 80%;
	width: 120px;
	height: 120px;
	-webkit-animation-delay: 3s;
	-moz-animation-delay: 3s;
	-o-animation-delay: 3s;
	-ms-animation-delay: 3s;
	animation-delay: 3s;
	background-color: rgba(255, 255, 255, 0.2);
}
.w3lsg-bubbles li:nth-child(7) {
	left: 32%;
	width: 160px;
	height: 160px;
	-webkit-animation-delay: 7s;
	-moz-animation-delay: 7s;
	-o-animation-delay: 7s;
	-ms-animation-delay: 7s;
	animation-delay: 7s;
}
.w3lsg-bubbles li:nth-child(8) {
	left: 55%;
	width: 20px;
	height: 20px;
	-webkit-animation-delay: 15s;
	-moz-animation-delay: 15s; 
	animation-delay: 15s;
	-webkit-animation-duration: 40s;
	-moz-animation-duration: 40s;
	animation-duration: 40s;
}
.w3lsg-bubbles li:nth-child(9) {
	left: 25%;
	width: 10px;
	height: 10px;
	-webkit-animation-delay: 2s;
	animation-delay: 2s;
	-webkit-animation-duration: 40s;
	animation-duration: 40s;
	background-color: rgba(255, 255, 255, 0.3);
}
.w3lsg-bubbles li:nth-child(10) {
	left: 90%;
	width: 160px;
	height: 160px;
	-webkit-animation-delay: 11s;
	animation-delay: 11s;
}
@-webkit-keyframes square {
	0% {
		-webkit-transform: translateY(0);
		-moz-transform: translateY(0);
		-o-transform: translateY(0);
		-ms-transform: translateY(0);
		transform: translateY(0);
	}
	100% {
		-webkit-transform: translateY(-700px) rotate(600deg);
		-moz-transform: translateY(-700px) rotate(600deg); 
		-o-transform: translateY(-700px) rotate(600deg); 
		-ms-transform: translateY(-700px) rotate(600deg); 
		transform: translateY(-700px) rotate(600deg);        
	}
}
@keyframes square {
	0% {
		-webkit-transform: translateY(0);
		-moz-transform: translateY(0); 
		-o-transform: translateY(0); 
		-ms-transform: translateY(0);
		transform: translateY(0);        
	}
	100% {
		-webkit-transform: translateY(-700px) rotate(600deg);
		-moz-transform: translateY(-700px) rotate(600deg);
		-o-transform: translateY(-700px) rotate(600deg); 
		-ms-transform: translateY(-700px) rotate(600deg);
		transform: translateY(-700px) rotate(600deg);        
	}
}


.shadiao{
width: 100%;
height:100%;
background: #33638a54;
}

@media screen and (max-width:1440px ) {
#login_wrap,
#login_img,
#login{
height:400px
}

#login_wrap{
    margin-top: -200px;
    margin-right: -400px;
        width: 800px;
    }
    
 #login #status{
     margin:30px auto 60px;
 }   
 .btn{margin-top: 10px;
 }
    
    #login span {
    margin-top: -120px;
}
    .login_main_errortip{
    margin-top: 10px;
    }
    
}



/* 响应式 */
@media screen and (max-width:1000px ) {
	#login_img {
		display: none;
	}
	#login_wrap {
		width: 490px;
		margin-right: -245px;
	}
	#login {
		width: 100%;
	}
}

@media screen and (max-width:560px ) {
	#login_wrap {
		width: 330px;
		margin-right: -165px;
	}
	#login span {
		margin-left: -125px;
	}
	.form input {
		width: 250px;
	}
	.btn {
		width: 113px;
	}
}

@media screen and (max-width:345px ) {
	#login_wrap {
		width: 290px;
		margin-right: -145px;
	}
}

input:-webkit-autofill{
    box-shadow: 0 0 0 23px #fff inset !important;
}
</style>

</head>

<body id="bd__" onload="openwin();" onkeydown="keyDown(event);">
<div class="shadiao"></div>
	<div id="index_top_ts" style="display:none; background:#c9e9f7;">
		<div id="index_top_nr" style=" height:30px; padding:3px 0px 0px 15px">
			<a onclick="closeDiv()"><i class="fa fa-close"></i></a> <a
				href="http://www.baidu.com/link?url=sxjhf9fRND9kby4fDuxtFaOiJm_di92vs40spc-hrsQPb0EClk6JYca002mbSkJSpbdQq0UWU8lMRZU84XVBEuhUReK6N1Gxg9XQCd8DXLm&wd=&eqid=f4837e920000fa9100000005592273b4"
				target="_blank" style="text-decoration:none;"> <span
				style="cursor:pointer; ">温馨提醒:为了更好体验使用效果，请使用谷歌浏览器 或
					Firefox浏览器 或 360浏览器极速模式！点我可下载相关浏览器！</span></a>

		</div>
	</div>
	<!-- 登录注册 -->
	<div class="container-fluid" style="">

		<!-- <div class="logintitle">
			化工企业安全生产信息化管理平台身份认证 <span class="fr tel"><img
				src="${ctx}/static/model/images/login/gzh.gif"
				style="margin-right: 20px;" />热线电话：400-039-1179</span>
		</div> -->
		
		<div id="bg">
			<div id="hint">
				<!-- 提示框 -->
				<p>登录失败</p>
			</div>
			<div id="login_wrap">
				<div id="tit__">
					化工企业安全生产管理一体化云平台
				</div>
				
				<div id="login">
					<!-- 登录注册切换动画 -->
					<div id="status">
						<i style="top: 0">登录</i> <i style="top: 35px">注册</i> <i style="right: 5px">账号</i>
					</div>
					<span>
						<form id="loginlForm" action="${ctx}/a/login" method="post">
							<p class="form">
								<i class="fa fa-user"></i><input type="text" placeholder="请输入用户名" id="username" name="username" value="${username}">
							</p>
							<p class="form">
								<i class="fa fa-lock"></i><input type="password" placeholder="请输入密码" id="password" name="password"value="${password}">
							</p>
							<p class="form c__">
								<i class="fa fa-paypal"></i><input type="text" placeholder="请输入验证码" id="captcha" name="captcha">
								<img id="validateCode" src="${ctx}/static/images/kaptcha.jpg" alt="请点击刷新验证码"/>
							</p>
							<p class="form confirm">
								<input type="password" id="confirm-passwd" placeholder="请再次确认密码">
							</p>
							<p class="form1">
								<label class="fl re" style="line-height:32px; color:#666;font-weight: 500;display: flex;align-items: center">
									<input type="checkbox" style="margin: 0px 4px 0 0;"> 记住密码
								</label>
							</p>
							
							<input id="loginbtn" type="button" value="登 录" class="btn" style="width: 100%;">
							<!-- <input type="button" value="注册" class="btn" onclick='signin()' id="btn"> -->
						</form>
						<div class="login_main_errortip" style="color: red;clear: both;"></div>
						<!--<a href="#">Forget your password?</a>-->
					</span>
				</div>

				<div id="login_img">
					<div id="login_img_">
						<!-- 图片绘制框 -->
						<span class="circle"> <span></span> <span></span>
						</span> <span class="star"> <span></span> <span></span> <span></span>
							<span></span> <span></span> <span></span> <span></span> <span></span>
						</span> <span class="fly_star"> <span></span> <span></span>
						</span>
						<p id="title">CLOUD</p>
					</div>
				</div>
			</div>
		</div>

		<!-- <div class="box copyright2">如需开通请联系客服电话：180 3600 9888</div> -->
		<div class="box copyright">©2019 &nbsp;&nbsp;江苏中安联科信息技术有限公司</div>
	</div>
	
	<ul class="w3lsg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
		
	<c:choose>
		<c:when test="${error eq 'com.cczu.sys.system.utils.CaptchaException'}">
			<script>
				$(".login_main_errortip").html("验证码错误，请重试!");
			</script>
		</c:when>
		<c:when
			test="${error eq 'org.apache.shiro.authc.UnknownAccountException'}">
			<script>
				$(".login_main_errortip").html("用户名不存在，请重试！");
			</script>
		</c:when>
		<c:when
			test="${error eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
			<script>
				$(".login_main_errortip").html("密码错误，请重试！");
			</script>
		</c:when>
		<c:when
			test="${error eq 'org.apache.shiro.authc.DisabledAccountException'}">
			<script>
				$(".login_main_errortip").html("该用户已禁用！");
			</script>
		</c:when>
	</c:choose>
</body>
<script>
</script>
</html>
