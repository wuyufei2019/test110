var dg;
var d;
//添加危险废物特性
function add() {
	openDialog("添加危险废物特性",  ctx + "/hjbh/wxfwgl/create","850px", "400px","");
}
//添加外单位信息(和form.jsp中的方法是一样的)
function addOtherDw() {
	openDialog("添加外单位信息", ctx + "/hjbh/otherDw/create/", "800px", "250px", "");
}
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看危险废物特性",ctx+"/hjbh/wxfwgl/view/"+row.id,"800px", "450px","");
}

//查看外单位信息
function viewotherdw(id) {
	riskid=id;
	layer.open({
		type: 1,  
		area: ['800px', '400px'],
		title:'外单位信息列表',
		content: $("#select_otherdw"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	    d =$('#otherdw').datagrid({
	    	    	url :ctx+"/hjbh/otherDw/list/" + id,
	    	    	fitColumns : true,
					border : true,
					striped : true,
					rownumbers : true,
					nowrap : false,
					idField : 'id',
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [ {
						field : 'name',
						title : '单位名称',
						sortable : false,
						width : 100
					}, {
						field : 'location',
						title : '所在地',
						sortable : false,
						width : 100
					}, {
						field : 'permit_num',
						title : '危险废物经营许可证',
						sortable : false,
						width : 80
					}, {
						field : 'description',
						title : '方法描述',
						sortable : false,
						width : 100
					}, {
						field : 'contact_name',
						title : '联系人',
						sortable : false,
						width : 100
					}, {
						field : 'contact_phone',
						title : '联系方式',
						sortable : false,
						width : 100
					}, {
						field : 'operation',
						title : '操作',
						sortable : false,
						width : 50,
						formatter : function(value, row) {
							return "<a class='btn btn-warning btn-xs' onclick='update(" + JSON.stringify(row) + ")'>修改</a>"
							+ "<a class='btn btn-danger btn-xs' onclick='deleterow(" + row.id + ")'>删除</a>";
						}
					}

					] ],
					onLoadSuccess : function() {
					},
					checkOnSelect : false,
					selectOnCheck : false
	    	    });
	      },
		cancel: function(index){ 
		}
	}); 
}
//跳转到修改外单位信息页面
function update(row) {
	openDialog("修改外单位信息",ctx + "/hjbh/otherDw/odwupdate/"+row.id ,"800px", "250px","");
}

//跳转到修改危险废物特性页面
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改危险废物特性",ctx+"/hjbh/wxfwgl/update/"+row.id,"800px", "450px","");
	
}

//删除危险废物特性记录
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
			url:ctx+"/hjbh/wxfwgl/delete/"+ids,
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
//删除外单位信息
function deleterow(id) {
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/hjbh/otherDw/delete/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				d.datagrid('reload');
				d.datagrid('clearChecked');
				d.datagrid('clearSelections');
			}
		});
	});
}

//创建查询对象并查询
function search(){
	var obj=$("#searchForm").serializeObject();
	dg.datagrid('load',obj);
}
//刷新页面
function reset(){
	$("#searchForm").form("clear");
	var obj=$("#searchForm").serializeObject();
	dg.datagrid('load',obj); 
}
