<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/yhpc/aqkf/hisindex.js?v=1.0"></script>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;" >
        		<input type="hidden" name="id1" value="${ID1}" >
        	</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
			        	<shiro:hasPermission name="yhpc:aqkf:export">
						     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
        </div>
 </div>
<table id="dg"></table> 
<script type="text/javascript">
var ID1='${ID1}';
</script>
</body>
</html>