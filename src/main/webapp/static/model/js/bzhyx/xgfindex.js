var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/xgf/list', 
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
        {field:'xgdwsum',title:'相关方单位名录',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"相关方单位名录\",\""+ctx+"/zyaqgl/xgdw/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'pdjhsum',title:'相关方评定计划',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"相关方评定计划\",\""+ctx+"/zyaqgl/xgfpdjh/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'pdsum',title:'相关方评定',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"相关方评定\",\""+ctx+"/zyaqgl/xgfpd/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'wgsum',title:'相关方违规记录',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"相关方违规记录\",\""+ctx+"/zyaqgl/xgfwg/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.xgdwsum != 0 || row.pdjhsum != 0 || row.pdsum != 0 || row.wgsum != 0){
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
