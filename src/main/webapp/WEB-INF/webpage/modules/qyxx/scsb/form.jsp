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
	$('#M2').combobox('setValue',$('#M2').combobox('getText'));
	$('#bis_scsb_form_mainform_M3').combobox('setValue',$('#bis_scsb_form_mainform_M3').combobox('getText'));
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

     <form id="inputForm" action="${ctx}/bis/scsb/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${scsblist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${scsblist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr >
					<td class="width-15 active"><label class="pull-right">设备位号：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m1 }" data-options=" editable:true,validType:'length[0,100]' " />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备类别：</label></td>
					<td class="width-35"><input id="M2" name="M2" value="${scsblist.m2 }" class="easyui-combobox" style="width: 100%;height: 30px;"
				data-options="required:true ,valueField:'text',textField:'text',url:'${ctx }/bis/scsb/sblblist',
								onSelect : function(rec){
											 	$.ajax({
											 	url:'${ctx}/bis/scsb/sbmclist',
					 							data:{'sblb':rec.text},
					 							dataType:'json',
					 							type : 'POST',
					 							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 				 			success: function (data){
					 				 			$('#bis_scsb_form_mainform_M3').combobox('clear');
						           				$('#bis_scsb_form_mainform_M3').combobox('loadData', data);
						           				}
										    });
										 }
								"/></td>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35"><input id="bis_scsb_form_mainform_M3" name="M3" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.m3 }"  data-options="validType:'length[0,100]',required:true ,valueField: 'text',textField: 'text',
					loader:function (param, success, error) {
								var sblb=$('#M2').combobox('getText');
						   	 		if(sblb!=''){
									$.ajax({
							       	url:'${ctx}/bis/scsb/sbmclist',
						           	data:{'sblb':sblb},
						           	dataType : 'json',
						           	type : 'POST',
						           	contentType:'application/x-www-form-urlencoded; charset=UTF-8',
						           	success: function (data){
						           	$('#bis_scsb_form_mainform_M3').combobox('loadData', data);
						           	}
							    });
						    }
						}
					 " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格尺寸：</label></td>
					<td class="width-35"><input id="M4" name="M4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m4 }" data-options="validType:'length[0,100]'" /></td>
					<td class="width-15 active"><label class="pull-right">型号：</label></td>
					<td class="width-35"><input id="bis_scsb_form_mainform_M5" name="M5" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.m5 }" data-options="validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">功率（KW）：</label></td>
					<td class="width-35"><input id="" name="M14" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m14 }" data-options="validType:'mone'" /></td>
					<td class="width-15 active"><label class="pull-right">出厂日期：</label></td>
					<td class="width-35"><input id="" name="M15" style="width: 100%;height: 30px;" class="easyui-datebox" value="${scsblist.m15 }" data-options=" editable:false "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备状况：</label></td>
					<td class="width-35"><input id="" name="M16" class="easyui-combobox" style="width: 100%;height: 30px;" value="${scsblist.m16 }" data-options="editable : false, panelHeight:80, data:[
										{value:'完好',text:'完好'},
								        {value:'待修',text:'待修'},
								        {value:'待报废',text:'待报废'}]
							" /></td>
					<td class="width-15 active"><label class="pull-right">设备使用单位：</label></td>
					<td class="width-35"><input id="" name="M17" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.m17 }" data-options="editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要技术参数：</label></td>
					<td class="width-85" colspan="3">
					<input name="M18" type="text" value="${scsblist.m18 }"   class="easyui-textbox" style="width: 100%;height: 50px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m6 }" data-options=" editable:true ,validType:'integ' " /></td>
					<td class="width-15 active"><label class="pull-right">介质：</label></td>
					<td class="width-35" >
							<input id="bis_scsb_form_mainform_M9" name="M9" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.m9 }" data-option="validType:'length[0,100]'"/>
					</td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">投用日期：</label></td>
					<td class="width-35" ><input id="M8" name="M8" class="easyui-datebox" style="width: 100%;height: 30px;" value="${scsblist.m8 }" data-options=" editable:false " /></td>
					<td class="width-15 active"><label class="pull-right">下次检验日期：</label></td>
					<td class="width-35" >
							<input id="bis_scsb_form_mainform_M10" name="M10" style="width: 100%;height: 30px;" class="easyui-datebox" value="${scsblist.m10 }" data-options=" editable:false " />
					</td>
				</tr>
								<tr >
					<td class="width-15 active"><label class="pull-right">制造单位：</label></td>
					<td class="width-35" colspan="3">
						<input id="M7" name="M7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m7 }" data-options=" editable:true,validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">责任人：</label></td>
					<td class="width-35" ><input id="M11" name="M11" class="easyui-textbox" style="width: 100%;height: 30px;" value="${scsblist.m11 }" data-options=" editable:true ,validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" >
							<input id="bis_scsb_form_mainform_M12" name="M12" style="width: 100%;height: 30px;" class="easyui-textbox" value="${scsblist.m12 }" data-options="validType:'mobileAndTel'"/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-85" colspan="3">
					<input name="M13" type="text" value="${scsblist.m13}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>
				</tr>

				<c:if test="${not empty scsblist.ID}">
					<input type="hidden" name="ID" value="${scsblist.ID}" />
					<input type="hidden" name="ID1" value="${scsblist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${scsblist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${scsblist.s3}" />
				</c:if>
				</tbody>
			</table>


       </form>

<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>