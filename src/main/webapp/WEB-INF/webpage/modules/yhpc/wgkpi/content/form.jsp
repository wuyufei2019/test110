<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>考核规则</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/wgkpi/content/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody id="tbody">
				<tr >
					<td class="width-20 active"><label class="pull-right">评分项目：</label></td>
					<td class="width-80">
						<input type="text" id="name" name="name" class="easyui-combobox" value="${cont.name }" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">项目总分：</label></td>
					<td class="width-80">
						<input type="text" id="score" name="score" class="easyui-textbox" value="${cont.score }"  data-options="required:'true',validType:'number'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">考核内容：</label></td>
					<td class="width-80">
						<input type="text" id="content" name="content" class="easyui-combobox" value="${cont.content }"  data-options="multiline:true,required:'true',validType:'length[0,500]',editable:true,valueField: 'text',textField: 'text',panelHeight:'100px' " style="width: 100%;height: 50px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">考核标准：</label></td>
					<td class="width-80">
						<input type="text" name="standard" class="easyui-textbox" value="${cont.standard }"  data-options="required:'true',validType:'length[0,100]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>

              <input type="hidden" id="wgid" name="id1" value="${cont.id1}" />
				<c:if test="${not empty cont.ID}">
					<input type="hidden" name="ID" value="${cont.ID}" />
				</c:if>
				</tbody>
            <c:if test="${action eq 'createSub' }">
              <td align="center" colspan="4"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addtr()"
                  title="其他问题"><i class="fa fa-plus"></i>考核标准</a></td>
             </c:if>    
			</table>

       </form>
 
<script type="text/javascript">

$("#wgid").val(parent.wgid);
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
$("#name").combobox({
	 panelHeight:'100px',
	 editable:true, 
	 required:'true',
	 queryParams: { 'code': parent.wgcode},
	 url:'${ctx}/yhpc/wgkpi/content/namejson',
	 valueField: 'text',
	 textField: 'text',
 	 onSelect: function(rec){
		 $("#score").textbox("setValue",rec.score);
		 $("#score").textbox({readonly : true});
		 $.post("${ctx}/yhpc/wgkpi/content/contentjson",{'code': parent.wgcode,'name':rec.text},function(data){
				$('#content').combobox('loadData', eval(data));
		 });
	 }
});
function addtr(){
	$("#tbody").append("<tr name=\"tr\"><td class=\"width-20 active\"><label class=\"pull-right\">考核标准：</label></td><td class=\"width-80\"><input type=\"text\" name=\"standard\" class=\"easyui-textbox\" value=\"${cont.standard }\" data-options=\"required:'true',validType:'length[0,100]'\" style=\"width:100%;height:30px\"></td></tr>");
	$.parser.parse("[name='tr']");
}
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