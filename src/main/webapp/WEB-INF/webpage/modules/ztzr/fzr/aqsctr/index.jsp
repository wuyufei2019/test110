<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>年度安全生产投入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzr/fzr/aqsctr/index.js?v=1.2"></script>
	<script type="text/javascript">
		var usertype = '${usertype}';
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="ztzr_sctr_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input id="ztzr_sctr_m1" name="ztzr_sctr_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '年度 ', editable:false "/>
		<input type="text"  name="qyname" style="height:30px" class="easyui-textbox"data-options="width:200,prompt: '企业名称'" />
		<input type="text" id="aqzf_fytq_m3" name="aqzf_fytq_m3" class="easyui-combobox" style="height: 30px;" value="" data-options="panelHeight:'100' ,prompt: '行业类型',editable:true,valueField:'text',textField:'text',url:'${ctx }/aqsctr/fytq/lxlist'" />    		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
</div>
<table id="ztzr_sctr_dg"></table> 
<script type="text/javascript">
var qyid = '${qyid}';
//年份下拉框初始化
$("#ztzr_sctr_m1").combobox({ 
	valueField:'year',    
	textField:'year',  
	panelHeight:'auto'
});

var data = [];
var thisYear;
var startYear=new Date().getUTCFullYear()+2;

for(var i=0;i<6;i++){
	thisYear=startYear-i;
	data.push({"year":thisYear});
}
	
$("#ztzr_sctr_m1").combobox("loadData", data);//下拉框加载数据
</script>
</body>
</html>