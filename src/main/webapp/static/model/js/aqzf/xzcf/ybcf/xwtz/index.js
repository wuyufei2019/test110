var dg;

$(function(){
	dg=$('#aqzf_xwtz_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/xwtz/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'm0',title : '编号',sortable : false,width : 80},
			{field : 'qyname',title : '企业名称',sortable : false,width : 100},
			{field : 'm1',title : '调查主题',sortable : false,width : 110,align : 'center'},
			{field : 'm2',title : '询问时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
                  	if(value!=null&&value!='') {
                  		var datetime=new Date(value);
                  		return datetime.format('yyyy-MM-dd hh:mm:ss');
                  	}
              	}	 
			},
			{field : 'm3',title : '询问地点',sortable : false,width : 130,align : 'center'},
			{field : 'caozuo',title : '添加操作',sortable : false,width : 40,align : 'center',
				formatter : function(value, row, index) {
					return	"<a  class='btn btn-info btn-xs' onclick='addXwbl("
					+ row.id3 + ")'>询问笔录</a>";
					
              	}	 
			},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess: function(){
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqzf_xwtz_tb'
	});
	
});

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}


//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
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
			url:ctx+"/xzcf/ybcf/xwtz/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

//添加
function add() {
	top.layer.confirm('添加询问记录后将会自动生成立案审批，请及时补全信息', {
		icon : 4,
		title : '提示'
	}, function(index) {
		top.layer.close(index);
		openDialog("添加询问通知信息",ctx+"/xzcf/ybcf/xwtz/createtemp","800px", "400px","");
	});
}
function addXwbl(id) {
	openDialog("添加询问笔录信息",ctx+"/xzcf/ybcf/xwbl/create/"+id,"800px", "400px","");
}
//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改询问通知信息",ctx+"/xzcf/ybcf/xwtz/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看询问通知信息",ctx+"/xzcf/ybcf/xwtz/view/"+row.id,"800px", "400px","");
	
}

function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/xwtz/export/xw/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}