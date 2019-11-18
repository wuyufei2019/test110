<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业化学品仓库配置信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype == '0'}">
				  	<tr>
				  		<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">${qyhxpckpzxx.qyname }</td>
				  	</tr>
			  	</c:if>
			  
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">所属化学品仓库：</label></td>
					<td class="width-30" colspan="3">${qyhxpckpzxx.hxpckname }</td>
			  	</tr>
			  		
				<tr >
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30">${qyhxpckpzxx.cascode }</td>
					<td class="width-20 active"><label class="pull-right">最大在线量：</label></td>
					<td class="width-30">${qyhxpckpzxx.olqty }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">危化品中文名称：</label></td>
					<td class="width-30">${qyhxpckpzxx.chemcname }</td>
					<td class="width-20 active"><label class="pull-right">危化品英文名称：</label></td>
					<td class="width-30">${qyhxpckpzxx.chemename }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">计量单位：</label></td>
					<td class="width-30">${qyhxpckpzxx.jldw}</td>
					<td class="width-20 active"><label class="pull-right">涉及危险工艺：</label></td>
					<td class="width-30">${qyhxpckpzxx.dangeart }</td>
				</tr>

			  </tbody>
			</table>
	 </form>
</body>
</html>