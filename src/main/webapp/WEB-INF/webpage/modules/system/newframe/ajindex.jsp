<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全生产监管一体化平台</title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css?v=1.5">
</head>
  
<body class="bgblue0">

       
	   
	   <div  class="mbox " >
	   
	   <div style="width:1000px;margin: 30px auto;">
	   
	  
	   <div class="menucontent" style="background: url('${ctxStatic}/model/main/images/sy_10.png');"  >
         <%--  <a href="#" class="col-lg-3">
            <img src="${ctxStatic}/model/main/images/yzt.png" onclick="openwin(0)">
            <div>一张图</div>
          </a> --%>
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/file.png" onclick="openwin(108)">
            <div>安全文件发布</div>
          </a>
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/knowledge.png" onclick="openwin(107)">
            <div>专家知识库</div>
          </a>
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/set.png" onclick="openwin(1)">
            <div>系统设置</div>
          </a>  
	   </div>
	  
	  
	  <div class="menucontent" style="background: url('${ctxStatic}/model/main/images/sy_20.png'); ">
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/yqyd.png" onclick="openwin(39)">
            <div>一企一档</div>
          </a>        
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/alert.png" onclick="openwin(3155)">
            <div>风险管控</div>
          </a>        
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/chem.png" onclick="openwin(81)">
            <div>在线监控</div>
          </a>        
	  </div>
	   
	   
	   
	   
	   <div class="menucontent" style="background: url('${ctxStatic}/model/main/images/sy_30.png'); ">
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/yhpc.png" onclick="openwin(3158)">
            <div>隐患排查</div>
          </a>        
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/wgh.png" onclick="openwin(3159)">
            <div>网格化管理</div>
          </a>        
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/move.png" onclick="openwin(3322)">
            <div>安全生产检查</div>
          </a>        
        
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/safe.png" onclick="openwin(59)">
            <div>安全监管</div>
          </a>       
	   </div>
	   
	   
	   <div class="menucontent" style="background: url('${ctxStatic}/model/main/images/sy_40.png'); ">
          <a href="#" class="col-lg-3 col-md-3 col-sm-3">
            <img src="${ctxStatic}/model/main/images/emergency.png" onclick="openwin(2903)">
            <div>事故应急</div>
          </a>      
	   </div>
	   
	  
	    </div>
 
    </div>


<script>

 
function openwin(id){
	
	parent.location.href='${ctx}/system/permission/subindex?id='+id;	
	
}
</script>
    
</body>
</html>
