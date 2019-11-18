<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格统计分析</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.a{
	   width:18%;
	   height:24%;
	   float: left;
	   margin-top: 12%;
	}
	</style>
</head>
<body>
<div class="a" style="margin-left: 19%;margin-right: 4%;" onclick="window.location.href = ctx+'/wghgl/tjfx/xjdindex';">
<img src="${ctx}/static/model/images/tjfx3.png" width="100%" height="100%">
</div>
<div class="a" style="margin-right: 4%;" onclick="window.location.href = ctx+'/wghgl/tjfx/xjryindex';">
<img src="${ctx}/static/model/images/tjfx1.png" width="100%" height="100%">
</div>
<div class="a" onclick="window.location.href = ctx+'/wghgl/tjfx/bcindex';">
<img src="${ctx}/static/model/images/tjfx2.png" width="100%" height="100%">
</div>
</body>
</html>