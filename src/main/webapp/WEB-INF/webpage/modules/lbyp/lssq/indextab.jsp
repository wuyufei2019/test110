<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>临时申请管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <div id="tabs" class="easyui-tabs" fit="true">
         <div id="sqgl" title="申请管理" style="height:100%;" href="${ctx }/lbyp/lssq/applyindex?qyid=${qyid}" data-options=""></div>
         <div id="shgl" title="审核管理" style="height:100%;" href="${ctx }/lbyp/lssq/reviewindex?qyid=${qyid}" data-options=""></div>
   </div>
</body>
</html>