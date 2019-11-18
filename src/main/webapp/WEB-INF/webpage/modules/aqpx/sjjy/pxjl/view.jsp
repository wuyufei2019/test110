<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>三级教育培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
			  		<td class="width-15 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-35"> ${yg.m1} </td>  
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35"> 
					${yg.m3 eq '1' ? '男' : yg.m3 eq '2' ? '女' : yg.m3 } 
					</td>
				</tr>
				
				<tr >
			  		<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"> ${yg.m8} </td>  
					<td class="width-15 active"><label class="pull-right">出生日期：</label></td>
					<td class="width-35"><fmt:formatDate value="${yg.m10 }" pattern="yyyy-MM-dd"  />  </td>
				</tr>
				
				<tr>
			  		<td class="width-15 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-35"> ${dep.m1} </td>  
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35"> ${sjjy.m1 } </td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${sjjy.m2 }" pattern="yyyy-MM-dd"  /></td>
					<td class="width-20 active"><label class="pull-right">考试成绩：</label></td>
					<td class="width-30">${sjjy.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训结果：</label></td>
					<td class="width-30">${sjjy.m8 }</td>
					<td class="width-20 active"><label class="pull-right">厂级教育人：</label></td>
					<td class="width-30">${sjjy.m4 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">车间教育人：</label></td>
					<td class="width-30">${sjjy.m5 }</td>
					<td class="width-20 active"><label class="pull-right">班组教育人：</label></td>
					<td class="width-30">${sjjy.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训内容：</label></td>
					<td class="width-80" colspan="3">${sjjy.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">${sjjy.m9 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty sjjy.m10}">
					 <c:forTokens items="${sjjy.m10}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</div> 
					</td>
				</tr>
				
			</table>

		  	</tbody>
	 </form>
</body>
</html>