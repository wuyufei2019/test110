var dg;
var d;
var jcdids;
$(function(){
	jcdids=window.parent.getjcdids();
	dg=$('#yhpc_jcdchoose_dg').datagrid({    
		method: "get",
	    url:ctx+'/yhpc/bcrw/jcdlist', 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'tid',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize:0,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:false,
	    columns:[[    
	        {field:'tid',title:'id',checkbox:true,align:'center'}, 
	        {field:'qyname',title:'所属企业',width:100},
	        {field:'name',title:'检查点',width:100},
	        {field:'fxfj',title:'风险分级',width:60,
	        	formatter : function(value, row, index) {
	        		if(value=='1'){
	        			return '红';
	        		}else if(value=='2'){
	        			return '橙';
	        		}else if(value=='3'){
	        			return '黄';
	        		}else if(value=='4'){
	        			return '蓝';
	        		}else{
	        			return '自定义点';
	        		}
	        	}
	        },
	        {field:'checkpointtype',title:'类型',width:60,
	        	formatter : function(value, row, index) {
	       			return value==1?'风险点':'隐患排查点';
	        	}
	        }
	    ]],
		onLoadSuccess:function(rowdata, rowindex, rowDomElement){
			var arryids=jcdids.split(",");
			console.log(arryids);
			$.each(rowdata.rows,function(i,row){
				if(arryids.indexOf(row.tid)!=-1){
					$("#yhpc_jcdchoose_dg").datagrid('selectRow',i);
				}
			});
			if(flag == '1'){
				$(this).datagrid("hideColumn", [ 'qyname' ]);
				$(this).datagrid("showColumn", [ 'checkpointtype' ]);
			}else{
				//集团公司
				$(this).datagrid("showColumn", [ 'qyname' ]);
				$(this).datagrid("hideColumn", [ 'checkpointtype' ]);
				$(this).datagrid("autoMergeCells",['qyname']);
			}
		},
	    toolbar:'#yhpc_jcdchoose_tb'
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
	var row=$('#yhpc_jcdchoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].tid +"||"+row[i].name+"||"+row[i].area+"||"+row[i].bindcontent+"||"+row[i].checkpointtype+ ",";
		}
	}
	return idname;
}


