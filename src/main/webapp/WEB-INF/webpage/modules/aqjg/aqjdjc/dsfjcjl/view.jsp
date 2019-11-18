<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方检查记录</title>
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
					<td class="width-15 active"><label class="pull-right">第三方单位名称：</label></td>
					<td class="width-35" colspan="3">${dwname}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查企业：</label></td>
					<td class="width-35" colspan="3">${qyname}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${dsfjcjl.m2 }"/></td>
					<td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-35">${dsfjcjl.m4 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">整改期限：</label></td>
					<td class="width-35"><fmt:formatDate value="${dsfjcjl.m3 }"/></td>
					<td class="width-15 active"><label class="pull-right">整改负责人：</label></td>
					<td class="width-35">${dsfjcjl.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查记录表：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty dsfjcjl.m7}">
					 <c:forTokens items="${dsfjcjl.m7}" delims="," var="url" varStatus="urls">
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
		</div>	
			
		<div title="复查信息" iconCls="icon-add" data-options="selected:false " style="padding:10px;">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-15" colspan="4"><label class="pull-left">复查信息：</label></td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">复查时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${dsfjcjl.m8 }"/></td>
					<td class="width-15 active"><label class="pull-right">复查人员：</label></td>
					<td class="width-35">${dsfjcjl.m9 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<table id="tt" style="width:605px"></table>
					</td>	
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查记录表：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty dsfjcjl.m12}">
					 <c:forTokens items="${dsfjcjl.m12}" delims="," var="url" varStatus="urls">
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
var jlid='${dsfjcjl.ID}';
$(function(){
	target=$('#tt').datagrid({
		method : "get",
		view: detailview,
		width:638,
		height: 'auto',
		nowrap:false,
		singleSelect:true,
		url:ctx+'/dsffw/jcjl/nrlist/'+jlid,
		columns:[[
			{field:'m1',title:'隐患名称(展开查看复查意见)',width:300},
			{field:'m2',title:'初检图片',width:160,
				formatter: function(value, rowData, rowIndex){
				    	if(value==null||value==""){
    						return '无';
    					}else{
    						var img=value.split("||");
    						return '<a target="_blank" href="'+img[0]+'">'+
								   '<div class=\'info\'>' + img[1] + '</div>' +
								   '</a>'
    					}
				}
			},
			{field:'m5',title:'状态',width:150,
				formatter: function(value, rowData, rowIndex){
					if(value=='1'){
                		return '已整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" disabled />' +
                		'未整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" disabled />';
            		}else if(value=='0'){
            		    return '已整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" disabled />' +
                		'未整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" disabled />';
            		}else{
            			return '已整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" disabled />' +
                		'未整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" disabled />';
            		}
            	}
			},
			{field:'ID',title:'id',width:50,hidden:'true'}
		]], 
        detailFormatter:function(rowIndex, rowData){
            var fcwt=rowData.m3;
        	if((fcwt==null)||(fcwt=="")){
        		fcwt="";
        	};
        	var picurl = '';
        	var url=rowData.m4;
        	if((url!=null)&&(url!="")&&(url!="undefined")){
        		var img=url.split("||");
        		picurl = '<a target="_blank" href="'+img[0]+'" id="picurl_'+rowData.id+'">'+img[1]+'</a>';
        	}
			return '<div id="ddv-' + rowIndex + '"><table><tr>' +
					'<td style="border:1px;width:700px " colspan="3" >' +
					'<input type="hidden" name="ccwt_' + rowData.id + '" value="' + rowData.m1 + '" />' +
					'<input type="hidden" name="ccpic_' + rowData.id + '" value="' + rowData.m2 + '" />' +
					'<input type="hidden" name="nrid" value="' + rowData.id + '" />' +
					'</td>' +
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'复查意见：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'<textarea name="fcwt_' + rowData.id + '" class="textarea" readonly="readonly" style="width: 100%;height: 80px;" >' +fcwt+'</textarea>'+
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'复查照片：' +
					'<span id="pict_'+rowData.id+'"></span>'+	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'<div>'+
					'<input type="hidden" name="fcpic_'+rowData.id+'" id="fcpic_'+rowData.id+'" value="'+rowData.m4+'" />'+
						picurl+
					'<div id="fileList_'+rowData.id+'" class="uploader-list" ></div>'+
					'</div>'+
					'</td>'+									
					'</tr>'+
					'</table></div>';    
		},
		onLoadSuccess : function() {
		},
 		onExpandRow: function(index,row){  
            $('#tt').datagrid('fixRowHeight',index);//防止出现滑动条  
 		},
 		onCollapseRow: function(index,row){  
            $('#tt').datagrid('fixRowHeight',index);//防止出现滑动条  
 		}
	});
});	
</script>
</body>
</html>