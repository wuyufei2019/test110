<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看动土作业证状态</title>
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
			<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.s1 }" /></span>
			<span class="txt">申请动土作业安全证,作业证编号:${dtzy.m1 }</span>
		</li>

		<c:choose>
		   <c:when test="${dtzy.zt<1 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待编制</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m19 }" /></span>
					<span class="txt">已编制</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dtzy.zt<2 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待作业单位签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m13_2 }" /></span>
					<span class="txt">作业单位已签字</span>
				</li>
		   </c:otherwise>
		</c:choose>	
		
		<c:if test="${fn:contains(dtzy.process, '2') }">
			<c:choose>
			   <c:when test="${dtzy.zt<3 }">  
				    <li>
						<i class="node-icon"></i>
						<span class="time">待能控中心审批</span>
						<span class="txt"></span>
					</li>           
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m14_2 }" /></span>
						<span class="txt">能控中心已审批</span>
					</li>
			   </c:otherwise>
			</c:choose>			
		</c:if>
		
		<c:if test="${fn:contains(dtzy.process, '3') }">
			<c:choose>
			   <c:when test="${dtzy.zt<4 }">  
				    <li>
						<i class="node-icon"></i>
						<span class="time">待分厂审批</span>
						<span class="txt"></span>
					</li>           
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m15_2 }" /></span>
						<span class="txt">分厂已审批</span>
					</li>
			   </c:otherwise>
			</c:choose>
		</c:if>
		
		<c:if test="${fn:contains(dtzy.process, '4') }">
			<c:choose>
			   <c:when test="${dtzy.zt<5 }">  
				    <li>
						<i class="node-icon"></i>
						<span class="time">待设备处审批</span>
						<span class="txt"></span>
					</li>           
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m16_2 }" /></span>
						<span class="txt">设备处已审批</span>
					</li>
			   </c:otherwise>
			</c:choose>		
		</c:if>
		
		<c:if test="${fn:contains(dtzy.process, '5') }">
			<c:choose>
			   <c:when test="${dtzy.zt<6 }">  
				    <li>
						<i class="node-icon"></i>
						<span class="time">待保卫处审批</span>
						<span class="txt"></span>
					</li>           
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m17_2 }" /></span>
						<span class="txt">保卫处已审批</span>
					</li>
			   </c:otherwise>
			</c:choose>	
		</c:if>
		
		<c:if test="${fn:contains(dtzy.process, '6') }">
			<c:choose>
			   <c:when test="${dtzy.zt<7 }">  
				    <li>
						<i class="node-icon"></i>
						<span class="time">待安全处审批</span>
						<span class="txt"></span>
					</li>           
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m18_2 }" /></span>
						<span class="txt">安全处已审批</span>
					</li>
			   </c:otherwise>
			</c:choose>	
		</c:if>

		<c:choose>
		   <c:when test="${dtzy.zt<8 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待现场确认安全措施</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m19_7 }" /></span>
					<span class="txt">已现场确认安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>	
				
		
		<c:choose>
		   <c:when test="${dtzy.zt<9 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待作业单位完工签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m20 }" /></span>
					<span class="txt">作业单位完工已签字</span>
				</li>
		   </c:otherwise>
		</c:choose>	
		
		<c:choose>
		   <c:when test="${dtzy.zt<10 }">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">待分厂完工签字</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dtzy.m21 }" /></span>
					<span class="txt">分厂完工已签字</span>
				</li>
		   </c:otherwise>
		</c:choose>															
	</ul>
</div>
</body>
</html>