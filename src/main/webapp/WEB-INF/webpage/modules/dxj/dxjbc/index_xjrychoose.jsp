<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查人员选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dxj/dxjbc/index_xjrychoose.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="dxj_xjrychoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="filter_LIKES_name"  class="easyui-textbox" style="height: 30px" data-options="prompt: '检查人员名称'" />
    </div>
	</form>
	<div class="pull-left" style="margin-top:-37px;margin-left:180px">
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchxjry()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
   </div>
</div>
<table id="dxj_xjrychoose_dg"></table> 
</body>
</html>