<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>入库管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/hjbh/rkgl/index.js?v=1.0"></script>
</head>
<body>
   <div id="rkgl_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <input type="text" name="view_rkgl_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '废物名称 '" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchrk()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="resetrk()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="hjbh:rkgl:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addrk()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:rkgl:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updrk()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:rkgl:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="delrk()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:rkgl:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewrk()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>
            </div>
            <div class="pull-right"></div>
         </div>
      </div>
   </div>
   <table id="rkgl_dg"></table>
   <script type="text/javascript">
   var rkdg;
   $(function(){ 
   rkdg= $('#rkgl_dg').datagrid({    
		method: "post",
	    url : ctx+'/hjbh/rkgl/list', 
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
		striped:true,
	    columns:[[    
	        {field:'id',title:'id',checkbox:true,width:50}, 
	        {field :'name',title : '废物名称',align:'center',width : 50},
	        {field:'intime',title:'入库时间',align:'center',width:80,
	        	formatter:function(value){
	        		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	        	}
	        },    
	        {field:'resource',title:'废物来源',align:'center',width:80},    
	        {field:'amount',title:'废物数量',align:'center',width:40},    
	        {field:'containeramount',title:'容器数量',align:'center',width:30},    
	        {field:'containermaterial',title:'容器材质',align:'center',width:40},    
	        {field:'containervolume',title:'容器容量',align:'center',width:30},    
	        {field:'location',title:'废物存在位置',align:'center',width:80},    
	        {field:'ysoperator',title:'废物运送部门经办人',align:'center',width:50},    
	        {field:'ccoperator',title:'废物贮存部门经办人',align:'center',width:50}  
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    	viewrk();
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#rkgl_tb'
		});	
   }); 
   

 //弹窗增加
 function addrk(u) {
 	openDialog("添加入库信息",ctx+"/hjbh/rkgl/create/","800px", "400px","");
 }

 //删除
 function delrk(){
 	var row = rkdg.datagrid('getChecked');
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
 			url:ctx+"/hjbh/rkgl/delete/"+ids,
 			success: function(data){
 				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
 				top.layer.close(index);
 				rkdg.datagrid('reload');
 				rkdg.datagrid('clearChecked');
 			}
 		});
 	});
  
 }

 //弹窗修改
 function updrk(){
 	var row = rkdg.datagrid('getSelected');
 	if(row==null) {
 		layer.msg("请选择一行记录！",{time: 1000});
 		return;
 	}
 	
 	openDialog("修改入库信息",ctx+"/hjbh/rkgl/update/"+row.id,"800px", "400px","");
 	
 }

 //查看
 function viewrk(){
 	var row = rkdg.datagrid('getSelected');
 	if(row==null) {
 		layer.msg("请选择一行记录！",{time: 1000});
 		return;
 	}
 	
 	openDialogView("查看入库信息",ctx+"/hjbh/rkgl/view/"+row.id,"800px", "350px","");
 	
 }

 //创建查询对象并查询
 function searchrk(){
 	var obj=$("#searchFrom").serializeObject();
 	rkdg.datagrid('load',obj); 
 }

 function resetrk(){
 	$("#searchFrom").form("clear");
 	var obj=$("#searchFrom").serializeObject();
 	rkdg.datagrid('load',obj); 
 }

	</script>
</body>
</html>