<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	
			  	<tr>
					<td class="width-20 active"><label class="pull-right">储罐名称：</label></td>
					<td class="width-30">
						${cglist.m1 }
					</td>
			  		<td class="width-20 active"><label class="pull-right">储罐编号：</label></td>
					<td class="width-30">
						${cglist.m9 }
					</td>
			  	</tr>
			  	
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-30">
						${cglist.hazardcode }
					</td>
			  		<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30">
						${cglist.equipcode }
					</td>
			  	</tr>
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">存储物料名称：</label></td>
					<td class="width-30">
						${cglist.m7 }
					</td>
					<td class="width-20 active"><label class="pull-right">储存介质英文名称：</label></td>
					<td class="width-30">
						${cglist.stormediaename }
					</td>
				</tr>
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30">
						${cglist.m8 }
					</td>
					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30">
						${cglist.prodcellid }
					</td>
				</tr>
			  	
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">储罐状态：</label></td>
					<td class="width-30">
						<c:if test="${cglist.jarstatus == 'H4701'}">
							液态
						</c:if>
						<c:if test="${cglist.jarstatus == 'H4702'}">
							气态
						</c:if>
						<c:if test="${cglist.jarstatus == 'H4703'}">
							固态
						</c:if>
					</td>
					<td class="width-15 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-85">
						<c:if test="${cglist.ID3 == 'H580101'}">
							急性毒性 类别1（气体）
						</c:if>
						<c:if test="${cglist.ID3 == 'H580102'}">
							急性毒性 类别2（气体）
						</c:if>
						<c:if test="${cglist.ID3 == 'H580103'}">
							急性毒性 类别3（气体）
						</c:if>
						<c:if test="${cglist.ID3 == 'H580201'}">
							易燃气体 类别1
						</c:if>
						<c:if test="${cglist.ID3 == 'H580202'}">
							易燃气体 类别2
						</c:if>
						<c:if test="${cglist.ID3 == 'H580301'}">
							易燃液体 类别1
						</c:if>
						<c:if test="${cglist.ID3 == 'H580302'}">
							易燃液体 类别2
						</c:if>
						<c:if test="${cglist.ID3 == 'H580303'}">
							易燃液体 类别3
						</c:if>
					</td>
				</tr>
			  	
			  	
			  	<tr>
			  		<td class="width-20 active"><label class="pull-right">储罐类型：</label></td>
					<td class="width-30">
						${cglist.m2}
					</td>
			  		<td class="width-15 active"><label class="pull-right">储罐型式：</label></td>
					<td class="width-85">
						${cglist.jarmode }
					</td>
			  	</tr>
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">容积（㎥）：</label></td>
					<td class="width-30">
						${cglist.m3 }
					</td>
					<td class="width-15 active"><label class="pull-right">规格（D*H）：</label></td>
					<td class="width-85">
						${cglist.spec }
					</td>
				</tr>
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">罐径（m）：</label></td>
					<td class="width-30">${cglist.m10 }</td>
					<td class="width-20 active"><label class="pull-right">罐高（m）：</label></td>
					<td class="width-30">${cglist.m11 }</td>
				</tr>
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">材质：</label></td>
					<td class="width-30" >${cglist.m4 }</td>
					<td class="width-20 active"><label class="pull-right">是否正常状态：</label></td>
					<td class="width-30">
						<c:if test="${cglist.isnormal == '0'}">
							否
						</c:if>
						<c:if test="${cglist.isnormal == '1'}">
							是
						</c:if>
					</td>
				</tr>

			  	<tr >
					<td class="width-20 active"><label class="pull-right">MAC最大允许浓度（有毒ppm）：</label></td>
					<td class="width-30" >
						${cglist.maxmacthick }
					</td>
					<td class="width-20 active"><label class="pull-right">临界量倍数值：</label></td>
					<td class="width-30">
						${cglist.criticalmultiplevalue }
					</td>
				</tr>
			  	
			  	
			  	<tr >
					<td class="width-20 active"><label class="pull-right">危险化学品重大危险源临界量（T）：</label></td>
					<td class="width-30">
						${cglist.dangechemcritical }
					</td>
					<td class="width-15 active"><label class="pull-right">储罐设计储存能力（T）：</label></td>
					<td class="width-85">
						${cglist.designcapacity }
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">压力计量单位：</label></td>
					<td class="width-30">
						<c:if test="${cglist.pressureunit == 'HJC01'}">
							MPa
						</c:if>
						<c:if test="${cglist.pressureunit == 'HJC02'}">
							KPa
						</c:if>
						<c:if test="${cglist.pressureunit == 'HJC03'}">
							bar
						</c:if>
						
					</td>
					<td class="width-15 active"><label class="pull-right">正常工作温度上限（℃）：</label></td>
					<td class="width-85">
						${cglist.normaltemtop }
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">储罐设计压力：</label></td>
					<td class="width-30">
						${cglist.designpressure }
					</td>
					<td class="width-15 active"><label class="pull-right">正常工作压力上限：</label></td>
					<td class="width-85">
						${cglist.normalpressuretop }
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">应急处置：</label></td>
					<td class="width-30" colspan="3">
						${cglist.emerplan }
					</td>
				</tr>
				
				
				<tr >
					<td class="width-20 active"><label class="pull-right">经度：</label></td>
					<td class="width-30">
						${cglist.longitude }
					</td>
					<td class="width-20 active"><label class="pull-right">纬度：</label></td>
					<td class="width-30">
						${cglist.latitude }
					</td>
				</tr>
				
				
				<tr >
					<td class="width-20 active"><label class="pull-right">重点监管危险化工工艺标识：</label></td>
					<td class="width-30">
						${cglist.chemartid }
					</td>
					<td class="width-20 active"><label class="pull-right">重点监管危险化工工艺名称：</label></td>
					<td class="width-30">
						${cglist.chemartart }
					</td>
				</tr>
				
				
				<tr >
					<td class="width-20 active"><label class="pull-right">所属储罐区：</label></td>
					<td class="width-30">
						${cglist.m22 }
					</td>
					<td class="width-20 active"><label class="pull-right">储罐区面积（㎥）：</label></td>
					<td class="width-30" >
							${cglist.m12 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">火灾危险性等级：</label></td>
					<td class="width-30" >${cglist.m6 }</td>
					<td class="width-20 active"><label class="pull-right">有无防火堤：</label></td>
					<td class="width-30" >${cglist.m13 }</td>
				</tr>
				
				<c:if test="${cglist.m13 =='有'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">防火堤所围面积（㎥）：</label></td>
						<td class="width-30" >
								${cglist.m14 }
							</td>
					</tr>
				</c:if>

				
				
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
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>