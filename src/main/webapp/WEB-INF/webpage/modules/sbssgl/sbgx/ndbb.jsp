<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>年度报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<button class="btn btn-white btn-sm" style="margin-top: 5px;margin-left: 5px;" data-toggle="tooltip" data-placement="left" onclick="upload()" title="上传年度大项修计划"><i class="fa fa-upload"></i> 上传年度大项修计划</button> 
	<div id="sbssgl_sbgx_ndbb_tb" style="padding:5px;height:auto"></div>
		<table id="sbssgl_sbgx_ndbb_dg"></table> 	
	
<script type="text/javascript">
	var dg=$('#sbssgl_sbgx_ndbb_dg').datagrid({    
		method: "post",
	    url: ctx+'/sbssgl/sbgx/ndbblist', 
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
	        {field:'qyname',title:'所属企业',sortable:false,width:100,align : 'center'},
	        {field:'m1',title:'年度',sortable:false,width:70,align : 'center'},
	        {field:'deptname',title:'部门名称',sortable:false,width:70,align:'center'},
	        {field:'m2',title:'标题',sortable:false,width:70,align : 'center'},
	        {field:'m3',title:'操作',sortable:false,width:70,align : 'center',
	        	formatter: function(value) {
        			var fileurl = value.split("||");
        			return "<a class='btn btn-warning btn-xs' onclick='window.open(\""+fileurl[0]+"\")' style='margin-left: 5px;'> 查看附件</a>";
	        	}
	        }
	    ]],
	    onLoadSuccess: function(){
			$(this).datagrid("autoMergeCells", [ 'qyname']);
			$(this).datagrid("autoMergeCells", [ 'm1']);
	    },
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#sbssgl_sbgx_ndbb_tb'
	});
		
	function upload(){
		openDialog("上传年度报表",ctx+"/sbssgl/sbgx/uploadndbb","80%", "100%","");
	}
</script>
</body>
</html>
