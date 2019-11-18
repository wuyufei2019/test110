var dg;
$(function(){
	dg=$('#target_aqhd_dg').datagrid({    
	method: "post",
    url:ctx+'/target/aqhd/qylist?qyid='+qyid,
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
              {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
              {field:'qyname',title:'企业名称',hidden:true,width:200,align:'center'},  
              {field:'name',title:'活动名称',sortable:false,width:100,align:'center'},  
              {field:'time',title:'时间',sortable:false,width:80,align:'center',
            	  formatter : function(value) {
              		return new Date(value).format('yyyy-MM-dd');
              }},
              {field:'address',title:'地点',sortable:false,width:150,align:'center'},    
              {field:'deptname',title:'召集部门',sortable:false,width:60,align:'center'}, 
              {field:'gatherper',title:'召集人',sortable:false,width:60,align:'center'}, 
              {field:'actiontlev',title:'活动级别',sortable:false,width:50,align:'center',
            	  formatter: function(value){
            		  if(value=='1') return "公司";
            		  else if(value=='2') return "部门";
            		  else if(value=='3') return "班组";
            		  else return ;
            	  }}, 
              {field:'state',title:'状态',sortable:false,width:100,align:'center',
            		  formatter: function(value){
                		  if(value=='1') return "待开";
                		  else if(value=='2') return "推迟";
                		  else if(value=='3') return "结束";
                		  else return ;
                 }}
            	  ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qyname");
    		$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_aqhd_tb'
	});
	
});