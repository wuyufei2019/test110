<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	</script>
</head>
<body class="gray-bg">
	<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:perm:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:perm:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:perm:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:perm:add">
		    	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addApp()" title="添加APP菜单"><i class="fa fa-plus"></i> 添加APP菜单</button>
		    </shiro:hasPermission>
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>

	</div>
	</div> 
	   
</div>
<table id="dg"></table>


<script type="text/javascript">
var dg;
var d;
var permissionDg;
var parentPermId;
$(function(){   
	dg=$('#dg').treegrid({  
	method: "post",
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
        {field:'id',title:'id',hidden:true,width:100},    
        {field:'name',title:'名称',width:100},
        {field:'href',title:'资源路径',width:100},
        {field:'sort',title:'排序'},
        {field:'description',title:'描述',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb',
    dataPlain: true,
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 upd();
     }
	});
	
});

//弹窗增加
function add() {
	parentPermId=0;
	var row = dg.treegrid('getSelected');
	if(row){
		parentPermId=row.id;
	}
	openDialog("添加菜单","${ctx}"+"/system/permission/menu/create?type=F","800px", "500px","");
}

//弹窗增加
function addApp() {
	parentPermId=0;
	var row = dg.treegrid('getSelected');
	if(row){
		parentPermId=row.id;
	}
	openDialog("添加菜单","${ctx}/system/permission/menu/create?type=AF","800px", "500px","");
}

//初始化权限
function init() {
	var row = dg.treegrid('getSelected');
	if(row==null) {
		layer.msg("请先选择菜单！",{time: 1000});
		return;
	} 
	openDialog("初始化权限","${ctx}/system/permission/initpermission?id="+row.id,"500px", "300px","");
}

//删除
function del(){
	var row = dg.treegrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:"${ctx}/system/permission/deletemenu/"+row.id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.treegrid('reload');
			}
		});
	});

}

//修改
function upd(){
	parentPermId=0;
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改菜单","${ctx}"+"/system/permission/menu/update/"+row.id,"800px", "500px","");
	 
}

var nowIcon;
var icon_dlg;
</script>
</body>
</html>