<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/yhpcjl/dsjindex.js?v=1.0"></script>
	<script type="text/javascript">
		var ctx='${ctx}';
	</script>
</head>
<body >
	<div id="yhpc_yhpcjl_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
		<div class="form-group">
		<input type="text" id="yhpc_yhpcjl_qyname" name="yhpc_yhpcjl_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	        
		<input type="text"  name="wghgl_xjjl_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>			    
	    </div>
		</form>
	
		<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
	        	<span id="divper2">
				</span>
		       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			
				</div>
			<div class="pull-right">
			</div>
		</div>
		</div> 
	</div>
	<table id="yhpc_yhpcjl_dg"></table> 
	<div id="yhpc_yhpcjl_tb" style="padding:5px;height:auto">
		<div class="row">
		<div class="col-sm-12">
		</div>
		</div>
	</div>
	<table id="yhpc_yhpcjl_dg"></table>
</body>
</html>