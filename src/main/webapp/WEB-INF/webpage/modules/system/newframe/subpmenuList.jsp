<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
  <head>
    <title>安全生产监管一体化平台</title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
   <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css?v=1.4">
	 
  </head>
  
  <body id="win" class="bgblue0">
	<div class="container-fluid clearpd">
	 <div class="mbox content yqyd posr" style="padding:0">
	 <div class="path"> </div>
	  
     <div class="col-lg-12 ofh">
     
	 <div id="two"></div>
	 
      <!-- 只有二级 没有三级的菜单 在这里显示-->
      <div id="one" class="thridmenu" style="margin-top: 30px;">
        
      </div>
      <div style="clear:both;height:20px;"></div>
     </div>
     
    </div>
  </div>
  
  	<script type="text/javascript">
  
  	$(document).ready(function(){
		change();
	});
    $(window).resize(function(){
		change();
	});
    function change(){
    	var h=$(window).height();
        var w=$(window).width();
        var object=document.getElementById('win');
        object.style.height = h+"px";
	}
  		$(function(){
  			
  			$("#one").empty();
  			$("#two").empty();
  			//初始化菜单
  			$.ajax({
  				type: 'post',
  			    url:'${ctx}/system/permission/i/childmenu?pid=${pid}',
  			    dataType:'json',
  				success:function(data)
  				{
  					$.each(data, function(i, item) {
  						if(item.id=='${pid}'){
  							data= item.children;
  							$(".path").html(item.name);
  							return;
  						} 
  					});
  					
  					for(var i=0;i<data.length;i++)
  					{
  						var item = data[i];
  						if(item.children==null||item.children.length == 0){
  							var iconpath = '${ctxStatic}/model/main/images/'+item.icon;
  							var li =  '<a class="col-lg-2 col-md-3 col-sm-4" href="javascript:openwin(\''+item.href+'\')">'+
								'<img alt="'+item.name+'" title="'+item.name+'" src="'+iconpath+'"><div>'+item.name+'</div>'+
			       			 '</a>';
  							$("#one").append(li);
  						}else{
  							var html='<div class="secondmenu"><i class="fa fa-star fblue"></i><a href="#">'+item.name+'</a></div>'
  							var html2='';
  							$.each(item.children, function(i, item2) {
  								iconpath = '${ctxStatic}/model/main/images/'+item2.icon;
  								html2+='<a class="col-lg-2 col-md-3 col-sm-4" href="javascript:openwin(\''+item2.href+'\')">'+
								'<img alt="'+item2.name+'" title="'+item2.name+'" src="'+iconpath+'"><div>'+item2.name+'</div></a>';
  		  					});
  							html2='<div class="thridmenu">'+html2+'</div>';
  							 
  							$("#two").append(html+html2);
  						}
  					}
						if($("#one").html()=='')
							$("#one").remove();
 
  				}
  			})
  			
  		})
  	
  		 
  		
  		
  		function logout(){
			 if(confirm("您确定要退出当前程序并关闭该网页？")){
				 
				 window.onbeforeunload = null;
		         location.href='${ctx}/a/logout';
			 }
		}
  		
  		function openwin(url){
  			if(url!=''){
  				window.location.href='${ctx}/'+url;	
  			}
		}
 
  	</script>
   
  </body>
</html>
