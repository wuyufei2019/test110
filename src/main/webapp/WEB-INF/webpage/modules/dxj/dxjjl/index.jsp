<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>点巡检记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dxj/dxjjl/index.js?v=1.1"></script>
	<script type="text/javascript">
		var type = '${type}';
	</script>
</head>
<body >
<div id="dxj_dxjjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type != '1' }">
	    	<input type="text" id="dxj_dxjjl_qyname" name="dxj_dxjjl_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	    	<input name="dxj_dxjjl_sbname" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '设备名称'" />
	    </c:if>
	    <c:if test="${type != '2' }">
			<input type="text" id="dxj_dxjjl_sbname" name="dxj_dxjjl_sbname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/dxj/sb/sblist',prompt: '设备名称' "/>
		</c:if>
		<!-- <input name="dxj_dxjjl_sbxmname" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '设备项目名称'" /> -->
	    <input type="text" name="dxj_dxjjl_checkresult" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查结果',data: [
						         {value:'0',text:'无隐患'},
						         {value:'1',text:'有隐患'}] "/>  
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>	  	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="dxj:dxjjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="dxj:dxjjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
        	<shiro:hasPermission name="dxj:dxjjl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>

<table id="dxj_dxjjl_dg"></table> 
</body>
</html>