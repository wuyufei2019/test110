var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({
		method: "post",
		url:ctx+'/bis/hazard/list',
		fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize:0,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		columns:[[
			{field:'id',title:'id',checkbox:true,width:50,align:'center'},
			{field:'m12',title:'重大危险源名称',sortable:false,width:100},
			{field:'hazardcode',title:'重大危险源编码',sortable:false,width:50},
			{field:'m1',title:'危险源级别',sortable:false,width:50,
				formatter : function(value, row, index){
					if(value=='1') return value='一级';
					if(value=='2') return value='二级';
					if(value=='3') return value='三级';
					if(value=='4') return value='四级';
				}
			},
			{field:'m2',title:'R值',sortable:false,width:50},
			{field:'m3',title:'厂区人数',sortable:false,width:50},
		/*	{field:'M4',title:'是否有安全监控预警系统',sortable:false,width:50,
				formatter : function(value, row, index){
					if(value=='0') return value='否';
					if(value=='1') return value='是';
				}
			},*/
			{field:'m5',title:'主要危险性',sortable:false,width:100},
			{field:'m6',title:'联系人',sortable:false,width:50},
			{field:'m7',title:'联系电话',sortable:false,width:50}
		]],
		onDblClickRow: function (rowdata, rowindex, rowDomElement){
			view();
		},
		enableHeaderClickMenu: true,
		enableRowContextMenu: false,
		toolbar:'#tb'
	});
});


//弹窗增加
function add() {
	openDialogView("添加重大危险源信息",ctx+'/bis/hazard/create',"1200px","700px","");
}


//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
    openDialogView("修改重大危险源信息",ctx+'/bis/hazard/update/'+row.ID,"1200px", "700px","");
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
			url:ctx+"/bis/hazard/ajdelete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看重大危险源信息",ctx+'/bis/hazard/qyview/'+row.ID,"1200px", "700px","");
}
//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	//obj.bis_hazard_wuzhi_name=$("#bis_hazard_wuzhi_name").combobox('getText');
	dg.datagrid('load',obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj);
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

function fileexport(){
	var obj=$("#searchFrom").serializeObject();
	obj.bis_hazard_wuzhi_name=$("#bis_hazard_wuzhi_name").combobox('getText');
	window.expara=obj;
	window.exdata=[
		{colval:'m12', coltext:'重大危险源名称'},
		{colval:'M1', coltext:'重大危险源级别'},
		{colval:'M2', coltext:'R值'},
		{colval:'M3', coltext:'厂区人数'},
		{colval:'M4', coltext:'是否有安全监控系统'},
		{colval:'M5', coltext:'主要危险'},
		{colval:'m1', coltext:'类别'},
		{colval:'m2', coltext:'辨识物质'},
		{colval:'m3', coltext:'最大储量'},
		{colval:'m4', coltext:'临界储量'},
		{colval:'m6', coltext:'计算值 '},
		{colval:'M6', coltext:'联系人'},
		{colval:'M7', coltext:'联系电话'},
		{colval:'M8', coltext:'备注'},
		{colval:'M9', coltext:'重大危险源计算Q值'},
		{colval:'M9_1', coltext:'增加暴露人数'},
		{colval:'M10', coltext:'易导致事故类型'}
	];

	layer.open({
		type: 2,
		area: ['350px', '350px'],
		title: '导出列选择',
		maxmin: true,
		shift: 1,
		content: ctx+'/bis/hazard/colindex',
		btn: ['导出'],
		yes: function(index, layero){
			var body = layer.getChildFrame('body', index);
			var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var inputForm = body.find('#excel_mainform');
			iframeWin.contentWindow.doExport();
		},
	});
}