<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动土作业信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/dtzy/index.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_dtzy_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<shiro:hasPermission name="zyaqgl:gczy:viewall">
			<input name="aqgl_dtzy_cx_depid" class="easyui-combotree" style="height: 30px;" data-options="editable:false,panelHeight:180, valueField: 'id',textField: 'text',url:'${ctx}/system/department/idjson', prompt:'所属部门' "/>
		</shiro:hasPermission>
		<input type="text" id="aqgl_dtzy_cx_m1" name="aqgl_dtzy_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '作业证编号 '"/>
		<input type="text" id="aqgl_dtzy_cx_m2" name="aqgl_dtzy_cx_m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '申请单位'"/>
		<input class="easyui-combobox" name="aqgl_dtzy_cx_zt"  style="height: 30px;" data-options="panelHeight:'150', prompt: '作业证状态 ',editable:false ,data: [
																																		        {value:'0',text:'待编制'},
																																		        {value:'1',text:'待作业单位确认'},
																																		        {value:'2',text:'待能控中心签字'},
																																		        {value:'3',text:'待分厂签字'},
																																		        {value:'4',text:'待设备处签字'},
																																		        {value:'5',text:'待保卫处签字'},
																																		        {value:'6',text:'待安全处签字'},
																																		        {value:'7',text:'待确认措施'},
																																		        {value:'8',text:'待作业单位完工签字'},
																																		        {value:'9',text:'待分厂完工签字'},
																																		        {value:'10',text:'已完工'}] "/>
    	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>      
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zyaqgl:dtzy:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="申请"><i class="fa fa-plus"></i> 申请</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:dtzy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
		</span>
       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewzt()" title="查看状态"><i class="fa fa-star-o"></i> 查看状态</button> 
       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button> 
        	<span id="divper2">
			</span>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>


<table id="zyaqgl_dtzy_dg"></table> 
<script type="text/javascript">
	var spzt='${spzt}';
	userid= '${userid}';
	var role0=0;//能控中心审批
	var role1=0;//分厂安全分管领导或分管领导审批（分厂审批）
	var role2=0;//安技员审批（分厂审批）
	var role3=0;//设备处审批
	var role4=0;//保卫处审批
	var role5=0;//安全处分管人员审批（安全处审批）
	var role6=0;//许可人（分析和编制确认）
	var role7=0;//安全处分管领导
</script>
<shiro:hasPermission name="zyaqgl:dhzy:nyzxsp">
<script type="text/javascript">
	role0 = 1;//能控中心审批
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:gczy:aqkzsp">
<script type="text/javascript">
	role1 = 1;//分厂安全分管领导或分管领导审批（分厂审批）
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:gczy:ajysp">
<script type="text/javascript">
	role2= 1; //安技员审批（分厂审批）
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:dtzy:sbcsp">
<script type="text/javascript">
	role3 = 1;//设备处审批
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:dhzy:bwcsp">
<script type="text/javascript">
	role4 = 1;//保卫处审批
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:gczy:aqcrysp">
<script type="text/javascript">
	role5 = 1;//安全处分管人员审批（安全处审批）
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:dhzy:fx">
<script type="text/javascript">
	role6 = 1;//许可人（分析和编制确认）
</script>
</shiro:hasPermission>

<shiro:hasPermission name="zyaqgl:gczy:aqcldsp">
<script type="text/javascript">
	role7 = 1;//安全处分管领导
</script>
</shiro:hasPermission>

</body>
</html>