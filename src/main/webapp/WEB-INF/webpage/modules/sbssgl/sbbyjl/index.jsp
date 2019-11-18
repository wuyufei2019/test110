<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备保养记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbyjl/index.js?v=1.1"></script>
</head>
<body >
<div class="easyui-tabs" fit="true">
	<div title="设备一级保养计划" style="height:100%;">
		<div id="sbssgl_sbbyjl_fir_tb" style="padding:5px;height:auto">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
					<c:if test="${type eq '2'}">
						<input type="text" name="qynamefir" class="easyui-combobox" style="width: 150px;height: 30px;" 
							data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称',
								onSelect: function(row) {
									$('#deptidfir').combobox('setValue', '');
									$('#deptidfir').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							 "/>
					</c:if>
					<input name="yearfir" id="yearfir" class="easyui-combobox" style="width: 150px;height: 30px;" data-options="prompt: '年度', panelHeight: '150px'" />
					<input name="month" id="month" class="easyui-combobox" style="width: 150px;height: 30px;" data-options="prompt: '月度', panelHeight: '150px'" />
					<input name="deptidfir" id="deptidfir" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
					<input name="bztimefir" id="bztimefir" class="easyui-datebox" style="width: 150px;height: 30px;" data-options="editable:false,prompt: '编制日期'" />
					<input name="bzrnamefir" id="bzrnamefir" class="easyui-textbox" style="width: 150px;height: 30px;" data-options="prompt: '编制人'" />
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			    </div>
			    <input type="hidden" name="sbtypefir" value="${sbtype}">
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="sbssgl:sbbyjl:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbbyjl:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbbyjl:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbbyjl:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出设备一级保养计划"><i class="fa fa-external-link"></i> 导出设备一级保养计划</button> 
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjl()" title="导出设备一级保养记录"><i class="fa fa-external-link"></i> 导出设备一级保养记录</button>
			        	</shiro:hasPermission>
			        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
					<div class="pull-right">
					</div>
				</div>
			</div> 	   
		</div>
		<table id="sbssgl_sbbyjl_fir_dg"></table> 
	 </div>
    <div title="设备二级保养计划" style="height:100%;">
    	<div id="sbssgl_sbbyjl_sec_tb" style="padding:5px;height:auto">
			<form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
					<c:if test="${type eq '2'}">
						<input type="text" name="qynamesec" class="easyui-combobox" style="height: 30px;" 
							data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称',
								onSelect: function(row) {
									$('#deptidsec').combobox('setValue', '');
									$('#deptidsec').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							 "/>
					</c:if>
					<input name="yearsec" id="yearsec" class="easyui-combobox" style="width: 120px;height: 30px;" data-options="prompt: '年度', panelHeight: '150px'" />
					<input name="deptidsec" id="deptidsec" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
					<input name="bztimesec" id="bztimesec" class="easyui-datebox" style="width: 150px;height: 30px;" data-options="editable:false,prompt: '编制日期'" />
					<input name="bzrnamesec" id="bzrnamesec" class="easyui-textbox" style="width: 150px;height: 30px;" data-options="prompt: '编制人'" />
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>
			    </div>
			    <input type="hidden" name="sbtypesec" value="${sbtype}">
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="sbssgl:sbbyjl:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd2()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbbyjl:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del2()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbbyjl:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbbyjl:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport2()" title="导出设备二级保养计划"><i class="fa fa-external-link"></i> 导出设备二级保养计划</button> 
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjl2()" title="导出设备一级保养记录"><i class="fa fa-external-link"></i> 导出设备二级保养记录</button>
			        	</shiro:hasPermission>
			        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
					<div class="pull-right">
					</div>
				</div>
			</div> 	   
		</div>
		<table id="sbssgl_sbbyjl_sec_dg"></table> 
	</div>
</div>

<script>
	var role = '0';
	var uploadrole = '0';
</script>
<shiro:hasPermission name="sbssgl:sbbyjl:operation">
	<script>role = '1';</script>
</shiro:hasPermission>
<shiro:hasPermission name="sbssgl:sbbyjl:upload">
	<script>uploadrole = '1';</script>
</shiro:hasPermission>
<script>
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
	
	$(function(){
		// 年度下拉框
		var curYear = new Date().getFullYear();
		var data = [];
		for (var  i = (curYear - 3); i < (curYear + 3); i++) {
			data.push({value: i, text: i});
		}
		$("#yearfir").combobox('loadData', data);
		$("#yearsec").combobox('loadData', data);
		
		// 月度下拉框
		var mons = [];
		for (var j = 1; j < 13; j++) {
			mons.push({value: j, text: j});
		}
		$("#month").combobox('loadData', mons);
	})
	
	
</script>
</body>
</html>