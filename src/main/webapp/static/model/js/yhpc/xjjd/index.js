var dg1;
var d;
var yf;

$(function(){   
	dg1=$('#yhpc_xjjd_dg1').datagrid({    
	method: "post",
    url:ctx+'/yhpc/xjjd/zjlist', 
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
        {field:'qyname',title:'企业名称',width:90,align:'center'},
        {field:'yccs',title:'应巡检次数',width:50,align:'center'},
        {field:'yxcs',title:'有效巡查次数',width:50,align:'center'},
        {field:'bl',title:'巡检率',width:50,align:'center',
        	formatter: function(value, row, index){
    			return value+"%";
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
	
});

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
	search1();
}

