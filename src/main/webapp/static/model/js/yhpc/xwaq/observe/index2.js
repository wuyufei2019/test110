var dg;
var d;
var pid=parent.pid;
$(function(){
	dg=$('#yhpc_observe_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/observe/xxjl/'+pid, 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[   
              {field:'secid',title:'id',checkbox:true,width:50,align:'center'}, 
              {field:'m2',title:'行为描述',sortable:false,width:100,align:'center'},    
              {field:'yw',title:'有无不安全行为 ',sortable:false,width:50,align:'center'},
              {field:'ms',title:'不安全行为描述 ',sortable:false,width:100,align:'center'},
              {field:'sh',title:'可能造成的伤害 ',sortable:false,width:50,align:'center'},
              {field:'yg',title:'涉及人员',sortable:false,width:90,align:'center'},
              {field:'xw',title:'类别',sortable:false,width:60,align:'center'},
              {field:'sl',title:'数量 ',sortable:false,width:40,align:'center'},
	  		  {field:'fj',title : '附件',sortable : false,width : 40,align : 'center',
	            	formatter : function(value, row, index) {
	  			  	  var content="";
	                  	  if(value!=null&&value!='') {
	                		  var arr1=value.split(",");
	                  	  for (var i = 0; i < arr1.length-1; i++) {
	                  		  var arr2=arr1[i].split("||");
	                  		  content=content+ '<a href='+arr2[0]+'>'+arr2[1]+'<a/>';
	                  	  } 
	                  	  return content;
	                	  }else{
	                		  return '/';
	                	  }
	                }
	  		  }
    ]],
    groupField:'m1',
    view: groupview,
	groupFormatter:function(value, rows){
		return value ;
    },
	checkOnSelect:false,
	selectOnCheck:false,
	toolbar:'#yhpc_observe_tb'
	});
});

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].secid;
		}else{
			ids=ids+","+row[i].secid;
		}
	}
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/yhpc/observe/deletejl/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				//重新加载下拉框
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}