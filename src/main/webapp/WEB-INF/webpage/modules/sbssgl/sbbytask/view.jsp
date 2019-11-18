<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备保养计划</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">${sbbytask.qyname }</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">计划标题：</label></td>
					<td class="width-80" colspan="3">${sbbytask.m1 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">年份（年）：</label></td>
					<td class="width-30">${sbbytask.m2 }</td>
				   	<td class="width-20 active"><label class="pull-right">计划类型：</label></td>
					<td class="width-30">
						<c:choose>
							<c:when test="${sbbytask.m3 == '1'}">
								月度
							</c:when>
							<c:when test="${sbbytask.m3 == '2'}">
								季度
							</c:when>
							<c:when test="${sbbytask.m3 == '3'}">
								半年度
							</c:when>
							<c:otherwise>
								年度
							</c:otherwise>
						</c:choose>
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-30" >
						<c:choose>
							<c:when test="${sbbytask.m5 == '0'}">A类</c:when>
							<c:when test="${sbbytask.m5 == '1'}">B类</c:when>
							<c:when test="${sbbytask.m5 == '2'}">C类</c:when>
							<c:otherwise>
								${sbbytask.m5}
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">保养计划模板：</label></td>
					<td class="width-80" colspan="3">
						<c:if test="${not empty sbbytask.m4}">
							<c:set var="url" value="${fn:split(sbbytask.m4, '||')}" />
							<div  style="margin-bottom: 10px;">
							<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
							</div>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<table id="sbssgl_sbby_dg" style="height: 300px;"></table>
	</form>
<script type="text/javascript">
var taskid = '${sbbytask.id }';
var dg;
$(function(){
	dg=$('#sbssgl_sbby_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbby/sbbylist/'+taskid,
    fit : false,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
		{field:'id',title:'id',checkbox:true,width:50,align:'center'},   
		{field:'m1',title:'执行保养期限',sortable:false,width:40,align : 'center',
			formatter : function(value, row, index) {
		    	if(row.jhlx == '1'){//月度
		    		return value+'月';
		    	}else if(row.jhlx == '2'){//季度
		    		return '第'+value+'季度';
		    	}else if(row.jhlx == '3'){//半年度
		    		if(value == '1'){
		    			return '上半年度';
		    		}else if(value == '2'){
		    			return '下半年度';
		    		}
		    	}else if(row.jhlx == '3'){//年度
		    		return '全年';
		    	}
			} 
		},
		{field:'sbbh',title:'设备编号',sortable:false,width:40,align:'center'},
		{field:'sbname',title:'设备名称',sortable:false,width:60,align:'center'},
		{field:'m2',title:'状态',sortable:false,width:40,align:'center',
			formatter : function(value, row, index) {
		    	if(value == '1'){
		    		return '已上传附件';
		    	}else{
		    		return '未上传附件';
		    	}
			} 
		},
		{field:'cz',title:'操作',sortable:false,width:40,align:'center',
			formatter : function(value, row, index) {
				if(row.m2 == '1'){
					return "<a style='margin:2px' class='btn btn-success btn-xs' onclick=window.open(\""+row.m3.split("||")[0]+"\")>下载附件</a>";
				}
			} 
		}
    ]],
    onLoadSuccess: function(){
    	$(this).datagrid("autoMergeCells", [ 'm1' ]);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'sbssgl_sbby_tb'
	});
	
});
</script>
</body>
</html>