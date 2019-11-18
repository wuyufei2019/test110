var dg;
var d;

$(function(){   
	dg=$('#aqpx_pxjl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/aqpxjl/kslist', 
    queryParams: {          
        kclx: 1            
    },
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
	pageList : [20, 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[
        {field:'qyname',title:'企业名称',sortable:true,width:80 },      
		{field:'yg',title:'员工名',sortable:true,width:80 },
        {field:'kc',title:'课程名称',sortable:true,width:150 },    
        {field:'s1',title:'考试时间',sortable:true,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
                }},
        {field:'m1',title:'考试成绩',sortable:true,width:80,align:'center'},    
        {field:'m3',title:'考试结果',sortable:true,width:80,align:'center'},    
        {field:'m2',title:'用时(分钟)',sortable:true,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
     		    var minute=Math.floor(value/60%60);
     		    if(minute<10){
     		    	minute="0"+minute;
     		    }
     		    var second=Math.floor(value%60);
     		    if(second<10){
     		    	second="0"+second;
     		    }
                return minute+":"+second; 
            }  
            }},
        {field:'h',title:'试卷标识',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	viewsj();
    },
    onLoadSuccess: function(){
       /* $(this).datagrid("autoMergeCells",['qyname']);*/
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_pxjl_tb'
	});
	
});


//考试记录查询
function search(){
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	obj.kclx=1;
	$('#aqpx_pxjl_dg').datagrid('load',obj); 
}
//清空
function clearA(){
	$("#aqpx_pxjl_searchFrom").form("clear");
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	obj.kclx=1;
	$('#aqpx_pxjl_dg').datagrid('load',obj);
}
//学习记录datagrid
$(function(){   
	$('#aqpx_pxjl_dg2').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/aqpx/aqpxjl/xxlist', 
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
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[    
		{field:'qyname',title:'企业名称',sortable:true,width:80 },
		{field:'yg',title:'员工名',sortable:true,width:80 },
        {field:'kc',title:'课程名称',sortable:true,width:150 },    
        {field:'m2',title:'开始时间',sortable:true,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
                }},
        {field:'m3',title:'结束时间',sortable:true,width:80,align:'center',
                	formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
                }},    
        
        {field:'m1',title:'学习时长(分钟)',sortable:true,width:100,align:'center',
        	formatter:function(value,row,index){
     		if(value!=null){
                return parseInt(value/60)+":"+value%60;  }  
                }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
        /*$(this).datagrid("autoMergeCells",['qyname']);*/
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#aqpx_pxjl_tb2'
	});
	
});


//学习记录查询
function search2(){
	var obj=$("#aqpx_pxjl_searchFrom2").serializeObject();
	obj.kclx=1;
	$('#aqpx_pxjl_dg2').datagrid('load',obj); 
}

//清空
function clearA2(){
	$("#aqpx_pxjl_searchFrom2").form("clear");
	var obj=$("#aqpx_pxjl_searchFrom2").serializeObject();
	obj.kclx=1;
	$('#aqpx_pxjl_dg2').datagrid('load',obj);
}
//查看试卷信息
function viewsj(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	layer.open({
		title:'试卷信息',
	    type: 2,  
	    shift: 1,
	    area: ['100%', '100%'],
	    title: false,
        maxmin: false,
        closeBtn:0,
	    content: ctx+'/aqpx/aqpxjl/view/'+row.h,
	    btn:['关闭']
	}); 
}