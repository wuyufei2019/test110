<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
	<title>在线监测指标</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
	<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
	<script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
	<script type="text/javascript">
		var usertype =${usertype};
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;
	</script>
</head>
<body>

<form id="inputForm" action="${ctx}/jtjcsj/sjcjsb/${action}" method="post" class="form-horizontal">
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
		<c:if test="${usertype == '0'}">
			<tr>
				<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
				<td class="width-30" colspan="3">${zxjczb.qyname }</td>
			</tr>
		</c:if>

		<tr>
			<td class="width-20 active"><label class="pull-right">网关编码：</label></td>
			<td class="width-30">
				${sjcjsb.gatewaycode }
			</td>
			<td class="width-20 active"><label class="pull-right">网关名称：</label></td>
			<td class="width-30">
				${sjcjsb.gatewayname }
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">供应商：</label></td>
			<td class="width-30">
				${sjcjsb.supplier }
			</td>
			<td class="width-20 active"><label class="pull-right">网关类别：</label></td>
			<td class="width-30">
				${sjcjsb.gatewaytype }
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">网关型号：</label></td>
			<td class="width-30">
				${sjcjsb.gatewaymodel }
			</td>
			<td class="width-20 active"><label class="pull-right">IP地址：</label></td>
			<td class="width-30">
				${sjcjsb.ipaddr }
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">访问端口：</label></td>
			<td class="width-30">
				${sjcjsb.portno }
			</td>
			<td class="width-20 active"><label class="pull-right">子网掩码：</label></td>
			<td class="width-30">
				${sjcjsb.netmask }
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">网关地址：</label></td>
			<td class="width-30">
				${sjcjsb.gateway }
			</td>
			<td class="width-20 active"><label class="pull-right">安装位置：</label></td>
			<td class="width-30">
				${sjcjsb.installloc }
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">安装日期：</label></td>
			<td class="width-30">
				<fmt:formatDate value="${sjcjsb.installdate }" pattern="yyyy-MM-dd"  />
			</td>
			<td class="width-20 active"><label class="pull-right">采集频率(秒)：</label></td>
			<td class="width-30">
				${sjcjsb.frequncey }
			</td>
		</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">

</script>
</body>
</html>