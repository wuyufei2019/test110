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
			<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.s1 }" /></span>
			<span class="txt">${sxzy.sqr }申请受限空间安全作业证</span>
		</li>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.m27_1 == null || sxzy.m27_1 == ''}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待分配任务</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m27_1 }" /></span>
					<span class="txt">${sxzy.fpr }分配任务</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || fn:length(sxfxs) == 0}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待分析</span>
					<span class="txt"></span>
				</li>         
		   </c:when>
		   <c:otherwise> 
		   		<c:forEach items="${sxfxs}" var="sxfx" varStatus="vs"> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxfx.m4 }" /></span>
						<span class="txt">${sxfx.czr }分析数据</span>
					</li>
				</c:forEach>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.m15_1 == null || sxzy.m15_1 == ''}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待编制安全措施</span>
					<span class="txt"></span>
				</li>         
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m15_1 }" /></span>
					<span class="txt">${sxzy.bzcsr }编制安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.zt eq '3' || sxzy.m16_1 == null || sxzy.m16_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待确认安全措施</span>
					<span class="txt"></span>
				</li>        
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m16_1 }" /></span>
					<span class="txt">${sxzy.qrcsr }确认安全措施</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.zt eq '3' || sxzy.zt eq '4' || sxzy.m13_1 == null || sxzy.m13_1 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待实施安全教育</span>
					<span class="txt"></span>
				</li>        
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m13_1 }" /></span>
					<span class="txt">${sxzy.ssjyr }实施安全教育</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.zt eq '3' || sxzy.zt eq '4' || sxzy.zt eq '5' || sxzy.m22 == null || sxzy.m22 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待申请单位确认</span>
					<span class="txt"></span>
				</li>     
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m22 }" /></span>
					<span class="txt">申请单位负责人（${sxzy.dwfzr }）确认</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.zt eq '3' || sxzy.zt eq '4' || sxzy.zt eq '5' || sxzy.zt eq '6' || sxzy.m24 == null || sxzy.m24 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待审批</span>
					<span class="txt"></span>
				</li>  
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m24 }" /></span>
					<span class="txt">${sxzy.spr }进行审批</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '0' || sxzy.zt eq '1' || sxzy.zt eq '2' || sxzy.zt eq '3' || sxzy.zt eq '4' || sxzy.zt eq '5' || sxzy.zt eq '6' || sxzy.zt eq '7' || sxzy.m25 == null || sxzy.m25 == ''}">  
			   	<li>
					<i class="node-icon"></i>
					<span class="time">等待验收</span>
					<span class="txt"></span>
				</li>
		   </c:when>
		   <c:otherwise> 
				<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m25 }" /></span>
					<span class="txt">${sxzy.ysr }进行验收</span>
				</li>
		   </c:otherwise>
		</c:choose>
		
		<c:choose>
		   <c:when test="${sxzy.zt eq '9' }">  
			   	<li class="last">
					<i class="node-icon"></i>
					<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m28_1 }" /></span>
					<span class="txt">${sxzy.gbr }关闭流程</span>
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