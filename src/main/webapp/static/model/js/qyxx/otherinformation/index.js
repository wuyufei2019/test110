var dg;
var d;
$(function(){
	dg=$('#bis_otherinformation_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/otherinformation/list', 
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
        {field:'m1',title:'现场供气',sortable:false,width:100,align:'center',
		formatter : function (value,row,index) {
			if(value=='0') return value='是';
			if(value=='1') return value='否';
		}},
		{field:'m3',title:'污水处理',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
		{field:'m5',title:'涂装',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
		{field:'m7',title:'电镀',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
		{field:'m9',title:'阴极氧化',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
		{field:'m11',title:'厂房权属',sortable:false,width:100,align:'center'},
		{field:'m12',title:'有协议',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
		{field:'m13',title:'锅炉',sortable:false,width:100,align:'center',
			formatter : function (value,row,index) {
				if(value=='0') return value='是';
				if(value=='1') return value='否';
			}},
	]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_otherinformation_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加其他信息",ctx+"/bis/otherinformation/create/","800px", "400px","");
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
			url:ctx+"/bis/otherinformation/delete/"+ids,
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
	
	openDialog("修改其他信息",ctx+"/bis/otherinformation/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看其他信息",ctx+"/bis/otherinformation/view/"+row.ID,"800px", "400px","");
	
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
	$('#bis_otherinformation_m11').combobox('setValue',$('#bis_otherinformation_m11').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'现场供气'},
			   		{colval:'m2', coltext:'用途'},
			   		{colval:'m3', coltext:'污水处理'},
			   		{colval:'m4', coltext:'用途'},
			   		{colval:'m5', coltext:'涂装'},
			   		{colval:'m6', coltext:'用途'},
			   		{colval:'m7', coltext:'电镀'},
			   		{colval:'m8', coltext:'用途'},
			   		{colval:'m9', coltext:'阴极氧化'},
			   		{colval:'m10', coltext:'用途'},
			   		{colval:'m11', coltext:'厂房权属'},
			   		{colval:'m12', coltext:'有协议'},
			   		{colval:'m13', coltext:'锅炉'},
					{colval:'m14', coltext:'锅炉原料'},
					{colval:'m15', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/otherinformation/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}