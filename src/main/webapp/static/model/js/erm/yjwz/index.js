var dg;
var cxtype;
var d;
var usertype;
$(function(){
	dg=$('#erm_yjwz_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yjwz/list', 
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
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
        {field:'qynm',title:'企业名称',width:100,align:'center'},        
        {field:'m1',title:'物资类别',width:100,align:'center'},    
        {field:'m2',title:'物资名称',width:100,align:'center'},
        {field:'m3',title:'型号',width:100,align:'center'},
        {field:'m4',title:'数量',width:100,align:'center'},
        {field:'m6',title:'自储数量',width:100,align:'center'},
        {field:'m7',title:'代储数量',width:100,align:'center'},
        {field:'m8',title:'储存单位',width:100,align:'center'},
        {field:'m9',title:'储存地址',width:200,align:'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
		if(usertype == '1'){
		}else{
    	    if(cxtype!=null&&cxtype!="2"){
    		   $(this).datagrid("hideColumn",['qynm']);
    	    }else{
    		   $(this).datagrid("showColumn",['qynm']);
    	    }
		}
		$(this).datagrid("autoMergeCells",['qynm']);
      },
    toolbar:'#erm_yjwz_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加应急物资信息",ctx+"/erm/yjwz/create/","800px", "400px","");
}
//查看地图
function openMap(){
	var cxtype=$('#cx_type_con').combobox('getValue');
	layer.open({
	    type: 2,  
	    area: ['700px', '400px'],
	    title: '查看物资位置信息',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/erm/yjwz/map/'+cxtype,
	});
	//openDialogView("查看应急队伍位置信息",ctx+"/erm/yjdw/map","900px", "700px",""); 
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
			url:ctx+"/erm/yjwz/delete/"+ids,
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
	
	openDialog("修改应急物资信息",ctx+"/erm/yjwz/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急物资信息",ctx+"/erm/yjwz/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	if(usertype != "1"&&usertype != "5"){
		cxtype = $('#cx_type_con').combobox('getValue');
	}
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	$('#cx_type_con').combobox('setValue', '1');
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//显示安监按钮
	$('#divper').show();
	$('#divper2').show();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'物资类别'},
			   		{colval:'m2', coltext:'名称'},
			   		{colval:'m3', coltext:'型号'},
			   		{colval:'m4', coltext:'数量'},
			   		{colval:'m5', coltext:'功能用途'},
			   		{colval:'m6', coltext:'自储数量'},
			   		{colval:'m7', coltext:'代储数量'},
			   		{colval:'m8', coltext:'储存单位'},
			   		{colval:'m9', coltext:'储存地址'},
			   		{colval:'m10', coltext:'联系人'},
			   		{colval:'m11', coltext:'联系电话'},
			   		{colval:'m13', coltext:'备注'},
			   		{colval:'m14', coltext:'经度'},
			   		{colval:'m15', coltext:'纬度'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yjwz/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}