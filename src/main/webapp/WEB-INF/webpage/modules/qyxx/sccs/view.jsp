<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>生产场所管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">单元名称：</label></td>
					<td class="width-30" colspan="3">
						${sccs.m1 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">生产场所位置：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label>经度：</label>${sccs.m3 }
						<label>纬度：</label>${sccs.m4 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">固定资产总值(万元)：</label></td>
					<td class="width-30">${sccs.m2 }</td>

					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30">${sccs.m5 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">占地面积(㎡)：</label></td>
					<td class="width-30">${sccs.m6 }</td>

					<td class="width-20 active"><label class="pull-right">正常当班人数：</label></td>
					<td class="width-30">${sccs.m7 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">单元内危险化学品：</label></td>
					<td class="width-30" colspan="3">${sccs.m8 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${sccs.m9 }</td>
				</tr>
			  </tbody>
			</table>
	 </form>
</body>
</html>