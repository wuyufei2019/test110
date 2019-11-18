<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>hazop风险评估管理</title>
<meta name="decorator" content="default" />
</head>
<body>
      <table id="rwtable" class="table table-bordered">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析题目：</label></td>
               <td class="width-35" colspan="3">${entity.question }</td>
            </tr>
            <tr>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">图纸编号：</label></td>
               <td class="width-35">${entity.drawingnumber }
               <td class="width-15 active"><label class="pull-right">修订号：</label></td>
               <td class="width-35">${entity.revision }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析日期：</label></td>
               <td class="width-35"><fmt:formatDate value="${entity.analysistime}" pattern="yyyy-MM-dd"  /></td>
               <td class="width-15 active"><label class="pull-right">会议日期：</label></td>
               <td class="width-35"><fmt:formatDate value="${entity.meetingtime}" pattern="yyyy-MM-dd"  /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">小组成员：</label></td>
               <td class="width-35" colspan="3">${entity.member }</td>
            </tr>
            
            <tr>
               <td class="width-15 active"><label class="pull-right">分析部分：</label></td>
               <td class="width-35" colspan="3">${entity.analysispart }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">物料：</label></td>
               <td class="width-35">${entity.material }</td>
               <td class="width-15 active"><label class="pull-right">功能：</label></td>
               <td class="width-35">${entity.function }</td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">来源：</label></td>
               <td class="width-35">${entity.source }</td>
               <td class="width-15 active"><label class="pull-right">目的地：</label></td>
               <td class="width-35">${entity.purpose }</td>
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
						url : ctx + "/fxpg/hazop/actionlist/" + '${entity.ID}',
						fitColumns : true,
						border : true,
						striped : true,
						rownumbers : false,
						nowrap : false,
						idField : 'id',
						scrollbarSize : 0,
						singleSelect : true,
						striped : true,
						columns : [ [  {
							field : 'guidanceword',
							title : '引导词',
							sortable : false,
							width : 60
						}, {
							field : 'factor',
							title : '要素',
							sortable : false,
							width : 60
						}, {
							field : 'deviation',
							title : '偏差',
							sortable : false,
							width : 60
						}, {
							field : 'possiblereason',
							title : '可能原因',
							sortable : false,
							width : 100
						}, {
							field : 'result',
							title : '后果',
							sortable : false,
							width : 50
						}, {
							field : 'safetymeasure',
							title : '安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'note',
							title : '注释',
							sortable : false,
							width : 50
						}, {
							field : 'suggestion',
							title : '建议安全措施',
							sortable : false,
							width : 100
						}, {
							field : 'executor',
							title : '执行人',
							sortable : false,
							width : 50
						}
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