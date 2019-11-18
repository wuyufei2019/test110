<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>部门管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
		validateForm = $("#inputForm").validate({
			 debug:true
		});
		 
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid =validateForm.form();
		    	var power=$("input[name='M4']:checked").val();
		    	if(power==null||power==''){
		    		layer.alert('请选择权限标识');
		    		isValid=false;
		    	}
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
	<form id="inputForm" class="form-horizontal" method="post" action="${ctx}/system/department/${action}">

		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">部门名称:</label></td>
		         <td class="width-35" ><input name="M1" value="${list.m1}" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="required:true ,validType:'length[0,50]'" /></td>
		         <td  class="width-15 active"><label class="pull-right">上级部门:</label></td>
		         <td class="width-35" ><input id="fid" type="text" name="fid" value="${list.fid}" class="easyui-combotree" style="width: 100%;height: 30px;" data-options="required:'required',panelHeight:'200',url:'${ctx}/system/department/idjson',method:'GET'"/></td>
		      </tr>
		      
		       <tr>
		         <td class="width-15 active"><label class="pull-right">排序:</label></td>
		         <td class="width-35" ><input name="M2" value="${list.m2}" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'length[0,11]'" /></td>
		         <td class="width-15 active"><label class="pull-right">权限标识:</label></td>
		         <td class="width-35">本级<input id="r1" type="radio"  name="M4" value="1" class="i-checks"/> 下级<input type="radio" id="r2" name="M4" value="2" class="i-checks"/> 全部<input id="r3" type="radio"  name="M4" value="3" class="i-checks"/></td>
		      </tr>
		      
		       <tr>
		         <td class="width-15 active"><label class="pull-right">描述:</label></td>
		         <td class="width-85" colspan="3"><input name="M3" value="${list.m3}" style="width: 100%;height: 80px;" class="easyui-textbox" data-options="multiline:true ,validType:'length[0,200]'" /></td>
		      </tr>
		   
			   	<c:if test="${not empty list.id}">
					<input type="hidden" name="id" value="${list.id}" />
					<input type="hidden" name="id1" value="${list.id1}" />
					<input type="hidden" name="id2" value="${list.id2}" />
					<input type="hidden" name="id3" value="${list.id3}" />
					<input type="hidden" name="flag" value="${list.flag}" />
					<input type="hidden" name="code" value="${list.code}" />
				</c:if>
			</tbody>
		</table>   
	</form>
	<script type="text/javascript">
		$(function(){
			if ('${action}' == "create" && parent.parentPermId != null)
				$('#fid').combotree('setValue', parent.parentPermId);
		})
		
		
		if ('${action}' == 'update') {
			$("input[name='M4'][value=${list.m4}]").attr("checked", true);
		}
	</script>
</body>
</html>