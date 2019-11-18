<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用预算管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${fyys.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${fyys.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35" colspan="3">
						${fyys.m1 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">支出项目类别：</label></td>
					<td class="width-35" colspan="3">
						${fyys.lx }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">项目说明：</label></td>
					<td class="width-30" colspan="3" style="height:80px" >${fyys.m5 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">预算费用（万元）：</label></td>
					<td class="width-35" colspan="3">
						${fyys.m3 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">预算识别人：</label></td>
					<td class="width-35" colspan="3">${fyys.m4 }</td>
				</tr>
				
			</table>

		  	<tbody>
       </form>
 
</body>
</html>