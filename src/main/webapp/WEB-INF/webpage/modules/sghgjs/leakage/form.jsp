<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>持续泄漏事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#leakage_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="leakage_form" class="form-horizontal" >
	<input type="hidden" id="leakage_form_id_m13"  name="M13" value="0" />
	<input type="hidden" id="leakage_form_id_m14"  name="M14" value="0" />
	<input type="hidden" id="leakage_form_id_m1_1"  name="M1_1" value="0" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td colspan="3"><input name="M1" id="leakage_form_m1" class="easyui-combobox" style="width:450px;height: 30px;" value="甲烷" data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				panelHeight:150,
				editable:true ,
				url:'${ctx}/tcode/acawxhxp/json?name=',
				
     	        onSelect: function(rec){
 					$('#leakage_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#leakage_form_id_m2').textbox('setValue',data.m7);
					                $('#leakage_form_id_m3').textbox('setValue',data.m8);
					                $('#leakage_form_id_m4').textbox('setValue',data.m9);
					                $('#leakage_form_id_m5').textbox('setValue',data.m2);
					                $('#leakage_form_id_m6').textbox('setValue',data.m6);
					           }
					     });
			   } " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">边界浓度1：</label></td>
		<td><input id="leakage_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" value="0.01" data-options="required:'true',validType:'number'" />（kg/㎥）</td>
		<td class="width-15 active"><label class="pull-right">边界浓度2：</label></td>
		<td><input id="leakage_form_id_m3" name="M3" class="easyui-textbox" style="width:100px;height: 30px;" value="0.005" data-options="required:'true',validType:'number'" />（kg/㎥）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">边界浓度3：</label></td>
		<td><input id="leakage_form_id_m4" name="M4" class="easyui-textbox" style="width:100px;height: 30px;" value="0.0025" data-options="required:'true',validType:'number'" />（kg/㎥）</td>
		<td class="width-15 active"><label class="pull-right">分子量：</label></td>
		<td><input id="leakage_form_id_m5" name="M5" class="easyui-textbox" style="width:100px;height: 30px;" value="16" data-options="required:'true',validType:'number'" /></td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">热容比：</label></td>
		<td><input id="leakage_form_id_m6" name="M6" class="easyui-textbox" style="width:100px;height: 30px;" value="1.32" data-options="required:'true',validType:'number'" /></td>
		<td class="width-15 active"><label class="pull-right">温度：</label></td>
		<td><input id="leakage_form_id_m7" name="M7" class="easyui-textbox" style="width:100px;height: 30px;" value="25" data-options="required:'true',validType:'number'" />（℃）</td>
	</tr>
	
	
	<tr >  
		<td class="width-15 active"><label class="pull-right">泄漏孔直径：</label></td>
		<td><input id="leakage_form_id_m8" name="M8" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
							required:'true',panelHeight:100,editable:true ,data: [
										{value:'0.005',text:'0.005'},
								        {value:'0.025',text:'0.025'},
								        {value:'0.1',text:'0.1'},
								        {value:'0.2',text:'0.2'}]
						    "/>（m）</td>
		<td class="width-15 active"><label class="pull-right">内部压力：</label></td>
		<td><input id="leakage_form_id_m9" name="M9" class="easyui-textbox" style="width:100px;height: 30px;" value="18" data-options="required:'true',validType:'number'" />（MPa）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">地面风速：</label></td>
		<td><input id="leakage_form_id_m10" name="M10" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
							required:'true',panelHeight:100,editable:false ,data: [
										{value:'1.8',text:'<2'},
								        {value:'2.5',text:'2~3'},
								        {value:'3.5',text:'3~4'},
								        {value:'5',text:'4~6'},
								        {value:'8',text:'>6'}]
						    "/>（m/s）</td>
		<td class="width-15 active"><label class="pull-right">风向：</label></td>
		<td><input id="leakage_form_id_m11" name="M11" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
							required:'true',panelHeight:100,editable:false ,data: [
										{value:'1',text:'东'},
								        {value:'2',text:'东南'},
								        {value:'3',text:'南'},
								        {value:'4',text:'西南'},
								        {value:'5',text:'西'},
								        {value:'6',text:'西北'},
								        {value:'7',text:'北'},
								        {value:'8',text:'东北'}]
						    "/></td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">天气条件：</label></td>
		<td colspan="3"><input id="leakage_form_id_m12" name="M12" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
							required:'true',panelHeight:100,editable:false ,data: [
										{value:'1',text:'白天-晴'},
								        {value:'2',text:'白天-少云'},
								        {value:'3',text:'白天-多云'},
								        {value:'4',text:'阴天'},
								        {value:'5',text:'夜间-阴'},
								        {value:'6',text:'夜间-多云'}] "/></td>
	</tr>
</table>
</form>

</body>
</html>