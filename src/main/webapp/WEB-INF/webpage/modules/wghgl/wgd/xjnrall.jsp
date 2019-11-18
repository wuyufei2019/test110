<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/wgd/xjnrall.js?v=1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_xjnrall_tb" style="padding:5px;height:auto">
	<!-- <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="fxgk_xjnrall_checktitle" name="fxgk_xjnrall_checktitle" class="easyui-textbox" style="height: 30px;" value="" data-options="prompt:'检查项目'" />
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</button>
    </div>
	</form> -->

	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
	    		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="绑定"><i class="fa fa-plus"></i> 绑定</button>
			</div>
		</div>
	</div> 
	   
</div>
<table id="fxgk_xjnrall_dg"></table> 
<script type="text/javascript">
var id1 = '${id1}'; 
var qyid= '${qyid}';
</script>
</body>
</html>