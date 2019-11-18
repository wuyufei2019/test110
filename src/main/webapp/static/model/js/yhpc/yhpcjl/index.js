var dg;

$(function(){
	dg=$('#yhpc_yhpcjl_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/yhpcjl/list?qyid='+qyid,
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
      			{field : 'qyname',title : '所属企业',sortable : true,width : 50,align : 'center'},
      			{field : 'xjdname',title : '巡检点名称',sortable : false,width : 50,align : 'center'},
      			{field : 'jcnr',title : '隐患内容',sortable : false,width : 90,align : 'center'},
      			{field : 'yhjb',title : '隐患级别',sortable : true,width : 20,align : 'center',
    				formatter : function(value, row, index) {
    	              	if(value=='1') {
    	              		return '一般';
    	              	}else{
    	              		return '重大';
    	              	}
    	            }
      			},
    			{field : 'yhfxr',title : '隐患发现人',sortable : false,width : 30,align : 'center'},
    			{field : 'createtime',title : '发现时间',sortable : true,width : 40,align : 'center',
    				formatter : function(value, row, index) {
    	              	if(value!=null&&value!='') {
    	              		var datetime=new Date(value);
    	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
    	              	}
    	            }
    			},
    			{field : 'zdzgr',title : '指定整改人',sortable : false,width : 40,align : 'center'},
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
                  			return "<a  class='btn btn-success btn-xs'>整改完成</a>"
                  		}else{
                  			return "<a style='margin:2px' class='btn btn-danger btn-xs' >未整改</a>"
                  		}
    	            }
    			},
    			{field : 'cz',title : '操作',sortable : true,width : 20,align : 'center',
    				formatter : function(value, row, index) {
    					return " <a class='btn btn-info btn-xs' onclick='viewjd("+row.id+")'>查看进度</a> ";
    	            }
    			}
    ]],
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#zgzt").combobox('setValue',cljd);
    		param.zgzt=cljd;
    	 }
    },
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			/*$(this).datagrid("autoMergeCells", [ 'qyname' ]);*/
		}
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    rowStyler:function(index,row){
    	if (row.dangerstatus != '3'){
			return 'background-color:#f9ebed;';
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_yhpcjl_tb'
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

//查看
function view() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患排查治理记录",ctx + "/yhpc/yhpcjl/view/" + row.id,"900px","525px","");

}

//查看进度
function viewjd(id) {
        openDialogView("查看隐患排查治理进度",ctx + "/yhpc/yhpcjl/viewjd/" + id,"800px","400px","");
}
// 弹窗修改
function upd() {
    var row = dg.datagrid('getSelected');
    if (row == null) {
        layer.msg("请选择一行记录！", {
            time : 1000
        });
        return;
    }
    if (row.dangerstatus != 0) {
        layer.msg("只能修改未整改记录！", {
            time : 1000
        });
        return;
    }
    openDialog("修改隐患排查治理", ctx + "/yhpc/yhpcjl/update/" + row.id, "900px","525px","");
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
			url:ctx+"/yhpc/yhpcjl/delete/"+ids,
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

//查看整改信息
function view2(id){	
	openDialogView("查看整改复查记录",ctx+"/wghgl/xjjlzg/view/"+id,"900px", "400px","");
}

/**************************************************网格隐患**********************************************************/
var dg2;
$(function(){
	dg2=$('#wghgl_xjjlzg_dg').datagrid({    
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
  			{field : 'wgdname',title : '巡检点名称',sortable : false,width : 70},
  			{field : 'jcnr',title : '隐患内容',sortable : false,width : 90,align : 'center'},
			{field : 'yhfxr',title : '隐患发现人',sortable : false,width : 30,align : 'center'},
			{field : 'createtime',title : '发现时间',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'dangerphoto',title : '隐患照片',sortable : false,width : 50,align : 'center',
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
			{field : 'zgsj',title : '整改时间',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'zgzp',title : '整改后照片',sortable : false,width : 50,align : 'center',
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
			{field : 'dangerstatus',title : '隐患状态',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
					if(value == '1'){
              			return "<a style='margin:2px' onclick='viewjl("+row.id + ")' class='btn btn-danger btn-xs'>企业整改完成</a>"
              		}else if(value == '2'){
              			return "<a style='margin:2px' onclick='viewjl("+row.id + ")' class='btn btn-danger btn-xs'>审核未通过</a>"
              		}else if(value == '3'){
              			return "<a  class='btn btn-success btn-xs' onclick='viewjl("+row.id + ")'>审核通过</a>"
              		}else{
              			return "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='viewjl("+row.id + ")'>未整改</a>"
              		}
	            }
			}
    ]],
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#wghgl_xjjlzg_dangerstatus").combobox('setValue',cljd);
    		param.wghgl_xjjlzg_dangerstatus=cljd;
    	 }
    },
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'wgdname']);
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	viewxq();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_xjjlzg_tb'
	});
});

//清空
function reset2(){
	$("#searchFrom2").form("clear");
	search2();
}

//查询
function search2(){
	var obj=$("#searchFrom2").serializeObject();
	dg2.datagrid('load',obj); 
}

//查看整改复查记录
function viewjl(id){
	openDialogView("查看整改复查记录",ctx+"/wghgl/xjjlzg/view/"+id,"900px", "400px","");
}

//查看隐患记录详情
function viewxq(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患记录详情",ctx+"/wghgl/xjjlzg/viewxq/"+row.id,"900px", "400px","");
}