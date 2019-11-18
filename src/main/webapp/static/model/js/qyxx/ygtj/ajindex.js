var dg;
var d;
//var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
$(function(){
	dg=$('#bis_ygtj_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/ygtjxx/ajlist?qyid='+qyid,
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
        {field:'m7',title:'员工姓名',sortable:false,width:50,align:'center'},
        {field:'m2',title:'体检类型',sortable:false,width:50,align:'center'},
        {field:'m3',title:'体检日期',sortable:false,width:50,align:'center',
      	  formatter : function(value, row, index) {
            	if(value!=null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m4',title:'体检医院',sortable:false,width:100,align:'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_ygtj_tb'
	});
	
	var flag=true;
});


//弹出添加或修改框
function openFormWindow(url,title){
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['800px', '400px'],
	    title: title,
      maxmin: true,  
	    content: url,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         iframeWin.contentWindow.doSubmit();
		  },
		  cancel: function(index){ 
	       }
	}); 	
}



//弹窗增加
function add(u) {
	openFormWindow(ctx+'/bis/ygtjxx/create/', '添加员工体检信息');
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
			url:ctx+"/bis/ygtjxx/delete/"+ids,
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
	
	openFormWindow(ctx+'/bis/ygtjxx/update/'+row.id, '修改员工体检信息');
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看员工体检信息",ctx+"/bis/ygtjxx/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_ygtj_cx_qyname').combobox('setValue',$('#bis_ygtj_cx_qyname').combobox('getText'));
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
	$('#bis_ygtj_cx_qyname').combobox('setValue',$('#bis_ygtj_cx_qyname').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'qyname', coltext:'企业名称'},
			        {colval:'m7', coltext:'员工姓名'},
			        {colval:'m1', coltext:'身份证号'},
			   		{colval:'m2', coltext:'体检类型'},
			   		{colval:'m3', coltext:'体检日期'},
			   		{colval:'m4', coltext:'体检医院'},
			   		{colval:'m5', coltext:'体检结论'},
			   		{colval:'m6', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/ygtjxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}