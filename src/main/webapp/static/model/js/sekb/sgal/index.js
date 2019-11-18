var dg;
var d;
$(function(){
	dg=$('#sekb_sgal_dg').datagrid({    
	method: "post",
    url:ctx+'/sekb/sgal/list', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
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
              {field:'m1',title:'类别',width:25},
              {field:'m2',title:'标题',width:100}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sekb_sgal_tb'
	});
	
});

//弹窗增加
function add(u) {
openDialog("添加事故案例信息",ctx+"/sekb/sgal/create/","800px", "400px","");
	
	
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
			url:ctx+"/sekb/sgal/delete/"+ids,
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
	
	openDialog("修改事故案例信息",ctx+"/sekb/sgal/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/sekb/sgal/view/"+row.ID, "事故案例");
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
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
		'#textarea_kindM3', 
		$.extend({},KEditorConfig,{
    		width: '700', 
    		items:[ 'source', '|', 'undo', 'redo', '|', 'justifyleft','justifycenter','justifyright','|',
					'fontsize','forecolor','hilitecolor','bold','italic','|','quickformat','|','image','|','link','fontname','fullscreen'
				  ]
    	 })
	);
 
}

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/sekb/sgal/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
	
}
