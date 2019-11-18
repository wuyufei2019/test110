var dg;
$(function(){
	dg=$('#target_mbzp_dg').datagrid({    
	method: "post",
    url:ctx+'/target/mbzp/qylist', 
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
              {field:'startdate',title:'开始时间',sortable: true,width:80,align:'center'},
              {field:'enddate',title:'结束时间',sortable: true,width:80,align:'center'},
              {field:'qyname',title:'员工姓名',width:50,align:'center'},
              {field:'department',title:'所属部门',sortable: false,width:100,align:'center',
            	  formatter:function(value){
            		  if(value) {
            			  return "第"+value+"部门";;
            		  }
            		  else return ;
            	  }},
            	  {field:'targetval',title:'指标值',sortable: false,width:50,align:'center',hidden: true},   
              {field:'targetname',title:'指标名称',sortable: false,width:120,align:'center',hidden: true},    
              
              {field:'m4',title:'轨迹状态',sortable: false,width:50,align:'center',
            	  formatter:function(value,row){
            		  if(value) {
       					  return value==1?"正常":"异常";
            		  }
            	  },styler: function(value, row, index){
            		  if(value!=1)
          			return 'background-color:rgb(249, 156, 130)';}},    
              {field:'m1',title:'审核人',sortable: false,width:50,align:'center'},    
              {field:'m2',title:'审核日期',sortable: false,width:80,align:'center',
            	  formatter:function(value){
            		  if(value) return new Date(value).format('yyyy-MM-dd');
            		  else return ;
            	  }},    
              {field:'m3',title:'备注',sortable: false,width:100,align:'center'},
              {field:'year',title:'摄像头',sortable: false,width:50,align:'center',
            	  formatter:function(value,row){
            		  if(value) {
       					  return value==1?"有":"/";
            		  }
            	  },styler: function(value, row, index){
            		  if(value!=1)
          			return 'background-color:rgb(249, 156, 130)';}},  
              {field:'caozuo',title:'操作',sortable:false,width:130,align:'center',
            	  formatter : function(value ,row){
            		  if(row.id%3==0){
            			  return "<a class='btn btn-success btn-xs' onclick='add("+row.jid+")'>查看轨迹</a>";
            		  }else if(row.id%2==1){
            			  return "<a class='btn btn-info btn-xs' onclick='add("+row.jid+")'>审核通过</a><a class='btn btn-success btn-xs' onclick='add("+row.jid+")'>查看轨迹</a>";
            		  }else{
            			  return "<a class='btn btn-success btn-xs' onclick='add("+row.jid+")'>审核不通过</a><a class='btn btn-success btn-xs' onclick='add("+row.jid+")'>查看轨迹</a>";
            		  }
            	  }
              }
              
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