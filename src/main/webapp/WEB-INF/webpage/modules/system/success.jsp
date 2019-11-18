]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css">
</head>
  
<body id="win" class="bgblue0">
<div class="container-fluid clearpd">
    <header class="ofh bgblue navbar-fixed-top">
      <div class="mbox">
        <div class="pull-left logo">斜桥镇安全生产监管一体化平台</div>
        <div class="user pull-right">
          <i class="fa fa-home"></i>
          <a href="javascript:logout()" id="btnExit" >退出系统</a>
          &nbsp;&nbsp;&nbsp;
           <shiro:principal property="name"/>，
          <span>您好！</span>
           &nbsp;&nbsp;&nbsp;
          <a>帮助文档</a>
        </div>
      </div>
    </header>
	  <div  class="ofh content" id="indexbox" >
      <ul class="mbox clearpd iconbox" id="menuli">
         <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/yqyd.png">
            <div>一企一档</div>
          </a>
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/alert.png">
            <div>风险管控</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/yhpc.png">
            <div>隐患排查</div>
          </a>        
        </li>
          <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/wgh.png">
            <div>网格化监管</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/emergency.png">
            <div>事故应急</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/chem.png">
            <div>化工监管</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/safe.png">
            <div>安全监管</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/work.png">
            <div>科室工作</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/knowledge.png">
            <div>专家知识库</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/move.png">
            <div>安全生产执法</div>
          </a>        
        </li>
        <li class="col-lg-2 col-xs-4">
	        <a href="${ctx}/a">
	        	<img alt="文件发布" src="${ctxStatic}/model/main/images/file.png">
	        	<div>文件发布</div>
	        </a>
        </li>
        <li class="col-lg-2 col-xs-4">
	        <a href="${ctx}/a">
	        	<img alt="系统监控" src="${ctxStatic}/model/main/images/jk.png">
	        	<div>系统监控</div>
	        </a>
        </li>
        <li class="col-lg-2 col-xs-4">
          <a href="${ctx}/a">
            <img src="${ctxStatic}/model/main/images/set.png">
            <div>系统管理</div>
          </a>        
        </li> 
      </ul>
    </div>
  </div>
  <footer class="ofh bgblue navbar-fixed-bottom">
    <div class="copyright">技术支持：江苏中安联科信息技术有限公司</div>
  </footer>

<script>

$(function(){
	
	
});


function logout(){
	 if(confirm("您确定要退出当前程序并关闭该网页？")){
		 
		 window.onbeforeunload = null;
		 location.href='${ctx}/a/logout';
	 }
}


function openwin(id){
	
	window.location.href='${ctx}/system/permission/subpmenuList?id='+id;	
	
	//window.mainpage.mainTabs.addModule('','${ctx}/system/permission/subpmenuList?id='+id,'');
	
}


</script>
    
</body>
</html>
