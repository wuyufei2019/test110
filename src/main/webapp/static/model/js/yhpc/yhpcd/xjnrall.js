var dg;
$(function(){
	dg=$('#fxgk_xjnrall_dg').datagrid({    
	method: "get",
    url:ctx+'/yhpc/yhpcd/xjnralllist?id1='+id1+'&qyid='+qyid,
    fit : false,
	fitColumns : true,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:false,
	striped:true,
    columns:[[    
  			{field :'id',title : 'id',checkbox : true,width : 50,align : 'center'},  
  	        {field:'dangerlevel',title:'隐患级别',width:50,
  	        	formatter : function(value, row, index){
  	        		if(value=="1") return value='一般';
  	        		if(value=="2") return value='重大';
  	        	}
  	        },
  	        {field:'checktitle',title:'检查单元',width:80 },  
  	        {field:'content',title:'检查项目',width:150 }  
    ]],
    onLoadSuccess: function(){
    	var rows=parent.dg.datagrid('getData').rows;
    	for(var i=0;i<rows.length;i++){
    		dg.datagrid('selectRecord',rows[i].id);
    	}
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:true,
	selectOnCheck:true,
    toolbar:'#fxgk_xjnrall_tb'
	});
	
});

//绑定
function add(u) {
	var cds = dg.datagrid("getChecked");
	if (cds == null || cds == "") {
		layer.msg("请选择需要绑定的数据！",{time: 1000});
		return;
	}

	for(var ckecknum=0;ckecknum<cds.length;ckecknum++){
		var srow=dg.datagrid("getChecked")[ckecknum];
		var prows=parent.dg.datagrid('getData');
		var b=true;
		for(var i=0;i<prows.total;i++){
			if(prows.rows[i].id==srow.id){
				b=false;
				break;
			}
		}
		if(b){
			parent.dg.datagrid('appendRow',srow);
		}
	}
}

//查询
function search(){
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}