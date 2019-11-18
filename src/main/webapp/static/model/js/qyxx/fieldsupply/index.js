var dg;
var d;
$(function(){
	dg=$('#bis_fieldsupply_dg').datagrid({
	method: "post",
    url:ctx+'/bis/fieldsupply/list',
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
    columns:[[
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
        {field:'m1',title:'介质',sortable:false,width:80,align:'center'},
        {field:'m2',title:'容积（m³）',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value!=null&&value!=""){
                    return value.toFixed(2);
                }
            }
        },
        {field:'m4',title:'气站性质',sortable:false,width:50,align:'center',
            formatter: function (value) {
                if(value == '0') {
                    return '自建';
                }else if(value == '1') {
                    return '租用';
                }
            }
        },
        {field:'m5',title:'供气单位',sortable:false,width:100,align:'center'},
        {field:'m6_1',title:'安评单位',sortable:false,width:100,align:'center'},
        {field:'m7_1',title:'备案编号',sortable:false,width:80,align:'center'},
        {field:'m9',title:'备注',sortable:false,width:80,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_fieldsupply_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加现场供气信息",ctx+"/bis/fieldsupply/create/","800px", "500px","");
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
			url:ctx+"/bis/fieldsupply/delete/"+ids,
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
	
	openDialog("修改现场供气信息",ctx+"/bis/fieldsupply/update/"+row.id,"800px", "500px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看现场供气信息",ctx+"/bis/fieldsupply/view/"+row.ID,"800px", "500px","");
	
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
			   		{colval:'m1', coltext:'介质'},
			   		{colval:'m2', coltext:'容积'},
			   		{colval:'m3', coltext:'用量'},
			   		{colval:'m4', coltext:'气站性质'},
			   		{colval:'m5', coltext:'供气单位'},
			   		{colval:'m6_1', coltext:'安评单位'},
			   		{colval:'m7_1', coltext:'备案编号'},
			   		{colval:'m8_1', coltext:'备案日期'},
			   		{colval:'m6_2', coltext:'安评单位'},
			   		{colval:'m7_2', coltext:'备案编号'},
			   		{colval:'m8_2', coltext:'备案日期'},
			   		{colval:'m6_3', coltext:'安评单位'},
			   		{colval:'m7_3', coltext:'备案编号'},
			   		{colval:'m8_3', coltext:'备案日期'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/fieldsupply/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}