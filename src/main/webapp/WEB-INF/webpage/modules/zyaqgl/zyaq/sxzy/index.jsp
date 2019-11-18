<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/zyaq/sxzy/index.js?v=1.1"></script>
</head>
<body >
<div id="zyaq_sxzy_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type != '1'}">
			<input type="text" id="zyaq_sxzy_qyname" name="zyaq_sxzy_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input type="text" id="zyaq_sxzy_m0" name="zyaq_sxzy_m0" style="height: 30px;" class="easyui-textbox" data-options="prompt: '作业证编号'"/>
		<input type="text" id="zyaq_sxzy_m3" name="zyaq_sxzy_m3" style="height: 30px;" class="easyui-textbox" data-options="prompt: '受限空间名称'"/>
		<input id="zyaq_sxzy_zt" name="zyaq_sxzy_zt" class="easyui-combobox" style="height: 30px;" data-options="prompt: '作业证状态',panelHeight:'150px',editable:false,data: [
										{value:'0',text:'已申请'},
										{value:'1',text:'已分配任务'},
								        {value:'2',text:'已分析'},
								        {value:'3',text:'已编制安全措施'},
								        {value:'4',text:'已确认安全措施'},
								        {value:'5',text:'已实施安全教育'},
								        {value:'6',text:'申请单位确认'},
								        {value:'7',text:'已审批'},
								        {value:'7',text:'已验收'},
								        {value:'7',text:'已关闭'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="zyaq:sxzy:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="申请"><i class="fa fa-plus"></i> 申请</button>
            </shiro:hasPermission>
			<shiro:hasPermission name="zyaq:sxzy:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaq:sxzy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaq:sxzy:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zyaq:sxzy:viewzt">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewzt()" title="查看状态"><i class="fa fa-star-o"></i> 查看状态</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zyaq:sxzy:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出Excel"><i class="fa fa-external-link"></i> 导出Excel</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zyaq:sxzy:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button> 
        	</shiro:hasPermission>  
        	<shiro:hasPermission name="zyaq:sxzy:gb">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="gb()" title="关闭"><i class="fa fa-times-circle"></i> 关闭</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="zyaq_sxzy_dg"></table> 
<script type="text/javascript">
var qyid= '${qyid}';
var businesstype= '${businesstype}';
var fp = '0';
var fx = '0';
var userid = '${userid}';
var type = '${type}';
</script>
<shiro:hasPermission name="zyaq:sxzy:fp">
<script type="text/javascript">
	fp = '1';
</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaq:sxzy:fx">
<script type="text/javascript">
	fx = '1';
</script>
</shiro:hasPermission>
</body>
</html>