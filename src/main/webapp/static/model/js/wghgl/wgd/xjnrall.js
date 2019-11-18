var dg;
$(function(){
	dg=$('#fxgk_xjnrall_dg').datagrid({    
	method: "get",
    url:ctx+'/wghgl/wgd/xjnralllist?id1='+id1+'&qyid='+qyid,
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'ID',
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
  			{field :'ID',title : 'id',checkbox : true,width : 50,align : 'center'},  
  	        {field:'dangerlevel',title:'隐患级别',width:50,
  	        	formatter : function(value, row, index){
  	        		if(value=="1") return value='一般';
  	        		if(value=="2") return value='重大';
  	        	}
  	        },
  	        {field:'checktitle',title:'检查单元',width:80 },  
  	        {field:'content',title:'检查项目',width:150 },  
  	        {field:'checkyes',title:'隐患内容',width:70,align:'center'},
  	        {field:'checkno',title:'正常内容 ',width:70,align:'center'}  
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
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

	var ids = "";
	for (var i = 0; i < cds.length; i++) {
		if (ids == "") {
			ids = cds[i].id;
		} else {
			ids = ids + "," + cds[i].id;
		}
	}
	if (cds.length != 0) {

	$.ajax({
		url : ctx + "/wghgl/wgd/buildCheckContent/" + ids,
		type : "POST",
		 data:{
				id1:id1
	        },
		success : function(data) {
			parent.parent.layer.open({icon:1,title: '提示',offset: 'rb',content:data,shade: 0 ,time: 2000 });
			dg.datagrid('reload');
			dg.datagrid('clearChecked');
			parent.dg.datagrid('reload');
		}
	});

	} else {
		layer.msg("请选择需要绑定的数据！",{time: 1000});
	}
}

////查询
//function search(){
//	var obj=$("#searchFrom").serializeObject();
//	dg.datagrid('load',obj); 
//}
//
//
////清空
//function reset(){
//	$("#searchFrom").form("clear");
//}
