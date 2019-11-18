var dg;
var d;
$(function(){
	dg=$('#bis_occupharm_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/occupharm/list', 
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
        {field:'m1',title:'职业病危害因素类别',sortable:true,width:60},    
        {field:'m2',title:'职业病危害因素名称',sortable:false,width:60,align:'center'},
        {field:'m3',title:'危害程度',sortable:true,width:60,align:'center',
        	formatter : function(value, row, index){
        		if(value=='1') return value='轻度危险性';
        		if(value=='2') return value='一般危险性';
        		if(value=='3') return value='严重危险性';
        		
        	}
        },
        {field:'m5',title:'可能导致的职业病',sortable:false,width:100,align:'center'},
        {field:'m6',title:'存在部门',sortable:false,width:60,align:'center'},
        {field:'m7',title:'存在岗位',sortable:false,width:60,align:'center'},
        {field:'m8',title:'接触人数',sortable:false,width:60,align:'center'},
        {field:'m9',title:'接触名单',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_occupharm_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加职业病危害因素信息",ctx+"/bis/occupharm/create/","800px", "500px","");
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
			url:ctx+"/bis/occupharm/delete/"+ids,
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
	
	openDialog("修改职业病危害因素信息",ctx+"/bis/occupharm/update/"+row.id,"800px", "500px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看职业病危害因素信息",ctx+"/bis/occupharm/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_zyb_cx_m2').combobox('setValue',$('#bis_zyb_cx_m2').combobox('getText'));
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
	$('#bis_zyb_cx_m2').combobox('setValue',$('#bis_zyb_cx_m2').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'职业病危害类别'},
			   		{colval:'m2', coltext:'职业病危害名称'},
			   		{colval:'m3', coltext:'危害程度'},
			   		{colval:'m5', coltext:'可能导致的职业病'},
			   		{colval:'m6', coltext:'存在部门'},
			   		{colval:'m7', coltext:'存在岗位'},	
			   		{colval:'m8', coltext:'接触人数'},	
			   		{colval:'m9', coltext:'接触名单'},	
			   		{colval:'m4', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/occupharm/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}