<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>   
<table id="ur_dg"></table>
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); 
	
	var ur_dg;
	ur_dg=$('#ur_dg').datagrid({    
	method: "get",
	url:'${ctx}/system/role/json', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 300 ],
	scrollbarSize:10,
	striped:true,
    columns:[[    
		{field:'ck',checkbox:true},
        {field:'id',title:'id',hidden:true,sortable:true,width:100},    
        {field:'name',title:'角色名称',sortable:true,width:100},
        {field:'roleCode',title:'角色编码',sortable:true,width:100},
        {field:'description',title:'描述',sortable:true,width:100,tooltip: true}
    ]],
    onLoadSuccess:function(){
    	//获取用户拥有角色,选中
    	$.ajax({
    		async:false,
			type:'get',
			url:"${ctx}/system/user/${userId}/role",
			success: function(data){
				if(data){
					for(var i=0,j=data.length;i<j;i++){
						ur_dg.datagrid('selectRecord',data[i]);
					}
				} 
			}
		});
    	
    }
	});

	
//保存用户角色
function doSubmit(){
	var newRoleList=[];
	var data=ur_dg.datagrid('getSelections');
	//所选的角色列表
	for(var i=0,j=data.length;i<j;i++){
		newRoleList.push(data[i].id);
	}
	$.ajax({
		async:false,
		type:'POST',
		data:JSON.stringify(newRoleList),
		contentType:'application/json;charset=utf-8',	//必须
		url:"${ctx}/system/user/${userId}/updateRole",
		success: function(data){
			if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.layer.close(index); 
		}
	});
}
</script>
</body>
</html>