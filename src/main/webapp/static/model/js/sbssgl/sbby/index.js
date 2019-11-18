var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/sbby/list?sbtype=' + sbtype;
	dg=$('#sbssgl_sbby_dg').datagrid({    
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
        {field:'jhnf',title:'保养计划年份',sortable:false,width:30,align : 'center',
        	formatter : function(value, row, index) {
            	return value + '年';
        	} 
        },
        {field:'qyname',title:'所属企业',sortable:false,width:60,align : 'center'},
        {field:'jhbt',title:'保养计划标题',sortable:false,width:60,align:'center'},
        {field:'jhlx',title:'保养计划类型',sortable:false,width:30,align:'center',
        	formatter : function(value, row, index) {
            	if(value == '1'){
            		return '月度';
            	}else if(value == '2'){
            		return '季度';
            	}else if(value == '3'){
            		return '半年度';
            	}else if(value == '4'){
            		return '年度';
            	}
        	} 
        },
        {field:'m1',title:'执行保养期限',sortable:false,width:30,align : 'center',
        	formatter : function(value, row, index) {
            	if(row.jhlx == '1'){//月度
            		return value+'月';
            	}else if(row.jhlx == '2'){//季度
            		return '第'+value+'季度';
            	}else if(row.jhlx == '3'){//半年度
            		if(value == '1'){
            			return '上半年度';
            		}else if(value == '2'){
            			return '下半年度';
            		}
            	}else if(row.jhlx == '3'){//年度
            		return '全年';
            	}
        	} 
        },
        {field:'sbbh',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'sbname',title:'设备名称',sortable:false,width:40,align:'center'},
        {field:'m2',title:'状态',sortable:false,width:30,align:'center',
        	formatter : function(value, row, index) {
            	if(value == '1'){
            		return '已上传附件';
            	}else{
            		return '未上传附件';
            	}
        	} 
        },
        {field:'czr',title:'操作人',sortable:false,width:30,align:'center'},
        {field:'cz',title:'操作',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
        		if(row.m2 == '0' && uploadrole == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=uploadfj("+row.id+")>上传附件</a>";
        		}else{
        			return '';
        		}
        	} 
        }
    ]],
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'jhnf' ]);
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    	$(this).datagrid("autoMergeCells", [ 'jhbt' ]);
    	$(this).datagrid("autoMergeCells", [ 'jhlx' ]);
    	$(this).datagrid("autoMergeCells", [ 'm1' ]);
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbby_tb'
	});
});

//上传附件
function uploadfj(id){
	openDialog("上传附件",ctx+"/sbssgl/sbby/uploadindex/"+id,"400px", "250px","");
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
			url:ctx+"/sbssgl/sbby/delete/"+ids,
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
	openDialogView("查看设备保养信息",ctx+"/sbssgl/sbby/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备保养信息",ctx+"/sbssgl/sbby/update/"+row.id,"400px", "250px","");
}
