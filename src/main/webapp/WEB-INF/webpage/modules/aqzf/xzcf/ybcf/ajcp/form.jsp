<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加安监呈批记录</title>
<meta name="decorator" content="default" />
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/ajclcp/${action}" method="post"
          class="form-horizontal">
          <tbody>
               <table
                    class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                    <c:if test="${action eq 'updateSub'}">
                         <tr>
                              <td class="width-20 active"><label class="pull-right">编号：</label></td>
                              <td class="width-30"><input name="number" class="easyui-textbox"
                                        editable="false" value="${jce.number }"
                                        style="width: 100%;height: 30px;" /></td>
                         </tr>
                    </c:if>
                    <tr>
                         <td class="width-20 active"><label class="pull-right"> 案件名称： </label>
                         </td>
                         <td class="width-30" colspan="3"><input value="${jce.casename }"
                                   id="casename" name="casename" style="width: 100%;height: 30px;"
                                   class="easyui-textbox" /></td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right"> 受处罚类型：
                              </label></td>
                         <td class="width-30" style="text-align:left"><span> <input
                                        type="radio" name="percomflag"
                                        style="width:15px;height:15px;margin-bottom:3px;"
                                        checked="true" value="1"> 单位 </input> <input type="radio"
                                        name="percomflag"
                                        style="width:15px;height:15px;margin-bottom:3px;margin-left:10px"
                                        value="0"> 个人 </input>
                         </span></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right"> 被处罚单位：
                              </label></td>
                         <td class="width-30"><input id="qypunishname" name="punishname"
                                   class="easyui-combobox" value="${jce.punishname }"
                                   style="width: 100%;height: 30px;" 
                                   data-options="readonly:'true',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td></td>
                         <td class="width-20 active"><label class="pull-right"> 企业地址： </label>
                         </td>
                         <td class="width-30"><input id="qyaddress" name="qyaddress"
                                   class="easyui-textbox" value="${jce.qyaddress }"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right"> 法定代表人：
                              </label></td>
                         <td class="width-30"><input id="legal" name="legal"
                                   class="easyui-textbox" value="${jce.legal }"
                                   style="width: 100%;height: 30px;" /></td>
                         <td class="width-20 active"><label class="pull-right"> 职务： </label></td>
                         <td class="width-30"><input id="duty" name="duty"
                                   class="easyui-textbox" value="${jce.duty }"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>
                    <tr name="company">
                         <td class="width-20 active"><label class="pull-right"> 企业邮编： </label>
                         </td>
                         <td class="width-30"><input name="qyyb" type="text" id="qyyb"
                                   value="${jce.qyyb }" class="easyui-textbox"
                                   style="width: 100%;height: 30px;" data-options="validType:'ZIP'" />
                         </td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right"> 被处罚人： </label>
                         </td>
                         <td class="width-30 "><input id="grpunishname" name="punishname"
                                   class="easyui-combobox" value="${jce.punishname }"
                                   style="width: 100%;height: 30px;" 
                                   data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/ygxx/ygxxlist/${yle.id1 }'" /></td>
                         <td class="width-20 active"><label class="pull-right"> 电话： </label></td>
                         <td class="width-30"><input id="pmobile" name="mobile"
                                   class="easyui-textbox" value="${jce.mobile }"
                                   style="width: 100%;height: 30px;" validtype="mobile" /></td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right"> 年龄： </label></td>
                         <td class="width-30"><input id="perage" name="age"
                                   class="easyui-textbox" value="${jce.age }"
                                   style="width: 100%;height: 30px;"
                                   data-options="validType:['length[0,2]','number']" /></td>
                         <td class="width-20 active"><label class="pull-right"> 性别： </label></td>
                         <td class="width-30"><input id="persex" name="sex"
                                   class="easyui-combobox" value="${jce.sex }"
                                   style="width:100%;height: 30px;"
                                   data-options="panelHeight:'auto',editable:false,data: [{value:'男',text:'男'},
									{value:'女',text:'女'}]" />
                         </td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right"> 所在单位： </label>
                         </td>
                         <td class="width-30" colspan="3"><input id="dwname" name="dwname"
                                   class="easyui-textbox" value="${jce.dwname }"
                                   style="width: 100%;height: 30px;" /></td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right"> 单位地址： </label>
                         </td>
                         <td class="width-30" colspan="3"><input id="dwaddress"
                                   name="dwaddress" class="easyui-textbox" value="${jce.dwaddress }"
                                   style="width:100%;height: 30px;" /></td>
                    </tr>
                    <tr name="person">
                         <td class="width-20 active"><label class="pull-right"> 家庭地址： </label>
                         </td>
                         <td class="width-30"><input id="paddress" name="address"
                                   class="easyui-textbox" value="${jce.address }"
                                   style="width: 100%;height: 30px;"
                                   data-options="validType:['length[0,100]']" /></td>
                         <td class="width-20 active"><label class="pull-right"> 家庭邮编： </label>
                         </td>
                         <td class="width-30"><input id="jtyb" name="jtyb"
                                   class="easyui-textbox" value="${jce.jtyb }"
                                   style="width:100%;height: 30px;" data-options="validType:'ZIP'" />
                         </td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">
                                   违法事实和处罚依据： </label></td>
                         <td class="width-30" colspan="3"><input id="illegalactandevidence"
                                   name="illegalactandevidence"
                                   value="${jce.illegalactandevidence }" class="easyui-textbox"
                                   style="width: 100%;height: 80px;"
                                   data-options="multiline:true,validType:['length[0,2000]']" /></td>
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right">
                                   当事人申辩意见： </label></td>
                         <td class="width-30" colspan="3"><input id="sbrecord" name="sbrecord"
                                   class="easyui-combobox" value="${jce.sbrecord }"
                                   style="width: 100%;height: 30px;"
                                   data-options="multiline:true,multiple:true,validType:['length[0,2500]'],valueField: 'id',textField: 'text',url:'${ctx}/aqzf/dict/json/sbyj'"/>
                            </td>
                                   
                    </tr>
                    <tr>
                         <td class="width-20 active"><label class="pull-right"> 承办人意见：
                              </label></td>
                         <td class="width-30" colspan="3"><input id="opinion" name="opinion"
                                   class="easyui-textbox" value="${jce.opinion }"
                                   style="width: 100%;height: 80px;"
                                   data-options="multiline:true,validType:['length[0,2500]']" /></td>
                    </tr>
                    
                <tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="cbr1" name="cbr1" class="easyui-combobox" value="${jce.cbr1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="cbr2" name="cbr2" class="easyui-combobox" value="${jce.cbr2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>    
                    
                <tr>
                	<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><input id="cbsj" name="cbsj" class="easyui-datebox" value="${jce.cbsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
                </tr>
                    
                <tr>
					<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${jce.fzshyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="fzshyj" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="fzshyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核人：</label></td>
					<td class="width-30"><input type="text" id="fzshr" name="fzshr" class="easyui-combobox" value="${jce.fzshr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-30"><input id="fzshsj" name="fzshsj" class="easyui-datebox" value="${jce.fzshsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>    
                    
                <tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${jce.shyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="shyj" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="shyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${jce.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><input id="shsj" name="shsj" class="easyui-datebox" value="${jce.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${jce.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30"><input type="text" name="spr" class="easyui-combobox" value="${jce.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><input id="spsj" name="spsj" class="easyui-datebox" value="${jce.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
                    
                    
                    
                    <!-- 隐藏域 -->
                    <input type='hidden' name="id1" value="${jce.id1}" />
                    <c:if test="${action eq 'updateSub' }">
                         <input type="hidden" name="ID" value="${jce.ID}" />
                         <input type='hidden' name="S1" value="<fmt:formatDate value="${jce.s1}"/>"/>
                    </c:if>
               </table>
          </tbody>
     </form>

     <script type="text/javascript">
			$(function() {
				if ('${action}' == 'createSub') {
					$("#qypunishname").combobox("setValue", '${yle.dsperson}');
					//$("#mobile").textbox("setValue", '${yle.contact}');
					$("#legal").textbox("setValue", '${yle.legalperson}');
					$("#qyaddress").textbox("setValue", '${yle.dsaddress}');
					$("#qyyb").textbox("setValue", '${yle.ybcode}');
					$("#illegalactandevidence").textbox("setValue", '${yle.casecondition}');
					$("input[name='id1']").val('${id1}');
					$("#sbrecord").combobox('setValue', '当事人六个月内未申辩');
					$("#casename").textbox('setValue', '${yle.casename}');
					$('#opinion').textbox('setValue', '${yle.opinion}');
					$("input[name='id1']").val('${id1}');
					comResult();
				} else {
					if ('${jce.percomflag}' == '1') {
						$("input[name='percomflag']").get(0).checked = true;
						comResult();
					} else {
						$("input[name='percomflag']").get(1).checked = true;
						personResult();
					}
				}
			});
			
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
			
			$("#grpunishname").combobox({
				onSelect : function() {
					var id = $('#grpunishname').combobox('getValue');
					$.ajax({
						type : 'get',
						url : '${ctx}/bis/ygxx/ygdetail/' + id,
						success : function(data) {
							var d = JSON.parse(data);

							$("#persex").combobox('setValue', d.m3);
							$("#pmobile").textbox('setValue', d.m9);
							$("#identity").textbox('setValue', d.m8);
							if(d.m10!=''&&d.m10!=null&&d.m10!=undefined){
								$("#perage").textbox('setValue', new Date().getFullYear() - new Date(d.m10).getFullYear());
							}
							$("#dwname").textbox('setValue', d.m1);
							//$("#perduty").textbox('setValue',d.m7);	
							$("#dwaddress").textbox('setValue', d.m33);
						}
					});
				}
			});
			
			$("#qypunishname").combobox({
				onSelect : function() {
					var id = $('#qypunishname').combobox('getValue');
					$.ajax({
						type : 'get',
						url : '${ctx}/bis/qyjbxx/qydetail/' + id,
						success : function(data) {
							var d = JSON.parse(data);
							$("#legal").textbox('setValue', d.m5);
							$("#mobile").textbox('setValue', d.m6);
							$("#qyyb").textbox('setValue', d.m9);
							$("#qyaddress").textbox('setValue', d.m8);

						}
					});
				}
			});

			function personResult() {
				$("[name='person']").show();
				$("[name='company']").hide();
				$("#qypunishname").combobox("disable");
				$("#legal").textbox("disable");
				$("#qyyb").textbox("disable");
				$("#duty").textbox("disable");
				$("#qyaddress").textbox("disable");
				$('#grpunishname').combobox('enable');
				$('#pmobile').textbox('enable');
				$('#perage').textbox('enable');
				$('#persex').textbox('enable');
				$('#perduty').textbox('enable');
				$('#dwname').textbox('enable');
				$('#dwaddress').textbox('enable');
				$('#paddress').textbox('enable');
				$('#jtyb').textbox('enable');
			}

			function comResult() {
				$("[name='person']").hide();
				$("[name='company']").show();
				$("#qypunishname").combobox("enable");
				$("#legal").textbox("enable");
				$("#qyyb").textbox("enable");
				$("#duty").textbox("enable");
				$("#qyaddress").textbox("enable");
				$('#grpunishname').combobox('disable');
				$('#pmobile').textbox('disable');
				$('#perage').textbox('disable');
				$('#persex').textbox('disable');
				$('#perduty').textbox('disable');
				$('#dwname').textbox('disable');
				$('#dwaddress').textbox('disable');
				$('#paddress').textbox('disable');
				$('#jtyb').textbox('disable');
			}

			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			function doSubmit() {
				if ($('input:radio:checked').val() == 0)
					$("#grpunishname").combobox("setValue", $("#grpunishname").combobox("getText"));
				else
					$("#qypunishname").combobox("setValue", $("#qypunishname").combobox("getText"));
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
						parent.layer.close(index); //关闭对话框。
					}
				});
			});
					</script>
</body>

</html>