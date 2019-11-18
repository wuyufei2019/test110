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
               <td class="width-15 active"><label class="pull-right">年度a：</label></td>
               <td class="width-35">${target.year }</td>
               <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-35" >${target.post }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">姓名：</label></td>
               <td class="width-35">${target.pername }</td>
               <td class="width-15 active"><label class="pull-right">电话：</label></td>
               <td class="width-35">${target.phone }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">承诺书附件：</label></td>
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
         </tbody>
      </table>
</body>
</html>