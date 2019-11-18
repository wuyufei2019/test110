<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备验收单</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${sbgl.qyname }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >${sbgl.m1 }</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" >${sbgl.m2 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-30" >
						<c:choose>
							<c:when test="${sbgl.m20 == '0'}">A类</c:when>
							<c:when test="${sbgl.m20 == '1'}">B类</c:when>
							<c:when test="${sbgl.m20 == '2'}">C类</c:when>
							<c:otherwise>
								${sbgl.m20}
							</c:otherwise>
						</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">是否是特种设备：</label></td>
					<td class="width-30" >
							<c:if test="${sbgl.m21 == '0'}">否</c:if>
							<c:if test="${sbgl.m21 == '1'}">是</c:if>
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" >${sbgl.m3 }</td>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >${sbgl.m4 }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备制造商：</label></td>
					<td class="width-30" >${sbgl.m5 }</td>
					<td class="width-20 active"><label class="pull-right">购置日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbgl.m6 }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">价格：</label></td>
					<td class="width-30" >${sbgl.m7 }</td>
					<td class="width-20 active"><label class="pull-right">系列号：</label></td>
					<td class="width-30" >${sbgl.m9 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">放置地点：</label></td>
					<td class="width-80" colspan="3">${sbgl.m8 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">电气功率(30KVA)：</label></td>
					<td class="width-30" >${sbgl.m10 }</td>
					<td class="width-20 active"><label class="pull-right">用气量(m3/min)：</label></td>
					<td class="width-30" >${sbgl.m11 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">用水量：</label></td>
					<td class="width-30" >${sbgl.m12 }</td>
					<td class="width-20 active"><label class="pull-right">外形尺寸：</label></td>
					<td class="width-30" >${sbgl.m13 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">加工范围：</label></td>
					<td class="width-30" >${sbgl.m14 }</td>
					<td class="width-20 active"><label class="pull-right">重量：</label></td>
					<td class="width-30" >${sbgl.m15 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">启用日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbgl.m16 }" /></td>
					<td class="width-20 active"><label class="pull-right">固定资产编号：</label></td>
					<td class="width-30" >${sbgl.m17 }</td>
				</tr> 
				<tr> 
                  <td class="width-15 active"><label class="pull-right">上传图片：</label></td>
   				  <td class="width-35" colspan="3">
   				  	  <c:if test="${not empty sbgl.m22}">
					 	<c:set var="urlna" value="${fn:split(sbgl.m22, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;"/><br/></a>
					 	</div>
					 </c:if>
   				  </td>
              </tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">${sbgl.m18 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">保管人：</label></td>
					<td class="width-30" >${sbgl.bgrname }</td>
				</tr>     
			</tbody>
		</table>
	</form>
</body>
</html>