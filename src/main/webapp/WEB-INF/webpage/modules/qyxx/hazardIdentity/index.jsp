<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>

<script type="text/javascript" src="${ctx}/static/model/js/bis/hazardIdentity/index.js?v=1.2"></script>
</head>
<body>
	<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" >
       	        <input type="text" name="bis_hazardIdentity_cx_m1" class="easyui-combobox" data-options="width:180,prompt: '类别', editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/wxhxpzdfl',
       	        onSelect: function(rec){
       	        	$('#bis_hazardIdentity_cx_m2').combobox('clear');  
				    var url = '${ctx}/bis/hazardIdentity/dict/'+rec.text;
				    $('#bis_hazardIdentity_cx_m2').combobox('reload', url);
				    } "/>
    			<input type="text" id="bis_hazardIdentity_cx_m2" name="bis_hazardIdentity_cx_m2" class="easyui-combobox" data-options="width:200,prompt: '辨识物质', editable:false ,valueField: 'id',textField: 'text' "/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="row">
    		<div class="col-sm-12">
    			<div class="pull-left">
    				<shiro:hasPermission name="bis:hazardIdentity:add">
		       			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			       	</shiro:hasPermission>
			        <shiro:hasPermission name="bis:hazardIdentity:update">
			            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="bis:hazardIdentity:delete">
			            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="bis:hazardIdentity:view">
		        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
		        	</shiro:hasPermission>
		        	<shiro:hasPermission name="bis:hazardIdentity:export">
					    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="bis:hazardIdentity:exin">
						<button class="btn btn-white btn-sm "  data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/gwgy/exinjump','${ctx}/bis/gwgy/exin','${ctx}/static/templates/危险工艺息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
					</shiro:hasPermission>
    			</div>
    		</div>
    	</div>
  </div>
  </div>
<table id="dg"></table> 
</body>
</html>