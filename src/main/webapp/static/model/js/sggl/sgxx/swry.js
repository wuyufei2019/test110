var dg;
$(function(){
	dg=$('#fxgk_xjnr_dg').datagrid({    
	method: "post",
    url:ctx+'/sggl/sgxx/swrylist2/'+id1,
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'ID',
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
  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},  
  	        {field:'dangerlevel',title:'隐患级别',width:50,
  	        	formatter : function(value, row, index){
  	        		if(value=="1") return value='一般';
  	        		if(value=="2") return value='重大';
  	        	}
  	        },
  	        {field:'checktitle',title:'检查单元',width:80 },  
  	        {field:'content',title:'检查项目',width:150 },  
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#fxgk_xjnr_tb'
	});
	
});

//添加
function add(u) {
	openDialogView("绑定巡检内容",ctx+"/fxgk/fxxx/xjnrcreate/"+id1+","+qyid,"900px", "400px","");
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
			url:ctx+"/fxgk/fxxx/xjnrdelete/"+ids,
			success: function(data){
				parent.layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}