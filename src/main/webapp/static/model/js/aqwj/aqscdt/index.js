var dg;
var d;
$(function(){
	dg=$('#issue_scdt_dg').datagrid({    
	method: "post",
    url:ctx+'/issue/aqscdt/list', 
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
        {field:'m1',title:'文件名称',sortable:false,width:100},  
        {field:'m4',title:'备注',sortable:false,width:60,align:'center'},
        {field:'S1',title:'发布时间',sortable:false,width:60,align:'center',
        	formatter : function(value){
        		if(value!=null&&value!=''){
            		var date = new Date(value);
    	        	return date.format("yyyy-MM-dd hh:mm:ss"); 
            	}else{
            		return '';
            	}
        }}
     
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    		view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#issue_scdt_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加动态信息", ctx + "/issue/aqscdt/create/","900px", "600px","");
}
//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改文件信息",ctx+"/issue/aqscdt/update/"+row.ID,"900px", "600px","");
	
}

//初始化
function onloadEditor(){

	var KConfigForFile = {
			uploadJson : ctx + '/kindeditor/upload.shtml',
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/issue/aqscdt/view/"+row.ID,"生产动态信息查看");
	
}

//创建查询对象并查询
function search(){
	//优化日期判断
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/issue/aqscdt/delete/"+ids,
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
