var dg;
var d;
$(function(){
	dg=$('#ztzr_sctr_dg').datagrid({
	method: "post",
    url:ctx+'/ztzr/aqsctr/list',
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
			  {field:'qyname',title:'企业名称',width:60,align:'center'},
              {field:'m1',title:'年度',width:60,align:'center',
				  formatter:function(value, row, index){
					  if(value!=null&&value!=""){
						  return value;
					  }else{
					  	return '/';
					  }
				  }
			  },
              {field:'m2',title:'销售收入（万元）',width:60,align:'center',
                	formatter:function(value, row, index){
                		if(value!=null&&value!=""){
                			return value.toFixed(2);
                		}else{
							return '/';
						}
                	}
              },
              {field:'m3',title:'行业类型',width:70,align:'center',
				  formatter:function(value, row, index){
					  if(value!=null&&value!=""){
						  return value;
					  }else{
						  return '/';
					  }
				  }
			  },
              {field:'m4',title:'提取标准',width:80,align:'center',
				  formatter:function(value, row, index){
					  if(value!=null&&value!=""){
						  return value;
					  }else{
						  return '/';
					  }
				  }
			  },
              {field:'m5',title:'提取数（万元）',width:60,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(2);
            		}else{
						return '/';
					}
            	  }
               },
			  {field:'m7',title:'附件',width:60,align:'center',
				  formatter:function(val,row,index){
					  if(val!=null&&val!=""){
						  var t="||";
						  val=val.split(t);
						  return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';
					  }else{
						  return '--';
					  }

				  }
			  },
			  {field:'dcd',title:'达成度',width:60,align:'center',
				  formatter:function(value, row, index){
					 return "<span class=\'fa fa-close btn-danger btn-outline\' >未落实</span>";
				  }
			  }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
	  	  $(this).datagrid("autoMergeCells",['qyname']);
      },
    toolbar:'#ztzr_sctr_tb'
	});
});

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看费用提取信息",ctx+"/aqsctr/fytq/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}