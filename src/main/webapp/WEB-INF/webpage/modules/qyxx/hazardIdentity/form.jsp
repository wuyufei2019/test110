<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
<title></title>
</head>
<body>
            <form id="inputForm" class="form-horizontal" action="${ctx}/bis/hazardIdentity/${action}" method="post"  >
			<table class="table table-bordered dataTable" style="margin:auto;" >
				<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35" colspan="3"><div><input name="M1" id="bis_hazardIdentity_form_m1" class="easyui-combobox" value="${qylist.m1 }" style="width:100%;height:30px;" data-options=" editable:false ,
					panelHeight:'180',valueField: 'text',textField: 'text',url:'${ctx}/bis/hazardIdentity/wzlblist',
	       	         onSelect: function(rec){
	       	        	$('#bis_hazardIdentity_form_m7').combobox('setValue','');
	       	        	$('#bis_hazardIdentity_form_m2').combobox('setValue','');
	       	        	
	       	        	$('#bis_hazardIdentity_form_m4').textbox('setValue','');
	       	        	
					    var url = '${ctx}/bis/hazardIdentity/flsmjson?text='+rec.text;
					    $('#bis_hazardIdentity_form_m7').combobox('reload', url);
					} "/></div></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">危险性分类及说明：</label></td>
					<td class="width-35" colspan="3"><div><input name="M7" id="bis_hazardIdentity_form_m7" class="easyui-combobox" value="${qylist.m7 }" style="width:100%;height:30px;" data-options="editable:false ,
							panelHeight:'auto',editable:false, valueField: 'id',textField: 'text',
							loader:function (param, success, error) {
								var t1=  $('#bis_hazardIdentity_form_m1').combobox('getText');
								if(t1!=null&&t1!=''){
									$.ajax({
							           url:'${ctx}/bis/hazardIdentity/flsmjson',
							           data:{'text':t1},
							           dataType : 'json',
							           type : 'POST',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#bis_hazardIdentity_form_m7').combobox('loadData', data);
							           }
							     });
								}
							},
							onChange: function(value){
							var t1= $('#bis_hazardIdentity_form_m1').combobox('getValue');
							$.ajax({
						           url:'${ctx}/bis/hazardIdentity/ljljson2',
						           data:{'text':value,'t1':t1},
						           dataType : 'json',
						           type : 'POST',
						           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
						           success: function (data){
						                $('#bis_hazardIdentity_form_m4').textbox('setValue', data);
						                var m3=$('#bis_hazardIdentity_form_m3').val();
									    var m4=data;
									    $('#bis_hazardIdentity_form_m5').val(m3/m4);
									    
									    $('#bis_hazardIdentity_form_m2').combobox('setValue','');
	       	        					$('#bis_hazardIdentity_form_m2').combobox({ required: false });
						           }
						     });
					} "/></div></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">辨识物质：</label></td>
					<td class="width-35" colspan="3"><div><input name="M2" id="bis_hazardIdentity_form_m2" class="easyui-combobox" value="${qylist.m2 }" style="width:100%;height:30px;" data-options="
							panelHeight:'180',valueField: 'text',textField: 'text',url:'${ctx}/bis/hazardIdentity/wznmlist',
							 onSelect: function(value){
							 
							 	$('#bis_hazardIdentity_form_m1').combobox('setValue','');
     	        				$('#bis_hazardIdentity_form_m7').combobox('setValue','');
	       	        			
								$.ajax({
							           url:'${ctx}/bis/hazardIdentity/ljljson',
							           data:{'text':value.text},
							           dataType : 'json',
							           type : 'POST',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#bis_hazardIdentity_form_m4').textbox('setValue', data);
							                var m3=$('#bis_hazardIdentity_form_m3').val();
										    var m4=data;
										    $('#bis_hazardIdentity_form_m5').val(m3/m4);
										    $('#bis_hazardIdentity_form_m2').combobox('setValue',value.text);
							           }
							     });
							     
							} " /></div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最大储量(t)：</label></td>
					<td class="width-35"><div><input name="M3" id="bis_hazardIdentity_form_m3" class="easyui-textbox" value="${qylist.m3 }" style="width:100%;height:30px;" data-options="required:true,validType:'mone' "/></div></td>
					<td class="width-15 active"><label class="pull-right">临界储量(t)：</label></td>
					<td class="width-35"><div><input name="M4" id="bis_hazardIdentity_form_m4" class="easyui-textbox" value="${qylist.m4 }" style="width:100%;height:30px;" data-options="required:true,validType:'mone'"/></div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><div><input name="M6" class="easyui-textbox" value="${qylist.m6 }" style="width:100%;height:80px;" data-options="multiline:true "/></div></td>
				</tr>
					<c:if test="${not empty qylist.ID}">
						<input type="hidden" name="ID"  value="${qylist.ID}" />
						<input type="hidden" name="ID1" value="${qylist.ID1}" />
						<input type="hidden" name="S1"  value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
						<input type="hidden" name="S3"  value="${qylist.s3}" />
					</c:if>
			</table>
			<input type="hidden" name="M5" id="bis_hazardIdentity_form_m5" value="${qylist.m5}" />
				<input type="hidden" name="demo" id="demo" value="" />
            </form>

<script type="text/javascript">
var usertype=${usertype};
var returnId ;
if("${returnId}"!=""&&"${returnId}"!=null){
	returnId = "${returnId}";
	$("#demo").val(returnId);
}
</script>


</body>
</html>