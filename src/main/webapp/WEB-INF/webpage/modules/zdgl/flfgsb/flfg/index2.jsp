<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>制度管理法律法规库</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zdgl/flfgsb/flfg/index.js?v=2.1"></script>
</head>
<body >
<div id="zdgl_flfg_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
        <input type="hidden" name="type" value="${type}" />
		<input type="text" id="zdgl_flfg_m2" name="zdgl_flfg_m2" style="height: 30px;" class="easyui-textbox" data-options="prompt: '法律法规名称'"/>
		<input type="text" id="zdgl_flfg_m3" name="zdgl_flfg_m3" style="height: 30px;" class="easyui-textbox" data-options="prompt: '颁布单位'"/>
		<input id="zdgl_flfg_m1_1" name="zdgl_flfg_m1_1" class="easyui-combobox" style="height: 30px;" data-options="prompt: '大类别',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'8',text:'国际公约'},
								        {value:'7',text:'其他'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sekb:flfg:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:flfg:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:flfg:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:flfg:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sekb:flfg:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sekb:flfg:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/zdgl/flfg/exinjump','${ctx}/zdgl/flfg/exin?type=2','${ctx}/static/templates/法律法规标准库导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="zdgl_flfg_dg"></table> 
<script type="text/javascript">
var qyid = '${qyid}';
var type = '${type}';
</script>
</body>
</html>