var dg;
var d;

$(function(){ 
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/gygc/list', 
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
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'类别',sortable:true,width:80,align:'center'},
        {field:'m2',title:'占地面积(㎡)',sortable:true,width:80,align:'center',
        	formatter:function(value, row, index){
        		if(value!=null&&value!=""){
        			return value.toFixed(2);
        		}
        	}
        },
        {field:'m3',title:'建筑结构',sortable:true,width:80,align:'center'},
        {field:'m4',title:'主要危险性',width:150,align:'center',
        	formatter:function(value,row,index){
        		if(value.length>24){
        			value=value.substring(0,24)+"...";
        		}
        		return value;
        	}},
	  {field:'m5',title:'照片',sortable:false,width:50,align:'center',
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
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});	
});

//增加
function add() {
	openDialog("添加公用工程信息",ctx+'/bis/gygc/create',"800px","400px","");
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
			url:ctx+"/bis/gygc/delete/"+ids,
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
	openDialog("修改公用工程信息",ctx+'/bis/gygc/update/'+row.id,"800px", "400px","");
}


//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看公用工程信息",ctx+'/bis/gygc/view/'+row.id,"800px", "400px","");
}

//查询
function search(){
	$("#bis_gygc_cx_m1").combobox("setValue",$("#bis_gygc_cx_m1").combobox("getText"));
	$("#bis_gygc_cx_m3").combobox("setValue",$("#bis_gygc_cx_m3").combobox("getText"));
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'类别'},
			   		{colval:'m2', coltext:'占地面积'},
			   		{colval:'m3', coltext:'建筑结构'},
			   		{colval:'m4', coltext:'主要危险性'},
			   		{colval:'m6', coltext:'备注'},
		   	];
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/gygc/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}