var dg;
var eid;
$(function(){
	dg=$('#lbyp_ffjl_dg').datagrid({    
	method: "post",
    url:ctx+'/lbyp/ffjl/overviewlist?qyid='+qyid,
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
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'jobtype',title:'岗位（工种）',sortable:false,width:50},
        {field:'deptname',title:'部门名称',sortable:false,width:50},    
        {field:'ename',title:'员工姓名',sortable:false,width:50},   
        {field:'total',title:'领取次数',sortable:false,width:50, 	
        	styler: function(value){
          		if(value==0) return 'background-color:rgb(249, 156, 140);';
      	}},   
        {field:'caozuo',title:'查看详细记录',sortable:false,width:25,align:'center',
        	formatter :function (value, row){
        		return "<a class='btn btn-info btn-xs' onclick='view("+row.id+")'>查看</a>";
        	},
        }
    ]],
    onDblClickRow: function (index, row){
        view(row.id);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['jobname','ename']);
        if(qyid != ""){
        	$(this).datagrid("hideColumn", [ 'caozuo' ]);
        }
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_ffjl_tb'
	});
	
});

//弹窗增加
function addrecord(u) {
	openDialog("添加发放记录",ctx+"/lbyp/ffjl/create/","800px", "300px","");
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
			url:ctx+"/lbyp/ffjl/delete/"+ids,
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
	
	openDialog("修改发放记录信息",ctx+"/lbyp/ffjl/update/"+row.id,"800px", "300px","");
	
}

//查看
function view(id){
	eid=id;
	openDialogView("查看详细记录",ctx+"/lbyp/ffjl/viewdetail","90%", "80%","");
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

