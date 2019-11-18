<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常检查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/rcjcjl/index.js?v=1.3"></script>
</head>
<body >
<script type="text/javascript">
var type = '${type}';
var usertype='${usertype}';
</script>
<style>.datagrid-cell{line-height:50px}</style>
<!-- 工具栏 -->
<div id="yhpc_rcjcjl_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         	<c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if>         
			<input name="yhpc_jcjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
		    <input name="yhpc_jcjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />	
			<input style="height: 30px;" name="yhpc_jcjl_jcjg" class="easyui-combobox"  data-options="panelHeight:'120', editable:false ,
									prompt:'检查结果',panelHeight:'auto',
									data: [
								        {value:'1',text:'有隐患'},
								        {value:'0',text:'无隐患'}]"/>					        
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
         </div>
      </form>

      <div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="yhpc:jcjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
      		<shiro:hasPermission name="yhpc:jcjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>
<table id="yhpc_rcjcjl_dg"></table> 
</body>
</html>