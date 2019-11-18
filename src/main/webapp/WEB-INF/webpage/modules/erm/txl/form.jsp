<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>通讯录管理</title>
<meta name="decorator" content="default" />
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/txl/${action}" method="post" class="form-horizontal">
          <table
               class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
               <tbody>
                    <tr>
                         <td class="width-15 active"><label class="pull-right">姓名：</label></td>
                         <td class="width-35"><input style="width: 100%;height: 30px;"
                                   name="M1" class="easyui-textbox " value="${txl.m1 }"
                                   data-options="required:'true',validType:'length[0,20]'" /></td>
                         <td class="width-15 active"><label class="pull-right">部门：</label></td>
                         <td class="width-35"><input style="width: 100%;height: 30px;"
                                   name="M2" class="easyui-textbox " value="${txl.m2 }"
                                   data-options="validType:'length[0,50]'" /></td>
                    </tr>
                    <tr>
                         <td class="width-15 active"><label class="pull-right">职务：</label></td>
                         <c:if test="${usertype eq '0' }">
                              <td class="width-35"><input style="width: 100%;height: 30px;"
                                        id="M3" name="M3" class="easyui-combobox "
                                        value="${txl.m3 }"
                                        data-options="editable:true ,data: [
								        {value:'局长',text:'局长'},
								        {value:'副局长',text:'副局长'},
								        {value:'处长',text:'处长'},
								        {value:'副处长',text:'副处长'},
								        {value:'主任',text:'主任'},
								        {value:'副主任',text:'副主任'},
								        {value:'办公室主任',text:'办公室主任'},
								        {value:'办公室副主任',text:'办公室副主任'},
								        {value:'科长',text:'科长'},
								        {value:'副科长',text:'副科长'},
								        {value:'主任科员',text:'主任科员'},
								        {value:'科员',text:'科员'},
								        {value:'其他',text:'其他'} ]" />
                              </td>
                         </c:if>
                         <c:if test="${usertype ne '0' }">
                              <td class="width-35"><input style="width: 100%;height: 30px;"
                                        name="M3" class="easyui-textbox " value="${txl.m3 }"
                                        data-options="validType:'length[0,50]'" /></td>
                         </c:if>
                         <td class="width-15 active"><label class="pull-right">应急电话(手机)：</label></td>
                         <td class="width-35"><input style="width: 100%;height: 30px;"
                                   name="M4" class="easyui-textbox" value="${txl.m4 }"
                                   data-options="required:'true', validType:'mobile'" /></td>
                    </tr>

                    <tr>
                         <td class="width-15 active"><label class="pull-right">固定电话：</label></td>
                         <td class="width-35" colspan="3"><input
                                   style="width: 100%;height: 30px;" name="M5"
                                   class="easyui-textbox" value="${txl.m5 }"
                                   data-options="validType:'tel'" /></td>
                    </tr>

                    </tr>

                    <tr>
                         <td class="width-15 active"><label class="pull-right">备注：</label></td>
                         <td class="width-85" colspan="3"><input name="M6" type="text"
                                   value="${txl.m6 }" class="easyui-textbox"
                                   style="width: 100%;height: 80px;"
                                   data-options="multiline:true , validType:'length[0,250]'">
                         </td>
                    </tr>

                    <c:if test="${not empty txl.ID}">
                         <input type="hidden" name="ID" value="${txl.ID}" />
                         <input type="hidden" name="qyid" value="${txl.qyid}" />
                         <input type="hidden" name="ID1" value="${txl.ID1}" />
                         <input type="hidden" name="userid" value="${txl.userid}" />
                         <input type="hidden" name="S1"
                              value="<fmt:formatDate value="${txl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
                         <input type="hidden" name="S3" value="${txl.s3}" />
                    </c:if>
          </table>
          <tbody>
     </form>
     <script type="text/javascript">
						var usertype = ${usertype};
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						var validateForm;

						function doSubmit() {
							$("#inputForm").submit();
						}

						$(function() {
							var flag = true;
							$('#inputForm').form({
								onSubmit : function() {
									var isValid = $(this).form('validate');
									if (isValid && flag) {
										//$('#M3').combobox('setValue', $('#M3').combobox('getText'));
										flag = false;
										$.jBox.tip("正在提交，请稍等...", 'loading', {
											opacity : 0
										});
										return true;
									}
									return false; // 返回false终止表单提交
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
									parent.layer.close(index);//关闭对话框。
								}
							});

						});
					</script>
</body>
</html>