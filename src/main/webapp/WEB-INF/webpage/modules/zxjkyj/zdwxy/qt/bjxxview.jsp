<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>气体报警信息</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<table id="qt_bjxx_dg"></table> 
<script type="text/javascript">
var pointid='${pointid}';
var jctype='${jctype}';
$(function(){
	dg=$('#qt_bjxx_dg').datagrid({    
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
		{field : 'value',title : '气体报警浓度',sortable : false,width : 50,align : 'center',
			formatter : function(value, row, index) {
				if (jctype == 'YDQT')
					return value.toFixed(2)+" mg/m³或ppm";
				else if (jctype == 'KRQT')
					return value.toFixed(2)+" %LEL";
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