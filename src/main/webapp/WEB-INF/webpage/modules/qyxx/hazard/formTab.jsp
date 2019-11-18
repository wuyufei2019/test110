<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
</head>
<body>

<form  class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active" ><label class="pull-right">企业档案R值：</label></td>
					<td class="width-30">${qylist.m2 }</td>
					<td class="width-20 active" ><label class="pull-right">重大危险源级别：</label></td>
					<td class="width-30">
						<c:if test="${qylist.m1 eq '1'}"> 
						   一级
						</c:if> 
						<c:if test="${qylist.m1 eq '2'}"> 
						   二级
						</c:if> 
						<c:if test="${qylist.m1 eq '3'}"> 
						   三级
						</c:if> 
						<c:if test="${qylist.m1 eq '4'}"> 
						  四级
						</c:if> 
					</td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">厂区人数：</label></td>
					<td class="width-30">${qylist.m3 }</td>
					<td class="width-20 active" ><label class="pull-right">是否有监控预警系统：</label></td>
					<td class="width-30">
						<c:if test="${qylist.m4 eq '0'}"> 
						   否
						</c:if> 
						<c:if test="${qylist.m4 eq '1'}"> 
						  是
						</c:if> 
					</td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">系统计算R值：</label></td>
					<td class="width-30">${qylist.m9 }</td>
					
					<td class="width-20 active" ><label class="pull-right">主要危险性：</label></td>
					<td class="width-30">${qylist.label }</td>
					
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">联系人：</label></td>
					<td class="width-30">${qylist.m6 }</td>
					<td class="width-20 active" ><label class="pull-right">联系电话：</label></td>
					<td class="width-30">${qylist.m7 }</td>
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">安全监控措施：</label></td>
					<td class="width-30" colspan="3">${qylist.m8 }</td>
				</tr>
				</tbody>
			</table>
</form>

<script type="text/javascript">
	 
</script>
</body>
</html>