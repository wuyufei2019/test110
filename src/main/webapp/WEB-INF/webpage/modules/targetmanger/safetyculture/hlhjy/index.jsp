<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>合理化建议信息</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/safetyculture/hlhjy/qy_index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_hlhjy_tb" style="padding:5px;height:auto">
	<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
     <c:if test="${ usertype eq 'isbloc' }">
        <input type="text"  name="target_hlhjy_qyname" style="height:30px" class="easyui-textbox"data-options="width:200,prompt: '企业名称'" />
    </c:if>
      <input type="text"  name="target_hlhjy_theme" style="height:30px" class="easyui-textbox" data-options="prompt: '主题名称'"/>
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="target:hlhjy:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="target:hlhjy:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:hlhjysh:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updReview()" title="修改"><i class="fa fa-file-text-o"></i> 修改审核内容</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:hlhjy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:hlhjysh:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="delReview()" title="删除"><i class="fa fa-trash-o"></i> 删除审核内容</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:hlhjy:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看建议内容</button> 
        	</shiro:hasPermission>
         
        <%-- 	<shiro:hasPermission name="target:hlhjy:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="target_hlhjy_dg"></table> 
<shiro:hasPermission name="target:hlhjysh:add">
<script>sh = 1;</script>
</shiro:hasPermission>
<script type="text/javascript">
var qyid= '${qyid}';
//authorizedepartment
var authorize='${authorize}';
var usertype='${usertype}';
var sh;
//弹窗增加
function add(u) {
	openDialog("添加合理化建议信息",ctx+"/target/hlhjy/create/","800px", "400px","");
}
function addReview(id) {
	openDialog("添加合理化建议审核信息",ctx+"/target/hlhjysh/create/"+id,"800px", "400px","");
}

//删除
function deleteinfo(url){
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
			url:ctx+url+ids,
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
//删除
function del(){
	deleteinfo("/target/hlhjy/delete/");
}
//删除审核内容
function delReview(){
	deleteinfo("/target/hlhjysh/delete/");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改合理化建议信息",ctx+"/target/hlhjy/update/"+row.id,"800px", "400px","");
}
//弹窗修改审核内容
function updReview(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}else if(!row.result){
		layer.msg("您还未添加审核信息，不能修改。");
		return ;
	}
	openDialog("修改审核信息",ctx+"/target/hlhjysh/update/"+row.id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/target/hlhjy/view/"+row.id,"合理化建议信息查看");
}
//查看
function viewReview(id){
	openDialog("查看审核信息",ctx+"/target/hlhjysh/view/"+id,"800px", "400px","");
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

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	var Columns=dg.datagrid("options").columns[0];
	window.exdata=[];
	for(var i=0;i<Columns.length;i++){
		if(Columns[i].field.toUpperCase()!="ID")
		window.exdata.push({colval:Columns[i].field, coltext:Columns[i].title});
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/target/hlhjy/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}</script>

</body>
</html>