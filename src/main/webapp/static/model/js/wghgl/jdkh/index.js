var dg1;
var dg2;
var d;
var yf;
var nf;

//月检统计datagrid
$(function(){   
	dg1=$('#wghgl_xjry_dg1').datagrid({    
	method: "post",
    url:ctx+'/wghgl/jdkh/yjlist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'wgdid',
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
        //查看功能要以wgdid和userid查询
        //{field:'wgdid',title:'wgdid',checkbox:true,width:50,align:'center'}, 
        {field:'wgname',title:'所属网格',width:70},
        {field:'yxcs',title:'应巡检次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        },
        {field:'wgyname',title:'网格员',width:70,align:'center'},
        {field:'wgdname',title:'网格点名称',width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '/';
         		else
         			return value;
            }
        },  
        {field:'xccs',title:'有效巡查次数',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null || value=='' || value==undefined)
         			return '0';
         		else
         			return value;
            }
        }
    ]],
    onLoadSuccess: function(){
	   	//$(this).datagrid("autoMergeCells", [ 'wgname','yxcs','wgyname' ]);
    	MergeCells("wghgl_xjry_dg1","wgname,yxcs,wgyname");
    	yf = $('#wghgl_yjxjry_yf').datebox('getValue');
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view1();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_xjry_tb1'
	});
	
});

function MergeCells(tableID, fldList) {
    var Arr = fldList.split(",");
    var tdg = $('#' + tableID);
    var fldName;
    var RowCount = tdg.datagrid("getRows").length;
    var span;
    var PerValue = "";
    var CurValue = "";
    var length = Arr.length - 1;
    var rows = tdg.datagrid("getRows");
 
    for (i = length; i >= 0; i--) {
        fldName = Arr[i];
        PerValue = "";
        span = 1;
        for (row = 0; row <= RowCount; row++) {
            if (row == RowCount) {
                CurValue = "";
            }
            else {
                CurValue = tdg.datagrid("getRows")[row][fldName];
            }
            //同组信息合并单元格，添加限制条件
            if (PerValue == CurValue &&rows[row-1]&&rows[row]["wgname"] == rows[row - 1]["wgname"])
             {
                span += 1;
            }
            else {
                var index = row - span;
                tdg.datagrid('mergeCells', {
                    index: index,
                    field: fldName,
                    rowspan: span,
                    colspan: null
                });
                span = 1;
                PerValue = CurValue;
            }
        }
    }
}


//月检统计查询
function search1(){
	var obj=$("#wghgl_xjry_searchFrom1").serializeObject();
	$('#wghgl_xjry_dg1').datagrid('load',obj); 
}

//清空
function clear1(){
	$("#wghgl_xjry_searchFrom1").form("clear");
	var now=new Date();
	$('#wghgl_yjxjry_yf').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
	search1();
}

//查看巡检记录
function view1() {
	var row = dg1.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看巡检记录",ctx + "/wghgl/jdkh/view?wgdid=" + row.wgdid+"&yf="+yf+"&userid="+row.userid,"800px", "400px","");
}

//年检统计datagrid
$(function(){   
	dg2=$('#wghgl_xjry_dg2').datagrid({    
	method: "post",
    url:ctx+'/wghgl/jdkh/njlist', 
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
	       //查看功能要以wgdid和userid查询
	       //{field:'wgdid',title:'wgdid',checkbox:true,width:50,align:'center'}, 
	       {field:'wgname',title:'所属网格',width:70},  
	       {field:'yxcs',title:'应巡检次数',width:50,align:'center',
	        	formatter : function(value, row, index) {
	         		if (value==null || value=='' || value==undefined)
	         			return '0';
	         		else
	         			return value;
	            }
	       },
	       {field:'wgyname',title:'网格员',width:70,align:'center'},
	       {field:'wgdname',title:'网格点名称',width:100,align:'center',
	       	formatter : function(value, row, index) {
	        		if (value==null || value=='' || value==undefined)
	        			return '/';
	        		else
	        			return value;
	           }
	       },  
	       {field:'xccs',title:'有效巡查次数',width:50,align:'center',
	       	formatter : function(value, row, index) {
	        		if (value==null || value=='' || value==undefined)
	        			return '0';
	        		else
	        			return value;
	           }
	       }
	]],
    onLoadSuccess: function(){
    	MergeCells("wghgl_xjry_dg2","wgname,yxcs,wgyname");
	   	nf = $("#wghgl_njxjry_nf").combobox('getValue');
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view2();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_xjry_tb2'
	});
	
});


//年检统计查询
function search2(){
	$("#wghgl_njxjry_nf").combobox('setValue',$("#wghgl_njxjry_nf").combobox('getText'));
	var obj=$("#wghgl_xjry_searchFrom2").serializeObject();
	$('#wghgl_xjry_dg2').datagrid('load',obj); 
}

//清空
function clear2(){
	$("#wghgl_xjry_searchFrom2").form("clear");
	var now=new Date();
	$("#wghgl_njxjry_nf").combobox('setValue',now.getFullYear());
	search2();
}

//查看年检巡检记录
function view2() {
	var row = dg2.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看检查记录",ctx + "/wghgl/jdkh/view?wgdid=" + row.wgdid+"&nf="+nf+"&userid="+row.userid,"800px", "400px","");
}