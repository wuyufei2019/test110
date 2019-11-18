<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检监督与考核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/xjjd/index.js?v=6"></script>
</head>
<body >
	<div id="yhpc_xjjd_tb1" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
		        	<form id="yhpc_xjjd_searchFrom1" style="margin-bottom: 8px;" action="" class="form-inline">
		        	    <input type="text" id="yhpc_xjsj_start" name="yhpc_xjsj_start" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '开始时间'" />
		        	    <input type="text" id="yhpc_xjsj_end" name="yhpc_xjsj_end" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '结束时间'" />
		       	        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search1()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear1()" ><i class="fa fa-refresh"></i> 全部</span>
					</form>
				</div>
				<div class="pull-right">
				</div>
			</div>
		</div>
      	</div>   
	<table id="yhpc_xjjd_dg1"></table> 
<script type="text/javascript">
</script>
</body>
</html>