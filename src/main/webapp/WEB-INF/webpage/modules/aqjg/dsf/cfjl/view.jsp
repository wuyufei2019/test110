<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方处罚记录信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-30">${cfjllist.dname }</td>
					<td class="width-20 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${cfjllist.s1 }"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚类型：</label></td>
					<td class="width-30" colspan="3">${cfjllist.m2 }</td>
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">处罚内容：</label></td>
					<td colspan="3"><input name="M5" type="text" value="${cfjllist.m3}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"></td> 
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td colspan="3"><input name="M8" type="text" value="${cfjllist.m5}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"></td> 
				</tr>
				
			</table>

		  	</tbody>
	 </form>
</body>
</html>