<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
 <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
    <div title="检查类型" style="padding:0px;display:none;">
    	<iframe frameborder="0" src="${ctx}/yhpc/rcjc/jclx" style="width: 100%;height: 95%;"></iframe>
    </div>
    <div title="缺失类型" data-options=" " style="overflow:auto;padding:0px;display:none;">
		<iframe frameborder="0" src="${ctx}/yhpc/rcjc/qslx" style="width: 100%;height: 95%;"></iframe>
    </div>
</div>
	
<script type="text/javascript">

</script>
</body>
</html>