var dg;
var d;
var url;
$(function(){
	url = ctx+'/sbssgl/sbgl/list?sbtype=' + sbtype;
	dg=$('#sbssgl_sbgl_dg').datagrid({    
	method: "post",
    url: url, 
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
        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
        {field:'deptname',title:'部门名称',sortable:false,width:50,align : 'center'},
        {field:'m1',title:'设备编号',sortable:false,width:40,align:'center'},
        {field:'m2',title:'设备名称',sortable:false,width:50,align:'center'},
        {field:'m3',title:'规格',sortable:false,width:40,align:'center'},
        {field:'m27',title:'型号',sortable:false,width:40,align:'center'},
        {field:'m5',title:'制造单位',sortable:false,width:60,align:'center'},
        {field:'m30',title:'安装单位',sortable:false,width:60,align : 'center'},
        {field:'m8',title:'安装地点',sortable:false,width:60,align : 'center'},
        {field:'m16',title:'启用时间',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'bgrname',title:'保管人',sortable:false,width:30,align:'center'},
        {field:'m19',title:'状态',sortable:false,width:40,align:'center',
        	formatter : function(value, row, index) {
        		if(value == '0'){
        			return "启用";
        		}else if(value == '2'){
        			return "报废";
        		}
        	},
        	styler: function(value){
        		if(value == '0'){
        			return "color: #23c6c8";
        		}else if(value == '2'){
        			return "color: #ed5565";
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
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_sbgl_tb'
	});
});

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
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
			url:ctx+"/sbssgl/sbgl/delete/"+ids,
			data: {"sbtype": sbtype},
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
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备管理信息",ctx+"/sbssgl/sbgl/view/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}

//弹窗增加设备台账信息
function add() {
	openDialog("添加设备管理信息",ctx+"/sbssgl/sbgl/create2?sbtype="+sbtype,"800px", "400px","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改设备管理信息",ctx+"/sbssgl/sbgl/update/"+row.id+"?sbtype="+sbtype,"800px", "400px","");
}

//导出
function fileexport(sbtype){
	var exportUrl;
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	/*{colval:'RowNumber', coltext:'序号/No.'},
			   		{colval:'m1', coltext:'设备编号/Code'},
			   		{colval:'m2', coltext:'设备名称/Description'},
			   		{colval:'m5', coltext:'制造单位/Manufacturing company'},
			   		{colval:'m3', coltext:'设备型号 /Code'},
			   		{colval:'m9', coltext:'系列号/SN'},
			   		{colval:'m10', coltext:'电气功率/Power (30KVA)'},
			   		{colval:'m11', coltext:'用气量/Compressed Air(m3/min)'},
			   		{colval:'m12', coltext:'用水量/Water'},
			   		{colval:'m13', coltext:'外形尺寸/overall size'},
			   		{colval:'m14', coltext:'加工范围/Process field'},
			   		{colval:'m15', coltext:'重量/Weight'},
			   		{colval:'qysj', coltext:'启用时间/Opening time'},
			   		{colval:'m8', coltext:'安装地点/Installaton site'},
			   		{colval:'m17', coltext:'固定资产编号/CAPEX'},
			   		{colval:'m18', coltext:'备注/Remarks'}*/
	               	{colval:'RowNumber', coltext:'序号'},
			   		{colval:'m1', coltext:'设备编号'},
			   		{colval:'m2', coltext:'设备名称'},
			   		{colval:'m5', coltext:'制造单位'},
			   		{colval:'m3', coltext:'设备型号'},
			   		{colval:'m4', coltext:'出厂编号'},
			   		{colval:'m10', coltext:'电气功率'},
			   		{colval:'m13', coltext:'外形尺寸'},
			   		{colval:'m33', coltext:'加工精度'},
			   		{colval:'m14', coltext:'加工范围'},
			   		{colval:'m15', coltext:'设备重量'},
			   		{colval:'qysj', coltext:'启用时间'},
			   		{colval:'m34', coltext:'复杂系数'},
			   		{colval:'m35', coltext:'原值'},
			   		{colval:'m30', coltext:'安装单位'},
			   		{colval:'m8', coltext:'安装地点'},
			   		{colval:'m18', coltext:'备注'}
		   	];
	
	if (sbtype == '1') {//特种设备
		exportUrl = ctx+'/sbssgl/sbgl/colindex2';
	} else if (sbtype == '0') {//普通设备
		exportUrl = ctx+'/sbssgl/sbgl/colindex';
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: exportUrl,
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}