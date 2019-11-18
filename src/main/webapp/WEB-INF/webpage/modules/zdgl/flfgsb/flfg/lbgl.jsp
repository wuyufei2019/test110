<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>类别管理</title>
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
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updlb()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="dellb()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>

	</div>
	</div> 
	   
</div>
<table id="dg"></table>


<script type="text/javascript">
var dg;
var d;
var menuId=0;
var parentId;
$(function(){   
	dg=$('#dg').treegrid({  
	method: "post",
    url:ctx+'/zdgl/flfg/treelist',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField:'text',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	scrollbarSize:0,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true,width:100},    
        {field:'text',title:'类别',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb',
    dataPlain: true,
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 menuId=rowdata.id;
             	 parentId=rowdata.id;
             	 dg.datagrid('reload',{pid:menuId});
                 /* upd(); */
     }
	});
	
});

//弹窗增加
function add() {
	var row = dg.treegrid('getSelected');
	if(row){
		parentPermId=row.id;
	}
	openDialog("添加类别分类",ctx+"/zdgl/flfg/createLb/","85%", "80%","");
}

//删除类别
function dellb(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zdgl/flfg/deleteLb/"+row.id,
			success: function(data){
				if(data=='success')
					layer.alert('删除成功！', {offset: 'rb',shade: 0,time: 2000}); 
		    	else
		    		layer.alert('删除失败！', {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.treegrid('reload');
				dg.datagrid('reload');
				dg.treegrid('clearChecked');
			}
		});
	});
}

//修改类别
function updlb(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一条记录！",{time: 1000});
		return;
	}else{
		parentPermId=row.pid;
	}
	openDialog("修改类别分类",ctx+"/zdgl/flfg/updateLb/"+row.id,"85%", "80%","");
}

var nowIcon;
var icon_dlg;
</script>
</body>
</html>