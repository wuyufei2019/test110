<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>培训计划信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzr/fzr/pxjh/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqpx_pxjhxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="aqpx_pxjhxx_cx_qyname" name="aqpx_pxjhxx_cx_qyname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '企业名称'" />
		<input type="text" id="aqpx_pxjhxx_cx_m1" name="aqpx_pxjhxx_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '计划名称'" />       	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
	</div>
	</div>
	   
</div>


<table id="aqpx_pxjhxx_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>