<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>驾驶证信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">驾驶证号：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m1}</td>
                  <td class="width-15 active"><label class="pull-right">姓名：</label></td>
   				  <td class="width-35" >${entity.m2}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">性别：</label></td>
   				  <td class="width-35" style="height: 30px;">
   				  	  <c:if test="${entity.m3 == '1'}">男</c:if>
   				  	  <c:if test="${entity.m3 == '2'}">女</c:if>
   				  </td>
                  <td class="width-15 active"><label class="pull-right">国籍：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m4}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">住址：</label></td>
   				  <td class="width-35"  colspan="3" style="height: 30px;">${entity.m5}</td>
              </tr> 
              
              <tr>
                  <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
   				  <td class="width-35"  colspan="3" style="height: 30px;">${entity.m12}</td>
              </tr>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">出生日期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m6}" pattern="yyyy-MM-dd"/></td>
              	  <td class="width-15 active"><label class="pull-right">初次领证日期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m7}" pattern="yyyy-MM-dd"/></td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">准驾车型：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m8}</td>
                  <td class="width-15 active"><label class="pull-right">有效期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m9}" pattern="yyyy-MM-dd"/></td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">扫描件：</label></td>
   				  <td class="width-35"  colspan="3">
				  	  <c:if test="${not empty entity.m11}">
				      <c:forTokens items="${entity.m11}" delims="," var="url" varStatus="urls">
				 	  	  <c:set var="urlna" value="${fn:split(url, '||')}" />
				 	  	  <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
				 		  	  <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;"/><br/></a>
				 	      </div>
				      </c:forTokens>
				      </c:if>
				  </td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">备注：</label></td>
   				  <td class="width-35"  colspan="3" style="height: 80px;">${entity.m10}</td>
              </tr> 
		</tbody>
	</table>

</body>
</html>