var dg;
var d;
var con1;
$(function(){   
	dg=$('#dg').datagrid({    
		nowrap:false,
	method: "post",
	url:ctx+'/yhpc/aqkf/list',
	queryParams : (parent.P_HeaderType=="expiration"?{isdq : 0 }:null),
	fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'ID',title:'ID',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'员工姓名',sortable:false,width:100},    
        {field:'dep',title:'所属部门',sortable:false,width:100},    
        {field:'m3',title:'最近扣分日期',sortable:false,width:100,align:'center',
			formatter: function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
				}  
			},
        },
        {field:'sum',title:'累计扣分',sortable:false,width:100,align:'center',
	    	styler: function(value, row, index){
	    		if(row.sum!=null&&row.sum!=''&&row.sum>=10){
	    			return 'background-color:#FF6347;';
	    		}
	    	}        	
        },
        {field:'handle',title:'操作',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
        		var cz="&nbsp<a class='btn btn-success btn-xs' onclick='showhistory("+row.id+")'>查看历史</a>";
        		var nowhm=(new Date()).getTime();
                cz="<a class='btn btn-success btn-xs' onclick='add2("+row.id+")'>添加记录</a>"+cz;
                return cz;
        	},
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    }, onLoadSuccess: function(){
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
	
/*	$.ajax({
		type:"GET",
        url:ctx+"/yhpc/aqkf/aqpxTimeEnd",
		success: function(data){
			if(data!='null'){
				layer.msg(data,{time: 3000});
			}
		}
	});*/
	
});

//添加
function add() {
	openDialog("添加基础信息",ctx+'/yhpc/aqkf/create',"800px","250px","");
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
			url:ctx+"/yhpc/aqkf/delete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改基础信息",ctx+'/yhpc/aqkf/update/'+row.id,"800px", "250px","");
}

//保存
function saveBtn(){
	if($("#bis_aqpxxx_form_mainform").form('validate')){
		$.post(ctx+url,$("#bis_tzsbxx_form_mainform").serializeObject(),function(data){
			parent.$.messager.show({ title : "提示",msg: data, position: "bottomRight" });
			$("#bis_aqpxxx_add_div").window('destroy');
			dg.datagrid('reload');
		});
	}
}	


//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看基础信息",ctx+'/yhpc/aqkf/view/'+row.id,"800px", "400px","");
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
	search();
}

//查看更新历史
function showhistory(id) {
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ["800px", "500px"],
	    title: "查看扣分历史",
        maxmin: false, 
	    content: ctx+'/yhpc/aqkf/hisindex/'+id ,
	    btn: ['关闭'],
	    yes: function(index, layero){
	    	dg.datagrid('reload');
	    	layer.close(index);
		},
	}); 
}

//添加扣分历史信息
function add2(id){
	openDialog("添加扣分信息",ctx+'/yhpc/aqkf/createhis/'+id,"800px", "400px","");
}