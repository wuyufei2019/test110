<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>企业基本信息统计</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
var ctx='${ctx}';
</script>

</head>
<body>
<div id="bis_total_tb" style="padding:5px;height:auto">
     <div style="width: 100%;text-align: center;"><span style="color: red;font-size: 28px;">企业数据完整性统计报表</span></div>   	
    <div class="row">
	<div class="col-sm-12">
		 <div class="pull-left">
			<form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
				
       	        <input type="text" id="check_company_name" name="check_company_name" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
       		</form>
		
		</div>
		<div class="pull-right">
        	<button class="btn btn-primary btn-rounded btn-outline btn-sm " data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
		</div>
	</div>
	</div> 
  </div>
<table id="bis_total_dg"></table> 
<script type="text/javascript">
var dg;
$(function(){   
	dg=$('#bis_total_dg').datagrid({    
	method: "POST",
    url:'${ctx}/bis/qyjbxx/totaljson', 
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	idField : 'id',
	pagination : true,
	rownumbers : true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 300 ],
	scrollbarSize:5,
	singleSelect:true,
    columns:[[
		{field:'id',title:'id',hidden:true},  
		{field:'m1',title:'企业名称',width:100},
		{field:'m11_3',title:'所属乡镇',width:50,align:'center'},
		{field:'name',title:'所属中介',width:50,align:'center'},
		{field:'cj',title:'车间',width:50 ,align:'center',
			formatter : function(value, row, index) {
        	if(value==null||value=='0') return '无';
        	else return value;
    	}},
        {field:'ck',title:'仓库' ,width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},    
        {field:'wl',title:'物料',width:50,align:'center',
			formatter : function(value, row, index) {
		       if(value==null||value=='0') return '无';
		       else return value;
		 }},
        {field:'cg',title:'储罐',width:50,align:'center',
			formatter : function(value, row, index) {
			      if(value==null||value=='0') return '无';
			      else return value;
		}},
        {field:'tzsb',title:'特种设备',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'aqpx',title:'安全培训',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'scsb',title:'生产设备',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'gygc',title:'公用工程',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'aqss',title:'安全设施',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'yg',title:'员工',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'zyb',title:'职业病危险因素',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'jcbg',title:'检测报告',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'zybtj',title:'职业病体检',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'ygtj',title:'员工体检',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无数据';
	        	else return value;
	    }},
        {field:'wxgy',title:'危险工艺',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'wxy',title:'重大危险源',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'fc',title:'粉尘',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'sxkj',title:'受限空间',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'ny',title:'能源',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'cbs',title:'承包商',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'yj',title:'冶金',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
        {field:'zybc',title:'作业班次',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }},
	    {field:'fxd',title:'风险点',width:50,align:'center',
			formatter : function(value, row, index) {
	        	if(value==null||value=='0') return '无';
	        	else return value;
	    }}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    toolbar:'#bis_total_tb'
});
});


//创建查询对象并查询
function search(){
	var obj={'check_company_name':$('#check_company_name').combobox('getText')};
	dg.datagrid('reload',obj); 
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}


//查看
function view(){
	
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.open({
	    type: 2,  
	    area: ['900px', '480px'],
	    title: '查看企业基本信息',
        maxmin: true, 
	    content: ctx+"/bis/qyjbxx/view/"+row.id ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 	
}

//导出
function fileexport(){
	var qyname=$('#check_company_name').combobox('getText');
	window.open(ctx+"/bis/qyjbxx/export?qyname="+qyname);
}

</script>
</body>
</html>