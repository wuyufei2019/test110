<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>关联信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgdw/glindex.js?v=1.1"></script>
<script type="text/javascript">
var ctx='${ctx}';
var usertype='${usertype}';
var dwid='${dwid}';
</script>
<style type="text/css">
.zyaqgl_xgdw_sj{
font-size: 12px;
margin: 8px 0px;
}
.zyaqgl_xgdw_sj span{
margin-right: 20px;
}
.zyaqgl_xgdw_sj input{
width: 13px;
margin: 0px;
padding: 0px;
}
.zyaqgl_xgdw_sjtk{
font-size: 12px;
margin: 5px 0px;
}
</style>
</head>
<body>
<div class="easyui-tabs" fit="true">
		<div title="资质信息表" style="height:100%;" data-options="">
			<div id="zyaqgl_xgdw_tb" style="padding:5px;height:auto">
			<div>
        	<form id="zyaqgl_xgdw_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
       	        <input class="easyui-combobox" name="jb" style="height: 30px;" data-options="prompt: '级别' ,
								 editable:false ,panelHeight:'auto' ,data: [
										{value:'一级',text:'一级'},
										{value:'二级',text:'二级'},
										{value:'三级',text:'三级'},
										{value:'四级',text:'四级'},
										{value:'五级',text:'五级'},
										{value:'六级',text:'六级'}
														]
						    "/>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
			<div class="pull-right">
			</div>
			</form>
			<span id="divper">
			<shiro:hasPermission name="zyaqgl:xgdw:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(1)" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(1)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(1)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(1)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zyaqgl:xgdw:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(1)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
			</div>
        	</div>    
			<table id="zyaqgl_xgdw_dg"></table> 
		</div>

		
		<div title="作业人员表" style="height:100%;">
			<div id="zyaqgl_xgdw_tb2" style="padding:5px;height:auto">
        	<div>
        	<form id="zyaqgl_xgdw_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline" >
       	        <input type="text" id="xgdw_tzzyry_cx_m1" name="xgdw_tzzyry_cx_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '姓名'"/>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA2()" ><i class="fa fa-refresh"></i> 全部</span>		
		    <div class="pull-right">
			</div>  
			</form>
			<span id="divper">
			<c:if test="${usertype == '1'}">
			<shiro:hasPermission name="zyaqgl:xgdw:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(2)" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(2)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zyaqgl:xgdw:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(2)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			</c:if>
			<shiro:hasPermission name="zyaqgl:xgdw:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(2)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zyaqgl:xgdw:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(2)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
        	</div>    
 			</div>

			<table id="zyaqgl_xgdw_dg2"></table> 
		</div>
		
			<div title="服务项目表" style="height:100%;">
				<div id="zyaqgl_xgdw_tb3" style="padding:5px;height:auto">
	        	<form id="zyaqgl_xgdw_searchFrom3" style="margin-bottom: 8px;" action="" class="form-inline">
	       	        <input type="text" id="xgdw_fwxm_cx_m2" name="xgdw_fwxm_cx_m2" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '项目名称'"/>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search3()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA3()" ><i class="fa fa-refresh"></i> 全部</span>
				</form>
				<span id="divper">
				<shiro:hasPermission name="zyaqgl:xgdw:add">
			       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(3)" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="zyaqgl:xgdw:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(3)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="zyaqgl:xgdw:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(3)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>
				
				<shiro:hasPermission name="zyaqgl:xgdw:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(3)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="zyaqgl:xgdw:export">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(3)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="zyaqgl:xgdw:exin">
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog2('${ctx}/yhpc/jcbk/exinjump','${ctx}/yhpc/jcbk/exin/'+3,'${ctx}/static/templates/巡检内容信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
				</shiro:hasPermission>
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				</span>
	        	</div>    
				<table id="zyaqgl_xgdw_dg3"></table> 
			</div>
</div>


<div id="zyaqgl_xgdw_dlg"></div>  

</body>
</html>