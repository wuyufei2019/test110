<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sggl/sgxx/swryall.js?v=3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="sgxx_swryall_tb" style="padding:5px;height:40px">
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<form id="searchFrom" class="form-inline" action="">				
					<input type="text" id="bis_ygxx_cx_m1" name="bis_ygxx_cx_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '姓名'"/>
					<input type="text" id="bis_ygxx_cx_m4" name="bis_ygxx_cx_m4" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '岗位'"/>
				</form>
			</div>
			<div class="pull-left" style="margin-left: 5px">
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="add()"><i class="fa fa-plus"></i> 添加</button>  
			</div>
		</div>
	</div>
</div>
<table id="sgxx_swryall_dg" style="height: 260px;"></table> 
<script type="text/javascript">
var id1 = '${id1}'; 
var qyid= '${qyid}';
var url = ctx+'/sggl/sgxx/swrylist2?id1='+id1+'&qyid='+qyid;

var dg;
$(function(){
	dg=$('#sgxx_swryall_dg').datagrid({    
	method: "get",
    url:url,
    fit : false,
	fitColumns : true,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:false,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},      
        {field:'m1',title:'姓名',width:40},    
        {field:'m3',title:'性别',width:40,align:'center'},
        {field:'gw',title:'岗位',width:60,align:'center'},
        {field:'m5',title:'学历',width:40,align:'center'},
        {field:'m9',title:'联系方式',width:70,align:'center'},
    ]],
    onLoadSuccess: function(){
    	var rows=parent.dg.datagrid('getData').rows;
    	for(var i=0;i<rows.length;i++){
    		dg.datagrid('selectRecord',rows[i].id);
    	}
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:true,
	selectOnCheck:true,
    toolbar:'sgxx_swryall_tb'
	});
});

</script>
</body>
</html>