<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>隐患排查---日常检查信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                <td class="width-15 active"><label class="pull-right">检查日期：</label></td>
                <td class="width-35" ><fmt:formatDate value="${entity.m1}" pattern="yyyy-MM-dd" /></td>
                <td class="width-15 active"><label class="pull-right">严重程度：</label></td>
                <td class="width-35" >${entity.m16 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">辖区部门：</label></td>
                <td class="width-35" >${entity.m3 }</td>
                <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
                <td class="width-35" > ${entity.m2 } </td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">检查类型：</label></td>
                <td class="width-35" >${entity.m6_1 }</td>
                <td class="width-15 active"><label class="pull-right">缺失类型：</label></td>
                <td class="width-35" >${entity.m6_2 }</td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">责任部门主管：</label></td>
                <td class="width-35" >${m8 }</td>
                <td class="width-15 active"><label class="pull-right">计划完成时间：</label></td>
                <td class="width-35" ><fmt:formatDate value="${entity.m10}" pattern="yyyy-MM-dd" /></td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
                <td class="width-85" colspan="3">${entity.m18 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">现场照片：</label></td>
                <td class="width-85" colspan="3">
                    <c:if test="${not empty entity.m4}">
                        <c:forTokens items="${entity.m4}" delims="," var="url" varStatus="urls">
                            <c:set var="urlna" value="${fn:split(url, '||')}" />
                            <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                                <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;"/><br/></a>
                            </div>
                        </c:forTokens>
                    </c:if>
                </td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">缺失情况：</label></td>
                <td class="width-35"  colspan="3" style=" height: 50px;">${entity.m5 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right" style="height: 25px">录入审核：</label></td>
                <td class="width-35" >
                    <c:if test="${entity.shstate=='1' }">通过</c:if>
                    <c:if test="${entity.shstate=='2' }">不通过</c:if>
                </td>
                <td class="width-15 active" ><label class="pull-right">指定整改人：</label></td>
                <td class="width-35" > ${m9 } </td>
            </tr>

	        <c:if test="${entity.shstate =='2' }">
	            <tr>
	                <td class="width-15 active"><label class="pull-right">整改审核反馈：</label></td>
	                <td class="width-35"  colspan="3" style="height: 50px;"> ${entity.m17 } </td>
	            </tr>
	        </c:if>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">解决措施：</label></td>
				  <td class="width-35"  colspan="3" style="height: 50px;">${entity.m7 }</td>
			  </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">实际完成时间：</label></td>
					<td class="width-35" ><fmt:formatDate value="${entity.m11}" pattern="yyyy-MM-dd" /></td>
					<td class="width-15 active"><label class="pull-right">整改费用：</label></td>
					<td class="width-35" >${entity.m15 }</td>
				</tr>
				<tr>
	                <td class="width-15 active"><label class="pull-right">整改后照片：</label></td>
	                <td class="width-85" colspan="3">
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
				  	<td class="width-15 active"><label class="pull-right">整改意见：</label></td>
				  	<td class="width-85" colspan="3">
					  	<c:if test="${entity.m13=='4' }">通过</c:if>
                    	<c:if test="${entity.m13=='3' }">不通过</c:if>
					</td>
			  </tr>
			  <c:if test="${entity.m13 =='3' }">
		      	<tr>
		        	<td class="width-15 active"><label class="pull-right">整改审核反馈：</label></td>
		         	<td class="width-35"  colspan="3" style="height: 50px;"> ${entity.m17 } </td>
		      	</tr>
	          </c:if>
              <tr>
                  <td class="width-15 active"><label class="pull-right">稽核人：</label></td>
                  <td class="width-35" >${entity.m14 }</td>
              </tr>
		</tbody>
	</table>

</body>
</html>