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
        {field:'username',title:'员工姓名',sortable:false,width:80 },    
        {field:'kcname',title:'课程名称',sortable:false,width:80 },    
        {field:'s1',title:'考试时间',sortable:false,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
                }},
        {field:'m1',title:'考试成绩',sortable:false,width:80,align:'center'},    
        {field:'m3',title:'考试结果',sortable:false,width:80,align:'center'},
        {field:'m2',title:'用时(分钟)',sortable:false,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                return parseInt(value/60)+":"+value%60;  }  
                }},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	//viewsj();
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
