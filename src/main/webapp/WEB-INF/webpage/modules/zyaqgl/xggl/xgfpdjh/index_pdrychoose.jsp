<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>评定人员选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgfpdjh/index_pdrychoose.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqgl_pdrychoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="filter_LIKES_name"  class="easyui-textbox" style="height: 30px" data-options="prompt: '评定人员名称'" />
    </div>
	</form>
	<div class="pull-left" style="margin-top:-45px;margin-left:180px">
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchpdry()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
   </div>
</div>
<table id="aqgl_pdrychoose_dg"></table> 
</body>
</html>