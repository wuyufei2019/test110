var dg;
var dg2;
var d;
$(function(){
	getSbdxData(ctx+'/sbssgl/sbdx/list');
	getSbgxData(ctx+'/sbssgl/sbgx/list');
});

//设备大修计划
function getSbdxData(url){
	dg=$('#sbssgl_sbdx_dg').datagrid({    
		method: "post",
	    url: url, 
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
	        {field:'m7',title:'年度',sortable:false,width:70,align : 'center'},
	        {field:'deptname',title:'使用单位',sortable:false,width:70,align:'center'},
	        {field:'m1',title:'工作令号',sortable:false,width:70,align : 'center'},
	        {field:'m2',title:'设备编号',sortable:false,width:70,align : 'center'},
	        {field:'m3',title:'型号名称',sortable:false,width:70,align:'center'},
	        {field:'m4',title:'启用年月',sortable:false,width:70,align:'center'},
	        {field:'m6',title:'计划修理费（万元）',sortable:false,width:100,align:'center'},
	        {field:'m18',title:'计划修理时间',sortable:false,width:70,align:'center'},
	        {field:'m8',title:'修理类别',sortable:false,width:70,align:'center',
	        	formatter: function(value){
	    			if (value == '0') {
	    				return "大修";
	    			} else if (value == '1') {
	    				return "项修";
	    			}
	        	}
	        },
	        {field:'m16',title:'设备现状描述',sortable:false,width:100,align:'center'},
	        {field:'m21',title:'检维修负责人',sortable:false,width:70,align:'center'},
	        {field:'m19',title:'是否完成',sortable:false,width:70,align:'center',
	        	formatter: function(value) {
	        		if (value == '0') {
	        			return '未完成';
	        		} else if (value == '1') {
	        			return '已完成';
	        		}
	        	},
	        	styler: function(value) {
	        		if (value == '0') {
	        			return 'color: #ed5565';
	        		} else if (value == '1') {
	        			return 'color: #23c6c8';
	        		}
	        	}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	if(type == '1'){
				$(this).datagrid("hideColumn", [ 'qyname']);
				$(this).datagrid("hideColumn", [ 'deptname']);
				$(this).datagrid("autoMergeCells", [ 'm7']);
			}else{
				$(this).datagrid("autoMergeCells", [ 'qyname']);
				$(this).datagrid("autoMergeCells", [ 'm7']);
				$(this).datagrid("autoMergeCells", [ 'deptname']);
			}
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	        view();
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbdx_tb'
		});
	
}

//设备更新计划
function getSbgxData(url){
	dg2=$('#sbssgl_sbdx_gx_dg').datagrid({    
		method: "post",
	    url: url, 
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
	        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
	        {field:'m14',title:'年度',sortable:false,width:70,align : 'center'},
	        {field:'deptname',title:'使用单位',sortable:false,width:70,align:'center'},
	        {field:'m1',title:'工作令号',sortable:false,width:70,align : 'center'},
	        {field:'m2',title:'设备名称',sortable:false,width:70,align : 'center'},
	        {field:'m3',title:'规格型号',sortable:false,width:70,align:'center'},
	        {field:'m5',title:'单价（万元）',sortable:false,width:70,align:'center'},
	        {field:'m6',title:'数量（台）',sortable:false,width:70,align:'center'},
	        {field:'m7',title:'合计（万元）',sortable:false,width:70,align:'center'},
	        {field:'m8',title:'设备更新类别',sortable:false,width:70,align:'center',
	        	formatter: function(value){
	    			if (value == '0') {
	    				return "替换";
	    			} else if (value == '1') {
	    				return "新增";
	    			}
	        	}
	        },
	        {field:'m12',title:'是否完成',sortable:false,width:70,align:'center',
	        	formatter: function(value) {
	        		if (value == '0') {
	        			return '未完成';
	        		} else if (value == '1') {
	        			return '已完成';
	        		}
	        	},
	        	styler: function(value) {
	        		if (value == '0') {
	        			return 'color: #ed5565';
	        		} else if (value == '1') {
	        			return 'color: #23c6c8';
	        		}
	        	}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	if(type == '1'){
				$(this).datagrid("hideColumn", [ 'qyname']);
				$(this).datagrid("hideColumn", [ 'deptname']);
				$(this).datagrid("autoMergeCells", [ 'm14']);
			}else{
				$(this).datagrid("autoMergeCells", [ 'qyname']);
				$(this).datagrid("autoMergeCells", [ 'm14']);
				$(this).datagrid("autoMergeCells", [ 'deptname']);
			}
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	        view2();
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbdx_gx_tb'
		});
	
}

//弹窗增加设备更新计划信息
function add() {
	openDialog("添加设备大修项信息",ctx+"/sbssgl/sbdx/create?sbtype="+sbtype,"800px", "430px","");
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
	
	var ids = "";
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
			url:ctx+"/sbssgl/sbdx/delete/"+ids,
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
	openDialogView("查看设备大修项信息",ctx+"/sbssgl/sbdx/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备大修项信息",ctx+"/sbssgl/sbdx/update/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}

//导出
function fileexport(sbtype){
	var exportUrl;
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'RowNumber', coltext:'序号'},
	               	{colval:'m7', coltext:'年度'},
			   		{colval:'m1', coltext:'工作令号'},
			   		{colval:'m2', coltext:'设备编号'},
			   		{colval:'m3', coltext:'型号名称'},
			   		{colval:'m4', coltext:'启用年月'},
			   		{colval:'deptname', coltext:'使用单位'},
			   		{colval:'m6', coltext:'计划修理费（万元）'},
			   		{colval:'m18', coltext:'计划修理时间'},
			   		{colval:'m16', coltext:'设备现状描述'},
			   		{colval:'m8', coltext:'修理类别'},
			   		{colval:'m15', coltext:'检维修方案'},
			   		{colval:'m20', coltext:'技术验收质量要求'},
			   		{colval:'m21', coltext:'检维修负责人'},
			   		{colval:'m19', coltext:'是否完成'},
			   		{colval:'m9', coltext:'定人'},
			   		{colval:'m10', coltext:'定期'},
			   		{colval:'m11', coltext:'定点'},
			   		{colval:'m12', coltext:'定质'},
			   		{colval:'m13', coltext:'定量'},
			   		{colval:'m14', coltext:'验收报告'},
			   		{colval:'m22', coltext:'维修合同'},
		   	];
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sbssgl/sbdx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
	         $.ajax({
	        	 type: 'post',
	        	 url: ctx+'/sbssgl/sbdx/exportfj',
	        	 data: $("#searchFrom").serialize(),
	        	 success: function(data){
	        		 for (var i = 0; i < data.length; i++) {
	        			 var fileUrl = data[i].m3;
	        			 console.log(fileUrl)
	        			 var file = fileUrl.split("||")
	        			 window.open(file[0]);
	        		 }
	        	 }
	         });
		  },
	});
}

function ndbb(){
	openDialog("年度报表",ctx+"/sbssgl/sbdx/ndbb","80%", "100%","");
}

/****************************************************设备更新计划***************************************************************************/
//弹窗增加设备更新计划信息
function add2() {
	openDialog("添加设备更新计划信息",ctx+"/sbssgl/sbgx/create?sbtype="+sbtype,"800px", "430px","");
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
			url:ctx+"/sbssgl/sbgx/delete/"+ids,
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
	openDialogView("查看设备更新计划信息",ctx+"/sbssgl/sbgx/view/"+row.id,"800px", "400px","");
}

//弹窗修改
function upd2(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备更新计划信息",ctx+"/sbssgl/sbgx/update/"+row.id,"800px", "400px","");
}

//导出
function fileexport2(sbtype){
	var exportUrl;
	window.expara=$("#searchFrom2").serializeObject();
	window.exdata=[
	               	{colval:'RowNumber', coltext:'序号'},
	               	{colval:'m7', coltext:'年度'},
			   		{colval:'m1', coltext:'工作令号'},
			   		{colval:'m2', coltext:'设备名称'},
			   		{colval:'m3', coltext:'规格型号'},
			   		{colval:'deptname', coltext:'使用单位'},
			   		{colval:'m5', coltext:'单价（万元）'},
			   		{colval:'m6', coltext:'数量（台）'},
			   		{colval:'m7', coltext:'合计（万元）'},
			   		{colval:'m8', coltext:'设备更新类别'},
			   		{colval:'m9', coltext:'设备替换原因描述'},
			   		{colval:'m10', coltext:'设备新增原因描述'},
			   		{colval:'m11', coltext:'设备更新后作用描述'},
			   		{colval:'m12', coltext:'是否完成'},
			   		{colval:'m13', coltext:'验收报告'},
			   		{colval:'m15', coltext:'合同附件'}
		   	];
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/sbssgl/sbgx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
	         $.ajax({
	        	 type: 'post',
	        	 url: ctx+'/sbssgl/sbgx/exportfj',
	        	 data: $("#searchFrom2").serialize(),
	        	 success: function(data){
	        		 for (var i = 0; i < data.length; i++) {
	        			 var fileUrl = data[i].m3;
	        			 console.log(fileUrl)
	        			 var file = fileUrl.split("||")
	        			 window.open(file[0]);
	        		 }
	        	 }
	         });
		  },
	});
}

function ndbb2(){
	openDialog("年度报表",ctx+"/sbssgl/sbgx/ndbb","80%", "100%","");
}
