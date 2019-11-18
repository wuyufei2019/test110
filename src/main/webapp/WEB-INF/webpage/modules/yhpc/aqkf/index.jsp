<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/yhpc/aqkf/index.js?v=1.0"></script>
<title>安全十二分</title>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;" >
       	        <input type="text" id="yhpc_aqkf_cx_m1" name="yhpc_aqkf_cx_m1" style="height:30px" class="easyui-textbox easyui-validatebox" data-options="width:150,prompt: '员工姓名'"/>
		        <input id="M2" name="yhpc_aqkf_cx_m2" class="easyui-combobox" style="height: 30px;" data-options="editable:false,panelHeight:180, valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson', prompt:'所属部门' "/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
				       	<shiro:hasPermission name="yhpc:aqkf:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				       	</shiro:hasPermission>
				        <shiro:hasPermission name="yhpc:aqkf:update">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				        </shiro:hasPermission>
				       	<shiro:hasPermission name="yhpc:aqkf:delete">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
				        </shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
				</div>
			</div>
        </div>
        
  </div>
<table id="dg"></table> 
<script type="text/javascript">
   var f='${sys}';
</script> 
</body>
</html>