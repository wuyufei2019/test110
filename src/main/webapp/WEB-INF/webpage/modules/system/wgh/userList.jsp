<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格员管理</title>
	<meta name="decorator" content="default"/>
</head>
<body >

<div id="tb" style="padding:5px;height:auto">
	 
</div>


<table id="dg"></table> 

<script type="text/javascript">
var dg;
var d;
$(function(){
	dg=$('#dg').datagrid({    
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	scrollbarSize:0,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'id',title:'id',hidden:true,align:'center'},    
        {field:'login_name',title:'帐号',width:50,align:'center'},    
        {field:'name',title:'昵称',width:50,align:'center'},
        {field:'gender',title:'性别',align:'center', 
        	formatter : function(value, row, index) {
       			return value==1?'男':'女';
        	}
        },
        {field:'phone',title:'电话',width:50,align:'center'},
        {field:'role_code',title:'角色',width:50,align:'center',
        	formatter:function(value,row,index){
        		if(row.role_code=='ajtownadmin')
        			return "<span class='btn-danger'>网格管理员</span>";
        		else if(row.role_code=='ajtown')
        			return "<span class='btn-primary'>普通网格员</span>";
        	}},
        {field:'操作',title:'操作',width:50,align:'center',  
       		formatter:function(value,row,index){
       			var html='';
       			if(row.role_code=='ajtown')
       				 html="<a href='#' onclick='setManager("+row.id+")' class='btn btn-success btn-xs' ><i class='fa '></i> 设为网格管理员</a>";
       			return html;
       	} } 
    ]],
    data:${userlist},
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 
    },
    
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
});


function setManager(userid){
	$.ajax({
		async:false,
		type:'GET',
		url:"${ctx}/system/user/settomanager/"+userid,
		success: function(data){
			if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			location.reload();
		}
	});
}
</script>
</body>
</html>