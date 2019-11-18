<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查人添加</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div id="dg_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" class="form-horizontal">
			<div class="form-group">
				<input name="yhpc_jcrw_bm" id="yhpc_jcrw_bm" style="height: 30px;" class="easyui-combobox" 
								data-options="prompt:'部门',panelHeight:'100',valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson'" />
				<input name="yhpc_jcrw_jcr" id="yhpc_jcrw_jcr" style="height: 30px;" class="easyui-textbox" 
								data-options="prompt:'检查人',panelHeight:'100'" />
				<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
				<input type="hidden" id="depids" name="depids" value="${depids}" />
			</div>
		</form>
	</div>
	<table id="yhpc_jcr_dg"></table>
	
	<script type="text/javascript">
var dg;
$(function() {
	dg = $('#yhpc_jcr_dg').datagrid(
			{
				method : "get",
				url : ctx + '/yhpc/jcrw/jcrlist?depids=${depids}',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : false,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 10000,
				pageList : [ 10000 ],
				scrollbarSize : 5,
				singleSelect : false,
				columns : [ [
				             {field:'id',title:'id',checkbox:true,width:50,align:'center'},
				             {field:'deptname',title:'部门',width:60,align:'center',
				            	 formatter: function(value){
				            		 if (value == null) {
				            			 return "全部";
				            		 } else {
				            			 return value;
				            		 }
				            	 }
				             },
				             {field:'text',title:'检查人',width:60,align:'center'}
				          ] ],
				checkOnSelect : true,
				selectOnCheck : true,
				onLoadSuccess: function(rowdata, rowindex, rowDomElement){
					var ids = '${jcrids}';
					$.each(rowdata.rows,function(i,row){
						if(ids.indexOf(row.id)!=-1){
							dg.datagrid('selectRow',i);
						}
					});
			    },
				toolbar : '#dg_tb'     
			});
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
</script>
</body>
</html>