var dg;
var d;
$(function(){
	dg=$('#erm_yljl_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yljl/list', 
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
              {field:'m1',title:'演练时间',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd');  
            		  }
            	  }  
              },    
              {field:'m2',title:'演练地点',width:120,align:'center'},
              {field:'m3',title:'总指挥',width:100,align:'center'},
              {field:'m4',title:'参演部门',width:90,align:'center'},
              {field:'m5',title:'演练名称',width:100,align:'center'},
              {field:'m7',title:'效果评价',width:100,align:'center'},
              {field:'m8',title:'评审人员',width:100,align:'center'},
              {field:'m11',title:'附件浏览',width:80,align:'center',
              	formatter : function(value, row, index){
              		var content="";
              		if(value!=null&&value!='') {
              			var arr1=value.split(",");
              			for (var i = 0; i < arr1.length; i++) {
              				var url=arr1[i];
              				var arr2=url.split("||");
                  			content=content+'<a href="'+arr2[0]+'" target="_blank">'+arr2[1]+'</a><br>'; 
              			} 
                  		return content;
              		}else{
              			return '/';
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
		if(type == '2' || usertype != '1'){
			$(this).datagrid("showColumn",['qynm']);
		 	$(this).datagrid("autoMergeCells",['qynm']);
		}else{
			$(this).datagrid("hideColumn",['qynm']);
		}
      },
    toolbar:'#erm_yljl_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加演练计划信息",ctx+"/erm/yljl/create/","800px", "400px","");
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
			url:ctx+"/erm/yljl/delete/"+ids,
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
	
	openDialog("修改演练计划信息",ctx+"/erm/yljl/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看演练计划信息",ctx+"/erm/yljl/view/"+row.id,"800px", "400px","");
	
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

//导出处理措施word
function exportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/erm/yljl/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
		{colval:'m1', coltext:'演练时间'},
		{colval:'m2', coltext:'演练地点'},
		{colval:'m3', coltext:'总指挥'},
		{colval:'m4', coltext:'参演部门'},
		{colval:'m5', coltext:'演练名称'},
		{colval:'m6', coltext:'演练内容'},
		{colval:'m7', coltext:'效果评价'},
		{colval:'m9', coltext:'评审时间'},
		{colval:'m8', coltext:'评审人员'},
		{colval:'m10', coltext:'存在问题'}
	];
	layer.open({
		type: 2,
		area: ['350px', '350px'],
		title: '导出列选择',
		maxmin: true,
		shift: 1,
		content: ctx+'/erm/yljl/colindex',
		btn: ['导出'],
		yes: function(index, layero){
			var body = layer.getChildFrame('body', index);
			var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			var inputForm = body.find('#excel_mainform');
			iframeWin.contentWindow.doExport();
		},
	});

}