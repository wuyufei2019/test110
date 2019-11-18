var dg;
var wqyname;

$(function(){
	dg=$('#yhpc_tjfx_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/tjfx/list', 
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
  			{field : 'qyname',title : '企业名称',sortable : false,width : 100},
  			{field : 'count_a',title : '正常巡检点数',sortable : true,width : 70,align : 'center'},
			{field : 'count_b',title : '异常巡检点数',sortable : true,width : 70,align : 'center'},
			{field : 'count_d',title : '应巡检点数',sortable : true,width : 70,align : 'center'},
			{field : 'count_e',title : '已巡检点数',sortable : true,width : 70,align : 'center'},
		    {field : 'count_c',title : '未巡检点数',sortable : true,width : 70,align : 'center'},
			{field : 'caozuo',title : '操作',sortable : false,width : 80,align : 'center',
				formatter : function(value, row, index) {
					return "<a class='btn btn-info btn-xs' onclick='viewXjdzt("+row.qyid+",\""+row.qyname+"\")'>查看隐患风险点状态</a>";
				}
			}
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	var row = dg.datagrid('getSelected');
    	viewXjdzt(row.qyid,row.qyname);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_tjfx_tb'
	});
	
});

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//获取选中行的企业name
function del(){
	var row = dg.datagrid('getSeleced');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	}


//跳转隐患风险点状态
function viewXjdzt(id,qyname){
	wqyname=qyname;
	layer.open({
	    type: 2,  
	    area: ["100%", "100%"],
	    title: " ",
        maxmin: true, 
	    content: ctx + "/yhpc/xjdzt/index2?qyid=" + id,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'qyname', coltext:'企业名称'},
			   		{colval:'count_a', coltext:'正常巡检点数'},
			   		{colval:'count_b', coltext:'异常巡检点数'},
			   		{colval:'count_c', coltext:'未巡检点数'},
			   		{colval:'count_d', coltext:'已巡检点数'},
			   		{colval:'count_e', coltext:'应巡检点数'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/tjfx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}