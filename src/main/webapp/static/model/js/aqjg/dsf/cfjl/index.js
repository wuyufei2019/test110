var dg;

$(function(){
	dg=$('#aqjg_cfjl_dg').datagrid({    
	method: "post",
    url:ctx+'/dsffw/cfjl/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field : 'm1',title : '单位名称',sortable : false,width : 100},
			{field : 'm2',title : '处罚类型',sortable : false,width : 100,align : 'center',
				formatter : function(value, row, index) {
	            	if(value=='1') return value='警告';
			        if(value=='2') return value='罚款';
			        if(value=='3') return value='没收违法所得、没收非法开采的煤炭产品、采掘设备';
			        if(value=='4') return value='责令停产停业整顿、责令停产停业、责令停止建设、责令停止施工';
			        if(value=='5') return value='暂扣或者吊销有关许可证，暂停或者撤销有关执业资格、岗位证书';
			        if(value=='6') return value='关闭';
			        if(value=='7') return value='拘留';
	        	} },
			{field : 'm3',title : '处罚内容',sortable : false,width : 100,align : 'center'},
			{field : 'm5',title : '备注',sortable : false,width : 100,align : 'center'},
			{field : 's1',title : '处罚时间',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
         		var datetime = new Date(value);  
         		return datetime.format('yyyy-MM-dd');  
     	}}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['m1']);
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqjg_dsf_tb'
	});
	
});

//添加
function add(u) {
	openDialog("添加第三方处罚信息",ctx+"/dsffw/cfjl/create/","800px", "370px","");
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
			url:ctx+"/dsffw/cfjl/delete/"+ids,
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
	
	openDialog("修改第三方处罚记录",ctx+"/dsffw/cfjl/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("第三方处罚记录信息",ctx+"/dsffw/cfjl/view/"+row.id,"650px", "350px","");
	
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
			   		{colval:'m2', coltext:'处罚类型'},
			   		{colval:'m3', coltext:'处罚内容'},
			   		{colval:'m5', coltext:'备注'},
			   		{colval:'s1', coltext:'处罚时间'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dsffw/cfjl/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}