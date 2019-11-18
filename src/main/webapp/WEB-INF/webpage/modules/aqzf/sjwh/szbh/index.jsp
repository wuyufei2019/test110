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
				<input type="hidden" name="ID" value="${bh.ID}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${bh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID1" value="${bh.ID1}" />
				<table
					class="table table-bordered  dataTables-example dataTable no-footer"
					style="margin:0 auto;min-width:900px;width:1000px;">
					<tbody>
						<tr>
							<td class="width-22 active"><label class="pull-right">检查方案编号：</label></td>
							<td class="width-28"><input name="M1" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m1 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">立案审批编号：</label></td>
							<td class="width-28"><input name="M6" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m6 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr>
						<%-- <tr>
							<td class="width-22 active"><label class="pull-right">处理措施决定书编号：</label></td>
							<td class="width-28"><input name="M3" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m3 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">检查记录编号：</label></td>
							<td class="width-28"><input name="M2" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m2 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">责令整改指令书编号：</label></td>
							<td class="width-28"><input name="M4" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m4 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">整改复查意见书编号：</label></td>
							<td class="width-28"><input name="M5" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m5 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr> --%>
						
						<%-- <tr>
							<td class="width-22 active"><label class="pull-right">行政处罚告知书编号：</label></td>
							<td class="width-28"><input name="M8" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m8 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">行政处罚听证告知书编号：</label></td>
							<td class="width-28"><input name="M9" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m9 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">案件处理呈批表编号：</label></td>
							<td class="width-28"><input name="M10" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m10 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">行政处罚决定书（单位）编号：</label></td>
							<td class="width-28"><input name="M11" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m11 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">行政处罚决定书（个人）编号：</label></td>
							<td class="width-28"><input name="M12" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m12 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">文书送达回执编号：</label></td>
							<td class="width-28"><input name="M13" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m13 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">行政强制执行事先催告书编号：</label></td>
							<td class="width-28"><input name="M14" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m14 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">强制执行申请书编号：</label></td>
							<td class="width-28"><input name="M15" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m15 }"
								data-options="required:'true',validType:'integ'" /></td>
							<td class="width-22 active"><label class="pull-right">结案审批表编号：</label></td>
							<td class="width-28"><input name="M16" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.m16 }"
								data-options="required:'true',validType:'integ'" /></td>
						</tr> --%>
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
				confirmx('确定要修改编号信息吗?', function(index) {
					
					flag=false;
					$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/aqzf/wsbh/update',
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