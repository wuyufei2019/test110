<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用类型管理</title>
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
			<shiro:hasPermission name="aqsc:fylx:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqsc:fylx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqsc:fylx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
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
var parentPermId=0;
$(function(){   
	dg=$('#dg').treegrid({  
	method: "post",
    url:'${ctx}/aqsc/fylx/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField : 'm1',
	parentField : 'pid',
	iconCls: 'm2',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	scrollbarSize:0,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true,width:100},    
        {field:'m1',title:'费用类型',width:100},
        {field:'m4',title:'备注',width:80},
        {field:'fid',title:'操作',width:200,formatter : function(value, row, index) {
        	var html="<a href='#' onclick='addNext(\""+row.id+"\")' class='btn btn-primary btn-xs' ><i class='fa fa-plus'></i> 添加下级</a>";
			return html;
		}
        },
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
	openDialog("添加费用类型","${ctx}"+"/aqsc/fylx/create","800px", "300px","");
	
}
//费用类型总览
function openAll(){
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '绑定企业',
        maxmin: true, 
	    content: "${ctx}/aqsc/fylx/allindex" ,
	    btn: ['关闭'],
	    yes: function(index, layero){	
	    	layer.close(index);
			},
	cancel: function(index){}
	}); 


}

//增加下级费用类型
function addNext(pid) {
	
	parentPermId=pid;
	openDialog("添加费用类型","${ctx}"+"/aqsc/fylx/create","800px", "300px","");
	
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
			url:"${ctx}/aqsc/fylx/delete/"+row.id,
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
	
	openDialog("修改费用类型","${ctx}"+"/aqsc/fylx/update/"+row.id,"800px", "300px","");
	
}
//查看费用类型区域
function view(id) {
	if(id==null){
		var row = dg.datagrid('getSelected');
		if(row==null) {
			layer.msg("请选择一行记录！",{time: 1000});
			return;
		}else{
			id=row.id;
		}
	}
	
	 	layer.open({
		    type: 2,  
		    area: ['100%', '100%'],
		    title: '查看费用类型',
	        maxmin: true, 
		    content: "${ctx}"+"/aqsc/fylx/view/"+id,
		    btn: ['关闭'],
		    yes: function(index, layero){	
		    	layer.close(index);
				},
		cancel: function(index){}
		}); 
	 	
	} 
	
var nowIcon;
var icon_dlg;
</script>
</body>
</html>