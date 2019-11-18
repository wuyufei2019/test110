var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/sbgz/list?sbtype=' + sbtype;
	dg=$('#sbssgl_sbgz_dg').datagrid({    
	method: "post",
    url: url, 
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
        {field:'qyname',title:'所属企业',sortable:false,width:100,align : 'center'},
        {field:'deptname',title:'部门名称',sortable:false,width:100,align : 'center'},
        {field:'m1',title:'报修日期',sortable:false,width:50,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd");
        		}
        	}
        },
        {field:'m2',title:'报修单编号',sortable:false,width:50,align : 'center'},
        {field:'sbname',title:'设备名称',sortable:false,width:70,align:'center'},
        {field:'m5',title:'故障现象',sortable:false,width:70,align:'center'},
        {field:'m6',title:'原因分析',sortable:false,width:70,align:'center'},
        {field:'m9',title:'维修开始时间',sortable:false,width:70,align:'center',
        	formatter: function(value) {
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd hh:mm");
        		}
        	}
        },
        {field:'m10',title:'维修结束时间',sortable:false,width:70,align:'center',
        	formatter: function(value) {
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd hh:mm");
        		}
        	}
        },
        {field:'m11',title:'维修停机时间（H）',sortable:false,width:70,align:'center'},
        /*{field:'m8',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value) {
        		if (value) {
        			var fileUrl = value.split("||");
        			return '<a class="btn btn-warning btn-xs" onclick="window.open(\''+fileUrl[0]+'\')">查看维修报告</a>';
        		} else {
        			return '/';
        		}
        	} 
        }*/
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbgz_tb'
	});
});


//弹窗增加设备故障信息
function add() {
	openDialog("添加设备故障信息",ctx+"/sbssgl/sbgz/create?sbtype="+sbtype,"800px", "400px","");
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
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
			url:ctx+"/sbssgl/sbgz/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备故障信息",ctx+"/sbssgl/sbgz/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备故障信息",ctx+"/sbssgl/sbgz/update/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}

//导出Excel
function fileexport(sbtype){
	var exportUrl;
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'RowNumber', coltext:'序号'},
	               	{colval:'bxsj', coltext:'报修时间'},
			   		{colval:'m2', coltext:'报修单编号'},
			   		{colval:'sbname', coltext:'设备名称'},
			   		{colval:'m3', coltext:'设备型号'},
			   		{colval:'m4', coltext:'设备编号'},
			   		{colval:'m5', coltext:'故障现象'},
			   		{colval:'m6', coltext:'原因分析'},
			   		{colval:'m7', coltext:'采取对策'},
			   		{colval:'wxkssj', coltext:'维修开始时间'},
			   		{colval:'wxjssj', coltext:'维修结束时间'},
			   		{colval:'m8', coltext:'维修报告'},
			   		{colval:'m11', coltext:'维修停机时间（H）'},
			   		{colval:'m12', coltext:'更换零部件记录'},
			   		{colval:'m13', coltext:'备注'}
		   	];

	if (sbtype == '1') {//特种设备
		exportUrl = ctx+'/sbssgl/sbgz/colindex2';
	} else if (sbtype == '0') {//普通设备
		exportUrl = ctx+'/sbssgl/sbgz/colindex';
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: exportUrl,
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//导出word
function fileexport2(){
	$.ajax({
		type:"POST",
		url:ctx+"/sbssgl/sbgz/exportword",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
