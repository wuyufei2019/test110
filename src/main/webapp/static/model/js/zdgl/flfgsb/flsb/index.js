var dg;
var d;
$(function(){
	dg=$('#zdgl_flsb_dg').datagrid({    
	method: "post",
    url:ctx+'/zdgl/flsb/list?qyid='+qyid,
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
        {field:'m1_1',title:'大类别',sortable:false,width:30,
        	formatter : function(value, row, index) {
	          	if(value=='1') {
	          		return '法律';  
	          	}else if(value=='2'){
	          		return '法规';  
	          	}else if(value=='3'){
	          		return '规章';  
	          	}else if(value=='4'){
	          		return '地方性法规';  
	          	}else if(value=='5'){
	          		return '政府文件';  
	          	}else if(value=='6'){
	          		return '标准规范';  
	          	}else if(value=='7'){
	          		return '其他';  
	          	}
          	} 
        },
        {field:'m1',title:'小类别',sortable:false,width:50},
        {field:'m2',title:'法律法规名称',sortable:false,width:90,align:'center',
        	formatter : function(value, row, index) {
	          	return "<a onclick='view2("+row.flfgid+")'>"+value+"</a>";
          	} 
        },
        {field:'m3',title:'颁布单位',sortable:false,width:60,align:'center'},
        {field:'m4',title:'文件编号',sortable:false,width:60,align:'center'},
        {field:'m5',title:'发布日期',sortable:false,width:40,align:'center',
	    	formatter : function(value, row, index) {
	          	if(value!==null&&value!='') {
	          		var datetime=new Date(value);
	          		 return datetime.format('yyyy-MM-dd');  
	          	}	
          	} 
        },
        {field:'m6',title:'实施日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'sytk',title:'适用条款',sortable:false,width:90,align:'center'},
        {field:'m13',title:'Gap',sortable:false,width:90,align:'center'},
        {field:'sbbm',title:'识别部门',sortable:false,width:60,align:'center'},
        {field:'sbr',title:'识别人',sortable:false,width:30,align:'center'},
        {field:'sbrq',title:'识别日期',sortable:false,width:40,align:'center',
        	formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'zt',title:'状态',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
        		var a = '';
        		if(role == '1'){
        			if(row.pzyj == '0'){
	            		a +="<font style='margin:2px' >批准未通过</font>";
	            	}else if(row.pzyj == '1'){
	            		a +="<font style='margin:2px' >批准通过</font>";
	            	}else if(row.shyj == '0'){
	            		a +="<font style='margin:2px' >审核未通过</font>";
	            	}else if(row.shyj == '1'){
	            		a +="<font style='margin:2px' >审核通过待批准</font>";
	            	}else{
	            		a +="<font style='margin:2px' >待审核</font>";
	            	}
        		}else if(role == '2'){
        			if(row.pzyj == '0'){
	            		a +="<font style='margin:2px'>批准未通过</font>";
	            	}else if(row.pzyj == '1'){
	            		a +="<font style='margin:2px'>批准通过</font>";
	            	}else if(row.shyj == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else if(row.shyj == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
	            	}
        		}else if(role == '3'){
        			if(row.pzyj == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.pzyj == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.shyj == '0'){
	            		a +="<font style='margin:2px'>审核未通过</font>";
	            	}else if(row.shyj == '1'){
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>审核通过待批准</a>";
	            	}else{
	            		a +="<font style='margin:2px'>待审核</font>";
	            	}
        		}else if(role == '4'){
        			if(row.pzyj == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsp("+row.id+")'>批准未通过</a>";
	            	}else if(row.pzyj == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsp("+row.id+")'>批准通过</a>";
	            	}else if(row.shyj == '1'){
	            		a +="<a style='margin:2px' class='btn btn-success btn-xs' onclick='addsh("+row.id+")'>审核通过</a><a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsp("+row.id+")'>待批准</a>";
	            	}else if(row.shyj == '0'){
	            		a +="<a style='margin:2px' class='btn btn-danger btn-xs' onclick='addsh("+row.id+")'>审核未通过</a>";
	            	}else{
	            		a +="<a style='margin:2px' class='btn btn-warning btn-xs' onclick='addsh("+row.id+")'>待审核</a>";
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
    toolbar:'#zdgl_flsb_tb'
	});
});

//审核
function addsh(id){
	openDialog("审核法律识别信息",ctx+"/zdgl/flsb/update/sh/"+id,"800px", "400px","");
}

//批准
function addsp(id){
	openDialog("批准法律识别信息",ctx+"/zdgl/flsb/update/pz/"+id,"800px", "400px","");
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
			url:ctx+"/zdgl/flsb/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看法律识别信息",ctx+"/zdgl/flsb/view/"+row.id,"800px", "400px","");
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
	openDialog("添加法律识别信息",ctx+"/zdgl/flsb/create/","800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.pzyj == '1'){
		layer.msg("批准通过，不得进行修改!",{time: 3000});
	}else if(row.shyj == '1' && row.pzyj == '0'){
		openDialog("修改法律识别信息",ctx+"/zdgl/flsb/update/xg/"+row.id,"800px", "400px","");
	}else if(row.shyj == '1'){
		layer.msg("审核通过，不得进行修改!",{time: 3000});
	}else{
		openDialog("修改法律识别信息",ctx+"/zdgl/flsb/update/xg/"+row.id,"800px", "400px","");
	}
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	                {colval:'m1_1', coltext:'大类别'},
			   		{colval:'lb', coltext:'小类别'},
			   		{colval:'flmc', coltext:'法律法规名称'},
			   		{colval:'bbdw', coltext:'颁布单位'},
			   		{colval:'wjbh', coltext:'文件编号'},
			   		{colval:'fbrq', coltext:'发布日期'},
			   		{colval:'ssrq', coltext:'实施日期'},
			   		{colval:'m1', coltext:'适用条款'},
			   		{colval:'m2', coltext:'适用部门'},
			   		{colval:'m13', coltext:'Gap(差异)'},
			   		{colval:'m3', coltext:'备注'},
			   		{colval:'sbr', coltext:'识别人'},
			   		{colval:'sbbm', coltext:'识别部门'},
			   		{colval:'m6', coltext:'识别日期'},
			   		{colval:'spr', coltext:'审核人'},
			   		{colval:'spyj', coltext:'审核意见'},
			   		{colval:'m9', coltext:'审核日期'},
			   		{colval:'pzr', coltext:'批准人'},
			   		{colval:'pzyj', coltext:'批准意见'},
			   		{colval:'m12', coltext:'批准日期'}
			   	   ];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/zdgl/flsb/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}

//在线查看文件
function view2(id){
	window.open(ctx+"/zdgl/flfg/view2/"+id, "安全法律法规");
}