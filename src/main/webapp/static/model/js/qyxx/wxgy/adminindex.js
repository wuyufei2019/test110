var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/gwgy/ajlist', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'value',title:'高危工艺',sortable:false,width:100,align:'left'},    
        {field:'M2',title:'套数（套）',sortable:false,width:100,align:'center'},
        {field:'m4',title:'照片',sortable:false,width:50,align:'center',
          	formatter : function(value) {
          		if(value){
          			var urls=value.split(",");
          			var html="";
          			for(var index in urls){
          				html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>"; 
          			}
          			return html;
          		}
          		else return ; 
         	 }
          }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

//添加
function add() {
	openDialog("添加危险工艺信息",ctx+'/bis/gwgy/create',"800px","400px","");
}
//统计
function statistics(){
	openDialogView("高危工艺统计",ctx+"/bis/gwgy/statistics","900px", "500px", "");
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/gwgy/delete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改危险工艺信息",ctx+'/bis/gwgy/update/'+row.ID,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看危险工艺信息",ctx+'/bis/gwgy/view/'+row.ID,"800px", "400px","");
}

//查询
function search(){
	var obj=$("#searchForm").serializeObject();
	obj.bis_gwgy_qy_name=$("#bis_gwgy_qy_name").combobox("getText");
	obj.bis_gwgy_cx_m1=$("#bis_gwgy_cx_m1").combobox("getValue");
	dg.datagrid('load',obj);
}
//清空
function reset(){
	$("#searchForm").form("clear");
	search();
}
function fileexport(){
	var obj=$("#searchForm").serializeObject();
	obj.bis_gwgy_qy_name=$("#bis_gwgy_qy_name").combobox("getText");
	obj.bis_gwgy_cx_m1=$("#bis_gwgy_cx_m1").combobox("getValue");
	window.expara=obj;
	window.exdata=[
	               {colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'高危工艺'},
			   		{colval:'m2', coltext:'套数（套）'},
			   		{colval:'m3', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
	    maxmin: true, 
	    shift: 1,
	    content: ctx+'/bis/gwgy/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  }
	});
}