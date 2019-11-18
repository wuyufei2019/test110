var dg;
var d;
var addM=false;

$(function(){ 
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/gyfkt/list2', 
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
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[   
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
              {field:'qyname',title:'企业名称',width:110},    
              {field:'name',title:'工艺名称',sortable:false,width:80,align:'center'},
              {field:'url',title:'下载',sortable:false,width:80,align:'center',
            	formatter:function(val){
        		if(val!=null&&val!=""){
        			val=val.split("||");
        			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">'+val[1]+'</a>';  
        		}else{
        			return '--';
        		}
	        	
        	} }
            ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    		view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
	
});

//增加
function add() {
	openDialog("添加工艺方块图信息",ctx+'/bis/gyfkt/create',"800px","400px","");
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
			url:ctx+"/bis/gyfkt/delete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改工艺方块图信息",ctx+'/bis/gyfkt/update/'+row.id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看工艺方块图信息",ctx+'/bis/gyfkt/view/'+row.id,"800px", "400px","");
}


//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

