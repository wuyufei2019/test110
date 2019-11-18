<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故应急决策 -应急调度与处置 </title>
	<meta name="decorator" content="default"/>
 
	<script type="text/javascript" src="${ctx}/static/model/js/ead/jclsjl/index.js?v=1.1"></script>

</head>
<body>
<!-- 工具栏 -->
<div id="jclsjl_index_tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		 <div class="pull-left">
			<form id="jclsjl_searchFrom" style="margin-bottom: 8px;" class="form-inline">
			<input type="text" name="check_name" class="easyui-textbox" style="width:150px;height: 30px;" data-options="prompt: '企业名称'"/>
			</form>
		</div>
		<div class="pull-left">
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="cx()" title="查询"><i class="fa fa-search"></i> 查询</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="clearf()" title="清空"><i class="fa fa-refresh"></i> 清空</button> 
		    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
		</div>
	</div>
	</div> 
					
 

</div>

 
<table id="jclsjl_index_dg"></table> 
 

</body>
</html>