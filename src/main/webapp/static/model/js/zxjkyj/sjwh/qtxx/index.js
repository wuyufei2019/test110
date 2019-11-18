var dg;
var d;
$(function(){
	dg=$('#sjwh_qtxx_dg').datagrid({    
	method: "post",
    url:ctx+'/zxjkyj/qtxx/list', 
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
              {field:'qyname',title:'企业名称',sortable:false,width:100},
              {field:'m1',title:'位号',sortable:false,width:50},    
              {field:'m2',title:'气体名称',sortable:false,width:50,align:'center'},
              {field:'m3',title:'气体类型',sortable:false,width:80,align:'center'},
              {field:'level1',title:'一级预警(比例小数)',sortable:false,width:50,align:'center'},
              {field:'level2',title:'二级预警(比例小数)',sortable:false,width:50,align:'center'},
              {field:'r1',title:'传感器位置',sortable:false,width:80,align:'center'}
          ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
             view();
    },
    onLoadSuccess:function(){
    	  if(usertype!="9"){
    		  $(this).datagrid("hideColumn",['qyname']);
    	  }
    	  $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sjwh_qtxx_tb'
	});
	
});

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//重置
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看高危工艺信息",ctx+"/zxjkyj/qtxx/view/"+row.ID,"1000px", "400px","");
	
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zxjkyj/qtxx/delete/"+ids,
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

//添加
function add(u) {
	openDialog("添加高危工艺信息",ctx+"/zxjkyj/qtxx/create/","800px", "350px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改储罐信息",ctx+"/zxjkyj/qtxx/update/"+row.ID,"1000px", "400px","");
	
}

