<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/jcjh/view.js?v=1.1"></script>
</head>
<body>
	<div class="easyui-tabs"  scrolling="auto" data-options="fit:true,tabWidth:170" id="qycount"  style="width: 400px">
		<div   >
			<table id="aqjg_jcjh_qy_dg"></table> 
			<input  id='ID' type='hidden' value='${id}' />
		</div>
		 <div>
		<table id="aqjg_jcjh_unfin_dg"></table> 
		</div> 
		 <div>
		<table id="aqjg_jcjh_cjfin_dg"></table> 
		</div>
		<div>
			<table id="aqjg_jcjh_fjfin_dg"></table> 
		</div>
	</div>
</body>
</html>