<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全生产执法字典表</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/dict/${action}"  method="post" class="form-horizontal" >
     <input type="hidden" name="ID" value="${dict.ID}" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">标签：</label></td>
					<td class="width-30" colspan="3">
						<input value="${dict.m1}" type="text" id="M1" name="M1" style="width: 100%;height: 60px;"
								class="easyui-textbox"
								data-options="required:true,validType:'length[0,200]',multiline:true" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" colspan="3">
						<input value="${dict.m2}" type="text" id="M2" name="M2" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:true,validType:'length[0,25]',
									url:'${ctx}/aqzf/dict/gettype',valueField:'id',textField:'text',panelHeight:'150',
									onSelect: function(rec){
										
							  			$('#M3').textbox('setValue',rec.id);
							    	}" />
					</td>
				</tr>
				
				<tr>
				    <td class="width-20 active"><label class="pull-right">类别编码：</label></td>
					<td class="width-30" >
						<input value="${dict.m3}" type="text" id="M3" name="M3" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="required:true,validType:'english'" />
					</td>
					<td class="width-20 active"><label class="pull-right">排序：</label></td>
					<td class="width-30" >
						<input value="${dict.m4}" type="text" id="M4" name="M4" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="validType:'integ'" />
					</td>
				</tr>
				
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$('#M2').textbox('setValue',$('#M2').textbox('getText'));
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