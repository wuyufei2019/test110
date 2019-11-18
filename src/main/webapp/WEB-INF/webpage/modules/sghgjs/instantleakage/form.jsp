<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>瞬时泄漏事故计算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function doSubmit(){
		return $("#instantleakage_form").form('validate'); 
	}
	</script>
</head>
<body>
<form id="instantleakage_form" class="form-horizontal" >
	<input type="hidden" id="instantleakage_form_id_m7"  name="M7" value="0" />
	<input type="hidden" id="instantleakage_form_id_m8"  name="M8" value="0" />
	<input type="hidden" id="instantleakage_form_id_m9"  name="M9" value="10" />
	<input type="hidden" id="instantleakage_form_id_m1_1"  name="M1_1" value="" />
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	<tbody>	
	<tr >  
		<td class="width-15 active"><label class="pull-right">选择物质：</label></td>
		<td colspan="3"><input name="M1" id="instantleakage_form_id_m1" class="easyui-combobox" style="width:450px;height: 30px;"  data-options="required:'true', 
				valueField: 'id',
				textField: 'text',
				panelHeight:130,
				editable:true ,
				url:'${ctx}/tcode/acawxhxp/json?name=',
				
     	        onSelect: function(rec){
     	     		$('#instantleakage_form_id_m1_1').val(rec.text);
     	        	$.ajax({
					           url:'${ctx}/tcode/acawxhxp/jsontid',
					           data:{'tid':rec.id},
					           dataType : 'json',
					           type : 'POST',
					           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					           success: function (data){
					                $('#instantleakage_form_id_m2').textbox('setValue',data.m7);
					           }
					     });
							    } " /></td> 
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">边界浓度：</label></td>
		<td><input id="instantleakage_form_id_m2" name="M2" class="easyui-textbox" style="width:100px;height: 30px;" data-options="required:'true',validType:'cczu_numberDecimalsTenCheck'" />（kg/㎥）</td>
		<td class="width-15 active"><label class="pull-right">泄漏质量：</label></td>
		<td><input id="instantleakage_form_id_m3" name="M3" class="easyui-textbox" value="100000" style="width:100px;height: 30px;" data-options="required:'true',validType:'cczu_numberDecimalsTenCheck'" />（kg）</td>
	</tr>
	<tr >  
		<td class="width-15 active"><label class="pull-right">地面风速：</label></td>
		<td><input id="instantleakage_form_id_m4" name="M4" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
							required:'true',panelHeight:100,editable:false ,data: [
										{value:'1.8',text:'<2'},
								        {value:'2.5',text:'2~3'},
								        {value:'3.5',text:'3~4'},
								        {value:'5',text:'4~6'},
								        {value:'8',text:'>6'}] "/>（m/s）</td>
		<td class="width-15 active"><label class="pull-right">风向：</label></td>
		<td><input id="instantleakage_form_id_m5" name="M5" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
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
		<td colspan="3"><input id="instantleakage_form_id_m6" name="M6" class="easyui-combobox" style="width:100px;height: 30px;" data-options="
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