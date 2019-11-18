<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>区域网格管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").submit(); 
	}
 
	$(function(){
		if('${action}'=="create"&&parent.parentPermId!=null)
			$('#fid').combotree('setValue', parent.parentPermId);
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.treegrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		}); 
	});
	</script>
</head>
<body>
	<form id="inputForm"  action="${ctx}/aqsc/fylx/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">类型名称:</label></td>
		         <td  class="width-35" ><input name="m1" type="text" value="${fylx.m1 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="required:'required',validType:'length[2,100]'"/></td>
		         <td  class="width-15 active"><label class="pull-right">上级目录:</label></td>
		         <td class="width-35" ><input id="fid" type="text" name="fid" value="${fylx.fid}" class="easyui-combotree" style="width: 100%;height: 30px;" data-options="required:'required',panelHeight:'143px',url:'${ctx}/aqsc/fylx/idjson',method:'GET'"/></td>
		      </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">排序:</label></td>
		         <td  class="width-35" ><input type="text" name="m3" value="${fylx.m3 }" class="easyui-numberbox" style="width: 100%;height: 30px;" /></td>
		      </tr>
		      <tr>
		         <td  class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" colspan="3"><input type="text" name="m4" value="${fylx.m4 }" class="easyui-textbox form-control" style="width: 100%;height: 80px;" data-options="multiline:true"> </td>
		      </tr>
		      
		      <c:if test="${not empty fylx.ID}">
		      	 <input type="hidden" name="ID" value="${fylx.ID}" />
				 <input type="hidden" name="S1"
					 value="<fmt:formatDate value="${fylx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				 <input type="hidden" name="S3" value="${fylx.s3}" />
		      </c:if>
		   </tbody>
		</table>
	</form>
</body>
</html>