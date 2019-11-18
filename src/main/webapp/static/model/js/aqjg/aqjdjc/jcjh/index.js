var dg;
var d;
$(function() {
	
	dg = $('#aqjg_jcjh_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqjd/jcjh/list',
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
							field : 'm2',
							title : '计划时间',
							sortable : false,
							width : 40,
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
							width : 100,
							align : 'center'
						},
						
						{
							field : 'm4',
							title : '备注',
							sortable : false,
							width : 100,
							align : 'center'
						},
						{
							field : 'qyids',
							title : '计划检查企业数',
							sortable : false,
							width : 40,
							align : 'center',
							formatter : function(value){
								return value.split(",").length-1;
							}
						},
						{
							field : 'schedule',
							title : '进度',
							sortable : false,
							width : 100,
							align : 'center',
							formatter : function(value) {
					        var htmlstr = '<div name="p" class="easyui-progressbar" data-options="value:100" style="width:100%;"></div> ';  
					        return htmlstr; 
				            	}
						},
						{
							field : 'caozuo',
							title : '添加新增企业至检查计划',
							sortable : false,
							width : 50,
							align : 'center',
							formatter : function(value, row, index) {
								return "<a  class='btn btn-info btn-xs' onclick='addQy("
											+ row.ID + ")'>添加企业</a>";
						}
						}
						
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					$(this).datagrid("autoMergeCells", [ 'm2' ]);
					var sum=0,sum2=0,len=0,tip,arr;//sum完成初查，sum2完成复查,tip提示框
					for(var i=0;i<rowdata.total;i++){
						var row=rowdata.rows[i];
						arr =row.schedule.split(";");
						len =row.qyids.split(",").length-1;
						sum=parseInt(arr[0]==''?0:arr[0]);
						sum2=parseInt(arr[1]==''?0:arr[1]);
						tip='共选择'+len+'家企业，企业只完成初查：'+(sum)+';完成复查：'+sum2; 	
						tip='<a href="#" title="'+tip+'" onclick="openResult('+row.ID+',\''+row.m1+'\')" class="easyui-tooltip">'+(isNaN(sum2/len)?0:(sum2/len*100).toFixed(2))+'%</a>';
						$("[name=p]:eq("+i+")").progressbar(
								{value:((sum2/len)*100).toFixed(2),//进度百分比
								text:tip//显示值
								}
						);  
					}
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqjg_jcjh_tb'
			});
});

// 弹窗增加
function add(u) {
	openDialog("添加检查计划", ctx + "/aqjd/jcjh/create/", "800px", "400px", "");
}

//弹出企业选择框
function addQy(id) {
	layer.open({
	    type: 2,  
	    area: ['600px', '350px'],
	    title: '选择企业',
        maxmin: false, 
	    content: ctx + "/aqjd/jcjh/choose/"+id,
	    btn: ['发送', '关闭'],
	    yes: function(index, layero){
	    	var iframeWin = layero.find('iframe')[0];
	    	var ids=iframeWin.contentWindow.getqyids();
	    	$.ajax({
	    		type:"post",
	    		url:ctx+"/aqjd/jcjh/updateqyids",
	    		data:{id:id,qyids:ids},
	    		success:function(data){
	    			if(data=="success"){
	    			parent.layer.open({
						icon : 1,
						title : '提示',
						offset : 'rb',
						content : '发送成功！',
						shade : 0,
						time : 2000
					});
	    			
	    			}
	    			
	    			else{
	    				parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '发送失败！',
							shade : 0,
							time : 2000
						});
	    			}
	    			layer.close(index);//关闭对话框。
	    			$("#searchFrom").form("clear");
	    			var obj = $("#searchFrom").serializeObject();
	    			dg.datagrid('load', obj);
	    			
	    		}
	    	});
	    },
	cancel: function(index){}
	}); 	
}

function openResult( id,  m1){
	openDialogView(m1+"：检查结果",ctx+"/aqjd/jcjh/jhresult/"+id,"800px", "400px","");
}
//向子页面发送qyids
function  getqyids(){
	return $("#qyids").val();
}
// 弹出企业选择框
function openqylist() {
	layer.open({
	    type: 2,  
	    area: ['600px', '300px'],
	    title: '选择企业',
        maxmin: false, 
	    content: ctx + "/aqjd/jcjh/choose/add" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#qyList");
	    	$list.html("");
	    	//var body = layer.getChildFrame('body', index);
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids="";
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					// 企业名称拼接
					var $li = $("<div id=\""
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeQy('"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#tip").hide();
			$("#qyids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
	
}
// 删除预览企业
function removeQy(fileid) {
	var ids = $("#qyids");
	var qy = ids.val();

	if (qy.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(qy.replace(fileid + ",", ""));
	}
	$("#" + fileid).remove();

};

// 初始化
function onloadEditor() {

	var KConfigForFile = {
		uploadJson : ctx + '/kindeditor/upload.shtml',
		fileManagerJson : ctx + '/kindeditor/manager.shtml',
		allowFileManager : false
	};

	var KEditorConfig = {
		uploadJson : ctx + '/kindeditor/upload.shtml',
		fileManagerJson : ctx + '/kindeditor/manager.shtml',
		allowFileManager : false,
		filterMode : true,
		afterBlur : function() {
			this.sync();
		},
		afterChange : function() {

		},
		pasteType : 1,
		afterCreate : function() {
			var self = this;
			KindEditor.ctrl(document, 13, function() {
				self.sync();
				KindEditor('form[name=form1]')[0].submit();
			});
			KindEditor.ctrl(self.edit.doc, 13, function() {
				self.sync();
				KindEditor('form[name=form1]')[0].submit();
			});
		}
	};

	var upEditor = KindEditor.editor(KConfigForFile);

	// 渲染富文本
	window.editor = KindEditor.create('#textarea_kindM3', $.extend({},
			KEditorConfig, {
				width : '600',
				items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft',
						'justifycenter', 'justifyright', '|', 'fontsize',
						'forecolor', 'hilitecolor', 'bold', 'italic', '|',
						'quickformat', '|', 'image', '|', 'link', 'fontname',
						'fullscreen' ]
			}));

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
			ids = row[i].ID;
		} else {
			ids = ids + "," + row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/aqjd/jcjh/delete/" + ids,
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
	openDialog("修改计划信息", ctx + "/aqjd/jcjh/update/" + row.ID, "900px",
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

	window.open(ctx + "/aqjd/jcjh/view/" + row.ID, "信息查看");

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

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/aqjd/jcjh/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
	}
