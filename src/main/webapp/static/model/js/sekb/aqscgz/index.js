var dg;
var d;
$(function(){
	dg=$('#sekb_aqscgz_dg').datagrid({    
	method: "post",
    url:ctx+'/sekb/aqscgz/list', 
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
              {field:'m2',title:'标题',width:100},
              {field:'qrcode',title:'查看二维码',width:50,align:'center',
            	  formatter : function(value, row, index) {
            		  if(value!=""&&value!=null){
            			  return "<a class='btn btn-success btn-xs' style='margin:2px;' onclick='openerm("+row.id+")'>生成二维码</a>"
            		  }else{
            			  return "";
            		  }
            	  }
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sekb_aqscgz_tb'
	});
	
});

//生成二维码图片
function openerm(id){
	$.ajax({
		type : 'get',
		url : ctx + "/sekb/aqscfl/erm?id=" + id,
		success : function(data) {
			window.open(ctx+data);
		}
	});
}

//弹窗增加
function add(u) {
	openDialog("添加安全生产规章信息",ctx+"/sekb/aqscgz/create/","800px", "300px","");
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
			url:ctx+"/sekb/aqscgz/delete/"+ids,
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
	
	openDialog("修改安全生产规章信息",ctx+"/sekb/aqscgz/update/"+row.id,"800px", "350px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/sekb/aqscgz/view/"+row.id, "安全生产规章");
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
