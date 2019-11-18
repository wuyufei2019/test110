var dg;
var cxtype;
var d;
var usertype;
$(function(){
	dg=$('#erm_yjdw_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yjdw/list', 
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
              {field:'m1',title:'队伍名称',width:100,align:'center'},    
              {field:'m2',title:'队伍类型',width:100,align:'center'},
              {field:'m4',title:'主要负责人',width:100,align:'center'},
              {field:'m5',title:'应急电话',width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
    	  $(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#erm_yjdw_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加应急队伍信息",ctx+"/erm/yjdw/create/","800px", "400px","");
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
			url:ctx+"/erm/yjdw/delete/"+ids,
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
	
	openDialog("修改应急队伍信息",ctx+"/erm/yjdw/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急队伍信息",ctx+"/erm/yjdw/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//查看地图
function openMap(){
	layer.open({
	    type: 2,  
	    area: ['700px', '400px'],
	    title: '查看应急队伍位置信息',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/erm/yjdw/map',
	});
	//openDialogView("查看应急队伍位置信息",ctx+"/erm/yjdw/map","900px", "700px",""); 
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
			   		{colval:'m1', coltext:'队伍名称'},
			   		{colval:'m2', coltext:'队伍类型'},
			   		{colval:'m3', coltext:'队伍地址'},
			   		{colval:'m4', coltext:'主要负责人'},
			   		{colval:'m5', coltext:'应急电话'},
			   		{colval:'m6', coltext:'主管部门'},
			   		{colval:'m7', coltext:'总人数'},
			   		{colval:'m8', coltext:'应急功能'},
			   		{colval:'m9', coltext:'成立时间'},
			   		{colval:'m11', coltext:'主要装备'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yjdw/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}