var dg;
var d;
$(function(){
	dg=$('#zdgl_wjck_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/wjck/list', 
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
        //id是传递接收id  文件id为wjid
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
        {field:'wjname',title:'文件名称',sortable:false,width:80},
        {field:'fbr',title:'发布人',sortable:false,width:50,align:'center'},
        {field:'s1',title:'发布日期',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'m2',title:'阅读情况',sortable:false,width:50,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value == '1') {
	          		return '已查看';  
	          	}else{
	          		return '未查看';  
	          	}
          	} 
        },
        {field:'m3',title:'下载情况',sortable:false,width:50,align:'center',
	    	formatter : function(value, row, index) {
	    		if(value == '1') {
	          		return '已下载';  
	          	}else{
	          		return '未下载';  
	          	}	
          	} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zdgl_wjck_tb'
	});
});

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
}

//在线查看文件
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/zdgl/wjck/view?wjid="+row.wjid+"&id="+row.id, "安全文件查看");
//	var o = window.open(ctx+"/zdgl/wjck/view?wjid="+row.wjid+"&id="+row.id, "安全文件查看");
//	setTimeout(function(){
//	    if(o){
//	    	dg.datagrid('reload');
//	    }
//	},100);
}