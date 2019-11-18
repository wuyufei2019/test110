<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时用电作业</title>
	<meta name="decorator" content="default"/>
</head>
<body>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-30">${lsyd.depname }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${lsyd.m1 }</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30">${lsyd.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30">${lsyd.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">计划作业开始时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m4 }"/></td>
					<td class="width-20 active"><label class="pull-right">计划作业结束时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m5 }"/></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-80" colspan="3">${lsyd.m6 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业规模：</label></td>
					<td class="width-80" colspan="3">${lsyd.m10 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">电源接入点：</label></td>
					<td class="width-30">${lsyd.m7 }</td>
					<td class="width-20 active"><label class="pull-right">工作电压：</label></td>
					<td class="width-30">${lsyd.m8 }</td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">用电设备及功率：</label></td>
					<td class="width-80" colspan="3">${lsyd.m9 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-30">${lsyd.m11 }</td>
					<td class="width-20 active"><label class="pull-right">电工证号：</label></td>
					<td class="width-30">${lsyd.m12 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30">${lsyd.m13 }</td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30">${lsyd.m14 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${lsyd.m15 },${lsyd.m15_1 }</td>
				</tr>
												
				</tbody>
			</table>

			
			<!-- 安全措施 -->
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30" >${lsyd.bzcsr }</td>
				</tr>  
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m22_2}">
						 <c:forTokens items="${lsyd.m22_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m22_7 }" />	
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m22_3}">
						 <c:forTokens items="${lsyd.m22_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m22_7 }" />					
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m22_4}">
					 <c:forTokens items="${lsyd.m22_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m22_5}">
					 <c:forTokens items="${lsyd.m22_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty lsyd.m22_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${lsyd.m16 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m16_1}">
						 <c:forTokens items="${lsyd.m16_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>							 
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m16_2 }" />			
					</td>
				</tr>  
			</table>
			<table id="aqcs"></table>
						
 			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">安技员意见：</label></td>
					<td class="width-30" >${lsyd.m18 }</td>
					<td class="width-20 active"><label class="pull-right">安技员：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m18_1}">
						 <c:forTokens items="${lsyd.m18_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>	 
					  </c:if>	
					  <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m18_2 }" />				
					</td>
				</tr>
				
			    <tr>
					<td class="width-20 active"><label class="pull-right">安全科长或分管领导意见：</label></td>
					<td class="width-30" >${lsyd.m19 }</td>
					<td class="width-20 active"><label class="pull-right">安全科长或分管领导：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m19_1}">
						 <c:forTokens items="${lsyd.m19_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m19_2 }" />					
					</td>
				</tr>
				
			    <tr>
					<td class="width-20 active"><label class="pull-right">条线分管领导意见：</label></td>
					<td class="width-30" >${lsyd.m20 }</td>
					<td class="width-20 active"><label class="pull-right">条线分管领导：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty lsyd.m20_1}">
						 <c:forTokens items="${lsyd.m20_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>				 	
						 </c:forTokens>
						 </c:if>	
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m20_2 }" />				
					</td>
				</tr>					

				<c:if test="${lsyd.m10 == '涉及变配电所' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">能源中心意见：</label></td>
							<td class="width-30" >${lsyd.m21 }</td>
							<td class="width-20 active"><label class="pull-right">能源中心：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty lsyd.m21_1}">
								 <c:forTokens items="${lsyd.m21_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m21_2 }" />					
							</td>
						</tr>				
				</c:if>	
							
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty lsyd.m23_1}">
							 <c:forTokens items="${lsyd.m23_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m23 }" />					
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty lsyd.m24_1}">
							 <c:forTokens items="${lsyd.m24_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m24 }" />					
					</td>					
				</tr>

			    <tr>
					<td class="width-20 active"><label class="pull-right">能源中心位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty lsyd.m25_1}">
							 <c:forTokens items="${lsyd.m25_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lsyd.m25 }" />					
					</td>
				</tr>
							
				</tbody>
			</table>
<script type="text/javascript">
$(function(){
	var lsydid='${lsyd.id}';
	var qrcsr='${lsyd.qrcsr}';
	var zt='${lsyd.zt}';
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/lsyd/aqcslist?type=0'+'&id1='+lsydid, 
	    fit : false,
		fitColumns : true,
		border : true,
		idField : 'id',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		striped:true,
	    columns:[[    
              	{field:'m1',title:'安全措施',width:100},
              	{field:'id2',title : '执行情况',sortable : false,width : 20,align : 'center',
					formatter : function(value, row, index) {
		              	if(value==null){
		              		return "";
		              	}else{
		              		return row.m3;
		              	}
		            }
	         	},
				{field : 'm4',title : '现场照片',sortable : false,width : 60,align : 'center',
		        	formatter : function(value) {
		        		if(value){
		        			var urls=value.split(",");
		        			var html="";
		        			for(var index in urls){
		        				html+="<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' onclick='openImgView(\""+urls[index].split("||")[0]+"\")'> 照片"+(parseInt(index)+1)+"</a><br>"; 
		        			}
		        			return html;
		        		}
		        		else return ; 
		        	}						
				}         	
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	if('${lsyd.m22_6}'!=null&&'${lsyd.m22_6}'!=''){
		var $list= $("#wlf");
		var wlflist= '${wlflist}';
		wlflist2=JSON.parse(wlflist);  
	    $.each(wlflist2, function(idx, obj) {
	        var $list= $("#wlf");
			var $li = $(
				'<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onClick="viewwlf('+obj.id+')">'+
				obj.wlfname+'</a>'
	            );
			$list.append( $li );
	    });			
	}
});

//查看
function viewwlf(id){
	openDialogView("查看外来方单位信息",ctx+"/ztzyaqgl/xgdw/view/"+id,"90%", "90%","");
	
}

</script>
</body>
</html>