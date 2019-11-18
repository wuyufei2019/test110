var dg;
var d;
$(function(){
	dg=$('#aqjg_tjfx_dg').datagrid({    
	method: "post",
    url:ctx+'/aqjg/tjfx/list', 
    fit : false,
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
        {field:'qynm',title:'发生单位',sortable:false,width:100,align:'center'},
        {field:'m1',title:'发生时间',sortable:false,width : 50,
			align : 'center',
			formatter : function(value) {
				if (value != null && value != '') {
					var date = new Date(value);
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					var hh = date.getHours();
					var mm = date.getMinutes();
					var ss = date.getSeconds();
					return y + '/' + (m < 10 ? ('0' + m) : m)
							+ '/' + (d < 10 ? ('0' + d) : d)
				} else {
					return '';
				}
			}},
		{field:'m3',title:'事故类型',width:120,align:'center'},
        {field:'m5',title:'事故等级',sortable:false,width:50,align:'center',formatter:function(value){
        	if(value=='0')return '特别重大事故';
	        if(value=='1')return '重大事故';
	        if(value=='2')return '较大事故';
	        if(value=='3')return '一般事故';
        }},
        {field:'m6',title:'死亡人数',sortable:false,width:30,align:'center'},
        {field:'m7',title:'重伤人数',sortable:false,width:30,align:'center'},
        /*{field:'m8',title:'轻伤人数',sortable:false,width:30,align:'center'},*/
        {field:'m9',title:'直接经济损失(万元)',sortable:false,width:60,align:'center'},
        {field:'m10',title:'间接经济损失(万元)',sortable:false,width:60,align:'center'},
       /* {field:'m11',title:'事故描述',sortable:false,width:50,align:'center'},
        {field:'m12',title:'事故处理',sortable:false,width:50,align:'center'},
        {field:'m13',title:'事故预防对策',sortable:false,width:50,align:'center'},*/
        {field:'m14',title:'备注',sortable:false,width:100,align:'center'}
       
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
      },
    toolbar:'#aqjg_tjfx_tb'
	});
});

// 查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看事件录入信息",ctx+"/aqjg/tjfx/view/"+row.id,"900px", "400px","");
	
}


// 创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	var year=$("[name=aqjg_tjfx_year]").val()+"年";
	if(year=='年')
		year="";
	loadEcharts(year);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	loadEcharts(year);
}

