var dg;
var d;
$(function(){
	$("#type").combobox("setValue","按部门");
	dg=$('#yhpc_observecount_dg').datagrid({    
	method: "post",
    // url:ctx+'/yhpc/gctj/list',
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
              {field:'m1',title:'行为类别记录',sortable:false,width:100,"rowspan":2},
              {title:'行为信息',sortable:false,width:100,"colspan":4},
              {title:'安全状态',sortable:false,width:100,"colspan":2}
             ],
             [
              {field:'qs',title:'员工编号',sortable:false,width:100,align:'center'},
              {field:'zs',title:'行为描述',sortable:false,width:100,align:'center'},
              {field:'sw',title:'开始时间',sortable:false,width:100,align:'center'},
              {field:'qt',title:'结束时间',sortable:false,width:100,align:'center'},
              {field:'baqxw',title:'人员行为',sortable:false,width:100,align:'center'},
              {field:'baqzt',title:'设备状态',sortable:false,width:100,align:'center'}
             ]
    ],
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#yhpc_observecount_tb',

        data: [
            {id:'1', m1:'跨区域行为' ,qs:'P03066',zs:'离开车间05',sw:'2018/12/31 15:03',qt:'2019/01/01 15:20',baqxw:'其他',baqzt:''},
            {id:'1', m1:'跨区域行为' ,qs:'S05211',zs:'离开质检车间',sw:'2018/12/26 09:18',qt:'2018/12/26 10:02',baqxw:'访客安排',baqzt:''},


        ],

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

