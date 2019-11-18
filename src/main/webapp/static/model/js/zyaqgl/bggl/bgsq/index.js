var dg;
var d;
var sh=0;
var sp=0;
$(function(){
	dg=$('#zyaqgl_bgsq_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/bgsq/list?qyid='+qyid,
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
              {field:'s1',title:'申请时间',width:60,align:'center',
      	    	formatter : function(value, row, index) {
    	          	if(value!==null&&value!='') {
    	          		var datetime=new Date(value);
    	          		 return datetime.format('yyyy-MM-dd');  
    	          	}	
              	} 
              },  
              {field:'m1',title:'变更名称',width:60,align:'center'},    
              {field:'sqr',title:'申请人',width:70,align:'center'},
              {field:'depart',title:'部门',width:100,align:'center'},
              {field:'m2',title:'变更日期',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd');  
            		  }
            	  }  
              }, 
              {field:'zt',title:'状态',sortable:false,width:80,align:'center',
              	formatter : function(value, row, index) {
              		var a = '';
              		//无审核审批权限
              		if(sh==0&&sp==0){
              			if(row.m10 == '0'){
      	            		a +="<font style='margin:2px' >批准未通过</font>";
      	            	}else if(row.m10 == '1'){
      	            		a +="<font style='margin:2px' >批准通过</font>";
      	            	}else if(row.m7 == '0'){
      	            		a +="<font style='margin:2px' >审核未通过</font>";
      	            	}else if(row.m7 == '1'){
      	            		a +="<font style='margin:2px' >审核通过待批准</font>";
      	            	}else{
      	            		a +="<font style='margin:2px' >待审核</font>";
      	            	}
              		}else if(sh==1&&sp==0){//有审核权限无审批权限
              			if(row.m10 == '0'){
      	            		a +="<font style='margin:2px'>批准未通过</font>";
      	            	}else if(row.m10 == '1'){
      	            		a +="<font style='margin:2px'>批准通过</font>";
      	            	}else if(row.m7 == '0'){
      	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
      	            	}else if(row.m7 == '1'){
      	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
      	            	}else{
      	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
      	            	}
              		}else if(sh==1&&sp==1){//有审核审批权限
              			if(row.m10 == '0'){
      	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
      	            	}else if(row.m10 == '1'){
      	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
      	            	}else if(row.m7 == '0'){
      	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
      	            	}else if(row.m7 == '1'){
      	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a> &nbsp <a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
      	            	}else{
      	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
      	            	}
              		}else if(sh==0&&sp==1){//无审核有审批权限
              			if(row.m10 == '0'){
      	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
      	            	}else if(row.m10 == '1'){
      	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
      	            	}else if(row.m7 == '0'){
      	            		a +="<font style='margin:2px'>审核未通过</font>";
      	            	}else if(row.m7 == '1'){
      	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
      	            	}else{
      	            		a +="<font style='margin:2px'>待审核</font>";
      	            	}
              		}
                  	return a;
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
    toolbar:'#zyaqgl_bgsq_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加变更申请信息",ctx+"/zyaqgl/bgsq/create/","800px", "400px","");
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
			url:ctx+"/zyaqgl/bgsq/delete/"+ids,
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
	if(row.m10==1) {
		layer.msg("批准已通过，不得进行修改！",{time: 3000});
		return;
	}else if(row.m7==1&&row.m10==0) {//已审核，批准未通过
		openDialog("修改相关方评定信息",ctx+"/zyaqgl/bgsq/update/update/"+row.id,"800px", "400px","");
		return;
	}else if(row.m7==1) {//已审核，还未批准
		layer.msg("已审核通过，还未进行批准操作，不得进行修改！",{time: 3000});
		return;
	}else {//审核不通过或者还未审核
		openDialog("修改相关方评定信息",ctx+"/zyaqgl/bgsq/update/update/"+row.id,"800px", "400px","");
		return;
	}
}

//审核
function addsh(id){
	openDialog("审核相关方评定信息",ctx+"/zyaqgl/bgsq/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准相关方评定信息",ctx+"/zyaqgl/bgsq/update/sp/"+id,"800px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看变更申请信息",ctx+"/zyaqgl/bgsq/view/"+row.id,"800px", "400px","");
	
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
