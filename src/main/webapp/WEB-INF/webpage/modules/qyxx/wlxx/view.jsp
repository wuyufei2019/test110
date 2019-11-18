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
					<td class="width-20 active"><label class="pull-right">中文名：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m1 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">别名：</label></td>
					<td class="width-30">${wllist.chemicalalias }</td>
					<td class="width-20 active"><label class="pull-right">化学品类型：</label></td>
					<td class="width-30">
						<c:if test="${wllist.m11 =='1'}">
							产品
						</c:if>
						<c:if test="${wllist.m11 =='2'}">
							中间产品
						</c:if>
						<c:if test="${wllist.m11 =='3'}">
							进口化产品
						</c:if>
						<c:if test="${wllist.m11 =='4'}">
							原料
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
					<td class="width-30">${wllist.m4 }</td>
					<td class="width-20 active"><label class="pull-right">储存方式：</label></td>
					<td class="width-30">${wllist.m5 }</td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">产品生产能力（吨）：</label></td>
					<td class="width-30">${wllist.capacity }</td>
					<td class="width-20 active"><label class="pull-right">产品最大储量（吨）：</label></td>
					<td class="width-30">${wllist.reserve }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">产品生产能力气体（方）：</label></td>
					<td class="width-30">${wllist.capacitygas }</td>
					<td class="width-20 active"><label class="pull-right">产品最大储量气体（方）：</label></td>
					<td class="width-30">${wllist.reservegas }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">危化品作用：</label></td>
					<td class="width-30">
						<c:if test="${wllist.chemicaltype =='HEX01'}">
							原料
						</c:if>
						<c:if test="${wllist.chemicaltype =='HEX02'}">
							中间产物
						</c:if>
						<c:if test="${wllist.chemicaltype =='HEX03'}">
							产品
						</c:if>
						<c:if test="${wllist.chemicaltype =='HEX04'}">
							副产品
						</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">是否重点监管危化品：</label></td>
					<td class="width-30" >${wllist.m12 }</td>
				</tr>
				
				
				<tr >
					<td class="width-20 active"><label class="pull-right">危化品类别：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m8 }
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-30" colspan="3">
					${wllist.m6 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">状态：</label></td>
					<td class="width-30" >${wllist.m9 }</td>
					<td class="width-20 active"><label class="pull-right">是否领证：</label></td>
					<td class="width-30" >
							${wllist.m10 }
						</td>
					
				</tr>
				
				<tr>

					<td class="width-20 active"><label class="pull-right">剧毒：</label></td>
					<td class="width-30" >
							${wllist.m13 }
						</td>
					<td class="width-20 active"><label class="pull-right">易制毒：</label></td>
					<td class="width-30" >${wllist.m14 }</td>	
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >
					${wllist.m7 }
					</td>			
				</tr>
		  	</tbody>
			</table>
       </form>

</body>
</html>