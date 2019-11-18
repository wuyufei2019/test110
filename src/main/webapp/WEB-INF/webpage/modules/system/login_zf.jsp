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
<title>城市安全与应急管理一体化云平台身份认证</title>
<meta name="renderer" content="webkit">
<link rel="shortcut icon" href="${ctx}/static/model/images/favicon_zf.ico" />
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
#index_top_nr a:HOVER
{ 
color:#FFFFFF; 
text-decoration:underline; 
} 

</style>

</head>

<body onload="openwin();" onkeydown="keyDown(event);" style="min-width: 1200px;">
    <div id="index_top_ts" style="display:none; background:#c9e9f7;">
	    <div id="index_top_nr" style=" height:30px; padding:3px 0px 0px 15px">
	    	<a onclick="closeDiv()"><i class="fa fa-close"></i></a>
	    	<a href="http://www.baidu.com/link?url=sxjhf9fRND9kby4fDuxtFaOiJm_di92vs40spc-hrsQPb0EClk6JYca002mbSkJSpbdQq0UWU8lMRZU84XVBEuhUReK6N1Gxg9XQCd8DXLm&wd=&eqid=f4837e920000fa9100000005592273b4" target="_blank" style="text-decoration:none;">
	    	<span style="cursor:pointer; ">温馨提醒:为了更好体验使用效果，请使用谷歌浏览器  或  Firefox浏览器 或 360浏览器极速模式！点我可下载相关浏览器！</span></a>
	    	
	    </div>
	</div>
    <!-- 登录注册 -->
	<div class="container-fluid" style="">
    
    <div class="logintitle" style="background: url(${ctx}/static/model/images/login/logo.png) left center no-repeat;">
		城市安全与应急管理一体化云平台身份认证
		<span class="fr tel" style="margin-right: 0px;display: flex;align-items: center">服务电话：
			<div style="margin-left: 10px;display: flex;flex-direction: column;padding: 0 20px;background: rgb(239, 239, 239);height:85px">
				<span style="margin-top: 15px;font-size: 16px;line-height:25px">180 3600 9888</span>
				<span style="font-size: 16px;line-height:25px">159 9603 7523</span>
			</div>
		</span>
    </div>
    <div class="bgbox" id="bgbox" style="clear: both;background-image:url('${ctx}/static/model/images/login/loginbg.jpg');">
      
			<div class="box">
    		<div class="loginbox">
        		<h4><i class="icon-user"></i>&nbsp;&nbsp;用户登录</h4>
           		<div class="login_from">
            	<form id="loginlForm" action="${ctx}/a/login" method="post">
                	<div class="input-group">
  						<span class="input-group-addon">用户名</span>
  						<input type="text" id="username" name="username" class="form-control" placeholder="Username" value="${username}">
					</div>
                    <div class="input-group">
  						<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
  						<input type="password" id="password" name="password" class="form-control" placeholder="Password" value="${password}">
					</div>
                    <div class="input-group">
  						<span class="input-group-addon">验证码</span>
  						<input type="text" class="form-control fl" id="captcha" name="captcha" style="width:120px;" placeholder="Verification"/>
                        <img id="validateCode" src="${ctx}/static/images/kaptcha.jpg" alt="请点击刷新验证码" />
					</div>
					
            <div class="b_input">
						<span class="fl" style="line-height:32px; color:#666;">
            <input type="checkbox">
                        记住密码
            </span>
            <button type="submit" class="btn btn-primary fr" id="loginbtn">登 录</button>
					</div>
                </form>
                <div class="login_main_errortip" style="color: red;clear: both;"></div>
            	</div>
        	</div>
        	</div>
    </div>
    <div class="box copyright">
        copyright©2018： 技术支持&nbsp;&nbsp;&nbsp;江苏中安联科信息技术有限公司
    </div>
	</div>
     
    <c:choose>
		<c:when test="${error eq 'com.cczu.sys.system.utils.CaptchaException'}">
			<script>
				$(".login_main_errortip").html("验证码错误，请重试!");
			</script>
		</c:when>
		<c:when test="${error eq 'org.apache.shiro.authc.UnknownAccountException'}">
			<script>
				$(".login_main_errortip").html("用户名不存在，请重试！");
			</script>
		</c:when>
		<c:when test="${error eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
			<script>
				$(".login_main_errortip").html("密码错误，请重试！");
			</script>
		</c:when>
 		<c:when test="${error eq 'org.apache.shiro.authc.DisabledAccountException'}">
			<script>
                $(".login_main_errortip").html("该用户已禁用！");
			</script>
		</c:when>
	</c:choose>
</body>
  <script>

  </script>
</html>
