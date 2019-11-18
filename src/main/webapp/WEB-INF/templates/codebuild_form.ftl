<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>${code_description }</title>
	<meta name="decorator" content="default" />
	<#list columns as map><#if map.col_showtype??&&(map.col_showtype=="file"||map.col_showtype=="image")><script src="${r'${ctxStatic}'}/common/webUpload.js"></script></#if></#list>
	</head>
<body>
	<form id="inputForm" action="${r'${ctx}'}/${code_mapurl!'' }/${r'${'}action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
          <#assign x=0 />
             <#list columns as map> <#if map.col_ename!='ID'&&map.col_ename!='S1'&&map.col_ename!='S2'&&map.col_ename!='S3'&&!(map.col_showtype??&&(map.col_showtype!="file"||map.col_showtype!="image"))><#if x%2==0>
                 <tr></#if><#if x%2==1&&map.col_length??&&(map.col_length?number>=200)>
                 </tr>
                 <tr></#if>
                     <td class="width-15 active"><label class="pull-right">${map.col_cname!'' }：</label></td>
      				 <td class="width-35" <#if map.col_length??&&(map.col_length?number>=200) > colspan="3"</#if>><input name="${map.col_ename }" id="${map.col_ename }" style="width: 100%;height: ${(map.col_length??&&map.col_length?number>=200)?string((map.col_length??&&map.col_length?number>=300)?string(8,5),3)}0px;" class="easyui-${map.col_showtype!'textbox' }"
      					 value="${r'${'}entity.${map.col_ename } }" data-options="<#if map.col_nullable??&&map.col_nullable=="false"> required:'true',</#if><#if map.col_length??&&(map.col_length?number>=200) >multiline: true,</#if><#if map.col_showtype??&&map.col_showtype=='combobox'><#if map.col_multiple??&&map.col_multiple=="true">multiple:true,</#if><#if map.col_editable??&&map.col_editable=="false">editable:false,</#if>valueField: 'value',textField: 'text',panelHeight:'auto',data:[{text:'',value:''}],</#if><#if map.col_datatype=='int'|| map.col_datatype=='bigint'>validType:['integ']</#if><#if map.col_datatype=='float'|| map.col_datatype=='numeric(18,2)'>validType:['number']</#if><#if map.col_datatype=='varchar'> validType:['length[0,${(map.col_length?number)/2 }]']</#if>" /></td>
                 <#if x%2==1 || !map_has_next || (x%2==0&&map.col_length??&&(map.col_length?number>=200))></tr></#if><#assign x=x+1/><#if map.col_length??&&(map.col_length?number>=200)> <#assign x=0/></#if></#if></#list>
				<#list columns as map><#if map.col_showtype??&&(map.col_showtype=="file"||map.col_showtype=="image")>
				<tr>
                    <td class="width-15 active"><label class="pull-right"><#if map.col_showtype=="file">附件：</#if><#if map.col_showtype=="image">图片：</#if></label></td>
                    <td class="width-35" colspan="3">
                        <div id="${map.col_ename}_uploader" style="margin-left:10px"></div>
                    </td>
                </tr>
				</#if></#list>
				<c:if test="${r'${'}not empty entity.ID}">
					<input type="hidden" name="ID" value="${r'${'}entity.ID}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${r'${'}entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${r'${'}entity.s3}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		<#list columns as map><#if map.col_showtype??><#if map.col_showtype=="file">
		$("#${map.col_ename}_uploader").WebFileUpload({hiddenInputId:'${map.col_ename}',existurl:'${r'${entity.'}${map.col_ename+'}'}'});
		</#if>
		<#if map.col_showtype=="image">$("#${map.col_ename}_uploader").WebImageUpload({hiddenInputId:'${map.col_ename}',existurl:'${r'${entity.'}${map.col_ename+'}'}'});</#if>
		</#if></#list>
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
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