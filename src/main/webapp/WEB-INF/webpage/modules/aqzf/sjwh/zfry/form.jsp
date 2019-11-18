<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>执法人员管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/zfry/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35" colspan="3">
						<input value="${zfry.m1}" type="text" id="M1" name="M1" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="required:true,editable:true,validType:'length[0,10]'" />
					</td>
				</tr>
				
				<tr>
				    <td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35" >
						<input value="${zfry.m2}" type="text" id="M2" name="M2" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto',editable:false,data: [
						         {value:'男',text:'男'},
						         {value:'女',text:'女'}
						        	]  " />
					</td>
					<td class="width-15 active"><label class="pull-right">证件号：</label></td>
						<td class="width-35" >
							<input value="${zfry.m3}" type="text" id="M3" name="M3" style="width: 100%;height: 30px;"
									class="easyui-textbox"
									data-options="validType:'length[0,25]' " />
					</td>
				</tr>

                <tr>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
						<td class="width-35" >
							<input value="${zfry.m4}" type="text" id="M4" name="M4" style="width: 100%;height: 30px;"
									class="easyui-textbox"
									data-options="validType:'length[0,10]'" />
						</td>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
						<td class="width-35" >
							<input value="${zfry.m7}" type="text" id="M7" name="M7" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="validType:'length[0,10]',panelHeight:'auto',editable:true,data: [
							         {value:'工贸',text:'工贸'},
							         {value:'危化',text:'危化'},
							         {value:'冶金',text:'冶金'},
							         {value:'社区',text:'社区'}
							        	]  " />
						</td>	
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
						<td class="width-35" >
							<input value="${zfry.m5}" type="text" id="M5" name="M5" style="width: 100%;height: 30px;"
									class="easyui-textbox"
									data-options="validType:['length[0,20]','mobileAndTel'] " />
					</td>
				</tr>
				
				<c:if test="${not empty zfry.ID}">
					<input type="hidden" name="ID" value="${zfry.ID}" />
					<input type="hidden" name="ID1" value="${zfry.ID1}" />
					<input type="hidden" name="M6" value="${zfry.m6}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${zfry.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
	    $("#M7").combobox("setValue", $("#M7").combobox("getValue"));
		$("#inputForm").submit(); 
	}
	
	$(function(){
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
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