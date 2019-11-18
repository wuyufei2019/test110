<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急预案编制与演练</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzr/fzr/yjya/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ztzr_yjya_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input id="ztzr_yjya__nd" name="ztzr_yjya__nd" class="easyui-textbox" style="height: 30px;" data-options="prompt: '年度 ', editable:false "/>
		<input type="text" id="ztzr_yjya_cx_qyname" name="ztzr_yjya_cx_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
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


<table id="ztzr_yjya_dg"></table> 

<script type="text/javascript">
//年份下拉框初始化
$("#ztzr_yjya__nd").combobox({ 
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
	
$("#ztzr_yjya__nd").combobox("loadData", data);//下拉框加载数据
</script>
</body>
</html>