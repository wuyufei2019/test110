<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全生产监管一体化平台</title>
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.4/css_default/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.4/js/bootstrap.min.js"  type="text/javascript"></script>
	<link href="${ctxStatic}/awesome/4.4/css/font-awesome.min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/main/style.css?v=1.4">
</head>
  
<body  class="bgblue0">
<div class="container-fluid clearpd">
 
	  <div  class="ofh content" id="indexbox" >
      <ul class="mbox clearpd iconbox" id="menuli">
         
      </ul>
    </div>
  </div>
 
<script>


$(function(){
	
	$("#menuli").empty();
	
	//初始化菜单
	$.ajax({
		type: 'post',
	    url:'${ctx}/system/permission/i/topmenu',
	    dataType:'json',
		success:function(data)
		{
			var html='';	
			for(var i=0;i<data.length;i++)
			{
				var obj = data[i];
				var iconpath = '${ctxStatic}/model/main/images/'+obj.icon;
				var li = '<li class="col-lg-2 col-xs-4">'+
				          '<a href="javascript:openwin('+obj.id+')">'+
				            '<img alt="'+obj.name+'"  title="'+obj.name+'" src="'+iconpath+'">'+
				            '<div>'+obj.name+'</div>'+
				          '</a>'+        
				        '</li>';
				html += li;
			}
			$("#menuli").html(html);
		}
	})
	
});


function logout(){
	 if(confirm("您确定要退出当前程序并关闭该网页？")){
		 
		 window.onbeforeunload = null;
		 location.href='${ctx}/a/logout';
	 }
}


function openwin(id){
	parent.location.href='${ctx}/system/permission/subindex?id='+id;	

}


</script>
    
</body>
</html>
