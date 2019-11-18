var dg;
var d;
$(function(){
	dg=$('#zyaqgl_xgfwg_dg').datagrid({    
	method: "post",
    url:ctx+'/zyaqgl/xgfwg/list?qyid='+qyid,
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
     		{field:'year', title:'年份',sortable : false,width : 100},
       		{field:'pddw', title:'评定单位',sortable : false,width : 100},
       		{field:'recentDate', title:'最近违规时间',sortable : false,width : 100,
	          	  formatter : function(value, row, index) {
	                	if(value!=null&&value!='') {
	                		var datetime=new Date(value);
	                		 return datetime.format('yyyy-MM-dd');  
	                	}
	              } 
       		},
       		{field:'kffz', title:'累积扣分值',sortable : false,width : 100,
		    	styler: function(value, row, index){
		    		if(value>40){//时间存在的情况下
			    			return 'background-color:rgb(249, 156, 140);';
		    		}
		    	}
       		},
    		{field:'m5',title :'操作',sortable : false,width : 100,align : 'center',
       			formatter : function(value, row, index) {
	            	return "<a class='btn btn-warning btn-xs' onclick='add("+row.dwid+",\""+row.pddw+"\")'>添加违规记录</a>" +
	            	"<a class='btn btn-info btn-xs' onclick='hisindex("+row.dwid+",\""+row.pddw+"\")'>查看历史记录</a>";       				
       			}
    		}
    ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zyaqgl_xgfwg_tb'
	});
});

//弹窗增加
function add(id,pddw) {
	openDialog("添加相关方违规信息",ctx+"/zyaqgl/xgfwg/createwg/"+id+"/"+pddw,"800px", "400px","");
}

//弹窗增加
function addgl() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("添加相关方关联信息",ctx+"/zyaqgl/xgfwg/create/"+row.id+"/"+row.pddw,"800px", "400px","");
	
}

//历史违规记录
function hisindex(id,pddw) {
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['90%', '80%'],
	    title: "相关方违规历史",
        maxmin: true, 
	    content: ctx+"/zyaqgl/xgfwg/index2?dwid="+id+"&pddw="+pddw ,
	    btn: ['关闭'],
		cancel: function(index){ 
	    }
	}); 
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
			url:ctx+"/zyaqgl/xgfwg/delete/"+ids,
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
	
	openDialog("修改相关方单位信息",ctx+"/zyaqgl/xgfwg/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看相关方单位信息",ctx+"/zyaqgl/xgfwg/view/"+row.id,"900px", "500px","");
	
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
