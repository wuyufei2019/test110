var dg;
var d;
$(function(){
	dg=$('#zyaqgl_xgfpd_setting_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgfpd/setting/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
              {field:'m1',title:'评定内容',width:80,align:'center'},   
              {field:'m2',title:'介绍',width:150,align:'center'}
         ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgfpd_setting_tb'
	});
});

//弹窗增加
function add() {
	openDialog("添加评定内容",ctx+"/zyaqgl/xgfpd/setting/create/","500px", "auto","");
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
			url:ctx+"/zyaqgl/xgfpd/setting/delete/"+ids,
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
	
	openDialog("修改评定内容",ctx+"/zyaqgl/xgfpd/setting/update/"+row.id,"500px", "auto","");
	
}
