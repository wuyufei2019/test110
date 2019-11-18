<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方评价表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/dsf/pj/index.js?v=2"></script>
</head>
<body >
<div id="aqjg_dsf_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="aqjg_dsf_pj_M1" name="aqjg_dsf_pj_M1" style="height: 30px;"
					class="easyui-combobox" data-options="panelHeight:'auto' ,editable:true,valueField:'text',textField:'text',url:'${ctx }/dsffw/fwxmbb/dwnamelist',prompt: '单位名称'"/> 	 
	<input id="aqjg_dsf_pj_M4" name="aqjg_dsf_pj_M4" class="easyui-combobox" value="" style="height: 30px;" data-options="panelHeight:'auto' ,prompt: '年度',editable:false ,valueField: 'text', textField: 'text',url:'${ctx}/dsffw/pj/ndlist' " />
   	<input id="aqjg_dsf_pj_M2" name="aqjg_dsf_pj_M2" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '评价等级',editable:false,data: [
						        {value:'1',text:'优秀'},
						        {value:'2',text:'良好'},
						        {value:'3',text:'合格'},
						        {value:'4',text:'不合格'},
						        	]  "/>		
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>	   	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="dsffw:pj:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dsffw:pj:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dsffw:pj:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			</span>
			<shiro:hasPermission name="dsffw:pj:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="dsffw:pj:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>

<table id="aqjg_pj_dg"></table> 
</body>
</html>