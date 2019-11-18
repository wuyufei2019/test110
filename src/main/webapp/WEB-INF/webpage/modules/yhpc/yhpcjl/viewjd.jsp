<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看隐患排查进度</title>
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
			<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${yhpcjl.createtime }" /></span>
			<span class="txt">${yhpcjl.jcr }发现隐患</span>
		</li>
		
		<c:forEach items="${zglist}" var="zg">
			<c:choose>
			   <c:when test="${zg.handletype eq '1'}">  
				    <li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${zg.handletime }" /></span>
						<span class="txt">${zg.zgr }进行整改</span>
					</li>       
			   </c:when>
			   <c:otherwise> 
					<li class="last">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${zg.handletime }" /></span>
						<span class="txt">${zg.zgr }进行复查，
							<c:if test="${zg.handlestatus eq '1' }">
							复查不通过
							</c:if>
							<c:if test="${zg.handlestatus eq '2' }">
							复查通过
							</c:if>
						</span>
					</li>
			   </c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
		   <c:when test="${yhpcjl.dangerstatus eq '0' || yhpcjl.dangerstatus eq '2'}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待${yhpcjl.zdzgr}整改</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:when test="${yhpcjl.dangerstatus eq '1'}">  
			    <li>
					<i class="node-icon"></i>
					<span class="time">等待${yhpcjl.jcr}复查</span>
					<span class="txt"></span>
				</li>           
		   </c:when>
		   <c:otherwise> 
		   </c:otherwise>
		</c:choose>
	</ul>
</div>
</body>
</html>