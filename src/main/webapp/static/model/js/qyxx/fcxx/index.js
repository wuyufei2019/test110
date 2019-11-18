var dg;
var d;
$(function(){
	dg=$('#bis_fcxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/fcxx/list', 
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
              {field:'m1',title:'粉尘种类',sortable:true,width:100,align:'center'},    
              {field:'m2',title:'涉粉作业人数',sortable:true,width:100,align:'center'},
              {field:'m3',title:'涉粉单班作业人数',sortable:true,width:100,align:'center'},
              {field:'m4',title:'取缔',sortable:false,width:100,align:'center', 
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m5',title:'关闭',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m6',title:'有除尘器',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m7',title:'除尘器种类',sortable:true,width:100,align:'center'},    
              {field:'m8',title:'建立粉尘清扫制度',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m9',title:'安装监控',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m11',title:'通过验收',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m12',title:'除尘器数量',sortable:true,width:100,align:'center'  },
              {field:'m13',title:'是否涉爆',sortable:true,width:100,align:'center', 
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              },
              {field:'m14',title:'是否职业病危害',sortable:true,width:100,align:'center',
              	formatter : function(value, row, index){
              		if(value=='1') return value='是';
              		if(value=='0') return value='否';
              	}
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_fcxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加粉尘信息",ctx+"/bis/fcxx/create/","800px", "400px","");
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
			url:ctx+"/bis/fcxx/delete/"+ids,
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
	
	openDialog("修改粉尘信息",ctx+"/bis/fcxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看粉尘信息",ctx+"/bis/fcxx/view/"+row.ID,"800px", "400px","");
	
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
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'粉尘种类'},
			   		{colval:'m2', coltext:'涉粉作业人数'},
			   		{colval:'m3', coltext:'涉粉单班作业人数'},
			   		{colval:'m4', coltext:'是否取缔'},
			   		{colval:'m5', coltext:'是否关闭'},
			   		{colval:'m6', coltext:'是否有除尘器'},
			   		{colval:'m7', coltext:'除尘器种类'},
			   		{colval:'m8', coltext:'是否建立粉尘清扫制度'},
			   		{colval:'m9', coltext:'是否安装监控'},
			   		{colval:'m11', coltext:'是否验收通过'},
			   		{colval:'m12', coltext:'除尘器数量'},
			   		{colval:'m10', coltext:'备注'},
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/fcxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}