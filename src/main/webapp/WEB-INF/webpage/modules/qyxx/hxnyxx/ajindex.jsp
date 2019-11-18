<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>化学能源信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/hxnyxx/ajindex.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_hxnyxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="bis_hxnyxx_cx_qyname" name="bis_hxnyxx_cx_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
    	<input type="text" id="bis_hxnyxx_cx_m1" name="bis_hxnyxx_cx_m1" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '能源名称',editable:false,data: [
								        {value:'液氨',text:'液氨'},
								        {value:'天然气',text:'天然气'},
								        {value:'液化气',text:'液化气'},
								        {value:'导热油',text:'导热油'},
								        {value:'煤',text:'煤'},
								        {value:'乙炔',text:'乙炔'},
								        {value:'生物质燃料',text:'生物质燃料'},
								        {value:'其他',text:'其他'}]"/>
	<input type="text" id="bis_hxnyxx_cx_m5" name="bis_hxnyxx_cx_m5" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '是否进行安全评价',editable:false,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'}]"/>
	<input type="text" id="bis_hxnyxx_cx_m6" name="bis_hxnyxx_cx_m6" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '是否设置监控设施',editable:false,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'}]"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>	
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:hxnyxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:hxnyxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:hxnyxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:hxnyxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:hxnyxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:hxnyxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/hxnyxx/exinjump','${ctx}/bis/hxnyxx/exin','${ctx}/static/templates/化学能源信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_hxnyxx_dg"></table> 

</body>
</html>