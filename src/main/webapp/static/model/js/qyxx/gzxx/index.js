var dg;
var d;
$(function(){
	dg=$('#bis_gzxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/gzxx/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
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
              {field:'m3',title:'车间（装置）名称',sortable:false,width:100},
              {field:'m1',title:'岗位名称',sortable:false,width:100,align:'center'},
		      {field:'m4',title:'装置区与岗位允许最大人数',sortable:false,width:120,align:'center'},
			  {field:'m5',title:'在岗时间段开始时间',sortable:false,width:100,align:'center'},
			  {field:'m6',title:'在岗时间段结束时间',sortable:false,width:100,align:'center'}
             
    ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_gzxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加工种（岗位）信息",ctx+"/bis/gzxx/create/","700px", "300px","");
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
			url:ctx+"/bis/gzxx/delete/"+ids,
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
	
	openDialog("修改工种（岗位）信息",ctx+"/bis/gzxx/update/"+row.id,"700px", "300px","");
	
}
