<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业生产装置危化品配置信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype == '0'}">
				  	<tr>
				  		<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">${qysczzpzxx.qyname }</td>
				  	</tr>
			  	</c:if>
			  
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">所属生产装置：</label></td>
					<td class="width-30" colspan="3">${qysczzpzxx.sczzname }</td>
			  	</tr>
			  		
				<tr >
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30">${qysczzpzxx.cascode }</td>
					<td class="width-20 active"><label class="pull-right">最大在线量：</label></td>
					<td class="width-30">${qysczzpzxx.maxolqty }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">危化品中文名称：</label></td>
					<td class="width-30">${qysczzpzxx.chemcname }</td>
					<td class="width-20 active"><label class="pull-right">危化品英文名称：</label></td>
					<td class="width-30">${qysczzpzxx.chemename }</td>
				</tr>

			  </tbody>
			</table>
	 </form>
</body>
</html>