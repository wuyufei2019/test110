<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>scl风险评估</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
      <tbody>
         <tr>
            <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
            <td class="width-35"><input value="${entity.deptid }" class="easyui-combobox" style="width: 100%;height: 30px;"
                  data-options="readonly:true,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'">

            <td class="width-15 active"><label class="pull-right">工段：</label></td>
            <td class="width-35" colspan="3">${entity.m1 }</td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">装置/设备：</label></td>
            <td class="width-35" colspan="3">${entity.equipment }</td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">分析人：</label></td>
            <td class="width-35">${entity.analysisper }</td>
            <td class="width-15 active"><label class="pull-right">分析时间：</label></td>
            <td class="width-35"> <fmt:formatDate value="${entity.analysistime}" pattern="yyyy-MM-dd"  /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">审核人：</label></td>
            <td class="width-35">${entity.reviewer }</td>
            <td class="width-15 active"><label class="pull-right">审定人：</label></td>
            <td class="width-35">${entity.auditor }</td>
         </tr>
      </tbody>
   </table>
   <div class="easyui-accordion" id="accordion" border="false">
      <div title="风险活动" data-options="selected:true" style="padding:10px;">
         <table id="fxhd_dg"></table>
      </div>
   </div>
   <script type="text/javascript">
				$(function() {
					var d = $('#fxhd_dg').datagrid({
						url : ctx + "/fxpg/scl/actionlist/" + '${entity.ID}',
						fitColumns : true,
						border : true,
						striped : true,
						rownumbers : false,
						nowrap : false,
						idField : 'id',
						scrollbarSize : 0,
						singleSelect : true,
						striped : true,
						columns : [ [ {
							field : 'project',
							title : '检查项目',
							sortable : false,
							width : 100
						}, {
							field : 'standard',
							title : '检查标准',
							sortable : false,
							width : 100
						}, {
							field : 'mainresult',
							title : '主要后果',
							sortable : false,
							width : 100
						}, {
							field : 'safetymeasure',
							title : '现有安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'possibility',
							title : '可能性(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'severity',
							title : '严重度(等级)',
							sortable : false,
							width : 50
						}, {
							field : 'riskvalue',
							title : '风险值',
							sortable : false,
							width : 50
						}, {
							field : 'risklevel',
							title : '风险等级',
							sortable : false,
							width : 50
						}, {
							field : 'improvemeasure',
							title : '改进措施',
							sortable : false,
							width : 100
						} ] ],
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false
					});
				});
			</script>
</body>
</html>