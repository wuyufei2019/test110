var dg;

$(function(){
	dg=$('#yhpc_xjjl_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/xjjl2/list?iszdwxy='+iszdwxy,
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
  			{field : 'checkpointname',title : '检查点',sortable : true,width : 80},
			{field : 'checkorder',title : '所属班次',sortable : false,width : 60,align : 'center'},
			{field : 'createtime',title : '检查时间',sortable : true,width : 80,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }},
			{field : 'checkperson',title : '检查人',sortable : false,width : 60,align : 'center'},
			{field : 'checkresult',title : '检查结果',sortable : true,width : 50,align : 'center',
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
			{field : 'note',title : '问题备注',sortable : false,width : 90,align : 'center'},
			{field : 'checkphoto',title : '附件',sortable : false,width : 90,align : 'center',
				formatter:function(value){
					if(value){
						var urls=value.split(",");
						var html="";
						for(var index in urls){
							html+="<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 附件"+(parseInt(index)+1)+"</a><br>";
						}
						return html;
					}
					else return ;
				}
			}
    ]],
    onLoadSuccess: function(){
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	viewXjdnr();
    },
    rowStyler:function(index,row){
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_xjjl_tb'
	});
	
});

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
			url:ctx+"/yhpc/xjjl2/delete/"+ids,
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
function viewXjdnr(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看巡检记录",ctx+"/yhpc/xjjl2/view/"+row.id,"100%", "100%","");
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'jcdname', coltext:'检查点'},
			   		{colval:'name', coltext:'所属班次'},
			   		{colval:'createtime', coltext:'检查时间'},
			   		{colval:'uname', coltext:'检查人'},
			   		{colval:'lx', coltext:'检查结果'},
			   		{colval:'note', coltext:'问题备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/yhpc/xjjl2/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}