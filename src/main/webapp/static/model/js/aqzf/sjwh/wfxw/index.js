var dg;

$(function(){
	dg=$('#aqzf_wfxw_dg').datagrid({    
	method: "post",
    url:ctx+'/aqzf/wfxw/list', 
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
			{field : 'm1',title : '违法行为',sortable : false,width : 60},
			{field : 'm2',title : '违法条款',sortable : false,width : 50,align : 'center'},
			{field : 'm3',title : '违法条款详情',sortable : false,width : 100,align : 'center'},
			{field : 'm4',title : '处罚依据',sortable : false,width : 60,align : 'center'},
			{field : 'm5',title : '处罚标准',sortable : false,width : 80,align : 'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqzf_wfxw_tb'
	});
	
});

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
			url:ctx+"/aqzf/wfxw/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

//添加
function add(u) {
	openDialog("添加违法行为信息",ctx+"/aqzf/wfxw/create/","800px", "400px","");
}


//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改违法行为信息",ctx+"/aqzf/wfxw/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看违法行为信息",ctx+"/aqzf/wfxw/view/"+row.id,"800px", "400px","");
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'违法行为'},
			   		{colval:'m2', coltext:'违法条款'},
			   		{colval:'m3', coltext:'违法条款详情'},
			   		{colval:'m4', coltext:'处罚依据'},
			   		{colval:'m5', coltext:'处罚标准'} 		
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqzf/wfxw/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//导入弹窗
function openImportDialog(url,uploadurl,templateurl){
	layer.open({
	    type: 2, 
	    shift:1,
	    area: ['370px', '180px'],
	    title:"导入数据",
	    content:url,
	    btn: ['确定','下载模板', '关闭'],
		yes: function(index, layero){
			 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var im = body.find('#importForm');
	         $(im).attr("action",uploadurl);
			  var str=$($(im).find("#uploadFile")).val().toString();
			  var ext=str.lastIndexOf('.')== -1 ? "" : str.substring(str.lastIndexOf('.')+1);
			  if(ext=='xls'){
				  iframeWin.contentWindow.doSubmit();
			  }else{
			  	  $($(im).find("#alertLabel")).css("color","red");
			  	  return false;
			  }
		}, end: function(layero, index){
			dg.datagrid('reload');
		 },
		btn2: function(index){ 
			  window.location.href=templateurl;
		},
		btn3: function(index){ 
			  layer.close(index);
		},
		cancel: function(){ 
		}
	}); 
}