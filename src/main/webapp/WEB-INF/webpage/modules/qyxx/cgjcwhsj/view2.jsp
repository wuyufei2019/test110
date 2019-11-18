<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>更多报警信息</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
	<div class="easyui-tabs" fit="true">
		<div title="储罐报警信息" style="height:100%;" data-options="">
			<iframe src="${ctx}/zxjkyj/bjxx/cgbjindex" style="width: 100%;height: 100%;"></iframe>
		</div>

		<div title="气体报警信息" style="height:100%;" data-options="">
            <iframe src="${ctx}/zxjkyj/bjxx/ndbjindex" style="width: 100%;height: 100%;"></iframe>
		</div>

		<div title="高危工艺报警信息" style="height:100%;" data-options="">
            <iframe src="${ctx}/zxjkyj/bjxx/gwgybjindex" style="width: 100%;height: 100%;"></iframe>
		</div>
	</div>
</body>
</html>