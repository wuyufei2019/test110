var dg;
var cxtype;
var d;
var usertype;
$(function(){
	dg=$('#erm_bncs_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/bncs/list', 
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
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
              {field:'m1',title:'场所名称',width:100},    
              {field:'m2',title:'场所类型',width:100,align:'center'},
              {field:'m3',title:'详细地址',width:100},
              {field:'m4',title:'可容纳人数',width:100,align:'center'},
              {field:'m5',title:'联系人',width:100,align:'center'},
              {field:'m6',title:'联系人电话',width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#erm_bncs_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加避难场所信息",ctx+"/erm/bncs/create/","800px", "400px","");
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
			url:ctx+"/erm/bncs/delete/"+ids,
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
	
	openDialog("修改避难场所信息",ctx+"/erm/bncs/update/"+row.id,"800px", "400px","");
	
}

//查看地图
function openMap(){
	layer.open({
	    type: 2,  
	    area: ['700px', '400px'],
	    title: '查看避难场所位置信息',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/erm/bncs/map',
	});
}


//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看避难场所信息",ctx+"/erm/bncs/view/"+row.id,"800px", "400px","");
	
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
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'场所名称'},
			   		{colval:'m2', coltext:'场所类型'},
			   		{colval:'m3', coltext:'详细地址'},
			   		{colval:'m4', coltext:'可容纳人数'},
			   		{colval:'m5', coltext:'联系人'},
			   		{colval:'m6', coltext:'联系人电话'},
			   		{colval:'m7', coltext:'负责人'},
			   		{colval:'m8', coltext:'负责人电话'},
			   		{colval:'m9', coltext:'功能描述'},
			   		{colval:'m11', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/bncs/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}