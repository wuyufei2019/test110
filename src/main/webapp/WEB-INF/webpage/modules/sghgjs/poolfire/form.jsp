<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>池火灾事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#poolfire_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="poolfire_form" novalidate  class="form-horizontal" >
	<input type="hidden" id="poolfire_form_id_m11"  name="M11" value="0" />
	<input type="hidden" id="poolfire_form_id_m12"  name="M12" value="0" />
	<input type="hidden" id="poolfire_form_id_m1_1"  name="M1_1" value="0" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td class="width-35" colspan="3"><input name="M1" id="poolfire_form_m1" class="easyui-combobox" style="width:450px;height: 30px;" data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				editable:true ,
				panelHeight:150,
				url:'${ctx}/tcode/acawxhxp/json?name=',
				 
     	        onSelect: function(rec){
					$('#poolfire_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#poolfire_form_id_m2').textbox('setValue',data.m4);
					                $('#poolfire_form_id_m3').textbox('setValue',data.m3);
					                $('#poolfire_form_id_m4').textbox('setValue',data.m10);
					                $('#poolfire_form_id_m5').textbox('setValue',data.m13);
					                $('#poolfire_form_id_m7').textbox('setValue',data.m14);
					           }
					     });
							    } " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">比热容：</label></td>
		<td><input id="poolfire_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg-K）</td>
		<td class="width-15 active"><label class="pull-right">沸点：</label></td>
		<td><input id="poolfire_form_id_m3" name="M3" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（℃）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">密度：</label></td>
		<td><input id="poolfire_form_id_m4" name="M4" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kg/㎥）</td>
		<td class="width-15 active"><label class="pull-right">燃烧热：</label></td>
		<td><input id="poolfire_form_id_m5" name="M5" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">相对湿度：</label></td>
		<td><input id="poolfire_form_id_m6" name="M6" class="easyui-textbox" style="width:100px;height: 30px;" value="0.5" data-options="required:'true',validType:'number'" /></td>
		<td class="width-15 active"><label class="pull-right">蒸发热：</label></td>
		<td><input id="poolfire_form_id_m7" name="M7" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（kJ/kg）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">液池半径：</label></td>
		<td colspan="3"><input id="poolfire_form_id_m8" name="M8" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'number'" />（m）</td>
	</tr>
</table>
 
</form>

</body>
</html>