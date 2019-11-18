var dg;
var cxtype;
var d;
var usertype;
$(function(){
	dg=$('#erm_yjzj_dg').datagrid({    
	method: "post",
    url:ctx+'/erm/yjzj/list', 
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
        {field:'m1',title:'姓名',width:100,align:'center'},    
        {field:'m2',title:'性别',width:50,align:'center',formatter: function(value,row,index){
																		if (row.m2=='1'){
																			return '男';
																		}else if (row.m2=='0'){
																			return '女';
																		}else {
																			return '';
																		}
																	}
		},
        {field:'m16',title:'应急专长',width:100,align:'center'},
        {field:'m14',title:'职称',width:100,align:'center'}
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
    toolbar:'#erm_yjzj_tb'
	});
});

//弹窗增加
function add(u) {
	openDialog("添加应急专家信息",ctx+"/erm/yjzj/create/","800px", "400px","");
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
			url:ctx+"/erm/yjzj/delete/"+ids,
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
	
	openDialog("修改应急专家信息",ctx+"/erm/yjzj/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看应急专家信息",ctx+"/erm/yjzj/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'姓名'},
			   		{colval:'m2', coltext:'性别'},
			   		{colval:'m3', coltext:'出生年月'},
			   		{colval:'m4', coltext:'身份证号码'},
			   		{colval:'m5', coltext:'政治面貌'},
			   		{colval:'m6', coltext:'地址'},
			   		{colval:'m7', coltext:'工作单位'},
			   		{colval:'m8', coltext:'毕业院校'},
			   		{colval:'m9', coltext:'最高学历'},
			   		{colval:'m10', coltext:'工作年限'},
			   		{colval:'m11', coltext:'联系电话'},
			   		{colval:'m12', coltext:'手机'},
			   		{colval:'m13', coltext:'职务'},
			   		{colval:'m14', coltext:'职称'},
			   		{colval:'m15', coltext:'专业'},
			   		{colval:'m16', coltext:'应急专长'},
			   		{colval:'m17', coltext:'专家类别'},
			   		{colval:'m18', coltext:'电子邮件'},
			   		{colval:'m19', coltext:'应对事故类型'},
			   		{colval:'m20', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/erm/yjzj/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}