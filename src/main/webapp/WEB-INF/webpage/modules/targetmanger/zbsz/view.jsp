<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>指标信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
             <shiro:hasAnyRoles name="admin,superadmin">
               <tr>
                  <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-80" colspan="3">
                     <input value="${target.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                           class="easyui-combobox"  data-options="required:'true',valueField: 'id',
                            readonly:'true',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </td>
               </tr>
            </shiro:hasAnyRoles>
				<tr>
					<td class="width-20 active"><label class="pull-right">指标名称：</label></td>
					<td class="width-80">${target.m1 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">指标值：</label></td>
					<td class="width-80">${target.m2 }</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80">${target.m3 }</td>					
				</tr>
			</table>
</body>
</html>