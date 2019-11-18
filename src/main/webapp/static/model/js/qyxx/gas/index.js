var dg;
var d;
$(function(){
	dg=$('#bis_gas_dg').datagrid({
	method: "post",
    url:ctx+'/bis/gas/list',
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
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
        {field:'m1',title:'燃气类别',sortable:false,width:80,align:'center'},
        {field:'m2',title:'储存类型',sortable:false,width:50,align:'center'},
        {field:'m3',title:'储罐数量',sortable:false,width:50,align:'center'},
        {field:'m4',title:'容积（m³）',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value!=null&&value!=""){
                    return value.toFixed(2);
                }
            }
        } ,
        {field:'m5',title:'瓶组数量',sortable:false,width:50,align:'center'},
        {field:'m6',title:'瓶组体积（KG）',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value!=null&&value!=""){
                    return value.toFixed(2);
                }
            }
        },
        {field:'m7',title:'管道用量（m³/月）',sortable:false,width:50,align:'center',
            formatter:function(value, row, index){
                if(value!=null&&value!=""){
                    return value.toFixed(2);
                }
            }
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_gas_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加燃气信息",ctx+"/bis/gas/create/","800px", "400px","");
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
			url:ctx+"/bis/gas/delete/"+ids,
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
	
	openDialog("修改燃气信息",ctx+"/bis/gas/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看燃气信息",ctx+"/bis/gas/view/"+row.ID,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'燃气类别'},
			   		{colval:'m2', coltext:'储存类型'},
			   		{colval:'m3', coltext:'储罐数量'},
			   		{colval:'m4', coltext:'储罐容积'},
			   		{colval:'m5', coltext:'瓶组数量'},
			   		{colval:'m6', coltext:'瓶组体积'},
			   		{colval:'m7', coltext:'管道月用量'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/gas/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}