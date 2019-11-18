<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关单位信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgdw/index.js?v=1.6"></script>
	<script type="text/javascript">
		var usertype = '${usertype}';
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_xgdw_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${usertype != '1'}">
			<input type="text" id="zyaqgl_xgdw_qy_name" name="zyaqgl_xgdw_qy_name" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="aqzf_xgdw_m1" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'auto', editable:false ,prompt: '类别',valueField:'text',textField:'text', data:[{value:'承包商',text:'承包商'},{value:'供应商',text:'供应商'}] " />
		<input name="aqzf_xgdw_m3" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'120',editable:false ,prompt: '行业类型', valueField:'text',textField:'text',url:'${ctx }/tcode/dict/hylx'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zyaqgl:xgdw:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
		</span>
        	<shiro:hasPermission name="zyaqgl:xgdw:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	
        	<shiro:hasPermission name="zyaqgl:xgdw:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	
			<shiro:hasPermission name="zyaqgl:xgdw:add">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addgl()" title="添加关联信息"><i class="fa fa-plus"></i> 添加关联信息</button> 
        	</shiro:hasPermission>

			<shiro:hasPermission name="zyaqgl:xgdw:exin">
        		<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/zyaqgl/xgdw/exinjump','${ctx}/zyaqgl/xgdw/exin','${ctx}/static/templates/相关方单位导入模板.xls')" title="导入">
        			<i class="fa fa-folder-open-o"></i> 导入
        		</button>        	
        	</shiro:hasPermission>
            <shiro:hasPermission name="zyaqgl:xgdw:blacklist">
                <button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="addblacklist()" title="加入黑名单"> 加入黑名单</button>
            </shiro:hasPermission>
            <shiro:hasPermission name="zyaqgl:xgdw:blacklist">
                <button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="removeblacklist()" title="移出黑名单"> 移出黑名单</button>
            </shiro:hasPermission>
            <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
	   
</div>
<table id="zyaqgl_xgdw_dg"></table>
<script type="text/javascript">
	var qyid= '${qyid}';
</script>
</body>
</html>