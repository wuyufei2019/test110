var dg;
var d;
var pid;
$(function(){
	dg=$('#yhpc_observe_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/observe/list', 
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
              {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
              {field:'s2',title:'观察日期',sortable:false,width:50,align:'center',
    				formatter : function(value, row, index){
  	  				if(value!=null){
  	  					var datetime=new Date(value);
  	  					return datetime.format('yyyy-MM-dd hh:mm:ss');
  	  				}
    				}
              },
              {field:'username',title:'观察员',sortable:false,width:60,align:'center'},    
              {field:'depart',title:'观察区域',sortable:false,width:80,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_observe_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加不安全行为信息",ctx+"/yhpc/observe/create/","800px", "400px","");
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
			url:ctx+"/yhpc/observe/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				//重新加载下拉框
				$('#yhpc_observe_cx_m1').combobox('reload');
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
	
	openDialog("修改不安全行为信息",ctx+"/yhpc/observe/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	pid=row.id;
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['80%', '400px'],
	    title: '详细记录',
        maxmin: false, 
        btn: ['关闭'],
	    content: ctx+'/yhpc/observe/xxjl',
		cancel: function(index){ 
		}
	});
	
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
			   		{colval:'m1', coltext:'不安全行为名称'},
			   		{colval:'m2', coltext:'行为描述'},
			   		{colval:'m3', coltext:'备注'}
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/observe/colindex',
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
			$('#yhpc_observe_cx_m1').combobox('reload');
		}
	}); 
}