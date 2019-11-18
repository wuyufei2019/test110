var dg;
var d;
$(function() {	
	dg = $('#aqzf_zlzg_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqzf/zlzg/list',
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
				             {field:'m3',title:'整改完毕日期',width:100,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		 return datetime.format('yyyy-MM-dd');  
				                  	}
				              	}	 
				             },
				             {field:'m6_1',title:'执法人员1',width:80,align:'center'}, 
				             {field:'m6_2',title:'执法人员2',width:80,align:'center'}, 
				             {field:'m8',title:'被检查单位负责人',width:80,align:'center'},  
				             {field:'m4',title:'操作',width:100,align:'center',
				            	 formatter : function(value, row, index) {
				            		if(row.m9=="1"){
				            			return "已复查";	
				            		}
						    	    var nowhm=(new Date()).getTime();
					    	   	    var m3=new Date(row.m3);
					    	        var m3time=m3.getTime();
					    	        var i= Math.ceil((m3time-nowhm)/1000/60/60/24);
					    	        return "距离复查还有"+i+"天<a style='margin-left:10px' class='btn btn-info btn-xs' onclick='addZgfc("+row.id+")'>整改复查</a>";
						         }
				             },
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
					$(this).datagrid("autoMergeCells", [ 'plantime' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqzf_zlzg_tb'
			});

});

//添加复查意见
function addZgfc(id) {
	openDialog("添加整改复查意见信息", ctx + "/aqzf/fcyj/create/"+id, "900px","400px", "");
}

//弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.m9 == '0'){
		openDialog("修改责令整改", ctx + "/aqzf/zlzg/update/" + row.id, "900px",
				"400px", "");
	}else if(row.m9 == '1'){
		layer.msg("该责令整改信息已添加整改复查意见，不得修改！", {
			time : 3000
		});
		return;
	}
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
		if(row[i].m9 == '1'){
			layer.msg("存在已添加复查的记录，不得删除！", {
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
			url : ctx + "/aqzf/zlzg/delete/" + ids,
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

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	
	openDialogView("查看责令整改",ctx + "/aqzf/zlzg/view/" + row.id,"800px", "400px","");

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

//导出责令整改word
function exportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/aqzf/zlzg/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}