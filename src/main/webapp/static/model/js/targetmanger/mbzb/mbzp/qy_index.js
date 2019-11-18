var dg;
$(function(){
	dg=$('#target_mbzp_dg').datagrid({    
	method: "post",
    url:ctx+'/target/mbzp/qylist?ajqyid='+ajqyid,
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
              {field:'id',title:'id',checkbox: true,width:50,align:'center'},   
              {field:'qyname',title:'企业名称',hidden: true,width:150,align:'center'},   
              {field:'year',title:'年度',sortable: false,width:50,align:'center'},    
              {field:'department',title:'责任部门',sortable: true,width:80,align:'center'},
              {field:'quarter',title:'季度',sortable: false,width:50,align:'center',
            	  formatter:function(value){
            		  if(value) {
            			  return "第"+value+"季度";;
            		  }
            		  else return ;
            	  }},
            	  {field:'targetval',title:'指标值',sortable: false,width:50,align:'center'},   
              {field:'targetname',title:'指标名称',sortable: false,width:120,align:'center'},    
              
              {field:'m4',title:'达标情况',sortable: false,width:50,align:'center',
            	  formatter:function(value,row){
            		  if(value) {
       					  return value==1?"达标":"未达标";
            		  }
            	  },styler: function(value, row, index){
            		  if(value!=1)
          			return 'background-color:rgb(249, 156, 130)';}},    
              {field:'m1',title:'评定人',sortable: false,width:50,align:'center'},    
              {field:'m2',title:'评定日期',sortable: false,width:50,align:'center',
            	  formatter:function(value){
            		  if(value) return new Date(value).format('yyyy-MM-dd');
            		  else return ;
            	  }},    
              {field:'m3',title:'备注',sortable: false,width:100,align:'center'}   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qyname");
    		$(this).datagrid("autoMergeCells",['qyname','year','department','quarter','m2']);
    	}else
    		$(this).datagrid("autoMergeCells",['year','department','quarter','m2']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_mbzp_tb'
	});
	
});