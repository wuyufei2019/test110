var dg;
var d;
$(function(){
	dg=$('#yhpc_rcjcbk_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/rcjcbk/list', 
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
              {field:'m1',title:'类别',sortable:false,width:50,align:'center'},    
              {field:'m2',title:'单元',sortable:false,width:50 },
              {field:'m3',title:'检查内容',sortable:false,width:100 },
              {field:'m4',title:'依据',sortable:false,width:50 },
              {field:'m5',title:'备注',sortable:false,width:50 }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['m1','m2']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_rcjcbk_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加日常检查表库信息",ctx+"/yhpc/rcjcbk/create/","800px", "400px","");
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
			url:ctx+"/yhpc/rcjcbk/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				//重新加载下拉框
				$('#yhpc_rcjcbk_cx_m1').combobox('reload');
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
	
	openDialog("修改日常检查表库信息",ctx+"/yhpc/rcjcbk/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看日常检查表库信息",ctx+"/yhpc/rcjcbk/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'类别'},
			   		{colval:'m2', coltext:'检查内容'},
			   		{colval:'m3', coltext:'备注'}
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/rcjcbk/colindex',
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
		},
		btn2: function(index){ 
			  window.location.href=templateurl;
		},
		btn3: function(index){ 
			  layer.close(index);
		},
		cancel: function(){ 
		},
		end: function () {
			//重新加载下拉框
			$('#yhpc_rcjcbk_cx_m1').combobox('reload');
		}
	}); 
}