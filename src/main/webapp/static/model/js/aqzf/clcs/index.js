var dg;
var d;
$(function() {	
	dg = $('#aqzf_clcs_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqzf/clcs/list',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 5,
				singleSelect : true,
				striped : true,
				columns : [ [
				             {field:'id',title:'id',checkbox:true,width:50,align:'center'},
				             {field:'m0',title:'编号',width:100,align:'center'},    
				             {field:'qyname',title:'被检查单位',width:150,align:'center'},    
				             {field:'m1',title:'检查日期',width:100,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		 return datetime.format('yyyy-MM-dd');  
				                  	}
				              	}	 
				             },
				             {field:'m7_1',title:'执法人员1',width:80,align:'center'}, 
				             {field:'m7_2',title:'执法人员2',width:80,align:'center'}, 
				             {field:'m9',title:'被检查单位负责人',width:80,align:'center'},
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
					$(this).datagrid("autoMergeCells", [ 'plantime' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqzf_clcs_tb'
			});

});

// 弹窗增加
function add(u) {
	openDialog("添加处理措施", ctx + "/aqzf/clcs/create/", "900px", "540px", "");
}

// 删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/aqzf/clcs/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});

}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialog("修改处理措施", ctx + "/aqzf/clcs/update/" + row.id, "900px",
			"400px", "");
}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	
	openDialogView("查看处理措施",ctx + "/aqzf/clcs/view/" + row.id,"800px", "400px","");

}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//导出处理措施word
function exportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/aqzf/clcs/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
