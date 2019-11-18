<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加课程</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
			<tr>
				<td class="width-20 active"><label class="pull-right">课程名称：</label></td>
				<td class="width-80">
					<input id="kcname" name="kcname" type="text" class="easyui-textbox" value="" style="width: 300px;height: 30px;" data-options="required:'true',validType:'length[0,50]'" />
				</td>
			</tr>
		</tbody>
	</table>
<script type="text/javascript">
function getkcname(){
	return $("#kcname").textbox("getValue");
}
</script>
</body>
</html>