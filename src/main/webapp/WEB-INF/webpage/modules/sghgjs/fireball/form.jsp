<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>火球事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#fireball_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="fireball_form" novalidate  class="form-horizontal" >
	<input type="hidden" id="fireball_form_id_m7"  name="M7" value="30" />
	<input type="hidden" id="fireball_form_id_m8"  name="M8" value="0" />
	<input type="hidden" id="fireball_form_id_m1_1"  name="M1_1" value="" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td class="width-35" colspan="3">
		<input name="M1" id="fireball_form_m1" class="easyui-combobox" style="width: 100%;height: 30px;" data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				panelHeight:130,
				editable:true ,
				url:'${ctx}/tcode/acawxhxp/json?name=',
				 
     	        onSelect: function(rec){
     	        	$('#fireball_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#fireball_form_id_m3').textbox('setValue',data.m13);
					           }
					     });
				} " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">泄漏量：</label></td>
		<td class="width-35"><input id="fireball_form_id_m2" name="M2" class="easyui-textbox" style="width: 120px;height: 30px;" data-options="required:'true',validType:'number'" />（kg）</td>
		<td class="width-15 active"><label class="pull-right">燃烧热：</label></td>
		<td class="width-35"><input id="fireball_form_id_m3" name="M3" class="easyui-textbox" style="width: 120px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg）</td>
	</tr>
	</tbody>
</table>
</form>

</body>
</html>