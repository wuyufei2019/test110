<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>培训需求调查</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
<div id="aqpx_xqdc_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="aqpx_xqdc_pxzt" name="aqpx_xqdc_pxzt" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '培训需求主题'"/>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqpx:xqdc:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="发起调查"><i class="fa fa-plus"></i> 发起调查</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:xqdc:set">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="setInfor()" title="调查设置"><i class="fa fa-file-text-o"></i> 调查设置</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:xqdc:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
            <shiro:hasPermission name="aqpx:xqdc:disable">
				<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disableDc()" title="终止"> 终止调查</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqpx:xqdc:enable">
				<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enableDc()" title="恢复"> 恢复调查</button>
			</shiro:hasPermission>
            <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
	</div>
	</div>    
</div>
<table id="aqpx_xqdc_dg"></table> 
<script type="text/javascript">
var dg;

$(function(){
	dg=$('#aqpx_xqdc_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqpx/xqdc/list', 
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
	        {field:'pxzt',title:'培训调查主题',sortable:false,width:100},    
	        {field:'kcname',title:'最高投票课程',sortable:false,width:80,align:'center'},    
	        {field:'toptp',title:'最高投票数',sortable:false,width:40,align:'center'},
	        {field:'tpid',title:'是否投票',sortable:false,width:60,align:'center',
	        	formatter:function (value,row) {
					if(value == null){
						return '未投票';
					}else{
						return '已投票';
					}
	            }
	        },
	        {field:'flag',title:'培训调查是否结束',sortable:false,width:60,align:'center',
	        	formatter:function (value,row) {
					if(value == 0){
						return '未结束';
					}else{
						return '已结束';
					}
	            }
	        },
	        {field:'cz',title:'操作',sortable:false,width:60,align:'center',
	        	formatter:function (value,row) {
					if(row.flag == 0){
						return "<a class='btn btn-success btn-xs' style='margin: 3px;' onclick='dctp("+row.id+")'>进行投票</a>";
					}
	            }
	        }
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#aqpx_xqdc_tb'
	});
});

//发起调查
function add(u) {
	openDialog("发起调查问卷",ctx+"/aqpx/xqdc/create/","800px", "400px","");
}

//投票
function dctp(id){
	openDialogView("投票页面",ctx+"/aqpx/xqdc/index/"+id,"100%", "100%","");
}

//调查设置
function setInfor(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    openDialog("设置培训课程投票项信息",ctx+"/aqpx/xqdc/setinfor/"+row.id,"900px", "70%","");
}

//终止调查
function disableDc(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.flag!=0){
        layer.msg("该调查已终止！");
        return;
    }
    var title = '确定要终止该调查吗？';
    var url = "/aqpx/xqdc/changedc/"+row.id+"/1";
    changedc(title,url);
}

//恢复调查
function enableDc(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.flag==0){
        layer.msg("该调查正在进行！");
        return;
    }
    var title = '确定要恢复该调查吗？';
    var url = "/aqpx/xqdc/changedc/"+row.id+"/0";
    changedc(title,url);
}

function changedc(title,url){
	top.layer.confirm(title, {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+url,
            success: function(data){
            	if(data == 'success')
            		layer.alert('操作成功！', {offset: 'rb',shade: 0,time: 2000});
            	else
            		layer.alert('操作失败！', {offset: 'rb',shade: 0,time: 2000}); 
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
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
			url:ctx+"/aqpx/xqdc/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
}
</script>
</body>
</html>