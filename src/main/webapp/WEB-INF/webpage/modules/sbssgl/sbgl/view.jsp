<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-25 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">${sbgl.qyname }</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">部门名称：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">${sbgl.deptname }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m1 }</td>
					<td class="width-25 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m2 }</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-25" style="height: 30px;">
						<c:choose>
							<c:when test="${sbgl.m20 == '0'}">A类</c:when>
							<c:when test="${sbgl.m20 == '1'}">B类</c:when>
							<c:when test="${sbgl.m20 == '2'}">C类</c:when>
							<c:otherwise>
								${sbgl.m20}
							</c:otherwise>
						</c:choose>
					</td>
					<%-- <td class="width-25 active"><label class="pull-right">是否是特种设备：</label></td>
					<td class="width-25" >
							<c:if test="${sbgl.m21 == '0'}">否</c:if>
							<c:if test="${sbgl.m21 == '1'}">是</c:if>
					</td> --%>
				</tr>
			<c:if test="${sbtype eq '1'}">
				<tr>
					<td class="width-25 active"><label class="pull-right">注册登记日期：</label></td>
					<td class="width-25" style="height: 30px;"><fmt:formatDate value="${sbgl.m28 }" pattern="yyyy-MM-dd"/></td>
					<td class="width-25 active"><label class="pull-right">状态：</label></td>
					<td class="width-25" style="height: 30px;">
						<c:if test="${sbgl.m29 eq '0'}">不完好</c:if>
						<c:if test="${sbgl.m29 eq '1'}">完好</c:if>
					</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">本次检验日期：</label></td>
					<td class="width-25" style="height: 30px;"><fmt:formatDate value="${sbgl.m31 }" pattern="yyyy-MM-dd"/></td>
					<td class="width-25 active"><label class="pull-right">下次检验日期：</label></td>
					<td class="width-25" style="height: 30px;"><fmt:formatDate value="${sbgl.m32 }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:if> 
				<tr>
					<td class="width-25 active"><label class="pull-right">规格：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m3 }</td>
					<td class="width-25 active"><label class="pull-right">型号：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m27 }</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">制造单位：</label></td>
					<td class="width-25" colspan="3" style="height: 30px;">${sbgl.m5 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m4 }</td>
					<td class="width-25 active"><label class="pull-right">电气功率：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m10 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">外形尺寸：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m13 }</td>
					<td class="width-25 active"><label class="pull-right">加工精度：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m33 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">加工范围：</label></td>
					<td class="width-75" style="height: 30px;" colspan="3">${sbgl.m14 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">设备重量：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m15 }</td>
					<td class="width-25 active"><label class="pull-right">启用时间：</label></td>
					<td class="width-25" style="height: 30px;"><fmt:formatDate pattern="yyyy-MM-dd" value="${sbgl.m16 }" /></td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">复杂系数：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m34 }</td>
					<td class="width-25 active"><label class="pull-right">原值：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.m35 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">安装单位：</label></td>
					<td class="width-25" colspan="3" style="height: 30px;">${sbgl.m30 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">安装地点：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">${sbgl.m8 }</td>
				</tr>
               <tr>
                     <td class="width-25 active" rowspan="2"><label class="pull-right">设备照片：</label></td>
      				 <td class="width-25">
						<strong><center>设备正面照片</center></strong>
      				 </td>
      				 <td class="width-25">
						<strong><center>设备侧面照片</center></strong>
      				 </td>
      				 <td class="width-25">
						<strong><center>设备铭牌照片</center></strong>
      				 </td>
                </tr>
                <tr>
                	<td class="width-25">
                		<c:choose>
							<c:when test="${not empty sbgl.m22}">
							 	<c:set var="urlna" value="${fn:split(sbgl.m22, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" width="190px" height="150px;"/><br/></a>
							 	</div>
							 </c:when>
							 <c:otherwise>
							 	/
							 </c:otherwise>
						 </c:choose>
      				 </td>
      				 <td class="width-25">
      				 	<c:choose>
							<c:when test="${not empty sbgl.m24}">
							 	<c:set var="urlna" value="${fn:split(sbgl.m24, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" width="190px" height="150px;"/><br/></a>
							 	</div>
						 	</c:when>
						 	<c:otherwise>
						 		/
						 	</c:otherwise>
						 </c:choose>
      				 </td>
      				 <td class="width-25">
      				 	<c:choose>
							<c:when test="${not empty sbgl.m25}">
							 	<c:set var="urlna" value="${fn:split(sbgl.m25, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" width="190px" height="150px;"/><br/></a>
							 	</div>
							 </c:when>
							 <c:otherwise>
							 	/
							 </c:otherwise>
					 	</c:choose>
      				 </td>
                </tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">备注：</label></td>
					<td class="width-75" colspan="3" style="height: 60px;">${sbgl.m18 }</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">保管人：</label></td>
					<td class="width-25" style="height: 30px;">${sbgl.bgrname }</td>
				</tr>     
			</tbody>
		</table>
	</form>
</body>
</html>