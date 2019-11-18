<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业生产装置信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype == '0'}">
				  	<tr>
				  		<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">${qysczzxx.qyname }</td>
				  	</tr>
			  	</c:if>
			  
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30">${qysczzxx.equipcode }</td>
					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30">${qysczzxx.prodcellid }</td>
			  	</tr>
			  		
				<tr>
					<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30">${qysczzxx.hazardname }</td>
					<td class="width-20 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-30">${qysczzxx.hazardcode }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">生产装置名称：</label></td>
					<td class="width-30">${qysczzxx.proddevname}</td>
					<td class="width-20 active"><label class="pull-right">平台最高层数：</label></td>
					<td class="width-30">${qysczzxx.platformtiers }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">涉及重点监管工艺：</label></td>
					<td class="width-30" colspan="3">${qysczzxx.importart}</td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">调度室电话：</label></td>
					<td class="width-30">${qysczzxx.ctrltel}</td>
					<td class="width-20 active"><label class="pull-right">是否正常状态：</label></td>
					<td class="width-30">${qysczzxx.zt}</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">经度：</label></td>
					<td class="width-30">${qysczzxx.longitude }</td>
					<td class="width-20 active"><label class="pull-right">纬度：</label></td>
					<td class="width-30">${qysczzxx.latitude }</td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">负责人：</label></td>
					<td class="width-30" colspan="3">${qysczzxx.dutypsn}</td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">生产工艺或存储情况简介：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qysczzxx.craftintroduction }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全措施：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${qysczzxx.safemeasures }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">重点监管危险化工工艺标识：</label></td>
					<td class="width-30">${qysczzxx.chemartid }</td>
					<td class="width-20 active"><label class="pull-right">重点监管危险化工工艺名称：</label></td>
					<td class="width-30">${qysczzxx.chemartart }</td>
				</tr>
				
				
				
			  </tbody>
			</table>
	 </form>
</body>
</html>