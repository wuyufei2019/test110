<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检内容记录表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/yhpcd/xjnr.js?v=2.1"></script>
</head>
<body >
<div id="fxgk_xjnr_tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<span id="divper">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
	   		</span>
        </div>
	</div>
	</div> 
</div>

<table id="fxgk_xjnr_dg"></table> 
<script type="text/javascript">
var id1 = ${id1};
var qyid = ${qyid};
</script>
</body>
</html>
