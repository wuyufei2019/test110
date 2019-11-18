var dg;
var d;
$(function(){
	dg=$('#bis_cgjcwhsj_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/cgjcwhsj/list', 
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
        {field:'equip_code',title:'设备编码',sortable:false,width:150,align:'center'},
        {field:'target_name',title:'指标名称',sortable:true,width:100,align:'center'},
        {field:'label',title:'监测类型',sortable:true,width:80,align:'center'},
        {field:'bit_no',title:'监测点位',sortable:true,width:100,align:'center'},
        {field:'unit',title:'监测点位单位',sortable:true,width:80,align:'center'},
        {field:'threshold_up',title:'阈值上限',sortable:false,width:100,align:'center',
			formatter: function (value,row) {
				if (value != null) {
					return value + ' ' + row.unit;
				}
			}
		},
		{field:'threshold_upplus',title:'阈值上上限',sortable:true,width:100,align:'center',
			formatter: function (value,row) {
				if (value != null) {
					return value + ' ' + row.unit;
				}
			}
		},
		{field:'threshold_down',title:'阈值下限',sortable:false,width:100,align:'center',
			formatter: function (value,row) {
				if (value != null) {
					return value + ' ' + row.unit;
				}
			}
		},
		{field:'threshold_downplus',title:'阈值下下限',sortable:false,width:100,align:'center',
			formatter: function (value,row) {
				if (value != null) {
					return value + ' ' + row.unit;
				}
			}
		},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	onLoadSuccess: function() {
		$(this).datagrid('autoMergeCells', ['equip_code']);
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_cgjcwhsj_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加监测指标信息",ctx+"/bis/cgjcwhsj/create/","800px", "400px","");
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
			url:ctx+"/bis/cgjcwhsj/delete/"+ids,
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
	
	openDialog("修改监测指标信息",ctx+"/bis/cgjcwhsj/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看监测指标信息",ctx+"/bis/cgjcwhsj/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'所属储罐'},
			   		{colval:'m2', coltext:'储罐类型'},
			   		{colval:'m3', coltext:'容积'},
			   		{colval:'m4', coltext:'材质'},
			   		{colval:'m6', coltext:'火灾危险性等级'},
			   		{colval:'m7', coltext:'储存物料名称'},
			   		{colval:'m8', coltext:'cas号'},
			   		{colval:'m9', coltext:'位号'},
			   		{colval:'m10', coltext:'罐径'},
			   		{colval:'m11', coltext:'罐高'},
			   		{colval:'m12', coltext:'储罐区面积'},
			   		{colval:'m13', coltext:'有无防火堤'},
			   		{colval:'m14', coltext:'防火堤所围面积'},
//			   		{colval:'deviceno', coltext:'设备编号'},
//			   		{colval:'channelno', coltext:'设备通道'},
			   		{colval:'whp', coltext:'危化品类别'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/cgjcwhsj/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}