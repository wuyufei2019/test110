<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgfpdjh/pdindex.js?v=1.3"></script>
	<script type="text/javascript">
		var usertype = '${usertype}';
		var id1 = '${id1}';
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_xgfpd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${usertype != '1'}">
			<input type="text" id="zyaqgl_xgfpd_qy_name" name="zyaqgl_xgfpd_qy_name" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		
		<input name="zyaqgl_xgfpd_m12" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'auto',editable:false ,prompt: '评定等级' ,
									data: [
									{value:'优秀',text:'优秀'},
									{value:'良好',text:'良好'},
									{value:'中等',text:'中等'},
									{value:'差',text:'差'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
		    <shiro:hasPermission name="zyaqgl:xgfpdjh:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        </span>
		</div>
	</div>
	</div> 
	   
</div>
<table id="zyaqgl_xgfpd_dg"></table> 
<script>var role = '1';</script>
<shiro:hasPermission name="zyaqgl:xgfpd:sh">
<script>role = '2';</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:xgfpd:sp">
<script>role = '3';</script>
</shiro:hasPermission>
<shiro:hasPermission name="zyaqgl:xgfpd:pd">
<script>role = '4';</script>
</shiro:hasPermission>
</body>
</html>