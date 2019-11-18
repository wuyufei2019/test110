<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
					<tr >  
					<td class="width-20 active" ><label class="pull-right">发生单位：</label></td>
					<td class="width-30" colspan="3">${aie.m2 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">发生日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${aie.m1 }"/></td>
					<td class="width-20 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-30" >${aie.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故类型：</label></td>
					<td class="width-30">${aie.m4}</td>
					
					<td class="width-20 active"><label class="pull-right">事故等级：</label></td>
					<td class="width-30">${aie.m5 }</td>
				</tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-30" >${aie.m6 }</td>
					<td class="width-20 active"><label class="pull-right">重伤人数：</label></td>
					<td class="width-30" >${aie.m7 }</td>
				</tr>
				<tr><td class="width-20 active"><label class="pull-right">轻伤人数：</label></td>
					<td class="width-30" >${aie.m8 }</td></tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">直接经济损失（万元）：</label></td>
					<td class="width-30" >${aie.m9 }</td>
					<td class="width-20 active"><label class="pull-right">间接经济损失（万元）：</label></td>
					<td class="width-30" >${aie.m10 }</td>
				</tr>

                <tr>
					<td class="width-20 active"><label class="pull-right">事故描述：</label></td>
					<td class="width-30" colspan="3">
					${aie.m11 }
					</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故处理：</label></td>
					<td class="width-30" colspan="3">
					${aie.m12 }
					</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故预防对策：</label></td>
					<td class="width-30" colspan="3">
					${aie.m13 }
					</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3">
					${aie.m14 }
					</td>
				</tr>
				
				<!-- <tr>
					<td class="width-20 active"><label class="pull-right">附件（事故调查）：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="filePicker1">选择文件</div>
					</div> 
					</td>
				</tr>
				
					<tr>
					<td class="width-20 active"><label class="pull-right">附件（事故处理）：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr> -->
				<%-- 
				<shiro:hasAnyRoles name="admin,ajcountyadmin">
					<input type="hidden" id="M4" name="M4" value="1" />
				</shiro:hasAnyRoles> --%>
			
				<%-- <shiro:hasAnyRoles name="company,companyadmin">
					<input type="hidden" id="M4" name="M4" value="0" />
				</shiro:hasAnyRoles> --%>
				
			<%-- 	<input type="hidden" id="balb" name="balb" />
				<c:if test="${not empty aie.ID}">
					<input type="hidden" name="ID" value="${aie.ID}" />
					<input type="hidden" id="M1" name="M1" value="${aie.m1}" />
					<input type="hidden" id="ID1" name="ID1" value="${aie.ID1 }" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${aie.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</c:if> --%>
				</tbody>
			</table>

		  	
	</form>
</body>
</html>