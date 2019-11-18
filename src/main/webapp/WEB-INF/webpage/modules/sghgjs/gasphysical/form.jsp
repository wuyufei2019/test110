<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>压缩气体物料爆炸事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#gasphysical_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="gasphysical_form" class="form-horizontal" >
	<input type="hidden" id="gasphysical_form_id_m7"  name="M7" value="0" />
	<input type="hidden" id="gasphysical_form_id_m8"  name="M8" value="0" />
	<input type="hidden" id="gasphysical_form_id_m1_1"  name="M1_1" value="" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td colspan="3"><input name="M1" id="gasphysical_form_m1" class="easyui-combobox" style="width:450px;height: 30px;" data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				panelHeight:130,
				editable:true ,
				url:'${ctx}/tcode/acawxhxp/json?name=',
     	        onSelect: function(rec){
     	     		$('#gasphysical_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#gasphysical_form_id_m2').textbox('setValue',data.m6);
					           }
					     });
							    } " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">热容比：</label></td>
		<td colspan="3"><input id="gasphysical_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg-K）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">压力：</label></td>
		<td class="width-35"><input id="gasphysical_form_id_m3" name="M3" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（MPa）</td>
		<td class="width-15 active"><label class="pull-right">容积：</label></td>
		<td class="width-35"><input id="gasphysical_form_id_m4" name="M4" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（㎥）</td>
	</tr>
</table>
</form>

</body>
</html>