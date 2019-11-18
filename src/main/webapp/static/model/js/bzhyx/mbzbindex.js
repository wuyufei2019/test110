var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/mbzb/list', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100}, 
        {field:'zbszsum',title:'指标设置',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"指标设置\",\""+ctx+"/target/zbsz/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'zbfpsum',title:'指标分配',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"指标分配\",\""+ctx+"/target/zbfp/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'mbzpsum',title:'目标自评',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"目标自评\",\""+ctx+"/target/mbzp/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'mbkhsum',title:'指标考核',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"目标自评\",\""+ctx+"/target/zbkh/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        }, 
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.zbszsum != 0 || row.zbfpsum != 0 || row.mbzpsum != 0 || row.mbkhsum != 0){
	        		a = "<a style='margin:2px' class='btn btn-primary btn-xs'>运行</a>";
	        	}else{
	        		a = "<a style='margin:2px' class='btn btn-danger btn-xs'>未运行</a>";
	        	}
	        	return a;
		    } 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#tb'
	});
	
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
