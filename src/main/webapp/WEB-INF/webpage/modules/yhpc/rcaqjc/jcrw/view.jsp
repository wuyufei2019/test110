<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查任务管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">任务名称：</label></td>
					<td class="width-30" colspan="3">${jcrw.m7}</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">部门：</label></td>
					<td class="width-30" >
						<c:choose>
							<c:when test="${jcrw.depname == '' || jcrw.depname == null}">
								全部
							</c:when>
							<c:otherwise>
								${jcrw.depname}
							</c:otherwise>
						</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">检查人：</label></td>
					<td class="width-30">${jcrw.jcr }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">计划检查时间：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd " value="${jcrw.m3 }" /></td>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30" >${jcrw.m5 }</td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-80" colspan="3" >
						<ul id="tt" style="width:400px" ></ul>
					</td>
				</tr>
												
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var flag=true;
$(function(){
	$('#tt').tree({
    	url: ctx+'/yhpc/jcrw/jcnr',
    	queryParams:{"lx":'${jcrw.m5}'},
    	checkbox: true,
    	loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
    	},
    	onLoadSuccess:function(){
			var m4=	'${jcrw.m4}';
			if(m4!=null&&m4!=""){
			var array = m4.split(",");
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