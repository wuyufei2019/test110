<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险废物产生记录</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
      <tbody>
         <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35">${entity.year }</td>
               <td class="width-15 active"><label class="pull-right">填报人：</label></td>
               <td class="width-35">${entity.informant }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
               <td class="width-35" >${entity.phone }</td>
               <td class="width-15 active"><label class="pull-right">填报日期：</label></td>
               <td class="width-35" ><fmt:formatDate value="${entity.recordtime}" pattern="yyyy-MM-dd"  /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">单位负责人：</label></td>
               <td class="width-35">${entity.principal }</td>
            </tr>
      </tbody>
   </table>
   <div class="easyui-accordion" id="accordion" border="false">
      <div title="危险废物产生记录附表" data-options="selected:true" style="padding:10px;">
         <table id="detail_dg"></table>
      </div>
   </div>
   <script type="text/javascript">
				$(function() {
					var d = $('#detail_dg').datagrid({
						url : ctx + "/hjbh/wxfw/detaillist/" + '${entity.ID}',
						fitColumns : true,
						border : true,
						striped : true,
						rownumbers : false,
						nowrap : false,
						idField : 'id',
						scrollbarSize : 0,
						singleSelect : true,
						striped : true,
						columns : [ [
				            {field : 'name',title : '废物描述/名称',sortable : false,width : 100}, 
				  			{field : 'kind',title : '废物类别',sortable : false,width : 100}, 
							{field : 'resource',title : '产生源/车间',sortable : false,width : 100}, 
							{field : 'unit',title : '计量单位',sortable : false,width : 100}, 
							{field : 'direction',title : '废物流向',sortable : false,width : 100}, 
							{field : 'm1',title : '外单位处置的企业',sortable : false,width : 100}, 
							{field : 'm2',title : '内部利用处理量',sortable : false,width : 100}, 
							{field : 'm3',title : '委托利用处理量',sortable : false,width : 100}, 
							{field : 'm4',title : '累计贮存量',sortable : false,width : 100}, 
							{field : 'm5',title : '年度产生量',sortable : false,width : 100}
						] ],
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false
					});
				});
			</script>
</body>
</html>