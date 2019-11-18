var dg;
var d;
$(function(){
	dg=$('#fxpg_zdfx_dg').datagrid({    
	method: "post",
    url:ctx+'/fxpg/zdfx/list', 
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
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},  
        {field :'qyname',title : '企业名称',sortable : false,width : 80},
        {field:'name',title:'名称',sortable:false,width:80},    
        {field:'deptname',title:'部门',sortable:false,width:50},    
        {field:'riskvalue',title:'风险值',sortable:false,width:30},    
        {field:'risklevel',title:'风险等级',sortable:false,width:30},    
        {field:'improvemeasure',title:'改进措施',sortable:false,width:80},   
        {field:'analysisper',title:'分析人',sortable:false,width:30},   
        {field:'analysistime',title:'分析时间',sortable:false,width:30,
        	formatter : function(value,row){
        	return new Date(value).format("yyyy-MM-dd");
        }},   
        {field:'reviewer',title:'审核人',sortable:false,width:30},   
        {field:'auditor',title:'审定人',sortable:false,width:30},   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#fxpg_zdfx_tb'
	});
	
});


//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看风险评估信息",ctx+"/fxpg/zdfx/view/"+row.id,"70%", "40%","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//导出
function excelexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata= [{colval:'name', coltext:'名称'},
			   		{colval:'deptname', coltext:'部门名称'},
			   		{colval:'riskvalue', coltext:'风险值'},
			   		{colval:'risklevel', coltext:'风险等级'},
			   		{colval:'improvemeasure', coltext:'改进措施'},
			   		{colval:'analysisper', coltext:'分析人'},
			   		{colval:'analysistime', coltext:'分析时间'},
			   		{colval:'reviewer', coltext:'审核人'},
			   		{colval:'auditor', coltext:'审定人'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/fxpg/zdfx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

