var dg;

$(function(){
	dg=$('#yhpc_yhpcjl_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/yhpcjl/dsjlist',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
				{field : 'qyid',title : '企业id',sortable : true,width : 50,align : 'center',hidden:true},
      			{field : 'qyname',title : '企业名称',sortable : true,width : 50,align : 'center'},
      			{field : 'fxcount',title : '风险点数量',sortable : false,width : 50,align : 'center'},
      			{field : 'jlcount',title : '隐患排查记录数',sortable : false,width : 50,align : 'center'},
      			{field : 'yhcount',title : '发现隐患数',sortable : false,width : 50,align : 'center'},
    			{field : 'zgcount',title : '隐患整改数',sortable : false,width : 50,align : 'center'},
    			{field : 'bl',title : '整改率',sortable : true,width : 40,align : 'center',
    				formatter : function(value, row, index) {
    	              	return value+"%";
    	            }
    			},
    			{field : 'cz',title : '操作',sortable : false,width : 20,align : 'center',
    				formatter : function(value, row, index) {
    					return " <a class='btn btn-info btn-xs' target='_blank' href='"+ctx+"/yhpc/yhpcjl/qydsj?qyid="+row.qyid+"'>查看大数据</a> ";
    	            }
    			}
    ]],
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_yhpcjl_tb'
	});
	
});

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

function viewdsj(qyid) {
    top.layer.open({
        type: 2,
        shift: 1,
        area: ["100%", "100%"],
        title: '企业大数据',
        maxmin: true,
        content: ctx+"/yhpc/yhpcjl/qydsj" ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
    // openDialogView("安全运行全貌",,"95%", "95%","");
}