<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加事先催告记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/sxcg/${action}"  method="post" class="form-horizontal" >
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<c:if test="${action eq 'updateSub'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${yse.number }" 
                         style="width: 100%;height: 30px;" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-20 active" ><label class="pull-right">单位（个人）:</label></td>
					<td class="width-30" colspan="3" >
					<input name="qyname" id="qyname" class="easyui-textbox" value="${yse.qyname }"editable="false"style="width: 100%;height: 30px;" 
                         data-options="validType:['length[0,50]'],required:'true'"/>
					</td>
					
				</tr>
		        <tr>
					<td class="width-20 active"><label class="pull-right">尚未履行的行政决定：</label></td>
					<td class="width-30" colspan="3"><input id="xzjd" name="xzjd"  class="easyui-textbox" value="${yse.xzjd }" 
                         style="width: 100%;height: 80px;"  data-options="multiline:true , validType:['length[0,500]']"/></td>
				</tr>
                 <tr>
                <td class="width-20 active"><label class="pull-right">罚款缴纳日期：</label></td>
                         <td class="width-30"><input id="finedate"name="finedate" class="easyui-datebox" value="${yse.finedate }" 
                         style="width: 100%;height: 30px;"data-options="required:'true'"/></td>
                 </tr>   
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款：</label></td>
					<td class="width-30"><input id="fine" name="fine"  class="easyui-textbox" value="${yse.fine }" style="width: 100%;height: 30px;" 
                         data-options="validType:['length[0,25]','money']"/></td>
					<td class="width-20 active"><label class="pull-right">加处罚款：</label></td>
					<td class="width-30"><input id="extrafine" name="extrafine"class="easyui-textbox" value="${yse.extrafine }"style="width:100%;height:30px;" 
                         data-options="validType:['length[0,25]','money']"/></td>
				</tr>
                <tr>
					<td class="width-20 active"><label class="pull-right">合计：</label></td>
					<td class="width-30"><input id="allfine" name="allfine"class="easyui-textbox"value="${yse.allfine }" style="width:100%;height:30px;" 
                         data-options="validType:['length[0,25]','money']"/></td>
				</tr>
                <tr>
                     <td class="width-20 active"><label class="pull-right">立即履行的行政决定：</label></td>
                     <td class="width-30" colspan="3"><input id="extraxzjd" name="extraxzjd"  class="easyui-textbox" value="${yse.extraxzjd }" 
                     style="width: 100%;height: 50px;" data-options="multiline:true , validType:['length[0,500]']"/></td>
                 </tr>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${yse.s1}"/>"/>
				 <input type="hidden" name="ID" value="${yse.ID}" />
				 <input type="hidden" name="id1" value="${yse.id1}" />
		</table>
	</div>
	</tbody>
</form>
<script type="text/javascript">
$(function(){
	if('${action}'=='createSub'){
		    $("input",$("#fine").next("span")).blur(function(){
		    	var f1=parseFloat($("#extrafine").textbox('getValue')==""?0:$("#extrafine").textbox('getValue'));
		    	var f2=parseFloat($("#fine").textbox('getValue')==""?0:$("#fine").textbox('getValue'));
		    	//var f2=parseFloat($("#fine").textbox('getValue'));
		       $("#allfine").textbox('setValue',f1+f2);
		    });
		    $("input",$("#extrafine").next("span")).blur(function(){
		    	var f1=parseFloat($("#extrafine").textbox('getValue')==""?0:$("#extrafine").textbox('getValue'));
		    	var f2=parseFloat($("#fine").textbox('getValue')==""?0:$("#fine").textbox('getValue'));
		    	//var f2=parseFloat($("#fine").textbox('getValue'));
		       $("#allfine").textbox('setValue',f1+f2);
		    	//$("#allfine").textbox('setValue',parseFloat($("#extrafine").textbox('getValue'))+parseFloat($("#fine").textbox('getValue')));
		    });
	$('#finedate').datebox('setValue', format(new Date()));	
	$("input[name='id1']").val('${id1}');
	}
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