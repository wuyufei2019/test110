<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加复检信息</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqjd/jcjl/${action}"  method="post" class="form-horizontal" >
		
		<div class="easyui-accordion"  style="width:100%;height：100%">
	
		<table   class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr >
					<td class="width-15 active" ><label class="pull-right">专项检查名称：</label></td>
					<td class="width-35"  >${cre.mc }
						</td>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35">${cre.m2 }</td>	
				</tr>
		</table>
		
			<div title="初检记录" iconCls="icon-add" style="padding:10px;"  data-options="collapsed:false"  >
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">检查人员姓名：</label></td>
					<td class="width-35">${cre.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患描述：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${cre.m4 }
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">整改负责人姓名：</label></td>
					<td class="width-35">${cre.m5 }</td>
					<td class="width-15 active"><label class="pull-right">整改期限至：</label></td>
					<td class="width-35">${cre.m6 }</td>
				</tr>

				<tr>
					<td class="width-15 active" style="width:6.5%"><label class="pull-right">隐患照片：</label></td>
					<td class="width-35" colspan="3">
						 <c:if test="${not empty cre.m7}">
					 <c:forTokens items="${cre.m7}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/><a target="_blank" href="${urlna[0]}">${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right">检查表附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cre.m8}">
					 <c:forTokens items="${cre.m8}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				</table>
			</div>

			<div title="复检记录" iconCls="icon-add" style="padding:10px;" selected="true" data-options="collapsed:false" >
		
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				
				<tr>
					<td class="width-15 active"><label class="pull-right">复查人员姓名：</label></td>
					<td class="width-35">${cre.m9 }</td>
					<td class="width-15 active"><label class="pull-right">复查时间：</label></td>
					<td class="width-35">${cre.m10 }</td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">复查意见：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${cre.m11 }
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">复查照片附件：</label></td>
					<td class="width-85" colspan="3">
					<c:if test="${not empty cre.m12}">
					 <c:forTokens items="${cre.m12}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/><a target="_blank" href="${urlna[0]}">${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				 <tr>
					<td class="width-15 active" ><label class="pull-right">复查检查表附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cre.m13}">
					 <c:forTokens items="${cre.m13}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if> 
					</td>
				</tr>
				
			</table>
			</div>			
			</div>
       </form>

<script type="text/javascript">
	
</script>
</body>
</html>