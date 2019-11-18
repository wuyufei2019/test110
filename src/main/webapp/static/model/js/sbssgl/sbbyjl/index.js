var dg;
var dg2;
var d;
$(function(){
	getFirData();
	getSecData();
});

//设备一级保养计划
function getFirData(){
	dg=$('#sbssgl_sbbyjl_fir_dg').datagrid({    
		method: "post",
		url: ctx+'/sbssgl/sbbyjl/firlist?sbtype='+ sbtype, 
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
	        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
	        {field:'qyname',title:'所属企业',sortable:false,width:100,align : 'center'},
	        {field:'type',title:'类别',sortable:false,width:80,align : 'center',
	        	formatter: function(value){
	        		if (value == '0') {
	        			return '一级';
	        		}
	        	}
	        },
	        {field:'year',title:'年度',sortable:false,width:80,align : 'center'},
	        {field:'month',title:'月度',sortable:false,width:80,align : 'center',
	        	formatter: function(value){
	        		return value + "月";
	        	}
	        },
	        {field:'deptname',title:'使用单位',sortable:false,width:100,align:'center'},
	        {field:'bztime',title:'编制日期',sortable:false,width:100,align : 'center',
	        	formatter: function(value){
	        		if (value) {
	        			return new Date(value).format("yyyy-MM-dd");
	        		}
	        	}
	        },
	        {field:'bzrname',title:'编制人',sortable:false,width:80,align : 'center'},
	        {field:'fj',title:'操作',sortable:false,width:70,align:'center',
	        	formatter : function(value, row, index) {
	        		/*if (role == '1') {
	        			if (value == '' || value == null) {
		        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj("+row.id+")>上传附件</a> " +
		        					"<a class='btn btn-primary btn-xs' onclick='wcqk("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
		        		} else {
		        			var fileurl = value.split("||");
		        			return "<a class='btn btn-info btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a> " +
		        					"<a class='btn btn-primary btn-xs' onclick='wcqk("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
		        		}
	        		} else {*/
	        			return "<a class='btn btn-primary btn-xs' onclick='wcqk("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
	        		/*}*/
	        	} 
	        }
	    ]],
	    onLoadSuccess: function(){
	    	if(type == '1'){
				$(this).datagrid("hideColumn", [ 'qyname']);
				$(this).datagrid("hideColumn", [ 'deptname']);
			}else{
				$(this).datagrid("autoMergeCells", [ 'qyname']);
			}
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	        view();
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbbyjl_fir_tb'
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
			url:ctx+"/sbssgl/sbbyjl/delete/"+ids,
			data: {"type": "0"},
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
	openDialogView("查看设备一级保养信息",ctx+"/sbssgl/sbbytaskfir/view/"+row.id,"100%", "100%","");
}

function wcqk(id, flag){
	openDialogView("查看设备一级保养信息",ctx+"/sbssgl/sbbytaskfir/view/"+id+"?flag="+flag,"100%", "100%","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	if (role == '1') {
		openDialog("修改设备一级保养信息",ctx+"/sbssgl/sbbytaskfir/update/"+row.id,"100%", "100%","");
	}
	if (uploadrole == '1') {
		openDialogView("修改设备一级保养信息",ctx+"/sbssgl/sbbytaskfir/update/"+row.id,"100%", "100%","");
	}
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

//上传附件
function uploadfj(id){
	var type = 0;
	openDialog("上传附件",ctx+"/sbssgl/sbbyjl/uploadindex/"+id+"/"+type,"400px", "250px","");
}

//导出计划
function fileexport(){
	if ($("#yearfir").combobox('getValue') == '') {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	if ($("#month").combobox('getValue') == '') {
		layer.msg('请选择月度', {time: 3000});
		return;
	}
	var data=$("#searchFrom").serialize();
	window.open(ctx+"/sbssgl/sbbytaskfir/export?"+data);
}

//导出记录
function fileexportjl(){
	if ($("#yearfir").combobox('getValue') == '') {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	var data=$("#searchFrom").serialize();
	window.open(ctx+"/sbssgl/sbbytaskfir/exportjl?"+data);
}

/****************************************************设备二级保养计划***************************************************************************/

//设备二级保养计划
function getSecData(){
	dg2=$('#sbssgl_sbbyjl_sec_dg').datagrid({    
		method: "post",
	    url: ctx+'/sbssgl/sbbyjl/seclist?sbtype='+ sbtype, 
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
			        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
			        {field:'qyname',title:'所属企业',sortable:false,width:100,align : 'center'},
			        {field:'type',title:'类别',sortable:false,width:80,align : 'center',
			        	formatter: function(value){
			        		if (value == '1') {
			        			return '二级';
			        		}
			        	}
			        },
			        {field:'year',title:'年度',sortable:false,width:80,align : 'center'},
			        {field:'deptname',title:'使用单位',sortable:false,width:100,align:'center'},
			        {field:'bztime',title:'编制日期',sortable:false,width:100,align : 'center',
			        	formatter: function(value){
			        		if (value) {
			        			return new Date(value).format("yyyy-MM-dd");
			        		}
			        	}
			        },
			        {field:'bzrname',title:'编制人',sortable:false,width:80,align : 'center'},
			        {field:'fj',title:'操作',sortable:false,width:70,align:'center',
			        	formatter : function(value, row, index) {
			        		/*if (value == '' || value == null) {
			        			return "<a class='btn btn-warning btn-xs' onclick=uploadfj2("+row.id+")>上传附件</a> " +
			        				"<a class='btn btn-primary btn-xs' onclick='wcqk2("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
			        		} else {
			        			var fileurl = value.split("||");
			        			return "<a class='btn btn-info btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>"
			        				"<a class='btn btn-primary btn-xs' onclick='wcqk2("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
			        		}*/
			        		return "<a class='btn btn-primary btn-xs' onclick='wcqk2("+row.id+",1)' style='margin-left: 5px;'> 完成情况</a>";
			        	} 
			        }
			    ]],
			    onLoadSuccess: function(){
			    	if(type == '1'){
						$(this).datagrid("hideColumn", [ 'qyname']);
						$(this).datagrid("hideColumn", [ 'deptname']);
					}else{
						$(this).datagrid("autoMergeCells", [ 'qyname']);
					}
			    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	        view2();
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbbyjl_sec_tb'
		});
	
}

//创建查询对象并查询
function search2(){
	var obj=$("#searchFrom2").serializeObject();
	dg2.datagrid('load',obj); 
}

function reset2(){
	$("#searchFrom2").form("clear");
	search2();
}

//删除
function del2(){
	var row = dg2.datagrid('getChecked');
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
			url:ctx+"/sbssgl/sbbyjl/delete/"+ids,
			data: {"type": "1"},
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg2.datagrid('reload');
				dg2.datagrid('clearChecked');
				dg2.datagrid('clearSelections');
			}
		});
	});
 
}

//查看
function view2(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备二级保养计划信息",ctx+"/sbssgl/sbbytasksec/view/"+ row.id,"100%", "100%","");
}

function wcqk2(id, flag){
	openDialogView("查看设备二级保养计划信息",ctx+"/sbssgl/sbbytasksec/view/"+id+"?flag="+flag,"100%", "100%","");
}

//弹窗修改
function upd2(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if (role == '1') {
		openDialog("修改设备二级保养计划信息",ctx+"/sbssgl/sbbytasksec/update/"+ row.id,"100%", "100%","");
	}
	if (uploadrole == '1') {
		openDialogView("修改设备二级保养计划信息",ctx+"/sbssgl/sbbytasksec/update/"+ row.id,"100%", "100%","");
	}
}

//上传附件
function uploadfj2(id){
	var type = 1;
	openDialog("上传附件",ctx+"/sbssgl/sbbyjl/uploadindex/"+id+"/"+type,"400px", "250px","");
}

//导出
function fileexport2(){
	if ($("#yearsec").combobox('getValue') == '') {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	var data=$("#searchFrom2").serialize();
	window.open(ctx+"/sbssgl/sbbytasksec/export?"+data);
}

//导出记录
function fileexportjl2(){
	if ($("#yearsec").combobox('getValue') == '') {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	var data=$("#searchFrom2").serialize();
	window.open(ctx+"/sbssgl/sbbytasksec/exportjl?"+data);
}


