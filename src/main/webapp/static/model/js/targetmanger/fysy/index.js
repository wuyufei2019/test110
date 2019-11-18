var dg;
var d;
$(function(){
	dg=$('#aqsc_fysy_dg').datagrid({    
	method: "post",
    url:ctx+'/aqsctr/fysy/list?qyid='+qyid,
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
              {field:'m1',title:'日期',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd');  
            		  }
            	  }  
              },  
              {field:'depart',title:'使用部门',width:60,align:'center'},    
              {field:'lx',title:'支出项目类别',width:90,align:'center'},
              {field:'m3',title:'具体用途',width:120,align:'center'},
              {field:'m4',title:'金额（万元）',width:60,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(4);
            		}
            	  }
               },
              {field:'m5',title:'经办人',width:60,align:'center'},
              {field:'m6',title:'审核人',width:60,align:'center'},
              {field:'m7',title:'批准人',width:60,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqsc_fysy_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加费用使用信息",ctx+"/aqsctr/fysy/create/","800px", "400px","");
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
			url:ctx+"/aqsctr/fysy/delete/"+ids,
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
	
	openDialog("修改费用使用信息",ctx+"/aqsctr/fysy/update/"+row.id,"800px", "400px","");
	
}

//弹窗修改
function upd2(id){
	openDialog("修改费用使用信息",ctx+"/aqsctr/fysy/update/"+id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看费用使用信息",ctx+"/aqsctr/fysy/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'日期'},
			   		{colval:'depart', coltext:'使用部门'},
			   		{colval:'lx', coltext:'支出项目类别'},
			   		{colval:'m3', coltext:'具体用途'},
			   		{colval:'m4', coltext:'金额（万元）'},
			   		{colval:'m5', coltext:'经办人'},
			   		{colval:'m6', coltext:'审核人'},
			   		{colval:'m7', coltext:'批准人'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/aqsctr/fysy/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}