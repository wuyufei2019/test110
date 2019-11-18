var dg;
var d;
$(function(){
	dg=$('#sbssgl_sbll_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbll/list', 
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
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
        {field:'m1',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'m2',title:'设备名称',sortable:false,width:50,align:'center'},
        {field:'m3',title:'规格 型号',sortable:false,width:40,align:'center',
        	formatter: function(value, row){
        		return value + " " + row.m27;
        	}
        },
        {field:'m4',title:'出厂编号',sortable:false,width:40,align:'center'},
        {field:'m5',title:'制造单位',sortable:false,width:60,align:'center'},
        {field:'m16',title:'启用时间',sortable:false,width:40,align:'center',
        	formatter: function(value){
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd");
        		}
        	}
        },
        {field:'m14',title:'加工范围',sortable:false,width:60,align : 'center'},
        {field:'m15',title:'设备重量',sortable:false,width:60,align : 'center'},
        {field:'m30',title:'安装单位',sortable:false,width:60,align : 'center'},
        {field:'m8',title:'安装地点',sortable:false,width:60,align : 'center'},
        {field:'m19',title:'状态',sortable:false,width:40,align:'center',
        	formatter: function(value){
        		if (value == '0') {
        			return '启用';
        		} else if (value == '1') {
        			return '停用';
        		} else if (value == '2') {
        			return '报废';
        		} 
        	},
        	styler: function(value){
        		if(value == '0'){
        			return "color: #23c6c8";
        		}else if(value == '2'){
        			return "color: #ed5565";
        		}
        	}
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbll_tb'
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

//导出word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	window.open(ctx+"/sbssgl/sbll/export/"+row.id);
}