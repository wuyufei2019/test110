<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>评论</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/model/js/aqwj/tfsj/index.js"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/issue/tfsj/${action}"
		method="post" class="form-horizontal">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85" ><textarea
							id="textarea_kindM3" name="M1"
							style="width: 100%;height:250px;visibility:hidden;">${pl.m1 }</textarea>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评论时间：</label></td>
					<td class="width-35" ><input id="M2" name="M2" class="easyui-datetimebox" value="${pl.m2 }" style="height: 30px;width: 200px;" data-options="editable:false" /></td>
				</tr>
				
				<input type="hidden" name="ID1" value="${pl.ID1}" />
				<input type="hidden" name="fid" value="${pl.fid}" />
				<c:if test="${not empty pl.ID}">
					<input type="hidden" name="ID" value="${pl.ID}" />
					<input type="hidden" name="ID2" value="${pl.ID2}" />
				</c:if>
				<tbody>
		</table>

	</form>


	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
			$("#inputForm").serializeObject();
			$("#inputForm").submit();

		}
		
		formatterDate = function (date) {
			var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
			var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
			+ (date.getMonth() + 1);
			var hor = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
		};

		$(function() {
		
			var curr_time = new Date();
			if('${action}'=='jspl' || '${action}'=='zcpl'){    
				$("#M2").datetimebox("setValue",formatterDate(curr_time)); 	
			}
			
			var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false;	// 返回false终止表单提交
				},
				success : function(data) {
					$.jBox.closeTip();
					if (data == 'success')
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '操作成功！',
							shade : 0,
							time : 2000
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
					parent.dg.datagrid('reload');
					if('${action}' != 'jspl'){
						parent.parent.dg.datagrid('reload');
					}
					parent.layer.close(index);//关闭对话框。
				}
			});

			//富文本渲染
			onloadEditor();

		});
	</script>
	</body>
</html>