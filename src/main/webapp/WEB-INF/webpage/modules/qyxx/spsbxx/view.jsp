<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		    <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30">${spsbxx.hazardname } </td>
					<td class="width-20 active"><label class="pull-right">重大危险源标识：</label></td>
					<td class="width-30">${spsbxx.hazardcode } </td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30" colspan="3">${spsbxx.prodcellid } </td>
				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头编码：</label></td>
					<td class="width-30">${spsbxx.m1 }</td>

					<td class="width-20 active"><label class="pull-right">摄像头类型：</label></td>
					<td class="width-30">${spsbxx.m2 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30">${spsbxx.m3 }</td>

					<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30">${spsbxx.m4 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">RTSP视频流：</label></td>
					<td class="width-30" colspan="3">${spsbxx.rtsp }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">接口请求地址：</label></td>
					<td class="width-30" colspan="3">${spsbxx.apiaddress }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">视频播放地址：</label></td>
					<td class="width-30" colspan="3">${spsbxx.playaddress }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">url：</label></td>
					<td class="width-30" colspan="3">${spsbxx.url }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">是否为重大危险源区域：</label></td>
					<td class="width-30">
						<c:if test="${spsbxx.m21 eq '0'}">否</c:if>
						<c:if test="${spsbxx.m21 eq '1'}">是</c:if>
					</td>

					<td class="width-20 active"><label class="pull-right">设备IP：</label></td>
					<td class="width-30">${spsbxx.m5}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头名称：</label></td>
					<td class="width-30" colspan="3">${spsbxx.m20}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">外观类型：</label></td>
					<td class="width-30">${spsbxx.m6}</td>

					<td class="width-20 active"><label class="pull-right">布控区域：</label></td>
					<td class="width-30">${spsbxx.m9 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经度：</label></td>
					<td class="width-30">${spsbxx.m7}</td>

					<td class="width-20 active"><label class="pull-right">纬度：</label></td>
					<td class="width-30">${spsbxx.m8}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">通道号：</label></td>
					<td class="width-30">${spsbxx.m10 }</td>

					<td class="width-20 active"><label class="pull-right">端口号：</label></td>
					<td class="width-30">${spsbxx.m11}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头顺序号：</label></td>
					<td class="width-30">${spsbxx.m12}</td>

					<td class="width-20 active"><label class="pull-right">登录用户：</label></td>
					<td class="width-30">${spsbxx.m13}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">登录密码：</label></td>
					<td class="width-30">${spsbxx.m14}</td>

					<td class="width-20 active"><label class="pull-right">客户端接入服务器ID：</label></td>
					<td class="width-30">${spsbxx.m15}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">企业负责人：</label></td>
					<td class="width-30">${spsbxx.m16}</td>

					<td class="width-20 active"><label class="pull-right">负责人联系电话：</label></td>
					<td class="width-30">${spsbxx.m17}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">生产厂家：</label></td>
					<td class="width-30">${spsbxx.m18}</td>

					<td class="width-20 active"><label class="pull-right">主要技术参数：</label></td>
					<td class="width-30">${spsbxx.m19}</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>