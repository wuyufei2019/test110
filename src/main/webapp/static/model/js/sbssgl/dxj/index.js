var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/dxj/list?sbtype=' + sbtype;
	dg=$('#sbssgl_dxj_dg').datagrid({    
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
        {field:'m2',title:'年份',sortable:false,width:70,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return value + "年";
        		}
        	}
        },
        {field:'m3',title:'月份',sortable:false,width:70,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return value + "月";
        		}
        	}
        },
        {field:'m1',title:'标题',sortable:false,width:70,align:'center'},
        {field:'m4',title:'附件',sortable:false,width:70,align:'center',
        	formatter : function(value) {
        		if (value) {
        			var file = value.split("||");
        			return '<a style="color:#337ab7;text-decoration:none;cursor:pointer;" onclick="window.open('+file[0]+')">'+file[1]+'</a>';
        		} else {
        			return '/';
        		}
        	} 
        }
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
    toolbar:'#sbssgl_dxj_tb'
	});
});


//弹窗增加设备台账信息
function add() {
	openDialog("添加点巡检信息",ctx+"/sbssgl/dxj/create?sbtype="+sbtype,"800px", "400px","");
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
			url:ctx+"/sbssgl/dxj/delete/"+ids,
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
	openDialogView("查看点巡检信息",ctx+"/sbssgl/dxj/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改点巡检信息",ctx+"/sbssgl/dxj/update/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}
