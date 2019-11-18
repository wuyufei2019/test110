var dg;
var d;
$(function(){
	dg=$('#zyaqgl_xgfpd_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgfpd/list2?id1='+id1, 
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
              {field:'qynm',title:'企业名称',width:80,align:'center'},   
              {field:'s1',title:'评定日期',width:60,align:'center',
      	    	formatter : function(value, row, index) {
    	          	if(value!==null&&value!='') {
    	          		var datetime=new Date(value);
    	          		 return datetime.format('yyyy-MM-dd');  
    	          	}	
              	} 
              },  
              {field:'pddw',title:'评定单位',width:60,align:'center'},    
              {field:'pdr',title:'评定人员',width:70,align:'center'},
              {field:'m11',title:'总分',width:100,align:'center'},
              {field:'m12',title:'评定等级',width:60,align:'center'}, 
              {field:'zt',title:'状态',sortable:false,width:80,align:'center',
              	formatter : function(value, row, index) {
              		if(row.m11 == null){
              			return  "<a style='margin:2px' class='btn btn-danger btn-xs' >待评定</a>"
              		}else{
              			return  "<a style='margin:2px' class='btn btn-danger btn-xs' >已评定</a>"
              		}
              	} 
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
	  	  if(usertype=="1"){
			  $(this).datagrid("hideColumn",['qynm']);
		  }else{
			  $(this).datagrid("showColumn",['qynm']);
		  }
    	  $(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#zyaqgl_xgfpd_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加相关方评定信息",ctx+"/zyaqgl/xgfpd/create/","700px", "350px","");
}


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
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/zyaqgl/xgfpd/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改相关方评定信息",ctx+"/zyaqgl/xgfpd/update/"+row.id,"700px", "350px","");
	
}

//弹窗修改
function pd(id){
	openDialog("修改相关方评定信息",ctx+"/zyaqgl/xgfpd/update/"+id,"700px", "350px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看相关方评定信息",ctx+"/zyaqgl/xgfpd/view/"+row.id,"700px", "350px","");
	
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
