<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高处作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-30">${gczy.depname }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${gczy.m1 }</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30">${gczy.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30">${gczy.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">计划作业开始时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${gczy.m4 }"/></td>
					<td class="width-20 active"><label class="pull-right">计划作业结束时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${gczy.m5 }"/></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-80" colspan="3">${gczy.m6 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-80" colspan="3">${gczy.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-80" colspan="3">${gczy.m8 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">特殊情况：</label></td>
					<td class="width-80" colspan="3">${gczy.m9_1 }</td>
				</tr>
												
				<tr>
					<td class="width-20 active"><label class="pull-right">作业高度(m)：</label></td>
					<td class="width-30">${gczy.m9 }</td>				
					<td class="width-20 active"><label class="pull-right">作业等级：</label></td>
					<td class="width-30">${gczy.m10 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监护人：</label></td>
					<td class="width-30">${gczy.m11 }</td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30">${gczy.m12 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-80" colspan="3">${gczy.m13 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${gczy.m14 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${gczy.m15 }</td>
				</tr>
												
				</tbody>
			</table>

			<table id="fxsj"></table>
			
			<!-- 安全措施 -->
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30" >${gczy.bzcsr }</td>
				</tr>  
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m21_2}">
						 <c:forTokens items="${gczy.m21_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m21_7 }" />	
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m21_3}">
						 <c:forTokens items="${gczy.m21_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>		
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m21_7 }" />			
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m21_4}">
					 <c:forTokens items="${gczy.m21_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m21_5}">
					 <c:forTokens items="${gczy.m21_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty gczy.m21_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${gczy.m16 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m16_1}">
						 <c:forTokens items="${gczy.m16_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>								 
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m16_2 }" />			
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
					<td class="width-30" >${gczy.m17 }</td>
					<td class="width-20 active"><label class="pull-right">安技员：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty gczy.m17_1}">
						 <c:forTokens items="${gczy.m17_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>	
					  </c:if>	
					  <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m17_2 }" />				
					</td>
				</tr>
				
				<c:if test="${gczy.m10 != '四级' }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">安全科长或分管领导意见：</label></td>
						<td class="width-30" >${gczy.m18 }</td>
						<td class="width-20 active"><label class="pull-right">安全科长或分管领导：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty gczy.m18_1}">
							 <c:forTokens items="${gczy.m18_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>						 	
							 </c:forTokens>
							 </c:if>	
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m18_2 }" />				
						</td>
					</tr>
					<c:if test="${gczy.m10 == '二级' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管人员意见：</label></td>
							<td class="width-30" >${gczy.m19 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管人员：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty gczy.m19_1}">
								 <c:forTokens items="${gczy.m19_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m19_2 }" />					
							</td>
						</tr>					
					</c:if>
					<c:if test="${gczy.m10 == '三级' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管领导意见：</label></td>
							<td class="width-30" >${gczy.m20 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty gczy.m20_1}">
								 <c:forTokens items="${gczy.m20_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>	
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m20_2 }" />					
							</td>
						</tr>					
					</c:if>					
				</c:if>

				<c:if test="${gczy.m10 == '四级' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">部门一把手意见：</label></td>
							<td class="width-30" >${gczy.m24 }</td>
							<td class="width-20 active"><label class="pull-right">部门一把手：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty gczy.m24_1}">
								 <c:forTokens items="${gczy.m24_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>	
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m24_2 }" />				
							</td>
						</tr>				
					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管领导意见：</label></td>
							<td class="width-30" >${gczy.m20 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty gczy.m20_1}">
								 <c:forTokens items="${gczy.m20_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>	
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m20_2 }" />					
							</td>
						</tr>
					    <tr>
							<td class="width-20 active"><label class="pull-right">公司分管领导意见：</label></td>
							<td class="width-30" >${gczy.m25 }</td>
							<td class="width-20 active"><label class="pull-right">公司分管领导：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty gczy.m25_1}">
								 <c:forTokens items="${gczy.m25_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>	
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m25_2 }" />					
							</td>
						</tr>										
				</c:if>	
							
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty gczy.m22_1}">
							 <c:forTokens items="${gczy.m22_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m22 }" />					
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty gczy.m23_1}">
							 <c:forTokens items="${gczy.m23_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${gczy.m23 }" />					
					</td>					
				</tr>
			
				</tbody>
			</table>
       </form>
<script type="text/javascript">
$(function(){
	var gczyid='${gczy.id}';
	var qrcsr='${gczy.qrcsr}';
	var zt='${gczy.zt}';
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/gczy/aqcslist?type=0'+'&id1='+gczyid, 
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
	
	if('${gczy.m21_6}'!=null&&'${gczy.m21_6}'!=''){
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