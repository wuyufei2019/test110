var dg;
var d;
//资质信息表datagrid
$(function(){
	dg=$('#zyaqgl_xgfwg_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgfwg/list2?dwid='+dwid, 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
			{field:'ID',title:'id',checkbox : true,width : 50,align : 'center'},
			{field:'m1',title:'违规日期',sortable:false,width:50,
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');   
					}  
	            }},    
			{field:'m2',title:'违规内容',sortable:false,width:290,align:'center'},
			{field:'m3',title:'相关责任人',sortable:false,width:100,align:'center'},
			{field:'m5',title:'罚单',sortable:false,width:100,align:'center'},
			{field:'m4',title:'扣分分值',sortable:false,width:60,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgfwg_tb'
	});
	
});


//弹窗增加
function add() {
	openDialog("添加相关方违规信息",ctx+"/zyaqgl/xgfwg/createwg/"+dwid+"/"+pddw,"800px", "400px","");
}

//弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("添加相关方违规信息",ctx+"/zyaqgl/xgfwg/update/"+row.id+"/"+pddw,"800px", "400px","");
}

//弹窗查看
function view() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("添加相关方违规信息",ctx+"/zyaqgl/xgfwg/view/"+row.id+"/"+pddw,"800px", "400px","");
}

//资质信息表查询
function search(){
	var obj=$("#zyaqgl_xgfwg_searchFrom").serializeObject();
	$('#zyaqgl_xgfwg_dg').datagrid('load',obj); 
}

//清空
function reset(){
	$("#zyaqgl_xgfwg_searchFrom").form("clear");
	var obj=$("#zyaqgl_xgfwg_searchFrom").serializeObject();
	$('#zyaqgl_xgfwg_dg').datagrid('load',obj);
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
			url:ctx+"/zyaqgl/xgfwg/delete/"+ids,
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
