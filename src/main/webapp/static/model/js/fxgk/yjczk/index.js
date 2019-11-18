var dg;
var d;
   
$(function() {
   var url = ctx + '/fxgk/yjczk/list';
   dg = $('#fxgk_yjczk_dg').datagrid({
	   method : "post",
	   url : url,
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
		   {field:'id',title:'id',checkbox:true,width:150,align:'center'},
		   {field:'m1',title:'岗位名称',sortable:false,width:100,align:'center'},
		   {field:'m4',title:'本企业救援队',sortable:false,width:100,align:'center'},
		   {field:'m5',title:'应急负责人',sortable:false,width:100,align:'center'},
		   {field:'m6',title:'控制室',sortable:false,width:100,align:'center'},
		   {field:'m7',title:'工厂总经理',sortable:false,width:100,align:'center'},
		   {field:'m8',title:'班长',sortable:false,width:100,align:'center'},
		   {field:'m9',title:'当地应急响应中心',sortable:false,width:100,align:'center'},
	       {field:'dc',title:'导出应急处置卡',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
                   return "<a style='margin:2px' class='btn btn-info btn-xs' onclick='exportword("
							+ row.id + ")'>导出</a>";
	        	}
	       }
	   ] ],
	   onDblClickRow : function(rowdata, rowindex, rowDomElement) {
		   view();
	   },
	   checkOnSelect : false,
	   selectOnCheck : false,
	   toolbar : '#fxgk_yjczk_tb'
   });
});

//弹窗增加
function add(u) {
	openDialog("添加应急处置卡信息",ctx+"/fxgk/yjczk/create/","800px", "400px","");
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
			url:ctx+"/fxgk/yjczk/delete/"+ids,
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
	
	openDialog("修改应急处置卡信息",ctx+"/fxgk/yjczk/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急处置卡信息",ctx+"/fxgk/yjczk/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchForm").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchForm").form("clear");
	var obj=$("#searchForm").serializeObject();
	dg.datagrid('load',obj); 
}

//初始化
function onloadEditor(){

	var KConfigForFile = {
		uploadJson : ctx + '/kindeditor/uploadFile.shtml',
		fileManagerJson : ctx + '/kindeditor/manager.shtml',
		allowFileManager : false
	};

	var KEditorConfig= {
		uploadJson : ctx + '/kindeditor/uploadFile.shtml',
		fileManagerJson : ctx + '/kindeditor/manager.shtml',
		allowFileManager : false,
		filterMode: true,
		afterBlur: function(){this.sync();},
		afterChange : function() {

		},
		pasteType: 1,
		afterCreate: function () {
			var self = this;
			KindEditor.ctrl(document, 13, function () {
				self.sync();
				KindEditor('form[name=form1]')[0].submit();
			});
			KindEditor.ctrl(self.edit.doc, 13, function () {
				self.sync();
				KindEditor('form[name=form1]')[0].submit();
			});
		}
	};

	var upEditor = KindEditor.editor(KConfigForFile);

	//渲染富文本
	window.editor = KindEditor.create(
		'#textarea_kindM2',
		$.extend({},KEditorConfig,{
			width: '600',
			items:[ 'source', '|', 'undo', 'redo', '|', 'justifyleft','justifycenter','justifyright','|',
				'fontsize','forecolor','hilitecolor','bold','italic','|','quickformat','|','image','|','link','fontname','fullscreen'
			]
		})
	);

	//渲染富文本
	window.editor = KindEditor.create(
		'#textarea_kindM3',
		$.extend({},KEditorConfig,{
			width: '600',
			items:[ 'source', '|', 'undo', 'redo', '|', 'justifyleft','justifycenter','justifyright','|',
				'fontsize','forecolor','hilitecolor','bold','italic','|','quickformat','|','image','|','link','fontname','fullscreen'
			]
		})
	);

}

function exportword(id){
	/*var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}*/
	$.post(ctx + "/fxgk/yjczk/exportword/" + id,function(data){
		window.open(ctx+data);
	});
}
