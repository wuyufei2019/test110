var dg;
$(function(){
	dg=$('#table_dg').datagrid({    
	method: "post",
	url:ctx+'/glsb/cpsb/list', 
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
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
	columns:[[ 
            {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
            {field:'code',title:'进出编号',sortable:false,width:100},
            {field:'in_out_time',title:'进出时间',sortable:false,width:100,formatter:function(value){
                  if(value) return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }},
            {field:'car_code',title:'车牌号',sortable:false,width:100},
        ]],
        onLoadSuccess: function(){
        },
        onDblClickRow: function (rowindex, rowdata, rowDomElement){
             view();
        },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#dg_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加车牌识别信息",ctx+"/glsb/cpsb/create/","800px", "400px","");
}

//删除
function del(){
	var rows = dg.datagrid('getChecked');
	if(rows.length==0) {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
    for(var item of rows){
       if(ids==""){
          ids=item.id;
       }else{
          ids=ids+","+item.id;
       }
    }

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/glsb/cpsb/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
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
	openDialog("修改车牌识别信息",ctx+"/glsb/cpsb/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看车牌识别信息",ctx+"/glsb/cpsb/view/"+row.id,"800px", "300px","");
	
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
}

