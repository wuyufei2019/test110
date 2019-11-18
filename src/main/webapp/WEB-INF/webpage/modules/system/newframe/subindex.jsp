<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
  <head>
    <title>安全生产监管一体化平台</title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
	<script src="${ctxStatic}/layer-v2.0/layer/laydate/laydate.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css?v=1.5">
	 
  </head>
 <%
 	String name="靖江市";
 	if(session.getAttribute("logotitle")!=null)
 		name=(String)session.getAttribute("logotitle");
 %>
  <body id="win" class="bgblue0">
  	<div class="container-fluid clearpd">
    <header class="ofh bgblue navbar-fixed-top" id="header" >
      <div class="mbox">
        <div class="pull-left logo"><%=name %>安全生产监管一体化平台</div>
        <div class="user pull-right">
          <i class="fa fa-home"></i>
          <a href="${ctx}/a">首页</a>
               &nbsp;&nbsp;&nbsp;
          <i class="fa fa-windows"></i>
          <a href="${ctx}/a/mainmenu" target="frame">系统</a>
               &nbsp;&nbsp;&nbsp;
          <i class="fa fa-sign-out"></i>
          <a href="javascript:logout()" id="btnExit" >退出</a>
         	&nbsp;&nbsp;&nbsp;
           <shiro:principal property="name"/>，
          <span>您好！</span>
        </div>
      </div>
    </header>

	<iframe src="${ctx}/system/permission/subpmenuList?id=${id}" width="100%" frameborder="0" id="frame" name="frame"></iframe>

	

   <footer class="ofh bgblue navbar-fixed-bottom" id="footer">
  
    <ul class="mbox clearpd caidan" id="main">
      <li class="col-lg-1  col-xs-1">
          <a href="javascript:history.go(-1)">
            <img src="${ctxStatic}/model/main/images/back.png" title="返回上一步">
            <div>返回</div>
          </a>
        </li>
    </ul>
  </footer>
  <div class="navbar-fixed-bottom fr" id="footbtn">
  	<div class="open" onclick="openfoot();">
  		<img src="${ctxStatic}/model/main/images/footup.png" title="显示菜单" width="66" style="float:right; margin: 25px;">
  	</div>
  </div>
  </div>
  	<script type="text/javascript">
  	
  	var id ='${id}';
  	$(document).ready(function(){
		change();
	});
    $(window).resize(function(){
		change();
	});
    function change(){
    	var h=$(window).height();
        var w=$(window).width();
        var headerheigh = $("#header").height();
        var footerheigh = $("#footer").height();
        if($("#footer").is(":hidden"))
        	footerheigh=10;
        $("#frame").height(h-headerheigh-footerheigh);//动态分配iframe高度
	}
    
 
    
    $(".open").hide();
    function closefoot(){
    	$(".open").show();
    	$("#footer").hide();
    	change();
    }
    function openfoot(){
    	$(".open").hide();
    	$("#footer").show();
    	change();
    }
    
  		$(function(){
  			
  			//初始化菜单
  			$.ajax({
  				type: 'post',
  			    url:'${ctx}/system/permission/i/topmenu',
  			    dataType:'json',
  				success:function(data)
  				{
  					var mainhtml='';
  					var count = 0;
  					for(var i=0;i<data.length;i++)
  					{
  						var obj = data[i];
  						var iconpath = '${ctxStatic}/model/main/images/'+obj.icon;
  							
  						var li=	'<li class="col-lg-1  col-xs-1">'+
	  				          		'<a href="javascript:openwin('+obj.id+')">'+
	  				            		'<img  title="'+obj.name+'" src="'+iconpath+'">'+
	  				            		'<div>'+obj.name+'</div>'+
	  				          		'</a>'+        
	  				        	'</li>';
 						mainhtml += li;
  					}
  					mainhtml+='<li class="col-lg-1  col-xs-1">'+
		          		'<a href="javascript:closefoot( )">'+
		            		'<img  title="隐藏菜单" src="${ctxStatic}/model/main/images/footdown.png">'+
		            		'<div>隐藏菜单</div>'+
		          		'</a>'+        
		        	'</li>';
  					$("#main").append(mainhtml);
  				}
  			})
  			
  		})
  	
  		
  		function logout(){
			 if(confirm("您确定要退出当前程序并关闭该网页？")){
				 window.onbeforeunload = null;
		         location.href='${ctx}/a/logout';
			 }
		}
  		
  		function openwin(id){
  			window.location.href='${ctx}/system/permission/subindex?id='+id;	
  		}
  	
  	</script>
   
  </body>
</html>
