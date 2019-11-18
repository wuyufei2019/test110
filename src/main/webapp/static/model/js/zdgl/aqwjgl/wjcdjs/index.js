var dg;
var d;
$(function(){
	dg=$('#zdgl_cdjs_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/cdjs/list', 
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
        {field:'m1',title:'文件名称',sortable:false,width:80,
        	formatter : function(value, row, index) {
	          	return "<a onclick='view3("+row.id+")'>"+value+"</a>";
          	} 
        },
        {field:'m2',title:'文件编号',sortable:false,width:60,align:'center'},
        {field:'fbr',title:'发布人',sortable:false,width:50,align:'center'},
        {field:'s1',title:'发布日期',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'cyqk',title:'传阅情况',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	return "<a onclick='view1("+row.id+")'>"+row.yck+"/"+row.zs+"</a>";
          	} 
        },
        {field:'xzqk',title:'下载情况',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	return "<a onclick='view2("+row.id+")'>"+row.yxz+"/"+row.zs+"</a>";
          	} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zdgl_cdjs_tb'
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

//查看传阅情况
function view1(id){
	openDialogView("查看传阅情况",ctx+"/zdgl/cdjs/view1/"+id,"800px", "400px","");
}

//查看下载情况
function view2(id){
	openDialogView("查看下载情况",ctx+"/zdgl/cdjs/view2/"+id,"800px", "400px","");
}

//在线查看文件
function view3(id){
	window.open(ctx+"/zdgl/wjfb/view2/"+id, "安全文件发布");
}