<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方违规统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgfwg/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_xgfwg_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="zyaqgl_xgfwg_xgfname" name="zyaqgl_xgfwg_xgfname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '评定单位 '"/>
		<input id="zyaqgl_xgfwg_year" name="zyaqgl_xgfwg_year" style="height: 30px;"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
        	<shiro:hasPermission name="zyaqgl:xgfwg:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
		</div>
	</div>
	</div> 
	   
</div>
<table id="zyaqgl_xgfwg_dg"></table> 
<script type="text/javascript">
	var qyid= '${qyid}';
//年份下拉框初始化
$("#zyaqgl_xgfwg_year").combobox({ 
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
	
$("#zyaqgl_xgfwg_year").combobox("loadData", data);//下拉框加载数据


</script>
</body>
</html>