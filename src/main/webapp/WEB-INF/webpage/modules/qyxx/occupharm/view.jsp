<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>职业病危害因素管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">职业病危害因素类别：</label></td>
					<td class="width-30">${whyslist.m1 }</td>
					<td class="width-20 active"><label class="pull-right">职业病危害因素名称：</label></td>
					<td class="width-30">${whyslist.m2 }</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">危害程度：</label></td>
					<td class="width-30" colspan="3">
						${whyslist.m3 }
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">可能导致的职业病：</label></td>
					<td class="width-30" colspan="3">
						${whyslist.m5 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">存在部门：</label></td>
					<td class="width-30">${whyslist.m6 }</td>
					<td class="width-20 active"><label class="pull-right">存在岗位：</label></td>
					<td class="width-30">${whyslist.m7 }</td>
				<tr>
					
				<tr >
					<td class="width-20 active"><label class="pull-right">接触人数：</label></td>
					<td class="width-30" colspan="3">
						${whyslist.m8 }
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">接触名单：</label></td>
					<td class="width-30" colspan="3">
						${whyslist.m9 }
					</td>
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >
					${whyslist.m4 }
					</td>					
				</tr>
				</tbody>
			</table>

		  	
       </form>
</body>
</html>