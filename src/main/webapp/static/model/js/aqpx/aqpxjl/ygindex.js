var dg;
var d;
$(function(){   
	dg=$('#aqpx_pxjl_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/aqpx/aqpxjl/kslistyg', 
    queryParams: {          
        kclx: 1            
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
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[    
        {field:'kc',title:'课程名称',sortable:true,width:80 },    
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
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#aqpx_pxjl_tb'
	});
	
});


//考试记录查询
function cx(){
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	obj.kclx=1;
	dg.datagrid('load',obj); 
}

//学习记录datagrid
$(function(){   
	$('#aqpx_pxjl_dg2').datagrid({    
	method: "post",
    url:ctx+'/aqpx/aqpxjl/xxlistyg', 
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
        {field:'kc',title:'课程名称',sortable:true,width:80 },    
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
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#aqpx_pxjl_tb2'
	});
	
});


//学习记录查询
function cx2(){
	var obj=$("#aqpx_pxjl_searchFrom2").serializeObject();
	obj.kclx=1;
	$('#aqpx_pxjl_dg2').datagrid('load',obj); 
}

//查看试卷信息
function viewsj(){
	var row = dg.datagrid('getSelected');
	$("<div id='aqpx_pxjl_add_div'></div>").window({
		title:'试卷信息',
		collapsible:false,
		minimizable:false,
		maximizable:false,
		width:$(window).width(),
		height:$(window).height(),
		modal:true,
		href:ctx+'/aqpx/aqpxjl/view/'+row.h,
		onClose: function () { 
              $("#aqpx_pxjl_add_div").window('destroy');//销毁dialog
        }
	});
}