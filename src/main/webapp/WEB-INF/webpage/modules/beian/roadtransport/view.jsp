<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>道路运输证信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">业户名称：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m1}</td>
                  <td class="width-15 active"><label class="pull-right">地址：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m2}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">车辆号牌：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m3}</td>
                  <td class="width-15 active"><label class="pull-right">道路运输证号：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m4}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">车辆类型：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m5}</td>
                  <td class="width-15 active"><label class="pull-right">吨(座)位：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m6}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">车辆(毫米)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m7}</td>
                  <td class="width-15 active"><label class="pull-right">经营范围：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m8}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">发证日期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m9}" pattern="yyyy-MM-dd"/></td>
                  <td class="width-15 active"><label class="pull-right">有效期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m10}" pattern="yyyy-MM-dd"/></td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">扫描件：</label></td>
   				  <td class="width-35"  colspan="3">
				  	  <c:if test="${not empty entity.m12}">
				      <c:forTokens items="${entity.m12}" delims="," var="url" varStatus="urls">
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
   				  <td class="width-35"  colspan="3" style="height: 80px;">${entity.m11}</td>
              </tr> 
		</tbody>
	</table>

</body>
</html>