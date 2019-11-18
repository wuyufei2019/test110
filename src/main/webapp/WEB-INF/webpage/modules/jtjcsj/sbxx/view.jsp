<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	
				<tr>
					<td class="width-15 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-35" >${scsblist.equipcode }</td>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35" >${scsblist.m3 }</td>
				</tr>			  	
			  	
			  	<tr>
					<td class="width-15 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-35">${scsblist.hazardname }</td>
					<td class="width-15 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-35">${scsblist.hazardcode }</td>
				</tr>
			  	
				<tr >
					<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
						<c:if test="${scsblist.equiptype == 'G0' }">
							<td class="width-35">罐</td>
						</c:if>
						<c:if test="${scsblist.equiptype == 'Q0' }">
							<td class="width-35">气体检测仪</td>
						</c:if>
						<c:if test="${scsblist.equiptype == 'S0' }">
							<td class="width-35">生产装置</td>
						</c:if>
						<c:if test="${scsblist.equiptype == 'C0' }">
							<td class="width-35">仓库</td>
						</c:if>
					
					<td class="width-15 active"><label class="pull-right">设备运行状态：</label></td>
					<td class="width-35">${scsblist.zt }</td>
					
				</tr>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">设备描述：</label></td>
					<td class="width-85" colspan="3" >${scsblist.equipdescribe}</td>		
				</tr>
			 	<tr>
					<td class="width-15 active"><label class="pull-right">设备介质：</label></td>
					<td class="width-35" colspan="3" >${scsblist.m9}</td>		
				</tr>
			  
			  	<tr>
					<td class="width-15 active"><label class="pull-right">安装位置：</label></td>
					<td class="width-35" colspan="3">${scsblist.installloc }</td>
				</tr>
			  	
			  	<tr>
			  		<td class="width-15 active"><label class="pull-right">经度：</label></td>
					<td class="width-35">${scsblist.longitude }</td>
					<td class="width-15 active"><label class="pull-right">纬度：</label></td>
					<td class="width-35">${scsblist.latitude }</td>
			  	</tr>
			  
				
				</tbody>
			</table>

	</form>
</body>
</html>