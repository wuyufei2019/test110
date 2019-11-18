<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业分配任务</title>
	<meta name="decorator" content="default"/>
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
</head>
<body>
     <form id="inputForm" action="${ctx}/ztzyaqgl/sxzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" >
						<input id="M0" name="M0" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sxzy.m0 }" data-options="readonly:'true',required:'true',validType:'length[0,50]'" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sxzy.m1 }" data-options="readonly:'true',required:'true',validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间所属单位：</label></td>
					<td class="width-30"><input id="M2" name="M2" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m2 }" data-options="readonly:'true',validType:'length[0,50]'"/></td>
					<td class="width-20 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-30"><input id="M3" name="M3" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m3 }" data-options="readonly:'true',validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-30"><input id="M4" name="M4" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m4 }" data-options="readonly:'true',validType:'length[0,100]'"/></td>
					<td class="width-20 active"><label class="pull-right">空间内介质名称：</label></td>
					<td class="width-30"><input id="M5" name="M5" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m5 }" data-options="readonly:'true',validType:'length[0,50]'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30" ><input id="M6" name="M6" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sxzy.m6 }" data-options="readonly:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30" ><input id="M7" name="M7" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sxzy.m7 }" data-options="readonly:'true',editable:false,showSeconds:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="动火作业" disabled="disabled"/>动火作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="高处作业" disabled="disabled"/>高处作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="吊装作业" disabled="disabled"/>吊装作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="临时用电" disabled="disabled"/>临时用电</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="动土/断路作业" disabled="disabled"/>动土/断路作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="锁定" disabled="disabled"/>锁定</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="盲板抽堵" disabled="disabled"/>盲板抽堵</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="易燃易爆物质" disabled="disabled"/>易燃易爆物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="坠落" disabled="disabled"/>坠落</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="腐蚀性物质" disabled="disabled"/>腐蚀性物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="蒸汽" disabled="disabled"/>蒸汽</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="高压气体/液体" disabled="disabled"/>高压气体/液体</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="有毒有害物质" disabled="disabled"/>有毒有害物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="高温/低温" disabled="disabled"/>高温/低温</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="触电" disabled="disabled"/>触电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="窒息" disabled="disabled"/>窒息</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="噪音" disabled="disabled"/>噪音</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="产生火花/静电" disabled="disabled"/>产生火花/静电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="旋转设备" disabled="disabled"/>旋转设备</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="机械伤害" disabled="disabled"/>机械伤害</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="粉尘" disabled="disabled"/>粉尘</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="不利天气" disabled="disabled"/>不利天气</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="淹没/埋没" disabled="disabled"/>淹没/埋没</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="辐射" disabled="disabled"/>辐射</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="交叉作业" disabled="disabled"/>交叉作业</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="其他" disabled="disabled"/>其他</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30" ><input id="M10" name="M10" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m10 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-30" ><input id="M11" name="M11" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m11 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">监护人：</label></td>
					<td class="width-30" ><input id="M12" name="M12" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m12 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30" ><input id="M13" name="M13" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m13 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">分析人：</label></td>
					<td class="width-30" colspan="3"><input id="M14" name="M14" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m14 }" data-options="required:'true',panelHeight:'150px',editable:true,multiple:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">安全措施编制人：</label></td>
					<td class="width-30" ><input id="M15" name="M15" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m15 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">安全措施确认人：</label></td>
					<td class="width-30" ><input id="M16" name="M16" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m16 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位负责人：</label></td>
					<td class="width-30" ><input id="M17" name="M17" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m17 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30" ><input id="M18" name="M18" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m18 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">验收人：</label></td>
					<td class="width-30" ><input id="M19" name="M19" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sxzy.m19 }" data-options="required:'true',panelHeight:'150px',editable:true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">rfid：</label></td>
					<td class="width-30" ><input id="M20" name="M20" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sxzy.m20 }" data-options="editable:true,validType:'length[0,50]'" /></td>
				</tr>
				
				<input type="hidden" name="ID" value="${sxzy.ID}" />
				<input type="hidden" name="ID1" value="${sxzy.ID1}" />
				<input type="hidden" name="ID2" value="${sxzy.ID2}" />
				<input type="hidden" name="S3" value="${sxzy.s3}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${sxzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="zt" value="${sxzy.zt}" />
				<input type="hidden" name="M8" value="${sxzy.m8}" />
				<input type="hidden" name="M9" value="${sxzy.m9}" />
				</tbody>
			</table>
       </form>
<script type="text/javascript">
//涉及的其他特殊作业
var tszy = "${sxzy.m8}";
var tszyArr = tszy.split(",");
for(var i=0;i<tszyArr.length;i++){
	$("input[name='TSZY']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
}
//危害辨识
var whbs = "${sxzy.m9}";
var whbsArr = whbs.split(",");
for(var i=0;i<whbsArr.length;i++){
	$("input[name='WHBS']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
}
</script>
</body>
</html>