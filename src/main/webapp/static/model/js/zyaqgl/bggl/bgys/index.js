var dg;
var d;
$(function(){
	dg=$('#zyaqgl_bgys_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/bgys/list?qyid='+qyid,
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
              {field:'qyname',title:'企业名称',width:80,align:'center'},   
              {field:'bgxm',title:'变更项目名称',width:60,align:'center'},  
              {field:'m2',title:'验收单位',width:60,align:'center'},    
              {field:'m3',title:'验收日期',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd');  
            		  }
            	  }  
              }, 
              {field:'m6',title:'验收结论',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value==1){
            			  return '合格';
            		  }else{
            			  return '不合格';
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
    toolbar:'#zyaqgl_bgys_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加变更验收信息",ctx+"/zyaqgl/bgys/create/","800px", "400px","");
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
			url:ctx+"/zyaqgl/bgys/delete/"+ids,
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
	
	openDialog("修改变更验收信息",ctx+"/zyaqgl/bgys/update/"+row.id,"800px", "400px","");
	
}

//审核
function addsh(id){
	openDialog("审核变更验收信息",ctx+"/zyaqgl/bgys/update/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准变更验收信息",ctx+"/zyaqgl/bgys/update/"+id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看变更验收信息",ctx+"/zyaqgl/bgys/view/"+row.id,"800px", "400px","");
	
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
