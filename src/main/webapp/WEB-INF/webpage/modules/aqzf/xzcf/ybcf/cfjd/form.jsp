<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加惩罚告知记录</title>
<meta name="decorator" content="default" />
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/cfjd/${action}" method="post"
          class="form-horizontal">
          <tbody>
               <table
                    class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                    <c:if test="${action eq 'updateSub'}">
                         <tr>
                              <td class="width-20 active"><label class="pull-right">编号：</label></td>
                              <td class="width-30" colspan="3"><input name="number"
                                        class="easyui-textbox" editable="false"
                                        value="${jce.number }" style="width: 100%;height: 30px;" /></td>
                         </tr>
                    </c:if>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">处罚时间：</label></td>
                         <td class="width-30"><input id="punishdate" name="punishdate"
                                   class="easyui-datebox" value="${jce.punishdate }"
                                   style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
                         <td class="width-30" colspan="3"><input value="${jce.punishname }"
                                   id="qyname" name="punishname" style="width: 100%;height: 30px;"
                                   class="easyui-textbox" /></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
                         <td class="width-30"><input id="legal" name="legal"
                                   class="easyui-textbox" value="${jce.legal }"
                                   style="width: 100%;height: 30px;" /></td>
                         <td class="width-20 active"><label class="pull-right">职务：</label></td>
                         <td class="width-30"><input id="duty" name="duty"
                                   class="easyui-textbox" value="${jce.duty }"
                                   style="width: 100%;height: 30px;" /></td>

                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right">电话：</label></td>
                         <td class="width-30"><input id="mobile" name="mobile"
                                   class="easyui-textbox" value="${jce.mobile }"
                                   style="width: 100%;height: 30px;" validtype="mobile" /></td>
                         <td class="width-20 active"><label class="pull-right">邮编：</label></td>
                         <td class="width-30"><input name="ybcode" type="text" id="ybcode"
                                   value="${jce.ybcode }" class="easyui-textbox"
                                   style="width: 100%;height: 30px;" data-options="validType:'ZIP'" /></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right">地址：</label></td>
                         <td class="width-30" colspan="3"><input id="address" name="address"
                                   class="easyui-textbox" value="${jce.address }"
                                   style="width: 100%;height: 30px;"
                                   data-options="validType:['length[0,100]']" /></td>
                    </tr>

                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">被处罚人姓名：</label></td>
                         <td class="width-30" colspan="3"><input value="${jce.punishname }"
                                   id="grname" name="punishname" style="width: 100%;height: 30px;"
                                   class="easyui-textbox" /></td>
                    </tr>

                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">性别：</label></td>
                         <td class="width-30"><input id="persex" name="sex"
                                   class="easyui-combobox" value="${jce.sex }"
                                   style="width:100%;height: 30px;"
                                   data-options="panelHeight:'auto',data: [{value:'男',text:'男'},
                                                {value:'女',text:'女'}]" /></td>
                         <td class="width-20 active"><label class="pull-right">年龄：</label></td>
                         <td class="width-30"><input id="perage" name="age"
                                   class="easyui-textbox" value="${jce.age }" validType="number"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>

                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">电话：</label></td>
                         <td class="width-30"><input id="pmobile" name="mobile"
                                   class="easyui-textbox" value="${jce.mobile }"
                                   style="width: 100%;height: 30px;" validtype="mobile" /></td>
                         <td class="width-20 active"><label class="pull-right">身份证号：</label></td>
                         <td class="width-30"><input id="identity" name="identity1"
                                   validType="idCode" class="easyui-textbox"
                                   value="${jce.identity1 }" style="width: 100%;height: 30px;" /></td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
                         <td class="width-30" colspan="3"><input id="paddress" name="address"
                                   class="easyui-textbox" value="${jce.address }"
                                   style="width: 100%;height: 30px;"
                                   data-options="validType:['length[0,100]']" /></td>
                    </tr>

                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">所在单位：</label></td>
                         <td class="width-30"><input id="dwname" name="dwname"
                                   class="easyui-textbox" value="${jce.dwname }"
                                   style="width: 100%;height: 30px;" /></td>
                         <td class="width-20 active"><label class="pull-right">职务：</label></td>
                         <td class="width-30"><input id="perduty" name="duty"
                                   class="easyui-textbox" value="${jce.duty }"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>

                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right">单位地址：</label></td>
                         <td class="width-30" colspan="3"><input id="dwaddress"
                                   name="dwaddress" class="easyui-textbox" value="${jce.dwaddress }"
                                   style="width: 100%;height: 30px;"
                                   data-options="validType:['length[0,100]']" /></td>
                    </tr>

                    <tr>
                         <td class="width-20 active"><label class="pull-right">违法事实和证据：</label></td>
                         <td class="width-30" colspan="3"><input id="illegalactandevidence"
                                   name="illegalactandevidence"
                                   value="${jce.illegalactandevidence }" class="easyui-textbox"
                                   style="width: 100%;height: 80px;"
                                   data-options="multiline:true,validType:['length[0,2500]']" /></td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">违反条款：</label></td>
                         <td class="width-30" colspan="3"><input id="unlaw" name="unlaw"
                                   class="easyui-textbox" value="${jce.unlaw }"
                                   style="width: 100%;height: 50px;"
                                   data-options="multiline:true,editable:true,validType:['length[0,500]'] " /></td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">处罚依据：</label></td>
                         <td class="width-30" colspan="3"><input id="enlaw" name="enlaw"
                                   class="easyui-textbox" value="${jce.enlaw }"
                                   style="width: 100%;height: 50px;"
                                   data-options="multiline:true,editable:true,validType:['length[0,500]'] " /></td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">处罚结果：</label></td>
                         <td class="width-30" colspan="3"><input id="punishresult"
                                   name="punishresult" class="easyui-textbox"
                                   value="${jce.punishresult }" style="width: 100%;height: 100px;"
                                   data-options="multiline:true,validType:['length[0,1000]']" /></td>

                         <!-- 隐藏域 -->

                         <input type='hidden' name="S1" value="<fmt:formatDate value="${jce.s1}"/>" />
                         <input type="hidden" name="ID" value="${jce.ID}" />
                         <input type='hidden' name="id1" value="${jce.id1}" />
                         <input type='hidden' name="percomflag" value="${jce.percomflag}" />
               </table>
          </tbody>
     </form>
     <script type="text/javascript">
						$(function() {
							if ('${action}' == 'createSub') {
								$('#punishdate').datebox('setValue', format(new Date()));
								$("#illegalactandevidence").textbox("setValue", '${ajcp.illegalactandevidence}');
								$("#unlaw").textbox("setValue", '${gie.unlaw}');
								$("#enlaw").textbox("setValue", '${gie.enlaw}');
								$("#punishresult").textbox("setValue", '${gie.punishresult}');
								$("input[name='id1']").val('${id1}');
								if('${ajcp.percomflag}'==1){
								comResult();
								$("#qyname").textbox("setValue", '${ajcp.punishname}');
								//$("#mobile").textbox("setValue", '${yle.contact}');
								$("#legal").textbox("setValue", '${ajcp.legal}');
								$("#address").textbox("setValue", '${ajcp.qyaddress}');
								$("#ybcode").textbox("setValue", '${ajcp.qyyb}');
								$("#duty").textbox("setValue", '${ajcp.duty}');
								$("input[name='percomflag']").val('1');
								}else{
									personResult();
									$("#grname").textbox('setValue', '${ajcp.punishname}');
									$("#pmobile").textbox('setValue', '${ajcp.mobile}');
									$("#persex").combobox('setValue', '${ajcp.sex}');
									$("#perage").textbox('setValue', '${ajcp.age}');
									$("#dwname").textbox('setValue', '${ajcp.dwname}');
									$("#paddress").textbox("setValue", '${ajcp.address}');
									$("#dwaddress").textbox('setValue', '${ajcp.dwaddress}');
									$("#perduty").textbox("setValue", '${ajcp.duty}');
									$("input[name='percomflag']").val('0');
								}
							} else {
								if ('${jce.percomflag}' == '1') {
									comResult();
								} else {
									personResult();
								}
							}
						});
						function format(date) {
							var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
							var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
							return date.getFullYear() + '-' + month + '-' + day;
						};
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						var a = '${action}';

						function personResult() {
							$("[name='person']").show();
							$("[name='company']").hide();
							$("#qyname").textbox("disable");
							$("#mobile").textbox("disable");
							$("#duty").textbox("disable");
							$("#address").textbox("disable");
							$('#grname').textbox('enable');
							$('#pmobile').textbox('enable');
							$('#perduty').textbox('enable');
							$('#paddress').textbox('enable');
						}

						function comResult() {
							$("[name='person']").hide();
							$("[name='company']").show();
							$("#qyname").textbox("enable");
							$("#mobile").textbox("enable");
							$("#duty").textbox("enable");
							$("#address").textbox("enable");
							$("#grname").textbox("disable");
							$("#pmobile").textbox("disable");
							$("#perduty").textbox("disable");
							$("#paddress").textbox("disable");
						}

			/* 			$("#qyname").combobox({
							onSelect : function() {
								var id = $('#qyname').combobox('getValue');
								$.ajax({
									type : 'get',
									url : '${ctx}/bis/qyjbxx/qydetail/' + id,
									success : function(data) {
										var d = JSON.parse(data);
										$("#legal").textbox('setValue', d.m5);
										$("#mobile").textbox('setValue', d.m6);
										$("#ybcode").textbox('setValue', d.m9);
										$("#address").textbox('setValue', d.m8);

									}
								});
							}
						}); */
				/* 		$("#grname").textbox({
							onSelect : function() {
								var id = $('#grname').combobox('getValue');
								$.ajax({
									type : 'get',
									url : '${ctx}/bis/ygxx/ygdetail/' + id,
									success : function(data) {
										var d = JSON.parse(data);

										$("#persex").combobox('setValue', d.m3);
										//$("#perage").textbox('setValue',d.m6);	
										$("#pmobile").textbox('setValue', d.m9);
										$("#identity").textbox('setValue', d.m8);
										$("#perage").textbox('setValue', new Date().getFullYear() - new Date(d.m10).getFullYear());
										$("#dwname").textbox('setValue', d.m1);
										//$("#perduty").textbox('setValue',d.m7);	
										$("#dwaddress").textbox('setValue', d.m33);
									}
								});
							}
						}); */

						$(":radio").change(function() {
							var flag = $('input:radio:checked').val();
							if (flag == "1") {
								//$("#grname").attr("disabled",true); 
								//$("#qyname").attr("disabled",false); 
								comResult();
							}
							if (flag == "0") {
								personResult();
								//$("#grname").attr("disabled",false); 
								//'$("#pmobile").attr("disabled",false); 
								//$("#perduty").attr("disabled",false); 
								//$("#paddress").attr("disabled",false); 
							}
						});

						function doSubmit() {
							/* var flag=$('input:radio:checked').val();
							alert(flag);
							if(flag=="0"){
							$("#qyname").attr("disabled",true); 
							$("#mobile").attr("disabled",true); 
							$("#duty").attr("disabled",true); 
							$("#address").attr("disabled",true); 
							}else{
							$("#grname").attr("disabled",true); 
							$("#pmobile").attr("disabled",true); 
							$("#perduty").attr("disabled",true); 
							$("#paddress").attr("disabled",true); 
							}  */
							$("#inputForm").serializeObject();
							$("#inputForm").submit();
						}
						$(function() {
							var flag = true;
							$('#inputForm').form({
								onSubmit : function() {
									var isValid = $(this).form('validate');
									if (isValid && flag) {
										flag = false;
										$.jBox.tip("正在提交，请稍等...", 'loading', {
											opacity : 0
										});
										return true;
									}
									return false; //返回false终止表单提交
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