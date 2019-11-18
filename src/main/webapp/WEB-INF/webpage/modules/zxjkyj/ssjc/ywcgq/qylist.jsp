<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>物料实时监测数据</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">

<table id="ssjc_wl_dg"></table> 

<script type="text/javascript" >
var qyid=${qyid};
var dg;
var d;

$(function(){   
	dg=$('#ssjc_wl_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/zxjkyj/cgssjc/qylist/'+qyid,
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:false,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[    
        {field:'wh',title:'储罐位号',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
     		if (value==null||value=='')
     			return '/';
     		else
     			return value;
     	}},    
        {field:'wl',title:'存储物料名称',sortable:false,width:100,align:'center'},
        {field:'lx',title:'储罐类型',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='立式圆筒形储罐';
            	if(value=='2') return value='卧式圆筒形储罐';
				if(value=='3') return value='球形储罐';
				if(value=='4') return value='其他储罐'; 
				if(value==null) return value='仓库';
        	}      
        },
        {field:'rj',title:'容积(m³)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},    
        {field:'gh',title:'罐高(m)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},
        {field:'gj',title:'罐径(m)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},
     	{field:'yw',title:'液位(m)',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
        		if (value!=null)
         		 	return value.toFixed(2);
        		else
         			return '/';
         }},
        {field:'cl',title:'实时储量(m³)',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value.toFixed(3);
         	}
     	},
     	 
        {field:'wd',title:'温度(℃)',sortable:false,width:80,align:'center' },
        {field:'yl',title:'压力(MPa)',sortable:false,width:80,align:'center',
     		formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value.toFixed(3);
         	}
     	},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	showqycg(rowindex.qyid);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    rowStyler:function(index,row){
		if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
			return 'background-color:rgb(232, 190, 101);';
		}
	},
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#ssjc_wl_tb'
	});
  
});

timerID = setInterval("refresh()",60000);

//datagrid刷新
function refresh(){
dg.datagrid('reload'); 
}


//显示企业储罐信息页面
function showqycg(n){
var href=ctx+'/zxjkyj/cgssjc/view/'+n;
window.location.href=href;
//top.openTab(href,"实时储量", false);
}

</script>
</body>
</html>