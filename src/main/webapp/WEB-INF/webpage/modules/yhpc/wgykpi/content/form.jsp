<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考核规则</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/wgykpi/content/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">评分项目：</label></td>
					<td class="width-80">
						<input type="text" name="name" class="easyui-textbox" value="${cont.name }" <c:if test="${cont.name eq '安全巡查' }"> readonly="true"</c:if> data-options="required:'true',validType:'length[0,100]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">项目总分：</label></td>
					<td class="width-80">
						<input type="text" name="score" class="easyui-textbox" value="${cont.score }"  data-options="required:'true',validType:'number'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">考核内容：</label></td>
					<td class="width-80">
						<input type="text" name="content" class="easyui-textbox" value="${cont.content }"  data-options="multiline:true,required:'true',validType:'length[0,500]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80">
						<input type="text" name="note" class="easyui-textbox" value="${cont.note }"  data-options="validType:'length[0,500]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
            
					<input type="hidden" id="id1" name="id1" value="${cont.id1}" />
            <c:if test="${not empty cont.ID}">
               <input type="hidden" name="ID" value="${cont.ID}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${cont.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${cont.s3}" />
            </c:if>
         </tbody>
			</table>

       </form>
 
<script type="text/javascript">
var wgid='${wgid}';
if(wgid) $("#id1").val(wgid);
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
function doSubmit(){
	top.layer.confirm('保存信息后不允许修改，请审核信息后保存。', {
		icon : 3,
		title : '提示'
	}, function(index) {
      	$("#inputForm").serializeObject();
      	$("#inputForm").submit();
		top.layer.close(index);
	});
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