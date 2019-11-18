var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/zyb/list', 
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
        {field:'zybyssum',title:'职业病危害因素',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"职业病危害因素\",\""+ctx+"/bis/occupharm/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'jcbgsum',title:'检测报告管理',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"检测报告管理\",\""+ctx+"/bis/occupharmreport/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'zybtjsum',title:'职业病体检',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"职业病体检\",\""+ctx+"/bis/occupillexam/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'ygtjsum',title:'员工体检信息',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"员工体检信息\",\""+ctx+"/bis/ygtjxx/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.zybyssum != 0 || row.jcbgsum != 0 || row.zybtjsum != 0 || row.ygtjsum != 0){
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
