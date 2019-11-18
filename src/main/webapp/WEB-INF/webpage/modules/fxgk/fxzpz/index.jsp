<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
</head>
<body>
	<div>
			<form id="inputForm" class="form-horizontal">
				<input type="hidden" name="ID" value="${fxz.ID}" />
				<table
					class="table table-bordered  dataTables-example dataTable no-footer"
					style="margin:0 auto;min-width:900px;width:1000px;">
					<tbody>
						<tr>
							<td class="width-22 active"><label class="pull-right">红色风险值：</label></td>
							<td class="width-28"><input name="M1" style="width:250px;height:30px;" class="easyui-textbox"
								value="${fxz.m1 }"
								data-options="required:'true'" /></td>
							<td class="width-22 active"><label class="pull-right">橙色风险值：</label></td>
							<td class="width-28"><input name="M2" style="width:250px;height:30px;" class="easyui-textbox"
								value="${fxz.m2 }"
								data-options="required:'true'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">黄色风险值：</label></td>
							<td class="width-28"><input name="M3" style="width:250px;height:30px;" class="easyui-textbox"
								value="${fxz.m3 }"
								data-options="required:'true'" /></td>
							<td class="width-22 active"><label class="pull-right">蓝色风险值：</label></td>
							<td class="width-28"><input name="M4" style="width:250px;height:30px;" class="easyui-textbox"
								value="${fxz.m4 }"
								data-options="required:'true'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">橙色界限：</label></td>
							<td class="width-28">
							<input name="M5" style="width:250px;height:30px;" class="easyui-textbox" value="${fxz.m5 }" data-options="required:'true'" />
							</td>
							<td class="width-22 active"><label class="pull-right">黄色界限：</label></td>
							<td class="width-28">
							<input name="M6" style="width:250px;height:30px;" class="easyui-textbox" value="${fxz.m6 }" data-options="required:'true'" />
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">蓝色界限：</label></td>
							<td class="width-28">
							<input name="M7" style="width:250px;height:30px;" class="easyui-textbox" value="${fxz.m7 }" data-options="required:'true'" />
							</td>
						</tr>
					</tbody>
				</table>
				<div style="text-align:center;margin: 20px;">
					<a  class="btn btn-primary"  onclick="updateInfor()" style="width:120px">保存信息</a>			
				</div>
			</form>
	</div>


	<script type="text/javascript">
		var flag=true;
		//保存
		function updateInfor() {
			if($("#inputForm").form('validate')&&flag){
				confirmx('确定要修改风险值信息吗?', function(index) {
					
					flag=false;
					$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/fxgk/fxzpz/update',
						data : $("#inputForm").serialize(),
						success : function(data) {
							$.jBox.closeTip();
							layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
							flag=true;
						},
						error : function(data) {
							$.jBox.closeTip();
							layer.open({icon:1,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
							flag=true;
						}
					});
				});
			}
		}
	 
	
</script>

</body>
</html>