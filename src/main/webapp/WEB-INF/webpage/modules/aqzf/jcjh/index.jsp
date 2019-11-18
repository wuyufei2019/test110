<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcjh/index.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqzf_jcjh_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	    <input id="aqzf_jcjh_year" name="aqzf_jcjh_year" style="height: 30px;"/>
	    <input id="aqzf_jcjh_month" name="aqzf_jcjh_month" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '月份',editable:true,data: [
						         {value:'1月',text:'1月'},
						         {value:'2月',text:'2月'}, 
						         {value:'3月',text:'3月'}, 
						         {value:'4月',text:'4月'}, 
						         {value:'5月',text:'5月'}, 
						         {value:'6月',text:'6月'},
						         {value:'7月',text:'7月'},
						         {value:'8月',text:'8月'}, 
						         {value:'9月',text:'9月'}, 
						         {value:'10月',text:'10月'}, 
						         {value:'11月',text:'11月'}, 
						         {value:'12月',text:'12月'}
						        	]  "/>
		<input type="text" id="aqzf_jcjh_M3" name="aqzf_jcjh_M3" class="easyui-combotree" style="height: 30px;" value="" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '属地'" />
		<input type="text" id="aqzf_jcjh_M4" name="aqzf_jcjh_M4" class="easyui-combobox" style="height: 30px;" value="" data-options="editable:false,prompt: '行业类型',panelHeight:'auto' ,data:[{value:'1',text:'工贸'},{value:'2',text:'化工'}]" />
		<input type="text" id="aqzf_jcjh_M5" name="aqzf_jcjh_M5" class="easyui-combobox" style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '检查科室',editable:true,valueField:'text',textField:'text',url:'${ctx }/system/department/deptjson'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqzf:jcjh:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjh:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjh:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjh:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:jcjh:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:jcjh:exin">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileimport()" title="导入"><i class="fa fa-external-link-square"></i> 导入</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		
		
			</div>
	</div>
	</div> 
	   
</div>

<table id="aqzf_jcjh_dg"></table> 


<script type="text/javascript">
//年份下拉框初始化
$("#aqzf_jcjh_year").combobox({ 
	prompt: '年份',
	editable:'true',
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
	
$("#aqzf_jcjh_year").combobox("loadData", data);//下拉框加载数据


</script>
</body>
</html>