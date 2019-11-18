<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>${code_description }</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
       <#assign x=0 />
          <#list columns as map> <#if map.col_ename!='ID'&&map.col_ename!='S1'&&map.col_ename!='S2'&&map.col_ename!='S3'!(map.col_showtype??&&(map.col_showtype!="file"||map.col_showtype!="image"))><#if x%2==0>
              <tr></#if><#if x%2==1&&map.col_length??&&(map.col_length?number>=200)>
              </tr>
              <tr></#if>
                  <td class="width-15 active"><label class="pull-right">${map.col_cname!'' }：</label></td>
   				  <td class="width-35" <#if map.col_length??&&(map.col_length?number>=200) > colspan="3"</#if>><#if map.col_datatype="datetime"><fmt:formatDate value="${r'${'}entity.${map.col_ename }}" pattern="yyyy-MM-dd" /><#else>${r'${'}entity.${map.col_ename }}</#if></td>
              <#if x%2==1 || !map_has_next || (x%2==0&&map.col_length??&&(map.col_length?number>=200))></tr></#if><#assign x=x+1/><#if map.col_length??&&(map.col_length?number>=200)> <#assign x=0/></#if></#if></#list>
		<#list columns as map><#if map.col_showtype??&&(map.col_showtype=="file"||map.col_showtype=="image")>
				<c:if test="${r'${not empty entity.'+map.col_ename+'}'}">
					<c:forTokens items="${r'${entity.'+map.col_ename+'}'}" delims="," var="url" varStatus="urls">
						<c:set var="urlna" value="${r"${fn:split(url, '||')}"}" />
						<div style="margin: 5px;">
							<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${r'${urlna[0]}'}">${r'${urlna[1]}'}</a>
						</div>
					</c:forTokens>
				</c:if>
		</#if></#list>
		</tbody>
	</table>

</body>
</html>