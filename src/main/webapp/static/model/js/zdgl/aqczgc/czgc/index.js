var dg;
var d;
$(function(){
	dg=$('#zdgl_czgc_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/czgc/list?qyid='+qyid,
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
        {field:'m2',title:'规程编号',sortable:false,width:60},
        {field:'m1',title:'规程名称',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
	          	return "<a onclick='view2("+row.id+")'>"+value+"</a>";
          	} 
        },
        {field:'m3',title:'版本号',sortable:false,width:60,align:'center'},
        {field:'m4',title:'发布日期',sortable:false,width:60,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'bjbm',title:'编辑部门',sortable:false,width:100,align:'center'},
        //{field:'lrr',title:'录入人员',sortable:false,width:50,align:'center'},
        {field:'zt',title:'状态',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
        		var a = '';
        		if(role == '1'){
        			if(row.m13 == '0'){
	            		a +="<font style='margin:2px' >批准未通过</font>";
	            	}else if(row.m13 == '1'){
	            		a +="<font style='margin:2px' >批准通过</font>";
	            	}else if(row.m10 == '0'){
	            		a +="<font style='margin:2px' >审核未通过</font>";
	            	}else if(row.m10 == '1'){
	            		a +="<font style='margin:2px' >审核通过待批准</font>";
	            	}else{
	            		a +="<font style='margin:2px' >待审核</font>";
	            	}
        		}else if(role == '2'){
        			if(row.m13 == '0'){
	            		a +="<font style='margin:2px'>批准未通过</font>";
	            	}else if(row.m13 == '1'){
	            		a +="<font style='margin:2px'>批准通过</font>";
	            	}else if(row.m10 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else if(row.m10 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
        		}else if(role == '3'){
        			if(row.m13 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m13 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.m10 == '0'){
	            		a +="<font style='margin:2px'>审核未通过</font>";
	            	}else if(row.m10 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<font style='margin:2px'>待审核</font>";
	            	}
        		}else if(role == '4'){
        			if(row.m13 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.m13 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.m10 == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a><a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
	            	}else if(row.m10 == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
        		}
                if (xd) {
                    if (row.m10 || row.m13) {
                        a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addxd("+row.id+")'>修订</a>";
                        a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='xdhis("+row.id+")'>修订记录</a>";

                    }
                }
        		a +="<a style='margin:2px' class='btn btn-info btn-xs' onclick='addps("+row.id+")'>评审</a>";
            	return a;
        	} 
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#zdgl_czgc_tb'
	});
});

//审核
function addsh(id){
	openDialog("审核安全操作规程信息",ctx+"/zdgl/czgc/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准安全操作规程信息",ctx+"/zdgl/czgc/update/pz/"+id,"800px", "400px","");
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
			url:ctx+"/zdgl/czgc/delete/"+ids,
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
//弹窗修订
function addxd(id){
    openDialog("修订安全管理制度信息",ctx+"/zdgl/czgc/revise/"+id,"800px", "400px","");

}
//
function xdhis(id){
    openDialogView("修订安全管理制度信息",ctx+"/zdgl/czgc/hisindex/"+id,"1000px", "600px","");

}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看安全操作规程信息",ctx+"/zdgl/czgc/view/"+row.id,"800px", "400px","");
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
}

//弹窗增加
function add() {
	openDialog("添加安全操作规程信息",ctx+"/zdgl/czgc/create/","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m13 == '1'){
		layer.msg("批准通过，不得进行修改!",{time: 3000});
	}else if(row.m10 == '1' && row.m13 == '0'){
		openDialog("修改安全操作规程信息",ctx+"/zdgl/czgc/update/xg/"+row.id,"800px", "400px","");
	}else if(row.m10 == '1'){
		layer.msg("审核通过，不得进行修改!",{time: 3000});
	}else{
		openDialog("修改安全操作规程信息",ctx+"/zdgl/czgc/update/xg/"+row.id,"800px", "400px","");
	}
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m2', coltext:'规程编号'},
			   		{colval:'m1', coltext:'规程名称'},
			   		{colval:'m3', coltext:'版本号'},
			   		{colval:'m4', coltext:'发布日期'},
			   		{colval:'m5', coltext:'规程正文'},
			   		{colval:'bjbm', coltext:'编辑部门'},
			   		{colval:'sybm', coltext:'适用部门'},
			   		{colval:'spr', coltext:'审核人'},
			   		{colval:'spyj', coltext:'审核意见'},
			   		{colval:'m11', coltext:'审核日期'},
			   		{colval:'pzr', coltext:'批准人'},
			   		{colval:'pzyj', coltext:'批准意见'},
			   		{colval:'m14', coltext:'批准日期'}
			   	   ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/zdgl/czgc/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//添加评审
function addps(id) {
	openDialog("添加安全管理制度评审信息",ctx+"/zdgl/aqps/createps2/"+id,"800px", "400px","");
}

//在线查看文件
function view2(id){
	window.open(ctx+"/zdgl/czgc/view2/"+id, "安全操作规程");
}