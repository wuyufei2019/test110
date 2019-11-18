var dg;
var d;
$(function(){
	dg=$('#zdgl_wjfb_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/wjfb/list', 
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
        {field:'m1',title:'文件名称',sortable:false,width:80,
        	formatter : function(value, row, index) {
	          	return "<a onclick='view2("+row.id+")'>"+value+"</a>";
          	} 
        },
        {field:'m2',title:'文件编号',sortable:false,width:60,align:'center'},
        {field:'m3',title:'类型',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
	          	if(value=='1') {
	          		return '国家总局';  
	          	}else if(value=='2'){
	          		return '省局';  
	          	}else if(value=='3'){
	          		return '市局';  
	          	}else if(value=='4'){
	          		return '区县级';  
	          	}else if(value=='5'){
	          		return '行业';  
	          	}else if(value=='6'){
	          		return '主管部门';  
	          	}else if(value=='7'){
	          		return '公司';  
	          	}else if(value=='8'){
	          		return '部门';  
	          	}else if(value=='9'){
	          		return '其他';  
	          	}
          	} 
        },
        {field:'m4',title:'性质',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
	          	if(value=='1') {
	          		return '转发';  
	          	}else if(value=='2'){
	          		return '内部';  
	          	}else if(value=='3'){
	          		return '其他';  
	          	}
          	} 
        },
        {field:'s1',title:'发布日期',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'fbr',title:'发布人',sortable:false,width:50,align:'center'},
        {field:'zt',title:'状态',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
        		var a = '';
        		if(role == '1'){
        			if(row.m11 == '0'){
	            		a +="<font style='margin:2px' >批准未通过</font>";
	            	}else if(row.m11 == '1'){
	            		a +="<font style='margin:2px' >批准通过</font>";
	            	}else if(row.m8 == '0'){
	            		a +="<font style='margin:2px' >审核未通过</font>";
	            	}else if(row.m8 == '1'){
	            		a +="<font style='margin:2px' >审核通过待批准</font>";
	            	}else{
	            		a +="<font style='margin:2px' >待审核</font>";
	            	}
        		}else if(role == '2'){
        			if(row.m11 == '0'){
	            		a +="<font style='margin:2px'>批准未通过</font>";
	            	}else if(row.m11 == '1'){
	            		a +="<font style='margin:2px'>批准通过</font>";
	            	}else if(row.m8 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else if(row.m8 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
        		}else if(role == '3'){
        			if(row.m11 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m11 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp2("+row.id+")'>批准通过</a>";
	            	}else if(row.m8 == '0'){
	            		a +="<font style='margin:2px'>审核未通过</font>";
	            	}else if(row.m8 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<font style='margin:2px'>待审核</font>";
	            	}
        		}else if(role == '4'){
        			if(row.m11 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m11 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp2("+row.id+")'>批准通过</a>";
	            	}else if(row.m8 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a><a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
	            	}else if(row.m8 == '0'){
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
    toolbar:'#zdgl_wjfb_tb'
	});
});

//审核
function addsh(id){
	openDialog("审核文件发布信息",ctx+"/zdgl/wjfb/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准文件发布信息",ctx+"/zdgl/wjfb/update/pz/"+id,"800px", "400px","");
}

//批准2
function addsp2(id){
	top.layer.confirm('修改信息将会重新给部门发布新文件信息。', {
		icon : 3,
		title : '提示'
	}, function(index) {
		openDialog("批准文件发布信息",ctx+"/zdgl/wjfb/update/pz/"+id,"800px", "400px","");
		top.layer.close(index);
	});
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
			url:ctx+"/zdgl/wjfb/delete/"+ids,
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
	openDialogView("查看文件发布信息",ctx+"/zdgl/wjfb/view/"+row.id,"800px", "400px","");
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
	openDialog("添加文件发布信息",ctx+"/zdgl/wjfb/create/","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m11 == '1'){
		layer.msg("批准通过，不得进行修改!",{time: 3000});
	}else if(row.m8 == '1' && row.m11 == '0'){
		openDialog("修改文件发布信息", ctx + "/zdgl/wjfb/update/xg/" + row.id, "800px","400px", "");
	}else if(row.m8 == '1'){
		layer.msg("审核通过，不得进行修改!",{time: 3000});
	}else{
		openDialog("修改文件发布信息", ctx + "/zdgl/wjfb/update/xg/" + row.id, "800px","400px", "");
	}
}

//在线查看文件
function view2(id){
	window.open(ctx+"/zdgl/wjfb/view2/"+id, "安全文件发布");
}