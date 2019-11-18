var dg;
var d;
$(function(){
	dg=$('#bis_scsb_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/scsb/ajlist', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'m1',title:'设备位号',sortable:false,width:80},
        {field:'m2',title:'设备类别',sortable:true,width:80,align:'center'},
        {field:'m3',title:'设备名称',sortable:false,width:80,align:'center'},
        {field:'m8',title:'投用日期',sortable:true,width:80,align:'center',
        	styler: function(value, row, index) {
              	if(value!==null&&value!='') {
		    	   var nowhm=(new Date()).getTime();
		    	   var m8=new Date(value);
		    	   var m8time=m8.getTime();
		    	   var i=(nowhm-m8time)/1000/60/60/24;
              		if(i<=1095){
              			return 'background-color:#FFD2D2;';
              		}else if(i>1095&&i<=1825){
              			return 'background-color:#ff7575';
              		}else if(i>1825&&i<=2920){
              			return 'background-color:#FF5151;';
              		}else if(i>2920){
              			return 'background-color:#AE0000;';
              		}
              	}
        	},
        	  formatter : function(value, row, index) {
              	if(value!==null&&value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');  
              	}
          	} 
        },
        {field:'m5',title:'型号',sortable:false,width:80,align:'center'},
        {field:'m6',title:'数量',sortable:true,width:80,align:'center'},
        {field:'m7',title:'制造单位',sortable:false,width:80,align:'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_scsb_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加生产设备信息",ctx+"/bis/scsb/create/","800px", "400px","");
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
			url:ctx+"/bis/scsb/delete/"+ids,
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
	
	openDialog("修改生产设备信息",ctx+"/bis/scsb/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看生产设备信息",ctx+"/bis/scsb/view/"+row.id,"800px", "400px","");
	
}
//统计
function statistics(){
	top.layer.open({
	    type: 2,  
	    area: ['80%', '80%'],
	    title: '生产设备统计分析',
        maxmin: true, 
	    content: ctx+"/bis/scsb/statistics" ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	});
}

//创建查询对象并查询
function search(){
	$('#bis_scsb_cx_id1').combobox('setValue',$('#bis_scsb_cx_id1').combobox('getText'));
	$('#bis_scsb_cx_m2').combobox('setValue',$('#bis_scsb_cx_m2').combobox('getText'));
	$('#bis_scsb_cx_m3').combobox('setValue',$('#bis_scsb_cx_m3').combobox('getText'));
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
	               	{colval:'qyname', coltext:'企业名称'},
			   		{colval:'m1', coltext:'设备位号'},
			   		{colval:'m2', coltext:'设备类别'},
			   		{colval:'m3', coltext:'设备名称'},
			   		{colval:'m4', coltext:'规格尺寸'},
			   		{colval:'m5', coltext:'型号'},
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
	    content: ctx+'/bis/scsb/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}