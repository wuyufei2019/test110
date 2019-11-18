<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>指标考核信息</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/mbzb/zbkh/qy_index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_zbkh_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">
         <div class="form-group">
            <c:if test="${ usertype eq 'isbloc' }">
               <input type="text" name="target_zbkh_qyname" style="height:30px" class="easyui-textbox" data-options="width:200,prompt: '企业名称'" />
            </c:if>
            <input type="text" name="target_mbkh_year" id="target_mbkh_year" class="easyui-combobox" style="width:100px;height: 30px;"
               data-options="editable : false,prompt:'年份',panelHeight:'120',valueField: 'year',textField: 'year' ">
            <shiro:hasRole name="companyadmin">
               <input name="target_zbkh_deptname" type="text" class="easyui-combobox" style="height: 30px;"
                  data-options="prompt: '考核部门',panelHeight:'100',valueField: 'text',textField: 'text',url: '${ctx}/system/department/deptjson'">
            </shiro:hasRole>
         </div>
      </form>
      <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="target:zbkh:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()" title="初始化考核数据"><i class="fa fa-plus"></i> 导入年度考核数据</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="target:zbkh:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:zbkh:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:zbkh:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
       <%--   
        	<shiro:hasPermission name="target:zbkh:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<div id="yearcombo" style="width:100%;height: 100%;display: none;text-align: center;padding-top: 30px ;">
    <input type="text"id="year" class="easyui-combobox"  style="width:150px;height: 30px;" data-options="
   valueField:'year',textField:'year',prompt:'考核年份',panelHeight:'auto'"/> 
</div>

<table id="target_zbkh_dg"></table> 
<script type="text/javascript">
var ajqyid='${ajqyid}';
var usertype='${usertype}';
$(function(){
	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear();
	for(var i=0;i<5;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
	$("#target_mbkh_year").combobox("loadData", data);
	data=[];
	for(var i=0;i<2;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
	$("#year").combobox("loadData", data);
});
//弹窗增加
function add(u) {
	openDialog("添加指标考核信息",ctx+"/target/zbkh/create/","800px", "500px","");
}
//初始化当年数据
function init() {
	layer.open({
	    type: 1,  
	    title: '选择年份',
	    zIndex :0,
	    area:['200px','200px'],
	    content: $("#yearcombo"),
	    btn: ['导入','关闭'],
	    yes: function(index,layero){
	    	var year=$("#year").combobox('getValue');
	    	if(!year){
	    		layer.msg("请选择年份");
	    		return ;
	    	}
	    	$.ajax({
	    		url  : ctx+"/target/zbkh/init",
	    		data : {'year':year},
	    		async : false,
	    		method : 'POST',
	    		success : function(data){
	    			if(data&&data=='success')
		    			layer.msg("数据导入成功！");
		    		else
		    			layer.msg("数据导入失败！");
	    		}
	    	});
	    	layer.close(index);
	    	dg.datagrid('reload'); 
	      },
	    cancel: function(index){}
	}); 
	
 
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
			url:ctx+"/target/zbkh/delete/"+ids,
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
	updatekh(row.id);
}
//弹窗修改
function updatekh(id){
	openDialog("修改指标考核信息",ctx+"/target/zbkh/update/"+id,"800px", "500px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看指标考核信息",ctx+"/target/zbkh/view/"+row.id,"800px", "400px","");
	
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

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	var Columns=dg.datagrid("options").columns[0];
	window.exdata=[];
	for(var i=0;i<Columns.length;i++){
		if(Columns[i].field.toUpperCase()!="ID")
		window.exdata.push({colval:Columns[i].field, coltext:Columns[i].title});
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/target/zbkh/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}</script>

</body>
</html>