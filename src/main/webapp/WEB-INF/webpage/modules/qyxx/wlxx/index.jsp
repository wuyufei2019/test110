<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
  <head>
  <title>物料信息</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript" src="${ctx}/static/model/js/qyxx/wlxx/index.js?v=1.6"></script>
  </head>
  
  <body>
    <!-- 工具栏 -->
<div id="bis_wlxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" name="bis_wlxx_cx_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt:'物料名称'" />
    	<input type="text" id="ccfs" name="ccfs" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' , editable:false,data: [
				        {value:'1',text:'储罐'},
				        {value:'2',text:'桶装'},
				        {value:'3',text:'袋装'},
				        {value:'4',text:'其他'} ] ,prompt: '储存方式'"/>
		    <input type="text" id="whplb" name="whplb" class="easyui-combobox" style="height: 30px;" data-options=" editable:true ,valueField: 'text',textField: 'text',
								url:'${ctx}/tcode/dict/wxhxpzywx',prompt: '危险性类别'"/>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:wlxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:wlxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:wlxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:wlxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="用户角色"><i class="fa fa-search-plus"></i>查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:wlxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i>导出</button> 
        	</shiro:hasPermission>
        	        	<shiro:hasPermission name="bis:wlxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/wlxx/exinjump','${ctx}/bis/wlxx/exin','${ctx}/static/templates/物料信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_wlxx_dg"></table> 

<script type="text/javascript">

</script>
  </body>
</html>
