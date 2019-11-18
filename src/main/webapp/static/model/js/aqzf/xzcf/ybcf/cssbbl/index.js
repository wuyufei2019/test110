var dg;

$(function(){
	dg=$('#aqzf_cssbbl_dg').datagrid({    
	method: "post",
    url:ctx+'/xzcf/ybcf/cssbbl/list', 
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
			{field : 'm11',title : '案件名称',sortable : false,width : 100,align : 'center'},
			{field : 'sbsj',title : '申辩时间',sortable : false,width : 100,align : 'center',
				formatter : function(value, row, index) {
					var datetime1 = '';
					var datetime2 = '';
                  	if(row.m1!=null&&row.m1!='') {
                  		var datetime1=new Date(row.m1);
                  		datetime1 = datetime1.format('yyyy-MM-dd hh:mm:ss');
                  	}
                  	if(row.m2!=null&&row.m2!='') {
                  		var datetime2=new Date(row.m2);
                  		datetime2 = datetime2.format('yyyy-MM-dd hh:mm:ss');
                  	}
                  	return datetime1+" ~ "+datetime2;
              	}	 
			},
			{field : 'm4',title : '陈述申辩人',sortable : false,width : 50,align : 'center'},
			{field : 'm12',title : '承办人1',sortable : false,width : 50,align : 'center'},
			{field : 'm14',title : '承办人2',sortable : false,width : 50,align : 'center'}
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
    toolbar:'#aqzf_cssbbl_tb'
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
			url:ctx+"/xzcf/ybcf/cssbbl/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看陈述申辩笔录信息",ctx+"/xzcf/ybcf/cssbbl/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改陈述申辩笔录信息",ctx+"/xzcf/ybcf/cssbbl/update/"+row.id,"800px", "400px","");
}

//导出文书
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/xzcf/ybcf/cssbbl/export/sb/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}