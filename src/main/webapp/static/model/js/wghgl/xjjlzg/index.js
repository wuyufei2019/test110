var dg;
$(function(){
	dg=$('#wghgl_xjjlzg_dg').datagrid({    
	method: "post",
    url:ctx+'/wghgl/xjjlzg/list', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'wgname',title : '所属网格',sortable : true,width :30},
  			{field : 'wgdname',title : '巡检点名称',sortable : false,width : 50,align : 'center'},
  			/*{field : 'qyname',title : '所属企业',sortable : true,width : 50,align : 'center'},*/
  			{field : 'jcnr',title : '隐患内容',sortable : false,width : 90,align : 'center'},
			{field : 'yhfxr',title : '隐患发现人',sortable : false,width : 30,align : 'center'},
			{field : 'createtime',title : '发现时间',sortable : true,width : 40,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'dangerphoto',title : '隐患照片',sortable : false,width : 40,align : 'center',
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
			{field : 'zgrname',title : '整改人',sortable : false,width : 30,align : 'center'},
			{field : 'zgsj',title : '整改时间',sortable : false,width : 40,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'zgzp',title : '整改后照片',sortable : false,width : 40,align : 'center',
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
			{field : 'dangerstatus',title : '隐患状态',sortable : true,width : 30,align : 'center',
				formatter : function(value, row, index) {
					if(value == '1'){
              			return "<a style='margin:2px' class='btn btn-danger btn-xs'>整改待复查</a>"
              		}else if(value == '2'){
              			return "<a style='margin:2px' class='btn btn-danger btn-xs'>复查未通过</a>"
              		}else if(value == '3'){
              			return "<a  class='btn btn-success btn-xs' >整改完成</a>"
              		}else{
              			return "<a style='margin:2px' class='btn btn-danger btn-xs'>未整改</a>"
              		}
	            }
			}
    ]],
    onLoadSuccess: function(){
    	/*$(this).datagrid("autoMergeCells", [ 'wgname' ,'wgdname','qyname']);*/
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	viewxq();
    },
    rowStyler:function(index,row){
    	if (row.dangerstatus != '3'){
			return 'background-color:#f9ebed;';
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_xjjlzg_tb'
	});
	
});

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//查看整改复查记录
function view(id){
//	var row = dg.datagrid('getSelected');
//	if(row==null) {
//		layer.msg("请选择一行记录！",{time: 1000});
//		return;
//	}
	openDialogView("查看整改复查记录",ctx+"/wghgl/xjjlzg/view/"+id,"900px", "400px","");
}

//查看隐患记录详情
function viewxq(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患记录详情",ctx+"/wghgl/xjjlzg/viewxq/"+row.id,"900px", "90%","");
}

//删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/wghgl/xjjlzg/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});

}