var dg;
var d;
$(function(){
	$("#type").combobox("setValue","按部门");
	dg=$('#yhpc_observecount_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/gctj/list', 
    fit : false,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	rownumbers:true,
	nowrap:false,
	singleSelect:true,
	striped:true,
    columns:[
             [    
              {field:'m1',title:'观察类别',sortable:false,width:100,"rowspan":2},
              {title:'可能造成的伤害(轻伤/重伤/死亡/其他事故)',sortable:false,width:100,"colspan":4},
              {title:'全部的不安全行为和状态',sortable:false,width:100,"colspan":2}
             ],
             [
              {field:'qs',title:'轻伤',sortable:false,width:100,align:'center'},
              {field:'zs',title:'重伤',sortable:false,width:100,align:'center'},
              {field:'sw',title:'死亡',sortable:false,width:100,align:'center'},
              {field:'qt',title:'其他事故',sortable:false,width:100,align:'center'},
              {field:'baqxw',title:'不安全行为数量',sortable:false,width:100,align:'center'},
              {field:'baqzt',title:'不安全状态数量',sortable:false,width:100,align:'center'}
             ]
    ],
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#yhpc_observecount_tb',
    onLoadSuccess:function(){
      }
	});
});

function ry(){
	dg=$('#yhpc_observecount_dg').datagrid({    
		method: "post",
	    url:ctx+'/yhpc/gctj/list', 
	    fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		striped:true,
	    columns:[
	             [    
	              {field:'m1',title:'观察类别',sortable:false,width:100,"rowspan":2},
	              {title:'可能造成的伤害(轻伤/重伤/死亡/其他事故)',sortable:false,width:100,"colspan":4},
	              {title:'全部的不安全行为和状态',sortable:false,width:100,"colspan":2}
	             ],
	             [
	              {field:'qs',title:'轻伤',sortable:false,width:100,align:'center'},
	              {field:'zs',title:'重伤',sortable:false,width:100,align:'center'},
	              {field:'sw',title:'死亡',sortable:false,width:100,align:'center'},
	              {field:'qt',title:'其他事故',sortable:false,width:100,align:'center'},
	              {field:'baqxw',title:'不安全行为数量',sortable:false,width:100,align:'center'},
	              {field:'baqzt',title:'不安全状态数量',sortable:false,width:100,align:'center'}
	             ]
	    ],
		checkOnSelect:false,
		selectOnCheck:false,
		toolbar:'#yhpc_observecount_tb',
	    onLoadSuccess:function(){
	      }
	});
}

// 创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	loadEcharts();
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	loadEcharts();
}

