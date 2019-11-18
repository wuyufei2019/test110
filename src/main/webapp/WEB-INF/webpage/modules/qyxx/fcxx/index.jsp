<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>粉尘信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/fcxx/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_fcxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">	
	<input type="text" id="bis_fcxx_cx_m1" name="bis_fcxx_cx_m1" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '粉尘种类',editable:true,data: [
										{value:'铝粉',text:'铝粉'},
								        {value:'镁粉',text:'镁粉'},
								        {value:'铁粉',text:'铁粉'},
								        {value:'锌粉',text:'锌粉'},
								        {value:'铜粉',text:'铜粉'},
								        {value:'木粉',text:'木粉'},
								        {value:'树脂粉尘 ',text:'树脂粉尘 '},
								        {value:'面粉',text:'面粉'},
								        {value:'小麦粉尘',text:'小麦粉尘'},
								        {value:'乳糖粉尘',text:'乳糖粉尘'},
								        {value:'纸浆粉尘',text:'纸浆粉尘'},
								        {value:'聚酯纤维',text:'聚酯纤维'},
								        {value:'棉花',text:'棉花'},
								        {value:'橡胶粉尘',text:'橡胶粉尘'},
								        {value:'乳化剂 ',text:'乳化剂 '},
								        {value:'静电粉末',text:'静电粉末'},
								        {value:'不锈钢',text:'不锈钢'}] ,
										"/>	
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>															        										
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:fcxx:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:fcxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:fcxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:fcxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:fcxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:fcxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/fcxx/exinjump','${ctx}/bis/fcxx/exin','${ctx}/static/templates/粉尘信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_fcxx_dg"></table> 

</body>
</html>