<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default"/>
</head>
<body>
		<form id="excel_mainform" action="${ctx}/${url}" method="post">
		
		<input type="hidden" id="colval" name="colval" />
	 	<input type="hidden" id="coltext" name="coltext" />
	 	
		<table id="exceldg" class="easyui-datagrid" style="width:100%;height:250px;" data-options="fitColumns:true">   
		</table>  
       	
       	</form> 
<script type="text/javascript">
			var para=parent.expara;//查询参数
			var exdata=parent.exdata;//字段
			var html="";
			 $.each( para, function(name, value){
			 	html+="<input type='hidden' name='"+name+"' value='"+value+"' /> "
			 });
			 $('#excel_mainform').append(html);
			 		
			 $('#exceldg').datagrid({    
			    columns:[[    
					        {field:'colval',title:'colval',checkbox:true,width:50,align:'center'},    
					        {field:'coltext',title:'列名',width:100,align:'center'}
					    ]],
			    data: exdata,
			    scrollbarSize:0
			});
						 
			function  doExport(){
				var colval="";
				var coltext="";
				var cols=$('#exceldg').datagrid("getChecked");
				if(cols.length==0){
					cols=$('#exceldg').datagrid("getData").rows;
				}
				for(var i=0;i<cols.length;i++){
					if(colval==""){
						colval=cols[i].colval;
					}else{
						colval=colval+","+cols[i].colval;
					}
					if(coltext==""){
						coltext=cols[i].coltext;
					}else{
						coltext=coltext+","+cols[i].coltext;
					}
				}
				$("#colval").val(colval);
				$("#coltext").val(coltext);
				
				$("#excel_mainform").submit();
				

			};
</script>
</body>
</html>