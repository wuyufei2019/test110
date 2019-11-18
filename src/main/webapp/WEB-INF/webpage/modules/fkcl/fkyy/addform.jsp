<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>访客预约信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
    <form id="inputForm" action="${ctx}/fkcl/fkyy/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			 <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约时间：</label></td>
					<td class="width-35">
					<input name="M1" class="easyui-datetimebox" value="${entity.m1 }" style="width: 100%;height: 30px;" required="required" data-options="editable:false" />
					</td>
					<td class="width-15 active"><label class="pull-right">被预约人：</label></td>
					<td class="width-35">
					<input name="M2" type="text" class="easyui-textbox" value="${entity.m2 }" style="width: 100%;height: 30px;" required="required" data-options="validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">事由：</label></td>
					<td class="width-85" colspan="3">
					<input name="M3" type="text" class="easyui-textbox" value="${entity.m3 }" style="width: 100%;height: 80px;" required="required" data-options="multiline:true, validType:'length[0,250]' ">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约人：</label></td>
					<td class="width-35">
					<input name="M4" type="text" class="easyui-textbox" value="${entity.m4 }" style="width: 100%;height: 30px;" required="required" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">手机号码：</label></td>
					<td class="width-35">
					<input name="M5" type="text" class="easyui-textbox" value="${entity.m5 }" style="width: 100%;height: 30px;" required="required" data-options="validType:'mobileAndTel'" />
					</td>
				</tr>
			</tbody>
		</table>
    </form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

function doSubmit(){
	$("#inputForm").serializeObject();
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
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>