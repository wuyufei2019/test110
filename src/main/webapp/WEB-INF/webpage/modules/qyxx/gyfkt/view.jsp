<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		  <tbody>
          <c:if test="${usertype != '1'}">
                 <tr>
                  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-35"><input value="${cse.ID1 }" id="_qyid" name="ID1" style="width: 630px;height:30px;" class="easyui-combobox"
                        data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
               </tr>
            </c:if>
		 <tr>
               <td class="width-15 active"><label class="pull-right">工艺名称：</label></td>
               <td class="width-35">${cse.name }</td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">简介：</label></td>
               <td class="width-35" >${cse.introduction}</td>
            </tr>


		<tr>
			<td class="width-15 active"><label class="pull-right">照片：</label></td>
			<td class="width-85" colspan="3"><c:if
					test="${not empty cse.url}">
					<c:forTokens items="${cse.url}" delims="," var="url"
						varStatus="urls">
						<c:set var="urlna" value="${fn:split(url, '||')}" />
						<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							<a target="_blank" href="${urlna[0]}">
							<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;" /><br />
							${urlna[1]}</a>
						</div>
					</c:forTokens>
				</c:if></td>
		</tr>
		</tbody>
	</table>
	</form>
</body>
</html>