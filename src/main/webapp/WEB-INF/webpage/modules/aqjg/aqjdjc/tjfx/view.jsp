<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.3"></script>
</head>
<body>
	<div class="easyui-tabs" id="qycount" style="height:100%">
		<div   style="padding:10px">
			<table id="aqjg_tjfx_qy_dg"></table> 
			<input  id='year' type='hidden' value='${year}' />
			<input  id='month' type='hidden' value='${month}' />
		</div>
		<div   style="padding:10px">
		<table id="aqjg_tjfx_fin_dg"></table> 
		</div>
		<div    style="padding:10px">
			<table id="aqjg_tjfx_unfin_dg"></table> 
		</div>
	</div>
</body>
</html>