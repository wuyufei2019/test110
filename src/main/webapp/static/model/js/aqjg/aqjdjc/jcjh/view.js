var dg;
var dg2;
var dg3;
var dg6;
var id;
var month;
$(function(){
	id=$("#ID").val();
	dg=$('#aqjg_jcjh_qy_dg').datagrid({    
	method: "post",
    url:ctx+'/aqjd/jcjh/list/'+id+'/1', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess:function(){
    	   var titles = $('#qycount').find('.tabs').find('.tabs-title');
    	   titles.eq(0).text('计划检查企业数量('+dg.datagrid('getData').total+")");
    },
	checkOnSelect:false,
	selectOnCheck:false,
	});
	
	//未检查企业
	dg6=$('#aqjg_jcjh_unfin_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/jcjh/list/'+id+'/2', 
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
	        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess:function(){
	  	 // $(this).datagrid("autoMergeCells",['name']);
	  	 var titles = $('#qycount').find('.tabs').find('.tabs-title');
  	      titles.eq(1).text('未检查企业('+dg6.datagrid('getData').total+")");
	    },
		checkOnSelect:false,
		selectOnCheck:false,
		});
	//只完成初检
	dg2=$('#aqjg_jcjh_cjfin_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/jcjh/list2/'+id+'/2', 
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
	        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess:function(){
	  	 // $(this).datagrid("autoMergeCells",['name']);
	  	 var titles = $('#qycount').find('.tabs').find('.tabs-title');
  	      titles.eq(2).text('只完成初查企业('+dg2.datagrid('getData').total+")");
	    },
		checkOnSelect:false,
		selectOnCheck:false,
		});
		
	//企业完成复查
	dg3=$('#aqjg_jcjh_fjfin_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/jcjh/list2/'+id+'/1', 
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
	        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    onLoadSuccess:function(){
	  	  $(this).datagrid("autoMergeCells",['name']);
	  	  var titles = $('#qycount').find('.tabs').find('.tabs-title');
  	      titles.eq(3).text('完成复查企业('+dg3.datagrid('getData').total+")");
	    },
		checkOnSelect:false,
		selectOnCheck:false,
		});
});
