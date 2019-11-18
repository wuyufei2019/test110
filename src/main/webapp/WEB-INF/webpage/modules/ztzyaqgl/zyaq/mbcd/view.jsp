<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>盲板抽堵作业</title>
	<meta name="decorator" content="default"/>
</head>
<body>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-30">${mbcd.depname }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${mbcd.m1 }</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30">${mbcd.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30">${mbcd.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">设备管道名称：</label></td>
					<td class="width-80"  colspan="3">${mbcd.m4 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">介质：</label></td>
					<td class="width-30">${mbcd.m5 }</td>
					<td class="width-20 active"><label class="pull-right">温度：</label></td>
					<td class="width-30">${mbcd.m6 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">压力：</label></td>
					<td class="width-30">${mbcd.m7 }</td>
					<td class="width-20 active"><label class="pull-right">盲板材质：</label></td>
					<td class="width-30">${mbcd.m8 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">盲板规格：</label></td>
					<td class="width-30">${mbcd.m9 }</td>
					<td class="width-20 active"><label class="pull-right">盲板编号：</label></td>
					<td class="width-30">${mbcd.m10 }</td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">实施时间（堵）：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m11 }"/></td>
					<td class="width-20 active"><label class="pull-right">实施时间（抽）：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m12 }"/></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">作业人（堵）：</label></td>
					<td class="width-30">${mbcd.m13 }</td>
					<td class="width-20 active"><label class="pull-right">作业人（抽）：</label></td>
					<td class="width-30">${mbcd.m14 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监护人（堵）：</label></td>
					<td class="width-30">${mbcd.m15 }</td>
					<td class="width-20 active"><label class="pull-right">监护人（抽）：</label></td>
					<td class="width-30">${mbcd.m16 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">生产单位作业指挥：</label></td>
					<td class="width-30" colspan="3">${mbcd.m17 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30" colspan="3">${mbcd.m18 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30">${mbcd.m19 }</td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30">${mbcd.m20 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${mbcd.m21 },${mbcd.m21_1 }</td>
				</tr>

			    <tr>
					<td class="width-20 active"><label class="pull-right">盲板位置图：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty mbcd.m22}">
						 <c:forTokens items="${mbcd.m22}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>					
					</td>
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
					<td class="width-30" >${mbcd.bzcsr }</td>
				</tr>  
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m29_2}">
						 <c:forTokens items="${mbcd.m29_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>	
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m29_7 }" />
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m29_3}">
						 <c:forTokens items="${mbcd.m29_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>	
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m29_7 }" />				
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m29_4}">
					 <c:forTokens items="${mbcd.m29_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m29_5}">
					 <c:forTokens items="${mbcd.m29_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty mbcd.m29_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${mbcd.m23 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m23_1}">
						 <c:forTokens items="${mbcd.m23_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>					 
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m23_2 }" />					
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
					<td class="width-30" >${mbcd.m24 }</td>
					<td class="width-20 active"><label class="pull-right">安技员：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m24_1}">
						 <c:forTokens items="${mbcd.m24_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>	
					  </c:if>	
					  <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m24_2 }" />				
					</td>
				</tr>
				
			    <tr>
					<td class="width-20 active"><label class="pull-right">部门一把手意见：</label></td>
					<td class="width-30" >${mbcd.m25 }</td>
					<td class="width-20 active"><label class="pull-right">部门一把手：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m25_1}">
						 <c:forTokens items="${mbcd.m25_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m25_2 }" />					
					</td>
				</tr>
				
			    <tr>
					<td class="width-20 active"><label class="pull-right">安全处分管领导意见：</label></td>
					<td class="width-30" >${mbcd.m26 }</td>
					<td class="width-20 active"><label class="pull-right">安全处分管领导：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m26_1}">
						 <c:forTokens items="${mbcd.m26_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m26_2 }" />					
					</td>
				</tr>					

			    <tr>
					<td class="width-20 active"><label class="pull-right">保卫部意见：</label></td>
					<td class="width-30" >${mbcd.m27 }</td>
					<td class="width-20 active"><label class="pull-right">保卫部：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m27_1}">
						 <c:forTokens items="${mbcd.m27_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m27_2 }" />					
					</td>
				</tr>				

			    <tr>
					<td class="width-20 active"><label class="pull-right">公司分管领导意见：</label></td>
					<td class="width-30" >${mbcd.m28 }</td>
					<td class="width-20 active"><label class="pull-right">公司分管领导：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty mbcd.m28_1}">
						 <c:forTokens items="${mbcd.m28_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m28_2 }" />					
					</td>
				</tr>
											
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty mbcd.m30_1}">
							 <c:forTokens items="${mbcd.m30_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m30 }" />					
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty mbcd.m31_1}">
							 <c:forTokens items="${mbcd.m31_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${mbcd.m31 }" />					
					</td>					
				</tr>

				</tbody>
			</table>
<script type="text/javascript">
$(function(){
	var mbid='${mbcd.id}';
	var qrcsr='${mbcd.qrcsr}';
	var zt='${mbcd.zt}';
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/mbcd/aqcslist?type=0'+'&id1='+mbid,
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
	
	if('${mbcd.m29_6}'!=null&&'${mbcd.m29_6}'!=''){
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