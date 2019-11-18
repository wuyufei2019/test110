<%@page import="com.cczu.sys.comm.utils.Global"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>法律法规数据库</title>
<link href="${ctx}/static/flfgk/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/flfgk/css/wjl_style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/static/flfgk/js/jquery-SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctxStatic}/flfgk/js/jquery-stylish-select.js"></script>
<script type="text/javascript">

$(function(){ 
	  //下拉框样式
	  $('#Type').sSelect();
	  var windowHeight = $(window).height();
	  var bodyHeight = $('body').height();
	  var mHeight = $('.m-height').height();
	  var txtScrollBoxHeight = $('.txtScrollBox').height();
	  if(windowHeight > bodyHeight){
		  var cHeight = (windowHeight - bodyHeight)/10;
		  $('.m-height').height(cHeight*7+mHeight-120);
		  $('.txtScrollBox').height(cHeight*3+txtScrollBoxHeight);
	  }else{
		  var cHeight = (bodyHeight - windowHeight)/10;
		  $('.m-height').height(mHeight - (cHeight*5)-120);
		  $('.txtScrollBox').height(txtScrollBoxHeight - (cHeight*5));
	  }
});
function changeType(type){
	var searchForm =document.getElementById("searchForm");
	var url = "${ctx}/sekb/ptzyk/search";
}
function search(){
	 if($("#Query").val()==''){
		alert("请输入需要检索的内容 ");
		return;
	}
	var searchForm =document.getElementById("searchForm");
	var url = "${ctx}/sekb/ptzyk/search";
	searchForm.action =url;
	searchForm.submit();
}
$(function(){
	var left=0;
	var $nav=$("#nav");
	if($("#nav>div").eq(0).width()<=$(".searchBox .searchNav").width()){
		$("#right").addClass("opacity");
		return;
	}
	var min=$("div.searchNav").eq(0).width()-$("#nav>div").eq(0).width();//left 最小值
	var showfirst=0;//当前显示在最前的项 
	var leftf=function(){
		if(showfirst<=0){
			return;
		}
		var move=$("#nav a").eq(showfirst).offset().left - $("#nav a").eq(showfirst-1).offset().left;
		showfirst--;
		left=left+move;
		$nav.css("left",left+"px");
		$("#right").removeClass("opacity");
		if(left>=0){
			$("#left").addClass("opacity");
		}
	};
	var right=function(){
		if(left<=min){
			return;
		}
		var move=$("#nav a").eq(showfirst+1).offset().left - $("#nav a").eq(showfirst).offset().left;
		showfirst++;
		left=left-move;
		$nav.css("left",left+"px");
		$("#left").removeClass("opacity");
		if(left<=min){
			$("#right").addClass("opacity");
		}
	};
//	var last=();
	var flag=0;
	
	$("#left").mousedown(function(){
		leftf();
		flag=setTimeout(function(){
			leftf();
			flag=setInterval(function(){leftf();},200);
		},500);
	});
	$("#right").mousedown(function(){
		right();
		flag=setTimeout(function(){
			right();
			flag=setInterval(function(){right();},200);
		},500);
	});
	$(document).mouseup(function(){
		clearTimeout(flag);
		clearInterval(flag);
	});
});
function checkEffectlevel(effectLevel,el){
	$("#effectLevel").val(effectLevel);
	$(".searchNav a.current").removeClass("current");
	$("#effectLevel_"+effectLevel).addClass("current");
	var num=lawCount(effectLevel);
	/* if(num>=10){
		$("#Query").attr("placeholder","在"+num+"部法规中搜索");
	}else if(num>0){
		$("#Query").attr("placeholder","在"+num+"部法规中搜索")
	}else {
		$("#Query").attr("placeholder","暂时没有法规可供搜索!")
	} */
}
//计算对应效力等级的大概法规数量
function lawCount(key){
	var num=$("div.searchB>a>span[key="+key+"]").text();
	/* var str="";
	if(num>10){
		str=num.substring(0,1)+num.substring(1).replace(/./g,"0");
	}else{
		str=num;
	} */
	//return parseInt(str);
	return parseInt(num);
}

/* $(function(){
	$("#Query").attr("placeholder","在"+lawCount(0)+"部法规中搜索")
}); */

</script>
<style>
	body{background:#f1f1f1;}
  div.searchNav span{
    position: absolute;
    top:0px;
    border: 15px solid transparent;
    border-width:15px 10px; 
    cursor: pointer;
  }
 div.searchNav .left{
    left:-50px;
    border-right-color:#295EAB;
  }
  div.searchNav .right{
    right:-50px;
     border-left-color:#295EAB; 
  }
 div.searchNav .opacity{
    cursor: default;
    border-color:transparent;
 }
</style>
</head>

<body>
<!------主体------->
<div class="w_m pageWidth">
	<div class="m-height"></div>
    <div class="w_logo"><a href="#"><img src="${ctxStatic}/flfgk/images/FLFGK_TITLE.png"/></a>
    </div>
    <form id="searchForm" method="get" action="${ctx}/sekb/ptzyk/search">
    <input type="hidden" id="effectLevel" name="effectLevel" value=""/>
    <div class="searchBox">
    
    	<div class="searchNav searchNav_index" style="position: relative;height: 36px;">
       <div style="width: 100%;overflow: hidden;">
        <div style="width:10000px;position: relative;" id="nav">
          <div style="display:inline-block;">
        	<a class="current" id="effectLevel_0" href="javascript:checkEffectlevel()">全库</a>
        	
        		
            		<a id="effectLevel_1" href="javascript:checkEffectlevel(1)">法律</a>
            	
        		
            		<a id="effectLevel_2" href="javascript:checkEffectlevel(2)">法规</a>
            	
        		
            		<a id="effectLevel_3" href="javascript:checkEffectlevel(3)">规章</a>
            		
            		
            		<a id="effectLevel_4" href="javascript:checkEffectlevel(4)">地方性法规</a>


                    <a id="effectLevel_5" href="javascript:checkEffectlevel(5)">政府文件</a>


                    <a id="effectLevel_6" href="javascript:checkEffectlevel(6)">标准规范</a>


                    <a id="effectLevel_7" href="javascript:checkEffectlevel(7)">其他</a>
        		
          </div></div></div>
          <span id="left" class="left opacity"></span>
          <span id="right" class="right"></span>
        </div>
        <div class="w_search" style="text-align: center;">
        	<input type="hidden" name="SiteID" value="124" />
        	<input id="Query" name="m2" class="w_searchText" type="text" value="" placeholder="请输入需要检索的内容"/>
            <input type="hidden" id="Sort" name="Sort" value="PublishTime" />
			<input class="w_searchBut" type="button" value="" onclick="search()" />
            <!-- <div class="selectCss">
            <select id="Type" name="Type" onchange="changeType()">
                <option value="1" selected="selected">标题</option>
                <option value="2" >内容</option>
            </select> -->
           <!-- <div class="w_searchListA high_search" >
                 	<a href="/AdvanceSearch?SiteID=124">高级检索</a>
		   </div> -->
            <%--<div style="padding-top:8px;clear:both;text-align: center;">
              <label style="font-size:16px"><input value="1" name="Type" type="radio" checked=&quot;checked&quot; onclick="changeType(1)"/>按标题检索</label>&nbsp;&nbsp;&nbsp;&nbsp;
               <label  style="font-size:16px"><input value="2" name="Type" type="radio"  onclick="changeType(2)"/>按正文检索</label>
            </div>--%>
        </div>
        <div class="searchB" style="display:none">
        
        	
             	
        		<!-- <a href="/list?Type=1&ID=2&SiteID=124">法律：<span key="2">1025</span></a>
             	
        		<a href="/list?Type=1&ID=3&SiteID=124">行政法规：<span key="3">1737</span></a>
             	
        		<a href="/list?Type=1&ID=5&SiteID=124">国务院部门规章：<span key="5">5816</span></a>
             	
        		<a href="/list?Type=1&ID=4&SiteID=124">地方性法规：<span key="4">26532</span></a>
             	
        		<a href="/list?Type=1&ID=6&SiteID=124">地方政府规章：<span key="6">30750</span></a>
            
             	
            	<a href="/list?Type=1&ID=8&SiteID=124" style="display:none">案例：<span key="8">0</span></a>
             	
            	<a href="/list?Type=1&ID=7&SiteID=124" style="display:none">司法解释：<span key="7">777</span></a>
             	
            	<a href="/list?Type=1&ID=13&SiteID=124" style="display:none">国外法规条例：<span key="13">0</span></a>
             	
            	<a href="/list?Type=1&ID=15&SiteID=124" style="display:none">领导讲话：<span key="15">0</span></a>
             	
            	<a href="/list?Type=1&ID=16&SiteID=124" style="display:none">中央文件：<span key="16">0</span></a>
             	
            	<a href="/list?Type=1&ID=19&SiteID=124" style="display:none">法规性文件：<span key="19">0</span></a> 
            <a href style="display:none">全库<span key="0">66637</span></a>-->
        </div>
    
    </div>
	</form>
	<div class="slideGroup">
        <div class="slideBox" style="height:130px">
        </div><!-- slideBox End -->
    </div>

	<script type="text/javascript">
        jQuery(".slideGroup .slideBox").slide({ mainCell:"ul",vis:5,prevCell:".sPrev",nextCell:".sNext",effect:"leftLoop",autoPlay:true});
    </script>
    
</div>
</body>
</html>
