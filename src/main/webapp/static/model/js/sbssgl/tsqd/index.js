var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/tsqd/list?sbtype=' + sbtype;
	dg=$('#sbssgl_tsqd_dg').datagrid({    
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
        {field:'deptname',title:'使用单位',sortable:false,width:100,align : 'center'},
        {field:'m3',title:'日期',sortable:false,width:70,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return value;
        		}
        	}
        },
        {field:'m1',title:'主要设备制度开动台时（H）',sortable:false,width:70,align : 'center'},
        {field:'m2',title:'主要设备实际开动台时（H）',sortable:false,width:70,align:'center'},
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
		}else{
			$(this).datagrid("hideColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_tsqd_tb'
	});
});


//弹窗增加设备台账信息
function add() {
	openDialog("添加台时确定信息",ctx+"/sbssgl/tsqd/create?sbtype="+sbtype,"800px", "330px","");
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
			url:ctx+"/sbssgl/tsqd/delete/"+ids,
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
	openDialogView("查看台时确定信息",ctx+"/sbssgl/tsqd/view/"+row.id,"800px", "300px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改台时确定信息",ctx+"/sbssgl/tsqd/update/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}
