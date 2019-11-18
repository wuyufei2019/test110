<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/dhzy/index.js?v=1.8"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ztzyaqgl_dhzy_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<shiro:hasPermission name="zyaqgl:gczy:viewall">
			<input name="aqgl_dhzy_cx_depid" class="easyui-combotree" style="height: 30px;" data-options="editable:false,panelHeight:180, valueField: 'id',textField: 'text',url:'${ctx}/system/department/idjson', prompt:'所属部门' "/>
		</shiro:hasPermission>
		<input type="text" id="aqgl_dhzy_cx_m1" name="aqgl_dhzy_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '作业证编号 '"/>
		<input class="easyui-combobox" name="aqgl_dhzy_cx_m4"  style="height: 30px;" data-options="panelHeight:'auto', prompt: '动火作业级别 ',editable:false ,data: [
																																		        {value:'特殊动火',text:'特殊动火'},
																																		        {value:'一级动火',text:'一级动火'},
																																		        {value:'二级动火',text:'二级动火'}] "/>
		<input class="easyui-combobox" name="aqgl_dhzy_cx_zt"  style="height: 30px;" data-options="panelHeight:'150', prompt: '作业证状态 ',editable:false ,data: [
																																		        {value:'0',text:'待编制'},
																																		        {value:'1',text:'待作业单位签字'},
																																		        {value:'2',text:'待安技员签字'},
																																		        {value:'3',text:'待部门一把手签字'},
																																		        {value:'4',text:'待分厂安全分管领导签字'},
																																		        {value:'5',text:'待安全处分管人员签字'},
																																		        {value:'6',text:'待安全处分管领导签字'},
																																		        {value:'7',text:'待保卫部签字'},
																																		        {value:'8',text:'待公司分管领导签字'},
																																		        {value:'9',text:'待能源管控中心签字'},
																																		        {value:'10',text:'待确认措施'},
																																		        {value:'11',text:'待分析'},
																																		        {value:'12',text:'待作业单位完工签字'},
																																		        {value:'13',text:'待分厂单位完工签字'},
																																		        {value:'14',text:'完工'}] "/>
    	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>      
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zyaqgl:dhzy:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="申请"><i class="fa fa-plus"></i> 申请</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:dhzy:delete">
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


<table id="zyaqgl_dhzy_dg"></table> 
<script type="text/javascript">
	var spzt='${spzt}';
	userid= '${userid}';
	var delay = 0;//延期权限
	var role0 = 0;//安技员审批
	var role1 = 0;//分厂安全分管领导或分管领导审批
	var role2 = 0;//部门一把手审批
	var role3 = 0;//安全处分管人员审批
	var role4 = 0;//安全处分管领导审批
	var role5 = 0;//保卫处审批
	var role6 = 0;//公司分管领导审批
	var role7 = 0;//能源中心审批
	var role8 = 0;//许可人（分析和编制确认）
	var role9 = 0;//编制人
</script>
<shiro:hasPermission name="zyaqgl:gczy:delay">
<script type="text/javascript">
	delay= 1; //延期权限
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:ajysp">
<script type="text/javascript">
	role0= 1; //安技员审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:aqkzsp">
<script type="text/javascript">
	role1 = 1;//分厂安全分管领导审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:ybssp">
<script type="text/javascript">
	role2 = 1;//部门一把手审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:aqcrysp">
<script type="text/javascript">
	role3 = 1;//安全处分管人员审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:aqcldsp">
<script type="text/javascript">
	role4 = 1;//安全处分管领导审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:dhzy:bwcsp">
<script type="text/javascript">
	role5 = 1;//保卫处审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:gczy:gsldsp">
<script type="text/javascript">
	role6 = 1;//公司分管领导审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:dhzy:nyzxsp">
<script type="text/javascript">
	role7 = 1;//能源中心审批
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:dhzy:fx">
<script type="text/javascript">
	role8 = 1;//许可人（分析和编制确认）
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:dhzy:bz">
<script type="text/javascript">
	role9 = 1;//编制人
</script>
</shiro:hasPermission>
</body>
</html>