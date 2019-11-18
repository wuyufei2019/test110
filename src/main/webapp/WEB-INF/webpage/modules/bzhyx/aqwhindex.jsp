<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全文化</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/bzhyx/aqwhindex.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
		<div class="form-group">
			<input type="text" id="qyname" name="qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
			<input name="starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '起始时间'" />
	    	<input name="finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '结束时间'" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
	    </div>
	</form>

	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
		       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		</div>
	</div> 
	   
</div>

<table id="dg"></table>

<script type="text/javascript">

</script>
</body>
</html>