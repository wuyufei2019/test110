<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>出卷规则信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/stgz/${action}"  method="post" class="form-horizontal" >
			

		  	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35" colspan="3">
					    <c:if test="${action=='create'}">
						<input class="easyui-combobox" id="hid_id2" name="ID2" value="${stgz.ID2 }" style="width: 100%;height: 30px;" data-options="required:'true', panelHeight:'auto', 
								editable:false ,valueField: 'id',textField: 'text',url: '${ctx}/aqpx/stgz/kcjson'">
						</c:if>
						<c:if test="${action=='update'}">
						<input class="easyui-combobox" id="hid_id2" name="ID2" value="${stgz.ID2 }" style="width: 100%;height: 30px;" data-options="readonly:'true', panelHeight:'auto', 
								editable:false ,valueField: 'id',textField: 'text',url: '${ctx}/aqpx/kcxx/json'">
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">单选题（道）：</label></td>
					<td class="width-35"><input name="M1" class="easyui-numberbox" value="${stgz.m1 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
					
					<td class="width-15 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-35"><input name="M5" class="easyui-numberbox" value="${stgz.m5 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">多选题（道）：</label></td>
					<td class="width-35"><input name="M2" class="easyui-numberbox" value="${stgz.m2 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
					<td class="width-15 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-35"><input name="M6" class="easyui-numberbox" value="${stgz.m6 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">填空题（道）：</label></td>
					<td class="width-35"><input name="M3" class="easyui-numberbox" value="${stgz.m3 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
					<td class="width-15 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-35"><input name="M7" class="easyui-numberbox" value="${stgz.m7 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">判断题（道）：</label></td>
					<td class="width-35"><input name="M4" class="easyui-numberbox" value="${stgz.m4 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
					<td class="width-15 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-35"><input name="M8" class="easyui-numberbox" value="${stgz.m8 }" style="width: 100%;height: 30px;" data-options="max:100,validType:['integ']"></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">合格分数线：</label></td>
					<td class="width-35"><input name="M9" class="easyui-numberbox" value="${stgz.m9 }" style="width: 100%;height: 30px;" data-options="required:'true',max:100, validType:['integ']"></td>
				</tr>
					
				<tr></tr>
				
				<c:if test="${not empty stgz.ID}">
					<input type="hidden" name="ID" value="${stgz.ID}">
					<input type="hidden" name="ID1" value="${stgz.ID1}">
					<input type="hidden" name="S1" value='<fmt:formatDate value="${stgz.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />'>
					<input type="hidden" name="S3" value="${stgz.s3}">
				</c:if>
			</tbody>
		</table>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
    var a1=isnull("input[name=M1]");
	var a2=isnull("input[name=M2]");
	var a3=isnull("input[name=M3]");
	var a4=isnull("input[name=M4]");
	var b1=isnull("input[name=M5]");
	var b2=isnull("input[name=M6]");
	var b3=isnull("input[name=M7]");
	var b4=isnull("input[name=M8]");
	if(a1*b1+a2*b2+a3*b3+a4*b4!=100){
        layer.msg("总分不等于100分，请重新设置！", {time : 3000});
		return;
	}	
	var d;
	$.ajax({
	      method : 'POST',
	      url : ctx+"/aqpx/stgz/creategz",
	      data : {"kcid":$("#hid_id2").combobox('getValue'), "m1":a1, "m2":a2, "m3":a3, "m4":a4},
	      async: false,
	      success : function(data) {
	    	  d = data;
	      }
	});
	
	if (d != "success") {
		  layer.msg(d,{time:5000});
		  return;
	}
	$("#inputForm").submit(); 
}

function isnull(select){
	var n=$(select).val();
	if(n){
		return n;
	}else{
		$(select).val(0)
		return 0;
	}
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else if(data=='hasadded')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '该课程考试规则已经制定，请勿重新添加！',shade: 0 ,time: 2000 });
	    	else if(data=='addinfor')
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '请先完善企业信息！',shade: 0 ,time: 2000 });
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