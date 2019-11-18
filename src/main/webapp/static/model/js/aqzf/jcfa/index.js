var dg;
var d;
$(function() {	
	dg = $('#aqzf_jcfa_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqzf/jcfa/list',
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
				             {field:'plantime',title:'计划时间',width:70,align:'center'},
				             {field:'m0',title:'编号',width:70,align:'center'},
				             {field:'qyname',title:'被检查单位',width:100,align:'center'},    
				             {field:'m1',title:'地址',width:120,align:'center'},    
				             {field:'m2',title:'联系人',width:50,align:'center'},
				             {field:'m3',title:'所属行业',width:50,align:'center'},
				             {field:'m4',title:'检查时间',width:60,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		 return datetime.format('yyyy-MM-dd');  
				                  	}
				              	}	 
				             },		             
				             {field:'m5',title:'行政执法人员',width:50,align:'center'},
				             {field : 'm11',title : '操作',sortable : false,width : 70,align : 'center',
									formatter : function(value, row, index) {
										if(value == '0'){
											return " <a class='btn btn-info btn-xs' onclick='addJl("+row.id+")'>添加检查</a> ";
										}else if(value == '1'){
											return "已检查";
										}
						            }
							 }		
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
					$(this).datagrid("autoMergeCells", [ 'plantime' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqzf_jcfa_tb'
			});

});

// 弹窗增加
function add(u) {
	openDialog("添加检查计划", ctx + "/aqzf/jcfa/create/", "900px", "400px", "");
}

//添加检查记录
function addJl(id){
		openDialog("添加检查记录", ctx + "/aqzf/jcjl/addJl/"+id, "900px","400px", "");
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
		if(row[i].m11 == '1'){
			layer.msg("存在已生成检查记录的方案，不得删除!", {
				time : 3000
			});
			return;
		}
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
			url : ctx + "/aqzf/jcfa/delete/" + ids,
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
	if(row.m11 == '1'){
		layer.msg("该方案已生成检查记录，不得修改!", {
			time : 3000
		});
		return;
	}else if(row.m11 == '0'){
		openDialog("修改检查方案", ctx + "/aqzf/jcfa/update/" + row.id, "900px",
				"400px", "");
	}
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
	openDialogView("查看检查方案",ctx + "/aqzf/jcfa/view/" + row.id,"800px", "400px","");

}

// 创建查询对象并查询
function search() {
	$("#aqzf_jcfa_year").combobox('setValue',$("#aqzf_jcfa_year").combobox('getText'));
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/aqzf/jcfa/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
	}
