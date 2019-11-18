<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				
				<tr>
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35"  colspan="3">${yle.number }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案由：</label></td>
                 	<td class="width-30" colspan="3" >${yle.ayname }</td>					

				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">案件来源:</label></td>
					<td class="width-30"  >
					${yle.casesource }
					</td>
					<td class="width-20 active"><label class="pull-right">立案时间：</label></td>
					<td class="width-30">
					
					<fmt:formatDate value="${yle.filldate }" pattern="yyyy-MM-dd"/>
					</td>	
				</tr>
		        <tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3">${yle.casename }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >
					${yle.dsperson }
					</td>
					
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" >${yle.contact }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">法定负责人/代表人：</label></td>
					<td class="width-30"  >${yle.legalperson }</td>
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">
					${yle.ybcode }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" colspan="3">${yle.dsaddress }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件基本状况：</label></td>
					<td class="width-30" colspan="3">${ajqk }</td>
				</tr>
					<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3">${yle.opinion }</td>
				</tr>
				<tr>
					<td class="width-20 active" rowspan="2"><label class="pull-right">承办人：</label></td>
					<td class="width-30" style="height:25px" >${yle.enforcer1 }</td>
					<td class="width-20 active" rowspan="2"><label class="pull-right">证号：</label></td>
					<td class="width-30" style="height:25px">
					${yle.identity1 }</td>
				</tr>
				<tr>
					<td class="width-30 "  style="height:25px">${yle.enforcer2 }</td>
					<td class="width-30" style="height:25px">
					${yle.identity2 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30">
					
					<fmt:formatDate value="${yle.cbsj }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${yle.fzshyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="fzshyj" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" checked="checked" disabled />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="fzshyj" checked="checked" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核人：</label></td>
					<td class="width-30">${yle.fzshr }</td>
					<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${yle.fzshsj }" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${yle.shyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="shyj" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" checked="checked" disabled />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="shyj" checked="checked" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30">${yle.shr }</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${yle.shsj }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px">
						<c:choose>
						<c:when test="${yle.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" disabled/>同意
							<input type="radio" value="0" class="i-checks"  name="spyj" disabled checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" disabled checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	

				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30">${yle.spr }</td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${yle.spsj }" /></td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审批表附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty yle.url}">
					 <c:forTokens items="${yle.url}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
		</table>
	</div>
</body>
</html>