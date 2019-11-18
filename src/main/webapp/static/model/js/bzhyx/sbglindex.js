var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/sbgl/list', 
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
        {field:'tzsum',title:'特种设备台账',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"特种设备台账\",\""+ctx+"/bis/tzsbxx/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'jwxsum',title:'维检修',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"检维修\",\""+ctx+"/sbgl/jwx/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'aqscsum',title:'安全设施',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"安全设施\",\""+ctx+"/bis/aqss/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.tzsum != 0 || row.jwxsum != 0 || row.aqscsum != 0){
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
