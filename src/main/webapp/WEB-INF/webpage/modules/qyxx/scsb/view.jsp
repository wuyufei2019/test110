<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">设备位号：</label></td>
					<td class="width-35" colspan="3">
						${scsblist.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备类别：</label></td>
					<td class="width-35">${scsblist.m2 }</td>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35">${scsblist.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格尺寸：</label></td>
					<td class="width-35">${scsblist.m4 }</td>
					<td class="width-15 active"><label class="pull-right">型号：</label></td>
					<td class="width-35">${scsblist.m5 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">功率（KW）：</label></td>
					<td class="width-35">${scsblist.m14 }</td>
					<td class="width-15 active"><label class="pull-right">出厂日期：</label></td>
					<td class="width-35"><fmt:formatDate value="${scsblist.m15 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备状况：</label></td>
					<td class="width-35">${scsblist.m16 }</td>
					<td class="width-15 active"><label class="pull-right">设备使用单位：</label></td>
					<td class="width-35"><input id="" name="M17" style="width: 100%;height: 30px;" class="easyui-combobox" value="${scsblist.m17 }" readonly="readonly" data-options="editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">主要技术参数：</label></td>
					<td class="width-85" colspan="3" >
					${scsblist.m18}
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35" >${scsblist.m6 }</td>
					<td class="width-15 active"><label class="pull-right">介质：</label></td>
					<td class="width-35" >
							${scsblist.m9 }
					</td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">投用日期：</label></td>
					<td class="width-35" ><fmt:formatDate value="${scsblist.m8 }"/></td>
					<td class="width-15 active"><label class="pull-right">下次检验日期：</label></td>
					<td class="width-35" >
							<fmt:formatDate value="${scsblist.m10 }"/>
					</td>	
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">制造单位：</label></td>
					<td class="width-35" colspan="3">
						${scsblist.m7 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">责任人：</label></td>
					<td class="width-35" >${scsblist.m11 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35" >
							${scsblist.m12 }
					</td>
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-85" colspan="3" >
					${scsblist.m13}
					</td>		
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>