<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>考试记录信息</title>
<meta name="decorator" content="default"/>
<style type="text/css">
body{
width: 100%;
height: 100%;
font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
background-color:#A3E0FF 
}
#nav {
    position: absolute;
    margin-left:100px;
    width: 150px;
    height: 120px;
	background-color:#FFFFFF; 
	margin: 0 50px 0 29px ;
	font-size: 15px;
	font-weight: bold; 
	padding: 15px 10px 20px 10px;  
    -webkit-box-shadow: 0 0 12px rgba(0,20,31,.35);
	-moz-box-shadow: 0 0 12px rgba(0,20,31,.35);
	box-shadow: 0 0 12px rgba(0,20,31,.35);
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

.aqpx_zxks_sjtk span{
font-size: 14px;
margin-top:48px;
line-height:32px; 
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
<div style="width: 100%;height:auto;text-align:center"" >
	<img src="${ctx}/static/model/images/aqpx/bg.png"/>
</div>
<div style="width: 100%;background-color:#A3E0FF " >
	<div id="content" style="width:900px;height:auto; margin: 0 auto;background-color: #FFFFFF; padding:10px 60px" closed="true">
		<div align="center" style="margin: 30px"><h3 style="color: #17A8EE;font-size:24px">考试记录</h3></div>
		<hr style="border-top:1px dashed #eeeeee; height:1px">
		<form id="aqpx_zxks_form_mainform"  novalidate>
		
		<c:if test="${stgz.m1>0}">
			<div id="lx">一、单项选择题(每题${stgz.m5}分，共${stgz.m1}题)</div>
			<c:set value="0" var="sum" />
			<c:forEach items="${list}" var="dx" varStatus="status">
				<c:if test="${dx.m1=='1'}">
				<c:set value="${sum + 1}" var="sum" />
				<div class="aqpx_zxks_sj">
				<div id="bt" style="margin: 3px 0px;font-size: 15px;font-weight: bold;line-height: 34px;<c:if test="${dx.m8!=dx.da}">color: #fc1d31
				</c:if>">${sum}. &nbsp ${dx.m2}（）</div>
				<span><input type="radio" value="A" class="i-checks" disabled="disabled"/>A.${dx.m3}</span><br/> 
				<span><input type="radio" value="B" class="i-checks" disabled="disabled" />B.${dx.m4}</span><br/> 
				<c:if test="${dx.m5 ne ''}">
				<span><input type="radio" value="C" class="i-checks" disabled="disabled" />C.${dx.m5}</span><br/> 
				</c:if>
				<c:if test="${dx.m6 ne ''}">
				<span><input type="radio" value="D" class="i-checks" disabled="disabled" />D.${dx.m6}</span><br/> 
				</c:if>
				<c:if test="${dx.m7 ne ''}">
				<span><input type="radio" value="E" class="i-checks" disabled="disabled" />E.${dx.m7}</span><br/>
				</c:if>
				<c:if test="${dx.m8==dx.da}">
					<span>考生答案：${dx.da}</span>
					<br/><span>正确答案：${dx.m8}</span>  
				</c:if>
				<c:if test="${dx.m8!=dx.da}">
					<span style="color: #fc1d31">考生答案：${dx.da}</span> 
					<br/><span style="color: #1ab394">正确答案：${dx.m8}</span> 
				</c:if>
				</div>
				<hr style="border-top:1px solid #eeeeee; height:1px">
				</c:if>
			</c:forEach> 
		</c:if>
		
		<c:if test="${stgz.m2>0}">
			<div id="lx">二、多选题(每题${stgz.m6}分，共${stgz.m2}题)</div>
			<c:set value="0" var="sum" />
			<c:forEach items="${list}" var="dsx" varStatus="status">
				<c:if test="${dsx.m1=='2'}">
				<c:set value="${sum + 1}" var="sum" />
				<div class="aqpx_zxks_sj" >
				<div id="bt">${sum}.&nbsp ${dsx.m2}（）</div>
				<span><input type="checkbox" value="A" name="dsx_${dsx.ID}" disabled="disabled"/> A.${dsx.m3}</span><br/> 
				<span><input type="checkbox" value="B" name="dsx_${dsx.ID}" disabled="disabled"/> B.${dsx.m4}</span><br/> 
				<c:if test="${dsx.m5 ne ''}">
				<span><input type="checkbox" value="C" name="dsx_${dsx.ID}" disabled="disabled"/> C.${dsx.m5}</span><br/> 
				</c:if>
				<c:if test="${dsx.m6 ne ''}">
				<span><input type="checkbox" value="D" name="dsx_${dsx.ID}" disabled="disabled"/> D.${dsx.m6}</span><br/> 
				</c:if>
				<c:if test="${dsx.m7 ne ''}">
				<span><input type="checkbox" value="E" name="dsx_${dsx.ID}" disabled="disabled"/> E.${dsx.m7}</span><br/>
				</c:if>
				<c:if test="${dsx.m8==dsx.da}">
					<span>考生答案：${dsx.da}</span> 
					<br/><span>正确答案：${dsx.m8}</span>
				</c:if>
				<c:if test="${dsx.m8!=dsx.da}">
					<span style="color: #fc1d31">考生答案：${dsx.da}</span> 
					<br/><span style="color: #1ab394">正确答案：${dsx.m8}</span>
				</c:if> 
				</div>
				<hr style="border-top:1px solid #eeeeee; height:1px">
				</c:if>
			</c:forEach> 
		</c:if>
		
		<c:if test="${stgz.m4>0}">
			<div  id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">三、判断题(每题${stgz.m8}分，共${stgz.m4}题)</div>
			<c:set value="0" var="sum" />
			<c:forEach items="${list}" var="pd" varStatus="status">
				<c:if test="${pd.m1=='4'}">
				<c:set value="${sum + 1}" var="sum" />
				<div class="aqpx_zxks_sj" >
				<div id="bt">${sum}.&nbsp ${pd.m2}</div>
				<span><input type="radio" class="i-checks" value="A" name="pd_${pd.ID}" disabled="disabled"/>A.${pd.m3}</span> 
				<span><input type="radio" class="i-checks" value="B" name="pd_${pd.ID}" disabled="disabled"/>B.${pd.m4}</span><br/>
				<c:if test="${pd.m8==pd.da}">
					<span>考生答案：${pd.da}</span> 
					<br/><span>正确答案：${pd.m8}</span>
				</c:if>
				<c:if test="${pd.m8!=pd.da}">
					<span style="color: #fc1d31">考生答案：${pd.da}</span> 
					<br/><span style="color: #1ab394">正确答案：${pd.m8}</span> 
				</c:if>
				</div>
				</c:if>
			</c:forEach> 
		</c:if>
		
		<c:if test="${stgz.m3>0}">
			<div id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">四、填空题(每题${stgz.m7}分，共${stgz.m3}题)</div>
			<c:set value="0" var="sum" />
			<c:forEach items="${list}" var="tk" varStatus="status">
			<c:if test="${tk.m1=='3'}">
			<c:set value="${sum + 1}" var="sum" />
				<div class="aqpx_zxks_sjtk" >
				<div id="bt">${sum}.&nbsp ${tk.m2}</div>
				<c:if test="${tk.m8==tk.da}">
					<span>考生答案：${tk.da}</span> 
					<br/><span>正确答案：${tk.m8}</span>
				</c:if>
				<c:if test="${tk.m8!=tk.da}">
					<span style="color: #fc1d31">考生答案：${tk.da}</span> 
					<br/><span style="color: #1ab394">正确答案：${tk.m8}</span>
				</c:if>
				</div>
			</c:if>
			</c:forEach> 
		</c:if>
		</form>
	</div>
</div>

<script type="text/javascript">
var lable;

//提交考试
function submitBtn(){
	layer.open({ 
	    area: [400, 200],
	    title: '提示',
        maxmin: false, 
	    content: '确定要提交本次考试？' ,
	    btn: ['确定','取消'],
	    yes: function(index, layero){
			$.post(ctx+"/aqpx/zxks/submit",$("#aqpx_zxks_form_mainform").serialize(),function(data){
				 layer.alert("本次考试成绩"+data.score+"分", {
				 	  title:'提示',
				 	  btn: ['查看考试详细','关闭'],
				      yes:function(){
					 	 openDialogView("考试详细",ctx+"/aqpx/aqpxjl/view/"+data.id,"100%", "100%","");
				      },
				      cancel:function(){
				          closelayer();
				      }
				  });
			});
		 },
		  cancel: function(index){ 
	     }
	}); 		
}	
//取消考试
function cancelBtn(){
	layer.open({ 
	    area: [400, 200],
	    title: '提示',
        maxmin: false, 
	    content: '确定要放弃本次考试？' ,
	    btn: ['确定','取消'],
	    yes: function(index, layero){
			closelayer();
		  },
		  cancel: function(index){ 
	     }
	}); 

}

function closelayer(){
 var index = parent.layer.getFrameIndex(window.name); 
    parent.layer.close(index);
}
</script>
</body>
</html>