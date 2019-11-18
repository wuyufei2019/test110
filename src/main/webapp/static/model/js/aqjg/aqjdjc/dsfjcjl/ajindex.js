var dg;
var d;
$(function(){
	dg=$('#aqjg_dsfjcjl_dg').datagrid({    
	method: "post",
    url:ctx+'/dsffw/jcjl/ajlist', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:80,align:'center'},
        {field:'dwname',title:'第三方单位名称',sortable:false,width:80,align:'center'},
        {field:'m2',title:'检查时间',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');
              	}
          	} 
          },
        {field:'m4',title:'检查人员',sortable:false,width:80,align:'center'},
        {field:'m3',title:'整改期限',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');  
              	}
          	} 
          },
        {field:'m5',title:'整改负责人',sortable:false,width:80,align:'center'},
		{field :'m13',title :'操作',sortable : false,width : 50,align : 'center',
			formatter : function(value, row, index) {
	            	if(value==null||value==''||value=='0') return "<a class='btn btn-warning btn-xs' onclick='upd("+row.id+")'>修改初查</a>" +
	            												  "<a class='btn btn-info btn-xs' onclick='addReCheck("+row.id+")'>添加复查</a>";
	            	if(value=='1') return "<a class='btn btn-danger btn-xs' onclick='updateReCheck("+row.id+")'>修改复查</a>";
		}
		}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
		$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		},

	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqjg_dsfjcjl_tb'
	});
	
});

//添加初查信息
function add(u) {
	openDialog("添加第三方检查信息",ctx+"/dsffw/jcjl/create/","800px", "400px","");
}

//修改初查信息
function upd(id){
	openDialog("修改第三方检查信息",ctx+"/dsffw/jcjl/updatecc/"+id,"800px", "400px","");
	
}

//添加复查信息
function addReCheck(id){
	top.layer.confirm('添加复查记录后初查记录将不能修改！', {
		icon : 4,
		title : '提示'
	}, function(index) {
		top.layer.close(index);
		openDialog("添加复查记录", ctx + "/dsffw/jcjl/addReCheck/"+id, "800px", "600px", "");
	});
}

//修改复查信息
function updateReCheck(id){
	openDialog("修改复查记录", ctx + "/dsffw/jcjl/updateCheck/"+id, "800px", "600px", "");
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
			url:ctx+"/dsffw/jcjl/delete/"+ids,
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
	
	openDialogView("查看第三方检查信息",ctx+"/dsffw/jcjl/view/"+row.id,"800px", "400px","");
	
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

//导出现场检查记录word
function exportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/dsffw/jcjl/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
