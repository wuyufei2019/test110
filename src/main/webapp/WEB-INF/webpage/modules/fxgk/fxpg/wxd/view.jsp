<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品仓库管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <table id="rwtable" class="table table-bordered">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35" colspan="3"><input id="deptid" name="deptid" type="text" value="${entity.deptid }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'">
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">分析人：</label></td>
               <td class="width-35">${entity.analysisper }</td>
               <td class="width-15 active"><label class="pull-right">分析时间：</label></td>
               <td class="width-35"><fmt:formatDate value="${entity.analysistime}" pattern="yyyy-MM-dd"  /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">审核人：</label></td>
               <td class="width-35">${entity.reviewer }</td>
               <td class="width-15 active"><label class="pull-right">审定人：</label></td>
               <td class="width-35">${entity.auditor }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">单元名称：</label></td>
               <td class="width-35" colspan="3">${entity.unit }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">涉及物料：</label></td>
               <td class="width-35" colspan="3">${entity.material }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">物质分值：</label></td>
               <td class="width-35" colspan="3">${entity.matter }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">容量分值：</label></td>
               <td class="width-35">${entity.capacity }</td>
               <td class="width-15 active"><label class="pull-right">温度分值：</label></td>
               <td class="width-35">${entity.temperature }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">压力分值：</label></td>
               <td class="width-35">${entity.pressure }</td>
               <td class="width-15 active"><label class="pull-right">操作分值：</label></td>
               <td class="width-35">${entity.operation }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">风险值：</label></td>
               <td class="width-35">${entity.riskvalue }</td>
               <td class="width-15 active"><label class="pull-right">风险等级：</label></td>
               <td class="width-35">${entity.risklevel }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">改进措施：</label></td>
               <td class="width-35" colspan="3">${entity.improvemeasure }</td>
            </tr>

         </tbody>
      </table>
</body>
</html>