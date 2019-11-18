var dg;
var d;
$(function(){
	dg=$('#aqsc_fytq_dg').datagrid({    
	method: "post",
    url:ctx+'/aqsctr/fytq/list?qyid='+qyid,
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
              {field:'m1',title:'年度',width:60,align:'center'},
		      {field:'m1_2',title:'月份',width:60,align:'center'},
              {field:'m2',title:'销售收入（万元）',width:60,align:'center',
                	formatter:function(value, row, index){
                		if(value!=null&&value!=""){
                			return value.toFixed(4);
                		}
                	}
              },    
              {field:'m3',title:'行业类型',width:70,align:'center'},
              {field:'m4',title:'提取标准',width:80,align:'center'},
              {field:'m5',title:'提取数（万元）',width:60,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(4);
            		}
            	  }
               }          
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
	  	  $(this).datagrid("autoMergeCells",['m1']);
      },
    toolbar:'#aqsc_fytq_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加费用提取信息",ctx+"/aqsctr/fytq/create/","800px", "400px","");
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
			url:ctx+"/aqsctr/fytq/delete/"+ids,
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
	
	openDialog("修改费用提取信息",ctx+"/aqsctr/fytq/update/"+row.id,"800px", "400px","");
	
}

//弹窗修改
function upd2(id){
	openDialog("修改费用提取信息",ctx+"/aqsctr/fytq/update/"+id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看费用提取信息",ctx+"/aqsctr/fytq/view/"+row.id,"800px", "400px","");
	
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
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'年度'},
					{colval:'m1_2', coltext:'月份'},
			   		{colval:'m2', coltext:'销售收入（万元）'},
			   		{colval:'m3', coltext:'行业类型'},
			   		{colval:'m4', coltext:'提取标准'},
			   		{colval:'m5', coltext:'提取数（万元）'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqsctr/fytq/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}