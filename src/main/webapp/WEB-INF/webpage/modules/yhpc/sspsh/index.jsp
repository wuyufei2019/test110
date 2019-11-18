<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>随手拍审核记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/sspsh/index.js?v=1.2"></script>
</head>
<body >
	<div id="yhpc_sspsh_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
			<div class="form-group">
				<c:if test="${type != '1'}">
					<input type="text" id="yhpc_sspsh_qyname" name="yhpc_sspsh_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
				</c:if>
				<input name="yhpc_sspsh_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '发现时间起'" />
			    <input name="yhpc_sspsh_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '发现时间止'" />   
			    <input type="text" name="shzt" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '审核状态',data: [
									         {value:'0',text:'待审核'},
									         {value:'1',text:'已审核'}] "/>
				<input type="text" name="yhdj" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患等级',data: [
											 {value:'0',text:'无隐患'},
									         {value:'1',text:'一级'},
									         {value:'2',text:'二级'},
									         {value:'3',text:'三级'},
									         {value:'4',text:'四级'}] "/>  
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  	   			
		    </div>
		</form>
	
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<span id="divper">
						<shiro:hasPermission name="yhpc:sspsh:add">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:sspsh:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:sspsh:delete">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			        	</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:sspsh:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        </span>
				    <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				</div>
			</div>
		</div> 
	</div>

	<table id="yhpc_sspsh_dg"></table> 
<script type="text/javascript">
var type = '${type}';
var sh = '0';
</script>
<shiro:hasPermission name="yhpc:sspsh:sh">
<script type="text/javascript">
sh = '1';
</script>
</shiro:hasPermission>
</body>
</html>