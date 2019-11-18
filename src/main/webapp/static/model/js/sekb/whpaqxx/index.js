var dg;
var cxtype;
var d;
var usertype;
$(function(){
	cxtype="1";
	dg=$('#sekb_whpaqxx_dg').datagrid({    
	method: "post",
    url:ctx+'/sekb/whpaqxx/list', 
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
              {field:'m2',title:'品名',width:100},    
              {field:'m3',title:'别名',width:100},
              {field:'m4',title:'英文名',width:120},
              {field:'m5',title:'CAS',width:70},
              {field:'m6',title:'危险性类别',width:150},
              {field:'m8',title:'重点监管',width:50,align:'center'},
              {field:'m9',title:'剧毒',width:30,align:'center'},
              {field:'m10',title:'易制毒',width:40,align:'center'},
              {field:'m7',title:'备注',width:60}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sekb_whpaqxx_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加危化品安全信息",ctx+"/sekb/whpaqxx/create/","800px", "400px","");
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
			url:ctx+"/sekb/whpaqxx/delete/"+ids,
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
	
	openDialog("修改危化品安全信息",ctx+"/sekb/whpaqxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看危化品安全信息",ctx+"/sekb/whpaqxx/view/"+row.id,"800px", "400px","");
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
			   		{colval:'m2', coltext:'品名'},
			   		{colval:'m3', coltext:'别名'},
			   		{colval:'m4', coltext:'英文名'},
			   		{colval:'m5', coltext:'CAS'},
			   		{colval:'m6', coltext:'危险性类别'},
			   		{colval:'m8', coltext:'重点监管'},
			   		{colval:'m9', coltext:'剧毒'},
			   		{colval:'m10', coltext:'易制毒'},
			   		{colval:'m7', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sekb/whpaqxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}