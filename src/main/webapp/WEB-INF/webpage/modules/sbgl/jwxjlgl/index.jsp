<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检维修记录管理信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="sbgl_jwxjlgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
	  <c:if test="${ type eq '2' }">
        <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
      </c:if>
      <input type="text" name="sbname" style="height:30px"  class="easyui-textbox" data-options="prompt: '设备名称'"/>
      <input id="" name="sybm" class="easyui-combobox" style="height: 30px;" 
                   data-options="prompt: '使用部门',editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"/>
      <input id="" name="jwxr" class="easyui-combobox" style="height: 30px;" 
      			   data-options="prompt: '检维修人',panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="sbgl:jwx:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="sbgl:jwx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:jwx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:jwx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:jwx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button>
        	</shiro:hasPermission>
         
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="sbgl_jwxjlgl_dg"></table> 
<script type="text/javascript">
var type='${type}';
var usertype='${usertype}';
var dg;
$(function(){
	dg=$('#sbgl_jwxjlgl_dg').datagrid({    
	method: "post",
    url:ctx+'/sbgl/jwxjlgl/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
              {field:'id',title:'id',checkbox:true,width:50,align:'center'},  
              {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
              {field:'sbname',title:'设备名称',width:80,align:'center'},  
              {field:'sbxh',title:'设备型号',width:80,align:'center'},  
              {field:'sbbh',title:'设备编号',width:80,align:'center'},  
              {field:'bm',title:'使用部门',width:70,align:'center'},  
              {field:'m2',title:'故障现象',width:130,align:'center'},  
              {field:'m13',title:'检维修人',width:60,align:'center'},  
              {field:'m17',title:'检维修时间',sortable:true,width:70,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd');
              }}
            	  ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'sbname' ]);
		if(type=="1"){
		 	$(this).datagrid("hideColumn",['qyname']);
		}else{
		 	$(this).datagrid("showColumn",['qyname']);
		 	$(this).datagrid("autoMergeCells",['qyname']);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbgl_jwxjlgl_tb'
	});
	
});
//弹窗增加
function add(u) {
	openDialog("添加检维修记录管理信息",ctx+"/sbgl/jwxjlgl/create/","800px", "600px","");
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
			url:ctx+"/sbgl/jwxjlgl/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改检维修记录管理信息",ctx+"/sbgl/jwxjlgl/update/"+row.id,"800px", "600px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看检维修记录管理信息",ctx+"/sbgl/jwxjlgl/view/"+row.id,"800px", "600px","");
	
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

//导出word
function fileexportword() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sbgl/jwxjlgl/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
</script>

</body>
</html>