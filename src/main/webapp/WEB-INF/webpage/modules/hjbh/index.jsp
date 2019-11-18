<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>出入库管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <div id="tabs" class="easyui-tabs" fit="true">
      <div id="rk" title="入库管理" style="height:100%;" href="${ctx }/hjbh/rkgl/index2" data-options="">
      </div>
      <div id="ck" title="出库管理" style="height:100%;" href="${ctx }/hjbh/ckgl/index" data-options="">
      </div>
   </div>
</body>
</html>