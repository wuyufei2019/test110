<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>集团首页</title>

	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${ctxStatic}/model/css/home/styles.css?v=1.0" rel="stylesheet" />
	<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
	<script src="${ctxStatic}/layer-v2.0/layer/laydate/laydate.js"></script>
<style type="text/css">

</style>
</head>
<body id="groupbox">
 
			<div class="container-fluid">
				<!--公司简介-->
				<div class="box">
					<div class="title">
						公司简介
					</div>
					<div class="aboutus">
						<c:if test="${!empty company.qyimg }">
						<img class="pull-left" src="${company.qyimg}">
						</c:if>
						<c:if test="${empty company.qyimg }">
						<img class="pull-left" src="${ctxStatic}/model/images/home/aboutus.jpg">
						</c:if>
						<div>${company.m45} </div>
					</div>
					<!-- <a class="btn bg-primary pull-right">进入系统</a>  -->
				</div>
				<!--子公司-->
				<div class="box">
					<div class="title">公司巡检隐患统计</div>
					<div class="totalcom">
						<div class="col-lg-2 col-md-4">共安装巡检点：<b class="f_red">${allxjds}</b></div>
						<div class="col-lg-2 col-md-4">本日已巡检：<b class="f_green">${allyxjds}</b></div>
						<div class="col-lg-2 col-md-4">本日未巡检：<b class="f_red">${allwxjds}</b></div>
						<div class="col-lg-2 col-md-4">隐患总数：<b class="f_green">${allyhs}</b></div>
						<div class="col-lg-2 col-md-4">已整改隐患数:<b class="f_red">${allzgyhs}</b></div>
					</div>
					<div class="soncom">
						
							<c:if test="${fn:length(jtgslist)>0}">
								<c:forEach items="${jtgslist}" var="list">
								<div class="col-lg-4 col-md-6">
									<div class="box">
										<div class="title">${list.RowNumber}.${list.m1}</div>
										<div class="totalpoint">
											安装巡检点：<b class="f_red">${list.xjds}</b>
										</div>
										<div class="col-lg-6 col-md-6">
											本日已巡检：<b class="f_green">${list.yxjds}</b>
										</div>
										
										<div class="col-lg-6 col-md-6">
											隐患总数：<b class="f_red">${list.yhs}</b>
										</div>
										<div class="col-lg-6 col-md-6">
											本日未巡检：<b class="f_red">${list.wxjds}</b>
										</div>
										<div class="col-lg-6 col-md-6">
											已整改隐患数：<b class="f_green">${list.zgyhs}</b>
										</div>
										<a href="javascript:void(0);"  class="btn bg-primary pull-right" onclick="viewXjdzt('${list.id}','${list.m1}')">查看详情</a>
									</div>
								</div>
								</c:forEach>
							</c:if>
					</div>
				</div>
			</div>
		 
		 
		 <script type="text/javascript">
		 
		 //查看分公司巡检点状态
		 function viewXjdzt(id,qyname){
			 window.wqyname=qyname;
		 	layer.open({
		 	    type: 2, 
		 	    area: ["95%", "95%"],
				offset: '10px',
		 	    title: " ",
		        maxmin: true, 
		 	    content: "${ctx}/yhpc/xjdzt/index2?qyid=" + id,
		 	    btn: ['关闭'],
		 	    cancel: function(index){ 
		 	       }
		 	}); 
		 }
			 
		 </script>
	</body>
</html>