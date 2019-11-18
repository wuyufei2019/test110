var dg;
var d;
var con1;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
	url:ctx+'/bis/tzsbxx/hislist', 
	queryParams : {"ID1":ID1},
	fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 20, 40, 60, 80, 100 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'m1',title:'检测日期',sortable:true,width:100,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					 var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
		}},
        {field:'m2',title:'检测有效期',sortable:true,width:100,align:'center',
            formatter:function(value,row,index){
            	if(value!=null){
	     			 var datetime = new Date(value);  
	                 return datetime.format('yyyy-MM-dd');    
                }  
            },
        },
    ]],
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});
