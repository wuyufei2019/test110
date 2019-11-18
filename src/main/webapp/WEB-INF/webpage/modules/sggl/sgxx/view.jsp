<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">事故编号：</label></td>
					<td class="width-35" colspan="3">
						${res.m1 }
					</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">事故发生时间：</label></td>
					<td class="width-35">
						<fmt:formatDate value="${res.m5 }" type="both" />
					</td>
					<td class="width-15 active"><label class="pull-right">事故名称：</label></td>
					<td class="width-35">
						${res.m2 }
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-35">
						<input style="width: 100%;height: 30px;" class="easyui-combobox" value="${res.m23 }"  data-options="readonly:true,valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-35">
						${res.m6 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故类型：</label></td>
					<td class="width-35">${res.m3 }</td>
					<td class="width-15 active"><label class="pull-right">事故等级：</label></td>
					<td class="width-35">${res.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">经济损失（万元）：</label></td>
					<td class="width-35">
						${res.m11 }
					</td>
					<td class="width-15 active"><label class="pull-right">事故性质：</label></td>
					<td class="width-35">
						${res.m7 }
					</td>
				</tr>


				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故经过：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m13 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">救援情况：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m14 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故教训：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m15 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故原因分析：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m16 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">事故预防措施：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m17 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故责任人处理：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m18 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">相关人员教育情况：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m19 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">${res.m20 }</td>
				</tr>												

				<tr>
					<td class="width-15 active"><label class="pull-right">事故现场照片/视频：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty res.m21}">
					 <c:forTokens items="${res.m21}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">事故调查报告：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty res.m22}">
					 <c:forTokens items="${res.m22}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20" colspan="4" style="text-align:center">
						<label>伤亡人员</label>
					</td>
				</tr>
				
         		<div id="bdnr">
               		<table id="fxgk_bdnr_dg" ></table>
           		</div>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var sgid='${res.ID}';
var Address = [{ "value": "1", "text": "轻伤" }, { "value": "2", "text": "重伤" }, { "value": "3", "text": "死亡" }];
function unitformatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
 
    for (var i = 0; i < Address.length; i++) {
        if (Address[i].value == value) {
            return Address[i].text;
        }
    }
}
dg=$('#fxgk_bdnr_dg').datagrid({    
	method: "post",
    url:ctx+'/sggl/sgxx/swrylist?sgid='+sgid,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},      
        {field:'m1',title:'姓名',width:40},    
        {field:'m3',title:'性别',width:40,align:'center'},
        {field:'gw',title:'岗位',width:60,align:'center'},
        {field:'m5',title:'学历',width:40,align:'center'},
        {field:'m9',title:'联系方式',sortable:false,width:70,align:'center'},
		{field:'shcd',title:'伤害程度',width: 70, formatter: unitformatter, align: 'center', editor: { type: 'combobox', options: {editable:false, data: Address, valueField: "value", textField: "text", panelHeight:'auto' } } }
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
	});	 	
</script>
</body>
</html>