<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>在线监测指标</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype == '0'}">
				  	<tr>
				  		<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">${qyscdyqyxx.qyname }</td>
				  	</tr>
			  	</c:if>
			  
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30">${qyscdyqyxx.hazardname }</td>
			  		<td class="width-20 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-30">${qyscdyqyxx.hazardcode }</td>
			  	</tr>
			  	
				<tr >
					<td class="width-20 active"><label class="pull-right">生产单元区域名称：</label></td>
					<td class="width-30" colspan="3">${qyscdyqyxx.prodcellname }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">生产区域类型：</label></td>
					<td class="width-30">
						<c:if test="${qyscdyqyxx.prodcelltype =='HEV01'}">
							罐区
						</c:if>
						<c:if test="${qyscdyqyxx.prodcelltype =='HEV02'}">
							生产装置区
						</c:if>
						<c:if test="${qyscdyqyxx.prodcelltype =='HEV03'}">
							库区
						</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30">${qyscdyqyxx.prodcellid }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全负责人姓名：</label></td>
					<td class="width-30">${qyscdyqyxx.safedutyername}</td>
					<td class="width-20 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-30">${qyscdyqyxx.linkmode }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">面积：</label></td>
					<td class="width-30">${qyscdyqyxx.prodarea}</td>
					<td class="width-20 active"><label class="pull-right">防护堤面积：</label></td>
					<td class="width-30">${qyscdyqyxx.iprotectarea }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">有无防护堤：</label></td>
					<td class="width-30">
						${qyscdyqyxx.fhd }
						<%-- <c:if test="${qyscdyqyxx.isprotect == '1'}">
							是
						</c:if>
						<c:if test="${qyscdyqyxx.isprotect == '0'}">
							否
						</c:if> --%>
					</td>
					<td class="width-20 active"><label class="pull-right">贮罐个数：</label></td>
					<td class="width-30">${qyscdyqyxx.tanknum }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">罐间最小间距：</label></td>
					<td class="width-30" colspan="3">${qyscdyqyxx.mintankspace }</td>			
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">生产工艺或存储状况简介：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qyscdyqyxx.craftintroduction }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全措施：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qyscdyqyxx.safemeasures }</td>
				</tr>
			  </tbody>
			</table>
	 </form>
</body>
</html>