<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业风险分级管控管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxdxx/index.js?v=1.4"></script>
	<script type="text/javascript">
	   var dg;
	   var d;
	   var zz = 1;
	   var f='${sys}';
	   var fxdj='${fxdj}';
	   $(function() {
		   var url = ctx + '/fxgk/fxxx/ajlist';
		   dg = $('#fxgk_fxxx_dg').datagrid(
				   {
					   method : "post",
					   url : url,
					   fit : true,
					   fitColumns : true,
					   border : false,
					   idField : 'id',
					   striped : true,
					   pagination : true,
					   rownumbers : true,
					   nowrap : false,
					   pageNumber : 1,
					   pageSize : 50,
					   pageList : [ 50, 100, 150, 200, 250 ],
					   scrollbarSize : 5,
					   singleSelect : true,
					   striped : true,
					   columns : [ [
						   {field:'m1',title:'风险点名称',sortable:false,width:100,align:'center'},
						   {field:'m2',title:'风险类别',sortable:false,width:100,align:'center'},
						   {field:'m9',title:'风险分级',sortable:false,width:100,align:'center',
							   formatter : function(value, row, index){
								   if(value=='1') return value='红';
								   else if(value=='2') return value='橙';
								   else if(value=='3') return value='黄';
								   else if(value=='4') return value='蓝';
							   },
							   styler : function(value, row, index){
								   if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
								   else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
								   else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
								   else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E';
							   }

						   },
						   {field:'fjgk1',title:'公司',sortable:false,width:100,align:'center'},
						   {field:'fjgk2',title:'部门',sortable:false,width:100,align:'center'},
						   {field:'fjgk3',title:'班组',sortable:false,width:100,align:'center'},
						   {field:'fjgk4',title:'岗位',sortable:false,width:100,align:'center'}
					   ] ],
					   onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					   		view();
					   },
					   onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
					   },
					   checkOnSelect : false,
					   selectOnCheck : false,
					   toolbar : '#fxgk_fxxx_tb'
				   });
		});
	   
	</script>
</head>
<body >
	<%--<div class="easyui-tabs" fit="true">
		<div title="风险点分布图" style="height:100%;" data-options="" id="">
			<iframe frameborder="0" src="${ctx}/fxgk/fxfb/index" style="width: 100%;height: 99%;"></iframe>
		</div>--%>
		<div title="风险分级管控清单" style="height:100%;" data-options="" id="">
			<div class="easyui-layout" style="height:100%; ">
				<div id="fxgk_fxxx_tb" style="padding:5px;height:auto">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
						<div class="form-group">
							<input type="text" id="fxgk_fxxx_name" name="fxgk_fxxx_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '风险点名称'" />
							<input type="text" name="fxgk_fxxx_fxlb" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险类别',
									editable:true ,panelHeight:'auto',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/fxfl'" />
							<input type="text" id="fxgk_fxxx_fxfj"name="fxgk_fxxx_fxfj" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险分级',panelHeight:'auto',
									 data: [{value:'1',text:'红'},
											{value:'2',text:'橙'},
											{value:'3',text:'黄'},
											{value:'4',text:'蓝'}]" />
							<input  type="hidden" id="panduan" name="panduan" value="1"/>
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
							<span class="btn btn-primary btn-rounded btn-outline btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</span>
						</div>
					</form>
				</div>

				<table id="fxgk_fxxx_dg"></table>
			</div>
		</div>
	<%--</div>--%>
<!-- 工具栏 -->


</body>
</html>