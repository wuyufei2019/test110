var dg;
var dg2;
var dg3;
var d;

//公共检查表datagrid
$(function(){   
	dg=$('#yhpc_jcbk_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/jcbk/ggbklist', 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'dangerlevel',title:'隐患级别',width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value=="1") return value='一般';
        		if(value=="2") return value='重大';
        	}
        },
        {field:'checktitle',title:'检查单元',width:70,align:'center'},  
        {field:'content',title:'检查项目',width:100},  
        {field:'checkyes',title:'隐患内容',width:50,align:'center'},
        {field:'checkno',title:'正常内容 ',width:50,align:'center'}  
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(1);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_jcbk_tb'
	});
	
});


//公共检查表查询
function search(){
	var obj=$("#yhpc_jcbk_searchFrom").serializeObject();
	$('#yhpc_jcbk_dg').datagrid('load',obj); 
}

//清空
function clearA(){
	$("#yhpc_jcbk_searchFrom").form("clear");
	var obj=$("#yhpc_jcbk_searchFrom").serializeObject();
	$('#yhpc_jcbk_dg').datagrid('load',obj);
}

//企业自增表datagrid
$(function(){   
	dg2=$('#yhpc_jcbk_dg2').datagrid({    
	method: "post",
    url:ctx+'/yhpc/jcbk/qybklist', 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
       {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
       {field:'qyname',title:'企业名称',width:100 },
       {field:'dangerlevel',title:'隐患级别',width:50,align:'center',
       	formatter : function(value, row, index){
       		if(value=="1") return value='一般';
       		if(value=="2") return value='重大';
       	}
       },
       {field:'checktitle',title:'检查单元',width:70,align:'center'},  
       {field:'content',title:'检查项目',width:100},  
       {field:'checkyes',title:'隐患内容',width:50,align:'center'},
       {field:'checkno',title:'正常内容 ',width:50,align:'center'}   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(2);
    },
    onLoadSuccess: function(){
  	  	if(usertype=="1"){
  	  		$(this).datagrid("hideColumn",['qyname']);
  	  	}
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_jcbk_tb2'
	});
	
});


//企业自增表查询
function search2(){
	var obj=$("#yhpc_jcbk_searchFrom2").serializeObject();
	$('#yhpc_jcbk_dg2').datagrid('load',obj); 
}

//清空
function clearA2(){
	$("#yhpc_jcbk_searchFrom2").form("clear");
	var obj=$("#yhpc_jcbk_searchFrom2").serializeObject();
	$('#yhpc_jcbk_dg2').datagrid('load',obj);
}

//网格检查表datagrid
$(function(){   
	dg3=$('#yhpc_jcbk_dg3').datagrid({    
	method: "post",
    url:ctx+'/yhpc/jcbk/wgbklist', 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'dangerlevel',title:'隐患级别',width:50,align:'center',
        	formatter : function(value, row, index){
        		if(value=="1") return value='一般';
        		if(value=="2") return value='重大';
        	}
        },
        {field:'content',title:'检查项目',width:100},  
        {field:'checkyes',title:'隐患内容',width:50,align:'center'},
        {field:'checkno',title:'正常内容 ',width:50,align:'center'}  
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view(3);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_jcbk_tb3'
	});
	
});


//公共检查表查询
function search3(){
	var obj=$("#yhpc_jcbk_searchFrom3").serializeObject();
	$('#yhpc_jcbk_dg3').datagrid('load',obj); 
}

//清空
function clearA3(){
	$("#yhpc_jcbk_searchFrom3").form("clear");
	var obj=$("#yhpc_jcbk_searchFrom3").serializeObject();
	$('#yhpc_jcbk_dg3').datagrid('load',obj);
}

//检查表库增加
function add(flag) {
	if(flag==1){
		openDialog("添加公共检查信息",ctx+"/yhpc/jcbk/create/","800px", "400px","");
	}else if(flag==2){
		openDialog("添加企业自增信息",ctx+"/yhpc/jcbk/create/","800px", "400px","");
	}else if(flag==3){
		openDialog("添加网格检查信息",ctx+"/yhpc/jcbk/create2/","800px", "400px","");
	}
}

//删除
function del(flag){
	var row;
	if(flag==1){
		row = dg.datagrid('getChecked');
	}else if(flag==2){
		row = dg2.datagrid('getChecked');
	}else if(flag==3){
		row = dg3.datagrid('getChecked');
	}
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
			url:ctx+"/yhpc/jcbk/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:1,offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg2.datagrid('reload');
				if(flag==1){
					dg3.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}else if(flag==2){
					dg2.datagrid('clearChecked');
					dg2.datagrid('clearSelections');
				}else if(flag==3){
					dg3.datagrid('reload');
					dg3.datagrid('clearChecked');
					dg3.datagrid('clearSelections');
				}
			}
		});
	});
 
}

//弹窗修改
function upd(flag){
	var row;
	if(flag==1){
		row = dg.datagrid('getSelected');
	}else if(flag==2){
		row = dg2.datagrid('getSelected');
	}else if(flag==3){
		row = dg3.datagrid('getSelected');
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改车间信息",ctx+"/yhpc/jcbk/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(flag){
	var row="";
	if(flag==1){
		row = dg.datagrid('getSelected');
	}else if(flag==2){
		row = dg2.datagrid('getSelected');
	}else if(flag==3){
		row = dg3.datagrid('getSelected');
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看巡检内容表信息",ctx+"/yhpc/jcbk/view/"+row.id,"800px", "400px","");
	
}

//导出
function fileexport(n){
	var content;
	if(n==1){
		content=ctx+'/yhpc/jcbk/colindex';
		window.expara=$("#yhpc_jcbk_searchFrom").serializeObject();
	}else if(n==2){
		content=ctx+'/yhpc/jcbk/colindex2';
		window.expara=$("#yhpc_jcbk_searchFrom2").serializeObject();
	}else if(n==3){
		content=ctx+'/yhpc/jcbk/colindex3';
		window.expara=$("#yhpc_jcbk_searchFrom3").serializeObject();
	}
	if(n==1||n==2){
		window.exdata=[
				   		{colval:'yh', coltext:'隐患级别'},
				   		{colval:'checktitle', coltext:'检查单元'},
				   		{colval:'content', coltext:'检查项目'},
				   		{colval:'checkyes', coltext:'有隐患状态'},
				   		{colval:'checkno', coltext:'无隐患状态'},
			   	];
	}else if(n==3){
		window.exdata=[
				   		{colval:'yh', coltext:'隐患级别'},
				   		{colval:'content', coltext:'检查项目'},
				   		{colval:'checkyes', coltext:'有隐患状态'},
				   		{colval:'checkno', coltext:'无隐患状态'},
			   	];
	}
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: content,
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
function openImportDialog2(url,uploadurl,templateurl){
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
			dg2.datagrid('reload');
			if(dg3!=undefined && dg3!=null){
				dg3.datagrid('reload');
			}
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
