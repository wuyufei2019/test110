var dg;
var d;
$(function(){
	dg=$('#aqjg_dsfbb_dg').datagrid({    
	method: "post",
    url:ctx+'/dsffw/fwxmbb/aqlist', 
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
  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'id1',title : 'id1',hidden:true},
			{field:'qyname', title:'企业名称',sortable : false,	width : 100,align:'left'},
			{field:'dwname', title:'第三方机构',sortable : false,	width : 100,align:'left'},
	   		{field:'m1', title:'项目类型',sortable : false,	width : 100},
	   		{field:'m2', title:'项目名称',sortable : false,	width : 100},
	   		{field:'m3', title:'服务项目内容',sortable : false,	width : 100},
	   		{field:'m4', title:'项目负责人',sortable : false,	width : 100},
	   		{field:'m5', title:'项目合同资金',sortable : false,	width : 100},
	   		{field:'m6', title:'开始时间',sortable : false,	width : 100,
	   			formatter:function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');   
				}  
            }},
	   		{field:'m7', title:'结束时间',sortable : false,	width : 100, 
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');  
					}  
	            }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqjg_dsfbb_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加第三方服务报备信息",ctx+"/dsffw/fwxmbb/create/","800px", "400px","");
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
			url:ctx+"/dsffw/fwxmbb/delete/"+ids,
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
	
	openDialog("修改第三方服务报备信息",ctx+"/dsffw/fwxmbb/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看第三方服务报备信息",ctx+"/dsffw/fwxmbb/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'dwname', coltext:'第三方机构'},
			   		{colval:'m1', coltext:'项目类型'},
			   		{colval:'m2', coltext:'项目名称'},
			   		{colval:'m3', coltext:'服务项目内容'},
			   		{colval:'m4', coltext:'项目负责人'},
			   		{colval:'m5', coltext:'项目合同资金'},
			   		{colval:'m6', coltext:'开始时间'},
			   		{colval:'m7', coltext:'结束时间'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dsffw/fwxmbb/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}