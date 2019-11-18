var dg;
var d;
$(function(){
	dg=$('#erm_yljh_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yljh/list', 
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
              {field:'m1',title:'年度',width:50,align:'center'},    
              {field:'m2',title:'层级',width:50,align:'center'},
              {field:'m3',title:'部门名称',width:100,align:'center'},
              {field:'m4',title:'演练主题',width:100,align:'center'},
              {field:'m5',title:'参演部门',width:100,align:'center'},
              {field:'m6',title:'执行人',width:100,align:'center'},
              {field:'m7',title:'完成情况',width:100,align:'center'},
              {field:'m8',title:'附件浏览',width:100,align:'center',
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
              },
              {field:'id1',title:'操作',width:100,align:'center',
					formatter : function(value, row, index) {
						if((value==null)||(value==0)){
							return "<a  class='btn btn-info btn-xs' style='margin-right:5px' onclick='addjl("
									+ row.id + ")'>添加演练记录</a>";
						}else{
							return "<a  class='btn btn-success btn-xs' style='margin-right:5px' onclick='viewjl("
							+ row.id1 + ")'>查看演练记录</a>";
						}
					}
              },
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
		if(type=="1"){
		 	$(this).datagrid("hideColumn",['qynm']);
		}else{
		 	$(this).datagrid("showColumn",['qynm']);
		 	$(this).datagrid("autoMergeCells",['qynm']);
		}
      },
    toolbar:'#erm_yljh_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加演练计划信息",ctx+"/erm/yljh/create/","800px", "400px","");
}

//弹窗增加演练记录
function addjl(jhid) {
	openDialog("添加演练记录信息",ctx+"/erm/yljl/create/"+jhid,"800px", "400px","");
}

//弹窗修改
function viewjl(id1){
	openDialogView("查看演练计划信息",ctx+"/erm/yljl/view/"+id1,"800px", "400px","");
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
			url:ctx+"/erm/yljh/delete/"+ids,
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
	
	openDialog("修改演练计划信息",ctx+"/erm/yljh/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看演练计划信息",ctx+"/erm/yljh/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$("#erm_yljh_cs_m2").combobox("setValue",$("#erm_yljh_cs_m2").combobox("getText"));
	$("#erm_yljh_cs_m3").combobox("setValue",$("#erm_yljh_cs_m3").combobox("getText"));
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'场所名称'},
			   		{colval:'m2', coltext:'场所类型'},
			   		{colval:'m3', coltext:'详细地址'},
			   		{colval:'m4', coltext:'可容纳人数'},
			   		{colval:'m5', coltext:'联系人'},
			   		{colval:'m6', coltext:'联系人电话'},
			   		{colval:'m7', coltext:'负责人'},
			   		{colval:'m8', coltext:'负责人电话'},
			   		{colval:'m9', coltext:'功能描述'},
			   		{colval:'m11', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yljh/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}