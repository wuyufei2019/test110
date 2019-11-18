<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考核规则</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/wgykpi/month/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">年月：</label></td>
					<td class="width-80">
						<input type="text" class="easyui-textbox" value="${mon.time }"  data-options="readonly:'true'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">评分项目：</label></td>
					<td class="width-80">
						<input type="text"  class="easyui-textbox" value="${mon.name }"  data-options="readonly:'true'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">考核内容：</label></td>
					<td class="width-80">
						<input type="text"  class="easyui-textbox" value="${mon.content }"  data-options="readonly:'true',multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
               <tr >
                  <td class="width-20 active"><label class="pull-right">得分：</label></td>
                  <td class="width-80">
                     <input type="text" class="easyui-textbox" name="score" value="${mon.score }"  data-options="required:'true',validType:'number'" style="width: 100%;height: 30px;" />
                  </td>
               </tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80">
						<input type="text" name="note" class="easyui-textbox" value="${mon.note }"  data-options="validType:'length[0,500]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<c:if test="${not empty mon.id}">
					<input type="hidden" name="ID" value="${mon.id}" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
var code='${code}';
if(code) $("#wgcode").val(code);
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
	    	parent.d.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
	</script>
</body>
</html>