var dg;
var d;

$(function(){
	dg=$('#sggl_sgxx_dg').datagrid({  
	method: "post",
    url:ctx+'/sggl/sgxx/list', 
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
              {field:'qynm',title:'企业名称',width:100,align:'center'},   
              {field:'m1',title:'事故编号',width:60,align:'center'},
              {field:'m2',title:'事故名称',width:80,align:'center'},
              {field:'m3',title:'事故类型',width:50,align:'center'},
              {field:'m4',title:'事故等级',width:40,align:'center'},
              {field:'m5',title:'发生时间',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd hh:mm:ss');  
            		  }
            	  }  
              },    
              {field:'m6',title:'发生地点',width:100,align:'center'},
              {field:'bmname',title:'所属部门',width:50,align:'center'},
              {field:'m7',title:'事故性质',width:40,align:'center'},
              {field:'qs',title:'轻伤人数',width:30,align:'center'},
              {field:'zs',title:'重伤人数',width:30,align:'center'},
              {field:'sw',title:'死亡人数',width:30,align:'center'},
              {field:'m11',title:'经济损失(万元)',width:40,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(2);
            		}
            	}
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess:function(){
		if(type == '2' || usertype != '1'){
			$(this).datagrid("showColumn",['qynm']);
		 	$(this).datagrid("autoMergeCells",['qynm']);
		}else{
		 	$(this).datagrid("hideColumn",['qynm']);
		}		
     },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sggl_sgxx_tb'
	});
});


//弹窗增加
function add(u) {
	openDialog("添加事故信息",ctx+"/sggl/sgxx/create/","100%", "100%","");
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
			url:ctx+"/sggl/sgxx/delete/"+ids,
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
	
	openDialog("修改事故登记信息",ctx+"/sggl/sgxx/update/"+row.id,"100%", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看事故登记信息",ctx+"/sggl/sgxx/view/"+row.id,"100%", "100%","");
	
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
