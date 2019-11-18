var dg;
var d;
$(function(){   
	dg=$('#aqpx_pxjl_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/aqpx/aqpxjl/jllistbyjh', 
	queryParams : {
		'jhid' : jhid,
		'type' : type
	},
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[    
        {field:'name',title:'未参加考试员工姓名',sortable:false,width:80 },    
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	//viewsj();
    	
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['username']);
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#aqpx_pxjl_tb'
	});
	
});


//考试记录查询
function cx(){
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//重置
function clear1(){
	$("#aqpx_pxjl_searchFrom").form("clear");
	$("input[name='jhid']").val(jhid);
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}




////查看试卷信息
//function viewsj(){
//	var row = dg.datagrid('getSelected');
//	layer.open({
//		title:'考试记录',
//	    type: 2,  
//	    shift: 1,
//	    area: ['100%', '100%'],
//	    title: false,
//        maxmin: false,
//        closeBtn:0,
//	    content: ctx+'/aqpx/aqpxjl/view/'+row.h,
//	    btn:['关闭']
//	}); 
//}
