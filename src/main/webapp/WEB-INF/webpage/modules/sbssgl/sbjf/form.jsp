<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备启动单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbjf/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">调出单位：</label></td>
					<td class="width-30" >
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m1 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">调入单位：</label></td>
					<td class="width-30" >
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m2 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr >
					<td class="width-20 active"><label class="pull-right">事由：</label></td>
					<td class="width-80" colspan="3">
						<input id="m3" name="m3" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbjf.m3 }" 
							data-options="required:'true',editable: false, panelHeight: 'auto',
								data:[
									{value: '启用', text: '启用'},
									{value: '封存', text: '封存'},
									{value: '调拨', text: '调拨'}
								]" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m4 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">名称型号：</label></td>
					<td class="width-30" >
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m5 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >
						<input id="m6" name="m6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m6 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">账面原值（元）：</label></td>
					<td class="width-30" >
						<input id="m7" name="m7" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m7 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">现值（元）：</label></td>
					<td class="width-30" >
						<input id="m8" name="m8" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m8 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">残值（元）：</label></td>
					<td class="width-30" >
						<input id="m9" name="m9" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m9 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">折旧年限（年）：</label></td>
					<td class="width-30" >
						<input id="m10" name="m10" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m10 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">已提折旧（年）：</label></td>
					<td class="width-30" >
						<input id="m11" name="m11" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m11 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">年折旧额（元/年）：</label></td>
					<td class="width-30" >
						<input id="m12" name="m12" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbjf.m12 }" data-options="min:0,validType:'length[0,10]'" />
					</td>
				</tr> 
				<tr >
					<td class="width-20 active"><label class="pull-right">折旧方法：</label></td>
					<td class="width-80" colspan="3">
						<input id="m13" name="m13" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m13 }" data-options="validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">完好程度：</label></td>
					<td class="width-30" >
						<input id="m14" name="m14" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbjf.m14 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">启用、封存、调拨日期：</label></td>
					<td class="width-30" >
						<input id="m15" name="m15" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbjf.m15 }" data-options="required:'true',editable:false" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">封存开始日期：</label></td>
					<td class="width-30" >
						<input id="m16" name="m16" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbjf.m16 }" data-options="editable:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">封存结束日期：</label></td>
					<td class="width-30" >
						<input id="m17" name="m17" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbjf.m17 }" data-options="editable:false" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">记事：</label></td>
					<td class="width-80" colspan="3">
						<input style="width:100%;height: 100px;" id="m18"name="m18"  class="easyui-textbox" value="${sbjf.m18 }" data-options="multiline:true,validType:'length[0,1000]'" />
				   	</td>
				</tr>
			</tbody>
		</table>	
		
		<input type="hidden" name="sbysid" value="${sbjf.sbysid}" />
		<c:if test="${action eq 'create' }">
			<input type="hidden" name="qyid" value="${sbjf.qyid}" />
		</c:if>
		<c:if test="${not empty sbjf.ID}">
			<input type="hidden" name="ID" value="${sbjf.ID}" />
			<input type="hidden" name="qyid" value="${sbjf.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbjf.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbjf.s3}" />
			<input type="hidden" name="m20" value="${sbjf.m20}" />
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