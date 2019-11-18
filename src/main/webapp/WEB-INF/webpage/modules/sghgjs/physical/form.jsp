<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>物理爆炸事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#physical_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="physical_form" class="form-horizontal" >
	<input type="hidden" id="physical_form_id_m5"  name="M5" value="0" />
	<input type="hidden" id="physical_form_id_m6"  name="M6" value="0" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">压力：</label></td>
		<td><input id="physical_form_id_m1" name="M1" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（MPa）</td>
		<td class="width-15 active"><label class="pull-right">容积：</label></td>
		<td><input id="physical_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（㎥）</td>
	</tr>
</table>
</form>

</body>
</html>