<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标分配信息</title>
<meta name="decorator" content="default" />
    <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/mbzb/zbfp/qy_index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_zbfp_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">
         <div class="form-group">
            <c:if test="${ usertype eq 'isbloc' }">
               <input type="text" name="target_zbfp_qyname" style="height:30px" class="easyui-textbox" data-options="width:200,prompt: '企业名称'" />
            </c:if>
            <input type="text" id="target_zbfp_mbname" name="target_zbfp_mbname" style="height:30px" class="easyui-textbox"
               data-options="prompt: '指标名称'" />
            <input id="target_zbfp_m1" name="target_zbfp_m1" class="easyui-combobox" style="height: 30px;"
               data-options="editable : false ,prompt: '年份',panelHeight:'100',valueField: 'year',textField: 'year' ">
            <input name="target_zbfp_m3" type="text" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '级别',panelHeight:'auto',data:[{value:'1',text:'公司'},{value:'2',text:'部门'},{value:'3',text:'班组'}] ">
			<shiro:hasPermission name="target:zbfp:viewall">
				<input name="target_zbfp_deptname" type="text" class="easyui-combobox" style="height: 30px;"
					data-options="prompt: '责任部门',panelHeight:'auto',valueField: 'text',textField: 'text',url: '${ctx}/system/department/deptjson'">
			</shiro:hasPermission>
         </div>
      </form>
      <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="target:zbfp:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="target:zbfp:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:zbfp:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="target:zbfp:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        <%-- 	<shiro:hasPermission name="target:zbfp:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission> --%>
        	<%-- <shiro:hasPermission name="target:zbfp:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/target/zbfp/exinjump','${ctx}/target/zbfp/exin','${ctx}/static/templates/指标信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>

<table id="target_zbfp_dg"></table> 
<script type="text/javascript">
var ajqyid='${ajqyid}';
var qyid='${qyid}';
var usertype='${usertype}';
$(function(){
	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear();
	for(var i=0;i<5;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
	$("#target_zbfp_m1").combobox("loadData", data);
});




//弹窗增加
function add(u) {
	openDialog("添加指标分配信息",ctx+"/target/zbfp/create/","900px", "500px","");
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
		if(row[i].id2!=qyid) {
			layer.msg("无法对下属企业数据进行操作！",{time: 3000});
			return;
		}
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/target/zbfp/delete/"+ids,
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
	if(row.id2!=qyid) {
		layer.msg("无法对下属企业数据进行操作！",{time: 3000});
		return;
	}
	openDialog("修改指标分配信息",ctx+"/target/zbfp/update/"+row.id,"900px", "500px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	var target = "?deptname="+row.deptname+"&m1="+row.m1+"&m3="+row.m3;
	openView("查看指标分配信息",ctx+"/target/zbfp/view?qyid="+row.id2+"&deptname="+row.deptname+"&m1="+row.m1+"&m3="+row.m3,"100%", "100%",target);
	
}

function openView(title,url,width,height,target){
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: [width, height],
	    title: title,
        maxmin: false, 
	    content: url ,
	    btn: ['导出', '关闭'],
	    yes: function(index, layero){
	    	fileexport(target);
		  },
		  cancel: function(index){ 
	     }
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
}

//导出
function fileexport(target){
	$.ajax({
		url:ctx+"/target/zbfp/export/"+target,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
	/* window.expara=$("#searchFrom").serializeObject();
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
	    content: ctx+'/target/zbfp/colindex'+target,
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	}); */
}
</script>

</body>
</html>