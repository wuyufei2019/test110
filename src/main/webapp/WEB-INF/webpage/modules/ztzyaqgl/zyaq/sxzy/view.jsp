<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30">${sxzy.bzr }</td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30">${sxzy.m1 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" >${sxzy.m2 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30" >${sxzy.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-30">${sxzy.m4 }</td>
					<td class="width-20 active"><label class="pull-right">受限空间内原有介质名称：</label></td>
					<td class="width-30">${sxzy.m5 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-30" colspan="3">${sxzy.m6 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sxzy.m7 }"/></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sxzy.m8 }"/></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30" colspan="3">${sxzy.m9 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30">${sxzy.m10 }</td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30">${sxzy.m11 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-30" colspan="3">${sxzy.m12 }</td>
				</tr>
																								
				<tr style="display:none" id="tsqk">
					<td class="width-20 active"><label class="pull-right">其他危险情况：</label></td>
					<td class="width30" colspan="3">${sxzy.m13_1 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间等级：</label></td>
					<td class="width-30">${sxzy.m13 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${sxzy.m14 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-80" colspan="3" style="height:80px">${sxzy.m15 }</td>
				</tr>
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析数据</strong></p><hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;" />
			<table id="fxsj"></table>
			
			<!-- 安全措施 -->
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30" colspan="3">${sxzy.bzr }</td>
				</tr>  
				</tbody>
			</table>
			<table id="aqcs"></table> 

			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 100%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m21_2}">
						 <c:forTokens items="${sxzy.m21_2}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>	
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m21_7 }" />
					</td>
					<td class="width-20 active"><label class="pull-right">许可人签字：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m21_3}">
						 <c:forTokens items="${sxzy.m21_3}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>
					 </c:if>
					 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m21_7 }" />					
					</td>
				</tr>			  	
			  	
				<tr>
					<td class="width-20 active"><label class="pull-right">安全交底：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m21_4}">
					 <c:forTokens items="${sxzy.m21_4}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m21_5}">
					 <c:forTokens items="${sxzy.m21_5}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>					
				</tr>
				
				<c:if test="${not empty sxzy.m21_6}">
				    <tr>
						<td class="width-20 active"><label class="pull-right">涉及外来方：</label></td>
						<td class="width-80" colspan="3" id="wlf"></td>
					</tr> 				
				</c:if>
						  	
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位意见：</label></td>
					<td class="width-30" >${sxzy.m16 }</td>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m16_1}">
						 <c:forTokens items="${sxzy.m16_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>						 
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m16_2 }" />				
					</td>
				</tr>  
			</table>

 			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">安技员意见：</label></td>
					<td class="width-30" >${sxzy.m17 }</td>
					<td class="width-20 active"><label class="pull-right">安技员：</label></td>
					<td class="width-30" >
					 <c:if test="${not empty sxzy.m17_1}">
						 <c:forTokens items="${sxzy.m17_1}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="50px;"/></a>
						 	</div><br/>
						 </c:forTokens>	
					  </c:if>	
					  <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m17_2 }" />				
					</td>
				</tr>
				
				<c:if test="${sxzy.m13 != '特殊' }">
				    <tr>
						<td class="width-20 active"><label class="pull-right">安全科长或分管领导意见：</label></td>
						<td class="width-30" >${sxzy.m18 }</td>
						<td class="width-20 active"><label class="pull-right">安全科长或分管领导：</label></td>
						<td class="width-30" >
						 <c:if test="${not empty sxzy.m18_1}">
							 <c:forTokens items="${sxzy.m18_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>	
							 </c:forTokens>
						 </c:if>
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m18_2 }" />					
						</td>
					</tr>
				</c:if>

				<c:if test="${sxzy.m13 == '特殊' }">
					    <tr>
							<td class="width-20 active"><label class="pull-right">部门一把手意见：</label></td>
							<td class="width-30" >${sxzy.m19 }</td>
							<td class="width-20 active"><label class="pull-right">部门一把手：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty sxzy.m19_1}">
								 <c:forTokens items="${sxzy.m19_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m19_2 }" />					
							</td>
						</tr>				
					    <tr>
							<td class="width-20 active"><label class="pull-right">安全处分管人员意见：</label></td>
							<td class="width-30" >${sxzy.m20 }</td>
							<td class="width-20 active"><label class="pull-right">安全处分管人员：</label></td>
							<td class="width-30" >
							 <c:if test="${not empty sxzy.m20_1}">
								 <c:forTokens items="${sxzy.m20_1}" delims="," var="url" varStatus="urls">
								 	<c:set var="urlna" value="${fn:split(url, '||')}" />
								 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
								 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
								 	</div><br/>
								 </c:forTokens>
								 </c:if>	
								 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m20_2 }" />				
							</td>
						</tr>
				</c:if>	
							
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业单位完工确认签字：</label></td>
					<td class="width-30" >
						 <c:if test="${not empty sxzy.m22_1}">
							 <c:forTokens items="${sxzy.m22_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>	
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m22}" />				
					</td>
					<td class="width-20 active"><label class="pull-right">分厂完工确认签字：</label></td>
					<td class="width-30" >
					 	<c:if test="${not empty sxzy.m23_1}">
							 <c:forTokens items="${sxzy.m23_1}" delims="," var="url" varStatus="urls">
							 	<c:set var="urlna" value="${fn:split(url, '||')}" />
							 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="50px;"/></a>
							 	</div><br/>
							 </c:forTokens>
						 </c:if>	
						 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m23 }" />				
					</td>					
				</tr>
			
				</tbody>
			</table>
       </form>
<script type="text/javascript">
//特殊作业
var tszy = '${sxzy.m14}';
var tszyArr = tszy.split(",");
for(var i=0;i<tszyArr.length;i++){
	$("input[name='M14']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
}

//危害辨识
var whbs = '${sxzy.m15}';
var whbsArr = whbs.split(",");
for(var i=0;i<whbsArr.length;i++){
	$("input[name='M15']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
}

$(function(){
	var sxzyid='${sxzy.id}';
	var qrcsr='${sxzy.qrcsr}';
	var zt='${sxzy.zt}';
	dg=$('#fxsj').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/sxzy/sxzyfxlist',
	    queryParams:{'sxzyid':sxzyid},
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
    			{field : 'm1',title : '类型',sortable : false,width : 80,align : 'center'},
				{field : 'm2',title : '介质',sortable : false,width : 40,align : 'center'},
				{field : 'm3',title : '数值',sortable : false,width : 40,align : 'center'},
				{field : 'm4',title : '单位',sortable : false,width : 40,align : 'center'},
	        	{field : 'm5',title : '分析时间',sortable : false,width : 60,align : 'center',
					formatter : function(value, row, index) {
		              	if(value!=null&&value!='') {
		              		var datetime=new Date(value);
		              		return datetime.format('yyyy-MM-dd hh:mm');
		              	}
		            }
	         	},
	         	{field : 'm6',title : '部位',sortable : false,width : 40,align : 'center'},
				{field : 'm7',title : '分析人',sortable : false,width : 40,align : 'center'},
				{field : 'm8',title : '现场照片',sortable : false,width : 60,align : 'center',
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
				},
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/ztzyaqgl/sxzy/aqcslist?type=0'+'&id1='+sxzyid,
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
				},	         	
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	if('${sxzy.m21_6}'!=null&&'${sxzy.m21_6}'!=''){
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