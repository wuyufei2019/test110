var dg;
var dg2;
var d;
$(function() {
	dg = $('#issue_aqwj_dg').datagrid(
			{
				method : "get",
				url : ctx + '/issue/zfwjfb/list',
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
							field : 'm1',
							title : '文件名称',
							sortable : false,
							width : 100 
						},
						{
							field : 'm5',
							title : '文件类型',
							sortable : false,
							width : 40,
							align : 'center'
						},
						{
							field : 'm4',
							title : '备注',
							sortable : false,
							width : 50,
							align : 'center'
						},

						{
							field : 's1',
							title : '发布时间',
							sortable : false,
							width : 60,
							align : 'center',
							formatter : function(value) {
								if (value != null && value != '') {
									var dt = new Date(value); 
						        	return dt.format("yyyy-MM-dd hh:mm:ss"); 
								} else {
									return '/';
								}
							}
						},
						
						{
							field : 'yd',
							title : '状态(点击查看)',
							sortable : false,
							width : 100,
							align : 'center',
							formatter : function(value, row, index) {
								var html= "<span class='fa fa-check btn-primary btn-outline' onclick='showqylist("+ row.id + ",1,1)'>已读"+ row.yd + "</span>&nbsp;&nbsp;"+"<span class='fa fa-close btn-warning btn-outline' onclick='showqylist("+ row.id + ",0,1)'>未读"+ row.wd + "</span>&nbsp;&nbsp;"+
								"<span class='fa fa-download btn-success btn-outline' onclick='showqylist("+ row.id + ",1,2)'>已下载"+ row.yxz + "</span>&nbsp;&nbsp;"+"<span class='fa fa-download btn-danger btn-outline' onclick='showqylist("+ row.id + ",0,2)'>未下载"+ row.wxz + "</span>";
								return html;
						}
						},
						{
							field : 'zfids',
							title : '转发文件至企业',
							sortable : false,
							width : 40,
							align : 'center',
							formatter : function(value, row, index) {
								return "<a  class='btn btn-info btn-xs' onclick='addQy("
											+ row.id2 + ","+row.id+")'>文件转发</a>";
						}
						}

				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#issue_aqwj_tb'
			});


});

$(function() {
	dg2=$('#issue_aqwj_dg2').datagrid({    
		method: "post",
	    url:ctx+'/issue/zfwjfb/list2', 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'ID',
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
	        {field:'M1',title:'文件名称',sortable:false,width:100 },    
	        {field:'M5',title:'文件类型',sortable:false,width:50,align:'center'},  
	        
	        {field:'look',title:'查阅情况',sortable:false,width:50,align:'center',
	        	formatter : function(value, row, index) {
	        	 if(value==1){
	        		 return '已查看';
	        	 }
	           	 return '未查看';
	        	}
	        },
	        {field:'M2',title:'下载情况',sortable:false,width:50,align:'center',
	        	formatter : function(value, row, index) {
	        	 if(value==1){
	        		 return '已下载';
	        	 }
	           	 return '未下载';
	        	}
	        },
	        {field:'S2',title:'更新时间',sortable:false,width:60,align:'center',formatter : function(value){
	        	if(value!=null&&value!=''){
	        		var date = new Date(value);
		        	return date.format("yyyy-MM-dd hh:mm:ss"); 
	        	}else{
	        		return '';
	        	}
	        }},
	        {field:'M4',title:'备注',sortable:false,width:100 }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	                 view2();
	    },
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['wj']);
	        $.ajax({
	        	   url: ctx+"/issue/aqwjfb/searchNoRead2",
	               dataType: "text",
	               success: function(data){
	            	   if(data!=null&&data>0){
	            		   layer.open({icon:1,title: '提示',offset: 'rb',content:"您有" + data + "个文件未查看，请及时查阅！",shade: 0 ,time: 3000 });
	            	   }
	               }
	        }
	        );
	    },
	  
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#issue_aqwj_tb2'
		});		
});

// 弹窗增加
function add(u) {
	openDialog("添加文件信息", ctx + "/issue/zfwjfb/create/", "900px", "550px", "");
}

//弹出企业选择框
function addQy(id2,id) {
	//选择企业需要的条件
	var wjid=0;
	if(id2!=null){
		wjid=id2;
	}
	//更新需要的条件
	var url;
	if(id2==null){
		//如果以前未转发给企业，则传政府文件id过去添加
		url=ctx+"/issue/zfwjfb/createSub2";
	}else{
		url=ctx+"/issue/zfwjfb/updateqyids";
		//如果已经转发过，则传企业文件id修改企业文件的qyid就可以
		id=id2;
	}
	layer.open({
	    type: 2,  
	    area: ['600px', '400px'],
	    title: '选择企业',
        maxmin: false, 
	    content: ctx + "/issue/zfwjfb/chooseqy/"+wjid,
	    btn: ['发送', '关闭'],
	    yes: function(index, layero){
	    	var index2=layer.load();
	    	//$.jBox.tip("正在提交，请稍等...",'loading',{opacity:1});
	    	var iframeWin = layero.find('iframe')[0];
	    	var ids=iframeWin.contentWindow.getqyids();
	    	$.ajax({
	    		type:"post",
	    		url:url,
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
					});}
	    			
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
	    			layer.close(index2);//关闭load层
	    			
	    		}
	    	});
	    },
	cancel: function(index){}
	}); 
}



// 弹出政府人员选择框
function openzflist(flag,id) {
	var f;
	if(flag=='createSub')
		f='add';
	else
		f=id;
	layer.open({
	    type: 2,  
	    area: ['600px', '400px'],
	    title: '选择政府人员',
        maxmin: false, 
	    content: ctx + "/issue/zfwjfb/choose/"+f ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var $list = $("#qyList");
	    	//var body = layer.getChildFrame('body', index);
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname(f);
	        var arr1=idname.split(",");
	        var ids=$("#zfids").val();
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
			$("#zfids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}
// 删除预览企业
function removeQy(fileid) {
	var ids = $("#zfids");
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
				width : '700',
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
			url : ctx + "/issue/zfwjfb/delete/" + ids,
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
	top.layer.confirm('修改信息将会重新给企业发布新文件信息，如需补发请点击文件补发。', {
		icon : 3,
		title : '提示'
	}, function(index) {
		openDialog("修改文件信息", ctx + "/issue/zfwjfb/update/" + row.id, "900px","550px", "");
		top.layer.close(index);
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

	window.open(ctx + "/issue/zfwjfb/view/" + row.id, "信息查看");

}

//查看2
function view2() {
	var row = dg2.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}

	window.open(ctx + "/issue/zfwjfb/view/" + row.ID, "信息查看");

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

//创建查询对象并查询
function search2() {
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}
 

function reset2() {
	$("#searchFrom2").form("clear");
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}

/**
 * 显示文件查看和下载情况的具体企业
 * wjid 文件id
 * state 状态 （1是 0否）
 * type 类别（1查看 2下载）
 */
function showqylist(wjid,state,type){
	openDialogView("查看企业",ctx+"/issue/zfwjfb/showqylist?wjid="+wjid+"&state="+state+"&type="+type,"400px", "400px","");
	
}
