<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>备品备件</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/bpbj/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" id="data_table">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">备品备件名称：</label></td>
					<td class="width-30">
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${bpbj.m2 }" data-options="required: true,validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-30">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${bpbj.m3 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">数量：</label></td>
					<td class="width-30">
						<input id="m6" name="m6" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${bpbj.m6 }" data-options="required: true,validType:'length[0,10]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">单位：</label></td>
					<td class="width-30">
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${bpbj.m4 }" data-options="validType:'length[0,10]', prompt: '例如，台'" />
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">最低安全库存：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${bpbj.m1 }" data-options="required: true,validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">制造商：</label></td>
					<td class="width-30">
						<input id="m7" name="m7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${bpbj.m7 }" data-options="validType:'length[0,25]'" />
				   	</td>
				</tr>
				<tr>
					 <td class="width-20" colspan="4" style="text-align: center;">
					 	 <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addInfo()" title="添加备品备件信息"><i class="fa fa-plus"></i> 添加备品备件信息</a>
					 </td>
				</tr>
			</tbody>
		</table>	
		<input type="hidden" name="sbtype" value="${sbtype}" />
		<input type="hidden" name="sbid" value="${sbid}" />
		<c:if test="${not empty bpbj.ID}">
			<input type="hidden" name="ID" value="${bpbj.ID}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${bpbj.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${bpbj.s3}" />
			<input type="hidden" name="m9" value="${bpbj.m9}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var tindex = 1;
$(function(){
	var bpbjList = eval(${bpbjlist});
	if (bpbjList.length > 0) {
		for (var i = 0; i < bpbjList.length; i++) {
			if (i == 0) {
				$("#m2").textbox('setValue', bpbjList[i].m2);
				$("#m3").textbox('setValue', bpbjList[i].m3);
				$("#m6").numberbox('setValue', bpbjList[i].m6);
				$("#m4").textbox('setValue', bpbjList[i].m4);
				$("#m7").textbox('setValue', bpbjList[i].m7);
				$("#m1").textbox('setValue', bpbjList[i].m1);
			} else {
				var index = i;
				var targetObj =$(
	   				'<table id="cz" class="table table-bordered  table-condensed dataTables-example dataTable no-footer"><tbody><tr>'
                    + '    <td class="width-20 active" colspan="4" style="text-align: center;">'
                    + '        <button class="btn btn-info btn-sm" style="width:80px;float: right;" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent().parent().parent());" title="删除"> 删除</button>'
                    + '    </td>'
                    + '</tr>'
		            +'<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">备品备件名称：</label></td>'
     				+ '    <td class="width-30"><input name="m2" id="m2_'+index+'" value="'+bpbjList[i].m2+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="required: true,validType:[\'length[0,50]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">规格型号：</label></td>'
     				+ '    <td class="width-30"><input name="m3" id="m3_'+index+'" value="'+bpbjList[i].m3+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,50]\']" /></td>'
                    + '</tr>'
                    + '<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">数量：</label></td>'
     				+ '    <td class="width-30"><input name="m6" id="m6_'+index+'" value="'+bpbjList[i].m6+'" style="width: 100%;height: 30px;" class="easyui-numberbox"'
     				+ '	   	   value="" data-options="required: true,validType:[\'length[0,10]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">单位：</label></td>'
     				+ '    <td class="width-30"><input name="m4" id="m4_'+index+'" value="'+bpbjList[i].m4+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,10]\'], prompt: \'例如，台\'" /></td>'
                    + '</tr>'
                    + '<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">最低安全库存：</label></td>'
     				+ '    <td class="width-30" ><input name="m1" id="m1_'+index+'" value="'+bpbjList[i].m1+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '        value="" data-options="required: true,validType:[\'length[0,10]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">制造商：</label></td>'
     				+ '    <td class="width-30"><input name="m7" id="m7_'+index+'" value="'+bpbjList[i].m7+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,25]\']" /></td>'
     				+ '</tr>'
                    + '</tbody></table>'
			 	).appendTo("#inputForm");
			 	$.parser.parse(targetObj);	
			}
		}
	} 
})
	function addInfo(){
	   var targetObj =$(
	   				'<table id="cz" class="table table-bordered  table-condensed dataTables-example dataTable no-footer"><tbody><tr>'
                    + '    <td class="width-20 active" colspan="4" style="text-align: center;">'
                    + '        <button class="btn btn-info btn-sm" style="width:80px;float: right;" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent().parent().parent());" title="删除"> 删除</button>'
                    + '    </td>'
                    + '</tr>'
		            +'<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">备品备件名称：</label></td>'
     				+ '    <td class="width-30"><input name="m2" id="m2_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="required: true,validType:[\'length[0,50]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">规格型号：</label></td>'
     				+ '    <td class="width-30"><input name="m3" id="m3_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,50]\']" /></td>'
                    + '</tr>'
                    + '<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">数量：</label></td>'
     				+ '    <td class="width-30"><input name="m6" id="m6_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-numberbox"'
     				+ '	   	   value="" data-options="required: true,validType:[\'length[0,10]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">单位：</label></td>'
     				+ '    <td class="width-30"><input name="m4" id="m4_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,10]\'], prompt: \'例如，台\'" /></td>'
                    + '</tr>'
                    + '<tr>'
                    + '    <td class="width-20 active"><label class="pull-right">最低安全库存：</label></td>'
     				+ '    <td class="width-30" ><input name="m1" id="m1_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '        value="" data-options="required: true,validType:[\'length[0,10]\']" /></td>'
     				+ '    <td class="width-20 active"><label class="pull-right">制造商：</label></td>'
     				+ '    <td class="width-30"><input name="m7" id="m7_'+tindex+'" style="width: 100%;height: 30px;" class="easyui-textbox"'
     				+ '	   	   value="" data-options="validType:[\'length[0,25]\']" /></td>'
     				+ '</tr>'
                    + '</tbody></table>'
			 ).appendTo("#inputForm");
				 
		 tindex++;
		 
		 $.parser.parse(targetObj);	 
	}
	
//删除添加的table
function removeTr(obj){
	obj.remove();
}
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