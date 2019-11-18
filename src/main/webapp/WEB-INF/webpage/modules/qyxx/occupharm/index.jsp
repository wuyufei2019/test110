<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>职业病危害因素信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/occupharm/index.js?v=1.5"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_occupharm_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		
		<input type="text" id="bis_zyb_cx_m1" name="bis_zyb_cx_m1" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '职业病危害因素分类',editable:true,data: [
								        {value:'粉尘',text:'粉尘'},
								        {value:'化学因素',text:'化学因素'},
								        {value:'物理因素',text:'物理因素'},
								        {value:'放射性因素',text:'放射性因素'},
								        {value:'生物性因素',text:'生物性因素'},
								        {value:'其他因素',text:'其他因素'} ]"/>
    	<input type="text" id="bis_zyb_cx_m2" name="bis_zyb_cx_m2" class="easyui-combobox" style="height: 30px;" data-options="prompt: '职业病危害因素名称',
		         valueField: 'id',textField: 'text',url:'${ctx}/bis/occupharm/izy'"/>
    	<input type="text" id="bis_zyb_cx_m3" name="bis_zyb_cx_m3" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '严重程度',editable:false,data: [
								        {value:'1',text:'轻度危险性'},
								        {value:'2',text:'一般危险性'},
								        {value:'3',text:'严重危险性'} ]"/>
		<input id="bis_zyb_cx_m5" name="bis_zyb_cx_m5" class="easyui-textbox"  style="height: 30px;"  data-options="prompt: '可能导致的职业病'  " />    
		<input type="text" name="bis_zyb_cx_m6" class="easyui-combobox" style="height: 30px;"
				data-options="editable:true ,prompt: '存在岗位',panelHeight:'100px', valueField: 'text',textField: 'text',url:'${ctx}/system/department/deptjson' "/>
		<input type="text" name="bis_zyb_cx_m7" class="easyui-combobox" style="height: 30px;"
				data-options="editable : true,prompt: '存在岗位',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson'" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:occupharm:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharm:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharm:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharm:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:occupharm:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			<shiro:hasPermission name="bis:occupharm:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/occupharm/exinjump','${ctx}/bis/occupharm/exin','${ctx}/static/templates/职业病危害因素导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
		</div>
	</div>
	</div> 
	   
</div>


<table id="bis_occupharm_dg"></table> 

</body>
</html>