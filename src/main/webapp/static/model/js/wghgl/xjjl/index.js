var dg;
$(function(){
	dg=$('#wghgl_xjjl_dg').datagrid({    
	method: "post",
    url:ctx+'/wghgl/xjjl/list', 
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
  			{field : 'wgdname',title : '巡检点名称',sortable : false,width : 90},
  			{field : 'wgname',title : '所属网格',sortable : true,width :50,align : 'center'},
  			{field : 'qyname',title : '所属企业',sortable : true,width : 90,align : 'center'},
  			{field : 'uname',title : '巡检网格员',sortable : false,width : 50,align : 'center'},
			{field : 'bcname',title : '所属班次',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
	              	if(value==null||value=='') {
	              		return '该班次已被删除';
	              	}else{
	              		return value;
	              	}
	            }  				
			},
			{field : 'createtime',title : '检查时间',sortable : true,width : 60,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'checkresult',title : '巡检结果',sortable : true,width : 40,align : 'center',
				formatter : function(value, row, index) {
              		if(value=='0'){
              			return '无隐患';
              		}else if(value=='1'){
              			return '有隐患';
              		}
	            },styler: function(value,row,index){
					if (value == '1'){
						return 'color:red;';
					}
				} 
			},
			{field : 'checkphoto',title : '现场照片',sortable : false,width : 60,align : 'center',
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
			}
    ]],
    onLoadSuccess: function(){
    	//$(this).datagrid("autoMergeCells", [ 'wgname' ]);
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    rowStyler:function(index,row){
    	if (row.checkresult == '1'){
			return 'background-color:#f9ebed;';
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#wghgl_xjjl_tb'
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

//查看巡检点内容
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看巡检记录",ctx+"/wghgl/xjjl/view/"+row.id,"100%", "100%","");
}

// 删除
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
			url : ctx + "/wghgl/xjjl/delete/" + ids,
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