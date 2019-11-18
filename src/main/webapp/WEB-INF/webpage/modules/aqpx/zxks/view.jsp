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
<div style="width: 100%;height:auto;text-align:center" >
	<img src="${ctx}/static/model/images/aqpx/bg.png"/>
	<div id="nav" style="">
		<div>
		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="submitBtn()" title="提交考试" style="width:120px"><i class="fa fa-plus"></i> <span style="font-size: 14px">提交考试</span></button>
		</div>
		<div style="margin: 10px 0px 6px 0px;">
		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="cancelBtn()" title="放弃考试" style="width:120px"><i class="fa fa-trash-o"></i> <span style="font-size: 14px">放弃考试</span></button>
		</div>
		<div style="text-align: center">
		<span>已用时间：</span><span id ="timelock"></span> 
		</div>
	</div>
</div>
<div style="width: 100%;background-color:#A3E0FF " >
	<div id="content" style="width:900px;height:auto; margin: 0 auto;background-color: #FFFFFF; padding:10px 60px" closed="true">
		<div align="center" style="margin:30px"><h3 style="color: #17A8EE;font-size:24px">${kcname}</h3></div>
		<font size="3" style="margin-left:25px">本次考试总分100分，考试时间60分钟 !</font>
		<hr style="border-top:1px dashed #eeeeee; height:1px">
		<form id="aqpx_zxks_form_mainform"  novalidate>
		
		<c:if test="${!empty stmap.dxlist}">
		<div id="lx">一、单项选择题(每题${stgz.m5}分，共${fn:length(stmap.dxlist)}题)</div>
		<c:forEach items="${stmap.dxlist}" var="dx" varStatus="status">
			<div class="aqpx_zxks_sj">
			<div id="bt" style="margin: 3px 0px;font-size: 15px;font-weight: bold;line-height: 34px">${status.count}. &nbsp ${dx.m2}</div>
			<span><input type="radio" value="A" class="i-checks" name="dx_${dx.ID}"/>A.${dx.m3}</span><br/> 
			<span><input type="radio" value="B" class="i-checks" name="dx_${dx.ID}"/>B.${dx.m4}</span><br/> 
			<c:if test="${dx.m5 ne ''}">
			<span><input type="radio" value="C" class="i-checks" name="dx_${dx.ID}"/>C.${dx.m5}</span><br/> 
			</c:if>
			<c:if test="${dx.m6 ne ''}">
			<span><input type="radio" value="D" class="i-checks" name="dx_${dx.ID}"/>D.${dx.m6}</span><br/> 
			</c:if>
			<c:if test="${dx.m7 ne ''}">
			<span><input type="radio" value="E" class="i-checks" name="dx_${dx.ID}"/>E.${dx.m7}</span><br/>
			</c:if>
			</div>
			<hr style="border-top:1px solid #eeeeee; height:1px">
		</c:forEach> 
		</c:if>
		
		<c:if test="${!empty stmap.dsxlist }">
		<div id="lx">二、多选题(每题${stgz.m6}分，共${fn:length(stmap.dsxlist)}题)</div>
		<c:forEach items="${stmap.dsxlist}" var="dsx" varStatus="status">
			<div class="aqpx_zxks_sj" >
			<div id="bt">${status.count}.&nbsp ${dsx.m2}（）</div>
			<span><input type="checkbox" class="i-checks" value="A" name="dsx_${dsx.ID}"/> A.${dsx.m3}</span><br/> 
			<span><input type="checkbox" class="i-checks" value="B" name="dsx_${dsx.ID}"/> B.${dsx.m4}</span><br/> 
			<c:if test="${dsx.m5 ne ''}">
			<span><input type="checkbox" class="i-checks" value="C" name="dsx_${dsx.ID}"/> C.${dsx.m5}</span><br/> 
			</c:if>
			<c:if test="${dsx.m6 ne ''}">
			<span><input type="checkbox" class="i-checks" value="D" name="dsx_${dsx.ID}"/> D.${dsx.m6}</span><br/> 
			</c:if>
			<c:if test="${dsx.m7 ne ''}">
			<span><input type="checkbox" class="i-checks" value="E" name="dsx_${dsx.ID}"/> E.${dsx.m7}</span><br/>
			</c:if>
			</div>
			<hr style="border-top:1px solid #eeeeee; height:1px">
		</c:forEach> 
		</c:if>
		
		<c:if test="${!empty stmap.pdlist }">
		<div  id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">三、判断题(每题${stgz.m8}分，共${fn:length(stmap.pdlist)}题)</div>
		<c:forEach items="${stmap.pdlist}" var="pd" varStatus="status">
			<div class="aqpx_zxks_sj" >
			<div id="bt">${status.count}.&nbsp ${pd.m2}</div>
			<span><input type="radio" class="i-checks" value="A" name="pd_${pd.ID}"/>A.${pd.m3}</span> 
			<span><input type="radio" class="i-checks" value="B" name="pd_${pd.ID}"/>B.${pd.m4}</span> 
			</div>
		</c:forEach> 
		</c:if>
		
		<c:if test="${!empty stmap.tklist }">
		<div id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">四、填空题(每题${stgz.m7}分，共${fn:length(stmap.tklist)}题)</div>
		<c:forEach items="${stmap.tklist}" var="tk" varStatus="status">
			<div class="aqpx_zxks_sjtk" >
			<div id="bt">${status.count}.&nbsp ${tk.m2}</div>
			答案：<input name="tk_${tk.ID}" class="easyui-textbox" value="" style="width: 300px;height: 30px;" "/>
			</div>
		</c:forEach> 
		</c:if>
		<input type="hidden"  value="" name="time"/>
		<input type="hidden"  value="${kcid}" name="kcid"/>
		<input type="hidden"  name="planid"/>
		</form>
	</div>
</div>

<script type="text/javascript">
var time=0;
var lable;
var pid=parent.planid;
var submitFlag = true;
var commitFlag = true;

function timedCount()
{	
	time=time+1;
	//考试时间到提醒交卷
	if(time>3600){
		layer.alert("考试时间到！请交卷！", {
			 title:'提示',
			 closeBtn: 0,
		     btn: ['提交'],
		     yes:function(){
		   	 submitact();
		    }
		 });
		return;
	}
    var minute=Math.floor(time/60%60);
    if(minute<10){
    	minute="0"+minute;
    }
    var second=Math.floor(time%60);
    if(second<10){
    	second="0"+second;
    }
	lable=minute+":"+second;
	document.getElementById('timelock').innerHTML=lable;
	$("input[name='time']").val(time);
	setTimeout("timedCount()",1000);
}
timedCount();

function submitact(){
	if (commitFlag) {
		commitFlag = false;
		$.post(ctx+"/aqpx/zxks/submit",$("#aqpx_zxks_form_mainform").serialize(),function(data){
		    console.log(data);
			 layer.alert("本次考试成绩"+data.score+"分", {
			 	  title:'提示',
			      btn: ['查看考试详细','关闭'],
			      yes:function(){
				 	 openDialogView("考试详细",ctx+"/aqpx/aqpxjl/view/"+data.id,"100%", "100%","");
			      },
                 btn2:function(index){
                     closelayer();
                 },
			      cancel:function(){
			          closelayer();
			      }
			  });
		});
	}
}

//提交考试
function submitBtn(){
	if (submitFlag) {
		submitFlag = false;
		layer.open({ 
		    area: [400, 200],
		    title: '提示',
	        maxmin: false, 
		    content: '确定要提交本次考试？' ,
		    btn: ['确定','取消'],
		    yes: function(index, layero){
		    	$("input[name='planid']").val(pid);
		    	layer.close(index);
		    	submitact();
			 },
			  cancel: function(index){ 
		     }
		}); 
		submitFlag = true;
		commitFlag = true;
	}		
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