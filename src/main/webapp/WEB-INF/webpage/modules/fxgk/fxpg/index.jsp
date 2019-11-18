<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>风险评估风险分析</title>
<meta name="decorator" content="default" />
</head>
<body>
   <div id="tabs" class="easyui-tabs" fit="true">
      <div  title="JHA风险分析" style="height:100%;" data-options="">
         <iframe id="jha" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
      </div>
      <div  title="SCL风险分析" style="height:100%;" data-options="">
         <iframe id="scl" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
      </div>
       <div  title="LEC风险分析" style="height:100%;" data-options="">
         <iframe  id="lec" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
      </div>
      <div  title="HAZOP风险分析" style="height:100%;" data-options="">
         <iframe  id="hazop" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
      </div>
       <div  title="危险度风险分析" style="height:100%;" data-options="">
         <iframe  id="wxd" frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no ></iframe>
      </div>
   </div>
   <script type="text/javascript">
   //防止页面多次加载
   $(function(){  
       $('#jha').attr('src',"${ctx}/fxpg/jha/index");  
	   $('#tabs').tabs({
		   onSelect: function(title){
			   if(title=="SCL风险分析"){
      			   $('#scl').attr('src',"${ctx}/fxpg/scl/index");  
			   }else if(title=="LEC风险分析"){
      			   $('#lec').attr('src',"${ctx}/fxpg/lec/index");  
			   }else if(title=="危险度风险分析"){
      				$('#wxd').attr('src',"${ctx}/fxpg/wxd/index");  
			   }else if(title=="HAZOP风险分析"){
      				$('#hazop').attr('src',"${ctx}/fxpg/hazop/index");  
			   }else if(title=="JHA风险分析"){
       				$('#jha').attr('src',"${ctx}/fxpg/jha/index");  
			   } 
		   }
		 });
  });  
	</script>
</body>
</html>