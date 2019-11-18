<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理备品备件</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/bpbj/index.js?v=1.1"></script>
</head>
<body >
<div id="sbssgl_bpbj_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" 
				data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称', 
					onSelect: function(row){
					  	  $('#m23').combobox('setValue', '');
						  $('#m23').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
					  }"/>
		</c:if>
		<shiro:hasRole name="companyadmin">
			<input name="m23" id="m23" class="easyui-combobox" style="height: 30px;" data-options="prompt: '使用部门',editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/system/department/deptjson/'" />
		</shiro:hasRole>
		<input name="m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备编号'" />
		<input name="m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备名称'" />
		<input name="m26" class="easyui-combobox" style="height: 30px;" 
			data-options="editable:false,prompt: '是否有备品备件',panelHeight: 'auto',
				data: [
					{value: '0', text: '否'},
					{value: '1', text: '是'}
				]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
		<%-- <shiro:hasPermission name="sbssgl:bpbj:export">
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="fileexport()" ><i class="fa fa-external-link"></i> 导出备品备件清单</span>
		</shiro:hasPermission> --%>
    </div>
	</form>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<%-- <shiro:hasPermission name="sbssgl:bpbj:add">
			       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:bpbj:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:bpbj:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>--%>
				<shiro:hasPermission name="sbssgl:bpbj:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission> 
	        	<%-- <shiro:hasPermission name="sbssgl:bpbj:exportword">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出备品备件清单"><i class="fa fa-external-link"></i> 导出备品备件清单</button>
	        	</shiro:hasPermission> --%>
		        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			
				</div>
			<div class="pull-right">
			</div>
		</div>
	</div> 	   
</div>
<table id="sbssgl_bpbj_dg"></table> 

<script>
	var role = '0';
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
</script>
<shiro:hasPermission name="sbssgl:bpbj:add">
	<script>role = '1';</script>
</shiro:hasPermission>
</body>
</html>