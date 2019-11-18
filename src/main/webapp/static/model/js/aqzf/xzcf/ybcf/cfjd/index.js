var dg;
var d;
var nowhm=(new Date()).getTime();
var gqCnt = 0;
$(function() {
	dg = $('#ybcf_cfjd_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/cfjd/list',
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
									width : 50,
									align : 'center'
								},
								{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 300,
									align : 'center'
								},
								{
									field : 'punishname',
									title : '受处罚单位（人）',
									sortable : false,
									width : 400,
									align : 'center'
								},
								{
									field : 'punishdate',
									title : '受处时间',
									sortable : false,
									width : 300,
									align : 'center',
									formatter : function(value, row, index) {
				                  		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
					              	}	 
								},
								{
									field : 'percomflag',
									title : '类型',
									sortable : false,
									width : 150,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null && value != '') {
											if(value=='0'){
													return "个人";
											}
											else{
												return "单位";
											}
									}
								}
								},
								{
									field : 'caozuo',
									title : '添加操作',
									sortable : false,
									width :150,
									//hidden:true,
									align : 'center',
									formatter : function(value, row, index) {
										if(row.jaflag!=1&&row.cgflag!=1){
											if(row.punishdate!=null&&row.punishdate!=""){
												if(nowhm>row.punishdate){
													var cha=(nowhm-row.punishdate)/1000/60/60/24;
													if(cha>50){
														gqCnt++;
														return "<a  class='btn btn-danger btn-xs' onclick='addCg("
														+ row.id+")'>添加事先催告</a>";
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
								//$('#ybcf_cfjd_dg').datagrid('showColumn', 'caozuo');
					    		layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + gqCnt + "个案件符合填写事先催告书条件，请及时填写！",shade: 0 ,time: 3000 });
					    	}
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#ybcf_cfjd_tb'
					});

});
//添加催告记录
//function addCg(id) {
//	openDialog("添加催告记录", ctx + "/xzcf/ybcf/sxcg/create/"+id, "800px","400px", "");
//}

//补全信息记录
function addCg(id) {
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['800px', '400px'],
	    title: "添加催告记录",
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/sxcg/create/"+id ,
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
			url : ctx + "/xzcf/ybcf/cfjd/delete/" + ids,
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
	openDialog("修改处罚记录", ctx + "/xzcf/ybcf/cfjd/update/" + row.id, "800px","400px", "");
	gqCnt=0;
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
	openDialogView("处罚信息查看",ctx + "/xzcf/ybcf/cfjd/view/" + row.id, "800px","400px", "");

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

// 导出告知书word
function fileexportcfjd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/cfjd/exportcfjd/cfjd/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
