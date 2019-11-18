<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急处置技术管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">化学品名称：</label></td>
					<td class="width-35" colspan="3">${res.m1 }</td>
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m2 }</td> 
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">事故应急处置技术：</label></td>
					<td class="width-85" colspan="3" style="height:80px">${res.m3 }</td> 
				</tr>
				
			</table>

		  	</tbody>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>