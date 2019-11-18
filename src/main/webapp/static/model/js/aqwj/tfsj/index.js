var dg;
var d;
$(function(){
	dg=$('#issue_tfsj_dg').datagrid({    
	method: "post",
    url:ctx+'/issue/tfsj/list', 
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
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'标题',sortable:false,width:80},  
        {field:'m3',title:'发生地点',sortable:false,width:60,align:'center'},
        {field:'m4',title:'发生时间',sortable:false,width:40,align:'center',
        	formatter : function(value){
        		if(value!=null&&value!=''){
            		var date = new Date(value);
    	        	return date.format("yyyy-MM-dd hh:mm:ss"); 
            	}else{
            		return '';
            	}
        	}
        },
        {field:'fbr',title:'发布人',sortable:false,width:30,align:'center'},
        
        {field:'m5',title:'状态',sortable:false,width:40,align:'center',
        	formatter : function(value){
        		if(value == '1'){
    	        	return "未处理"; 
            	}else if(value == '2'){
            		return "处理中"; 
            	}else if(value == '3'){
            		return "处理完成"; 
            	}
        	}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    		view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#issue_tfsj_tb'
	});
	
});

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//重置
function reset(){
	$("#searchFrom").form("clear");
	search(); 
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
			url:ctx+"/issue/tfsj/delete/"+ids,
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

//弹窗增加
function add(u) {
	openDialog("添加信息", ctx + "/issue/tfsj/create/","100%", "100%","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改信息",ctx+"/issue/tfsj/update/"+row.id,"100%", "100%","");
	
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
  		width: '100%', 
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
	openDialogView("查看突发事件信息",ctx+"/issue/tfsj/view/"+row.id,"1000px","100%");
	
}

//结束评论
function jspl(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m5 == '3'){
		layer.msg("该事件已处理完成！",{time: 1000});
		return;
	}else{
		openDialog("结束处理意见",ctx+"/issue/tfsj/jspl/"+row.id,"800px", "400px");
	}
}
