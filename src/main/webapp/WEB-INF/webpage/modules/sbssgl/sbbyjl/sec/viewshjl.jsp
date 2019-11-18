<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>审核记录</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		th {text-align: center;}
	</style>
</head>
<body>

	<div id="sbssgl_shjl_tb" style="padding:5px;height:auto">
	</div>
	<table id="sbssgl_shjl_dg"></table>
	
<script type="text/javascript">
var sbbytasksecid = '${id}';//设备二级保养计划id
var dg;
$(function(){
	dg=$('#sbssgl_shjl_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbbytasksec/shjllist/'+sbbytasksecid, 
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
        {field:'s1',title:'审核时间',sortable:false,width:70,align : 'center',
        	formatter: function(value) {
        		if (value) {
        			return new Date(value).format("yyyy-MM-dd hh:mm:ss");
        		}
        	}
        },
        {field:'m1',title:'审核结果',sortable:false,width:50,align:'center',
        	formatter: function(value) {
        		if(value == '0') {
        			return "<span style='color: white;'>未完成</span>";
        		} else if(value == '1') {
        			return "<span style='color: white;'>完成</span>";
        		} 
        	},
        	styler: function(value, row, index){
            	if (value == '0') {//当状态为未完成时，背景颜色为红色 
            		return 'background-color: #ed5565';
        		} else if (value == '1') {//当状态为完成时，背景颜色为绿色    #f8ac59
            		return 'background-color: #23c6c8'; 
        		} 
        	} 
        },
        {field:'shrname',title:'审核人',sortable:false,width:70,align:'center'},
        {field:'m3',title:'附件',sortable:false,width:50,align:'center',
        	formatter: function(value) {
        		if (value) {
        			var fileUrl = value.split("||");
        			return "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileUrl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
        		}
        	}
        }
    ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_shjl_tb'
	});
});
</script>		
</body>
</html>