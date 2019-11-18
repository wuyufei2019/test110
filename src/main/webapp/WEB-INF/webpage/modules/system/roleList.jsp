]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
	<div class="form-group">
    	<span>名称：</span><input type="text" name="filter_LIKES_name" class="form-control"  />
    	<span>编码：</span><input type="text" name="filter_LIKES_roleCode" class="form-control" />
       </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:role:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:role:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:role:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:role:permView">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="setRole()" title="权限设置"><i class="fa fa-trash-o"></i>权限设置</button> 
		    </shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
		</div>
	</div>
	</div> 
	   
</div>


<table id="dg"></table> 

<script type="text/javascript">
var dg;	 
$(function(){   
	dg=$('#dg').datagrid({    
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
	scrollbarSize:0,
	striped:true,
    columns:[[    
        {field:'id',title:'id',checkbox:true,width:100},    
        {field:'name',title:'角色名称',sortable:true,width:100},
        {field:'roleCode',title:'角色编码',sortable:true,width:100},
        {field:'description',title:'描述',sortable:true,width:100,tooltip: true},
        {field : 'action',title : '操作',
			formatter : function(value, row, index) {
				return "<a href='#' onclick='setRole("+row.id+")' class='btn btn-primary btn-xs' ><i class='fa fa-edit'></i> 权限设置</a>";
			}
        }
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});


//弹窗增加
function add() {
	openFormWindow('${ctx}/system/role/create', '添加角色');
}

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	var rowIndex = row.id;
	
	openFormWindow('${ctx}/system/role/update/'+rowIndex, '修改角色')
	 
}

//弹出添加或修改框
function openFormWindow(url,title){
	top.layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['600px', '300px'],
	    title: title,
        maxmin: true,  
	    content: url,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#inputForm');
	         
	         if(iframeWin.contentWindow.validateForm.form()){
	     		$.ajax({
	     			type:'post',
	     			url:iframeWin.contentWindow.url,
	     			data:inputForm.serialize(),
	                 success:function(data){
	                	 if(data=='success')
	        		    		layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	        		    	else
	        		    		layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	                	 top.layer.close(index);
	     	        	 dg.datagrid('reload');
	     	        	 return true;
	                 }
	     		});
	     	} 

		  },
		  cancel: function(index){ 
	       }
	}); 	
}


//删除
function del(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:"${ctx}/system/role/delete/"+row.id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
	 
}



//设置角色权限
function setRole(roleId){
	if(roleId==null||roleId==''){
		var row = dg.datagrid('getSelected');
		if(row==null) {
			layer.msg("请选择一行记录！",{time: 1000});
			return;
		}
		roleId=row.id;
	}

    var flag=true;
	
	top.layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['450px', '90%'],
	    title: '权限设置',
        maxmin: true, 
	    content: '${ctx}/system/role/'+roleId+'/setrole',
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	         if(!flag)
	            return ;
	    	 var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var permissions = iframeWin.contentWindow.getPermissionList();
	         
	         top.layer.confirm('确认要保存保存角色权限？', {icon: 3, title:'提示'}, function(index2){
                 $.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
                 flag=false;
	         	 $.ajax({
	         			async:false,
	        			type:'POST',
	        			data:JSON.stringify(permissions),
	        			contentType:'application/json;charset=utf-8',
	        			url:'${ctx}/system/role/'+roleId+'/updatePermission',
	        			success: function(data){
                            $.jBox.closeTip();
	        				if(data=='success')
	        		    		layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	        		    	else
	        		    		layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	        				top.layer.close(index2);
	        				top.layer.close(index);
	        			}
	        		});
	        	});
		  },
		  cancel: function(index){ 
	       }
	}); 	
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//全部
function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}


</script>
</body>
</html>