<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>风险监管</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxjg/index.js?v=2.0"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_fxjg_tb" style="padding:5px;height:auto" class="datagrid-toolbar">
<div class="row">
    <div class="col-sm-12">
	<div class="pull-left">
	<form id="searchFrom" action="" style="margin: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="fxgk_fxjg_qyname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '企业名称'" />
		<!--
		<input type="text" name="xiangzheng" class="easyui-combobox" style="height: 30px;" data-options="prompt: '镇区名称',
		data: [{value:'靖城街道',text:'靖城街道'},{value:'1',text:'有风险点'},{value:'新桥镇',text:'新桥镇'},{value:'东兴镇',text:'东兴镇'}
		,{value:'生祠镇',text:'生祠镇'}
		,{value:'马桥镇',text:'马桥镇'}
		,{value:'季市镇',text:'季市镇'}
		,{value:'孤山镇',text:'孤山镇'}
		,{value:'西来镇',text:'西来镇'}
		,{value:'斜桥镇',text:'斜桥镇'}
		,{value:'滨江新区',text:'滨江新区'}
		,{value:'开发区管委会',text:'开发区管委会'}
		,{value:'城南园区',text:'城南园区'}
		,{value:'城北园区',text:'城北园区'}
		,{value:'江阴靖江工业园区',text:'江阴靖江工业园区'}
		,{value:'江阴靖江工业园区办事处',text:'江阴靖江工业园区办事处'}]" />
		  -->
		<input type="text" name="fxgk_fxjg_hasfx" class="easyui-combobox" style="height: 30px;" data-options="prompt: '有无风险点'
		,panelHeight:'auto',data: [{value:'0',text:'无风险点'},{value:'1',text:'有风险点'}]" />
		<input type="text" id="fxgk_fxjg_fxdj"name="fxgk_fxjg_fxdj" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险等级'
		,panelHeight:'auto',data: [{value:'1',text:'红'},{value:'2',text:'橙'},{value:'3',text:'黄'},{value:'4',text:'蓝'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>
	</div>
		<div class="pull-left" style="margin: 8px;">
		</div>
		</div>
</div>
</div>

<table id="fxgk_fxjg_dg"></table> 
<div id="select_type" style="display:none;height:350px"  >
 	<table id="areadata"></table> 
</div>
<script type="text/javascript">
   var f='${sys}';
   var fxdj='${fxdj}';
</script> 
</body>
</html>