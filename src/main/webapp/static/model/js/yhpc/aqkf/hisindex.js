var dg;
var d;
var con1;
var cont=1;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
	url:ctx+'/yhpc/aqkf/hislist', 
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
        {field:'m1',title:'扣分时间',sortable:false,width:100,align:'center',
        	formatter: function(value,row,index){
				if(value!=null){
					 var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
        	}
        },
        {field:'m2',title:'扣分原因',sortable:false,width:150},    
        {field:'m3',title:'扣分分值',sortable:false,width:100},
        {field:'cz',title:'操作',sortable:false,width:100,align:'center',
            formatter:function(value,row,index){
            	var cz = '';
            	cz += "<a style='margin:2px' class='btn btn-success btn-xs' onclick='xg("+row.id+")'>修改</a>";
            	return cz;
            }
        }       
    ]],
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

//修改信息
function xg(id){
	openDialog("修改信息",ctx+'/yhpc/aqkf/updatehis/'+id,"700px", "300px","");
}

//删除
function sc(id){
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/yhpc/aqkf/deleterq/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   	{colval:'ygname', coltext:'员工姓名'},
		   		{colval:'dep', coltext:'所属部门'},
		   		{colval:'kftime', coltext:'扣分时间'},
		   		{colval:'kffz', coltext:'扣分分值'},
		   		{colval:'kfyy', coltext:'扣分原因'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/aqkf/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}