var dg;
var d;
$(function(){
	url = ctx+'/sbssgl/bpbj/list?sbtype=' + sbtype;
	dg=$('#sbssgl_bpbj_dg').datagrid({    
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
        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
        {field:'deptname',title:'部门名称',sortable:false,width:50,align : 'center'},
        {field:'m20',title:'设备类型',sortable:false,width:40,align:'center',
        	formatter: function(value){
        		if (value == '0') {
        			return 'A类';
        		}
        	}
        },
        {field:'m1',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'m2',title:'设备名称',sortable:false,width:50,align : 'center'},
        {field:'m3',title:'规格/型号',sortable:false,width:50,align : 'center'},
        {field:'m5',title:'设备制造商',sortable:false,width:70,align:'center'},
        {field:'m8',title:'放置地点',sortable:false,width:50,align:'center'},
        {field:'m16',title:'启用日期',sortable:false,width:50,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m26',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
    			if (role == '1') {
					return "<a class='btn btn-info btn-xs' onclick=addOrUpdInfo("+row.id+")>添加 / 修改</a>";
				} else if (role == '0') {
					if (value == '0') {
	    				return "暂无备品备件信息";
	    			} else if (value == '1') {	
	    				return "<a class='btn btn-info btn-xs' onclick=viewInfo("+row.id+",\""+row.m26+"\")>查看</a>";
	    			}
        		}
			}
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){ //子公司
			$(this).datagrid("hideColumn", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
			$(this).datagrid("hideColumn", [ 'deptname']);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'deptname']);
		}
    },
    onDblClickRow: function (rowindex, rowdata, rowDomElement){
    	viewInfo(rowdata.id, rowdata.m26);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_bpbj_tb'
	});
});


//添加或修改
function addOrUpdInfo(sbid) {
	openDialog("添加/修改备品备件信息",ctx+"/sbssgl/bpbj/create/"+sbid+"?sbtype="+sbtype,"800px", "80%","");
}

//查看（每条记录中的按钮）
function viewInfo(sbid, hasBpbj) {
	if (hasBpbj == '1') {
		openDialogView("查看备品备件信息",ctx+"/sbssgl/bpbj/view/"+sbid,"800px", "80%","");
	} else {
		layer.msg("该设备暂无备品备件信息！",{time: 3000});
	}
}

//查看（页面上方的按钮）
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if (row.m26 == '1') {
		openDialogView("查看备品备件信息",ctx+"/sbssgl/bpbj/view/"+row.id,"800px", "400px","");
	} else {
		layer.msg("该设备暂无备品备件信息！",{time: 3000});
	}
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
			url:ctx+"/sbssgl/bpbj/delete/"+ids,
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

//导出word
function fileexport(){
	var obj=$("#searchFrom").serializeObject();
	$.ajax({
		type:"POST",
		url:ctx+"/sbssgl/bpbj/export?sbtype=" + sbtype,
		data:{"qyname": obj.qyname, "m2": obj.m2, "m3": obj.m3, "m5": obj.m5, "m6": obj.m6, "m7": obj.m7},
		success:function(data){
			window.open(ctx+data);
		}
	});
}
