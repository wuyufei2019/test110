<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急组织职责</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yjzzzz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">组织名称：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" name="M1" class="easyui-textbox " value="${res.m1 }" data-options="required:'true',validType:'length[0,25]'"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">组织负责人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M2" class="easyui-textbox " value="${res.m2 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">负责人联系电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M3" class="easyui-textbox " value="${res.m3 }" data-options="validType:'mobile'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">组织联系人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M4" class="easyui-textbox " value="${res.m4 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">组织联系人电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;"  name="M5" class="easyui-textbox " value="${res.m5 }" data-options="validType:'mobile'"/></td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">组织应急职责：</label></td>
					<td class="width-35" colspan="3">
						<input name="M6" type="text" value="${res.m6 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>
					
				</tr>
				
				
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${res.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
					
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="userid" value="${res.userid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

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