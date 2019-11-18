<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看受限空间作业证状态</title>
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
			<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.s1 }" /></span>
			<span class="txt">${dhzy.sqr }申请动火作业安全作业证</span>
		</li>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.m27_1 == null || dhzy.m27_1 == ''}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待分配任务</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m27_1 }" /></span>
					<span class="txt">${dhzy.fpr }分配任务</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || fn:length(dhfxs) == 0}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待分析</span>
					<span class="txt"></span>
				</li>         
		   </c:when>
		   <c:otherwise> 
		   		<c:forEach items="${dhfxs}" var="dhfx" varStatus="vs"> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhfx.m3 }" /></span>
						<span class="txt">${dhfx.czr }分析数据</span>
					</li>
				</c:forEach>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.m15_1 == null || dhzy.m15_1 == ''}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待编制安全措施</span>
					<span class="txt"></span>
				</li>         
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m15_1 }" /></span>
					<span class="txt">${dhzy.bzcsr }编制安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.m16_1 == null || dhzy.m16_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待确认安全措施</span>
					<span class="txt"></span>
				</li>        
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m16_1 }" /></span>
					<span class="txt">${dhzy.qrcsr }确认安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.m13_1 == null || dhzy.m13_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待生产单位确认</span>
					<span class="txt"></span>
				</li>        
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m13_1 }" /></span>
					<span class="txt">生产部门负责人（${dhzy.scdwr }）确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.m18_1 == null || dhzy.m18_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待监火人确认</span>
					<span class="txt"></span>
				</li>     
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m18_1 }" /></span>
					<span class="txt">监护人（${dhzy.jhr }）确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.m20_1 == null || dhzy.m20_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待动火初审人确认</span>
					<span class="txt"></span>
				</li>  
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m20_1 }" /></span>
					<span class="txt">作业区域负责人（${dhzy.csr }）确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.zt eq '7' || dhzy.m21_1 == null || dhzy.m21_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待实施安全教育</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m21_1 }" /></span>
					<span class="txt">${dhzy.aqjyr } 已实施 安全教育</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.zt eq '7' || dhzy.zt eq '8' || dhzy.m22_2 == null || dhzy.m22_2 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待申请单位确认</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m22_2 }" /></span>
					<span class="txt">申请单位负责人（${dhzy.sqdwr }）已确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.zt eq '7' || dhzy.zt eq '8' || dhzy.zt eq '9' ||dhzy.m23_2 == null || dhzy.m23_2 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待安全部门确认</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m23_2 }" /></span>
					<span class="txt">安全部门负责人（${dhzy.aqbmr }）已确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.zt eq '7' || dhzy.zt eq '8' || dhzy.zt eq '9' || dhzy.zt eq '10' || dhzy.m24_2 == null || dhzy.m24_2 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待动火审批人审批</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m24_2 }" /></span>
					<span class="txt">动火审批人（${dhzy.spr }）已审批</span>
				</li>
		   </c:otherwise>
		</c:choose>

		<c:choose>
		   <c:when test="${dhzy.zt eq '0' || dhzy.zt eq '1' || dhzy.zt eq '2' || dhzy.zt eq '3' || dhzy.zt eq '4' || dhzy.zt eq '5' || dhzy.zt eq '6' || dhzy.zt eq '7' || dhzy.zt eq '8' || dhzy.zt eq '9' ||dhzy.zt eq '10' || dhzy.zt eq '11' || dhzy.m25_1 == null || dhzy.m25_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待验收</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m25_1 }" /></span>
					<span class="txt">${dhzy.ysr } 已验收</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${dhzy.zt eq '13' }">  
			   	<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dhzy.m28_1 }" /></span>
					<span class="txt">${dhzy.gbr } 关闭流程</span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li>
					<i class="node-icon"></i>
					<span class="time">等待关闭</span>
					<span class="txt"></span>
				</li>
		   </c:otherwise>
		</c:choose>
	</ul>
</div>
</body>
</html>