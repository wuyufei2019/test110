<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">保养计划信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">保养计划名称：</label></td>
               <td class="width-35" >${sbgl.m6 }</td>
               <td class="width-15 active"><label class="pull-right">制定人：</label></td>
               <td class="width-35" >${sbgl.m7 }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">制定日期：</label></td>
               <td class="width-35" ><fmt:formatDate value="${sbgl.m8}" /></td>
            </tr>
            <tr>
               <td class="width-15 active" align="center" colspan="4">设备基本信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">${sbgl.sbname }</td>
               <td class="width-15 active"><label class="pull-right">设备类别：</label></td>
               <td class="width-35" >${sbgl.sblb }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备编号：</label></td>
               <td class="width-35" >${sbgl.sbbh }</td>
               <td class="width-15 active"><label class="pull-right">设备型号：</label></td>
               <td class="width-35" >${sbgl.sbxh }</td>
            </tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">内容设置：</label></td>
				<td class="width-35" colspan="3">
					<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
						<tr >
							<td style="text-align: center;width: 50%;">保养项目</td>
							<td style="text-align: center;width: 50%;">保养级别</td>
						</tr>
			         	<c:forEach items="${sbgl1 }" var="s">
			         		<tr style="height:30px" >
								<td style="width:37%" align="center">${s.m1 }</td>
								<td style="width:37%" align="center">
									<c:if test="${s.m2 eq '0' }">例行保养</c:if>
									<c:if test="${s.m2 eq '1' }">一级保养</c:if>
									<c:if test="${s.m2 eq '2' }">二级保养</c:if>
								</td>
							</tr>
			         	</c:forEach>
					</table>	
				</td>
			</tr>
			<tr>
               <td class="width-15 active" align="center" colspan="4">设备保养周期（h）</td>
            </tr>
        	<tr>
               <td class="width-15 active"><label class="pull-right">例行保养：</label></td>
               <td class="width-35" >${sbgl.m3 }</td>
               <td class="width-15 active"><label class="pull-right">一级保养：</label></td>
               <td class="width-35" >${sbgl.m4 }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">二级保养：</label></td>
               <td class="width-35" >${sbgl.m5 }</td>
            </tr>
         </tbody>
      </table>
	</form>
</body>
</html>