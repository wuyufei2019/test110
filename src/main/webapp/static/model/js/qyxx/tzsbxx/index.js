var dg;
var d;
var con1;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
	url:ctx+'/bis/tzsbxx/list', 
	queryParams : (parent.P_HeaderType=="expiration"?{time1 : 0 }:null),
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
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'ID',title:'ID',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'设备名称',sortable:false,width:100},    
        {field:'m2',title:'资产编号',sortable:false,width:100,align:'center'},
        {field:'LABEL',title:'设备类型',sortable:false,width:100,align:'center'},
        {field:'m4',title:'规格型号',sortable:false,width:100,align:'center'},
        {field:'m15',title:'出厂年月',sortable:false,width:100,align:'center',
            formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                 return datetime.format('yyyy-MM-dd');    }  
                }
        },
        {field:'m9',title:'上次审验',sortable:false,width:100,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
		}},
        {field:'m10',title:'下次审验',sortable:false,width:100,align:'center',
            formatter:function(value,row,index){
     		if(value!=null){
                var datetime = new Date(value);  
                 return datetime.format('yyyy-MM-dd');    }  
                },
                styler: function(value, row, index){
            		var nowhm=(new Date()).getTime();
            		var time=value;
            		if(time){//在时间存在的情况下进行
            			if(nowhm<=time){//未过期（3个月以内）
                    		var cha=(time-nowhm)/1000/60/60/24;
                    		if(cha<=90) return 'background-color:rgb(255,240,245);';
                		}else{//过期
                			return 'background-color:rgb(249, 156, 140);';
                		}
            		}
            	}},
    	{field:'dep',title:'使用部门',sortable:false,width:60,align:'center'},
        {field:'cz',title:'操作',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
        		var cz="&nbsp<a class='btn btn-success btn-xs' onclick='showhistory("+row.ID+")'>查看历史</a>";
        		var nowhm=(new Date()).getTime();
        		var time=row.m10;
        		if(time){//在时间存在的情况下进行
                		var cha=(time-nowhm)/(1000 * 60 * 60 * 24);
                		if(cha<=90) {cz="<a class='btn btn-success btn-xs' onclick='upd2("+row.ID+")'>更新日期</a>"+cz;
                		}else{
                		}
        		}
        		return cz;
        	}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#bis_tzsb_cx_time1").combobox('setValue','0');
    		param.time1=0;
    	 }
	    },
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

//添加
function add() {
	openDialog("添加特种设备信息",ctx+'/bis/tzsbxx/create',"800px","400px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}
	
	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/tzsbxx/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
}

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改特种设备信息",ctx+'/bis/tzsbxx/update/'+row.ID,"800px", "400px","");
}

//更新特种设备日期
function upd2(id){
	openDialog("修改特种设备信息",ctx+'/bis/tzsbxx/update2/'+id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看特种设备信息",ctx+'/bis/tzsbxx/view/'+row.ID,"800px", "400px","");
}

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

function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.excolumn=[[    
				        {field:'colval',title:'colval',checkbox:true,width:50,align:'center'},    
				        {field:'coltext',title:'列名',width:100,align:'center'}
				    ]];
	window.exdata=[
			   		{colval:'m1', coltext:'特种设备名称'},
			   		{colval:'m2', coltext:'特种设备编号'},
			   		{colval:'m3', coltext:'特种设备类型'},
			   		{colval:'m4', coltext:'规格型号'},
			   		{colval:'m5', coltext:'主要参数'},
			   		{colval:'m6', coltext:'数量'},
			   		{colval:'m7', coltext:'功能'},
			   		{colval:'m8', coltext:'备注'},
			   		{colval:'m9', coltext:'检测日期'},
			   		{colval:'m10', coltext:'有效期'},
			   		{colval:'m11', coltext:'检测单位'}
		   	];
	window.colval="";
	window.coltext="";
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/tzsbxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//查看更新历史
function showhistory(id) {
	openDialog("查看更新历史",ctx+'/bis/tzsbxx/hisindex/'+id,"800px","400px","");
}