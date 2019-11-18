<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户使用手册</title>
	<meta name="decorator" content="default"/>

  </head>
  
  <body>
    <div style="margin-top: 20px;width: 100%;text-align: center;">
    <shiro:hasAnyRoles name="companyadmin,company">
    <a class="btn btn-info btn-lg" onclick="window.open('${ctxStatic}/model/file/滨海沿海工业园智慧安监与事故应急辅助决策系统软件使用说明(企业版).docx')">下载使用手册</a>
    </shiro:hasAnyRoles>
    
    
    <shiro:hasAnyRoles name="admin,superadmin,ajcountyadmin,ajcounty">
    <a class="btn btn-info btn-lg" onclick="window.open('${ctxStatic}/model/file/滨海沿海工业园智慧安监与事故应急辅助决策系统软件使用说明(安监版).docx')">下载使用手册</a>
    </shiro:hasAnyRoles>
    
    
    
    </div>
   
  </body>
</html>
