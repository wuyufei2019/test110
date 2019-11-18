var dg1;
var dg2;
var d;
var yf;
var nf;

//班次月检统计datagrid
$(function(){   
	dg1=$('#wghgl_bc_dg1').datagrid({    
	method: "post",
    url:ctx+'/wghgl/tjfx/bcyjlist', 
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
        //{field:'id',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'wgname',title:'所属网格',width:70},  
        {field:'name',title:'班次名称',width:100,align:'center'},  
        {field:'xccs',title:'应巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'wccs',title:'有效巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'e',title:'未巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (row.xccs==null || row.xccs=='' || row.xccs==undefined)
         			return '0';
         		else
         			return row.xccs-row.wccs;
            }
        },
        {field:'zccs',title:'正常次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'yccs',title:'异常次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        }
    ]],
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'wgname' ]);
    	yf = $('#wghgl_yjbc_yf').datebox('getValue');
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view1();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_bc_tb1'
	});
	
});


//班次月检统计查询
function search1(){
	var obj=$("#wghgl_bc_searchFrom1").serializeObject();
	$('#wghgl_bc_dg1').datagrid('load',obj); 
}

//清空
function clear1(){
	$("#wghgl_bc_searchFrom1").form("clear");
	var now=new Date();
	$('#wghgl_yjbc_yf').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
	search1();
}

//查看班次巡检记录
function view1() {
	var row = dg1.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看检查记录",ctx + "/wghgl/tjfx/bcview?bcid=" + row.id+"&yf="+yf,"800px", "400px","");
}

//班次年检统计datagrid
$(function(){   
	dg2=$('#wghgl_bc_dg2').datagrid({    
	method: "post",
    url:ctx+'/wghgl/tjfx/bcnjlist', 
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
        //{field:'id',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'wgname',title:'所属网格',width:70},  
        {field:'name',title:'班次名称',width:100,align:'center'},  
        {field:'xccs',title:'应巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'wccs',title:'有效巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'e',title:'未巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (row.xccs==null || row.xccs=='' || row.xccs==undefined)
         			return '0';
         		else
         			return row.xccs-row.wccs;
            }
        },
        {field:'zccs',title:'正常次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'yccs',title:'异常次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view2();
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'wgname' ]);
    	nf = $("#wghgl_njbc_nf").combobox('getValue');
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_bc_tb2'
	});
	
});


//班次年检统计查询
function search2(){
	$("#wghgl_njbc_nf").combobox('setValue',$("#wghgl_njbc_nf").combobox('getText'));
	var obj=$("#wghgl_bc_searchFrom2").serializeObject();
	$('#wghgl_bc_dg2').datagrid('load',obj); 
}

//清空
function clear2(){
	$("#wghgl_bc_searchFrom2").form("clear");
	var now=new Date();
	$("#wghgl_njbc_nf").combobox('setValue',now.getFullYear());
	search2();
}

//查看巡检记录
function view2() {
	var row = dg2.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看检查记录",ctx + "/wghgl/tjfx/bcview?bcid=" + row.id+"&nf="+nf,"800px", "400px","");
}