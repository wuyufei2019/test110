var dg;
var d;
$(function(){
	dg=$('#sjwh_cgxx_dg').datagrid({    
	method: "post",
    url:ctx+'/zxjkyj/cgxx/list', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100},   
        {field:'M1',title:'储罐名称',sortable:false,width:100},   
        {field:'M9',title:'储罐位号',sortable:false,width:100,align:'center'},   
        {field:'M3',title:'容积(m³)',sortable:false,width:100,align:'center'},
        {field:'M10',title:'罐径(m)',sortable:false,width:100,align:'center'},
        {field:'M11',title:'罐高(m)',sortable:false,width:100,align:'center'},
        {field:'level1',title:'一级液位预警比例（小数）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
     		if (value==null)
     			return '/';
     		else
     			return value;
        }},
        {field:'level2',title:'二级液位预警比例（小数）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature1',title:'一级高温预警（℃）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature2',title:'二级高温预警（℃）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'pressure1',title:'一级高压预警（MPa）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'pressure2',title:'二级高压预警（MPa）',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'r1',title:'液位点号',sortable:false,width:100,align:'center'},
        {field:'r2',title:'温度点号',sortable:false,width:100,align:'center'},
        {field:'r3',title:'压力点号',sortable:false,width:100,align:'center'},
        {field:'r4',title:'流量点号',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess:function(){
  	  if(usertype!="9"){
  		 $(this).datagrid("hideColumn",['r1']);
  		 $(this).datagrid("hideColumn",['r2']);
  		 $(this).datagrid("hideColumn",['r3']);
  		 $(this).datagrid("hideColumn",['r4']);
  		$(this).datagrid("hideColumn",['qyname']);
  	  }else{
  		 $(this).datagrid("showColumn",['r1']);
  		 $(this).datagrid("showColumn",['r2']); 
  		 $(this).datagrid("showColumn",['r3']); 
  		 $(this).datagrid("showColumn",['r4']);
  		 $(this).datagrid("showColumn",['qyname']);
  	  }
  	 $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sjwh_cgxx_tb'
	});
	
});

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//重置
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看储罐信息",ctx+"/zxjkyj/cgxx/view/"+row.ID,"900px", "400px","");
	
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改储罐信息",ctx+"/zxjkyj/cgxx/update/"+row.ID,"900px", "400px","");
	
}

