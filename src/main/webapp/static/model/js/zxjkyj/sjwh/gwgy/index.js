var dg;
var d;
$(function(){
	dg=$('#sjwh_gwgy_dg').datagrid({    
	method: "post",
    url:ctx+'/zxjkyj/gwgy/list', 
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
    columns:[
             [{field:'id',title:'id',checkbox:true,width:50,align:'center',"rowspan":2},
              {field:'qyname',title:'企业名称',sortable:false,width:100,"rowspan":2},
              {field:'m1',title:'高危工艺名称',sortable:false,width:100,"rowspan":2},
              {field:'m5',title:'储罐或反应釜(塔)位号',sortable:false,width:100,align:'center',"rowspan":2},
              {title:'高液位预警(LEL/ppm)',sortable:false,width:100,align:'center',"colspan":2},
              {title:'釜内高温度预警(LEL/ppm)',sortable:false,width:100,align:'center',"colspan":2},
              {title:'夹套高温度预警(LEL/ppm)',sortable:false,width:100,align:'center',"colspan":2},
              {title:'高压力预警(LEL/ppm)',sortable:false,width:100,align:'center',"colspan":2},
              {title:'高流量预警(LEL/ppm)',sortable:false,width:100,align:'center',"colspan":2},
              {field:'r1',title:'液位点号',sortable:false,width:100,align:'center',"rowspan":2},
              {field:'r2',title:'温度点号',sortable:false,width:100,align:'center',"rowspan":2},
              {field:'r3',title:'压力点号',sortable:false,width:100,align:'center',"rowspan":2},
              {field:'r4',title:'流量点号',sortable:false,width:100,align:'center',"rowspan":2}
              ],
        [         
        {field:'level1',title:'预警值1',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
     		if (value==null)
     			return '/';
     		else
     			return value;
        }},
        {field:'level2',title:'预警值2',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature1',title:'预警值1',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature2',title:'预警值2',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature3',title:'预警值1',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'temperature4',title:'预警值2',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'pressure1',title:'预警值1',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'pressure2',title:'预警值2',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'flux1',title:'预警值1',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }},
        {field:'flux2',title:'预警值2',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
            }}
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
    toolbar:'#sjwh_gwgy_tb'
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
	
	openDialogView("查看高危工艺信息",ctx+"/zxjkyj/gwgy/view/"+row.id,"1000px", "400px","");
	
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
			url:ctx+"/zxjkyj/gwgy/delete/"+ids,
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

//添加
function add(u) {
	openDialog("添加高危工艺信息",ctx+"/zxjkyj/gwgy/create/","1000px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改储罐信息",ctx+"/zxjkyj/gwgy/update/"+row.id,"1000px", "400px","");
	
}

