<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备台账</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbtz/index.js?v=1.1"></script>
</head>
<body >
<div id="sbssgl_sbtz_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="m1" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备编号'" />
		<input name="m2" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备名称'" />
		<input name="m9" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '系列号'" />
		<input name="m16" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '启用日期'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
		
		<shiro:hasPermission name="sbssgl:sbgl:export">
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="fileexport('${sbtype}')" ><i class="fa fa-external-link"></i> 导出设备清单</span>
       	</shiro:hasPermission>
    </div>
	</form>

</div>
<table id="sbssgl_sbtz_dg"></table> 
<script>
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
</script>
</body>
</html>