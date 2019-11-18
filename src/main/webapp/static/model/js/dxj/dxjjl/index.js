var dg;

$(function(){
	dg=$('#dxj_dxjjl_dg').datagrid({    
	method: "post",
    url:ctx+'/dxj/dxjjl/list', 
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
  			{field : 'qyname',title : '企业名称',sortable : false,width : 70},
  			{field : 'sbname',title : '设备名称',sortable : false,width : 60,align : 'center'},
			{field : 'bcname',title : '检查班次',sortable : false,width : 50,align : 'center'},
			{field : 'jcr',title : '检查人',sortable : false,width : 40,align : 'center'},
			{field : 'createtime',title : '检查时间',sortable : false,width : 50,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }
			},
			{field : 'checkresult',title : '检查结果',sortable : false,width : 30,align : 'center',
				formatter : function(value, row, index) {
              		if(value=='0'){
              			return '无隐患';
              		}else if(value=='1'){
              			return '有隐患';
              		}
	            }
			},
			{field : 'note',title : '检查备注',sortable : false,width : 70,align : 'center'},
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
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    rowStyler:function(index,row){
    	if (row.checkresult == '1'){
			return 'background-color:#f39999;color:#fff;';
		}
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#dxj_dxjjl_tb'
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
			url:ctx+"/dxj/dxjjl/delete/"+ids,
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
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看点巡检信息",ctx + "/dxj/dxjjl/view/" + row.id,"800px", "400px","");
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'qyname', coltext:'企业名称'},
			   		{colval:'sbname', coltext:'设备名称'},
			   		{colval:'bcname', coltext:'班次名称'},
			   		{colval:'jcr', coltext:'检查人'},
			   		{colval:'createtime', coltext:'检查时间'},
			   		{colval:'checkresult', coltext:'检查结果'},
			   		{colval:'note', coltext:'检查备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dxj/dxjjl/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}