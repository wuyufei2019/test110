var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/hazard/ajlist', 
    loadMsg :'正在加载',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:0,
	singleSelect:true,
	checkOnSelect:false,
    columns:[[    
              {field:'ID',title:'ID',checkbox:true,width:50,align:'center'},
              {field:'qyid',title:'qyid',hidden:true},    
              {field:'qyname',title:'企业名称',sortable:false,width:100},    
              {field:'M6',title:'联系人',sortable:false,width:30,align:'center'},
              {field:'M7',title:'联系人电话',sortable:false,width:50,align:'center'},
              {field:'M2',title:'R值',sortable:false,width:30,align:'center'},
              {field:'M4',title:'是否有监控预警系统',sortable:false,width:30,align:'center',
              	formatter : function(value, row, index){
            		if(value=='0') return value='否';
            		if(value=='1') return value='是';
            	}},
              {field:'M5',title:'主要危险性',sortable:false,width:100,align:'center'},
              {field:'M1',title:'重大危险源级别',sortable:false,width:30,align:'center',
              	formatter : function(value, row, index){
            		if(value=='1') return value='一级';
            		if(value=='2') return value='二级';
            		if(value=='3') return value='三级';
            		if(value=='4') return value='四级';
            	}},
            	{field:'cz',title:'操作',sortable:false,width:50,align:'center',
                  	formatter : function(value, row, index){
                		 return "<a  class='btn btn-info btn-xs' onclick='showCGlist("
							+ row.qyid + ")'>实时数据</a>";;
                	}},
              
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	 view();
    }, onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

//统计
function statistics(){
	openDialogView("重大危险源统计",ctx+"/bis/hazard/statistics","700px", "500px", "");
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
			url:ctx+"/bis/hazard/ajdelete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改重大危险源信息",ctx+'/bis/hazard/ajupdate/'+row.qyid,"850px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看重大危险源信息",ctx+'/bis/hazard/view/'+row.qyid,"800px", "400px","");
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	obj.bis_hazard_qy_name=$("#bis_hazard_qy_name").combobox("getText");
	obj.bis_hazard_wuzhi_name=$("#bis_hazard_wuzhi_name").combobox('getText');
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function fileexport(){
	var obj=$("#searchFrom").serializeObject();
	obj.bis_hazard_qy_name=$("#bis_hazard_qy_name").combobox("getText");
	obj.bis_hazard_wuzhi_name=$("#bis_hazard_wuzhi_name").combobox('getText');
	window.expara=obj;
	window.exdata=[
		{colval:'qyname', coltext:'企业名称'},
		{colval:'M1', coltext:'重大危险源级别'},
		{colval:'M2', coltext:'R值'},
		{colval:'M3', coltext:'厂区人数'},
		{colval:'M4', coltext:'是否有安全监控系统'},
		{colval:'M5', coltext:'主要危险'},
		{colval:'M6', coltext:'联系人'},
		{colval:'M7', coltext:'联系电话'},
		{colval:'M8', coltext:'备注'},
		{colval:'M9', coltext:'重大危险源计算Q值'},
		{colval:'M9_1', coltext:'增加暴露人数'},
		{colval:'M10', coltext:'易导致事故类型'}
		   	];
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/hazard/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}


function showCGlist(qyid){
	openDialogView("储罐实时信息",ctx+'/zxjkyj/cgssjc/showqy/'+qyid,"800px", "400px","");
}
