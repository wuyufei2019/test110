<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<html>
<head>
	<title>靖江市安全生产监管一体化平台</title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
	<script src="${ctxStatic}/layer-v2.0/layer/laydate/laydate.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css?v=1.5">
</head>
 <%
 	String name="靖江市";
 	if(session.getAttribute("logotitle")!=null)
 		name=(String)session.getAttribute("logotitle");
 %>
<body id="win" class="bgblue0" style="height: 100%;">
<div class="container-fluid clearpd">
    <header class="ofh bgblue navbar-fixed-top" id="header"  >
      <div class="mbox">
        <div class="pull-left logo"><%=name %>安全生产监管一体化平台</div>
        <div class="user pull-right">
          <i class="fa fa-home"></i>
          <a href="${ctx}/a">首页</a>
               &nbsp;&nbsp;&nbsp;
          <i class="fa fa-windows"></i>
          <a href="${ctx}/a/mainmenu" target="frame">系统</a>
               &nbsp;&nbsp;&nbsp;
          <i class="fa fa-sign-out"></i>
          <a href="javascript:logout()" id="btnExit" >退出</a>
          &nbsp;&nbsp;&nbsp;
           <shiro:principal property="name"/>，
          <span>您好！</span>
           &nbsp;&nbsp;&nbsp;
          <shiro:hasAnyRoles name="companyadmin,company">
          	<a target="_blank" href="${ctxStatic}/model/file/靖江市安全监管一体化平台（企业端）使用说明书.pdf">帮助文档</a>
          </shiro:hasAnyRoles>
          <shiro:hasAnyRoles name="ajcountyadmin,ajcounty,ajtownadmin,ajtown">
          	<a target="_blank">帮助文档</a>
          </shiro:hasAnyRoles>
        </div>
        
      </div>
    </header>

	   <iframe src="${ctx}/a/home" width="100%" frameborder="0" id="frame" name="frame"></iframe>
	    
  </div>
  <footer class="ofh bgblue navbar-fixed-bottom" id="footer">
    <div class="copyright">技术支持：江苏有线靖江分公司&nbsp;&nbsp; 江苏中安联科信息技术有限公司</div>
  </footer>

<script>
$(document).ready(function(){
	change();
});
$(window).resize(function(){
	change();
});
function change(){
	var h=$(window).height();
    var w=$(window).width();
    
    var headerheigh = $("#header").height();
    var footerheigh = $("#footer").height();
   
    $("#frame").height(h-headerheigh-footerheigh); 
}
 

function logout(){
	 if(confirm("您确定要退出当前程序并关闭该网页？")){
		 
		 window.onbeforeunload = null;
		 location.href='${ctx}/a/logout';
	 }
}
 
</script>
    
</body>
</html>
