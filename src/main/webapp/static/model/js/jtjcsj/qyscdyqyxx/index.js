var dg;
var d;
var usertype;
$(function(){
	dg=$('#jtjcsj_qyscdyqyxx_dg').datagrid({    
	method: "post",
    url:ctx+'/jtjcsj/qyscdyqyxx/list', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100},
        {field:'companycode',title:'企业编码',sortable:false,width:100},
        {field:'hazardname',title:'重大危险源名称',sortable:false,width:100,align:'center'},
        {field:'hazardcode',title:'重大危险源编码',sortable:false,width:100,align:'center'},
        {field:'prodcelltype',title:'生产区域类型',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index){
        		if(value=='HEV01'){
        			return '罐区';
        		}else if(value=='HEV02'){
        			return '生产装置区';
        		}else if(value=='HEV03'){
        			return '库区';
        		}
        	}
        },
        {field:'prodcellname',title:'生产单元区域名称',sortable:false,width:100,align:'center'},
        {field:'isprotect',title:'有无防护堤',sortable:false,width:100,align:'center',
    	    formatter : function(value, row, index) {
            	if(value=='0'){
            		return '否';
            	}else if(value=='1'){
            		return '是';
            	}
          	}
        },
		{field:'prodarea',title:'面积',sortable:false,width:100,align:'center'},
		{field:'iprotectarea',title:'防护堤面积  ',sortable:true,width:100,align:'center'},
		{field:'safedutyername',title:'安全负责人姓名',sortable:false,width:100,align:'center'},
		{field:'linkmode',title:'联系方式',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess:function(){
		if(usertype=="1"){
		 	$(this).datagrid("hideColumn",['qyname']);
		}else{
		 	$(this).datagrid("showColumn",['qyname']);
		 	$(this).datagrid("autoMergeCells",['qyname']);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#jtjcsj_qyscdyqyxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加企业生产单元区域信息",ctx+"/jtjcsj/qyscdyqyxx/create/","800px", "500px","");
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
			url:ctx+"/jtjcsj/qyscdyqyxx/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改企业生产单元区域信息",ctx+"/jtjcsj/qyscdyqyxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看企业生产单元区域信息",ctx+"/jtjcsj/qyscdyqyxx/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'单元名称'},
			   		{colval:'m2', coltext:'固定资产总值'},
			   		{colval:'m3', coltext:'经度'},
			   		{colval:'m4', coltext:'纬度'},
			   		{colval:'m5', coltext:'编号'},
			   		{colval:'m6', coltext:'占地面积(㎡)'},
					{colval:'m7', coltext:'当班人数'},
					{colval:'m8', coltext:'单元内危险化学品'},
					{colval:'m9', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/sccs/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}