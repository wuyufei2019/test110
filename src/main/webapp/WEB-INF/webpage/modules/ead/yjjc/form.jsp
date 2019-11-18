<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title> </title>
	<meta name="decorator" content="default"/>
	 
	<script type="text/javascript">
	function doSubmit(){
		return $("#ead_yjjc_form_mainform").form('validate'); 
	}
	</script>
</head>
<body>
<div style="width:100%;height:auto;text-align:center;margin:0 auto;">
		<form id="ead_yjjc_form_mainform" >
			<input type="hidden" id="ead_yjjc_form_id_hid_lng"  name="ead_yjjc_lng" value="" />
			<input type="hidden" id="ead_yjjc_form_id_hid_lat"  name="ead_yjjc_lat" value="" />
			<input type="hidden" id="ead_yjjc_form_id_hid_weathertoday_fx"  name="ead_yjjc_weathertoday_fx" value="" />
			<table class="ead_yjjc_form_id_table" style="margin:0 auto;" >
				<tr>
					<td class="ead_yjjc_form_id_table_td" colspan="4"><span style="color: red;">可以选择事故企业(如不选企业，默认坐标为地图选择的坐标)：</span></td>
				</tr>
				
				<tr style="height: 50px;">
				 
					<td class="ead_yjjc_form_id_table_td" style="text-align: right;">事故企业：</td>
					<td class="ead_yjjc_form_id_table_td2">
							<input name="ead_yjjc_qy" id="ead_yjjc_qy" class="easyui-combobox" style="width: 280px;height: 30px;" data-options="
						editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',
						onSelect: function(rec){
								$.ajax({
							           url:'${ctx}/bis/qyjbxx/dict',
							           data:{'id':rec.id},
							           dataType : 'json',
							           type : 'POST',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							      
							                parent.pide.point.lng=data.lng;
										    parent.pide.point.lat=data.lat;
										    $('#ead_yjjc_form_id_hid_lng').val(data.lng);
										    $('#ead_yjjc_form_id_hid_lat').val(data.lat);
							           }
							     });
							    } 
						 "/>
					</td>
				 
					<td class="ead_yjjc_form_id_table_td" style="text-align: right;">事故类型：</td>
					<td class="ead_yjjc_form_id_table_td2">
							<input name="ead_yjjc_type" id="ead_yjjc_type" class="easyui-combobox" style="width: 150px;height: 30px;" data-options="
						 editable:false ,data: [
										{value:'1',text:'池火灾'},
								        {value:'2',text:'喷射火'},
								        {value:'3',text:'火球'},
								        {value:'4',text:'物理爆炸（压力容器爆炸）'},
								        {value:'5',text:'化学爆炸'},
								        {value:'6',text:'压缩气体物理爆炸'},
								        {value:'7',text:'持续泄漏'},
								        {value:'8',text:'瞬时泄漏'}],
						onSelect: function(rec){
									$('#ead_yjjc_form_btn').unbind('click');
									
									if(rec.value=='1'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/poolfire/create',
				                        		onLoad:function(){
				                        			$('#poolfire_form_id_m11').val(parent.pide.point.lng);
													$('#poolfire_form_id_m12').val(parent.pide.point.lat);
													}
											});
										
										$('#ead_yjjc_form_btn').bind('click',function(){ parent.poolfire(); });	
									
									}else if(rec.value=='2'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/jetfire/create',
				                        		onLoad:function(){
				                        			$('#jetfire_form_id_m11').val(parent.pide.point.lng);
													$('#jetfire_form_id_m12').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.jetfire(); });	
										
									}else if(rec.value=='3'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/fireball/create',
				                        		onLoad:function(){
				                        			$('#fireball_form_id_m7').val(parent.pide.point.lng);
													$('#fireball_form_id_m8').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.fireball(); });	
											
									}else if(rec.value=='4'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/physical/create',
				                        		onLoad:function(){
				                        			$('#physical_form_id_m5').val(parent.pide.point.lng);
													$('#physical_form_id_m6').val(parent.pide.point.lat);
													}
											});
										$('#ead_yjjc_form_btn').bind('click',function(){ parent.physical(); });	
										
									}else if(rec.value=='5'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/vce/create',
				                        		onLoad:function(){
				                        			$('#vce_form_id_m6').val(parent.pide.point.lng);
													$('#vce_form_id_m7').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.vce(); });	
										
									}else if(rec.value=='6'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/gasphysical/create',
				                        		onLoad:function(){
				                        			$('#gasphysical_form_id_m7').val(parent.pide.point.lng);
													$('#gasphysical_form_id_m8').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.gasphysical(); });	
										
									}else if(rec.value=='7'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/leakage/create',
				                        		onLoad:function(){
				                        			$('#leakage_form_id_m13').val(parent.pide.point.lng);
													$('#leakage_form_id_m14').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.leakage(); });	
										
									}else if(rec.value=='8'){
										$('#p').css('display','block');
										$('#p').panel({
				                        		href:'${ctx}/sghgjs/instantleakage/create',
				                        		onLoad:function(){
				                        			$('#instantleakage_form_id_m7').val(parent.pide.point.lng);
													$('#instantleakage_form_id_m8').val(parent.pide.point.lat);
													}
											});
											$('#ead_yjjc_form_btn').bind('click',function(){ parent.instantleakage(); });	
									
									}else{
									
									}
									
							    } 
						 "/>
					</td>
					 
				</tr>
				
				<tr><td colspan="4">
					<div id="p" class="easyui-panel" style="width:600px;height:240px;display: none;"  data-options=""></div>
				</td></tr>
				<tr>
				<td colspan="4"><a href='javascript:void(0)' class='btn btn-danger btn-sm'  id="ead_yjjc_form_btn" style="margin-top:5px;width: 100px; "><i class='fa fa-calculator'></i> 计算</a></td>
				</tr>
			</table>
		</form>
</div>


<script type="text/javascript">

	 
	
</script>
</body>
</html>