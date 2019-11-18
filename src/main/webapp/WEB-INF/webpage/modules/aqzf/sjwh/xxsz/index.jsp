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
							<td class="width-22 active"><label class="pull-right">省市区名称：</label></td>
							<td class="width-28"><input name="ssqmc" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.ssqmc }"
								data-options="required:'true',validType:'length[0,25]'" /></td>
							<td class="width-22 active"><label class="pull-right">省市区简称：</label></td>
							<td class="width-28"><input name="ssqjc" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.ssqjc }"
								data-options="required:'true',validType:'length[0,25]'" /></td>
						</tr>
						
						<tr>
							<td class="width-22 active"><label class="pull-right">安全生产监督管理部门地址：</label></td>
							<td class="width-28"><input name="address" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.address }"
								data-options="required:'true',validType:'length[0,100]'" /></td>
							<td class="width-22 active"><label class="pull-right">邮编：</label></td>
							<td class="width-28"><input name="ybcode" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.ybcode }"
								data-options="validType:'length[0,10]'" /></td>
						</tr>
                          <tr>
                                   <td class="width-22 active"><label class="pull-right">人民政府：</label></td>
                                   <td class="width-28"><input name="gov" style="width:250px;height:30px;" class="easyui-textbox"
                                        value="${bh.gov }"
                                        data-options="validType:'length[0,50]'" /></td>
                                   <td class="width-22 active"><label class="pull-right">人民法院：</label></td>
                                   <td class="width-28"><input name="court" style="width:250px;height:30px;" class="easyui-textbox"
                                        value="${bh.court }"
                                        data-options="validType:'length[0,50]'" /></td>
                            </tr>
                           <tr>
                                   <td class="width-22 active"><label class="pull-right">上级人民政府：</label></td>
                                   <td class="width-28" colspan="3"><input name="highgov" style="width:92%;height:30px;" class="easyui-textbox"
                                        value="${bh.highgov }"
                                        data-options="validType:'length[0,50]'" /></td>
                            </tr>
                            <tr>
							<td class="width-22 active"><label class="pull-right">银行名称：</label></td>
							<td class="width-28"><input name="bankname" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.bankname }"
								data-options="validType:'length[0,25]'" /></td>
							<td class="width-22 active"><label class="pull-right">银行账号：</label></td>
							<td class="width-28"><input name="account" style="width:250px;height:30px;" class="easyui-textbox"
								value="${bh.account }"
								data-options="validType:'integ'" /></td>
							</tr>
                            <tr>
                                  <td class="width-22 active"><label class="pull-right">联系人：</label></td>
                                  <td class="width-28"><input name="contact" style="width:250px;height:30px;" class="easyui-textbox"
                                       value="${bh.contact }"
                                       data-options="validType:'length[0,10]'" /></td>
                                  <td class="width-22 active"><label class="pull-right">联系电话：</label></td>
                                  <td class="width-28"><input name="phone" style="width:250px;height:30px;" class="easyui-textbox"
                                       value="${bh.phone }"
                                       data-options="validType:['length[0,20]']" /></td>
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
				confirmx('确定要修改相关信息吗?', function(index) {
					
					flag=false;
					$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
					
					$.ajax({
						type : 'post',
						url : '${ctx}/aqzf/xxsz/update',
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