<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>固定资产请购单</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">请购部门：</label></td>
					<td class="width-30" >${sbsq.sqbmname }</td>
					<td class="width-20 active"><label class="pull-right">请购人：</label></td>
					<td class="width-30" >${sbsq.sqrname }</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">请购日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbsq.m3 }" /></td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">请购资产明细：</label></td>
					<td class="width-80" colspan="3">${sbsq.m4 }</td>
				</tr>
			</tbody>
		</table>
		
		<p style="margin-top: 10px;text-align: center;font-size: 17px;color: dodgerblue;">
			<strong>请购固定资产</strong>
		</p>
		<table style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 25%;">资产名称</td>
				<td style="text-align: center;width: 20%;">规格型号</td>
				<td style="text-align: center;width: 25%;">制造商/品牌</td>
				<td style="text-align: center;width: 10%;">数量</td>
				<td style="text-align: center;width: 10%;">单价（RMB）</td>
				<td style="text-align: center;width: 10%;">总价（RMB）</td>
			</tr>
			<c:forEach items="${qgsblist}" var="qgsb" varStatus="o">
		     	<tr style="height:30px" >
					<td style="" align="center">${qgsb.m1 }</td>
					<td style="" align="center">${qgsb.m2 }</td>
					<td style="" align="center">${qgsb.m3 }</td>
					<td style="" align="center">${qgsb.m4 }</td>
					<td style="" align="center">${qgsb.m5 }</td>
					<td style="" align="center">${qgsb.m6 }</td>
				</tr>
		    </c:forEach>
		</table>
		
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">合计（RMB）：</label></td>
					<td class="width-30" >${sbsq.m5 }</td>
					<td style="width: 50%;"></td>
				</tr>  
				<%-- <tr >
					<td class="width-20 active"><label class="pull-right">供应商：</label></td>
					<td class="width-80" colspan="2">${sbsq.m6 }</td>
				</tr> --%>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">请购理由类别：</label></td>
					<td class="width-80" colspan="2">
					<!-- <input type="radio" name="m7" style="margin-bottom: 6px;" value="1" disabled="disabled"> 预算内
					<input type="radio" name="m7" style="margin-bottom: 6px;margin-left: 20px;" value="2" disabled="disabled"> 预算外 -->
					<input type="checkbox" name="m7" style="margin-bottom: 6px;" value="2" disabled="disabled"> 预算外
                    </td>	
				</tr>
				<%-- <tr id="z1">
					<td class="width-20 active"><label class="pull-right">预算项目编号：</label></td>
					<td class="width-30">${sbsq.m8 }</td>	
				</tr> --%>
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">预算外理由：</label></td>
					<td class="width-80" colspan="2">${sbsq.m9 }</td>
				</tr>
				<c:if test="${sbsq.m10 eq '1'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">附件：</label></td>
						<td class="width-80" colspan="2">
							<c:if test="${not empty sbsq.m11}">
								<c:set var="url" value="${fn:split(sbsq.m11, '||')}" />
								<div  style="margin-bottom: 10px;">
								<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
								</div>
							</c:if>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>
<script type="text/javascript">
$(function(){
	/* if('${sbsq.m7}'==1){
		$("input[name='m7']").get(0).checked = true;
		$('#z1').show();
		$('#z2').hide();
	}else{
		$("input[name='m7']").get(1).checked = true;
		$('#z1').hide();
		$('#z2').show();
	} */
	
	if('${sbsq.m7}'==2){
		$("input[name='m7']").get(0).checked = true;
		$('#z2').show();
	} else {
		$('#z2').hide();
	}
});
</script>
</body>
</html>