<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>不安全行为管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/unsafe/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-80" >
							<input value="${unsafe.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-80" >
						<input value="${unsafe.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">不安全行为类型：</label></td>
					<td class="width-80" ><input name="M1" class=easyui-combobox value="${unsafe.m1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'120px', editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/yhpc/unsafe/xwlblist', validType:'length[0,100]' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">行为描述：</label></td>
					<td class="width-80" ><input name="M2" type="text" class="easyui-textbox" value="${unsafe.m2 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,100]' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" ><input name="M3" type="text" value="${unsafe.m3}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,1000]' "></td>					
				</tr>
				
				<c:if test="${not empty unsafe.ID}">
					<input type="hidden" name="ID" value="${unsafe.ID}" />
					<input type="hidden" name="ID1" value="${unsafe.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${unsafe.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${unsafe.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
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
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.$('#yhpc_unsafe_cx_m1').combobox('reload');
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	});
</script>
</body>
</html>