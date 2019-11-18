var dg;
var d;
$(function(){
	dg=$('#bis_scsb_dg').datagrid({    
	method: "post",
    url:ctx+'/jtjcsj/sbxx/list', 
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
        {field:'equipcode',title:'设备编码',sortable:false,width:80},
        {field:'m3',title:'设备名称',sortable:false,width:80,align:'center'},
        {field:'hazardname',title:'重大危险源名称',sortable:false,width:80,align:'center'},
        {field:'hazardcode',title:'重大危险源编码',sortable:true,width:80,align:'center'},
        {field:'equiptype',title:'设备类型',sortable:false,width:80,align:'center',
        	formatter : function(value) {
        		if(value=='G0'){
        			return '罐'
        		}else if(value=='Q0'){
        			return '气体检测仪'
        		}else if(value=='S0'){
        			return '生产装置'
        		}else if(value=='C0'){
        			return '仓库'
        		}
        	}
        },
        {field:'equipstatus',title:'设备运行状态',sortable:false,width:80,align:'center',
        	formatter : function(value) {
        		if(value=='0'){
        			return '停用'
        		}else if(value=='1'){
        			return '在用'
        		}
        	}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_scsb_tb'
	});
	
});



//弹窗增加
function add(u) {
	openDialog("添加设备信息",ctx+"/jtjcsj/sbxx/create/","800px", "500px","");
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
			url:ctx+"/jtjcsj/sbxx/delete/"+ids,
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
	
	openDialog("修改设备信息",ctx+"/jtjcsj/sbxx/update/"+row.id,"800px", "500px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看设备信息",ctx+"/jtjcsj/sbxx/view/"+row.id,"800px", "500px","");
	
}

//创建查询对象并查询
function search(){
/*	$('#bis_scsb_cx_m2').combobox('setValue',$('#bis_scsb_cx_m2').combobox('getText'));
	$('#bis_scsb_cx_m3').combobox('setValue',$('#bis_scsb_cx_m3').combobox('getText'));*/
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
	$('#bis_scsb_cx_m2').combobox('setValue',$('#bis_scsb_cx_m2').combobox('getText'));
	$('#bis_scsb_cx_m3').combobox('setValue',$('#bis_scsb_cx_m3').combobox('getText'));
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'设备位号'},
			   		{colval:'m2', coltext:'设备类别'},
			   		{colval:'m3', coltext:'设备名称'},
			   		{colval:'m4', coltext:'规格尺寸'},
			   		{colval:'m5', coltext:'型号'},
			   		{colval:'m14', coltext:'功率'},
			   		{colval:'m15', coltext:'出厂日期'},
			   		{colval:'m16', coltext:'设备状况'},
			   		{colval:'m17', coltext:'设备使用单位'},
			   		{colval:'m18', coltext:'主要技术参数'},
			   		{colval:'m6', coltext:'数量'},
			   		{colval:'m7', coltext:'制造单位'},
			   		{colval:'m8', coltext:'投用日期'},
			   		{colval:'m9', coltext:'介质'},
			   		{colval:'m10', coltext:'下次检验日期'},
			   		{colval:'m11', coltext:'责任人'},
			   		{colval:'m12', coltext:'联系电话'},
			   		{colval:'m13', coltext:'主要危险性'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/jtjcsj/sbxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}