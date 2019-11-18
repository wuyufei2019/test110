<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/dsf/index.js?v=1"></script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >  
					<td class="width-15 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-85" colspan="3" >
					${manage.m1 }
					</td>
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">单位类型：</label></td>
					<td colspan="3">${manage.m2 }</td> 
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法人代表：</label></td>
					<td class="width-30">${manage.m3 }</td>
					<td class="width-20 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-30">${manage.m4 }</td>
				</tr>
				<tr>	
					<td class="width-20 active"><label class="pull-right">注册地址：</label></td>
					<td class="width-30">
					${manage.m5 }
					</td>
					<td class="width-20 active"><label class="pull-right">注册资金(万元)：</label></td>
					<td class="width-30">${manage.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">项目负责人：</label></td>
					<td class="width-30">${manage.m7 }</td>
					<td class="width-20 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-30">${manage.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${manage.m9 }
					</td>				
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">上传文件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty manage.m10}">
					 <c:forTokens items="${manage.m10}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
			</tbody>
			</table>
			<input type="hidden" id="dwid" value="${manage.ID }" />	
		  	
	 </form>
	 <div style="margin-bottom: 5px;">
			<shiro:hasPermission name="dsffw:dsf:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addZz()" title="添加资质"><i class="fa fa-plus"></i> 添加资质</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dsffw:dsf:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addTz()" title="添加特种人员"><i class="fa fa-plus"></i> 添加特种人员</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dsffw:dsf:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="delZzorTz()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dsffw:dsf:update">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updZzprTz()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
			</shiro:hasPermission>
		</div>
		<div id="tt" class="easyui-tabs" data-options="fit:false,border:false"
			style="width:auto;height:100%;text-align: center;">
			<div title="资质信息" style="text-align: center;">
				<table id="aqjg_dsf_zz_dg"></table>
			</div>
			<div title="人员信息" style="text-align: center;">
				<table id="aqjg_dsf_tz_dg"></table>
			</div>
		</div>
<script type="text/javascript">
		var dwid=${manage.ID };
		$('#tt').tabs({
			onSelect : function(title, index) {
				//$("#div01").html()="<td>test</td>";
				if (title == "资质信息") { //资质信息
					tzorzz = true;
					zzlist();
				} else if (title == "特种人员信息") { //特种人员信息
					tzorzz = false;
					tzlist();
				}
			}
		});	
</script>
</body>
</html>