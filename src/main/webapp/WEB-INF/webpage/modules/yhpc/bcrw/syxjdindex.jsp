<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检点信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="yhpc_jcdchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="jcdname"  class="easyui-textbox" style="height: 30px" data-options="prompt: '检查点名称'" />
		<input type="text" name="jcdtype" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查点类型',data: [
						         {value:'1',text:'风险辨识点'},
						         {value:'2',text:'自定义隐患点'}] "/> 
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchjcd()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>						         
    </div>
	</form>
</div>
<table id="yhpc_jcdchoose_dg"></table> 
<script type="text/javascript">
var dg;
var d;
$(function(){
	dg=$('#yhpc_jcdchoose_dg').datagrid({    
		method: "get",
	    url:ctx+'/yhpc/bcrw/jcdlist', 
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
	        //{field:'id',title:'id',checkbox:true,align:'center'}, 
	        {field:'name',title:'巡检点名称',width:100},
	        {field:'checkpointtype',title:'类型',width:60,
	        	formatter : function(value, row, index) {
	       			return value==1?'风险辨识点':'自定义隐患点';
	        	}
	        }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	        view();
	    },
		onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		},
	    toolbar:'#yhpc_jcdchoose_tb'
		});
});


function searchjcd() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看巡检点信息",ctx + "/yhpc/bcrw/syxjdview?xjdid=" + row.id+"&type="+row.checkpointtype,"100%", "100%","");
}
</script>
</body>
</html>