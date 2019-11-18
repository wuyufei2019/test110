<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>三同时管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35">${entity.m1}</td>
					<td class="width-15 active"><label class="pull-right">所在乡镇：</label></td>
					<td class="width-35">${entity.m2}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">项目名称：</label></td>
					<td class="width-35">${entity.m3}</td>
					<td class="width-15 active"><label class="pull-right">建设地址：</label></td>
					<td class="width-35">${entity.m4}</td>
				</tr>		
				<tr>
					<td class="width-15 active"><label class="pull-right">产能(万只/年)：</label></td>
					<td class="width-35">${entity.m5}</td>
					<td class="width-15 active"><label class="pull-right">劳动定员：</label></td>
					<td class="width-35">${entity.m6}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">环评批复意见文号：</label></td>
					<td class="width-35">${entity.m7}</td>
					<td class="width-15 active"><label class="pull-right">批复时间：</label></td>
					<td class="width-35">${entity.m8 }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">批准部门：</label></td>
					<td class="width-35">${entity.m14 }</td>
					<td class="width-15 active"><label class="pull-right">试生产核准文号：</label></td>
					<td class="width-35">${entity.m9}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">核准部门：</label></td>
					<td class="width-35">${entity.m15 }</td>
					<td class="width-15 active"><label class="pull-right">核准时间：</label></td>
					<td class="width-35">${entity.m10 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">三同时验收部门：</label></td>
					<td class="width-35">${entity.m16 }</td>
				    <td class="width-15 active"><label class="pull-right">验收时间：</label></td>
					<td class="width-35">${entity.m11 }</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">产能核准量(万只/年)：</label></td>
					<td class="width-35">${entity.m12}</td>
					<td class="width-15 active"><label class="pull-right">产污核准量(吨/年)：</label></td>
					<td class="width-35">${entity.m13}</td>
				</tr>
				</tbody>
			</table>
       </form>
</body>
</html>