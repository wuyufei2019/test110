<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>通讯录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">组织名称：</label></td>
					<td class="width-35" colspan="3">
						${res.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">组织负责人：</label></td>
					<td class="width-35">${res.m2 }</td>
					<td class="width-15 active"><label class="pull-right">负责人联系电话：</label></td>
					<td class="width-35">${res.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">组织联系人：</label></td>
					<td class="width-35">${res.m4 }</td>
					<td class="width-15 active"><label class="pull-right">组织联系人电话：</label></td>
					<td class="width-35">${res.m5 }</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">组织应急职责：</label></td>
					<td class="width-35" colspan="3" style="height:80px">
						${res.m6 }
					</td>
				</tr>
					
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
						${res.m7 }
					</td>
					
				</tr>
				
			</table>

		  	<tbody>
       </form>
 
</body>
</html>