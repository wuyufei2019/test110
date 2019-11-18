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
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35">${txl.m1 }</td>
					<td class="width-15 active"><label class="pull-right">部门：</label></td>
					<td class="width-35">${txl.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35">${txl.m3 }</td>
					<td class="width-15 active"><label class="pull-right">应急电话(手机)：</label></td>
					<td class="width-35">${txl.m4 }</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">固定电话：</label></td>
					<td class="width-35" colspan="3" >
						${txl.m5 }
					</td>
				</tr>
					
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${txl.m6 }
					</td>				
				</tr>
				
			</table>

		  	<tbody>
       </form>
</body>
</html>