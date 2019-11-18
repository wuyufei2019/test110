<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查方案管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-35" colspan="3">${jcfa.m0 }</td>
				</tr>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-35" colspan="3">${jcfa.qyname }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						${jcfa.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35">${jcfa.m2 }</td>
					<td class="width-15 active"><label class="pull-right">所属行业：</label></td>
					<td class="width-35">${jcfa.m3}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35" colspan="3"><fmt:formatDate type="date"  value="${jcfa.m4 }" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">行政执法人员：</label></td>
					<td class="width-35" colspan="3">${jcfa.m5 }</td>								
				<tr>
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<ul id="tt" style="width:400px" ></ul>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查方式：</label></td>
					<td class="width-35" colspan="3">
						${jcfa.m7 }
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35" colspan="3" style="height:30px" >
						<c:choose>
						<c:when test="${jcfa.m8=='0'}">
							<input type="radio" value="1" class="i-checks" name="M8" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="M8" checked="checked" disabled />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M8" checked="checked" disabled />同意
							<input type="radio" value="0" class="i-checks"  name="M8" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><fmt:formatDate type="date"  value="${jcfa.m8_2 }" /></td>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">${jcfa.m8_1 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-35" colspan="3" style="height:30px">
						<c:choose>
						<c:when test="${jcfa.m9=='0'}">
							<input type="radio" value="1" class="i-checks" name="M9" disabled/>同意
							<input type="radio" value="0" class="i-checks"  name="M9" disabled checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M9" disabled checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="M9" disabled />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-35"><fmt:formatDate type="date"  value="${jcfa.m9_2 }" /></td>
					<td class="width-15 active"><label class="pull-right">审批人：</label></td>
					<td class="width-35">${jcfa.m9_1 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">
						${jcfa.m10 }
					</td>
				</tr>
												
				<c:if test="${not empty jcfa.id}">
					<input type="hidden" id="M6" name="M6" value="${jcfa.m6}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var flag=true;
$(function(){
	$('#tt').tree({
    	url: ctx+'/aqzf/jcfa/jcnr',
    	checkbox: true,
    	loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
    	},
    	onLoadSuccess:function(){
			var m6=	$("#M6").val();
			if(m6!=null&&m6!=""){
			var array = m6.split(",");
    		for(var i=0;i<array.length;i++)  
       		{  
         		var node = $('#tt').tree('find',array[i]);
         		$('#tt').tree('check',node.target);  
       		}  	
       		flag=false;
    	}
    },
    onBeforeCheck: function ()
    {
        if(flag == false){
            return false;
        }
    },
	});
});


</script>
</body>
</html>