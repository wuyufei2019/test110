var dg1;
var d;
var yf;
var flag;//当前所查类型标识
$(function(){   
	tjdatagrid(1);
});

//按类型统计
function tjdatagrid(flag){
	if(flag==1){
		dg1=$('#yhpc_xjjd_dg1').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/xjjd/zjlist2',
            queryParams: {          
               tjlx: 1            
            },  
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'qyid',
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
		        {field:'username',title:'人员名称',width:90,align:'center'},
		        {field:'yccs',title:'应巡检次数',width:50,align:'center'},
		        {field:'yxcs',title:'有效巡查次数',width:50,align:'center'},
		        {field:'wts',title:'发现隐患数',width:50,align:'center'},
		        {field:'zgs',title:'隐患整改数',width:50,align:'center'},
		        {field:'bl',title:'巡检率',width:50,align:'center',
		        	formatter: function(value, row, index){
		    			return value +"%";
		        	}
		    	},
		        {field:'RowNumber',title:'排名',width:50,align:'center'}
		    ]],
		    onLoadSuccess: function(){
		    },
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    },
			checkOnSelect:false,
			selectOnCheck:false,
		    toolbar:'#yhpc_xjjd_tb1'
		});
	}
	if(flag==2){
		dg1=$('#yhpc_xjjd_dg1').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/xjjd/zjlist2',
            queryParams: {          
                tjlx: 2            
            },
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'qyid',
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
		        {field:'pointname',title:'隐患点名称',width:90,align:'center'},
		        {field:'yccs',title:'应巡检次数',width:50,align:'center'},
		        {field:'yxcs',title:'有效巡查次数',width:50,align:'center'},
		        {field:'wts',title:'发现隐患数',width:50,align:'center'},
		        {field:'zgs',title:'隐患整改数',width:50,align:'center'},
		        {field:'bl',title:'巡检率',width:50,align:'center',
		        	formatter(value, row, index){
		    			return value.toFixed(2)+"%";
		        	}
		    	},
		        {field:'RowNumber',title:'排名',width:50,align:'center'}
		    ]],
		    onLoadSuccess: function(){
		    },
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    },
			checkOnSelect:false,
			selectOnCheck:false,
		    toolbar:'#yhpc_xjjd_tb1'
		});	
	}
	if(flag==3){
		dg1=$('#yhpc_xjjd_dg1').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/xjjd/zjlist2',
            queryParams: {          
                tjlx: 3            
            },
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'qyid',
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
		        {field:'pointname',title:'风险点名称',width:90,align:'center'},
		        {field:'yccs',title:'应巡检次数',width:50,align:'center'},
		        {field:'yxcs',title:'有效巡查次数',width:50,align:'center'},
		        {field:'wts',title:'发现隐患数',width:50,align:'center'},
		        {field:'zgs',title:'隐患整改数',width:50,align:'center'},
		        {field:'bl',title:'巡检率',width:50,align:'center',
		        	formatter(value, row, index){
		    			return value.toFixed(2)+"%";
		        	}
		    	},
		        {field:'RowNumber',title:'排名',width:50,align:'center'}
		    ]],
		    onLoadSuccess: function(){
		    },
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    },
			checkOnSelect:false,
			selectOnCheck:false,
		    toolbar:'#yhpc_xjjd_tb1'
		});	
	}
	if(flag==4){
		dg1=$('#yhpc_xjjd_dg1').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/xjjd/zjlist2',
            queryParams: {          
                tjlx: 4            
            },
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'qyid',
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
		        {field:'planname',title:'班次名称',width:90,align:'center'},
		        {field:'yccs',title:'应巡检次数',width:50,align:'center'},
		        {field:'yxcs',title:'有效巡查次数',width:50,align:'center'},
		        {field:'wts',title:'发现隐患数',width:50,align:'center'},
		        {field:'zgs',title:'隐患整改数',width:50,align:'center'},
		        {field:'bl',title:'巡检率',width:50,align:'center',
		        	formatter(value, row, index){
		    			return value.toFixed(2)+"%";
		        	}
		    	},
		        {field:'RowNumber',title:'排名',width:50,align:'center'}
		    ]],
		    onLoadSuccess: function(){
		    },
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    },
			checkOnSelect:false,
			selectOnCheck:false,
		    toolbar:'#yhpc_xjjd_tb1'
		});	
	}
	
}

//月检统计查询
function search1(){
	var obj=$("#yhpc_xjjd_searchFrom1").serializeObject();
	$('#yhpc_xjjd_dg1').datagrid('load',obj); 
}

//清空
function clear1(){
	$("#yhpc_xjjd_searchFrom1").form("clear");
	var now=new Date();
	$('#yhpc_yjxjry_yf').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
    var curr_time = new Date();
    var strDate = curr_time.getFullYear()+"-";
    strDate += curr_time.getMonth()+1+"-";
    strDate += curr_time.getDate();
    var strDate2 = curr_time.getFullYear()+"-01-01";
    $("#yhpc_xjsj_start").datebox("setValue", strDate2); 
    $("#yhpc_xjsj_end").datebox("setValue", strDate); 
    $("#tjlx").combobox("setValue", flag); 
	search1();
}

