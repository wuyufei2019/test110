<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>三级安全教育线下培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="aqpx_pxjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="aqpx_sjjy_qyname" name="aqpx_sjjy_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input type="text"  name="name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '姓名'" />	
		<input type="text"  name="idcard" class="easyui-textbox" style="height: 30px;" data-options="prompt: '身份证号'" />		        	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sjjy:pxjl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sjjy:pxjl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sjjy:pxjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sjjy:pxjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sjjy:pxjl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sjjy:pxjl:statistics">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="statistics()" title="统计"><i class="fa fa-bar-chart"></i> 统计</button> 
        	</shiro:hasPermission>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqpx_pxjl_dg"></table> 

<script type="text/javascript">
var dg;
var d;
$(function(){
	dg=$('#aqpx_pxjl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/sjjy/pxjl/list', 
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
              {field:'ID',title:'id',checkbox:true,width:50},
              {field:'qyname',title:'所属企业',sortable:false,width:60},    
              {field:'name',title:'姓名',sortable:false,width:30},
              {field:'sex',title:'性别',sortable:false,width:30,align:'center',
            	  formatter : function(value, view ,index){
                    	if(value=='1') 
                    		return '男';  
                    	else if (value=='2')
                    		return '女';
                    	else return value;
              	  }},    
              {field:'birthday',title:'出生日期',sortable:false,width:40,align:'center',
              		formatter:function(value,row,index){
    					if(value!=null){
    						var datetime = new Date(value);  
    						 return datetime.format('yyyy-MM-dd');   
    					}  
    	            }},
    	      {field:'idcard',title:'身份证号',sortable:false,width:50},
    	      {field:'dep',title:'部门名称',sortable:false,width:50},
              {field:'m1',title:'课程名称',sortable:false,width:50},    
              {field:'m2',title:'培训时间',sortable:false,width:40,align:'center',
            	  formatter:function(value,row,index){
  					if(value!=null){
  						var datetime = new Date(value);  
  						 return datetime.format('yyyy-MM-dd');   
  					}  
  	            }},
              {field:'m7',title:'考试成绩',sortable:false,width:30,align:'center'},
              {field:'m8',title:'是否合格',sortable:false,width:30,align:'center'},
              {field:'state',title:'培训方式',sortable:false,width:30,align:'center',
            	  formatter : function(value, view ,index){
                  	if(value=='1') 
                  		return "<a class='btn-primary btn-outline'>线上</a>";  
                  	else if (value=='2')
                  		return "<a class='btn-warning btn-outline'>线下</a>";
                  	else return value;
            	  }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
         view();
    },
    onLoadSuccess: function(){
        /* $(this).datagrid("autoMergeCells",['qyname']); */
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_pxjl_tb'
	});
	
});

//弹窗增加
function add(u) {
	  openDialog("添加三级教育线下培训记录",ctx+"/aqpx/sjjy/pxjl/create/","800px", "400px","");
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
			url:ctx+"/aqpx/sjjy/pxjl/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
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
	
	openDialog("修改三级教育培训记录",ctx+"/aqpx/sjjy/pxjl/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看三级教育培训记录",ctx+"/aqpx/sjjy/pxjl/view/"+row.id,"800px", "400px","");
	
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

//导出
function fileexport(){
	var param=encodeURIComponent(JSON.stringify($("#searchFrom").serializeObject()));
	url=ctx+"/aqpx/sjjy/pxjl/export2?param="+param;
	window.location.href=url;
}

//统计
function statistics(){
	layer.open({
	    type: 2,  
	    area: ['90%', '90%'],
	    title: '统计分析',
        maxmin: true, 
	    content: ctx+"/aqpx/sjjy/statistics",
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	});
}
</script>
</body>
</html>