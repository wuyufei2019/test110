var dg;
var d;
$(function() {	
	dg = $('#yhpc_sspsh_dg').datagrid({
		method : "get",
		url : ctx + '/yhpc/sspsh/list',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped : true,
		pagination : true,
		rownumbers : true,
		nowrap : false,
		pageNumber : 1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize : 5,
		singleSelect : true,
		striped : true,
		columns : [ [
			{field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
			{field :'qyname',title : '企业名称',sortable : false,width : 60},
			{field :'createtime',title : '发现时间',sortable : true,width : 40,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field :'dangerdesc',title : '隐患备注',sortable : false,width : 60,align : 'center'},
			{field :'yhfxr',title : '隐患发现人',sortable : false,width : 30,align : 'center'},
			{field :'dangerphoto',title : '隐患照片',sortable : false,width : 40,align : 'center',
	        	formatter : function(value, row, index) {
					var content="";
	              	if(value!=null&&value!='') {
	              		var arr1=value.split("||");
	                	for (var i = 0; i < arr1.length-1; i++) {
	                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:80px;max-height:50px; />';
	                	} 
	                	return content;
	              	}else{
	              		return '/';
	              	}
	            }
			},
			{field :'jhzgr',title : '计划整改人',sortable : false,width : 40,align : 'center'},
			{field :'sechandletime',title : '计划整改时间',sortable : true,width : 40,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field :'shr',title : '审核人',sortable : false,width : 30,align : 'center'},
			{field :'dangerlevel',title : '隐患等级',sortable : true,width : 30,align : 'center',
				formatter : function(value, row, index) {
					if(value=='0') {
	              		return '无隐患';
	              	}else if(value=='1') {
	              		return '一级';
	              	}else if(value=='2'){
	              		return '二级';
	              	}else if(value=='3'){
	              		return '三级';
	              	}else if(value=='4'){
	              		return '四级';
	              	}
	            }
			},
			{field :'approvestatue',title : '操作',sortable : false,width : 20,align : 'center',
				formatter : function(value, row, index) {
	              	if(value=='0') {
	              		if(sh == '1'){
	              			return "<a style='margin:3px;' class='btn btn-info btn-xs' onclick='addsh("+row.id+")'>审核</a>";
	              		}else{
	              			return '待审核';
	              		}
	              	}else if(value=='1'){
	              		return '已审核';
	              	}
	            }
			}
	        ]],
			onDblClickRow : function(rowdata, rowindex, rowDomElement) {
				view();
			},
			onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
				if(type == '1'){
					$(this).datagrid("hideColumn", [ 'qyname' ]);
				}else{
			    	$(this).datagrid("autoMergeCells", [ 'qyname' ]);
				}
			},
		checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#yhpc_sspsh_tb'
	});

});

//审核
function addsh(id){
	openDialog("随手拍审核信息",ctx+"/yhpc/sspsh/sh/"+id,"800px", "400px","");
}

//添加
function add(){
	openDialog("添加随手拍审核信息",ctx+"/yhpc/sspsh/create","800px", "400px","");
}

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.approvestatue == '1'){
		layer.msg("该随手拍已审核，不得修改！",{time: 1000});
		return;
	}
	openDialog("修改随手拍审核信息",ctx+"/yhpc/sspsh/update/"+row.id,"800px", "400px","");
}

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
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
			url:ctx+"/yhpc/sspsh/delete/"+ids,
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

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	
	openDialogView("查看随手拍审核信息",ctx + "/yhpc/sspsh/view/" + row.id,"900px", "450px","");

}
