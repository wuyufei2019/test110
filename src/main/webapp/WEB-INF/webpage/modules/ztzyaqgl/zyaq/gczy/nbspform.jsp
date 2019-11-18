<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>作业单位确认页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
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
     <form id="inputForm" action="${ctx}/ztzyaqgl/gczy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">意见：</label></td>
					<td class="width-85" >
						<input id="suggest" name="suggest" class="easyui-textbox" style="width: 100%;height: 80px;" value="" data-options="multiline:true,validType:'length[0,500]'" />
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">审批结果：</label></td>
					<td class="width-35" colspan="3">
						<input type="radio" value="通过" class="i-checks" name="spflag" checked="checked" />通过
						<input type="radio" value="不通过" class="i-checks"  name="spflag" />不通过
					</td>
				</tr>
				
				<input type="hidden" name="id" value="${gczyid }" />
				</tbody>
			</table>	
      </form>
<script type="text/javascript">
	var action = $("[name=action]").val();
</script>
</body>
</html>