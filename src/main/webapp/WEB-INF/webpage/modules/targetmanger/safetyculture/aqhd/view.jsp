<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责管理</title>
<meta name="decorator" content="default" />
</head>
<body>

      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">活动名称：</label></td>
               <td class="width-35">${target.name }</td>
               <td class="width-15 active"><label class="pull-right">时间：</label></td>
               <td class="width-35" ><fmt:formatDate value="${target.time }" pattern="yyyy-MM-dd"  /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">地点：</label></td>
               <td class="width-35" colspan="3">${target.address }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">召集部门：</label></td>
               <td class="width-35"><input value="${target.ID2 }"class="easyui-combobox"  style="width:100%;"
               data-options="readonly:true,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
               <td class="width-15 active"><label class="pull-right">召集人：</label></td>
               <td class="width-35" >${target.gatherper }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">参加部门：</label></td>
               <td class="width-35"><input value="${target.attenddeps }"class="easyui-combobox"  style="width:100%;"
               data-options="readonly:true,multiple:true,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
               <td class="width-15 active"><label class="pull-right">活动级别：</label></td>
               <td class="width-35" ><input class="easyui-combobox" value="${target.actiontlev }"
                   style="width:100%;" data-options="readonly:true, valueField:'value',textField:'text',
                   data:[{value:1,text:'公司'},{value:2,text:'部门'},{value:3,text:'班组'}]"> </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">记录人：</label></td>
               <td class="width-35">${target.recordper }</td>
               <td class="width-15 active"><label class="pull-right">状态：</label></td>
               <td class="width-35" ><input  class="easyui-combobox" value="${target.state }"
                   style="width:100%;"data-options="readonly:true, valueField:'value',textField:'text',
                   data:[{value:1,text:'待开'},{value:2,text:'推迟'},{value:3,text:'结束'}]"> </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">活动照片：</label></td>
               <td class="width-35"colspan="3">
                <c:if test="${not empty target.url}">
                <c:forTokens items="${target.url}" delims="," var="url" varStatus="urls">
                  <c:set var="urlna" value="${fn:split(url, '||')}" />
                  <div style="margin: 5px;"> 
                        <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}" title="${urlna[1]}">
                        	<img alt="${urlna[1]}"  src="${urlna[0]}" style="max-height: 300px;max-width: 200px;"><br>
                        </a>
                     </div>
                </c:forTokens>
                </c:if>
               </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">其他材料：</label></td>
               <td class="width-35"colspan="3">
                <c:if test="${not empty target.otherurl}">
                <c:forTokens items="${target.otherurl}" delims="," var="url" varStatus="urls">
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