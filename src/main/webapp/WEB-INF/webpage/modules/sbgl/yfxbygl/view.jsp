<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
            	<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
            	<td class="width-35" colspan="3">${sbgl.sbname }</td>
            </tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">维护项目：</label></td>
				<td class="width-35" >${sbgl.m1 }</td>
				<td class="width-15 active"><label class="pull-right">维护周期：</label></td>
				<td class="width-35" >${sbgl.m4 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">维护要求：</label></td>
				<td class="width-35" colspan="3">${sbgl.m2 }</td>					
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">维护方法：</label></td>
				<td class="width-35" colspan="3">${sbgl.m3 }</td>					
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">操作者：</label></td>
				<td class="width-35" >${sbgl.m5 }</td>
				<td class="width-15 active"><label class="pull-right">维修者：</label></td>
				<td class="width-35" >${sbgl.m6 }</td>
			</tr>
         </tbody>
      </table>
	</form>
</body>
</html>