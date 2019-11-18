var dg;
$(function(){
	dg=$('#target_zbkh_dg').datagrid({    
	method: "post",
    url:ctx+'/target/zbkh/qylist?ajqyid='+ajqyid,
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
              {field:'qyname',title:'企业名称',hidden: true,width:150,align:'center'},  
              {field:'year',title:'年度',sortable:false,width:50,align:'center'},    
              {field:'dpname',title:'考核部门',sortable:false,width:80,align:'center'},    
              {field:'tnum',title:'目标指标数',sortable:false,width:60,align:'center'},    
              {field:'zpnum',title:'自评达标数',sortable:false,width:60,align:'center'},    
              {field:'khnum',title:'考核达标数',sortable:false,width:60,align:'center',
            	  formatter:function(value, row, rowDomElement){
            		  if(value==null){
            			 return "<a class='fa fa-plus btn-info btn-outline' onclick='updatekh("+ row.id + ")'>考核</a>" 
            		  }else return value+"--<a class='fa fa-edit btn-danger btn-outline' onclick='updatekh("+ row.id + ")'>修改</a>";
            	   }},    
              {field:'m4',title:'考核结论',sortable:false,width:100,align:'center',
            	  formatter:function(value){
            		  if(value){
            			  if(value==0) return '未达标';
            			  else if(value==1) return '达标';
            		  }else return ;
            				  
            	  }},    
              {field:'m5',title:'考核情况说明',sortable:false,width:50,align:'center'},
              {field:'m2',title:'考核人',sortable:false,width:50,align:'center'},
              {field:'m3',title:'考核时间',sortable:false,width:80,align:'center',
            	  formatter : function(value) {
            		if(value)return new Date(value).format('yyyy-MM-dd');
              }}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qyname");
    		$(this).datagrid("autoMergeCells",['qyname','year']);
    	}else
    		$(this).datagrid("autoMergeCells",['year']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_zbkh_tb'
	});
	
});