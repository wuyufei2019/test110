var dg;
var d;
$(function(){
	dg=$('#ztzr_gzzd_dg').datagrid({
	method: "post",
	url:ctx+'/ztzr/gzzd/list',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
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
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
		{field:'qyname',title:'企业名称',sortable:false,width:60,align:'center'},
        {field:'zdsum',title:'安全管理制度',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全管理制度\",\""+ctx+"/zdgl/glzd/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        },
		{field:'zdjlsum',title:'制度评审记录',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"制度评审记录\",\""+ctx+"/zdgl/aqps/index1?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
		},
        {field:'gcsum',title:'操作规程',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"安全操作规程\",\""+ctx+"/zdgl/czgc/index?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
        },
		{field:'gcjlsum',title:'规程评审记录',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
    			return "<a onclick='openDialogView(\"规程评审记录\",\""+ctx+"/zdgl/aqps/index2?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
        	}
		},
		{field:'dcd',title:'达成度',width:60,align:'center',
			formatter:function(value, row, index){
				return "<span class=\'fa fa-close btn-danger btn-outline\' >未落实</span>";
			}
		}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#ztzr_gzzd_tb'
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
