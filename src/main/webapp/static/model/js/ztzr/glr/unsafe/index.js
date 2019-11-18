var dg;
var d;
$(function(){
	dg=$('#glr_unsafe_dg').datagrid({    
	method: "post",
	url: ctx + '/glr/unsafe/list',
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
		      {field: 'qyname', title: '企业名称', sortable: false, width: 80},
              {field:'xwaq',title:'行为管理',sortable:false,width:50,align:'center',
		    	  formatter : function(value, row, index) {
		    		  return "<a onclick='openDialogView(\"行为管理\",\""+ctx+"/yhpc/unsafe/index/?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>"; 
		    	  }
              },
              {field:'gcjl',title:'不安全行为纠正记录',sortable:false,width:50,align:'center',
		    	  formatter : function(value, row, index) {
		    		  return "<a onclick='openDialogView(\"不安全行为纠正记录\",\""+ctx+"/yhpc/observe/index/?qyid="+row.qyid+"\",\"90%\", \"90%\",\"\")'>"+value+"</a>";
		    	  }
              },
              {field:'dcd',title:'达成度',width:60,align:'center',
                  formatter:function(value, row, index){
                      return "<span class=\'fa fa-close btn-danger btn-outline\' >未落实</span>";
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
    toolbar:'#glr_unsafe_tb'
	});
	
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}