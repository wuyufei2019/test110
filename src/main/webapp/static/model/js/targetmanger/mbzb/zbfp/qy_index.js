var dg;
$(function(){
	dg=$('#target_zbfp_dg').datagrid({    
	method: "post",
    url:ctx+'/target/zbfp/qylist?ajqyid='+ajqyid,
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
              {field:'qynm',title:'企业名称',hidden : true,width:100,align:'center'}, 
              {field:'m1',title:'年份',sortable: true,width:50,align:'center'},    
              {field:'m3',title:'级别',sortable: true,width:50,align:'center',
            	  formatter:function(value){
        		  if(value==1) return '公司';
        		  if(value==2) return '部门';
        		  if(value==3) return '班组';
        		  else return ;
        	  }},
              {field:'deptname',title:'责任部门',sortable: true,width:60,align:'center'},
              {field:'targetname',title:'指标名称',sortable: false,width:120,align:'center'},    
              {field:'targetval',title:'指标值',sortable: false,width:60,align:'center'},    
              {field:'m11',title:'预算（万元）',sortable: true,width:50,align:'center'},    
              {field:'m12',title:'责任人',sortable: false,width:50,align:'center'},    
              {field:'m13',title:'预计完成时间',sortable: true,width:60,align:'center'},
              {field:'m7',title:'制定人',sortable: false,width:50,align:'center'},    
              {field:'m8',title:'审核人',sortable: false,width:50,align:'center'},    
              {field:'m9',title:'批准人',sortable: false,width:50,align:'center'},    
              {field:'m5',title:'批准日期',sortable: true,width:60,align:'center',
            	  formatter:function(value){
            		  if(value) return new Date(value).format('yyyy-MM-dd');
            		  else return ;
            	  }},    
              {field:'url',title:'附件',sortable: false,width:50,align:'center',
            		  formatter:function(value){
            			  if(value){
                  			var urls=value.split(",");
                  			var html="";
                  			for(var index in urls){
                  				html+="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 附件"+(parseInt(index)+1)+"</a><br>"; 
                  			}
                  			return html;
                  		}
                  		else return ; 
            		  }} 
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
    	if(usertype=='isbloc'){
    		$(this).datagrid("showColumn","qynm");
    		$(this).datagrid("autoMergeCells",['qynm','m1','m3','m2','deptname']);
    	}else
    		$(this).datagrid("autoMergeCells",['m1','m3','m2','deptname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#target_zbfp_tb'
	});
	
});