<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标分配管理</title>
<meta name="decorator" content="default" />
</head>
<body>

      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <shiro:hasAnyRoles name="admin,superadmin">
               <tr>
                  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-35" colspan="3"><input value="${target.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                        class="easyui-combobox"
                        data-options="required:'true',valueField: 'id',
                            <c:if test="${action eq 'update' }">readonly:'true',</c:if>
                           textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </td>
               </tr>
            </shiro:hasAnyRoles>
            <tr>
               <td class="width-15 active"><label class="pull-right">年份：</label></td>
               <td class="width-35">${target.year }</td>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35"><input type="text" value="${target.departments }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="readonly:true,valueField:'id',textField:'text',multiple: true,
                                    url: '${ctx}/system/department/deptjson'"></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3" style="height: 50px">${target.note }</td>
            </tr>
           <tr>
               <td class="width-15 active"><label class="pull-right">安全目标责任书：</label></td>
               <td class="width-35" colspan="3">
                <c:if test="${not empty target.url}">
                <c:forTokens items="${target.url}" delims="," var="url" varStatus="urls">
                  <c:set var="urlna" value="${fn:split(url, '||')}" />
                  <div style="margin: 5px;"> 
                        <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
                     </div>
                </c:forTokens>
                </c:if>
               </td>
            </tr>

         </tbody>
      </table>
</body>

</html>