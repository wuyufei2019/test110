<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时检查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			
			<div title="检查信息" iconCls="icon-add" data-options="selected:true " style="padding:10px;">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			    <tbody>
			    <tr>
					<td class="width-15" colspan="4"><label class="pull-left">初查信息：</label></td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查名称：</label></td>
					<td class="width-35" colspan="3">${lsjcjl.m16 }</td>
				</tr>				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${lsjcjl.m2 }"/></td>
					<td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-35">${lsjcjl.m4 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">整改期限：</label></td>
					<td class="width-35"><fmt:formatDate value="${lsjcjl.m3 }"/></td>
					<td class="width-15 active"><label class="pull-right">整改负责人：</label></td>
					<td class="width-35">${lsjcjl.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查记录表：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty lsjcjl.m7}">
					 <c:forTokens items="${lsjcjl.m7}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">
						${lsjcjl.m14 }
					</td>
				</tr>				
				</tbody>
				</table>
		</div>	
			
		<div title="复查信息" iconCls="icon-add" data-options="selected:false " style="padding:10px;">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-15" colspan="4"><label class="pull-left">复查信息：</label></td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">复查时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${lsjcjl.m8 }"/></td>
					<td class="width-15 active"><label class="pull-right">复查人员：</label></td>
					<td class="width-35">${lsjcjl.m9 }</td>
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">检查情况：</label></td>
					<td class="width-35" colspan="3">
						<table id="czwttable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 15%; "></td>
								<td style="text-align: center;width: 50%;  ">内容</td>
								<td style="text-align: center;width: 35%;">图片(点击查看原图)</td>
							</tr>
						</table>	
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查记录表：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty lsjcjl.m12}">
					 <c:forTokens items="${lsjcjl.m12}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				</table>
			</div>	
       </form>
<script type="text/javascript">
var wtid=1;
var wtListInit=${czwt};
	
//初始化
function initData(){
	$.each(wtListInit, function(idx, obj) {
		var czwt=obj.m1;
    	var url=obj.m2;  	
    	var img=url.split("||");
    	var pic="";
    	if(url==null||url==""){
    		pic='<div style="margin:0 auto; width:20px">/<div>';
    	}else{
    		pic='<img onclick=openImgView("'+img[0]+'") src='+img[0]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
    	}
		var $trHtml = $(
			'<tr style="height:60px" >'+
			'<td>初查情况:'+
			'</td>'+
			'<td style="width:50%" >'+
			czwt+
			'</td>'+
			'<td>'+
			'<div id="divwtfj_'+ wtid + '" >' +
			pic+
			'</div>'+
			'</td>'+
			'</tr>'
		 );	
		 
		var czwt2=obj.m3;
    	var url2=obj.m4;  	
    	var img2=url2.split("||");
    	var pic2="";
    	if(url2==null||url2==""){
    		pic2='<div style="margin:0 auto; width:20px">/<div>';
    	}else{
    		pic2='<img onclick=openImgView("'+img2[0]+'") src='+img2[0]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
    	}
		var $trHtml2 = $(
			'<tr style="height:60px" >'+
			'<td>复查情况:'+
			'</td>'+			
			'<td style="width:50%" >'+
			czwt2+
			'</td>'+
			'<td>'+
			'<div id="divwtfj_'+ wtid + '" >' +
			pic2+
			'</div>'+
			'</td>'+
			'</tr>'
		 );	
		//id和数量加1
		wtid=wtid+1;
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml2 );
		$list.after( $trHtml );
	});
}

$(function(){
	initData();
});
</script>
</body>
</html>