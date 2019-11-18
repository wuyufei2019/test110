<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqpx/kjkgl/index_kjchoose.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqpx_kjgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="aqpx_kjgl_cx_m1" name="aqpx_kjgl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课件名称'" />		        	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

</div>


<table id="aqpx_kjgl_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>