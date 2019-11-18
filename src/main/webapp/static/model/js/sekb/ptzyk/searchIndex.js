var dg;
var d;
$(function(){
	dg=$('#sekb_ptzyk_dg').datagrid({    
	method: "post",
    url: wfwurl +"/MicroService/tdic/flfg/flfglist",
    queryParams: {zdgl_flfg_m2: m2, zdgl_flfg_m1_1: m1_1},
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
              {field:'m1_1',title:'大类别',width:40,align:'center',
				  formatter : function(value, row, index) {
					  if(value=='1') {
						  return '法律';
					  }else if(value=='2'){
						  return '法规';
					  }else if(value=='3'){
						  return '规章';
					  }else if(value=='4'){
						  return '地方性法规';
					  }else if(value=='5'){
						  return '政府文件';
					  }else if(value=='6'){
						  return '标准规范';
					  }else if(value=='7'){
						  return '其他';
					  }
				  }
			  },
		      {field:'m1',title:'小类别',width:80,align:'center'},
              {field:'m2',title:'名称',width:170,
            	  formatter: function(value, row){
            		  return "<a style='cursor: pointer;' onclick=view("+row.id+")>"+value+"</a>";
            	  } 
              },
              {field:'m8',title:'附件',width:50,align:'center',
            	  formatter: function(val) {
            		  if(val!=null&&val!=""){
	              			var t="||";
	              			val=val.split(t);
	              			return '<a onclick="window.open(\''+val[0]+'\')" href="javascript:void(0)">附件下载</a>';  
	              		}else{
	              			return '--';
	              		}
            	  }
              },
              
    ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sekb_ptzyk_tb'
	});
	
});

// 返回到法律法规查询页面
function back(){
	location.href = ctx+'/sekb/ptzyk/index';
}

//查看
function view(id){
	$.ajax({
		type: 'POST',
		url: wfwurl +"/MicroService/tdic/flfg/json2",
		data: {'id': id},
		success: function(data){
			var obj = JSON.parse(data);
			var m11 = obj.m11.split("||")[0];
			window.open(m11);
		}
	});
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
