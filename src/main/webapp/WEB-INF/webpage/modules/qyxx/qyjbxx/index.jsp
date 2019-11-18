<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/qyjbxx/index.js?v=2.3"></script>
<script type="text/javascript">
var ctx='${ctx}';
jglx='${jglx}';
</script>

</head>
<body>
<div id="bis_index_tb" style="padding:5px;height:auto">
        	<form id="bis_index_searchFrom" style="margin-bottom: 8px;" class="form-inline">
        		<input type="hidden" id="check_company_ids" name="bis_ids" />
       	        <input type="text" id="check_company_name" name="check_company_name" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		        <input id="qygm" name="qygm" class="easyui-combobox" style="height: 30px;width: 100px;" data-options="panelHeight:'auto',
								editable:false ,data: [
										{value:'规上',text:'规上'},
										{value:'规下',text:'规下'}],prompt: '规模情况' "/>
				<c:if test="${userroleflg!=1&&userroleflg!=2}">
				<input id="jglx" name="jglx" class="easyui-combobox" style="height: 30px;width: 100px;" value="${jglx}" data-options="panelHeight:'auto',
								editable:false ,data:[{value:'0',text:'全部'},{value:'1',text:'工贸'},{value:'2',text:'化工'},{value:'9',text:'其他'}],prompt: '监管分类' "/>
				</c:if>				
								
		        <input id="wxydj" name="wxydj" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:100,
								editable:false ,data: [
										{value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'}],prompt: '重大危险源等级' "/>
		        <input id="fxdj" name="fxdj" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:100,
								editable:false ,data: [
										{value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'}],prompt: '企业风险等级' "/>
		        <input id="bzhdj" name="bzhdj" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:100,
								editable:false ,data: [
										{value:'1',text:'一级'},
										{value:'2',text:'二级'},
										{value:'3',text:'三级'},
										{value:'4',text:'达标'},
										{value:'5',text:'未创建'}],prompt: '安全标准化等级' "/>
				<input type="text"  name="xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
       			<input  name="isbloc" class="easyui-combobox" style="height: 30px;width: 100px;" data-options="panelHeight:'auto',
								editable:false ,data: [
										{value:'1',text:'是'},
										{value:'0',text:'否'}],prompt: '集团公司' "/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
       		
       		</form> 
       		<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">

			<shiro:hasPermission name="bis:qyjbxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>

			<shiro:hasPermission name="bis:qyjbxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:qyjbxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:qyjbxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="bis:qyjbxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
 			</shiro:hasPermission>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="statistics()" title="统计"><i class="fa fa-bar-chart"></i> 统计</button>
            <shiro:hasPermission name="bis:qyjbxx:comfirm">
            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="nodatamodule()" title="确认"><i class="fa fa-check"></i> 信息确认</button> 
            </shiro:hasPermission>
            <shiro:hasAnyRoles name="admin,superadmin">
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enableBis()" title="启用"> 启用</button>
        		<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disableBis()" title="停用"> 停用</button>
        	</shiro:hasAnyRoles>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		
	</div>
	</div> 
  </div>
<table id="bis_index_dg"></table> 
<div id="select_type" style="display:none;height:350px"  >
       <table id="areadata"></table> 
      </div>


<script type="text/javascript">
	var role = '0';
	var state = '${state}';
</script>
<shiro:hasAnyRoles name="admin,superadmin">
	<script>role = '1';</script>
</shiro:hasAnyRoles>
</body>
</html>