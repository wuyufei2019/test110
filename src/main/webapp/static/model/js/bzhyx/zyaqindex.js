var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "post",
    url:ctx+'/bzhyx/zyaq/list', 
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
        {field:'dhzysum',title:'动火作业',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"动火作业\",\""+ctx+"/zyaqgl/dhzy/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		 },
        {field:'sxzysum',title:'受限空间作业',sortable:false,width:80,align:'center',
			formatter: function(value,row,index){
				return "<a href=\"#\" style='margin-right:5px' onclick='openDialogView(\"受限空间作业\",\""+ctx+"/zyaqgl/sxzy/index?qyid="+row.qyid+"\",\"100%\", \"100%\",\"\")'>"+value+"</a>";
			}
		},
		{field:'dzsum',title:'吊装作业',sortable:false,width:80,align:'center',},
		{field:'mbcdsum',title:'盲板抽堵作业',sortable:false,width:80,align:'center'},
		{field:'dtzysum',title:'动土作业',sortable:false,width:80,align:'center'},
		{field:'dlzysum',title:'断路作业',sortable:false,width:80,align:'center'},
		{field:'gczysum',title:'高处作业',sortable:false,width:80,align:'center'},
		{field:'jwxsum',title:'检维修作业',sortable:false,width:80,align:'center'}, 
        {field:'zt',title:'运行状态',sortable:false,width:50,align:'center',
		    formatter : function(value, row, index) {
		    	var a = "";
	        	if(row.dhzysum != 0 || row.sxzysum != 0 || row.dzsum != 0 || row.mbcdsum != 0 || row.dtzysum != 0 || row.gczysum != 0 || row.jwxsum != 0){
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
