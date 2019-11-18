<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">介质：</label></td>
                      <td class="width-30" colspan="3">${entity.m1 }</td>
                  </tr>
                  <tr>
                      <td class="width-20 active"><label class="pull-right">容积（m³）：</label></td>
                      <td class="width-30">${entity.m2 }</td>
                      <td class="width-20 active"><label class="pull-right">用量（m³/月）：</label></td>
                      <td class="width-30">${entity.m3 }</td>
                  </tr>
                  <tr>
                      <td class="width-20 active"><label class="pull-right">气站性质：</label></td>
                      <td class="width-30">
                          <c:if test="${entity.m4 == '0'}">自建</c:if>
                          <c:if test="${entity.m4 == '1'}">租用</c:if>
                      </td>
                      <td class="width-20 active"><label class="pull-right">供气单位：</label></td>
                      <td class="width-30">${entity.m5 }</td>
                  </tr>
                  <tr>
                      <td class="width-20 active"><label class="pull-right">备注：</label></td>
                      <td class="width-30" colspan="3">${entity.m9 }</td>
                  </tr>

                  <tr style="height: 10px"></tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                      <td class="width-30" ><fmt:formatDate value="${entity.m8_1 }" pattern="yyyy-MM-dd" /></td>
                      <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                      <td class="width-30">${entity.m7_1 }</td>
                  </tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                      <td class="width-30" colspan="3">${entity.m6_1 }</td>
                  </tr>
                  <tr style="height: 7px"></tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                      <td class="width-30" ><fmt:formatDate value="${entity.m8_2 }" pattern="yyyy-MM-dd" /></td>
                      <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                      <td class="width-30">${entity.m7_2 }</td>
                  </tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                      <td class="width-30" colspan="3">${entity.m6_2 }</td>
                  </tr>
                  <tr style="height: 7px"></tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">实施日期：</label></td>
                      <td class="width-30" ><fmt:formatDate value="${entity.m8_3 }" pattern="yyyy-MM-dd" /></td>
                      <td class="width-20 active"><label class="pull-right">备案编号：</label></td>
                      <td class="width-30">${entity.m7_3 }</td>
                  </tr>
                  <tr >
                      <td class="width-20 active"><label class="pull-right">安评单位：</label></td>
                      <td class="width-30" colspan="3">${entity.m6_3 }</td>
                  </tr>

				</tbody>
			</table>
	</form>
</body>
</html>