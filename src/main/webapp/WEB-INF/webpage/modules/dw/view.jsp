<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>作业报备管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.combo-arrow {
		    background: url('images/combo_arrow.png') no-repeat center center;
		}
	</style>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">作业类型：</label></td>
					<td class="width-35" colspan="3">${zysp.zylx}</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">作业方式：</label></td>
					<td class="width-35"><input class="easyui-combobox" readonly="readonly" name="M1" value="${zysp.zyfs }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto', editable:false , multiple:true , required:true ,data: [
								        {value:'1',text:'动火作业'},
								        {value:'2',text:'受限空间作业'},
								        {value:'3',text:'管道拆卸作业'},
								        {value:'4',text:'盲板抽堵作业'},
								        {value:'5',text:'高处安全作业'},
								        {value:'6',text:'吊装安全作业'},
								        {value:'7',text:'临时用电安全作业'},
								        {value:'8',text:'动土安全作业'},
								        {value:'9',text:'断路安全作业'},
								        {value:'10',text:'其他作业'}]"/></td>
					<td class="width-15 active"><label class="pull-right">作业级别：</label></td>
					<td class="width-35">${zysp.zyjb }</td>
				</tr>
				
				<tr >   
					<td class="width-15 active"><label class="pull-right">作业队伍：</label></td>
					<td class="width-35" colspan="3">
						${zysp.sgdw }
					</td>
				</tr>	
				
				<tr >
					<td class="width-15 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-35" colspan="3">
						${zysp.m3 }
					</td>
				</tr>				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业负责人：</label></td>
					<td class="width-35" >${zysp.m4 }</td>
					<td class="width-15 active"><label class="pull-right">作业人员：</label></td>
					<td class="width-35">${zysp.m5 }</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-35" colspan="3" >
						${zysp.m6 }
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-35"><fmt:formatDate value="${zysp.m7 }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="width-15 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-35"><fmt:formatDate value="${zysp.m8 }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">第三方服务公司：</label></td>
					<td class="width-35">${zysp.m18 }</td>
					<td class="width-15 active"><label class="pull-right">第三方负责人：</label></td>
					<td class="width-35">${zysp.m19 }</td>
				</tr>
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">危险因素：</label></td>
					<td colspan="3" id="zybb_wxys"> </td>
					</tr>	
					<tr> 
					<td class="width-15 active"><label class="pull-right">易导致事故类型：</label></td>
					<td colspan="3" id="zybb_sglx"> </td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业票：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty zysp.m12}">
					 <c:forTokens items="${zysp.m12}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">作业方案：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty zysp.m13}">
					 <c:forTokens items="${zysp.m13}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">应急方案：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty zysp.m15}">
					 <c:forTokens items="${zysp.m15}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
								<tr>
					<td class="width-15 active"><label class="pull-right">人员证件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty zysp.m16}">
					 <c:forTokens items="${zysp.m16}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty zysp.m14}">
					 <c:forTokens items="${zysp.m14}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>			
				<tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var M9 = "${zysp.m9}";
	var zylxArr = M9.split(",");
	for(var i=0;i<zylxArr.length;i++){
		if(zylxArr[i]=='1')
			$("#zybb_wxys").append("易燃易爆物质、 ");
		else if(zylxArr[i]=='2')
			$("#zybb_wxys").append("坠落、 ");
		else if(zylxArr[i]=='3')
			$("#zybb_wxys").append("腐蚀性物质、 ");
		else if(zylxArr[i]=='4')
			$("#zybb_wxys").append("蒸汽、 ");
		else if(zylxArr[i]=='5')
			$("#zybb_wxys").append("高压气体/液体、 ");
		else if(zylxArr[i]=='6')
			$("#zybb_wxys").append("有毒有害物质、 ");
		else if(zylxArr[i]=='7')
			$("#zybb_wxys").append("高温/低温、 ");
		else if(zylxArr[i]=='8')
			$("#zybb_wxys").append("触电、 ");
		else if(zylxArr[i]=='9')
			$("#zybb_wxys").append("窒息、 ");
		else if(zylxArr[i]=='10')
			$("#zybb_wxys").append("噪音、 ");
		else if(zylxArr[i]=='11')
			$("#zybb_wxys").append("产生火花/静电、 ");
		else if(zylxArr[i]=='12')
			$("#zybb_wxys").append("旋转设备、 ");
		else if(zylxArr[i]=='13')
			$("#zybb_wxys").append("机械伤害、 ");
		else if(zylxArr[i]=='14')
			$("#zybb_wxys").append("粉尘、 ");
		else if(zylxArr[i]=='15')
			$("#zybb_wxys").append("不利天气、 ");
		else if(zylxArr[i]=='16')
			$("#zybb_wxys").append("淹没/埋没、 ");
		else if(zylxArr[i]=='17')
			$("#zybb_wxys").append("辐射、 ");
		else if(zylxArr[i]=='18')
			$("#zybb_wxys").append("交叉作业、 ");
		else if(zylxArr[i]=='19')
			$("#zybb_wxys").append("${zysp.m10 } ");
		
	}

	var M11 = "${zysp.m11}";
	var zyjbArr = M11.split(",");
	for(var i=0;i<zyjbArr.length;i++){
		if(zyjbArr[i]=='1')
			$("#zybb_sglx").append("物体打击、 ");
		else if(zyjbArr[i]=='2')
			$("#zybb_sglx").append("车辆伤害、 ");
		else if(zyjbArr[i]=='3')
			$("#zybb_sglx").append("机械伤害 、 ");
		else if(zyjbArr[i]=='4')
			$("#zybb_sglx").append("起重伤害、 ");
		else if(zyjbArr[i]=='5')
			$("#zybb_sglx").append("触电、 ");
		else if(zyjbArr[i]=='6')
			$("#zybb_sglx").append("淹溺、 ");
		else if(zyjbArr[i]=='7')
			$("#zybb_sglx").append("灼烫 、 ");
		else if(zyjbArr[i]=='8')
			$("#zybb_sglx").append("火灾、 ");
		else if(zyjbArr[i]=='9')
			$("#zybb_sglx").append("高处坠落、 ");
		else if(zyjbArr[i]=='10')
			$("#zybb_sglx").append("坍塌、 ");
		else if(zyjbArr[i]=='11')
			$("#zybb_sglx").append("冒顶片帮、 ");
		else if(zyjbArr[i]=='12')
			$("#zybb_sglx").append("透水、 ");
		else if(zyjbArr[i]=='13')
			$("#zybb_sglx").append("放炮、 ");
		else if(zyjbArr[i]=='14')
			$("#zybb_sglx").append("火药爆炸、 ");
		else if(zyjbArr[i]=='15')
			$("#zybb_sglx").append("瓦斯爆炸、 ");
		else if(zyjbArr[i]=='16')
			$("#zybb_sglx").append("锅炉爆炸、 ");
		else if(zyjbArr[i]=='17')
			$("#zybb_sglx").append("容器爆炸、 ");
		else if(zyjbArr[i]=='18')
			$("#zybb_sglx").append("其它爆炸、 ");
		else if(zyjbArr[i]=='19')
			$("#zybb_sglx").append("中毒和窒息、 ");
		else if(zyjbArr[i]=='20')
			$("#zybb_sglx").append("其它伤害");
	}

	$(function(){ 
		$("input[name='M9']:checkbox").css("width","18px");
		$("input[name='M9']:checkbox").css("height","18px");
		$("input[name='M11']:checkbox").css("width","18px");
		$("input[name='M11']:checkbox").css("height","18px");
	});
</script>
</body>
</html>