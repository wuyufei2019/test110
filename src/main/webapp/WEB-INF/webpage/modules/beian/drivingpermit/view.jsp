<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>车辆行驶证信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">号牌号码：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m1}</td>
                  <td class="width-15 active"><label class="pull-right">车辆类型：</label></td>
   				  <td class="width-35" >${entity.m2}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">所有人：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m3}</td>
                  <td class="width-15 active"><label class="pull-right">住址：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m4}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">使用性质：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m5}</td>
              	  <td class="width-15 active"><label class="pull-right">品牌型号：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m6}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">车辆识别代码：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m7}</td>
                  <td class="width-15 active"><label class="pull-right">发动机号码：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m8}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">注册日期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m9}" pattern="yyyy-MM-dd"/></td>
               
                  <td class="width-15 active"><label class="pull-right">发证日期：</label></td>
   				  <td class="width-35" style="height: 30px;"><fmt:formatDate value="${entity.m10}" pattern="yyyy-MM-dd"/></td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">有效期：</label></td>
   				  <td class="width-35"  style="height: 30px;"><fmt:formatDate value="${entity.m11}" pattern="yyyy-MM-dd"/></td>
                  <td class="width-15 active"><label class="pull-right">核定载质量(kg)：</label></td>
                  <td class="width-35" style="height: 30px;">${entity.m18}</td>

              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">核定载人数：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m12}</td>
               
                  <td class="width-15 active"><label class="pull-right">总质量(kg)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m13}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">整备质量(kg)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m14}</td>
               
                  <td class="width-15 active"><label class="pull-right">外廓尺寸(mm)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m15}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">扫描件：</label></td>
   				  <td class="width-35"  colspan="3">
				  	  <c:if test="${not empty entity.m17}">
				      <c:forTokens items="${entity.m17}" delims="," var="url" varStatus="urls">
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
   				  <td class="width-35"  colspan="3" style="height: 80px;">${entity.m16}</td>
              </tr> 
		</tbody>
	</table>

</body>
</html>