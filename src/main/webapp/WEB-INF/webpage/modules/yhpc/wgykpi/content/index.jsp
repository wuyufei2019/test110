<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>网格员考勤规则管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.myPanelHead .panel-title {
	font-size: 18px;
	height: 25px;
	line-height: 25px;
	color: red;
	text-align :center
}
</style>
</head>
<body>
	<div class="easyui-panel" title="网格员考核规则"style="width:100%;height:100%;" data-options=" headerCls:'myPanelHead'">
		<div class="easyui-layout" id="layout" style="height:100%; ">
			<div data-options="region:'west',split:true,border:false,title:'网格名称'" style="width: 200px">
				<table id="menuDg"></table>
			</div>
			<div id="title" data-options="region:'center',split:true,border:true,title:'网格员考核规则'">
				<div id="tg_tb" style="padding:5px;height:auto">
					<div>
						<shiro:hasPermission name="yhpc:wgykpimon:add">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
								<i class="fa fa-plus"></i> 添加
							</button>
						</shiro:hasPermission>
						<%-- <shiro:hasPermission name="yhpc:wgykpimon:update">
  				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button>
		        </shiro:hasPermission> --%>
						<shiro:hasPermission name="yhpc:wgykpimon:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
								<i class="fa fa-trash-o"></i>删除
							</button>
						</shiro:hasPermission>
					</div>
				</div>
				<table id="dg"></table>
			</div>
		</div>
	</div>


	<div id="dlg"></div> 
<div id="icon_dlg"></div>  

<script type="text/javascript">
var dg;
var d;
var menuDg;
var menuId=0;
var parentId;
$(function(){   
	menuDg=$('#menuDg').treegrid({  
	method: "get",
	url:'${ctx}/system/admin/xzqy/xzqyjson', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField: 'text',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	scrollbarSize:0,
	striped:true,
	loadFilter: lazyLoadFilter,
    columns:[[    
        {field:'id',title:'id',hidden:true},    
        {field:'text',title:'名称',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    dataPlain: true,
    onClickRow:function(rowData){
    	$('#layout').layout('panel', 'center').panel({ title: rowData.text+'网格员考核规则'});
     	var code=rowData.id;
    	dg.datagrid('reload',{wgcode:code});
    }
	});
	
	dg=$('#dg').datagrid({   
	method: "get",
	url:'${ctx}/yhpc/wgykpi/content/list',
	fitColumns : true,
	border : false,
	idField : 'id',
    fit : true,
	fitColumns : true,
	striped:true,
	rownumbers:true,
	nowrap: false,
	singleSelect:true,
	scrollbarSize:0,
    columns:[[    
        {field:'id',title:'id',checkbox:true,width:100},    
        {field:'name',title:'评分项目',width:100},
        {field:'score',title:'总分',width:30},
        {field:'content',title:'考核内容',width:200},
        {field:'note',title:'备注',width:100}
    ]],
    toolbar:'#tg_tb',
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
             },
	});
	
});


//弹窗增加
function add() {
	var row = menuDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请先选择网格！",{time: 1000});
		return;
	}
	openDialog("添加网格员考核规则","${ctx}/yhpc/wgykpi/content/create?wgid="+row.s+"&wgname="+row.text,"800px", "300px","");
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
			url:ctx+"/yhpc/wgykpi/content/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
 
}
</script>
</body>
</html>