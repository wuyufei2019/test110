<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐报警信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<table id="cg_bjxx_dg"></table> 
<script type="text/javascript">
var pointid='${pointid}';
var jctype='${jctype}';
$(function(){
	dg=$('#cg_bjxx_dg').datagrid({    
	method: "post",
    url: ctx+'/zxjkyj/bjxx/list',
	queryParams: {pointid: pointid},
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	rownumbers:true,
	nowrap:false,
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field : 'value',title : '报警信息',sortable : false,width : 50,align : 'center',
 			formatter : function(value, row, index) {
				if (jctype == 'WD')
					return value.toFixed(2)+" ℃";
				else if (jctype == 'YL')
					return value.toFixed(2)+" kPa";
				else if (jctype == 'YW')
					return value.toFixed(2)+" m";
			}
		},
		{field : 'alarmtype',title : '报警类型',sortable : false,width : 50,align : 'center'},
		{field : 'alarmtime',title : '报警时间',sortable : false,width : 70,align : 'center',
			formatter : function(value, row, index) {
              	if(value!=null&&value!='') {
              		var datetime=new Date(value);
              		return datetime.format('yyyy-MM-dd hh:mm:ss'); 
              	}
            }
		}
    ]],
    onLoadSuccess: function(){
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
	
});
</script>
</body>
</html>