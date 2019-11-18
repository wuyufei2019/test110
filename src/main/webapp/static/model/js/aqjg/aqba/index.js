var dg;
var d;
$(function(){
	dg=$('#aqjg_aqba_dg').datagrid({
		method: "post",
		url:ctx+'/aqjg/aqba/list',
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
			{field:'qyname',title:'企业名称',sortable:false,width:100},
			{field:'m1',title:'备案编号',sortable:false,width:50},
			{field:'m2',title:'备案日期',sortable:false,width:40,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);
						return datetime.format('yyyy-MM-dd');  }
				} },
			{field:'ruletime',title:'编制日期',sortable:false,width:40,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);
						return datetime.format('yyyy-MM-dd');  }
				} },
			{field:'expiration',title:'到期日期',sortable:false,width:40,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);
						return datetime.format('yyyy-MM-dd');
					}
				},styler: function(value, row, index){
					var nowhm=(new Date()).getTime();
					if(nowhm>=value){
						return 'background-color:rgb(249, 156, 140);';
					}else{
						var cha=(value-nowhm)/1000/60/60/24;
						if(cha<=90) return 'background-color:rgb(255, 228, 141);';
					}
				}
			 },
			{field:'agency',title:'中介公司',sortable:false,width:80},
			{field:'m3',title:'类别',sortable:false,width:50,align:'center'},
			{field:'m7',title:'经手人',sortable:false,width:30,align:'center'}
		]],
		onDblClickRow: function (rowdata, rowindex, rowDomElement){
			view();
		},
		checkOnSelect:false,
		selectOnCheck:false,
		onLoadSuccess:function(){
			if(usertype=="1"){
				$(this).datagrid("hideColumn",['qyname']);
			}else{
				$(this).datagrid("showColumn",['qyname']);
			}
			$(this).datagrid("autoMergeCells",['qyname']);
		},
		toolbar:'#aqjg_aqba_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加安全备案信息",ctx+"/aqjg/aqba/create/","800px", "400px","");
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
			url:ctx+"/aqjg/aqba/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
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

	openDialog("修改安全备案信息",ctx+"/aqjg/aqba/update/"+row.id,"800px", "400px","");

}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}

	openDialogView("查看安全备案信息",ctx+"/aqjg/aqba/view/"+row.id,"800px", "400px","");

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
		{colval:'label', coltext:'备案类别'},
		{colval:'m2', coltext:'备案日期'},
		{colval:'m7', coltext:'备案经手人'},
		{colval:'m5', coltext:'审核意见'},
		{colval:'m8', coltext:'备注'}
	];
	layer.open({
		type: 2,
		area: ['350px', '350px'],
		title: '导出列选择',
		maxmin: false,
		shift: 1,
		content: ctx+'/aqjg/aqba/colindex',
		btn: ['导出'],
		yes: function(index, layero){
			var body = layer.getChildFrame('body', index);
			var iframeWin = layero.find('iframe')[0];
			var inputForm = body.find('#excel_mainform');
			iframeWin.contentWindow.doExport();
		},
	});

}