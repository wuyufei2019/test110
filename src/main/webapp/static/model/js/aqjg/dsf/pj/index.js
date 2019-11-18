var dg;

$(function(){
	dg=$('#aqjg_pj_dg').datagrid({    
	method: "post",
    url:ctx+'/dsffw/pj/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'm1',title : '单位名称',sortable : false,width : 100},
			{field : 'm2',title : '评价',sortable : false,width : 100,align : 'center',
				formatter : function(value, row, index) {
	            	if(value=='1') return value='优秀';
			        if(value=='2') return value='良好';
			        if(value=='3') return value='合格';
			        if(value=='4') return value='不合格';
	        	} 
			},
			{field : 'm5',title : '评价人',sortable : false,width : 100,align : 'center'},
			{field : 'm4',title : '评价年度',sortable : false,width : 100,align : 'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['m1']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqjg_dsf_tb'
	});
	
});

//添加
function add(u) {
	openDialog("添加第三方评价信息",ctx+"/dsffw/pj/create/","650px", "330px","");
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
			url:ctx+"/dsffw/pj/delete/"+ids,
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
	
	openDialog("修改第三方评分记录",ctx+"/dsffw/pj/update/"+row.id,"650px", "330px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("第三方评分记录信息",ctx+"/dsffw/pj/view/"+row.id,"650px", "340px","");
	
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}


//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//刷新
function refresh(){
    dg.datagrid('reload'); 
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'单位名称'},
			   		{colval:'m2', coltext:'评价'},
			   		{colval:'m4', coltext:'年度'},
			   		{colval:'m5', coltext:'评价人'},
			   		{colval:'m6', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dsffw/pj/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}