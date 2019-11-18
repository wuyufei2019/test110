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
				<tr>
					<td class="width-15 active"><label class="pull-right">预约确认人员：</label></td>
					<td class="width-35">
						<input name="M6" type="text" class="easyui-textbox" value="${entity.m6 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">预约确认时间：</label></td>
					<td class="width-35">
						<input name="M7" class="easyui-datetimebox" value="${entity.m7 }" style="width: 100%;height: 30px;" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂确认人员：</label></td>
					<td class="width-35">
						<input name="M8" type="text" class="easyui-textbox" value="${entity.m8 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">出厂确认人员：</label></td>
					<td class="width-35">
						<input name="M11" type="text" class="easyui-textbox" value="${entity.m11 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂时间：</label></td>
					<td class="width-35">
						<input name="M9" class="easyui-datetimebox" value="${entity.m9 }" style="width: 100%;height: 30px;" data-options="editable:false" />
					</td>
					<td class="width-15 active"><label class="pull-right">出厂时间：</label></td>
					<td class="width-35">
						<input name="M12" class="easyui-datetimebox" value="${entity.m12 }" style="width: 100%;height: 30px;" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">进厂人数：</label></td>
					<td class="width-35" >
						<input name="M10" type="text" class="easyui-numberbox" value="${entity.m10 }" style="width: 100%;height: 30px;"  data-options="min:0" />
					</td>
					<td class="width-15 active"><label class="pull-right">出厂人数：</label></td>
					<td class="width-35" >
						<input name="M13" type="text" class="easyui-numberbox" value="${entity.m13 }" style="width: 100%;height: 30px;"  data-options="min:0" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">预约状态：</label></td>
					<td class="width-35">
						<input name="status" type="text" class="easyui-combobox" value="${entity.status }" style="width: 100%;height: 30px;" required="required" data-options="panelHeight:'auto',editable:false,data: [
										{value:'1',text:'预约确认中'},
								        {value:'2',text:'拒绝预约'},
								        {value:'3',text:'预约通过待进厂'},
								        {value:'4',text:'进厂待出厂'},
								        {value:'5',text:'已出厂'}]"/>	
					</td>
				</tr>
				<input type="hidden" name="ID" value="${entity.ID}" />
				<input type="hidden" name="qyid" value="${entity.qyid}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="S3" value="${entity.s3}" />
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