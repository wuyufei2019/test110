<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>企业安全知识在线考试</title>
<meta name="decorator" content="default"/>
<style type="text/css">
body{
	width: 100%;
	height: 100%;
	font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
	background-color:#A3E0FF;
	background-image: url('${ctx}/static/model/images/aqpx/bg2.png');
	background-position-x:center;
	background-repeat:no-repeat;
}

#header_title {
    width: 100%;
    text-align:center;
}

#lx {
	font-size: 16px;
	font-weight: bold;
	color: #76889c;
	margin-bottom:20px;
	line-height: 32px
}

#bt {
	font-size: 15px;
	font-weight: bold;
	margin-bottom:20px;
	line-height: 32px
}

.aqpx_zxks_sj span{
font-size: 14px;
margin-top:48px;
line-height:32px; 
}

.aqpx_zxks_sj{
margin-top:30px;
}

.aqpx_zxks_sj input{
margin-left:20px;
}

button:hover{
background-color:#ddf3ff ;
}

input[type="checkbox"]
{ 
width:18px;
height:18px;
}
</style>
</head>
<body>
<div id="header_title">
	<h1 style="padding:35px 0 50px;font-weight: bold;">培训课程调查</h1>
	<div style="position: absolute;top: 20px;right: 80px;">
	<%-- <shiro:hasPermission name="aqpx:xqdc:set">
		<a href="javascript:void(0);" class="btn btn-success" onclick="setInfor()" style="width:120px">调查设置</a>
	</shiro:hasPermission> --%>
	</div>
</div>

<div style="width: 100%;" >
	<div id="content" style="width:900px;height:auto; margin: 0 auto;background-color: #FFFFFF; padding: 60px" closed="true">
		<form id="aqpx_zxks_form_mainform"  novalidate>
			<a href="javascript:void(0);" class="btn btn-info" onclick="tj()" style="width:80px;float: right;" >统计信息</a>
			<div id="lx">请选择您想培训的课程</div>
			<hr style="border-top:1px solid #eeeeee; height:1px">
			
			<c:forEach items="${kclist}" var="kc" varStatus="vs">  
		         <div class="aqpx_zxks_sj">
		         	<span>
		         		<c:set var="isDoing" value="0" />
	         			<c:forEach var="kcid" items="${tp.ID1}" varStatus="status">
	         				<c:if test="${kc.id == kcid }">
	         					<input type="checkbox" value="${kc.id }" class="i-checks" name="dx" checked="checked"/>${kc.RowNumber}、${kc.m1 }
	         					<c:set var="isDoing" value="1" />
	         				</c:if>
	         			</c:forEach>
			         	<c:if test="${isDoing eq '0'}">
			         		<input type="checkbox" value="${kc.id }" class="i-checks" name="dx"/>${kc.RowNumber}、${kc.m1 }
			         	</c:if>
			        	<c:choose>
				         	<c:when test="${kc.high eq '1'}">
				         		<font style="float: right;margin-right: 30px;color: red;">${kc.m2 }票</font>
				         	</c:when>
				         	<c:otherwise>
				         		<font style="float: right;margin-right: 30px;">${kc.m2 }票</font>
				         	</c:otherwise>
				         </c:choose>
			        </span>
				 	<br/><hr style="border-top:1px dashed #eeeeee; height:1px">
				 </div>    
			</c:forEach>  
			
		 	<div style="text-align: center;">
		 		<a class="btn btn-primary" onclick="savetp()" style="width:120px">提交保存</a>
		 	</div>
		 	<input type="hidden" name="ztid" value="${ztid}" />
		 	<c:if test="${not empty tp.ID}">
				<input type="hidden" name="ID" value="${tp.ID}" />
				<input type="hidden" name="ID1" value="${tp.ID1}" />
				<input type="hidden" name="ID2" value="${tp.ID2}" />
				<input type="hidden" name="ID3" value="${tp.ID3}" />
			</c:if>
		</form>
	</div>
</div>

<script type="text/javascript">
var ztid = '${ztid}';

function savetp() {
	if($('input:checkbox:checked').val()==undefined){
		layer.msg("请选择您想培训的课程！",{time: 3000});
		return;
	}
	confirmx('确定提交保存吗?', function(index) {
		var url="${ctx}/aqpx/xqdc/tpcreate";
		if('${flag}'=='1'){
			var url="${ctx}/aqpx/xqdc/tpupdate";
		}
		$.ajax({
			type : 'post',
			url : url,
			data : $("#aqpx_zxks_form_mainform").serialize(),
			success : function(data) {
				window.location.reload();
			},
			error : function(data) {
				layer.open({icon:1,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
		});
	});
}

/* function setInfor() {
	openDialog("设置培训课程信息",ctx+"/aqpx/xqdc/setinfor/"+ztid,"900px", "70%","");
} */

function tj() {
	window.location.href = ctx+"/aqpx/xqdc/dctjindex/"+ztid;
}
</script>
</body>
</html>