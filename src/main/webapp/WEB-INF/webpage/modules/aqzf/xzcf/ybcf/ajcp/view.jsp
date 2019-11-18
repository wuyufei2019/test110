<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加安监呈批记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  colspan="3">${jce.number }</td>
				</tr>
				<tr>
						<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
						<td class="width-30" colspan="3">
							${jce.casename }
						</td>
				</tr>
                    <c:if test="${jce.percomflag eq 1 }">
				<tr >
					<td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
					<td class="width-30">${jce.punishname }</td>
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-30" >${jce.qyaddress }</td>
				
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-30">${jce.legal }</td>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" >${jce.duty }</td>
				
				</tr>
				
				  <tr >
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">
					${jce.qyyb }</td>
				</tr>
                    </c:if>
                    <c:if test="${jce.percomflag eq 0 }">
				<tr >
					<td class="width-20 active"><label class="pull-right">被处罚人：</label></td>
					<td class="width-30 ">${jce.punishname }</td>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" >${jce.mobile }</td>
				
				</tr>
				
					<tr >
					<td class="width-20 active"><label class="pull-right">年龄：</label></td>
					<td class="width-30" >${jce.age }</td>
					<td class="width-20 active"><label class="pull-right">性别：</label></td>
					<td class="width-30">${jce.sex }</td>	
				</tr>
					<tr >
					<td class="width-20 active"><label class="pull-right">所在单位：</label></td>
					<td class="width-30" colspan="3" >${jce.dwname }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-30" colspan="3" >${jce.dwaddress }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-30" >${jce.address }</td>
					<td class="width-20 active"><label class="pull-right">家庭邮编：</label></td>
					<td class="width-30">${jce.jtyb }</td>	
				</tr>
                    </c:if>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实和处罚依据：</label></td>
					<td class="width-30" colspan="3" >${jce.illegalactandevidence }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人申辩意见：</label></td>
					<td class="width-30" colspan="3">${jce.sbrecord }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3">${jce.opinion }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30" >${jce.cbr1 }</td>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30" >${jce.cbr2 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${jce.cbsj }" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${jce.fzshyj=='0'}">
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
					<td class="width-30">${jce.fzshr }</td>
					<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${jce.fzshsj }" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${jce.shyj=='0'}">
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
					<td class="width-30">${jce.shr }</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${jce.shsj }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3" style="height:30px">
						<c:choose>
						<c:when test="${jce.spyj=='0'}">
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
					<td class="width-30">${jce.spr }</td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><fmt:formatDate type="date"  value="${jce.spsj }" /></td>
				</tr>
		</table>
</body>
</html>