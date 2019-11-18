<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/jcrw/index.js?v=1.7"></script>
<script type="text/javascript">
var flag=0;
</script>
</head>
<body >
		<!-- 工具栏 -->
		<div class="easyui-tabs" fit="true">
			<div title="任务分配" style="height:100%;" data-options="" id="fp">
				<div id="yhpc_jcrw_tb" style="padding:5px;height:auto">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
					<div class="form-group">
						<input name="yhpc_jcrw_jcr" id="yhpc_jcrw_jcr" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'检查人',panelHeight:'100',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
						<input name="yhpc_jcrw_time" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查时间'" />
						<input name="yhpc_jcrw_jclb" id="yhpc_jcrw_jclb" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'检查类别',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/yhpc/rcjcbk/xwlblist'" />
						<input name="yhpc_jcrw_zt" id="yhpc_jcrw_zt" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'状态',panelHeight:'auto',data: [
									        {value:'1',text:'已检查'},
									        {value:'0',text:'未检查'},
									        	]" />
		
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
				    </div>
					</form>
				
					<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="yhpc:jcrw:add">
							    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 	 
							</shiro:hasPermission>
		 					<shiro:hasPermission name="yhpc:jcrw:update">
							    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 	 
							</shiro:hasPermission>
							<shiro:hasPermission name="yhpc:jcrw:delete">
								<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
							</shiro:hasPermission>
							<shiro:hasPermission name="yhpc:jcrw:view">
				        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
				        	</shiro:hasPermission>
					       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="reset()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</div>
					</div>
					</div> 
				</div>
				<table id="yhpc_jcrw_dg"></table> 
			</div>
			
			<div title="我的任务" style="height:100%;" id="rw"> 
				<div id="yhpc_wdrw_tb" style="padding:5px;height:auto">
					<form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">				
					<div class="form-group">
						<input name="yhpc_jcrw_time" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查时间'" />
						<input name="yhpc_jcrw_jclb2" id="yhpc_jcrw_jclb2" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'检查类别',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/yhpc/rcjcbk/xwlblist'" />
						<input name="yhpc_jcrw_zt2" id="yhpc_jcrw_zt2" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'状态',panelHeight:'auto',data: [
									        {value:'1',text:'已检查'},
									        {value:'0',text:'未检查'},
									        	]" />
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>
				    </div>
					</form>
				
					<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
							<shiro:hasPermission name="yhpc:jcrw:view">
				        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
				        	</shiro:hasPermission>
					       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="reset2()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</div>
					</div>
					</div> 
				</div>
				<table id="yhpc_wdrw_dg"></table> 	
			</div>
		</div>

<shiro:hasPermission name="yhpc:jcrw:add">
flag=1;
</shiro:hasPermission>
</body>
</html>
<script type="text/javascript">
if(flag==0){
	$("#rw").hide();
}
</script>