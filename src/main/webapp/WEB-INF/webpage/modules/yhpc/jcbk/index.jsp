<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检查表库信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/yhpc/jcbk/index.js?v=1.2"></script>
<script type="text/javascript">
var ctx='${ctx}';
var usertype='${usertype}';
</script>
</head>
<body>
<div class="easyui-tabs" fit="true">
		<div title="公共检查表" style="height:100%;" data-options="">
			<div id="yhpc_jcbk_tb" style="padding:5px;height:auto">
				<div class="row">
					<div class="col-sm-12">
				       	<form id="yhpc_jcbk_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
				      	    <input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
											 editable:false ,panelHeight:'auto' ,data: [
											        {value:'1',text:'一般'},
											        {value:'2',text:'重大'}
																	]"/>
							<input name="checktitle" class="easyui-combobox"  style="height: 30px; " 
											data-options="editable:true ,prompt: '检查单元' ,
											valueField: 'text',textField: 'text',url:'${ctx}/yhpc/jcbk/gettype' " />
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>  	   				
						</form>
						<div class="pull-left">
							<span id="divper">
							<c:if test="${usertype != '1'}">
							<shiro:hasPermission name="yhpc:jcbk:add">
						       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(1)" title="添加"><i class="fa fa-plus"></i> 添加</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="yhpc:jcbk:update">
							    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(1)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
							</shiro:hasPermission>
							<shiro:hasPermission name="yhpc:jcbk:delete">
								<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(1)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
							</shiro:hasPermission>
							</c:if>
							<shiro:hasPermission name="yhpc:jcbk:view">
					       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(1)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
					       	</shiro:hasPermission>
					       	<shiro:hasPermission name="yhpc:jcbk:export">
					       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(1)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
					       	</shiro:hasPermission>
					       	<c:if test="${usertype != '1'}">
					       	<shiro:hasPermission name="yhpc:jcbk:exin">
								<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog2('${ctx}/yhpc/jcbk/exinjump','${ctx}/yhpc/jcbk/exin/'+1,'${ctx}/static/templates/巡检内容信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
							</shiro:hasPermission>
							</c:if>
								<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</span>
						</div>
        			</div>    
        		</div>
        	</div> 
			<table id="yhpc_jcbk_dg"></table> 
		</div>

		
		<div title="企业自增表" style="height:100%;">
			<div id="yhpc_jcbk_tb2" style="padding:5px;height:auto">
			<div class="row">
				<div class="col-sm-12">
	        	<form id="yhpc_jcbk_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline" >
	        	<c:if test="${usertype ne '1' }">
	        		<input type="text" id="yhpc_jcbk_qyname" name="yhpc_jcbk_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	       	    </c:if>
	       	        <input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
									 editable:false ,panelHeight:'auto' ,data: [
									        {value:'1',text:'一般'},
									        {value:'2',text:'重大'}
															]
							    "/>
	       	        <input name="checktitle" class="easyui-combobox" style="height: 30px;"
									data-options="prompt: '检查单元' ,editable:true ,
									valueField: 'text',textField: 'text',url:'${ctx}/yhpc/jcbk/gettype' " />
					
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA2()" ><i class="fa fa-refresh"></i> 全部</span>  	   	
				</form>
				<div class="pull-left">
					<span id="divper">
					<c:if test="${usertype == '1'}">
					<shiro:hasPermission name="yhpc:jcbk:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(2)" title="添加"><i class="fa fa-plus"></i> 添加</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="yhpc:jcbk:update">
					    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(2)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
					</shiro:hasPermission>
					<shiro:hasPermission name="yhpc:jcbk:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(2)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
					</shiro:hasPermission>
					</c:if>
					<shiro:hasPermission name="yhpc:jcbk:view">
		        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(2)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
		        	</shiro:hasPermission>
		        	<shiro:hasPermission name="yhpc:jcbk:export">
		        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(2)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
		        	</shiro:hasPermission>
		        	<c:if test="${usertype == '1'}">
		        	<shiro:hasPermission name="yhpc:jcbk:exin">
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog2('${ctx}/yhpc/jcbk/exinjump','${ctx}/yhpc/jcbk/exin/'+2,'${ctx}/static/templates/巡检内容信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
					</shiro:hasPermission>
					</c:if>
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</span>
				</div>
 			</div>
			</div>
 			</div>
			<table id="yhpc_jcbk_dg2"></table> 
		</div>
		
		<c:if test="${usertype != '1'}">
			<div title="网格检查表" style="height:100%;">
				<div id="yhpc_jcbk_tb3" style="padding:5px;height:auto">
				<div class="row">
				<div class="col-sm-12">
	        	<form id="yhpc_jcbk_searchFrom3" style="margin-bottom: 8px;" action="" class="form-inline">
	       	        <input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
									 editable:false ,panelHeight:'auto' ,data: [
									        {value:'1',text:'一般'},
									        {value:'2',text:'重大'}]"/>
					<!-- <input name="checktitle" class="easyui-textbox"  style="height: 30px; " 
									data-options="editable:true ,prompt: '检查单元'" /> -->
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search3()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA3()" ><i class="fa fa-refresh"></i> 全部</span>  	   	
				</form>
				<div class="pull-left">
				<span id="divper">
				<shiro:hasPermission name="yhpc:jcbk:add">
			       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add(3)" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="yhpc:jcbk:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd(3)" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="yhpc:jcbk:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del(3)" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>
				
				<shiro:hasPermission name="yhpc:jcbk:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view(3)" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="yhpc:jcbk:export">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(3)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="yhpc:jcbk:exin">
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog2('${ctx}/yhpc/jcbk/exinjump','${ctx}/yhpc/jcbk/exin/'+3,'${ctx}/static/templates/巡检内容信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
				</shiro:hasPermission>
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				</span>
				</div>
				<div style = "text-align:right;">
	        	</div>
	        	</div>    
	        	</div>
	        	</div> 
				<table id="yhpc_jcbk_dg3"></table> 
			</div>
		</c:if>
</div>
</body>
</html>