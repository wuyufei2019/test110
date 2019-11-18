var dg;
var d;
$(function(){
	dg=$('#bis_wlxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/wlxx/list', 
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
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'物料名称',sortable:false,width:120},     
        {field:'m2',title:'年用量/年产量(t/m³)',sortable:true,width:70,align:'center'},
        {field:'m3',title:'最大储量(t/m³)',sortable:true,width:70,align:'center'},
        {field:'m5',title:'储存方式',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value=='1') return value='储罐';
        		if(value=='2') return value='桶装';
        		if(value=='3') return value='袋装';
        		if(value=='4') return value='其他';
        	}
        },
        {field:'m8',title:'危化品类别',sortable:false,width:80,align:'center'},
        {field:'m12',title:'重点监管',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value==null||value=='') return value='/';
        		if(value=='0') return value='否';
        		if(value=='1') return value='是';
    	}},
    	{field:'m13',title:'剧毒',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value==null||value=='') return value='/';
        		if(value=='0') return value='否';
        		if(value=='1') return value='是';
    	}},
    	{field:'m14',title:'易制毒',sortable:true,width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value==null||value=='') return value='/';
        		if(value=='0') return value='否';
        		if(value=='1') return value='是';
    	}}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_wlxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加物料信息",ctx+"/bis/wlxx/create/","800px", "400px","");
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
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/wlxx/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改物料信息",ctx+"/bis/wlxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看物料信息",ctx+"/bis/wlxx/view/"+row.ID,"800px", "520px","");
	
}

//创建查询对象并查询
function search(){
	$('#whplb').combobox('setValue',$('#whplb').combobox('getText'));
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	$('#whplb').combobox('setValue',$('#whplb').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'物料名称'},
			   		{colval:'m2', coltext:'年用量（t）'},
			   		{colval:'m3', coltext:'最大储量（t）'},
			   		{colval:'m4', coltext:'CAS号'},
			   		{colval:'m5', coltext:'存储方式'},
			   		{colval:'m6', coltext:'主要危险性'},
			   		{colval:'m7', coltext:'备注'},
			   		{colval:'m8', coltext:'物料类别'},
			   		{colval:'m9', coltext:'状态'},
			   		{colval:'m10', coltext:'是否领证'},
			   		{colval:'m12', coltext:'重点监管'},
			   		{colval:'m13', coltext:'剧毒'},
			   		{colval:'m14', coltext:'易制毒'},
			   		{colval:'m11', coltext:'标识'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/wlxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}