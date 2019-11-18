<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业数据总览</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/ckplayer/ckplayer.js"></script>
	<style>
		.tree-title {
			font-size: 14px;
		}
		#bis_index_searchFrom span.textbox.combo{
			margin: 8px;
		}
	</style>
</head>
<body>
<div class="easyui-layout" style="height:100%;">
	<div data-options="region:'west',split:true,border:false" style="width: 300px">
		<form id="bis_index_searchFrom" style="" class="form-inline">
			<input type="text" id="qyname" name="qyname" class="easyui-combobox" style="width: 280px;height: 30px;margin:8px" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/zxjkyj/zdwxyaj/zdwxydjjson',prompt: '企业名称' "/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " style="margin:0 5px 0 8px" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</span>
		</form> 	
		<div id="tg_tb2" style="padding:5px;height:auto">
		</div>
		<table id="qydg"></table>
	</div>
	<div data-options="region:'center',split:true,border:false">
		<div id="playlist" style="width: 100%;height: 100%;margin: 0 auto;">
			<iframe id="myIframe" src="" style="display: none;width: 100%;height: 100%;"></iframe>
		</div>
	</div>
</div>

<script type="text/javascript">
var qysum=${qysum};
var qyid='${qyid}';

var qydg;
var player;
var d;
var oldid=0;
$(function(){
	qydg=$('#qydg').treegrid({
		method: "post",
		url:ctx+'/zxjkyj/zdwxyaj/json',
		queryParams: {qyid:qyid},
		fit : true,
		fitColumns : true,
		border : true,
		idField : 'id',
		treeField : 'name',
		parentField : 'pid',
		iconCls: 'm2',
		animate:true,
		rownumbers:true,
		singleSelect:true,
		scrollbarSize:0,
		striped:true,
		columns:[[
			{field:'id',title:'id',hidden:true,width:100},
			{field:'pid',title:'pid',hidden:true,width:100},
			{field:'name',title:'企业('+qysum+'家)',sortable:false,width:100}
		]],
		enableHeaderClickMenu: false,
		enableHeaderContextMenu: false,
		enableRowContextMenu: false,
		dataPlain: true,
		onClickRow:function(row){
			$('#myIframe').show();
			$('#myIframe').prop('src', '${ctx}/hgqyIndex/zdwxyhome?qyid='+row.id);
			var el_name = row.name;
			$("tr[node-id="+el_name+"]").find("span.tree-hit").trigger("click");
		},
		onLoadSuccess: function (row, data)
		{
			/*$.each(data, function (i, val) { qydg.treegrid('collapseAll', data[i].id)})*/
		}
	});

});

function search(){
	var qyname=$("#qyname").combobox("getValue");
	//window.location.href='${ctx}/zxjkyj/spjk/index?qyname='+qyname;
	var obj=$("#bis_index_searchFrom").serializeObject();
	qydg.treegrid('load',obj);
}

//清空
function reset(){
	$("#bis_index_searchFrom").form("clear");
	search();
}

</script>
</body>
</html>