<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检维修信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="sbgl_jwx_tb" style="padding:5px;height:auto">
	<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
      <c:if test="${ type eq '2' }">
          <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
      </c:if>
      <input type="text" name="view_sbname" style="height:30px"  class="easyui-textbox" data-options="prompt: '设备名称'"/>
      <input type="text" name="view_sbtype" style="height:30px;" class="easyui-combobox"  
                   data-options="editable:false,prompt: '设备类别',panelHeight:'auto',valueField:'value', textField:'text',data:[{value:'tzsb',text:'特种设备'},
                   {value:'scsb',text:'生产设备'},{value:'aqss',text:'安全设施'}]"/>
      <input type="text" name="view_type" style="height:30px;" class="easyui-combobox" 
                   data-options="editable:false,prompt: '检修类别',panelHeight:'auto',valueField:'value', textField:'text',data:[{value:'1',text:'计划维修'},
                   {value:'2',text:'故障维修'}]"/>
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="sbgl:jwx:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="sbgl:jwx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:jwx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:jwx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
         
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="sbgl_jwx_dg"></table> 
<script type="text/javascript">
var qyid = '${qyid}';
var type='${type}';
var usertype='${usertype}';
var dg;
$(function(){
	dg=$('#sbgl_jwx_dg').datagrid({    
	method: "post",
    url:ctx+'/sbgl/jwx/list?qyid='+qyid,
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
              {field:'id',title:'id',checkbox:true,width:50,align:'center'},   
              {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
              {field:'sbname',title:'设备名称',width:150,align:'center'},  
              {field:'sbtype',title:'设备类别',sortable:false,width:100,align:'center',
            	  formatter: function(value){
            		  if(value=='tzsb') return "特种设备";
            		  else if(value=='scsb') return "生产设备  ";
            		  else if(value=='gygc') return "公用工程";
            		  else if(value=='aqss') return "安全设施";
            		  else return ;
            	  }}, 
              {field:'type',title:'检修类别',sortable:false,width:100,align:'center',
            	  formatter: function(value){
            		  if(value=='1') return "计划维修";
            		  else if(value=='2') return "故障维修 ";
            	  }}, 
              {field:'starttime',title:'开工时间',sortable:false,width:100,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd');
              }},
              {field:'endtime',title:'结束时间',sortable:false,width:100,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd');
              }},
              {field:'operator',title:'操作人',sortable:false,width:100,align:'center'},    
              {field:'jfr',title:'交付人',sortable:false,width:100,align:'center'},    
              {field:'jsr',title:'接收人',sortable:false,width:100,align:'center'}   
            	  ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
		if(type=="1"){
		 	$(this).datagrid("hideColumn",['qyname']);
		}else{
		 	$(this).datagrid("showColumn",['qyname']);
		 	$(this).datagrid("autoMergeCells",['qyname']);
		}	      	
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbgl_jwx_tb'
	});
	
});
//弹窗增加
function add(u) {
	openDialog("添加检维修信息",ctx+"/sbgl/jwx/create/","90%", "70%","");
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
			url:ctx+"/sbgl/jwx/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改检维修信息",ctx+"/sbgl/jwx/update/"+row.id,"90%", "70%","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看检维修信息",ctx+"/sbgl/jwx/view/"+row.id,"80%", "60%","");
	
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
}

</script>

</body>
</html>