<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>员工管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<div class="easyui-tabs" fit="true">
		<div title="基本信息" style="padding-top: 5px;" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
					<tr>
						<td class="width-15 active"><label class="pull-right">员工状态：</label></td>
						<td class="width-35" >
						<c:if test="${yglist.status eq 1}">
							正常
							</td>
						</c:if> 
						<c:if test="${yglist.status eq 2}">
							离职
							</td>
							<td class="width-15 active"><label class="pull-right">离职日期：</label></td>
							<td class="width-35"><fmt:formatDate value="${yglist.m15 }" pattern="yyyy-MM-dd"  /></td>
						</c:if>     
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">工号：</label></td>
						<td class="width-35" >${yglist.m2 }</td>
						<td class="width-15 active"><label class="pull-right">姓名：</label></td>
						<td class="width-35">${yglist.m1 }</td>
					</tr>				
					<tr>
						<td class="width-15 active"><label class="pull-right">性别：</label></td>
						<td class="width-35">${yglist.m3 }</td>
						<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
						<td class="width-35">${yglist.m8 }</td>
					</tr>
					<tr>
	                    <td class="width-15 active"><label class="pull-right">人员类别：</label></td>
	                    <td class="width-35">${yglist.m16 }</td>
						<td class="width-15 active"><label class="pull-right">出生日期：</label></td>
						<td class="width-35"><fmt:formatDate value="${yglist.m10 }" pattern="yyyy-MM-dd"  /></td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">到职日期：</label></td>
						<td class="width-35"><fmt:formatDate value="${yglist.m14 }" pattern="yyyy-MM-dd"  /></td>
						<td class="width-15 active"><label class="pull-right">婚姻状况：</label></td>
						<td class="width-35">${yglist.m11 }</td>				
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">部门：</label></td>
						<td class="width-35">${yglist.bmmc }</td>
						<td class="width-15 active"><label class="pull-right">职位：</label></td>
						<td class="width-35">${yglist.m4 }</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">职务：</label></td>
						<td class="width-35">${yglist.m7 }</td>
						<td class="width-15 active"><label class="pull-right">学历：</label></td>
						<td class="width-35">${yglist.m5 }</td>				
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">贯籍：</label></td>
						<td class="width-35 ">${yglist.m12 }</td>
						<td class="width-15 active"><label class="pull-right">民族：</label></td>
						<td class="width-35">${yglist.m13 }</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">专业：</label></td>
						<td class="width-35">${yglist.m6 }</td>
						<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
						<td class="width-35">${yglist.m9 }</td>					
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">二维码：</label></td>
						<td class="width-85" colspan="3"><img id="imgurl" alt="" src="" width="100px;" height="100px;"></td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">责任区域：</label></td>
						<td class="width-85" colspan="3">${yglist.m18 }</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">照片：</label></td>
						<td class="width-85" colspan="3">
						 <c:if test="${not empty yglist.m19}">
						 <c:forTokens items="${yglist.m19}" delims="," var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" style="max-height: 100px;"/><br/>${urlna[1]}</a>
						 	</div>
						 </c:forTokens>
						 </c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div title="体检信息" style="padding-top: 5px;" >
			<table id="tjxx_dg"></table>
		</div>
		<div title="学习记录" style="padding-top: 5px;" >
			<table id="xxxx_dg"></table>
		</div>
		<div title="考试记录" style="padding-top: 5px;" >
			<table id="ksxx_dg"></table>
		</div>
		<div title="三级教育培训信息" style="padding-top: 5px;" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			    <tbody>
			  	<tr >
			  		<td class="width-15 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-35"> ${yg.m1} </td>  
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35"> ${yg.m3 } </td>
				</tr>
				<tr >
			  		<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"> ${yg.m8} </td>  
					<td class="width-15 active"><label class="pull-right">出生日期：</label></td>
					<td class="width-35"><fmt:formatDate value="${yg.m10 }" pattern="yyyy-MM-dd"  />  </td>
				</tr>
				<tr>
			  		<td class="width-15 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-35"> ${dep.m1} </td>  
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35"> ${sjjy.m1 } </td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">培训时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${sjjy.m2 }" pattern="yyyy-MM-dd"  /></td>
					<td class="width-20 active"><label class="pull-right">考试成绩：</label></td>
					<td class="width-30">${sjjy.m7 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">培训结果：</label></td>
					<td class="width-30">${sjjy.m8 }</td>
					<td class="width-20 active"><label class="pull-right">厂级教育人：</label></td>
					<td class="width-30">${sjjy.m4 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">车间教育人：</label></td>
					<td class="width-30">${sjjy.m5 }</td>
					<td class="width-20 active"><label class="pull-right">班组教育人：</label></td>
					<td class="width-30">${sjjy.m6 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">培训内容：</label></td>
					<td class="width-80" colspan="3">${sjjy.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">${sjjy.m9 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty sjjy.m10}">
					 <c:forTokens items="${sjjy.m10}" delims="," var="url" varStatus="urls">
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
	</div>
<script type="text/javascript">
if('${imgurl}'!='/download/'){
	var imgurl = ctx + '${imgurl}';
	$('#imgurl').attr('src',imgurl);
}

$(function(){
	var sfz = '${yglist.m8}'; 	
	if(sfz==''||sfz==null||sfz==undefined){
		sfz = -1;
	}
	dg=$('#tjxx_dg').datagrid({    
			nowrap:false,
			method: "post",
		    url:ctx+'/bis/ygtjxx/ygtjlist/'+sfz, 
		    fit : true,
			fitColumns : true,
			border : false,
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
			checkOnSelect:false,
			selectOnCheck:false,
		    columns:[[    
				{field:'m7',title:'员工姓名',width:80},
				{field:'m1',title:'身份证号',width:80,align:'center'},
				{field:'m2',title:'体检类型',width:80,align:'center'},
				{field:'m3',title:'体检日期',width:80,align:'center',
					  formatter : function(value, row, index) {
				    	if(value!='') {
				    		var datetime=new Date(value);
				    		 return datetime.format('yyyy-MM-dd');  
				    	}
					} 
				},
				{field:'m4',title:'体检医院',width:110,align:'center'}
		    ]],
		    enableHeaderClickMenu: true,
		    enableRowContextMenu: false
		});
	
	var userid = '${yglist.id1}'; 	
	if(userid==''||userid==null||userid==undefined){
		userid = -1;
	}
	console.log(userid);
	$('#xxxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqpx/aqpxjl/xxlistyg/'+userid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[    
	        {field:'kc',title:'课程名称',sortable:false,width:80 },    
	        {field:'m2',title:'开始时间',sortable:false,width:100,align:'center',
	        	formatter:function(value,row,index){
	     		if(value!=null){
	                var datetime = new Date(value);  
	                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
	                }},
	        {field:'m3',title:'结束时间',sortable:false,width:80,align:'center',
	                	formatter:function(value,row,index){
	     		if(value!=null){
	                var datetime = new Date(value);  
	                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
	                }},    
	        
	        {field:'m1',title:'学习时长(分钟)',sortable:false,width:100,align:'center',
	        	formatter:function(value,row,index){
	     		if(value!=null){
	                return parseInt(value/60)+":"+value%60;  }  
	                }}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	});
	
	$('#ksxx_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/aqpx/aqpxjl/kslistyg/'+userid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [20, 50, 100, 150, 200, 250 ],
		singleSelect:true,
	    columns:[[    
	        {field:'kc',title:'课程名称',sortable:false,width:80 },    
	        {field:'s1',title:'考试时间',sortable:false,width:100,align:'center',
	        	formatter:function(value,row,index){
	     		if(value!=null){
	                var datetime = new Date(value);  
	                return datetime.format('yyyy-MM-dd hh:mm:ss');  }  
	                }
	        },
	        {field:'m1',title:'考试成绩',sortable:false,width:80,align:'center'},    
	        {field:'m3',title:'考试结果',sortable:false,width:80,align:'center'},
	        {field:'m2',title:'用时(分钟)',sortable:false,width:100,align:'center',
	        	formatter:function(value,row,index){
	     		if(value!=null){
	                return parseInt(value/60)+":"+value%60;  }  
	                }},
	        {field:'h',title:'试卷标识',sortable:false,width:100,align:'center'}
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    	viewsj();
	    },
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false,
	});
});
</script>
</body>
</html>