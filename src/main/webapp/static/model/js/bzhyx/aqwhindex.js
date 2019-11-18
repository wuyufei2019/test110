var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/aqwh/list', 
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
        {field:'aqdtsum',title:'安全动态',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"安全动态\",\""+ctx+"/target/aqdt/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'jfglsum',title:'积分管理',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"积分管理\",\""+ctx+"/target/jfgl/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'hlhsum',title:'合理化建议',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"合理化建议\",\""+ctx+"/target/hlhjy/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'aqhdsum',title:'安全活动',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"安全活动\",\""+ctx+"/target/aqhd/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'aqhysum',title:'安全会议',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"安全会议\",\""+ctx+"/target/aqhy/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.aqdtsum != 0 || row.jfglsum != 0 || row.hlhsum != 0 || row.aqhdsum != 0 || row.aqhysum != 0){
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
