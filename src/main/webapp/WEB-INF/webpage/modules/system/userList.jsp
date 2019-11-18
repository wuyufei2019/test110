<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script>
		var btflg=0;
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
	<shiro:hasAnyRoles name="ajcountryadmin,companyadmin">
    	<input type="hidden" name="filter_EQS_xzqy" value="${xzqy}" />
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="admin,spueradmin,ajcountyadmin,ajtownadmin">
     <script>
		btflg=2;
     </script>
     		用户类型：<select id="filter_EQS_usertype" name="filter_EQS_usertype" class="form-control">
			<option value="" selected="selected"></option>
			<option value="0">安监人员</option>
			<option value="1">企业人员</option>
			<shiro:hasAnyRoles name="admin,spueradmin"><option value="5">中介机构</option></shiro:hasAnyRoles>
		    </select>
	</shiro:hasAnyRoles>
				
	<div class="form-group">
		<span>账号：</span><input type="text" name="filter_LIKES_loginName" class="form-control"  />
    	<span>昵称：</span><input type="text" name="filter_LIKES_name" class="form-control"  />
    	<span>电话：</span><input type="text" name="filter_LIKES_phone" class="form-control" />
       </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:user:add">
				<shiro:hasAnyRoles name="admin,spueradmin">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加用户</button>
				</shiro:hasAnyRoles>
		       	<shiro:hasAnyRoles name="ajcountyadmin,ajtownadmin">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加安监成员"><i class="fa fa-plus"></i> 添加安监成员</button>
				</shiro:hasAnyRoles>
				<shiro:hasAnyRoles name="companyadmin">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加企业普通用户"><i class="fa fa-plus"></i> 添加企业成员</button>
				</shiro:hasAnyRoles>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:disable">
				<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disableUser()" title="禁用"> 禁用</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:enable">
				<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enableUser()" title="恢复"> 恢复</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:resetpaw">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="resetPaw()" title="重置密码"><i class="fa fa-reply"></i> 重置密码</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:roleView">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="userForRole()" title="用户角色"><i class="fa fa-plus-circle"></i> 用户角色</button> 
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
var d;
$(function(){
	dg=$('#dg').datagrid({    
	method: "get",
    url:'${ctx}/system/user/json', 
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
	scrollbarSize:0,
	singleSelect:true,
	checkOnSelect:false,
	selectOnCheck:false,
    columns:[[    
        {field:'id',title:'id',checkbox:true,align:'center'},    
        {field:'loginName',title:'帐号',width:50},    
        {field:'name',title:'昵称',width:50},
        {field:'gender',title:'性别', 
        	formatter : function(value, row, index) {
       			return value==1?'男':'女';
        	}
        },
        {field:'phone',title:'电话',width:40},
        {field:'loginCount',title:'登录次数',width:30},
        {field:'previousVisit',title:'上一次登录时间',width:50,  
                     formatter:function(value,row,index){
                     		if(value!=null){
                         var datetime = new Date(value);  
                         return datetime.format("yyyy-MM-dd hh:mm:ss");  }  
                         }  
         },
         {field:'lastVisit',title:'最后一次登录',width:50,  
                     formatter:function(value,row,index){
                     		if(value!=null){
                         var datetime = new Date(value);  
                         return datetime.format("yyyy-MM-dd hh:mm:ss");  }  
                         }  
         },
         {field:'loginip',title:'最后一次登录IP',width:50} ,

        {field:'delFlag',title:'用户状态',width:50,align:'center',
            formatter:function (value) {
                if(!value || value==0){
                    return "<a class='btn btn-success btn-xs'>已启用</a>";
                }else{
                    return "<a class='btn btn-danger btn-xs'>已禁用</a>";
                }
            }
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 upd();
    },
    headerContextMenu: [
        {
            text: "冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", true).contains(field); },
            handler: function (e, field) { dg.datagrid("freezeColumn", field); }
        },
        {
            text: "取消冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", false).contains(field); },
            handler: function (e, field) { dg.datagrid("unfreezeColumn", field); }
        }
    ],
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加用户","${ctx}/system/user/create/"+u,"800px", "500px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少选择一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:"${ctx}/system/user/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}

//禁用
function disableUser(){
    var row = dg.datagrid('getChecked');
    if(row==null||row=='') {
        layer.msg("请至少选择一行记录！",{time: 1000});
        return;
    }

    var ids="";
    for(var i=0;i<row.length;i++){
        if(row.delFlag==1){
            layer.msg("请选择已启用的用户！");
            return;
        }
        if(ids==""){
            ids=row[i].id;
        }else{
            ids=ids+","+row[i].id;
        }
    }

    top.layer.confirm('确定要禁用该用户吗？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:"${ctx}/system/user/disable/"+ids,
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
}
//恢复
function enableUser(){
    var row = dg.datagrid('getChecked');
    if(row==null||row=='') {
        layer.msg("请至少选择一行记录！",{time: 1000});
        return;
    }
    var ids="";
    for(var i=0;i<row.length;i++){
        if(row.delFlag==1){
            layer.msg("请选择已禁用的用户！");
            return;
        }
        if(ids==""){
            ids=row[i].id;
        }else{
            ids=ids+","+row[i].id;
        }
    }
    top.layer.confirm('确定要恢复该用户吗？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:"${ctx}/system/user/enable/"+ids,
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
}

//弹窗修改
function upd(){
    var row = dg.datagrid('getSelections');

    if(row.length==0) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.length > 1) {
        layer.msg("只能选择一行记录！",{time: 1000});
        return;
    }
	
	openDialog("修改用户","${ctx}/system/user/update/"+row[0].id,"800px", "500px","");
	
}

//用户角色弹窗
function userForRole(){
    var row = dg.datagrid('getSelections');

    if(row.length==0) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.length > 1) {
        layer.msg("只能选择一行记录！",{time: 1000});
        return;
    }
	
	openDialog("用户角色管理","${ctx}/system/user/"+row[0].id+"/userRole","500px", "400px","");
	
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
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//重置密码
function resetPaw(){
    var row = dg.datagrid('getChecked');
    if(row==null||row=='') {
        layer.msg("请至少选择一行记录！",{time: 1000});
        return;
    }

    var ids="";
    for(var i=0;i<row.length;i++){
        if(ids==""){
            ids=row[i].id;
        }else{
            ids=ids+","+row[i].id;
        }
    }
	
	top.layer.confirm('确定要重置密码吗？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'post',
			url:"${ctx}/system/user/resetPwd",
			data:{'ids':ids},
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
			}
		});
	});
}
</script>
</body>
</html>