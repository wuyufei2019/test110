var dg;
var d;
$(function() {
	dg = $('#aqjg_jcjl_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/aqjd/jcjl/list2',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'ID',
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
										{
											field : 'ID',
											title : 'ID',
											checkbox : true,
											width : 50,
											align : 'center'
										},
										{
											field : 'ym',
											title : '检查时间',
											sortable : false,
											width : 50,
											align : 'center',
											formatter : function(value) {
												var arry=value.split("-");
												return arry[0]+"年"+arry[1]+"月"
											}
										},
										{
											field : 'm1',
											title : '专项检查名称',
											sortable : false,
											width : 125,
											align : 'center'
										},
										{
											field : 'qyname',
											title : '企业名称',
											sortable : false,
											width : 125,
											align : 'center'
										},
										{
											field : 'm2',
											title : '初查时间',
											sortable : false,
											width : 80,
											align : 'center',
											formatter : function(value) {
												if (value != null && value != '') {
													var date = new Date(value);
													var y = date.getFullYear();
													var m = date.getMonth() + 1;
													var d = date.getDate();
													var hh = date.getHours();
													var mm = date.getMinutes();
													var ss = date.getSeconds();
													return y + '/' + (m < 10 ? ('0' + m) : m)
															+ '/' + (d < 10 ? ('0' + d) : d);
												} else {
													return '';
												}
											}
										},
										{
											field : 'm3',
											title : '初查负责人',
											sortable : false,
											width : 80,
											align : 'center'
										},
										
										{
											field : 'm10',
											title : '复查时间',
											sortable : false,
											width : 50,
											align : 'center',
											formatter : function(value) {
												if (value != null && value != '') {
													var date = new Date(value);
													var y = date.getFullYear();
													var m = date.getMonth() + 1;
													var d = date.getDate();
													var hh = date.getHours();
													var mm = date.getMinutes();
													var ss = date.getSeconds();
													return y + '/' + (m < 10 ? ('0' + m) : m)
															+ '/' + (d < 10 ? ('0' + d) : d);
												} else {
													return '';
												}
											}
										},
										{
											field : 'm9',
											title : '复查负责人',
											sortable : false,
											width : 80,
											align : 'center'
										},
										{
											field : 'checkflag',
											title : '检查进度',
											sortable : false,
											width : 80,
											align : 'center',
											formatter : function(value, row, index) {
									            	if(value==null||value==''||value=='0') return "<a  class='btn btn-info btn-xs' onclick='addReCheck("+row.id+")'>添加复查</a>";
									            	else return "检查已完成";
										},
								    	styler: function(value, row, index){
								    		if(value!=null&&value!=''&&value!='0')
								    			return 'background-color:rgb(216,155,176);';
								    	}
										}
								] ],
						onDblClickRow : function(rowdata, rowindex,
								rowDomElement) {
							view();
						},
						 onBeforeLoad:function(param){
						    	if(f!=''&&f=='sys'){
						    		$("#aqjg_jcjj_checkflag").combobox('setValue',jcjd);
						    		param.aqjg_jcjj_checkflag=jcjd;
						    	 }
							    },
						onLoadSuccess : function() {
							$(this).datagrid("autoMergeCells", [ 'm2','m1' ]);
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#aqjg_jcjl_tb'
					});

});

//查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("记录信息查看",ctx + "/aqjd/jcjl/view/" + row.id, "900px","400px", "");

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

// 导出word
function fileexport() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	$.ajax({
		url : ctx + "/aqjd/jcjl/exportqy/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
