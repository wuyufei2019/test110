<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全管理制度评审</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
		var flag=true;
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
		    	if(isValid&&flag){
		    		flag=false;
		    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
		    		return true;
		    	}
				return false;	// 返回false终止表单提交
		    },    
		    success:function(data){ 
		    	$.jBox.closeTip();
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	});
	</script>
</head>
<body>
     <form id="inputForm" action="${ctx}/zdgl/aqps/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${action != 'createps' }">
					<tr>
						<td class="width-15 active"><label class="pull-right">选择制度：</label></td>
						<td class="width-35" colspan="3">
							<input value="${aqps.m1 }" id="M1" name="M1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',multiple:true,valueField: 'id',textField: 'text',url:'${ctx}/zdgl/aqps/zdidjson'" />
						</td>
					</tr>
				</c:if>
			 	<tr >
					<td class="width-15 active"><label class="pull-right">评审主题：</label></td>
					<td class="width-35" colspan="3">
						<input id="M2" name="M2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m2 }" data-options="required:'true',validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">主持人：</label></td>
					<td class="width-35" >
						<input id="M5" name="M5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m5 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">评审日期：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-datebox" style="width: 100%;height: 30px;" value="${aqps.m3 }" data-options="editable:false" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35" colspan="3">
						<input id="M4" name="M4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m4 }" data-options="validType:'length[0,100]'" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审依据：</label></td>
					<td class="width-35" colspan="3">
						<input id="M6" name="M6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m6 }" data-options="validType:'length[0,500]'" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审范围：</label></td>
					<td class="width-35" colspan="3">
						<input id="M7" name="M7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m7 }" data-options="validType:'length[0,250]'" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审人员：</label></td>
					<td class="width-35" colspan="3">
						<input id="M8" name="M8" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m8 }" data-options="validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">评审纪要：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M9" class="easyui-textbox" value="${aqps.m9 }"  data-options="multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">评审结论：</label></td>
					<td class="width-35" colspan="3">
						<input id="M10" name="M10" class="easyui-textbox" style="width: 100%;height: 30px;" value="${aqps.m10 }" data-options="validType:'length[0,100]'" />
					</td>
				</tr>
				
				<c:if test="${action eq 'update'}">
					<c:if test="${type eq 'sh'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${aqps.m13=='0'}">
										<input type="radio" value="1" class="i-checks" name="M13" />同意
										<input type="radio" value="0" class="i-checks"  name="M13" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M13" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M13" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="2" />
					</c:if>
					
					<c:if test="${type eq 'pz'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<input class="easyui-textbox" style="width: 100%;height: 30px;" value="同意" data-options="readonly:'true'"/>
							</td>
							<td class="width-15 active"><label class="pull-right">领导批准意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${aqps.m16=='0'}">
										<input type="radio" value="1" class="i-checks" name="M16" />同意
										<input type="radio" value="0" class="i-checks"  name="M16" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M16" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M16" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="3" />
						<input type="hidden" name="M12" value="${aqps.m12}" />
						<input type="hidden" name="M13" value="${aqps.m13}" />
						<input type="hidden" name="M14"
							value="<fmt:formatDate value="${aqps.m14}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</c:if>
				</c:if>
				
				<c:if test="${action eq 'createps'}">
					<input type="hidden" name="M18" value="${aqps.m18}" />
					<input type="hidden" name="M1" value="${aqps.m1}" />
				</c:if>
				<c:if test="${action eq 'update'}">
					<input type="hidden" name="ID" value="${aqps.ID}" />
					<input type="hidden" name="ID1" value="${aqps.ID1}" />
					<input type="hidden" name="M18" value="${aqps.m18}" />
					<input type="hidden" name="M20" value="${aqps.m20}" />
					<input type="hidden" name="M19"
						value="<fmt:formatDate value="${aqps.m19}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
    
</script>
</body>
</html>