var dg;
var d;
$(function(){
	dg=$('#issue_cdjs_dg').datagrid({    
	method: "post",
    url:ctx+'/issue/wjcdjs/list', 
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
        {field:'wj',title:'文件名称',sortable:false,width:100,align:'center'},    
        {field:'qy',title:'企业名称',sortable:false,width:100,align:'center'},
        {field:'M1',title:'查阅情况',sortable:false,width:50,align:'center',
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
        {field:'M5',title:'回执情况',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        		if(value==1){
        			return '已回执';
        		}
        		return '未回执';
        	}
        },
        {field:'M3',title:'下载时间',sortable:false,width:60,align:'center',formatter : function(value){
        	if(value==null||value==''){
        		return '';	
        	}else{
        		var date = new Date(value);
        		var y = date.getFullYear();
        		var m = date.getMonth() + 1;
        		var d = date.getDate();
        		var hh=date.getHours();
        		var mm=date.getMinutes();
        		var ss= date.getSeconds();
        		return y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d)+'  '+(hh<10?('0'+hh):hh)+':'+(mm<10?('0'+mm):mm)+':'+(ss<10?('0'+ss):ss);
        	}
        }},
        {field:'S1',title:'发布时间',sortable:false,width:60,align:'center',formatter : function(value){
        	if(value==null||value=='')
            	return '';	
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            var hh=date.getHours();
            var mm=date.getMinutes();
            var ss= date.getSeconds();
            return y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d)+'  '+(hh<10?('0'+hh):hh)+':'+(mm<10?('0'+mm):mm)+':'+(ss<10?('0'+ss):ss);
        }}
     
    ]],
   
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['wj']);
    },
    onDblClickRow : function(rowdata, rowindex, rowDomElement) {
		view();
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#issue_cdjs_tb'
	});
});

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看回执信息",ctx+"/issue/wjcdjs/view/"+row.ID,"600px", "300px","");
}

/*
//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改车间信息",ctx+"/bis/cjxx/update/"+row.id,"800px", "400px","");
	
}


	
}*/

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
			url:ctx+"/issue/wjcdjs/delete/"+ids,
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

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'wj', coltext:'文件名称'},
			   		{colval:'qy', coltext:'企业名称'},
			   		{colval:'M1', coltext:'查阅情况'},
			   		{colval:'M2', coltext:'下载情况'},
			   		{colval:'M3', coltext:'下载时间'},
			   		{colval:'S1', coltext:'发布时间'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/issue/wjcdjs/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}