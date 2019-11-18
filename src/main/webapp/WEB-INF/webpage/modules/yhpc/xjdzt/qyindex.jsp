<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检点状态</title>
	<meta name="decorator" content="default"/>
   <link href="${ctxStatic}/model/css/yhpc/style.css" rel="stylesheet" />
   
	<style type="text/css">
.f_blue{color: #00568e;font-weight: bold;}
.f_green{color: #55a63b;}
.f_red{color: #ce4a4a;}
.f_gray{}
.box1{
	overflow: hidden;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 2px 2px 5px #dedede;
	margin-bottom: 30px;
	}
.box1 div.title{
	text-indent: 1em;
	margin: 0 20px;
	border-bottom: 1px dashed #ddd;
	}
.box1 h5{margin: 0; line-height: 45px;}
.count{overflow: hidden; width: 400px; margin:20px auto;}
.count img{height: 20px; margin:0;padding:0; border:0; float: left;}
.box1 .fxdbc{ margin: 10px 0; padding-bottom:10px;cursor: pointer;}
.box1 .fxdbcred{border:5px solid #ce4a4a; border-radius: 5px;} 
.box1 .fxdbc .fxdred{background:url(${ctxStatic}/model/images/yhpc/fxdred.png) top center no-repeat; height:50px; background-size: contain;text-align: center;}
.box1 .fxdbcred .fxdname{color: #ce4a4a; }
.fxdbc img{height:30px; margin-top: 10px;}
.box1 .fxdbc .fxdname{
	line-height: 20px;
	height:40px;
	overflow:hidden;
	text-align: center;
	margin: 5px 10px;
	font-weight: bold;
	display: block;
	cursor: pointer;
	text-decoration: none;}

.box1 .fxdbc .fxdperson{
	text-align: center;
	padding: 5px;
	border-bottom: 1px dashed #aaa;
	margin-bottom: 10px;
}
.box1 .fxdbc .fxbc{
	text-align: center;
	line-height: 28px;
	height: 112px;
	overflow-y: auto;
	width: 100%;}
.box1 .fxdbcorange{border:5px solid #fb941a; border-radius: 5px; margin-top: 10px;} 
.box1 .fxdbc .fxdorange{background:url(${ctxStatic}/model/images/yhpc/fxdorange.png) top center no-repeat; height:50px; background-size: contain;text-align: center;}
.box1 .fxdbcorange .fxdname{color: #fb941a;}

.box1 .fxdbcyellow{border:5px solid #fbe70c; border-radius: 5px; margin-top: 10px;} 
.box1 .fxdbc .fxdyellow{background:url(${ctxStatic}/model/images/yhpc/fxdyellow.png) top center no-repeat; height:50px; background-size: contain;text-align: center;}
.box1 .fxdbcyellow .fxdname{color: #fbe70c;}

.box1 .fxdbcblue{border:5px solid #02abf9; border-radius: 5px; margin-top: 10px;} 
.box1 .fxdbc .fxdblue{background:url(${ctxStatic}/model/images/yhpc/fxdblue.png) top center no-repeat; height:50px; background-size: contain;text-align: center;}
.box1 .fxdbcblue .fxdname{color: #02abf9;}

.box1 .fxdbczc{border:5px solid #9d9d9d; border-radius: 5px; margin-top: 10px;} 
.box1 .fxdbc .fxdzc{background:url(${ctxStatic}/model/images/yhpc/fxdzc.png) top center no-repeat; height:50px; background-size: contain;text-align: center;}
.box1 .fxdbczc .fxdname{color: #9d9d9d;}
	</style>
</head>
<body class="bggrey">
 
<div style="width: 100%;text-align: center;margin:1px auto"><span id="titletext" style="color: red;font-size: 28px;"></span></div> 
   <div class="box">
      <div class="count" style="text-align: center">
      <br>
         <span class="fl">共有${tj[5][3]}个检查点 &nbsp;</span> <img src="${ctxStatic}/model/images/yhpc/zc.gif"> <span class="fl">&nbsp;正常：</span> <span class="fl f_blue">${tj[5][0]} &nbsp;</span> <img
            src="${ctxStatic}/model/images/yhpc/yc.gif"> <span class="fl">&nbsp;异常：</span> <span class="fl f_blue">${tj[5][1]} &nbsp;</span> <img src="${ctxStatic}/model/images/yhpc/wj.gif">
         <span class="fl">&nbsp;未巡检：</span> <span class="fl f_blue">${tj[5][2]}</span>
      </div>
   </div>
   <c:if test="${fn:length(listr)>0}">
      <div class="box1">
         <div class="title">
            <h5>红色等级风险点:正常：${tj[0][0]} &nbsp; 异常：${tj[0][1]} &nbsp; 未巡检：${tj[0][2]} &nbsp;</h5>
         </div>
         <c:forEach items="${listr}" var="item">
	           <div class="col-lg-3 col-sm-4" >
				<div class="fxdbc fxdbcred" onclick="infor('${item.jcdid}','${item.jcdtype}')">
					<div class="fxdred">
						<c:choose>
                           <c:when test="${item.checkresult==0}">
                              <img src="${ctxStatic}/model/images/yhpc/zc.gif">
                           </c:when>
                           <c:when test="${item.checkresult==1}">
                              <img src="${ctxStatic}/model/images/yhpc/yc.gif">
                           </c:when>
                           <c:otherwise>
                              <img src="${ctxStatic}/model/images/yhpc/wj.gif">
                           </c:otherwise>
                        </c:choose>
					</div>
					<span class="fxdname">
						${item.jcdname}
					</span>
					<div class="fxdperson">
						<div><dd>最近检查人：${item.uname}</dd></div>
						<div><dd>最近检查时间：<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					<div class="fxbc">
						<c:if test="${fn:length(item.bclist)>0}">
							<c:forEach items="${item.bclist}" var="bc">
								<c:choose>
									<c:when test="${bc.checkresult==0}">
										<div>${bc.bcname}<span class="f_green">(无问题)</span></div>
									</c:when>
									<c:when test="${bc.checkresult==1}">
										<div>${bc.bcname}<span class="f_red">(有问题)</span></div>
									</c:when>
									<c:otherwise>
			                            <div>${bc.bcname}<span class="f_gray">(未巡检)</span></div>
			                        </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
			  </div>
         </c:forEach>
      </div>
   </c:if>

   <c:if test="${fn:length(listo)>0}">
      <div class="box1">
         <div class="title">
            <h5>橙色等级风险点:正常：${tj[1][0]} &nbsp; 异常：${tj[1][1]} &nbsp; 未巡检：${tj[1][2]} &nbsp;</h5>
         </div>
         <c:forEach items="${listo}" var="item">
               <div class="col-lg-3 col-sm-4" >
				<div class="fxdbc fxdbcorange" onclick="infor('${item.jcdid}','${item.jcdtype}')">
					<div class="fxdorange">
						<c:choose>
                           <c:when test="${item.checkresult==0}">
                              <img src="${ctxStatic}/model/images/yhpc/zc.gif">
                           </c:when>
                           <c:when test="${item.checkresult==1}">
                              <img src="${ctxStatic}/model/images/yhpc/yc.gif">
                           </c:when>
                           <c:otherwise>
                              <img src="${ctxStatic}/model/images/yhpc/wj.gif">
                           </c:otherwise>
                        </c:choose>
					</div>
					<span class="fxdname">
						${item.jcdname}
					</span>
					<div class="fxdperson">
						<div><dd>最近检查人：${item.uname}</dd></div>
						<div><dd>最近检查时间：<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					<div class="fxbc">
						<c:if test="${fn:length(item.bclist)>0}">
							<c:forEach items="${item.bclist}" var="bc">
								<c:choose>
									<c:when test="${bc.checkresult==0}">
										<div>${bc.bcname}<span class="f_green">(无问题)</span></div>
									</c:when>
									<c:when test="${bc.checkresult==1}">
										<div>${bc.bcname}<span class="f_red">(有问题)</span></div>
									</c:when>
									<c:otherwise>
			                            <div>${bc.bcname}<span class="f_gray">(未巡检)</span></div>
			                        </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
			  </div>
         </c:forEach>
      </div>
   </c:if>

   <c:if test="${fn:length(listy)>0}">
      <div class="box1">
         <div class="title">
            <h5>黄色等级风险点:正常：${tj[2][0]} &nbsp; 异常：${tj[2][1]} &nbsp; 未巡检：${tj[2][2]} &nbsp;</h5>
         </div>
         <c:forEach items="${listy}" var="item">
               <div class="col-lg-3 col-sm-4" >
				<div class="fxdbc fxdbcyellow" onclick="infor('${item.jcdid}','${item.jcdtype}')">
					<div class="fxdyellow">
						<c:choose>
                           <c:when test="${item.checkresult==0}">
                              <img src="${ctxStatic}/model/images/yhpc/zc.gif">
                           </c:when>
                           <c:when test="${item.checkresult==1}">
                              <img src="${ctxStatic}/model/images/yhpc/yc.gif">
                           </c:when>
                           <c:otherwise>
                              <img src="${ctxStatic}/model/images/yhpc/wj.gif">
                           </c:otherwise>
                        </c:choose>
					</div>
					<span class="fxdname" >
						${item.jcdname}
					</span>
					<div class="fxdperson">
						<div><dd>最近检查人：${item.uname}</dd></div>
						<div><dd>最近检查时间：<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					<div class="fxbc">
						<c:if test="${fn:length(item.bclist)>0}">
							<c:forEach items="${item.bclist}" var="bc">
								<c:choose>
									<c:when test="${bc.checkresult==0}">
										<div>${bc.bcname}<span class="f_green">(无问题)</span></div>
									</c:when>
									<c:when test="${bc.checkresult==1}">
										<div>${bc.bcname}<span class="f_red">(有问题)</span></div>
									</c:when>
									<c:otherwise>
			                            <div>${bc.bcname}<span class="f_gray">(未巡检)</span></div>
			                        </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
			  </div>
         </c:forEach>
      </div>
   </c:if>

   <c:if test="${fn:length(listb)>0}">
      <div class="box1">
         <div class="title">
            <h5>蓝色等级风险点:正常：${tj[3][0]} &nbsp; 异常：${tj[3][1]} &nbsp; 未巡检：${tj[3][2]} &nbsp;</h5>
         </div>
         <c:forEach items="${listb}" var="item">
              <div class="col-lg-3 col-sm-4" >
				<div class="fxdbc fxdbcblue" onclick="infor('${item.jcdid}','${item.jcdtype}')">
					<div class="fxdblue">
						<c:choose>
                           <c:when test="${item.checkresult==0}">
                              <img src="${ctxStatic}/model/images/yhpc/zc.gif">
                           </c:when>
                           <c:when test="${item.checkresult==1}">
                              <img src="${ctxStatic}/model/images/yhpc/yc.gif">
                           </c:when>
                           <c:otherwise>
                              <img src="${ctxStatic}/model/images/yhpc/wj.gif">
                           </c:otherwise>
                        </c:choose>
					</div>
					<span class="fxdname" >
						${item.jcdname}
					</span>
					<div class="fxdperson">
						<div><dd>最近检查人：${item.uname}</dd></div>
						<div><dd>最近检查时间：<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					<div class="fxbc">
						<c:if test="${fn:length(item.bclist)>0}">
							<c:forEach items="${item.bclist}" var="bc">
								<c:choose>
									<c:when test="${bc.checkresult==0}">
										<div>${bc.bcname}<span class="f_green">(无问题)</span></div>
									</c:when>
									<c:when test="${bc.checkresult==1}">
										<div>${bc.bcname}<span class="f_red">(有问题)</span></div>
									</c:when>
									<c:otherwise>
			                            <div>${bc.bcname}<span class="f_gray">(未巡检)</span></div>
			                        </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
			  </div>
         </c:forEach>
      </div>
   </c:if>

   <c:if test="${fn:length(listd)>0}">
      <div class="box1">
         <div class="title">
            <h5>自定义风险点:正常：${tj[4][0]} &nbsp; 异常：${tj[4][1]} &nbsp; 未巡检：${tj[4][2]} &nbsp;</h5>
         </div>
         <c:forEach items="${listd}" var="item">
              <div class="col-lg-3 col-sm-4" >
				<div class="fxdbc fxdbczc" onclick="infor('${item.jcdid}','${item.jcdtype}')">
					<div class="fxdzc">
						<c:choose>
                           <c:when test="${item.checkresult==0}">
                              <img src="${ctxStatic}/model/images/yhpc/zc.gif">
                           </c:when>
                           <c:when test="${item.checkresult==1}">
                              <img src="${ctxStatic}/model/images/yhpc/yc.gif">
                           </c:when>
                           <c:otherwise>
                              <img src="${ctxStatic}/model/images/yhpc/wj.gif">
                           </c:otherwise>
                        </c:choose>
					</div>
					<span class="fxdname" >
						${item.jcdname}
					</span>
					<div class="fxdperson">
						<div><dd>最近检查人：${item.uname}</dd></div>
						<div><dd>最近检查时间：<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm" /></div>
					</div>
					<div class="fxbc">
						<c:if test="${fn:length(item.bclist)>0}">
							<c:forEach items="${item.bclist}" var="bc">
								<c:choose>
									<c:when test="${bc.checkresult==0}">
										<div>${bc.bcname}<span class="f_green">(无问题)</span></div>
									</c:when>
									<c:when test="${bc.checkresult==1}">
										<div>${bc.bcname}<span class="f_red">(有问题)</span></div>
									</c:when>
									<c:otherwise>
			                            <div>${bc.bcname}<span class="f_gray">(未巡检)</span></div>
			                        </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
			  </div>
         </c:forEach>
      </div>
   </c:if>
<script type="text/javascript">
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}


//查询
function search(){
    $("#searchFrom").submit();
}

//巡检记录
function infor(jcdid, jcdtype) {
	if (jcdid == null || jcdid == "") {
		jcdid = 0;
	}
	if (jcdtype == null || jcdtype == "") {
		jcdtype = 0;
	}
	layer.open({
		type : 2,
		area : [ '900px', '450px' ],
		title : '历史巡检记录',
		maxmin : true,
		content : ctx + '/yhpc/xjdzt/xjjl?jcdid=' + jcdid + '&jcdtype=' + jcdtype,
		btn : [ '关闭' ],
		cancel : function(index) {
		}
	});
}
</script>
</body>
</html>