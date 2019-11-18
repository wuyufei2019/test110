<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30">${dhzy.bzr }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${dhzy.m1 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" >${dhzy.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30" >${dhzy.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">易燃易爆场所：</label></td>
					<td class="width-30" colspan="3">
						<c:if test="${dhzy.m4_1 != null && dhzy.m4_1 != '' }">
							<input type="radio" value="0" class="i-checks"  name="csflag" checked="checked" />涉及
							<input type="radio" value="1" class="i-checks"  name="csflag" />不涉及
						</c:if>
						<c:if test="${dhzy.m4_1 == null || dhzy.m4_1 == '' }">
							<input type="radio" value="0" class="i-checks" name="csflag" />涉及
							<input type="radio" value="1" class="i-checks" name="csflag"  checked="checked"/>不涉及
						</c:if>
					</td>
				</tr>

				<c:if test="${dhzy.m4_1 != null }">
					<tr style="display:none" id="tsqk">
						<td class="width-20 active"><label class="pull-right">特殊情况：</label></td>
						<td class="width30" colspan="3">
								${dhzy.m4_1 }
						</td>
					</tr>
				</c:if>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">动火作业级别：</label></td>
					<td class="width-30">${dhzy.m4 }</td>
					<td class="width-20 active"><label class="pull-right">动火地点：</label></td>
					<td class="width-30">${dhzy.m5 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火方式：</label></td>
					<td class="width-30" colspan="3">${dhzy.m6 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dhzy.m7 }"/></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dhzy.m8 }"/></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">动火作业负责人：</label></td>
					<td class="width-30">${dhzy.m9 }</td>
					<td class="width-20 active"><label class="pull-right">动火人：</label></td>
					<td class="width-30">${dhzy.m10 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监火人：</label></td>
					<td class="width-30" >${dhzy.m11 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${dhzy.m12 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${dhzy.m13 }</td>
				</tr>
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析</strong></p><hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;" />
			<table id="fxsj"></table>
			
			<!-- 安全措施 -->
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30" colspan="3">${dhzy.bzr }</td>
				</tr>  
				</tbody>
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m23_2}">
						 <c:forTokens items="${dhzy.m23_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m23_7 }" />
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m23_3}">
						 <c:forTokens items="${dhzy.m23_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m23_7 }" />
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m23_4}">
					 <c:forTokens items="${dhzy.m23_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m23_5}">
					 <c:forTokens items="${dhzy.m23_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty dhzy.m23_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${dhzy.m14 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m14_1}">
						 <c:forTokens items="${dhzy.m14_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>			
					 </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m14_2 }" />
					</td>
				</tr>  
			</table>

 			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">安技员意见：</label></td>
					<td class="width-30" >${dhzy.m15 }</td>
					<td class="width-20 active"><label class="pull-right">安技员：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dhzy.m15_1}">
						 <c:forTokens items="${dhzy.m15_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>	
					  </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m15_2 }" />
					</td>
				</tr>
				
				<c:if test="${dhzy.m4 != '特殊动火' }">
					<c:if test="${dhzy.m4 == '二级动火' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">分厂安全分管领导意见：</label></td>
							<td class="width-30" >${dhzy.m16 }</td>
							<td class="width-20 active"><label class="pull-right">分厂安全分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m16_1}">
								 <c:forTokens items="${dhzy.m16_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m16_2 }" />
							</td>
						</tr>
					</c:if>
					<c:if test="${dhzy.m4 == '一级动火' }">
<%-- 					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管人员意见：</label></td>
							<td class="width-30" >${dhzy.m17 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管人员：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m17_1}">
								 <c:forTokens items="${dhzy.m17_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m17_2 }" />
							</td>
						</tr> --%>
						<tr>
							<td class="width-20 active"><label class="pull-right">部门一把手意见：</label></td>
							<td class="width-30" >${dhzy.m20 }</td>
							<td class="width-20 active"><label class="pull-right">部门一把手：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m20_1}">
								 <c:forTokens items="${dhzy.m20_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m20_2 }" />
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">安全处分管领导意见：</label></td>
							<td class="width-30" >${dhzy.m18 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m18_1}">
								 <c:forTokens items="${dhzy.m18_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m18_2 }" />
							</td>
						</tr>	
					    <tr>
							<td class="width-20 active"><label class="pull-right">保卫部意见：</label></td>
							<td class="width-30" >${dhzy.m19 }</td>
							<td class="width-20 active"><label class="pull-right">保卫部：</label></td>
							<td class="width-30" >
							 	<c:if test="${not empty dhzy.m19_1}">
									 <c:forTokens items="${dhzy.m19_1}" delims="," var="url" varStatus="urls">
										<c:set var="urlna" value="${fn:split(url, '||')}" />
										<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
											<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
										</div><br/>
									 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m19_2 }" />
							</td>
						</tr>
						<tr>
							<td class="width-20 active"><label class="pull-right">能源防控中心意见：</label></td>
							<td class="width-30" >${dhzy.m22 }</td>
							<td class="width-20 active"><label class="pull-right">能源防控中心：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m22_1}">
								 <c:forTokens items="${dhzy.m22_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m22_2 }" />
							</td>
						</tr>
					</c:if>
					
				</c:if>

				<c:if test="${dhzy.m4 == '特殊动火' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">部门一把手意见：</label></td>
							<td class="width-30" >${dhzy.m20 }</td>
							<td class="width-20 active"><label class="pull-right">部门一把手：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m20_1}">
								 <c:forTokens items="${dhzy.m20_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m20_2 }" />
							</td>
						</tr>				
					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管领导意见：</label></td>
							<td class="width-30" >${dhzy.m18 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m18_1}">
								 <c:forTokens items="${dhzy.m18_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m18_2 }" />
							</td>
						</tr>
					    <tr>
							<td class="width-20 active"><label class="pull-right">保卫部意见：</label></td>
							<td class="width-30" >${dhzy.m19 }</td>
							<td class="width-20 active"><label class="pull-right">保卫部：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m19_1}">
								 <c:forTokens items="${dhzy.m19_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m19_2 }" />
							</td>
						</tr>							
					    <tr>
							<td class="width-20 active"><label class="pull-right">公司分管领导意见：</label></td>
							<td class="width-30" >${dhzy.m21 }</td>
							<td class="width-20 active"><label class="pull-right">公司分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m21_1}">
								 <c:forTokens items="${dhzy.m21_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m21_2 }" />
							</td>
						</tr>		
					    <tr>
							<td class="width-20 active"><label class="pull-right">能源防控中心意见：</label></td>
							<td class="width-30" >${dhzy.m22 }</td>
							<td class="width-20 active"><label class="pull-right">能源防控中心：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty dhzy.m22_1}">
								 <c:forTokens items="${dhzy.m22_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m22_2 }" />
							</td>
						</tr>															
				</c:if>	
							
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty dhzy.m24_1}">
							 <c:forTokens items="${dhzy.m24_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m24 }" />
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty dhzy.m25_1}">
							 <c:forTokens items="${dhzy.m25_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m25 }" />
					</td>					
				</tr>
			
				</tbody>
			</table>
       </form>
<script type="text/javascript">
//特殊作业
var tszy = '${dhzy.m10}';
var tszyArr = tszy.split(",");
for(var i=0;i<tszyArr.length;i++){
	$("input[name='M10']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
}

//危害辨识
var whbs = '${dhzy.m11}';
var whbsArr = whbs.split(",");
for(var i=0;i<whbsArr.length;i++){
	$("input[name='M11']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
}

$(function(){
	var dhzyid='${dhzy.id}';
	var qrcsr='${dhzy.qrcsr}';
	var zt='${dhzy.zt}';
	dg=$('#fxsj').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/dhzy/dhzyfxlist',
	    queryParams:{'dhzyid':dhzyid},
	    fit : false,
		fitColumns : true,
		border : true,
		idField : 'id',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		striped:true,
	    columns:[[    
    			{field : 'm1',title : '分析点名称',sortable : false,width : 80,align : 'center'},
				{field : 'm2',title : '介质',sortable : false,width : 40,align : 'center'},
				{field : 'm2_1',title : '数值',sortable : false,width : 40,align : 'center'},
				{field : 'm2_2',title : '单位',sortable : false,width : 40,align : 'center'},
	        	{field : 'm3',title : '分析时间',sortable : false,width : 60,align : 'center',
					formatter : function(value, row, index) {
		              	if(value!=null&&value!='') {
		              		var datetime=new Date(value);
		              		return datetime.format('yyyy-MM-dd hh:mm:ss');
		              	}
		            }
	         	},
				{field : 'm4',title : '分析人',sortable : false,width : 40,align : 'center'},
				{field : 'm5',title : '现场照片',sortable : false,width : 60,align : 'center',
		        	formatter : function(value) {
		        		if(value){
		        			var urls=value.split(",");
		        			var html="";
		        			for(var index in urls){
		        				html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>"; 
		        			}
		        			return html;
		        		}
		        		else return ; 
		        	}						
				},
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/dhzy/aqcslist?type=0'+'&id1='+dhzyid,
	    fit : false,
		fitColumns : true,
		border : true,
		idField : 'id',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		striped:true,
	    columns:[[    
              	{field:'m1',title:'安全措施',width:100},
              	{field:'id2',title : '执行情况',sortable : false,width : 20,align : 'center',
					formatter : function(value, row, index) {
		              	if(value==null){
		              		return "";
		              	}else{
		              		return row.m3;
		              	}
		            }
	         	},
				{field : 'm4',title : '现场照片',sortable : false,width : 60,align : 'center',
		        	formatter : function(value) {
		        		if(value){
		        			var urls=value.split(",");
		        			var html="";
		        			for(var index in urls){
		        				html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>"; 
		        			}
		        			return html;
		        		}
		        		else return ; 
		        	}						
				},	         	
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	if('${dhzy.m23_6}'!=null&&'${dhzy.m23_6}'!=''){
		var $list= $("#wlf");
		var wlflist= '${wlflist}';
		wlflist2=JSON.parse(wlflist);  
	    $.each(wlflist2, function(idx, obj) {
	        var $list= $("#wlf");
			var $li = $(
				'<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onClick="viewwlf('+obj.id+')">'+
				obj.wlfname+'</a>'
	            );
			$list.append( $li );
	    });			
	}
});

//查看
function viewwlf(id){
	openDialogView("查看外来方单位信息",ctx+"/ztzyaqgl/xgdw/view/"+id,"90%", "90%","");
}

</script>
</body>
</html>