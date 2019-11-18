var dg;
var d;
$(function(){
	dg=$('#bis_occupillexam_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/occupillexam/ajlist?qyid='+qyid,
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
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
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'M1',title:'体检日期',sortable:true,width:50,align:'center',
        	formatter : function(value,row,index){
        		if(value!=null){
                    var datetime = new Date(value);  
                     return datetime.format('yyyy-MM-dd');    }  
              }},   
        {field:'M2',title:'体检医院',sortable:false,width:100},
        {field:'M3',title:'体检人数',sortable:true,width:40,align:'center'},
        {field:'M4',title:'体检结论',sortable:false,width:100,
        	formatter:function(value,row,index){
        	if(value != null){
				if(value.length>24){
					value=value.substring(0,24)+"...";
				}
        	}
    		return value;
    	}},
        {field:'M5',title:'附件',sortable:false,width:50,align:'center',
        	formatter:function(val,row,index){
        		if(val!=null&&val!=""){
        			var t="||";
        			val=val.split(t);
        			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';  
        		}else{
        			return '--';
        		}
	        	
        	}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_occupillexam_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加职业病体检信息",ctx+"/bis/occupillexam/create/","800px", "400px","");
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
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/occupillexam/delete/"+ids,
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
	
	openDialog("修改职业病体检信息",ctx+"/bis/occupillexam/update/"+row.ID,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看职业病体检信息",ctx+"/bis/occupillexam/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$('#bis_occupillexam_cx_m2').combobox('setValue',$('#bis_occupillexam_cx_m2').combobox('getText'));
	$('#bis_occupillexam_cx_qyname').combobox('setValue',$('#bis_occupillexam_cx_qyname').combobox('getText'));
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	$('#bis_occupillexam_cx_m2').combobox('setValue',$('#bis_occupillexam_cx_m2').combobox('getText'));
	$('#bis_occupillexam_cx_qyname').combobox('setValue',$('#bis_occupillexam_cx_qyname').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	           		{colval:'qynm', coltext:'企业名称'},
			   		{colval:'m1', coltext:'体检日期'},
			   		{colval:'m2', coltext:'体检医院'},
			   		{colval:'m3', coltext:'体检人数'},
			   		{colval:'m4', coltext:'体检结论'},
			   		{colval:'m6', coltext:'体检类型'},
			   		{colval:'m7', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/occupillexam/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}