var dg;
var d;
$(function() {
	dg = $('#ybcf_lasp_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/lasp/list',
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
									title : 'id',
									checkbox : true,
									width : 50,
									align : 'center'
								},
								{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 70,
									align : 'center'
								},
								{
									field : 'dsperson',
									title : '当事人',
									sortable : false,
									width : 100,
									align : 'center'
								},
								{
									field : 'ayname',
									title : '案由',
									sortable : false,
									width : 100,
									align : 'center'
								},
								{
									field : 'caozuo',
									title : '添加记录',
									sortable : false,
									width : 200,
									align : 'center',
									formatter : function(value, row, index) {
										var id=row.id;
										var xwflag= row.xwflag==1?1:0;
										var gzflag= row.gzflag==1?1:0;
										var dcflag= row.dcflag==1?1:0;
										var tzflag= row.tzflag==1?1:0;
										var cfjdflag= row.cfjdflag==1?1:0;
										var cbflag= row.cbflag==1?1:0;
										var cgflag= row.cgflag==1?1:0;
										var qzflag= row.qzflag==1?1:0;
										
					                    var a = "";
					                    if(xwflag=="1" || dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>询问通知</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='addXw("
												+ id +","+row.tempflag+ ")'>询问通知</a>";
					                    }
					                    
					                    if(dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>询问笔录</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addBl("
												+ id+ "," +xwflag+ ","+row.tempflag+ ")'>询问笔录</a>";
					                    }
					                    
					                    if(dcflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>调查报告</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick='adddc("
											+ id+","+row.tempflag+ ")'>调查报告</a>";
					                    }
					                    
					                    if(row.tlflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>集体讨论</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addtl("
											+ id+","+row.tempflag+")'>集体讨论</a>";
					                    }
					                    
					                    if(row.sbflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>陈述申辩</a><br>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsb("
											+ id+","+row.tempflag+")'>陈述申辩</a><br>";
					                    }
					                    
					                    if(gzflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚告知</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick='addGz("
											+ id +"," +dcflag+")'>处罚告知</a>";
					                    }
					                    
					                    if(tzflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>听证告知</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addTz("
											+id+","+gzflag+")'>听证告知</a>";
					                    }
					                    
					                    if(cbflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>案件呈批</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addCp("
											+ id +","+gzflag+")'>案件呈批</a>";
					                    }
					                    
					                    if(cfjdflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>处罚决定</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-success btn-xs' onclick='addJd("
											+ id +"," +row.cbflag+ ")'>处罚决定</a>";
					                    }
					                    
					                    if(row.jaflag=="1"){
					                    	a+= "<a style='margin:2px' disabled='true' class='btn btn-default btn-xs'>结案审批</a>";
					                    }else{
											a+= "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addJa("
											+ id +"," +cfjdflag+")'>结案审批</a>";
					                    }
										
										return a;
									}
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#ybcf_lasp_tb'
					});

});

// 添加立案审批记录
function add() {
	openDialog("添加立案审批", ctx + "/xzcf/ybcf/lasp/create", "800px","400px", "");
}

//添加调查报告
function adddc(id,t) {
	if (t==1) {
		top.layer.confirm('请先补全立案审批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加调查报告", ctx + "/xzcf/ybcf/dcbg/create/"+id, "800px","400px", "");
}

//添加集体讨论
function addtl(id,t) {
	if (t==1) {
		top.layer.confirm('请先补全立案审批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加集体讨论",ctx+"/xzcf/ybcf/jttl/create/"+id,"800px", "400px","");
}

//添加陈述申辩
function addsb(id,t) {
	if (t==1) {
		top.layer.confirm('请先补全立案审批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加陈述申辩",ctx+"/xzcf/ybcf/cssbbl/create/"+id,"800px", "400px","");
}

//补全信息记录
function addtemp(count) {
	if (count==0) {
		top.layer.confirm('没有需要待补全的信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['800px', '400px'],
	    title: "补全审批记录",
        maxmin: false, 
	    content: ctx + "/xzcf/ybcf/lasp/update/temp" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
	         //window.location.reload();
		  },
		  end: function(layero, index){
			  window.location.reload();
		 },
		  cancel: function(index){ 
	     }
	}); 
}
//添加询问笔录
function addBl(id,f1,t) {
	if (t==1) {
		top.layer.confirm('请先补全立案审批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	if (f1!=1) {
		top.layer.confirm('您还没有添加询问通知', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加询问笔录",ctx+"/xzcf/ybcf/xwbl/create/"+id,"800px", "400px","");
}

//添加询问通知
function addXw(id,t) {
	if (t==1) {
		top.layer.confirm('请先补全立案审批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加询问通知", ctx + "/xzcf/ybcf/xwtz/create/"+id, "800px",
			"400px", "");
}
//添加处罚告知
function addGz(id,f1) {
	if (f1!=1) {
		top.layer.confirm('您还没有添加调查报告信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加处罚告知", ctx + "/xzcf/ybcf/cfgz/create/"+id, "800px",
			"400px", "");
}
//添加听证告知记录
function addTz(id,f2) {
	if (f2!= 1) {
		top.layer.confirm('您还没有添加处罚告知信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加听证告知", ctx + "/xzcf/ybcf/tzgz/create/"+id, "800px",
			"400px", "");
}
//添加处罚决定记录
function addJd(id,f1) {
	if (f1!=1) {
		top.layer.confirm('您还没有添加案件呈批信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加处罚决定", ctx + "/xzcf/ybcf/cfjd/create/"+id, "800px",
			"400px", "");
}

//添加结案审批记录
function addJa(id,f1) {
	if (f1!=1) {
		top.layer.confirm('您还没有添加处罚决定信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加结案审批", ctx + "/xzcf/ybcf/jasp/create/"+id, "800px",
			"400px", "");
}
//添加安监呈批处理记录
function addCp(id,f2) {
	if (f2!= 1) {
		top.layer.confirm('您还没有添加处罚告知信息', {
			icon : 2,
			title : '提示'
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	openDialog("添加案件呈批", ctx + "/xzcf/ybcf/ajclcp/create/"+id, "800px",
			"400px", "");
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
			url : ctx + "/xzcf/ybcf/lasp/delete/" + ids,
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
	if(row.dcflag == '1'){
		layer.msg("已生成调查报告，不得修改！", {
			time : 3000
		});
		return;
	}else{
		layer.open({
		    type: 2,  
		    shift: 1,
		    area: ['800px', '400px'],
		    title: "修改审批记录",
		    maxmin: false, 
		    content: ctx + "/xzcf/ybcf/lasp/update/" + row.id ,
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var body = layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0];
		         var inputForm = body.find('#inputForm');
		         iframeWin.contentWindow.doSubmit();
		         //window.location.reload();
			  },
			  end: function(layero, index){
				  window.location.reload();
			 },
			  cancel: function(index){ 
		     }
		}); 
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
	openDialogView("审批信息查看",ctx + "/xzcf/ybcf/lasp/view/" + row.id, "800px","400px", "");

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

// 导出立案审批word
function fileexportlasp() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	else if(row.tempflag=='1'){
		top.layer.confirm('临时添加的立案审批记录，请先补全记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
		}else{
			$.ajax({
				url : ctx + "/xzcf/ybcf/lasp/exportlasp/" + row.id,
				type : "POST",
				success : function(data) {
					window.open(ctx + data);
				}
			});
		}
}
// 导出送达回执word
function fileexporsdhz() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.tempflag == 1) {
		top.layer.confirm('立案审批信息未补全，请先补全信息！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	
	$.ajax({
		url : ctx + "/xzcf/ybcf/lasp/exportsdhz/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
// 导出告知书记录word
function fileexportgzs() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.gzflag=='1'){
		$.ajax({
			url : ctx + "/xzcf/ybcf/cfgz/exportgzs/la/" + row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
		}else{
			top.layer.confirm('您还没有添加告知记录！', {
				icon : 2,
				title : '提示' 
			}, function(index) {
				top.layer.close(index);
			});
			return;
		}
	}
// 导出调查包裹word
function fileexportdc() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.dcflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/dcbg/export/la/"+row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		top.layer.confirm('您还没有添加调查报告记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
}

//导出集体讨论word
function fileexporttl() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.tlflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/jttl/export/la/"+row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		top.layer.confirm('您还没有添加集体讨论记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
}

//导出陈述申辩word
function fileexportsb() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.sbflag=='1'){
		$.ajax({
			url:ctx+"/xzcf/ybcf/cssbbl/export/la/"+row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
	}else{
		top.layer.confirm('您还没有添加陈述申辩笔录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
}

//导出听证告知记录word
function fileexporttz() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.tzflag=='1'){
		$.ajax({
			url : ctx + "/xzcf/ybcf/tzgz/exporttz/la/" + row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
		}else{
			top.layer.confirm('您还没有添加听证记录！', {
				icon : 2,
				title : '提示' 
			}, function(index) {
				top.layer.close(index);
			});
			return;
		}
	}

//导出处罚决定书word
function fileexportcfjd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.cfjdflag != '1') {
		top.layer.confirm('您还没有添加处罚决定记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/cfjd/exportcfjd/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出询问通知书word
function fileexportxw() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.xwflag !='1') {
		top.layer.confirm('您还没有添加询问记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/xwtz/export/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

function fileexportajcp() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.cbflag != '1') {
		top.layer.confirm('您还没有添加案件呈批记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/ajclcp/exportajcp/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
//导出结案审批word
function fileexportjasp() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.cbflag != '1') {
		top.layer.confirm('您还没有添加结案审批记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	
	$.ajax({
		url : ctx + "/xzcf/ybcf/jasp/exportjasp/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
//导出强制执行word
function fileexportqzzx() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.qzflag !='1') {
		top.layer.confirm('您还没有添加强制执行记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	
	$.ajax({
		url : ctx + "/xzcf/ybcf/qzzx/exportqzzx/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
//导出事先催告表
function fileexportsxcg() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.cgflag!=1) {
		top.layer.confirm('您还没有添加事先催告记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}
	$.ajax({
		url : ctx + "/xzcf/ybcf/sxcg/exportsxcg/la/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
