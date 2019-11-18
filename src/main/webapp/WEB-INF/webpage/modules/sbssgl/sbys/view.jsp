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
					<td class="width-20 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-30" >${sbys.m1 }</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >${sbys.m2 }</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">验收日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbys.m3 }" /></td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" >${sbys.m4 }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" >${sbys.m5 }</td>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >${sbys.m6 }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">设备制造商/代理商：</label></td>
					<td class="width-30" >${sbys.m7 }</td>
					<td class="width-20 active"><label class="pull-right">出厂日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbys.m8 }" /></td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">放置地点：</label></td>
					<td class="width-80" colspan="3">${sbys.m9 }</td>
				</tr>
			</tbody>
		</table>
		<p style="margin-top: 10px;text-align: center;font-size: 17px;color: dodgerblue;">
			<strong>硬件验收</strong>
		</p>
		<table style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 25%;">项目</td>
				<td style="text-align: center;width: 10%;">定购数量</td>
				<td style="text-align: center;width: 10%;">实交数量</td>
				<td style="text-align: center;width: 20%;">外观</td>
				<td style="text-align: center;width: 35%;">说明</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">主件</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m10 }</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m11 }</td>
				<td style="text-align: center;width: 20%;padding: 4px;">${sbys.m12 }</td>
				<td style="text-align: center;width: 40%;padding: 4px;">${sbys.m13 }</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">附件</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m14 }</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m15 }</td>
				<td style="text-align: center;width: 20%;padding: 4px;">${sbys.m16 }</td>
				<td style="text-align: center;width: 40%;padding: 4px;">${sbys.m17 }</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">技术手册、设备资料</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m18 }</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m19 }</td>
				<td style="text-align: center;width: 20%;padding: 4px;">${sbys.m20 }</td>
				<td style="text-align: center;width: 40%;padding: 4px;">${sbys.m21 }</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">随机工具</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m22 }</td>
				<td style="text-align: center;width: 10%;padding: 4px;">${sbys.m23 }</td>
				<td style="text-align: center;width: 20%;padding: 4px;">${sbys.m24 }</td>
				<td style="text-align: center;width: 40%;padding: 4px;">${sbys.m25 }</td>
			</tr>
		</table>	
		<p style="margin-top: 10px;text-align: center;font-size: 17px;color: dodgerblue;">
			<strong>功能验收</strong>
		</p>
		<table style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 50%;">测试项目</td>
				<td style="text-align: center;width: 50%;">测试结果</td>
			</tr>
			<c:forEach items="${gnyslist}" var="gnys" varStatus="o">
		     	<tr style="height:30px" >
					<td style="" align="center">${gnys.m1 }</td>
					<td style="" align="center">${gnys.m2 }</td>
				</tr>
		    </c:forEach>
		</table>
		
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <%-- <tr>
					<td class="width-20 active"><label class="pull-right">保修开始日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbys.m26 }" /></td>
					<td class="width-20 active"><label class="pull-right">保修结束日期：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd" value="${sbys.m27 }" /></td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">保修证书：</label></td>
					<td class="width-80" colspan="3">${sbys.m28 }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">保修收费条件：</label></td>
					<td class="width-80" colspan="3">${sbys.m29 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检验条件：</label></td>
					<td class="width-80" colspan="3">
						<input type="radio" name="m30" style="margin-bottom: 6px;" value="1" disabled="disabled"> 不需校验
						<input type="radio" name="m30" style="margin-bottom: 6px;margin-left: 20px;" value="2" disabled="disabled"> 需校验
                    </td>	
				</tr>
				<tr id="z1">
					<td class="width-20 active"><label class="pull-right">是否新购买设备：</label></td>
					<td class="width-80" colspan="3">
						<input type="radio" name="m31" style="margin-bottom: 6px;" value="1" disabled="disabled"> 不选择
						<input type="radio" name="m31" style="margin-bottom: 6px;margin-left: 20px;" value="2" disabled="disabled"> 选择
                    </td>	
				</tr>
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">合格实验室校验报告：</label></td>
					<td class="width-80" colspan="3">
						<c:if test="${not empty sbys.m32}">
							<c:set var="url" value="${fn:split(sbys.m32, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
                    </td>	
				</tr> --%>
				<tr>
					<td class="width-20 active"><label class="pull-right">验收最终结果和相关说明：</label></td>
					<td class="width-80" colspan="3">${sbys.m33 }</td>
				</tr>
				<c:if test="${sbys.m34 != '0'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">附件：</label></td>
						<td class="width-80" colspan="3">
							<c:if test="${not empty sbys.m35}">
								<c:set var="url" value="${fn:split(sbys.m35, '||')}" />
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
/* $(function(){
	if('${sbys.m30}'==1){
		$("input[name='m30']").get(0).checked = true;
		$('#z1').hide();
		$('#z2').hide();
	}else{
		$("input[name='m30']").get(1).checked = true;
		$('#z1').show();
		if('${sbys.m31}'==1){
			$("input[name='m31']").get(0).checked = true;
			$('#z2').hide();
		}else{
			$("input[name='m31']").get(1).checked = true;
			$('#z2').show();
		}
	}
}); */
</script>
</body>
</html>