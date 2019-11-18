<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查方案</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcfa/index.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqzf_jcfa_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	    <input id="aqzf_jcfa_year" name="aqzf_jcfa_year" style="height: 30px;"/>
	    <input id="aqzf_jcfa_month" name="aqzf_jcfa_month" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '月份',editable:true,data: [
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
		<input type="text" name="aqzf_jcfa_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>				        	
		<input type="text" name="aqzf_jcfa_cz" class="easyui-combobox" style="height: 30px;" data-options="editable:false ,panelHeight:'auto', prompt: '操作类型', 
																												data:[{value:'0',text:'未添加检查'},{value:'1',text:'已添加检查'}]"
		/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqzf:jcfa:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 	 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcfa:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcfa:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcfa:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出现场检查方案"><i class="fa fa-file-word-o"></i> 导出现场检查方案</button> 
        	</shiro:hasPermission>        	
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>
<table id="aqzf_jcfa_dg"></table> 
<script type="text/javascript">
//年份下拉框初始化
$("#aqzf_jcfa_year").combobox({ 
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
	
$("#aqzf_jcfa_year").combobox("loadData", data);//下拉框加载数据


</script>
</body>
</html>