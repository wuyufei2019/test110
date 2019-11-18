<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>喷射火事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#jetfire_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="jetfire_form" novalidate  class="form-horizontal" >
	<input type="hidden" id="jetfire_form_id_m11"  name="M11" value="0" />
	<input type="hidden" id="jetfire_form_id_m12"  name="M12" value="0" />
	<input type="hidden" id="jetfire_form_id_m1_1"  name="M1_1" value="" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td class="width-35" colspan="3"><input name="M1" id="jetfire_form_m1" class="easyui-combobox" style="width:450px;height: 30px;" data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				panelHeight:130,
				editable:true ,
				url:'${ctx}/tcode/acawxhxp/json?name=',
				
     	        onSelect: function(rec){
     	        	$('#jetfire_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#jetfire_form_id_m2').textbox('setValue',data.m6);
					                $('#jetfire_form_id_m4').textbox('setValue',data.m2);
					                $('#jetfire_form_id_m5').textbox('setValue',data.m13);
					           }
					     });
							    } " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">热容比：</label></td>
		<td class="width-35"><input id="jetfire_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" /></td>
		<td class="width-15 active"><label class="pull-right">压力：</label></td>
		<td class="width-35"><input id="jetfire_form_id_m3" name="M3" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（MPa）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">分子量：</label></td>
		<td class="width-35"><input id="jetfire_form_id_m4" name="M4" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" /></td>
		<td class="width-15 active"><label class="pull-right">燃烧热：</label></td>
		<td class="width-35"><input id="jetfire_form_id_m5" name="M5" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">孔径：</label></td>
		<td colspan="3"><input id="jetfire_form_id_m6" name="M6" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（m）</td>
	</tr>
</table>
</form>

</body>
</html>