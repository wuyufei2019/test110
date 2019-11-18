var dg;
var d;
$(function(){
	dg=$('#aqpx_kjgl_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqpx/kjkgl/list', 
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
		singleSelect:false,
		striped:true,
	    columns:[[    
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	              {field:'m1',title:'课件名称',width:100},    
	              {field:'m2',title:'附件',width:50,align:'center',
	              	formatter : function(value, row, index){
	            		var content="";
	            		if(value!=null&&value!='') {
	            			var arr1=value.split(",");
	        				var url=arr1[0];
	        				var arr2=url.split("||");
	        				var arr3=arr2[0].split(".");
	        				var wjurl=arr3[0]+"."+row.m3;
	            			content=content+'<a href="'+wjurl+'" target="_blank">'+arr2[1]+'</a><br>'; 
	                		return content;
	            		}else{
	            			return '/';
	            		}
	                }  
	              }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	                 view();
	    },
	    toolbar:'#aqpx_kjgl_tb'
		});
});


function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function getdata(){
	var row=$('#aqpx_kjgl_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			var arr=row[i].m2.split("||");
			var arr2=row[i].m2.split(".");
			var fjurl=arr2[0]+"."+row[i].m3;
			var wjurl=arr[0]+"||"+row[i].m1;
			idname += row[i].m1 +";"+wjurl+";"+fjurl+";"+row[i].m3+ ",";
		}
	}
	return idname;
}


