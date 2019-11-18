<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var usertype=${usertype};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
	$('#sas').combobox('setValue',$('#sas').combobox('getText'));
	$("#inputForm").submit(); 
}
$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
	
	</script>
</head>
<body>
     <form id="inputForm" action="${ctx}/bis/wlxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${wllist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${wllist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
<%-- 				<tr >
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
					<c:when test="${wllist.m11=='2'}">
						<input type="radio" value="1" class="i-checks"  name="M11"  />原料
						<input type="radio" value="2" class="i-checks"  name="M11" checked="checked"/>产品
					</c:when>
					<c:otherwise>
						<input type="radio" value="1" class="i-checks" name="M11" checked="checked"/>原料
						<input type="radio" value="2" class="i-checks"  name="M11"/>产品
					</c:otherwise>
					</c:choose>
					</td>
				</tr> --%>
				<tr >
					<td class="width-20 active"><label class="pull-right">中文名：</label></td>
					<td class="width-30" colspan="3">
					<input id="sas" name="M1" style="width: 100%;height: 30px;" class="easyui-combobox"
								value="${wllist.m1 }" data-options="required:'true', 
								valueField: 'id',
							 	textField: 'text',
							 	editable:true ,
							 	mode : 'remote',
								loader:function (param, success, error) {
								    $.ajax({  
								        url:wfwurl+'/MicroService/fxbs/data/wlnames',
								        type: 'post',  
								        data:{'m1':$('#sas').combobox('getText')},
								        dataType: 'json',
								        contentType:'application/x-www-form-urlencoded; charset=UTF-8',  
								        success: function (data) {  
								            var items = $.each(data, function(value){  
								                return value;
								            });
								            success(items);
								        }  
								    });
								 },
								onSelect: function(rec){
							  	
							  	$.ajax({
							           url:wfwurl+'/MicroService/fxbs/data/wldata',
							           data:{'m1':rec.text},
							           dataType : 'text',
							           type : 'POST',
							            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							           		var dataObj = JSON.parse(data);
							                $('#bis_wlxx_form_a2').textbox('setValue',dataObj[0].m4);
							                $('#validate_wl').val($('#sas').combobox('getValue'));
							                $('#validate_zdjd').val(dataObj[0].m7);
							                $('#validate_jd').val(dataObj[0].m8);
							                $('#validate_yzd').val(dataObj[0].m9);
							                $('#bis_wlxx_form_a6').combobox('setValue',dataObj[0].m5);
							                $('#bis_wlxx_form_M12').combobox('setValue',dataObj[0].m7);
							                $('#bis_wlxx_form_a13').combobox('setValue',dataObj[0].m8);
							                $('#bis_wlxx_form_a14').combobox('setValue',dataObj[0].m9);
							           }
							     });
							   }" />
					</td>
				</tr>
				
				 <tr>
					<td class="width-20 active"><label class="pull-right">别名：</label></td>
					<td class="width-30"><input name="chemicalalias" class="easyui-textbox" value="${wllist.chemicalalias }" style="width: 100%;height: 30px;" data-options="validType:'length[0,100]' "/></td>
					<td class="width-20 active"><label class="pull-right">化学品类型：</label></td>
					<td class="width-30"><input name="M11" class="easyui-combobox" value="${wllist.m11 }" style="width: 100%;height: 30px;" 
							data-options="panelHeight:'auto',required:'true',data:[{value:'1',text:'产品'},{value:'2',text:'中间产品'},{value:'3',text:'进口化产品'},{value:'4',text:'原料'}]"/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30"><input id="bis_wlxx_form_a2" class="easyui-textbox" name="M4" value="${wllist.m4 }"
								 style="width: 100%;height: 30px;"
								data-options="required:'true',validType:'length[0,100]' " /></td>
					<td class="width-20 active"><label class="pull-right">储存方式：</label></td>
					<td class="width-30"><input class="easyui-combobox" id="ccfs" name="M5" value="${wllist.m5 }"
								style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto',
									required:'true',editable:false ,data: [
									        {value:'1',text:'储罐'},
									        {value:'2',text:'桶装'},
									        {value:'3',text:'袋装'},
									        {value:'4',text:'其他'} ]
							    " /></td>				
				</tr> 
				
				<tr>
					<td class="width-20 active"><label class="pull-right">产品生产能力（吨）：</label></td>
					<td class="width-30"><input name="capacity" class="easyui-textbox" value="${wllist.capacity }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,15]'] "/></td>
					<td class="width-20 active"><label class="pull-right">产品最大储量（吨）：</label></td>
					<td class="width-30"><input name="reserve" class="easyui-textbox" value="${wllist.reserve }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,15]'] "/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">产品生产能力气体（方）：</label></td>
					<td class="width-30"><input name="capacitygas" class="easyui-textbox" value="${wllist.capacitygas }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,15]'] "/></td>
					<td class="width-20 active"><label class="pull-right">产品最大储量气体（方）：</label></td>
					<td class="width-30"><input name="reservegas" class="easyui-textbox" value="${wllist.reservegas }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,15]'] "/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">危化品作用：</label></td>
					<td class="width-30"><input name="chemicaltype" class="easyui-combobox" value="${wllist.chemicaltype }" style="width: 100%;height: 30px;" 
							data-options="panelHeight:'auto',data:[{value:'HEX01',text:'原料'},{value:'HEX02',text:'中间产物'},{value:'HEX03',text:'产品'},{value:'HEX04',text:'副产品'}]"/></td>
					
					<td class="width-20 active"><label class="pull-right">是否重点监管危化品：</label></td>
					<td class="width-30" ><input class="easyui-combobox" id="bis_wlxx_form_M12" name="M12" value="${wllist.m12 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto',editable:false ,data: [
										{value:'1',text:'是'},
								        {value:'0',text:'否'}]
						    "/></td>
				</tr>
				
				
				<tr >
					<td class="width-20 active"><label class="pull-right">危险类别：</label></td>
					<td class="width-30" colspan="3">
					<input id="bis_wlxx_form_a3" name="M8" style="width: 100%;height: 30px;" class="easyui-combobox"
								value="${wllist.m8 }"
								data-options=" 
								valueField: 'id',
							 	textField: 'text',
							 	editable:false ,
							 	mode : 'remote',
								loader:function (param, success, error) {
									if(param.p==undefined||param.p==''){
									}  
								    $.ajax({  
								        url:'${ctx}/tcode/wlwxxlbs/wxhxpfl',
								        type: 'post',  
								        data:{},
								        dataType: 'json',
								        contentType:'application/x-www-form-urlencoded; charset=UTF-8',  
								        success: function (data) {  
								            var items = $.each(data, function(value){  
								                return value;
								            });
								            success(items);
								        }  
								    });
								 } "/>
					</td>
				</tr>
 				<tr >
					<td class="width-20 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-30" colspan="3">
					<input class="easyui-combobox" name="M6" id="bis_wlxx_form_a6" value="${wllist.m6 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px',
								 editable:false ,valueField: 'text',textField: 'text',
								url:'${ctx}/tcode/dict/wxhxpzywx',multiple:true,
								onLoadSuccess: 
								function(){
									$('#aaa').val($('#bis_wlxx_form_a6').combobox('getText'));
								}
								 "/>
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">状态：</label></td>
					<td class="width-30" ><input class="easyui-combobox" id="wlxx_form_m9" name="M9" value="${wllist.m9 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto', editable:false ,data: [
								        {value:'0',text:'正常'},
								        {value:'1',text:'注销'}]
						    "/></td>
					<td class="width-20 active"><label class="pull-right">是否领证：</label></td>
					<td class="width-30" >
							<input class="easyui-combobox" id="wlxx_form_m10" name="M10" value="${wllist.m10 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto',editable:false ,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'} ]
						    "/>
						</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">剧毒：</label></td>
					<td class="width-30" >
							<input class="easyui-combobox" id="bis_wlxx_form_a13" name="M13" value="${wllist.m13 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto',editable:false ,data: [
										{value:'1',text:'是'},
								        {value:'0',text:'否'} ]
						    "/>
						</td>
					<td class="width-20 active"><label class="pull-right">易制毒：</label></td>
					<td class="width-30" ><input class="easyui-combobox" id="bis_wlxx_form_a14" name="M14" value="${wllist.m14 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto',editable:false ,data: [
										{value:'1',text:'是'},
								        {value:'0',text:'否'} ]
						    "/></td>		
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${wllist.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[1,250]'">
					</td>
					
				</tr>
				<c:if test="${not empty wllist.ID}">
					<input type="hidden" name="ID" value="${wllist.ID}" />
					<input type="hidden" name="ID1" value="${wllist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${wllist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${wllist.s3}" />
					<input type="hidden" name="companycode" value="${wllist.companycode}"/>
           			<input type="hidden" name="districtcode" value="${wllist.districtcode}"/>
           			<input type="hidden" name="parkid" value="${wllist.parkid}"/>
           			<input type="hidden" name="uuid" value="${wllist.uuid}"/>
				</c:if>
				</tbody>
			</table>

		  	
       </form>


<script type="text/javascript">
/* $('.i-checks').on('ifChecked', function(event){    
    var selectedvalue = $(event.target).val();
	if (selectedvalue == 1) {
		$("#wlxx_form_m9").combobox('disable');
		$("#wlxx_form_m10").combobox('disable');
	} 
	if (selectedvalue == 2){
		$("#wlxx_form_m9").combobox('enable');
		$("#wlxx_form_m10").combobox('enable');
	}
      
});   */


/* $(function(){ 
var lb = $("input[name='M11']:checked").val();
if (lb == 1) {
 		$("#wlxx_form_m9").combobox('disable');
		$("#wlxx_form_m10").combobox('disable');
}if (lb == 2){
 		$("#wlxx_form_m9").combobox('enable');
		$("#wlxx_form_m10").combobox('enable');
 }
 }) */
</script>


</body>
</html>