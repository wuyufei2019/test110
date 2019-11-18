<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责管理</title>
<meta name="decorator" content="default" />
</head>
<body>
      <table readonly="true" class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">会议信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">会议主题:</label></td>
               <td class="width-35" colspan="3">${target.theme }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">会议开始时间:</label></td>
               <td class="width-35"><fmt:formatDate value="${target.time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
               <td class="width-15 active"><label class="pull-right">状态:</label></td>
               <td class="width-35"><input class="easyui-combobox" value="${target.state }" style="width:100%;height: 30px;"
                     data-options="readonly:true, valueField:'value',textField:'text',data:[{value:1,text:'待开'},{value:2,text:'推迟'},{value:3,text:'结束'},]" />
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">召集部门:</label></td>
               <td class="width-35"><input  type="text" value="${target.ID2 }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="readonly:true,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'" /></td>

               <td class="width-15 active"><label class="pull-right">主持人:</label></td>
               <td class="width-35">${target.hostess }</td>

            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">参加部门:</label></td>
               <td class="width-35"><input type="text" value="${target.attenddeps }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="readonly:true,multiple:true,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'"></td>
               <td class="width-15 active"><label class="pull-right">会议类型:</label></td>
               <td class="width-35"><input  class="easyui-combobox" value="${target.type }" style="width:100%;height: 30px;"
                     data-options="readonly:true,valueField:'value',textField:'text',
                   data:[{value:1,text:'安委会例会'},{value:2,text:'部门例会'},{value:3,text:'临时会议'},{value:4,text:'其他会议'}]" />
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">地点:</label></td>
               <td class="width-35" colspan="3">${target.address }</td>
            </tr>
        
         </tbody>
      </table>
      <c:if test="${target.state eq 3 }">
      <table id="table" class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
             <tr>
               <td class="width-15 active" align="center" colspan="4">会议记录</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">会议记录:</label></td>
               <td class="width-85" >${target.content }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">会议督办事项:</label></td>
               <td class="width-85" >${target.note }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">其他材料：</label></td>
               <td class="width-35"colspan="3">
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
            <tr>
               <td class="width-15 active"><label class="pull-right">记录人:</label></td>
               <td class="width-35">${target.recordper }</td>
            </tr>
              </tbody>
      </table>
      </c:if>
      <c:if test="${not empty target.feedback}">
         <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
           <tr>
               <td class="width-15 active" align="center" colspan="4">会议督办事项反馈</td>
            </tr>
               <tr>
                  <td class="width-15 active"><label class="pull-right">督办事项反馈:</label></td>
                  <td class="width-85">${target.feedback }</td>
               </tr>
         </table>
      </c:if>
 
         
</body>

</html>