<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
</head>
<body  >
<div class="easyui-layout" style="height:100%; ">
	<div data-options="region:'west',split:true,border:false,title:'菜单列表'"  style="width: 300px">
		<table id="menuDg"></table>
    </div>  
    <div data-options="region:'center',split:true,border:false,title:'权限列表'">
    	<shiro:hasRole name="admin">
    	<div id="tg_tb" style="padding:5px;height:auto">
		    <div>
		    	<shiro:hasPermission name="sys:perm:add">
		    	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
		    	</shiro:hasPermission>
		        <shiro:hasPermission name="sys:perm:update">
  				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="sys:perm:delete">
		        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
		        </shiro:hasPermission>
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()" title="初始化"><i class="fa fa-cog"></i>初始化</button> 
		    </div>
		</div>
		</shiro:hasRole>
    	<table id="dg"></table>
    </div>   
</div>
	 
    
<div id="dlg"></div> 
<div id="icon_dlg"></div>  

<script type="text/javascript">
var dg;
var d;
var menuDg;
var menuId=0;
var parentId;
$(function(){   
	menuDg=$('#menuDg').treegrid({  
	method: "get",
    url:'${ctx}/system/permission/menu/treejson', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField:'name',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	scrollbarSize:0,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true},    
        {field:'name',title:'名称',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    dataPlain: true,
    onClickRow:function(rowData){
    	menuId=rowData.id;
    	parentId=rowData.id;
    	dg.datagrid('reload',{pid:menuId});
    }
	});
	
	dg=$('#dg').datagrid({   
	method: "get",
	url:'${ctx}/system/permission/ope/json',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField:'name',
	parentField : 'pid',
	iconCls: 'icon',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true,width:100},    
        {field:'name',title:'名称',width:100},
        {field:'url',title:'访问路径',width:100},
        {field:'permCode',title:'权限编码',width:100},
        {field:'sort',title:'排序'},
        {field:'description',title:'描述',width:100}
    ]],
    toolbar:'#tg_tb',
    dataPlain: true,
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 upd();
             }
	});
	
});

//弹窗增加
function add() {
	var row = menuDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请先选择菜单！",{time: 1000});
		return;
	}
	openDialog("添加权限","${ctx}/system/permission/create?pid="+parentId,"500px", "400px","");
}

//初始化权限
function init() {
/* 	if(dg.datagrid("getData").total!=0){
		layer.msg("已有权限！");
		return;
	} */
	var row = menuDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请先选择菜单！",{time: 1000});
		return;
	} 
	openDialog("初始化权限","${ctx}/system/permission/initpermission?id="+parentId,"500px", "300px","");
}

//删除
function del(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:"${ctx}/system/permission/delete/"+row.id,
			success: function(data){
				if(data=='success')
					layer.alert('删除成功！', {offset: 'rb',shade: 0,time: 2000}); 
		    	else
		    		layer.alert('删除失败！', {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//修改
function upd(){
	
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一条记录！",{time: 1000});
		return;
	}
	openDialog("修改权限","${ctx}/system/permission/update/"+row.id,"500px", "400px","");

}
</script>
</body>
</html>