var dg;
var d;
var con1;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
	url:ctx+'/bis/ygtjxx/hislist', 
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
        {field:'m3',title:'体检类型',sortable:false,width:50,align:'center'},
        {field:'m1',title:'体检日期',sortable:true,width:80,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					 var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
		}},
		{field:'m5',title:'接触危害因素',sortable:false,width:80,align:'center'},
		{field:'m4',title:'体检医院',sortable:false,width:100,align:'center'},
		{field:'m7',title:'体检结论',sortable:false,width:50,align:'center'},
        {field:'m2',title:'下次体检日期',sortable:true,width:80,align:'center',
            formatter:function(value,row,index){
            	if(value!=null){
	     			 var datetime = new Date(value);  
	                 return datetime.format('yyyy-MM-dd');    
                }  
            },
        }    
    ]],
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

