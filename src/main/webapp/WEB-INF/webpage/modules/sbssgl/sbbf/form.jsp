<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备报废</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbbf/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbbf.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
										  onSelect: function(row){
										  	$('#m23').combobox('setValue', '');
											$('#m23').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
										  }" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-30">
						<input id="m23" name="m23" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbbf.m23 }" 
							data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/system/department/deptjson',
								onSelect: function(row){
									if ('${sbtype}' == '0') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson4/'+row.id);
									} else if ('${sbtype}' == '1') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson4/'+row.id);
									}
								}
							" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
				<c:if test="${sbtype eq '0'}">
					<td class="width-30">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbbf.m2 }" 
							data-options="panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/sbssgl/sbgl/sbjson/',
								onSelect: function(row) {
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson/'+row.id,
										success: function(data){
											var rdata = JSON.parse(data);
											$('#sbid').val(rdata.id);
											$('#m1').textbox('setValue', rdata.m1);
											$('#m3').textbox('setValue', rdata.m3);
											$('#m4').textbox('setValue', rdata.m4);
											$('#m5').textbox('setValue', rdata.m5);
											$('#m6').datebox('setValue', new Date(rdata.m6).format('yyyy-MM-dd'));
										}
									});
								}
							" />
					</td>
				</c:if>
				<c:if test="${sbtype eq '1'}">
					<td class="width-30">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbbf.m2 }" 
							data-options="editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/sbssgl/sbgl/tzsbjson/',
								onSelect: function(row) {
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson/'+row.id,
										success: function(data){
											var rdata = JSON.parse(data);
											$('#sbid').val(rdata.id);
											$('#m1').textbox('setValue', rdata.m1);
											$('#m3').textbox('setValue', rdata.m3);
											$('#m4').textbox('setValue', rdata.m4);
											$('#m5').textbox('setValue', rdata.m5);
											$('#m6').datebox('setValue', new Date(rdata.m6).format('yyyy-MM-dd'));
											$('#m23').textbox('setValue', rdata.deptname);
											$('#deptid').val(rdata.m23);
										}
									});
								}
							" />
					</td>
				</c:if>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbbf.m1 }" data-options="validType:'length[0,50]'" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbbf.m3 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30">
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbbf.m4 }" data-options="validType:'length[0,50]'" />
				   	</td>
					<td class="width-20 active"><label class="pull-right">设备制造商：</label></td>
					<td class="width-30">
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbbf.m5 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">购入时间：</label></td>
					<td class="width-30">
						<input id="m6" name="m6" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbbf.m6 }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">资产原/净值：</label></td>
					<td class="width-30">
						<input id="zcy" name="zcy" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbbf.zcy }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">规定使用年限（年）：</label></td>
					<td class="width-30">
						<input id="gdsynx" name="gdsynx" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbbf.gdsynx }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">实际使用年限（年）：</label></td>
					<td class="width-30">
						<input id="sjsynx" name="sjsynx" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbbf.sjsynx }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">报废原因：</label></td>
					<td class="width-80" colspan="3">
						<input id="reason" name="reason" class="easyui-textbox" style="width: 100%;height: 80px;" value="${sbbf.reason }" data-options="multiline: true, validType:'length[0,100]'" />
				   	</td>
				</tr>
			</tbody>
		</table>	
		<input type="hidden" id="sbid" name="sbid" value="${sbbf.sbid }" />
		<c:if test="${not empty sbbf.id}">
			<input type="hidden" name="ID" value="${sbbf.id}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbbf.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbbf.s3}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

function doSubmit(){
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
</body>
</html>