<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>断路作业</title>
	<meta name="decorator" content="default"/>
</head>
<body>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-30">${dlzy.depname }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${dlzy.m1 }</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30">${dlzy.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30">${dlzy.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30">${dlzy.m4 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30">${dlzy.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30" colspan="3">${dlzy.m6 }</td>
				</tr>

				<tr> 
					<td class="width-20 active"><label class="pull-right">审批部门：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="process" value="2" />能控中心</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="3" />分厂</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="4" />设备处</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="5" />保卫处 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="6" />安全科 </div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">断路原因：</label></td>
					<td class="width-30" colspan="3">${dlzy.m7 }</td>
				</tr>
												
				<tr>
					<td class="width-20 active"><label class="pull-right">作业开始时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m8 }"/></td>
					<td class="width-20 active"><label class="pull-right">作业结束时间：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m9 }"/></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">断路相关说明：</label></td>
					<td class="width-30" colspan="3" style="height: 80px;">${dlzy.m10 }</td>
				</tr>

			    <tr>
					<td class="width-20 active"><label class="pull-right">示意图：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty dlzy.m10_1}">
						 <c:forTokens items="${dlzy.m10_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>					
					</td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${dlzy.m11 },${dlzy.m11_1 }</td>
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
					<td class="width-30" >${dlzy.bzcsr }</td>
				</tr>  
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dlzy.m18_2}">
						 <c:forTokens items="${dlzy.m18_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m18_7 }" />	
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dlzy.m18_3}">
						 <c:forTokens items="${dlzy.m18_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m18_7 }" />					
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dlzy.m18_4}">
					 <c:forTokens items="${dlzy.m18_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dlzy.m18_5}">
					 <c:forTokens items="${dlzy.m18_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty dlzy.m18_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${dlzy.m12 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty dlzy.m12_1}">
						 <c:forTokens items="${dlzy.m12_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>						 
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m12_2 }" />		
						 			
					</td>
				</tr>  
			</table>
			<table id="aqcs"></table>
						
 			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			  	
			  	<c:if test="${fn:contains(dlzy.process, '2') }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">能控中心意见：</label></td>
						<td class="width-30" >${dlzy.m13 }</td>
						<td class="width-20 active"><label class="pull-right">能控中心：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty dlzy.m13_1}">
							 <c:forTokens items="${dlzy.m13_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>	
						  </c:if>
						  <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m13_2 }" />					
						</td>
					</tr>
				</c:if>
				
				<c:if test="${fn:contains(dlzy.process, '3') }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">分厂意见：</label></td>
						<td class="width-30" >${dlzy.m14 }</td>
						<td class="width-20 active"><label class="pull-right">分厂：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty dlzy.m14_1}">
							 <c:forTokens items="${dlzy.m14_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
							 </c:if>
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m14_2 }" />					
						</td>
					</tr>
				</c:if>
				
				<c:if test="${fn:contains(dlzy.process, '4') }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">设备处意见：</label></td>
						<td class="width-30" >${dlzy.m15 }</td>
						<td class="width-20 active"><label class="pull-right">设备处：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty dlzy.m15_1}">
							 <c:forTokens items="${dlzy.m15_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
							 </c:if>
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m15_2 }" />					
						</td>
					</tr>					
				</c:if>
				
				<c:if test="${fn:contains(dlzy.process, '5') }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">保卫处意见：</label></td>
						<td class="width-30" >${dlzy.m16 }</td>
						<td class="width-20 active"><label class="pull-right">保卫处：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty dlzy.m16_1}">
							 <c:forTokens items="${dlzy.m16_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
							 </c:if>
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m16_2 }" />					
						</td>
					</tr>				
				</c:if>	

				<c:if test="${fn:contains(dlzy.process, '6') }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">安全处意见：</label></td>
						<td class="width-30" >${dlzy.m17 }</td>
						<td class="width-20 active"><label class="pull-right">安全处：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty dlzy.m17_1}">
							 <c:forTokens items="${dlzy.m17_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
							 </c:if>
							 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m17_2 }" />					
						</td>
					</tr>				
				</c:if>	
											
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty dlzy.m19_1}">
							 <c:forTokens items="${dlzy.m19_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m19 }" />					
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty dlzy.m20_1}">
							 <c:forTokens items="${dlzy.m20_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dlzy.m20 }" />					
					</td>					
				</tr>
							
				</tbody>
			</table>
<script type="text/javascript">
$(function(){
	var dlzyid='${dlzy.id}';
	var zt='${dlzy.zt}';
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/dlzy/aqcslist?type=0'+'&id1='+dlzyid, 
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
	
	if('${dlzy.m18_6}'!=null&&'${dlzy.m18_6}'!=''){
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
	
	//审批部门
	var spbm = '${dlzy.process}';
	var spbmArr = spbm.split(",");
	for(var i=0;i<spbmArr.length;i++){
		$("input[name='process']:checkbox[value='"+spbmArr[i]+"']").attr('checked','true');
	}
	
	$("input[type='checkbox']").attr('disabled','disabled');
});

//查看
function viewwlf(id){
	openDialogView("查看外来方单位信息",ctx+"/ztzyaqgl/xgdw/view/"+id,"90%", "90%","");
}

</script>
</body>
</html>