var dg1;
var dg2;
var d;

$(function(){
	dg1=$('#bjyj_gyxx_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/fmew/bj/gylist',
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		nowrap: false,
		idField : 'id',
		striped:true,
		scrollbarSize:0,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[    
	              {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'},    
	              {field:'colltime',title:'报警时间',sortable:false,width:50,align:'center',
	              	  formatter : function(value, row, index) {
	                   	if(value!=null&&value!='') {
	                   		var dt=new Date(value);
	                   		return dt.format("yyyy-MM-dd hh:mm:ss");
	                   	}else{
	                   		return '/';
	                   	}
	               	}	
	              },
	              {field:'situation',title:'报警情况',sortable:false,width:200,align:'center'},
	              {field:'status',title:'是否处理',sortable:false,width:50,align:'center',
	 	           	  formatter : function(value, row, index) {
	 	                   	if(value=="1") {
	 							return "已处理";
	 	                   	}else{
	 	                   		return "未处理";
	 	                   	}
	 	               	},
	 	            	styler: function(value, row, index) {
	 	                  	if(value=="1") {
	 	                  	}else{
	 	                  		return 'background-color:#FFD2D2;';
	 	                  	}
	 	            	},
	              },
	              {field:'caozuo',title:'操作',sortable:false,width:50,align:'center',
	 					formatter : function(value, row, index) {
	 						return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='upd("
	 							+ row.id + ")'>报警处理</a>";
	 					}           	 
	             }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
            view();
		},
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	    toolbar:'#bjyj_gyxx_tb'
		});
	
});


//处理情况查询
function search(){
	var obj=$("#bjyj_gyxx_searchFrom").serializeObject();
	$('#bjyj_gyxx_dg').datagrid('load',obj); 
}

//清空
function clearA(){
	$("#bjyj_gyxx_searchFrom").form("clear");
	var obj=$("#bjyj_gyxx_searchFrom").serializeObject();
	$('#bjyj_gyxx_dg').datagrid('load',obj);
}

//学习记录datagrid
$(function(){   
	dg2=$('#bjyj_gyxx_dg2').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/fmew/bj/gylist2',
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		nowrap: false,
		idField : 'id',
		striped:true,
		scrollbarSize:0,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[    
	              {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'},    
	              {field:'colltime',title:'报警时间',sortable:false,width:50,align:'center',
	              	  formatter : function(value, row, index) {
	                   	if(value!=null&&value!='') {
	                   		var dt=new Date(value);
	                   		return dt.format("yyyy-MM-dd hh:mm:ss");
	                   	}else{
	                   		return '/';
	                   	}
	               	}	
	              },
	              {field:'situation',title:'报警情况',sortable:false,width:200,align:'center'},
	              {field:'status',title:'是否处理',sortable:false,width:50,align:'center',
	 	           	  formatter : function(value, row, index) {
	 	                   	if(value=="1") {
	 							return "已处理";
	 	                   	}else{
	 	                   		return "未处理";
	 	                   	}
	 	               	},
	 	            	styler: function(value, row, index) {
	 	                  	if(value=="1") {
	 	                  	}else{
	 	                  		return 'background-color:#FFD2D2;';
	 	                  	}
	 	            	},
	              },
	              {field:'caozuo',title:'操作',sortable:false,width:50,align:'center',
	 					formatter : function(value, row, index) {
	 						return "<a style='margin:2px' class='btn btn-success btn-xs' onclick='upd("
	 							+ row.id + ")'>报警处理</a>";
	 					}           	 
	             }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
            view2();
		},
	    onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells",['qyname']);
	    },
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	    toolbar:'#bjyj_gyxx_tb2'
		});
	
});


//学习记录查询
function search2(){
	var obj=$("#bjyj_gyxx_searchFrom2").serializeObject();
	$('#bjyj_gyxx_dg2').datagrid('load',obj); 
}

//清空
function clearA2(){
	$("#bjyj_gyxx_searchFrom2").form("clear");
	var obj=$("#bjyj_gyxx_searchFrom2").serializeObject();
	$('#bjyj_gyxx_dg2').datagrid('load',obj);
}

//报警处理
function upd(id){
	openDialog("报警信息处理",ctx+"/fmew/bj/update/"+id,"800px", "250px","");
	
}

//查看(未处理)
function view(){
	var row = dg1.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看高危工艺报警信息",ctx+"/fmew/bj/view/"+row.id,"800px", "400px","");
	
}

//查看(已处理)
function view2(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看高危工艺报警信息",ctx+"/fmew/bj/view/"+row.id,"800px", "400px","");
	
}

//导出
function fileexport1(){
	$("#status").val(0);
	window.expara=$("#bjyj_gyxx_searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'colltime', coltext:'报警时间'},
			   		{colval:'situation', coltext:'报警情况'},
			   		{colval:'reason', coltext:'报警原因'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/fmew/bj/colindex3',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//导出2
function fileexport2(){
	$("#status2").val(1);
	window.expara=$("#bjyj_gyxx_searchFrom2").serializeObject();
	window.exdata=[
			   		{colval:'colltime', coltext:'报警时间'},
			   		{colval:'situation', coltext:'报警情况'},
			   		{colval:'reason', coltext:'报警原因'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/fmew/bj/colindex3',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}