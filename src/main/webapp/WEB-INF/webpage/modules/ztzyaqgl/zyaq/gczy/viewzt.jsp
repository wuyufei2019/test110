<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看高处作业证状态</title>
	<meta name="decorator" content="default"/>
	<style>
		.hovertree-tracklist ul li {
		list-style: none;
		}
		
		.hovertree-trackrcol {
		max-width: 900px;
		border: 1px solid #eee;
		}
		
		.hovertree-tracklist {
		margin: 20px;
		padding-left: 5px;
		position: relative;
		}
		
		.hovertree-tracklist li {
		position: relative;
		padding: 9px 0 0 25px;
		line-height: 18px;
		border-left: 1px solid #d9d9d9;
		color: #999;
		}
		
		.hovertree-tracklist li .node-icon {
		position: absolute;
		left: -6px;
		top: 60%;
		width: 11px;
		height: 11px;
		background: url(${ctx}/static/model/images/order-icons.png) -21px -72px no-repeat;
		}
		
		.hovertree-tracklist li.first {
		color: red;
		border-left-color: #fff;
		}
		
		.hovertree-tracklist li.first .node-icon {
		background-position: 0 -72px;
		}
		
		.hovertree-tracklist li.last {
		color: red;
		}
		
		.hovertree-tracklist li.last .node-icon {
		background-position: 0 -72px;
		}
		
		.hovertree-tracklist li .time {
		margin-right: 20px;
		position: relative;
		top: 4px;
		display: inline-block;
		vertical-align: middle;
		}
		
		.hovertree-tracklist li .txt {
		max-width: 600px;
		position: relative;
		top: 4px;
		display: inline-block;
		vertical-align: middle;
		}
		
		.hovertree-tracklist li.first .time {
		margin-right: 20px;
		}
		
		.hovertree-tracklist li.first .txt {
		max-width: 600px;
		}
		.hovertreeinfo{margin-top:10px;}
		.hovertreeinfo a{color:blue;}
	</style>
</head>
<body>
<div class="hovertree-tracklist">
	<ul>
		<li class="first">
			<i class="node-icon"></i>
			<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.s1 }" /></span>
			<span class="txt">申请高处作业安全证,作业证编号:${gczy.m1 }</span>
		</li>

		<c:choose>
		   <c:when test="${gczy.zt<1 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待编制</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m21 }" /></span>
					<span class="txt">已编制</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${gczy.zt<2 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待作业单位签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m16_2 }" /></span>
					<span class="txt">作业单位已签字</span>
				</li>
		   </c:otherwise>
		</c:choose>	

		<c:choose>
		   <c:when test="${gczy.zt<3 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待安技员审批</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m17_2 }" /></span>
					<span class="txt">安技员已审批</span>
				</li>
		   </c:otherwise>
		</c:choose>			

		<c:if test="${gczy.m10=='四级' }">		
				<c:choose>
				   <c:when test="${gczy.zt<4 }">  
					    <li>
							<i class="node-icon"></i>
							<span class="time">待部门一把手审批</span>
							<span class="txt"></span>
						</li>           
				   </c:when>
				   <c:otherwise> 
						<li class="last">
							<i class="node-icon"></i>
							<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m24_2 }" /></span>
							<span class="txt">部门一把手已审批</span>
						</li>
				   </c:otherwise>
				</c:choose>	
				
				<c:choose>
				   <c:when test="${gczy.zt<7 }">  
					    <li>
							<i class="node-icon"></i>
							<span class="time">待安全处分管领导审批</span>
							<span class="txt"></span>
						</li>           
				   </c:when>
				   <c:otherwise> 
						<li class="last">
							<i class="node-icon"></i>
							<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m20_2 }" /></span>
							<span class="txt">安全处分管领导已审批</span>
						</li>
				   </c:otherwise>
				</c:choose>		
				
				<c:choose>
				   <c:when test="${gczy.zt<8 }">  
					    <li>
							<i class="node-icon"></i>
							<span class="time">待公司分管领导审批</span>
							<span class="txt"></span>
						</li>           
				   </c:when>
				   <c:otherwise> 
						<li class="last">
							<i class="node-icon"></i>
							<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m25_2 }" /></span>
							<span class="txt">公司分管领导已审批</span>
						</li>
				   </c:otherwise>
				</c:choose>								
		</c:if>
		
		<c:if test="${gczy.m10!='四级' }">		
				<c:choose>
				   <c:when test="${gczy.zt<5 }">  
					    <li>
							<i class="node-icon"></i>
							<span class="time">待分厂安全分管领导审批</span>
							<span class="txt"></span>
						</li>           
				   </c:when>
				   <c:otherwise> 
						<li class="last">
							<i class="node-icon"></i>
							<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m18_2 }" /></span>
							<span class="txt">分厂安全分管领导已审批</span>
						</li>
				   </c:otherwise>
				</c:choose>	
				
				<c:if test="${gczy.m10=='二级' }">
					<c:choose>
					   <c:when test="${gczy.zt<6 }">  
						    <li>
								<i class="node-icon"></i>
								<span class="time">待安全处分管人员审批</span>
								<span class="txt"></span>
							</li>           
					   </c:when>
					   <c:otherwise> 
							<li class="last">
								<i class="node-icon"></i>
								<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m19_2 }" /></span>
								<span class="txt">安全处分管人员已审批</span>
							</li>
					   </c:otherwise>
					</c:choose>		
				</c:if>

				<c:if test="${gczy.m10=='三级' }">
					<c:choose>
					   <c:when test="${gczy.zt<7 }">  
						    <li>
								<i class="node-icon"></i>
								<span class="time">待安全处分管领导审批</span>
								<span class="txt"></span>
							</li>           
					   </c:when>
					   <c:otherwise> 
							<li class="last">
								<i class="node-icon"></i>
								<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m20_2 }" /></span>
								<span class="txt">安全处分管领导已审批</span>
							</li>
					   </c:otherwise>
					</c:choose>		
				</c:if>
		</c:if>	
		
		<c:choose>
		   <c:when test="${gczy.zt<9 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待现场确认安全措施</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m21_7 }" /></span>
					<span class="txt">已现场确认安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>	
		
		<c:choose>
		   <c:when test="${gczy.zt<10 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待作业单位完工签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m22 }" /></span>
					<span class="txt">作业单位已完工签字</span>
				</li>
		   </c:otherwise>
		</c:choose>	
		
		<c:choose>
		   <c:when test="${gczy.zt<11 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待分厂完工签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m23 }" /></span>
					<span class="txt">分厂完工已签字</span>
				</li>
		   </c:otherwise>
		</c:choose>														
	</ul>
</div>
</body>
</html>