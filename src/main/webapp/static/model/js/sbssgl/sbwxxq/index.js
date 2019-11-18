var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/sbwxxq/list?sbtype=' + sbtype;
	dg=$('#sbssgl_sbwxxq_dg').datagrid({    
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
        {field:'m1',title:'申请日期',sortable:false,width:70,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd");
        		}
        	}
        },
        {field:'m2',title:'设备编号',sortable:false,width:70,align : 'center'},
        {field:'sbname',title:'设备名称',sortable:false,width:70,align : 'center'},
        {field:'m22',title:'使用人',sortable:false,width:70,align:'center'},
        {field:'m3',title:'故障发现时间',sortable:false,width:70,align : 'center',
        	formatter: function(value){
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd hh:mm");
        		}
        	}
        },
        {field:'m4',title:'故障处理等级',sortable:false,width:70,align:'center',
        	formatter: function(value){
        		if (value == '0') {
        			return "立刻处理";
        		} else if (value == '1') {
        			return "停机后处理";
        		} else if (value == '2') {
        			return "保养过程中处理";
        		} else if (value == '3') {
        			return "其他情况";
        		} 
	    	},
	    	styler: function(value, row, index){
        		if(value == '0'){//立刻处理
        			return 'background-color:rgb(249, 156, 140);';
        		}
        	}
	    },
        {field:'sqrname',title:'申请人',sortable:false,width:70,align:'center'},
        {field:'m23',title:'状态',sortable:false,width:70,align:'center',
        	formatter: function(value){
        		if (value == '0') {
        			return "等待确认";
        		} else if (value == '1') {
        			return "已通过，等待维修";
        		} else if (value == '2') {
        			return "驳回";
        		} else if (value == '3') {
        			return "已维修，等待验收";
        		} else if (value == '4') {
        			return "已验收，等待评定";
        		} else if (value == '5') {
        			return "已评定，等待添加改善措施";
        		} else if (value == "6") {
        			return "等待结束";
        		} else if (value == "7") {
        			return "已结束";
        		}
        	}
        },
        {field:'caozuo',title:'操作',sortable:false,width:70,align:'center',
        	formatter: function(value, row, index) {
        		if (row.m23 == '0' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=changeZt("+row.id+",'1')>通过</a>" +
						   "<a style='margin:2px' class='btn btn-danger btn-xs' onclick=changeZt("+row.id+",'2')>驳回</a>";
        		} else if (row.m23 == '1' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=addWx("+row.id+")>添加维修记录</a>";
        		} else if (row.m23 == '3'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=addYs("+row.id+")>验收</a>";
        		} else if (row.m23 == '4' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=addPd("+row.id+")>添加结果评定</a>";
        		} else if (row.m23 == '5' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=addCs("+row.id+")>添加再发生防止改善措施</a>";
        		} else if (row.m23 == '6' && role == '1'){
        			return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=end("+row.id+")>结束</a>";
        		} 
        	}
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname']);
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
    toolbar:'#sbssgl_sbwxxq_tb'
	});
});


//弹窗增加设备维修需求申请信息
function add() {
	openDialog("添加设备维修需求申请信息",ctx+"/sbssgl/sbwxxq/create?sbtype="+sbtype,"800px", "400px","");
}

//弹窗增加设备维修记录信息
function addWx(id) {
	openDialog("添加设备维修记录信息",ctx+"/sbssgl/sbwxxq/wxcreate/"+id,"800px", "380px","");
}

//弹窗增加设备维修验收信息
function addYs(id) {
	openDialog("添加设备维修验收信息",ctx+"/sbssgl/sbwxxq/yscreate/"+id,"800px", "380px","");
}

//弹窗增加设备维修评定信息
function addPd(id) {
	openDialog("添加设备维修评定信息",ctx+"/sbssgl/sbwxxq/pdcreate/"+id,"800px", "300px","");
}

//弹窗增加再发生防止改善措施信息
function addCs(id) {
	openDialog("添加再发生防止改善措施信息",ctx+"/sbssgl/sbwxxq/cscreate/"+id,"800px", "500px","");
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
			url:ctx+"/sbssgl/sbwxxq/delete/"+ids,
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
	openDialogView("查看设备维修申请信息",ctx+"/sbssgl/sbwxxq/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m23 == '7') {
		layer.msg("维修已结束，不能变更信息！",{time: 3000});
		return;
	}
	openDialog("修改设备维修需求信息",ctx+"/sbssgl/sbwxxq/update/"+row.id,"800px", "400px","");
}

//通过或驳回操作
function changeZt(id,type){
	if(type == '1'){//通过
		top.layer.confirm('您是否确定通过此次申请？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/sbssgl/sbwxxq/tgzt/"+id,
				success: function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
				}
			});
		});
	}else if(type == '2'){//驳回
		top.layer.confirm('您是否确定驳回此次申请？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/sbssgl/sbwxxq/bhzt/"+id,
				success: function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
				}
			});
		});
	}
}

//结束
function end(id){
	top.layer.confirm('结束后信息无法变更，您确定结束吗？', {icon: 3, title:'提示'}, function(index){
		top.layer.close(index);
		$.ajax({
			type:'get',
			url:ctx+"/sbssgl/sbwxxq/end/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//导出word
function fileexport(){
	$.ajax({
		type:"POST",
		url:ctx+"/sbssgl/sbwxxq/exportword",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//导出word
/*function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	$.ajax({
		type:"POST",
		url:ctx+"/sbssgl/sbwxxq/exportword/"+row.id,
		success:function(data){
			window.open(ctx+data);
		}
	});
}*/


