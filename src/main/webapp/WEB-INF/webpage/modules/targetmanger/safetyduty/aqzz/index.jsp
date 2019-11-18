<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全职责信息</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/safetyduty/aqzz/qy_index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_aqzz_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
     <c:if test="${ usertype eq 'isbloc' }">
        <input type="text"  name="target_aqzz_qyname" style="height:30px" class="easyui-textbox"data-options="width:200,prompt: '企业名称'" />
    </c:if>
      <input type="text"  name="target_aqzz_gwname" style="height:30px" class="easyui-textbox" data-options="prompt: '岗位名称'"/>
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<%--  	<shiro:hasPermission name="target:aqzz:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission>  --%>
		<%-- 	<shiro:hasPermission name="target:aqzz:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修订</button> 
			</shiro:hasPermission> --%>
		<%-- 	<shiro:hasPermission name="target:aqzz:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="target:aqzz:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
         
        <%-- 	<shiro:hasPermission name="target:aqzz:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="target_aqzz_dg"></table> 
<script type="text/javascript">
var ajqyid='${ajqyid}';
var usertype='${usertype}';
var parid;
function add(u) {
	parid=u;
	openDialog("添加安全职责信息",ctx+"/target/aqzz/create/","800px", "400px","");
}

//弹窗修改
function upd(u){
	openDialog("修订安全职责信息",ctx+"/target/aqzz/update/"+u,"800px", "400px","");
}
//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/target/aqzz/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}



//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.id){
      	window.open(ctx + "/target/aqzz/view/" + row.id, "查看安全职责信息");
	}else{
		layer.msg("还未添加责任书，无法预览");
	}
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

</script>

</body>
</html>