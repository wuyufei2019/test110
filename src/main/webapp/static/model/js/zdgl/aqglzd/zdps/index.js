var dg;
var d;
$(function(){
	dg=$('#zdgl_zdps_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/aqps/list1?qyid='+qyid,
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
        {field:'m2',title:'评审主题',sortable:false,width:100},
        {field:'m3',title:'评审日期',sortable:false,width:60,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m4',title:'地点',sortable:false,width:80,align:'center'},
        {field:'m5',title:'主持人',sortable:false,width:60,align:'center'},
        {field:'m10',title:'评审结论',sortable:false,width:100,align:'center'},
        {field:'zt',title:'状态',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
        		var a = '';
        		if(role == '1'){
        			if(row.m16 == '0'){
	            		a +="<font style='margin:2px' >批准未通过</font>";
	            	}else if(row.m16 == '1'){
	            		a +="<font style='margin:2px' >批准通过</font>";
	            	}else if(row.m13 == '0'){
	            		a +="<font style='margin:2px' >审核未通过</font>";
	            	}else if(row.m13 == '1'){
	            		a +="<font style='margin:2px' >审核通过待批准</font>";
	            	}else{
	            		a +="<font style='margin:2px' >待审核</font>";
	            	}
        		}else if(role == '2'){
        			if(row.m16 == '0'){
	            		a +="<font style='margin:2px'>批准未通过</font>";
	            	}else if(row.m16 == '1'){
	            		a +="<font style='margin:2px'>批准通过</font>";
	            	}else if(row.m13 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else if(row.m13 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
	            	
        		}else if(role == '3'){
        			if(row.m16 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m16 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.m13 == '0'){
	            		a +="<font style='margin:2px'>审核未通过</font>";
	            	}else if(row.m13 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<font style='margin:2px'>待审核</font>";
	            	}
	            	
        		}else if(role == '4'){
        			if(row.m16 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m16 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.m13 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a><a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
	            	}else if(row.m13 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
        		}
            	return a;
        	} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zdgl_zdps_tb'
	});
});

//审核
function addsh(id){
	openDialog("审核制度评审信息",ctx+"/zdgl/aqps/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准制度评审信息",ctx+"/zdgl/aqps/update/pz/"+id,"800px", "400px","");
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
			url:ctx+"/zdgl/aqps/delete/"+ids,
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
	openDialogView("查看安全管理制度评审信息",ctx+"/zdgl/aqps/view1/"+row.id,"800px", "400px","");
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

//弹窗增加
function add() {
	openDialog("添加安全管理制度评审信息",ctx+"/zdgl/aqps/create1","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m16 == '1'){
		layer.msg("批准通过，不得进行修改!",{time: 3000});
	}else if(row.m13 == '1' && row.m16 == '0'){
		openDialog("修改安全管理制度评审信息",ctx+"/zdgl/aqps/update/xg/"+row.id,"800px", "400px","");
	}else if(row.m13 == '1'){
		layer.msg("审核通过，不得进行修改!",{time: 3000});
	}else{
		openDialog("修改安全管理制度评审信息",ctx+"/zdgl/aqps/update/xg/"+row.id,"800px", "400px","");
	}
}

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/zdgl/aqps/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
