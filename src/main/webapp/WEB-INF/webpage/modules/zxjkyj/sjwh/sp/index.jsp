<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>监控摄像头数据</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div id="bis_spjk_tb" style="padding:5px;height:auto">
    <div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
    	<shiro:hasPermission name="bis:spjk:add">
    		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="bis:spjk:delete">
         	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
     	</shiro:hasPermission>
     	<shiro:hasPermission name="bis:spjk:update">
        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
     	</shiro:hasPermission>
     	<shiro:hasPermission name="bis:spjk:reset">
        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="resetVideo()" title="重新生成视频流"><i class="glyphicon glyphicon-repeat"></i> 重新生成视频流</button>
     	</shiro:hasPermission>
     	<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
    	</div>
		<div class="pull-right">
			
		</div>
	</div>
	</div> 
        
</div>
<table id="bis_spjk_dg"></table> 
<div id="bis_spjk_dlg"></div>  

<script type="text/javascript">
var dg;
var d;

$(function(){   
	dg=$('#bis_spjk_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/spjk/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	checkOnSelect:false,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:0,
	singleSelect:true,
    columns:[[    
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'qyname',title:'企业名称',sortable:false,width:100},    
        {field:'name',title:'名称',sortable:false,width:100,align:'center'},    
        {field:'ip',title:'设备ip',sortable:false,width:100,align:'center'},
        {field:'port',title:'端口',sortable:false,width:40,align:'center' },
        {field:'username',title:'登录名',sortable:false,width:60,align:'center'},
        {field:'password',title:'密码',sortable:false,width:60,align:'center' },
        {field:'url',title:'视频流url',sortable:false,width:200,align:'center' },
        {field:'beizhu',title:'操作',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index){
   		 		return "<a  class='btn btn-info btn-xs' onclick='showlive("+ row.id + ")'>播放</a>";} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	upd();
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#bis_spjk_tb'
	});
});



//增加
function add() {
	openDialog("添加信息",ctx+"/bis/spjk/create","600px", "400px","");
	 
	
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
			url:ctx+"/bis/spjk/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
	
}

//修改
function upd(){
	
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改信息",ctx+"/bis/spjk/update/"+row.id,"600px", "400px","");
}

//重新生成视频流
function resetVideo(){
	top.layer.confirm('确定要执行吗？', {icon: 3, title:'提示'}, function(index){
		top.layer.close(index);
		var loding = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
		$.ajax({
			type:'get',
			url:ctx+'/bis/spjk/reset',
	        success:function(data){
	        	layer.close(loding);//关闭加载层
	        	layer.msg(data,{time: 1000});
	        },
	        error:function(){
	        	layer.close(loding);//关闭加载层
	        	layer.msg("操作失败！",{time: 1000});
	        }
		});
	});
}


//播放视频
function showlive(id){
	
	openDialogView("查看直播",ctx+"/zxjkyj/spjk/showsp/"+id,"600px", "400px","");
}
</script>
</body>
</html>