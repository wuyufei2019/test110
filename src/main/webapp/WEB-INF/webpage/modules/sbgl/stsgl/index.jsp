<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三同时管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;" >
       	        <input type="text" id="view_projectname" name="view_projectname" style="height: 30px;" class="easyui-textbox" data-options="prompt: '项目名称'"/>
       	         <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	        	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
				       	<shiro:hasPermission name="sbgl:stsgl:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				       	</shiro:hasPermission>
				        <shiro:hasPermission name="sbgl:stsgl:update">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				        </shiro:hasPermission>
				       	<shiro:hasPermission name="sbgl:stsgl:delete">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
				        </shiro:hasPermission>
				        <shiro:hasPermission name="sbgl:stsgl:view">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
			        	</shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
				</div>
			</div>
        </div>
  </div>
<table id="dg"></table> 
  <div id="select_file" style="display:none;height:100%">
      <button style="margin:5px" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addfile()" title="添加">
         <i class="fa fa-plus"></i> 添加
      </button>
   <table id="file_dg"></table>
   </div>
<script type="text/javascript">
var dg;//项目datagrid
var d;//附件datagrid
var Global_Id;//全局变量id
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/sbgl/stsgl/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'ID',title:'ID',checkbox:true,width:50,align:'center'},    
        {field:'projectname',title:'项目名称',sortable:false,width:100},    
        {field:'projectproduce',title:'项目简介',sortable:false,width:200},    
        {field:'approvedept',title:'批准部门',sortable:false,width:100,align:'center'},
        {field:'approvetime',title:'批准日期',sortable:true,width:100,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
		}},
		{field : 'operation',title : '操作',width : 50,
			formatter : function(value, row) {
				return "<a class='btn btn-danger btn-xs' onclick='updateFile(" + row.id + ")'>修改附件信息</a>";
			}
		}
    ]],
    onDblClickRow: function (){
    	view();
    },
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});



//添加
function add() {
	openDialog("添加信息",ctx+'/sbgl/stsgl/create',"850px","450px","");
}

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改信息",ctx+'/sbgl/stsgl/update/'+row.ID,"800px","400px","");
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/sbgl/stsgl/delete/"+ids,
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
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看三同时管理信息",ctx+'/sbgl/stsgl/view/'+row.ID,"75%","60%","");
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}



//----------------------附件操作----------------------------

//修改附件信息
function updateFile(id) {
	Global_Id=id;
	layer.open({
		type: 1,  
		area: ['700px', '400px'],
		title:'修改风险活动',
		content: $("#select_file"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	    d =$('#file_dg').datagrid({
	    	    	url :ctx+"/sbgl/stsgl/filelist/" + id,
	    	    	fitColumns : true,
					border : true,
					striped : true,
					rownumbers : true,
					nowrap : false,
					idField : 'id',
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [ {
						field : 'type',
						title : '文件类型',
						sortable : false,
						width : 100,
						align : 'center',
						formatter: function(value){
							var type;
							if(value==1){
								type = "立项审批文件";
							}else if(value==2){
								type ="可行性研究报告";
							}else if(value==3){
								type ="预评价报告";
							}else if(value==4){
								type ="安全设施设计";
							}else if(value==5){
								type ="项目试生产方案";
							}else if(value==6){
								type ="安全设施竣工验收";
							}else if(value==7){
								type ="设计、施工、监理单位的相关资质";
							}else{
								type ="其他材料";
							}
							return type;
						}
					}, {
						field : 'contjson',
						title : '文件基本信息',
						sortable : false,
						width : 200,
						formatter: function(value){
							return value.replace("}","").replace("{","").replace(/\"/g,"");	
						}
					}, {
						field : 'url',
						title : '文件名称',
						sortable : false,
						width : 100,
						align : 'center',
						formatter: function(value){
							var html="";
							if(value){
			        			var sp = value.split("||");
			        			html="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+sp[0]+"'> "+sp[1]+"</a>"; 
			        		}
			        		return html; 
						}
					}, {
						field : 'operation',
						title : '操作',
						sortable : false,
						width : 50,
						align : 'center',
						formatter: function(value,row){
							var html="";
			        		html="<a class='btn btn-xs fa fa-trash-o btn-danger' target='_blank' onclick='deleterow(" + row.id + ")'>删除</a>"; 
			        		return html; 
						}
					}
					] ],
					checkOnSelect : false,
					selectOnCheck : false
	    	    });
	      },
		cancel: function(index){ 
		}
	}); 
}

//删除附件
function deleterow(id) {
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/sbgl/stsgl/deletefile/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				d.datagrid('reload');
			}
		});
	});
}
//添加附件
function addfile() {
	openDialog("添加附件", ctx + "/sbgl/stsgl/addfile/", "800px", "400px", "");
}

</script>
</body>
</html>