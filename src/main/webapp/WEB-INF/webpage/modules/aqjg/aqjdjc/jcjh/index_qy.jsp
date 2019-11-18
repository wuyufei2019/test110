<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/jcjh/index_qy.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqjg_jcjh_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="aqjg_jcjh_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '检查计划名称'" />
		<input id="aqjg_jcjh_year" name="aqjg_jcjh_year" style="height: 30px;"/>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqjd:jcjh:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqjg_jcjh_dg"></table> 
<script type="text/javascript">
//年份下拉框初始化
$("#aqjg_jcjh_year").combobox({ 
	prompt: '年份',
	editable:'true',
	valueField:'year',    
	textField:'year',  
	panelHeight:'auto'
});
var data = [];
var thisYear;
var startYear=new Date().getUTCFullYear()+1;

for(var i=0;i<5;i++){
	thisYear=startYear-i;
	data.push({"year":thisYear});
}
	
$("#aqjg_jcjh_year").combobox("loadData", data);//下拉框加载数据


</script>
</body>
</html>