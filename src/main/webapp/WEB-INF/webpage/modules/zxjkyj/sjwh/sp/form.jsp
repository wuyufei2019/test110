<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>监控摄像头数据</title>
	<meta name="decorator" content="default"/>
		<script type="text/javascript">
	var usertype=${usertype};

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
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
</head>
<body>
	     <form id="inputForm" action="${ctx}/bis/spjk/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35"  >
							<input value="${video.ID1 }" id="_qyid" name="ID1" style="width: 400px;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" >
						<input value="${video.ID1 }" id="_qyid" name="ID1" style="width: 400px;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
			 
				
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">名称：</label></td>
					<td><input name="name" type="text" class="easyui-textbox" value="${video.name }" style=" width: 400px;height: 30px;"
								data-options=" required:'true',validType:'xhtt_englishCheckSub'" /></td> 
			
				</tr>
				<tr >  
					<td class="width-15 active"><label class="pull-right"> ip/域名 ：</label></td>
					<td><input name="ip" type="text" class="easyui-textbox" value="${video.ip }" style="width: 400px;height: 30px;"  data-options="required:'true',width:400"/></td> 
				</tr>
				<tr >  
					<td class="width-15 active"><label class="pull-right">端口号：</label></td>
					<td><input name="port" type="text" class="easyui-textbox" value="${video.port }" style="width: 400px;height: 30px;"  data-options="required:'true',width:400,validType:'xhtt_numberDecimalsCheck'"/></td>   
				</tr>
				<tr>  
					<td class="width-15 active"><label class="pull-right">登录名 ：</label></td>
					<td><input name="username" type="text" class="easyui-textbox" value="${video.username }" style="width: 400px;height: 30px;"   data-options="required:'true',width:400"/></td>
				</tr>
				<tr>  
					<td class="width-15 active"><label class="pull-right">密码：</label></td>
					<td><input name="password" type="text" class="easyui-textbox" value="${video.password }" style="width: 400px;height: 30px;"  data-options="required:'true',width:400"/></td>  
				</tr>
				<c:if test="${action=='update' }">
				<tr>  
					<td class="width-15 active"><label class="pull-right">url：</label></td>
					<td><input name="url" type="text" class="easyui-textbox" value="${video.url }" style="width: 400px;height: 30px;"  data-options="width:400"/></td>  
				</tr>
				</c:if>
				<tr >  	
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td><input name="beizhu" type="text" class="easyui-textbox" value="${video.beizhu }" style="width: 400px;height: 30px;"  data-options="width:400 "/></td>  	
				</tr>
				<input type="hidden" name="ID" value="${video.ID}" />
				<input type="hidden" name=ID1 value="${video.ID1}" />
			</table>
            </form>
        </div>

</body>
</html>