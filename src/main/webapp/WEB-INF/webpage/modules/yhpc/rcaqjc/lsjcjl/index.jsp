<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时检查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/lsjcjl/index.js?v=1.4"></script>
</head>
<body >
<script type="text/javascript">
var usertype='${usertype}';
</script>
<!-- 工具栏 -->
<div id="yhpc_lsjcjl_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <c:if test="${usertype != '1'}">
               <input type="text" id="yhpc_jcjl_qyname" name="yhpc_jcjl_qyname" class="easyui-combobox" style="height: 30px;"
                  data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/qynmlist',prompt: '企业名称' " />
               <input type="text" name="yhpc_jcjd_type" class="easyui-combobox" style="height: 30px;"
                  data-options="panelHeight:'auto', prompt: '检查类型', data:[{value:'1',text:'安监检查'},{value:'2',text:'企业自查'}]" />
            </c:if>
            <input type="text" name="yhpc_jcjd_name" class="easyui-combobox" style="height: 30px;"
               data-options="panelHeight:'auto', prompt: '检查进度', data:[{value:'0',text:'未复查'},{value:'1',text:'已复查'}]" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="yhpc:lsjc:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加检查</button>
			</shiro:hasPermission> 
			<shiro:hasPermission name="yhpc:lsjc:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:lsjc:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="yhpc:lsjc:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportword()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		</div>
	</div>
	</div> 
</div>
<table id="yhpc_lsjcjl_dg"></table> 
</body>
</html>