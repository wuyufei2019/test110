var dg;
var cxtype;
var d;
var usertype;
$(function(){
	dg=$('#erm_yjzb_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yjzb/list', 
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
        {field:'m2',title:'装备名称',width:100,align:'center'},
        {field:'m3',title:'规格型号',width:100,align:'center'},
        {field:'m4',title:'数量',width:100,align:'center'},
        {field:'m6',title:'自储数量',width:100,align:'center'},
        {field:'m7',title:'代储数量',width:100,align:'center'},
        {field:'m10',title:'联系人',width:100,align:'center'},
        {field:'m11',title:'应急电话',width:100,align:'center'},
        {field:'m9',title:'地址',width:200,align:'center'}
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
    toolbar:'#erm_yjzb_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加应急装备信息",ctx+"/erm/yjzb/create/","800px", "400px","");
}
//查看地图
function openMap(){
	var cxtype=$('#cx_type_con').combobox('getValue');
	layer.open({
	    type: 2,  
	    area: ['700px', '400px'],
	    title: '查看应急装备位置信息',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/erm/yjzb/map/'+cxtype,
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
			url:ctx+"/erm/yjzb/delete/"+ids,
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
	
	openDialog("修改应急装备信息",ctx+"/erm/yjzb/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急装备信息",ctx+"/erm/yjzb/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'M1', coltext:'类别'},
			   		{colval:'M2', coltext:'名称'},
			   		{colval:'M3', coltext:'规格型号'},
			   		{colval:'M4', coltext:'数量'},
			   		{colval:'M5', coltext:'功能用途'},
			   		{colval:'M6', coltext:'自储数量'},
			   		{colval:'M7', coltext:'代储数量'},
			   		{colval:'M8', coltext:'储存单位'},
			   		{colval:'M9', coltext:'储存地址'},
			   		{colval:'M10', coltext:'主要负责人'},
			   		{colval:'M11', coltext:'应急电话'},
			   		{colval:'M13', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yjzb/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}