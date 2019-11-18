<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备预防性保养管理信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="sbgl_sbyfxbygl_tb" style="padding:5px;height:auto">
	<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">
	  <c:if test="${ type eq '2' }">
        <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
      </c:if>
      <input type="text" name="sbname" style="height: 30px;" class="easyui-textbox" value="" data-options="prompt: '设备名称'"/>
      <input type="text" name="M1" style="height:30px"  class="easyui-textbox" data-options="prompt: '维护项目'"/>
      <input type="text" name="M4" style="height:30px;" class="easyui-combobox" 
                   data-options="editable:false,prompt: '维护周期',panelHeight:'auto',valueField:'value', textField:'text',data:[{value:'日',text:'日'},
                   {value:'周',text:'周'},{value:'月',text:'月'},{value:'季',text:'季'},{value:'半年',text:'半年'},{value:'年',text:'年'}]"/>
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		 	<shiro:hasPermission name="sbgl:yfxbygl:add">
		       	  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission> 
			<shiro:hasPermission name="sbgl:yfxbygl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:yfxbygl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:yfxbygl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="sbgl:yfxbygl:export">
         		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-file-excel-o"></i> 导出</button>
         	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
  </div>
</div>
<table id="sbgl_sbyfxbygl_dg"></table> 
<script type="text/javascript">
var type='${type}';
var usertype='${usertype}';
var dg;
$(function(){
	dg=$('#sbgl_sbyfxbygl_dg').datagrid({    
	method: "post",
    url:ctx+'/sbgl/yfxbygl/list', 
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
              {field:'sbname',title:'设备名称',sortable:false,width:80,align:'center'},  
              {field:'m1',title:'维护项目',sortable:false,width:80,align:'center'},
              {field:'m2',title:'维护要求',sortable:false,width:90},  
              {field:'m3',title:'维护方法',sortable:false,width:90},
              {field:'m4',title:'维护周期',sortable:true,width:40,align:'center'},
              {field:'m5',title:'操作者', sortable:false,width:50,align:'center'},
              {field:'m6',title:'维修者', sortable:false,width:50,align:'center'}
            	  ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells",['sbname']);
		if(type=="1"){
		 	$(this).datagrid("hideColumn",['qyname']);
		}else{
		 	$(this).datagrid("showColumn",['qyname']);
		 	$(this).datagrid("autoMergeCells",['qyname']);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbgl_sbyfxbygl_tb'
	});
	
});
//弹窗增加
function add(u) {
	openDialog("添加设备预防性保养管理",ctx+"/sbgl/yfxbygl/create/","900px", "500px","");
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
			url:ctx+"/sbgl/yfxbygl/delete/"+ids,
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
	openDialog("修改设备预防性保养管理信息",ctx+"/sbgl/yfxbygl/updata/"+row.id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看设备预防性保养管理信息",ctx+"/sbgl/yfxbygl/view/"+row.id,"800px", "300px","");
	
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	                {colval:'sbname', coltext:'设备名称'},
			   		{colval:'m1', coltext:'维护项目'},
			   		{colval:'m2', coltext:'维护要求'},
			   		{colval:'m3', coltext:'维护方法'},
			   		{colval:'m4', coltext:'维护周期'},
			   		{colval:'m5', coltext:'操作者'},
			   		{colval:'m6', coltext:'维修者'}
			   	   ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/sbgl/yfxbygl/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
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

</script>

</body>
</html>