var dg;
var d;
$(function() {	
	dg = $('#yhpc_ssp_dg').datagrid({
	method : "get",
	url : ctx + '/yhpc/ssp/list',
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
		{field :'qyname',title : '企业名称',sortable : true,width : 100},
		{field :'createtime',title : '发现时间',sortable : true,width : 40,align : 'center',
			formatter : function(value, row, index) {
              	if(value!=null&&value!='') {
              		var datetime=new Date(value);
              		return datetime.format('yyyy-MM-dd hh:mm:ss');
              	}
            }},
		{field :'dangerdesc',title : '隐患备注',sortable : false,width : 60,align : 'center'},
		{field :'username',title : '隐患发现人',sortable : false,width : 30,align : 'center'},
		{field :'dangerphoto',title : '隐患照片',sortable : false,width : 40,align : 'center',
        	formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
		{field :'sechandletime',title : '计划整改时间',sortable : true,width : 40,align : 'center',
			formatter : function(value, row, index) {
              	if(value!=null&&value!='') {
              		var datetime=new Date(value);
              		return datetime.format('yyyy-MM-dd hh:mm:ss');
              	}
            }
		},
		{field :'jhzgr',title : '计划整改人',sortable : false,width : 30,align : 'center'},
//		{field :'hiddentype',title : '隐患类型',sortable : false,width : 40,align : 'center'},
//		{field :'violationlevel',title : '违规级别',sortable : false,width : 40,align : 'center'},
		{field :'shr',title : '审核人',sortable : false,width : 30,align : 'center'},
		{field :'dangerlevel',title : '隐患等级',sortable : false,width : 40,align : 'center',
        	formatter : function(value, row, index) {
        		if(value=='1') {
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
		{field :'zgrname',title : '整改人',sortable : false,width : 30,align : 'center'},
		{field : 'zgsj',title : '整改时间',sortable : false,width : 40,align : 'center',
			formatter : function(value, row, index) {
              	if(value!=null&&value!='') {
              		var datetime=new Date(value);
              		return datetime.format('yyyy-MM-dd hh:mm:ss');
              	}
            }
		},
		{field :'zgzp',title : '整改照片',sortable : false,width : 40,align : 'center',
        	formatter : function(value, row, index) {
				var content="";
              	if(value!=null&&value!='') {
              		var arr1=value.split("||");
                	for (var i = 0; i < arr1.length-1; i++) {
                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
                	} 
                	return content;
              	}else{
              		return '/';
              	}
            }
		},
		{field : 'dangerstatus',title : '隐患状态',sortable : true,width : 40,align : 'center',
			formatter : function(value, row, index) {
				if(value == '1'){
          			return "<a style='margin:2px' class='btn btn-danger btn-xs'>整改待复查</a>"
          		}else if(value == '2'){
          			return "<a style='margin:2px' class='btn btn-danger btn-xs'>复查未通过</a>"
          		}else if(value == '3'){
          			return "<a  class='btn btn-success btn-xs'>整改完成</a>"
          		}else{
          			return "<a style='margin:2px' class='btn btn-danger btn-xs' >未整改</a>"
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
				$(this).datagrid("showColumn", [ 'qyname' ]);
				/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
			}
			
		},
		rowStyler:function(index,row){
	    	if (row.dangerstatus != '3'){
				return 'background-color:#f9ebed;';
			}
		},
		checkOnSelect : false,
	selectOnCheck : false,
	toolbar : '#yhpc_ssp_tb'
});

});

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看随手拍信息",ctx + "/yhpc/ssp/view/" + row.id,"900px", "90%","");

}

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if (row.dangerstatus == '3') {
		layer.msg("整改完成不可修改信息！",{time: 2000});
	} else {
		openDialog("修改随手拍信息",ctx+"/yhpc/ssp/update/"+row.id,"700px", "370px","");
	}
	
}

// 创建查询对象并查询
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
			url:ctx+"/yhpc/ssp/delete/"+ids,
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
