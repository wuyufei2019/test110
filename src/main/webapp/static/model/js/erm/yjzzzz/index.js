var dg;
var cxtype;
var d;
$(function(){
	dg=$('#erm_yjzzzz_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yjzzzz/list', 
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
        {field:'qynm',title:'企业名称',width:100,align:'center'},    
        {field:'m1',title:'组织名称',width:100,align:'center'},    
        {field:'m2',title:'组织负责人',width:100,align:'center'},
        {field:'m3',title:'负责人联系电话',width:100,align:'center'},
        {field:'m4',title:'组织联系人',width:100,align:'center'},
        {field:'m5',title:'组织联系人电话',width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
       	if(usertype == '1'){
		}else{
    	    if(cxtype!=null&&cxtype!="2"){
    		   $(this).datagrid("hideColumn",['qynm']);
    	    }else{
    		   $(this).datagrid("showColumn",['qynm']);
    	    }
		}
		$(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#erm_yjzzzz_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加应急组织职责信息",ctx+"/erm/yjzzzz/create/","800px", "400px","");
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
			url:ctx+"/erm/yjzzzz/delete/"+ids,
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
	
	openDialog("修改应急组织职责信息",ctx+"/erm/yjzzzz/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急组织职责信息",ctx+"/erm/yjzzzz/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	if(usertype != "1"&&usertype != "5"){
		cxtype = $('#cx_type_con').combobox('getValue');
	}
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	$('#cx_type_con').combobox('setValue', '1');
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//显示安监按钮
	$('#divper').show();
	$('#divper2').show();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'组织名称'},
			   		{colval:'m2', coltext:'组织负责人'},
			   		{colval:'m3', coltext:'负责人联系电话'},
			   		{colval:'m4', coltext:'组织联系人'},
			   		{colval:'m5', coltext:'组织联系人电话'},
			   		{colval:'m6', coltext:'组织应急职责'},
			   		{colval:'m7', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yjzzzz/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}