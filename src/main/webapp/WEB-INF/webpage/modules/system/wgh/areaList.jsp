<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>区域网格管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/webpage/include/treetable.jsp" %>
	<script type="text/javascript">

	</script>
</head>
<body class="gray-bg">
	<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:xzqy:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:xzqy:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:xzqy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			</shiro:hasPermission>
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="view()" title="查看网格地图"><i class="fa fa-search-plus"></i> 查看网格地图</button>
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openAll()" title="查看网格地图"><i class="fa fa-search-plus"></i> 网格总览</button>
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
    url:'${ctx}/system/admin/xzqy/json', 
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
        {field:'m1',title:'网格名称',width:100},
        {field:'m4',title:'备注',width:80},
        {field:'fid',title:'操作',width:200,formatter : function(value, row, index) {
        	var html="<a href='#' onclick='addNext(\""+row.id+"\")' class='btn btn-primary btn-xs' ><i class='fa fa-plus'></i> 添加下级网格</a>";
    		if(row.mappoint!=null&&row.mappoint!="")
				html+="<a style='margin-left:10px' href='#' onclick='addMapArea("+row.id+")' class='btn btn-success btn-xs' ><i class='fa fa-file-text-o'></i> 重绘地图区域</a>";	
			else
				html+="<a style='margin-left:10px' href='#' onclick='addMapArea("+row.id+")' class='btn btn-info btn-xs' ><i class='fa fa-plus'></i> 勾画地图区域</a>";
			html+="<a href='#'style='margin-left:10px' onclick='bindQy(\""+row.code+"\")' class='btn btn-danger btn-xs' ><i class='fa fa-plus'></i> 绑定企业</a>";
			html+="<a href='#'style='margin-left:10px' onclick='view("+row.id+")' class='btn btn-warning btn-xs' ><i class='fa fa-map'></i> 查看网格</a>";
			html+="<a href='#'style='margin-left:10px' onclick='viewUser(\""+row.code+"\")' class='btn btn-success btn-xs' ><i class='fa fa-search-plus'></i> 查看网格员</a>";
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
	openDialog("添加网格","${ctx}"+"/system/admin/xzqy/create","800px", "500px","");
	
}
//网格总览
function openAll(){
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '绑定企业',
        maxmin: true, 
	    content: "${ctx}/system/admin/xzqy/allindex" ,
	    btn: ['关闭'],
	    yes: function(index, layero){	
	    	layer.close(index);
			},
	cancel: function(index){}
	}); 


}
//弹出企业选择框
function bindQy(code) {
	layer.open({
	    type: 2,  
	    area: ['850px', '500px'],
	    title: '绑定企业',
        maxmin: false, 
	    content: "${ctx}"+"/system/admin/xzqy/bindqy/"+code ,
	    btn: ['绑定', '关闭'],
	    yes: function(index, layero){
	    	//$.jBox.tip("正在提交，请稍等...",'loading',{opacity:1});
	    	var iframeWin = layero.find('iframe')[0];
	    	var ids=iframeWin.contentWindow.getqyids();
	    	if(ids==""){
	    		layer.msg("未绑定任何企业");
				return;
	    		}
	    	var index2=layer.load();
	    	$.ajax({
	    		type:"post",
	    		url:"${ctx}"+"/system/admin/xzqy/updatexzqy/" ,
	    		data:{ids:ids,xzqy:code},
	    		success:function(data){
	    			if(data=="success"){
	    			parent.layer.open({
						icon : 1,
						title : '提示',
						offset : 'rb',
						content : '保存成功！',
						shade : 0,
						time : 2000
					});}
	    			
	    			else{
	    				parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '保存失败！',
							shade : 0,
							time : 2000
						});
	    			}
	    			layer.close(index);//关闭对话框。
	    			layer.close(index2);//关闭load层
	    			
	    		}
	    	});
	    },
	cancel: function(index){}
	}); 	
}
 

//添加网格区域
function addMapArea(id) {
 	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '勾画网格区域',
        maxmin: true, 
	    content: '${ctx}'+'/system/admin/xzqy/mindex/'+id ,
	    btn: ['关闭'],
	    yes: function(index, layero){	
	    	layer.close(index);
			},
	}); 	
} 

//增加下级网格
function addNext(pid) {
	
	parentPermId=pid;
	openDialog("添加网格","${ctx}"+"/system/admin/xzqy/create","800px", "500px","");
	
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
			url:"${ctx}/system/admin/xzqy/delete/"+row.id,
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
	
	openDialog("修改网格","${ctx}"+"/system/admin/xzqy/update/"+row.id,"800px", "500px","");
	
}
//查看网格区域
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
		    title: '查看网格',
	        maxmin: true, 
		    content: "${ctx}"+"/system/admin/xzqy/view/"+id,
		    btn: ['关闭'],
		    yes: function(index, layero){	
		    	layer.close(index);
				},
		cancel: function(index){}
		}); 
	 	
	} 
	
function viewUser(code) {
	
	openDialogView("查看网格成员","${ctx}/system/admin/xzqy/viewWgUser?code="+code,"800px", "500px");
}

var nowIcon;
var icon_dlg;
</script>
</body>
</html>