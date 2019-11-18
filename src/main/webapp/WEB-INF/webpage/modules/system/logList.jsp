<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日志管理</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
		<div class="form-group">
	    	<span>描述：</span><input type="text" name="filter_LIKES_description" class="form-control"  />
	    </div>
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
 			<shiro:hasPermission name="sys:log:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:log:exportExcel">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="export()" title="导出"><i class="fa fa-file-text-o"></i> 导出</button> 
			</shiro:hasPermission>
		</div>
		<div class="pull-right">
			
		</div>
	</div>
	</div> 
	   
</div>


<table id="dg"></table> 

<script type="text/javascript">
var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "get",
    url:'${ctx}/system/log/json', 
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
	scrollbarSize:0,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'id',title:'id',checkbox:true,align:'center'},    
       // {field:'userid',title:'帐号',width:50},    
        //{field:'username',title:'昵称',width:50},
        {field:'type',title:'类别',width:30 },
        {field:'browser',title:'浏览器',width:40},
        {field:'ip',title:'ip',width:40},
        {field:'createDate',title:'操作时间',width:50,  
                     formatter:function(value,row,index){
                     		if(value!=null){
                         var datetime = new Date(value);  
                         return datetime.format("yyyy-MM-dd hh:mm:ss");  }  
        	}  
        },
         {field:'description',title:'操作描述',width:100 },
         {field:'classinfo',title:'类名',width:100 },
         {field:'method',title:'方法名',width:30 },
         {field:'requestUri',title:'URL',width:50} 
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                
    },
    toolbar:'#tb'
	});
	
});


//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids=[];
	for(var i=0;i<row.length;i++){
		ids.push(row[i].id);
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'post',
			contentType:"application/json",               
			url:"${ctx}/system/log/delete",
			data:JSON.stringify(ids),
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
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
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}
 
</script>
</body>
</html>