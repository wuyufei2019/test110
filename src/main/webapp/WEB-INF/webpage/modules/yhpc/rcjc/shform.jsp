<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>审核表单信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/yhpc/rcjc/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                  
                 <tr>
                     <td class="width-15 active"><label class="pull-right">检查日期：</label></td>
      				 <td class="width-35" ><fmt:formatDate value="${entity.m1}" pattern="yyyy-MM-dd" /></td>
					 <td class="width-15 active"><label class="pull-right">严重程度：</label></td>
					 <td class="width-35" >${entity.m16 }</td>
                 </tr> 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">辖区部门：</label></td>
      				 <td class="width-35" >${entity.m3 }</td>
                 	 <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
      				 <td class="width-35" >${entity.m2 }</td>
                 </tr> 
                 <tr>
                 	<td class="width-15 active"><label class="pull-right">检查类型：</label></td>
      				<td class="width-35" >${entity.m6_1 }</td>
					<td class="width-15 active"><label class="pull-right">缺失类型：</label></td>
      				<td class="width-35" >${entity.m6_2 }</td>
                 </tr> 

                 <tr>
                     <td class="width-15 active"><label class="pull-right">责任部门主管：</label></td>
      				 <td class="width-35" >${m8 }</td>
					 <td class="width-15 active"><label class="pull-right">计划完成时间：</label></td>
					 <td class="width-35" ><fmt:formatDate value="${entity.m10}" pattern="yyyy-MM-dd" /></td>
                 </tr>
                 
                 <tr>
					 <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					 <td class="width-85" colspan="3">${entity.m18 }</td>
                 </tr>
                 <tr>
                  	 <td class="width-15 active"><label class="pull-right">现场照片：</label></td>
				  	 <td class="width-85" colspan="3">
					   	 <c:if test="${not empty entity.m4}">
						   	 <c:forTokens items="${entity.m4}" delims="," var="url" varStatus="urls">
						 	  	 <c:set var="urlna" value="${fn:split(url, '||')}" />
						 	  	 <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		  	 <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;"/><br/></a>
						 	  	 </div>
						   	 </c:forTokens>
					   	 </c:if>
				  	 </td>
              	 </tr>

				 <tr>
					 <td class="width-15 active"><label class="pull-right">缺失情况：</label></td>
					 <td class="width-35"  colspan="3">${entity.m5 }</td>
				 </tr>
				 <tr ><td style="background-color: #50a8f1;" colspan="4"></td></tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right" style="height: 25px">审核：</label></td>
					<td class="width-35" >						
							<input type="radio" value="1" class="i-checks" name="shstate" />通过
							<input type="radio" value="2" class="i-checks" name="shstate" checked="checked"/>不通过
					</td>
					<td class="width-15 active" id="zgr1" style="display:none"><label class="pull-right">制定整改人：</label></td>
					<td class="width-35" id="zgr2" style="display:none">
						<input name="M9" id="rcjc_zdzgr" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m9 }" data-options="valueField: 'id',textField: 'text', url:'${ctx}/system/user/ryjson?depid=${bmid }' " />
					</td>
				</tr>
				
				<tr id="shfk">
					<td class="width-15 active" ><label class="pull-right">审核反馈：</label></td>
					<td class="width-85" colspan="3">
						<input name="M17" id="M17" style="width: 100%;height: 80px;" class="easyui-textbox"
															  value="${entity.m17 }" data-options="multiline: true,validType:['length[0,250]']" />
					</td>
				</tr>

				 <input type="hidden" name="ID" value="${entity.ID}" />
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
	
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			if($("#M17").val() == '' && $("#shfk").css('display') !== 'none') {
				layer.msg("请填写审核反馈！",{time: 1000});
				return;
			}
			if($("#rcjc_zdzgr").combobox('getValue') == '' && $("#zgr2").css('display') !== 'none') {
				layer.msg("请选择制定整改人！",{time: 1000});
				return;
			}
			if($("#zgr2").css('display') === 'none') {
				$("#rcjc_zdzgr").combobox('setValue','');
			}
			if($("#shfk").css('display') === 'none') {
				$("#M17").textbox('setValue','');
			}
			$("#inputForm").submit();
		}
			
		$(function() {
			$("input[name='shstate']").on('ifChecked', function(event){    
			    var selectedvalue = $(event.target).val();
				if (selectedvalue == "1") {
					$("#zgr1").show();
					$("#zgr2").show();
					$("#shfk").hide();
				} 
				if (selectedvalue == "2"){
					$("#zgr1").hide();
					$("#zgr2").hide();
					$("#shfk").show();
				}
			});
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