var dg;
var d;
$(function(){
	var nowyear=new Date().getUTCFullYear();
	dg=$('#sggl_sgtj_dg').datagrid({    
	method: "post",
    url:ctx+'/sggl/sgxx/tjlist', 
    fit : false,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	queryParams:{'sggl_sgtj_year':nowyear},
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
              {field:'qynm',title:'发生单位',width:100,align:'center'}, 
              {field:'m1',title:'事故编号',width:60,align:'center'},
              {field:'m2',title:'事故名称',width:80,align:'center'},
              {field:'m3',title:'事故类型',width:50,align:'center'},
              {field:'m4',title:'事故等级',width:40,align:'center'},
              {field:'m5',title:'发生时间',width:60,align:'center',
            	  formatter : function(value, row, index){
            		  if(value!=null&&value!=''){
            			  var datetime=new Date(value);
            			  return datetime.format('yyyy-MM-dd hh:mm:ss');  
            		  }
            	  }  
              },    
              {field:'m6',title:'发生地点',width:100,align:'center'},
              {field:'bmname',title:'所属部门',width:50,align:'center'},
              {field:'m7',title:'事故性质',width:40,align:'center'},
              {field:'qs',title:'轻伤人数',width:30,align:'center'},
              {field:'zs',title:'重伤人数',width:30,align:'center'},
              {field:'sw',title:'死亡人数',width:30,align:'center'},
              {field:'m11',title:'经济损失(万元)',width:40,align:'center',
              	formatter:function(value, row, index){
            		if(value!=null&&value!=""){
            			return value.toFixed(2);
            		}
            	}
              }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
      },
    toolbar:'#sggl_sgtj_tb'
	});
});

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看事故登记信息",ctx+"/sggl/sgxx/view/"+row.id,"100%", "100%","");
	
}


// 创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	var year=$("[name=sggl_sgtj_year]").val()+"年";
	if(year=='年'){
		var myDate = new Date();
		year=myDate.getFullYear();
	}
	loadEcharts(year);
}

function reset(){
	$("#searchFrom").form("clear");
	$('#type').combobox('setValue', '按月份');
	$('#sggl_sgtj_year').combobox('setValue', nowyear);
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	loadEcharts(year);
}

