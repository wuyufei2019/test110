<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业化学品仓库信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype == '0'}">
				  	<tr>
				  		<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">${qyhxpckxx.qyname }</td>
				  	</tr>
			  	</c:if>
			  
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30">${qyhxpckxx.equipcode }</td>
					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30">${qyhxpckxx.prodcellid }</td>
			  	</tr>
			  		
				<tr >
					<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30">${qyhxpckxx.hazardname }</td>
					<td class="width-20 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-30">${qyhxpckxx.hazardcode }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">化学品仓库名称：</label></td>
					<td class="width-30">${qyhxpckxx.chmstorname}</td>
					<td class="width-20 active"><label class="pull-right">化学品仓库用途：</label></td>
					<td class="width-30">
						<c:if test="${qyhxpckxx.chmstoruse =='H4601'}">
							原料
						</c:if>
						<c:if test="${qyhxpckxx.chmstoruse =='H4602'}">
							辅助原料
						</c:if>
						<c:if test="${qyhxpckxx.chmstoruse =='H4603'}">
							中间产品
						</c:if>
						<c:if test="${qyhxpckxx.chmstoruse =='H4604'}">
							终端产品
						</c:if>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">面积（㎡）或容积：</label></td>
					<td class="width-30">${qyhxpckxx.areavol}</td>
					<td class="width-20 active"><label class="pull-right">存放物体形态：</label></td>
					<td class="width-30">
						<c:if test="${qyhxpckxx.matterform =='H4701'}">
							液态
						</c:if>
						<c:if test="${qyhxpckxx.matterform =='H4702'}">
							气态
						</c:if>
						<c:if test="${qyhxpckxx.matterform =='H4703'}">
							固态
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">设计使用年限：</label></td>
					<td class="width-30">${qyhxpckxx.deslifespan}</td>
					<td class="width-20 active"><label class="pull-right">竣工时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${qyhxpckxx.completime }" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">库房形式：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qyhxpckxx.storform }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">库房结构：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qyhxpckxx.storstructure }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">经度：</label></td>
					<td class="width-30">${qyhxpckxx.longitude }</td>
					<td class="width-20 active"><label class="pull-right">纬度：</label></td>
					<td class="width-30">${qyhxpckxx.latitude }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">仓库管理员姓名：</label></td>
					<td class="width-30">${qyhxpckxx.storemanname }</td>
					<td class="width-20 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-30">${qyhxpckxx.linkmode }</td>
				</tr>
				
				
				
				
				
				
				
				
				
				
			  </tbody>
			</table>
	 </form>
</body>
</html>