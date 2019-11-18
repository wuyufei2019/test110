<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div id="jl_infor">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						${jcjl.qyname}
					</td>
				</tr>
			  
				<tr>
					<td class="width-20 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-30" >
						<fmt:formatDate pattern="yyyy-MM-dd" value="${jcjl.m1 }" />
					</td>
					<td class="width-20 active"><label class="pull-right">责任部门：</label></td>
					<td class="width-30" >
						${jcjl.depname}
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">检察人：</label></td>
					<td class="width-30">
						${jcjl.jcr}
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty jcjl.m3}">
					 <c:forTokens items="${jcjl.m3}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="100px;" height="100px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-30" colspan="3">
						${jcjl.m5 }
					</td>
				</tr>
				</tbody>
			</table>
			<center style="font-size: 16px">检查内容</center>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>

				<div class="col-xs-9" id="content">
				</div>

				</tbody>
			</table>
 	</div>
 	<table id="tt" ></table>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
var target;
$(function(){
	var rwid= '${jcjl.id1}';
	var jlid= '${jcjl.id}';
		target=$('#tt').datagrid({
		method : "get",
		url:ctx+'/yhpc/rcjl/nrlist/'+rwid+'/'+jlid,
	    fit : true,
		fitColumns : true,
		showHeader:true,
		selectOnCheck:false,
		idField : 'ID',
		striped:true,
		pagination:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[
	        {field:'index',title:' ',align: 'center',
	  			formatter:function(val,row,index){
	  				var options = target.datagrid('getPager').data("pagination").options; 
	  			    var currentPage = options.pageNumber;
	  			    var pageSize = options.pageSize;
	  			    return value=(pageSize * (currentPage -1))+(index+1);
	  			}
	  		},      
			{field:'m3',title:'检查内容',width:150},
			{field:'11',title:'检查结果',width:50,
				formatter: function(value, rowData, rowIndex){
                	if(rowData.pic!=null&&rowData.pic!=""){
                		return "异常有隐患";
                	}else{
                		return "正常无隐患";
                	}
            	}
			},
			{field:'pic',title:'隐患照片',width:50,
				formatter : function(value, row, index) {
					var content="";
	              	if(value!=null&&value!='') {
	              		var arr1=value.split("||");
	                	for (var i = 0; i < arr1.length-1; i++) {
	                		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />';
	                	} 
	                	return content;
	              	}else{
	              		return '/';
	              	}
	            }
			},
			{field:'wtms',title:'问题描述',width:100}
		]],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			var row = target.datagrid('getSelected');
			if(row.id!=null)
			viewhidden();
		},		
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:'#jl_infor'
	});
});

//查看隐患整改情况
function viewhidden(){
	var row = target.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看隐患整改信息",ctx+"/yhpc/jcyh/view/"+row.id,"900px", "90%","");
}
</script>
</body>
</html>