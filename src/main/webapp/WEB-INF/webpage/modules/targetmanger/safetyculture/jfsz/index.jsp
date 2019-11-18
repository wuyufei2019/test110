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
			<input type="hidden" name="ID" value="${jf.ID}" />
			<table
				class="table table-bordered  dataTables-example dataTable no-footer"
				style="margin:0 auto;min-width:900px;width:1000px;">
				<tbody>
					<tr>
						<td class="width-22 active"><label class="pull-right">隐患排查积分：</label></td>
						<td class="width-28"><input name="M1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m1 }"
							data-options="required:'true',validType:'integ'" /></td>
						<td class="width-22 active"><label class="pull-right">系数：</label></td>
						<td class="width-28"><input name="M1_1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m1_1 }"
							data-options="required:'true',validType:'integ'" />%（百分比）</td>
					</tr>
					<tr>
						<td class="width-22 active"><label class="pull-right">随手拍积分：</label></td>
						<td class="width-28"><input name="M2" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m2 }"
							data-options="required:'true',validType:'integ'" /></td>
						<td class="width-22 active"><label class="pull-right">系数：</label></td>
						<td class="width-28"><input name="M2_1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m2_1 }"
							data-options="required:'true',validType:'integ'" />%（百分比）</td>
					</tr>
					<tr>
						<td class="width-22 active"><label class="pull-right">安全培训积分：</label></td>
						<td class="width-28"><input name="M3" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m3 }"
							data-options="required:'true',validType:'integ'" /></td>
						<td class="width-22 active"><label class="pull-right">系数：</label></td>
						<td class="width-28"><input name="M3_1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m3_1 }"
							data-options="required:'true',validType:'integ'" />%（百分比）</td>
					</tr>
					<tr>
						<td class="width-22 active"><label class="pull-right">建言献策积分：</label></td>
						<td class="width-28"><input name="M4" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m4 }"
							data-options="required:'true',validType:'integ'" /></td>
						<td class="width-22 active"><label class="pull-right">系数：</label></td>
						<td class="width-28"><input name="M4_1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m4_1 }"
							data-options="required:'true',validType:'integ'" />%（百分比）</td>
					</tr>
					<tr>
						<td class="width-22 active"><label class="pull-right">其他积分：</label></td>
						<td class="width-28"><input name="M5" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m5 }"
							data-options="required:'true',validType:'integ'" /></td>
						<td class="width-22 active"><label class="pull-right">系数：</label></td>
						<td class="width-28"><input name="M5_1" style="width:250px;height:30px;" class="easyui-textbox"
							value="${jf.m5_1 }"
							data-options="required:'true',validType:'integ'" />%（百分比）</td>
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
				confirmx('确定要修改积分设置吗?', function(index) {
					
					flag=false;
					$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/target/jfsz/update',
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