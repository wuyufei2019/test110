var dg;
var d;
$(function(){
	dg=$('#lydw_gkgl_dg').datagrid({    
	method: "post",
    url:ctx+'/lydw/gkgl/list', 
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
        {field:'fileid',title:'fileid',checkbox:true,width:50,align:'center'},
        {field:'bqh',title:'标签号',sortable:false,width:80,
            formatter : function (value, row, index) {
                return row.fileid;
            }
        },
        {field:'filecode',title:'标签编码',sortable:true,width:50,align:'center',
        },
        {field:'tag',title:'标签',sortable:true,width:50,align:'center',
        },
       	{field:'intime',title:'载入时间',sortable:true,width:50,align:'center',
            formatter : function(value, row, index) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss")
			}
        },
        {field:'uptime',title:'更新时间',sortable:true,width:50,align:'center', 
            formatter : function(value, row, index) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss")
			}
        },
        {field:'val1',title:'状态',sortable:true,width:50,align:'center',
        },
        {field:'online',title:'是否在线',sortable:false,width:50,align:'center',
            formatter : function (value, row, index) {
                if(value == '0') {
                    return '离线';
                }else if(value == '1'){
                    return '在线';
                }
            }
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lydw_gkgl_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加工卡信息",ctx+"/lydw/gkgl/create/","800px", "350px","");
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
			ids=row[i].fileid;
		}else{
			ids=ids+","+row[i].fileid;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/lydw/gkgl/delete/"+ids,
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
	
	openDialog("修改工卡信息",ctx+"/lydw/gkgl/update/"+row.fileid,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看车间信息",ctx+"/lydw/gkgl/view/"+row.fileid,"800px", "400px","");
	
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
