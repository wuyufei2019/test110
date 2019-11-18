var dg;
var d;
var nowhm=(new Date()).getTime();
var gqCnt=0;
$(function() {
	dg = $('#xzcf_sxcg_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/sxcg/list',
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
								{
									field : 'id',
									title : 'ID',
									checkbox : true,
									width : 10,
									align : 'center'
								},{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 300,
									align : 'center'
								},
								
								{
									field : 'qyname',
									title : '受处单位（个人）名',
									sortable : false,
									width : 400,
									align : 'center'
								},
								{
									field : 'xzjd',
									title : '未履行的行政决定',
									sortable : false,
									width : 600,
									align : 'center'
								},
								{
									field : 'caozuo',
									title : '添加操作',
									sortable : false,
									width :150,
									//hidden:true,
									align : 'center',
									formatter : function(value, row, index) {
										if(row.jaflag!=1&&row.qzflag!=1){
											if(row.s1!=null&&row.s1!=""){
												if(nowhm>row.s1){
													var cha=(nowhm-row.s1)/1000/60/60/24;
													if(cha>30){
														gqCnt++;
														return "<a  class='btn btn-danger btn-xs' onclick='addCg("
														+ row.id+")'>强制执行申请书</a>";
													}
													}
								          		}
											}
									}
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
							if(gqCnt > 0){
								var dg = $('#xzcf_sxcg_dg');
							/*	var col = dg.datagrid('getColumnOption','caozuo');//获得该列属性
								col.width = document.body.clientWidth*0.1;//调整该列宽度
								col.align = 'left';*/
								//dg.datagrid('showColumn', 'caozuo');
					    		layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个案件符合填写强制执行条件，请及时填写！",shade: 0 ,time: 3000 });
					    	}
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#xzcf_sxcg_tb'
					});

});
function addCg(id) {
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['800px', '400px'],
	    title: "添加强制执行申请记录",
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/qzzx/create/"+id ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
	        // window.location.reload();
		  },
		  end: function(layero, index){
			  window.location.reload();
		 },
		  cancel: function(index){ 
	     }
	}); 
}

//// 添加告知记录
//function add() {
//	openDialog("添加告知记录", ctx + "/xzcf/xzcf/sxcg/create", "750px",
//			"400px", "");
//}

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
	gqCnt=0;
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
			url : ctx + "/xzcf/ybcf/sxcg/delete/" + ids,
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
	gqCnt=0;
	openDialog("修改催告记录", ctx + "/xzcf/ybcf/sxcg/update/" + row.id, "800px","400px", "");
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
	openDialogView("催告信息查看",ctx + "/xzcf/ybcf/sxcg/view/" + row.id, "800px","400px", "");

}

// 创建查询对象并查询
function search() {
	gqCnt=0;
	var obj = $("#searchFrom").serializeObject();
	if($("#type").combobox('getValue')==0){
		dg.datagrid('hideColumn', 'caozuo');
	}
	dg.datagrid('load', obj);
}

function reset() {
	gqCnt=0;
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

// 导出事先催告word
function fileexport() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/sxcg/exportsxcg/cg/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}


