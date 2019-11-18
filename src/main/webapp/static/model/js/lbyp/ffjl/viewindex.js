var dg;
$(function(){
	dg=$('#dgdata').datagrid({    
	method: "post",
	url: ctx+'/lbyp/ffjl/detaillist/'+parent.eid,
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
	singleSelect:true,
	striped:true,
	columns:[[    
	          {field:'id',title:'id',width:50,hidden : true},
	          {field:'ename',title:'员工姓名',sortable:false,width:50}, 
	          {field:'goodsname',title:'用品名称',sortable:false,width:40},
	          {field:'amount',title:'数量',sortable:false,width:20},
	          {field:'time',title:'领取日期',sortable:false,width:40, formatter : function (value, row){
	          	if(value) return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	          	else return '';
	          }},    
	          {field:'flg',title:'操作', sortable:false, width:30, formatter : function (value, row){
	        	  var html="<a class='btn btn-info btn-xs' onclick='view("+row.id+")'>查看</a>";
	        	  if(value)
	        		  html+= "<a class='btn btn-warning btn-xs' onclick='upd("+row.id+")'>修改  </a>";
	        		  		//+"<a class='btn btn-danger btn-xs' onclick='del("+row.id+")'>删除</a>";
	        	  return html;
	          }}   
	    ]],
    onDblClickRow: function (index, row){
        view(row.id);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['ename']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_ffjl_tb'
	});
	
});

//删除
function del(id){
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/lbyp/ffjl/delete/"+id,
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

//弹窗查看
function view(id){
	openDialogView("查看记录信息",ctx+"/lbyp/ffjl/view/"+id,"800px", "250px","");
}
//弹窗修改
function upd(id){
	openDialog("修改发放记录信息",ctx+"/lbyp/ffjl/update/"+id,"800px", "400px","");
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

