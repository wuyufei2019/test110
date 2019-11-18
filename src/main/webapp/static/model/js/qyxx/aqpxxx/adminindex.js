var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/aqpxxx/ajlist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[ 
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'m1',title:'安全培训人员姓名',sortable:false,width:60,align:'center'},    
        {field:'m2',title:'安全培训人员类别',sortable:false,width:60,align:'center'},
        {field:'m3',title:'安全培训人员职务',sortable:false,width:50,align:'center'},
        {field:'m4',title:'最近培训时间',sortable:true,width:60,align:'center',formatter: function(value,row,index){
        	if(value!=null){
				var datetime = new Date(value);  
				 return datetime.format('yyyy-MM-dd');    
			}  
		}},
        {field:'m5',title:'下次培训时间',sortable:true,width:60,align:'center',
			formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
			},
	    	styler: function(value, row, index){
	    		var nowhm=(new Date()).getTime();
	    		var m5time=value;
	    		if(m5time){//时间存在的情况下
	    			if(nowhm<=m5time){//未过期（3个月以内）
		        		var cha=(m5time-nowhm)/1000/60/60/24;
		        		if(cha<=90) return 'background-color:rgb(255,240,245);';
		    		}else{
		    			return 'background-color:rgb(249, 156, 140);';
		    		}
	    		}
	    	}
		},
        {field:'m6',title:'发证机关',sortable:true,width:50,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#bis_aqpx_cx_m3").combobox('setValue','0');
    		param.M3=0;
    	 }
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
        $(this).datagrid("autoMergeCells",['m1']);
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
	$.ajax({
		type:"GET",
        url:ctx+"/bis/aqpxxx/aqpxTimeEnd",
		success: function(data){
			if(data!='null'){
				layer.msg(data, {offset: 'rb',time: 3000, icon:0 });
				//layer.alert(data, {offset: 'rb',shade: 0,time: 3000}); 
			}
		}
	});
});
//添加
function add() {
	openDialog("添加安全培训信息",ctx+'/bis/aqpxxx/create',"800px","400px","");
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
			url:ctx+"/bis/aqpxxx/delete/"+ids,
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
	openDialog("修改安全培训信息",ctx+'/bis/aqpxxx/update/'+row.ID,"800px", "400px","");
}

 

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看安全培训信息",ctx+'/bis/aqpxxx/view/'+row.ID,"800px", "400px","");
}

//查询
function search(){
	$('#qy_name').combobox('setValue',$('#qy_name').combobox('getText'));
	var obj=$("#searchFrom").serializeObject();
	obj.qy_name=$("#qy_name").combobox('getText');
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[ {colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'姓名'},
			   		{colval:'m2', coltext:'安全人员类别'},
			   		{colval:'m3', coltext:'职务'},
			   		{colval:'m4', coltext:'最近培训时间'},
			   		{colval:'m5', coltext:'下次培训时间'},
			   		{colval:'m6', coltext:'发证机关'},
			   		{colval:'m7', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/aqpxxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}