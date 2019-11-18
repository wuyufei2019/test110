var dg;
var d;
var jcdids;
$(function(){
	jcdids=window.parent.getjcdids();
	dg=$('#dxj_jcdchoose_dg').datagrid({    
		method: "get",
	    url:ctx+'/dxj/bcrw/jcdlist', 
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
		scrollbarSize:0,
		checkOnSelect:true,
		selectOnCheck:false,
	    columns:[[    
	        {field:'id',title:'id',checkbox:true,align:'center'}, 
	        {field:'sbm',title:'设备名称',width:60},
	        {field:'name',title:'设备项目名称',width:60,align:'center'},
	        {field:'standard',title:'标准',sortable:false,width:30,align:'center'},
			{field:'scope',title:'范围',sortable:false,width:50,align:'center'}
	    ]],
		onLoadSuccess:function(rowdata, rowindex, rowDomElement){
			$.each(rowdata.rows,function(i,row){
				if((','+jcdids).indexOf(','+row.id+',')!=-1){
					$("#dxj_jcdchoose_dg").datagrid('selectRow',i);
				}
			});
			$(this).datagrid("autoMergeCells", [ 'sbm' ]);
		},
	    toolbar:'#dxj_jcdchoose_tb'
		});
});


function searchjcd() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function getidname2(){
	var row=$('#dxj_jcdchoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].id +"||"+row[i].name+"||"+row[i].area+"||"+row[i].bindcontent+"||"+row[i].sbm+ ",";
		}
	}
	return idname;
}


