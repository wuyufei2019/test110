<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/tzgz/${action}"  method="post" class="form-horizontal" >
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<c:if test="${action eq 'updateSub'}">
				<tr>
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${yte.number }" style="width: 100%;height: 30px;" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-15 active" ><label class="pull-right">单位（个人）:</label></td>
					<td class="width-35"  >
					<input name="name"  class="easyui-textbox" value="${yte.name }" style="width: 100%;height: 30px;" data-options="validType:['length[0,50]'],required:'true'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-35"><input id="punishdate"name="punishdate" class="easyui-datebox" value="${yte.punishdate }" style="width: 100%;height: 30px;" data-options="required:'true'"/></td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-35" colspan="3">
						<input id="illegalact" name="illegalact"  class="easyui-textbox" value="${yte.illegalact }" style="width: 100%;height: 80px;" data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/>
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" colspan="3">
					<input id="evidence" name="evidence"  class="easyui-textbox" value="${yte.evidence }" style="width: 100%;height: 80px;" data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-35"  colspan="3">
					<input id="unlaw" name="unlaw"  class="easyui-textbox" value="${yte.unlaw }" style="width: 100%;height: 50px;" data-options="multiline:true , validType:['length[0,500]'],required:'true'"/>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-35" colspan="3">
					<input id="enlaw" name="enlaw"  class="easyui-textbox" value="${yte.enlaw }" style="width: 100%;height: 50px;" data-options="multiline:true , validType:['length[0,500]'],required:'true'"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚结果：</label></td>
					<td class="width-35" colspan="3">
					<input id="punishresult" name="punishresult"  class="easyui-textbox" value="${yte.punishresult }" style="width: 100%;height: 100px;" data-options="multiline:true , validType:['length[0,1000]'],required:'true'"/>
					</td>
				</tr>
			<%-- 	<tr>
					<td class="width-15 active"><label class="pull-right">联系人姓名：</label></td>
					<td class="width-35"><input name="contactname" class="easyui-textbox" value="${yte.contactname }" style="width: 100%;height: 30px;" /></td>
					<td class="width-15 active"><label class="pull-right">联系人电话：</label></td>
					<td class="width-35"><input name="phone" class="easyui-textbox" validtype="mobile" value="${yte.phone }" style="width: 100%;height: 30px;" /></td>
				</tr> --%>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${yte.s1}"/>"/>
				 <input type="hidden" name="ID" value="${yte.ID}" />
				 <input type="hidden" name="id1" value="${yte.id1}" />
		</table>
	</div>
	</tbody>
</form>
<script type="text/javascript">
 $(function(){
	 if('${action}'=='createSub')
	 $("input[name='ID']").val('');
	 
	 $('#punishdate').datebox('setValue', format(new Date()));	
}); 
 function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
}; 

var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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
			return false; //返回false终止表单提交
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