<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐区管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr >
				<td class="width-20 active"><label class="pull-right">储罐区编号：</label></td>
				<td class="width-30">${cgqxx.m1 }</td>

				<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
				<td class="width-30">${cgqxx.equipcode }</td>
			</tr>

			<tr >
				<td class="width-20 active"><label class="pull-right">储罐区名称：</label></td>
				<td class="width-30" colspan="3">${cgqxx.m2 }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">储罐区面积(㎡)：</label></td>
				<td class="width-30">${cgqxx.m3 }</td>

				<td class="width-20 active"><label class="pull-right">储罐个数：</label></td>
				<td class="width-30">${cgqxx.m4 }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">储罐区位置：</label></td>
				<td colspan="3" style="height:30px;line-height:30px;">
					<label>经度：</label>${cgqxx.m6 }
					<label>纬度：</label>${cgqxx.m7 }
				</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">罐间最小距离(m)：</label></td>
				<td class="width-30" >${cgqxx.m5 }</td>

				<td class="width-20 active"><label class="pull-right">防护堤长度(m)：</label></td>
				<td class="width-30" >${cgqxx.m9 }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">防护堤宽度(m)：</label></td>
				<td class="width-30" >${cgqxx.m10 }</td>

				<td class="width-20 active"><label class="pull-right">防护堤高度(m)：</label></td>
				<td class="width-30" >${cgqxx.m11 }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">备注：</label></td>
				<td class="width-30" colspan="3" style="height: 80px;">${cgqxx.m8 }</td>
			</tr>
		</tbody>
	</table>

	<c:if test="${cglist ne null}">
		<c:forEach items="${cglist}" var="cglist" varStatus="count">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
				<tr>
					<td colspan="4" style="text-align: center;font-size: 20px;color: red;">储罐${count.count}</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">所属储罐区：</label></td>
					<td class="width-30">
							${cglist.m22 }
					</td>
					<td class="width-20 active"><label class="pull-right">位号：</label></td>
					<td class="width-30">
							${cglist.M9 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
					<td class="width-30" colspan="3">
							${cglist.M1 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
					<td class="width-30" colspan="3">
							${cglist.M7 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-85" colspan="3">
							${cglist.id3 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30">${cglist.M10 }</td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30">${cglist.M11 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
					<td class="width-30">
						<c:if test="${cglist.M2 eq '1'}">立式圆筒形储罐</c:if>
						<c:if test="${cglist.M2 eq '2'}">卧式圆筒形储罐</c:if>
						<c:if test="${cglist.M2 eq '3'}">球形储罐</c:if>
						<c:if test="${cglist.M2 eq '4'}">其他储罐</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30">${cglist.M3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">材质：</label></td>
					<td class="width-30" >
						<c:if test="${cglist.M4 eq '1'}">滚塑</c:if>
						<c:if test="${cglist.M4 eq '2'}">玻璃钢</c:if>
						<c:if test="${cglist.M4 eq '3'}">碳钢</c:if>
						<c:if test="${cglist.M4 eq '4'}">陶瓷</c:if>
						<c:if test="${cglist.M4 eq '5'}">橡胶</c:if>
						<c:if test="${cglist.M4 eq '6'}">其他</c:if>
						<c:if test="${cglist.M4 eq '7'}">不锈钢</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">储罐区面积（㎥）：</label></td>
					<td class="width-30" >
							${cglist.m12 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">有无防火堤：</label></td>
					<td class="width-30" >${cglist.m13 }</td>
					<td class="width-20 active"><label class="pull-right">防火堤所围面积（㎥）：</label></td>
					<td class="width-30" >
							${cglist.m14 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">火灾危险性等级：</label></td>
					<td class="width-30" >
						<c:if test="${cglist.M6 eq '1'}">甲类</c:if>
						<c:if test="${cglist.M6 eq '2'}">乙类</c:if>
						<c:if test="${cglist.M6 eq '3'}">丙类</c:if>
						<c:if test="${cglist.M6 eq '4'}">丁类</c:if>
						<c:if test="${cglist.M6 eq '5'}">戊类</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30" >
							${cglist.M8 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">温度传感器设备编码：</label></td>
					<td class="width-30" >${cglist.m17 }</td>
					<td class="width-20 active"><label class="pull-right">压力传感器设备编码：</label></td>
					<td class="width-30" >
							${cglist.m18 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">液位传感器设备编码：</label></td>
					<td class="width-30" >${cglist.m19 }</td>
					<td class="width-20 active"><label class="pull-right">可燃气体传感器设备编码：</label></td>
					<td class="width-30" >
							${cglist.m20 }
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">有毒气体传感器设备编码：</label></td>
					<td class="width-30" >${cglist.m21 }</td>
					</td>

				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
						<c:if test="${not empty cglist.m15}">
							<c:forTokens items="${cglist.m15}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
									<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[0]}</a>
								</div>
							</c:forTokens>
						</c:if>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-30" colspan="3">
						<c:if test="${not empty cglist.m16}">
							<c:forTokens items="${cglist.m16}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="margin: 5px;">
									<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
								</div>
							</c:forTokens>
						</c:if>
					</td>
				</tr>
				</tbody>
			</table>
		</c:forEach>
	</c:if>

</body>
</html>