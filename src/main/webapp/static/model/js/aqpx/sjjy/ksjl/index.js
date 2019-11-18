var dg;
var d;

$(function(){   
	dg=$('#aqpx_pxjl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/aqpxjl/kslist',
    queryParams: {          
        kclx: 2            
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
	    {field:'id',title:'id',checkbox:true,width:50,align:'center'},   
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
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_pxjl_tb'
	});
	
});


//考试记录查询
function search(){
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	obj.kclx=2;
	$('#aqpx_pxjl_dg').datagrid('load',obj); 
}
//清空
function clearA(){
	$("#aqpx_pxjl_searchFrom").form("clear");
	var obj=$("#aqpx_pxjl_searchFrom").serializeObject();
	obj.kclx=2;
	$('#aqpx_pxjl_dg').datagrid('load',obj);
}
//学习记录datagrid
$(function(){   
	
	$('#aqpx_kssc_dg').datagrid({    
		nowrap:false,
		method: "post",
		url:ctx+'/aqpx/sjjy/kssclist', 
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
		          {field:'name',title:'员工名',sortable:false,width:40 },
		          {field:'starttime',title:'第一次学习日期',sortable:false,width:100,align:'center',
		        	  formatter:function(value,row,index){
		        		  if(value!=null){
		        			  var datetime = new Date(value);  
		        			  return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
		           }},
	        	  {field:'endtime',title:'最后一次学习日期',sortable:false,width:100,align:'center',
	        		  formatter:function(value,row,index){
	        			  if(value!=null){
	        				  var datetime = new Date(value);  
	        				  return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
	        	  }},    
		        		  
		          {field:'total',title:'累计学习时长（分钟）',sortable:false,width:40,align:'center',
		        			  formatter:function(value,row,index){
		        				  if(value!=null){
		        					  return parseInt(value/60);  }  
		          }}]],
        		  onDblClickRow: function (rowdata, rowindex, rowDomElement){
        			  
        		  },
        		  enableHeaderClickMenu: true,
        		  enableRowContextMenu: false,
        		  toolbar:'#aqpx_kssc_tb'
	    });
});


//学习记录查询
function search2(){
	var obj=$("#aqpx_pxjl_searchFrom2").serializeObject();
	obj.kclx=2;
	$('#aqpx_pxjl_dg2').datagrid('load',obj); 
}
//学习时长记录查询
function searchkssc(){
	var obj=$("#aqpx_kssc_searchFrom").serializeObject();
	$('#aqpx_kssc_dg').datagrid('load',obj); 
}

//清空
function reset(){
	$("#aqpx_kssc_searchFrom").form("clear");
	var obj=$("#aqpx_kssc_searchFrom").serializeObject();
	$('#aqpx_kssc_dg').datagrid('load',obj);
}
//清空
function clearA2(){
	$("#aqpx_pxjl_searchFrom2").form("clear");
	var obj=$("#aqpx_pxjl_searchFrom2").serializeObject();
	obj.kclx=2;
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

//删除考试记录
function delKs(){
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
			url:ctx+"/aqpx/aqpxjl/deleteKs/"+ids,
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