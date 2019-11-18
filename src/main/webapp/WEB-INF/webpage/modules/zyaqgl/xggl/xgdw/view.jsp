<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关单位管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
		  <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-30" colspan="3">
						${xgdw.m2 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30">${xgdw.m1 }</td>
					<td class="width-20 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-30">
					${xgdw.m3}
					</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>法人代表：</label></td>
					<td class="width-28">${xgdw.m4 }</td>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系电话：</label></td>
					<td class="width-28">${xgdw.m5 }</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系人：</label></td>
					<td class="width-28">${xgdw.m6 }</td>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系电话：</label></td>
					<td class="width-28">${xgdw.m7 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经营范围：</label></td>
					<td class="width-80" colspan="3" height="80" >${xgdw.m10 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">营业执照：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m11}">
					 <c:forTokens items="${xgdw.m11}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">商务合同：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m15}">
					 <c:forTokens items="${xgdw.m15}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">安全合同：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m16}">
					 <c:forTokens items="${xgdw.m16}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">人员资质：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m17}">
					 <c:forTokens items="${xgdw.m17}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">安全协议：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m18}">
					 <c:forTokens items="${xgdw.m18}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m19}">
					 <c:forTokens items="${xgdw.m19}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">技术措施：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m20}">
					 <c:forTokens items="${xgdw.m20}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">相关培训资料：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m21}">
					 <c:forTokens items="${xgdw.m21}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>	
				
				<tr>
					<td class="width-15 active"><label class="pull-right">法人和安全人员证书：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty xgdw.m22}">
					 <c:forTokens items="${xgdw.m22}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>			
				<tbody>
		    </table>
      </form>
<!--       <div style="margin-bottom: 5px;">
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addZz()" title="添加资质"><i class="fa fa-plus"></i> 添加资质</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addTz()" title="添加特种人员"><i class="fa fa-plus"></i> 添加特种人员</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="delZzorTz()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updZzprTz()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
		</div> -->
		<div id="tt" class="easyui-tabs" data-options="fit:false,border:false"
			style="width:auto;height:300px;text-align: center;">
			<div title="资质信息" style="text-align: center;">
				<table id="aqjg_dsf_zz_dg"></table>
			</div>
			<div title="作业人员信息" style="text-align: center;">
				<table id="aqjg_dsf_tz_dg"></table>
			</div>
			<div title="服务项目信息" style="text-align: center;">
				<table id="aqjg_dsf_fw_dg"></table>
			</div>
		</div>
<script type="text/javascript">
var dwid=${xgdw.id };
$('#tt').tabs({
	onSelect : function(title, index) {
		if (title == "资质信息") { //资质信息
			zzlist();
		} else if (title == "作业人员信息") { //作业人员信息
			tzlist();
		} else if (title == "服务项目信息") { //服务项目信息
			fwlist();
		}
	}
});	
	
var zzdg;
var tzdg;
var fwdg;
var d;

function zzlist(){
	zzdg =$('#aqjg_dsf_zz_dg').datagrid({    
		method: "post",
		url:ctx+'/zyaqgl/zzxx/list?dwid='+dwid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[   
			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field:'m1',title:'资质证书名称',sortable:false,width:100},    
			{field:'m2',title:'级别',sortable:false,width:100,align:'center', 
				formatter : function(value, row, index){
					if(value=='一级') return value='一级';
					if(value=='二级') return value='二级';
					if(value=='三级') return value='三级';
					if(value=='四级') return value='四级';
					if(value=='五级') return value='五级';
					if(value=='六级') return value='六级';
				}
			},
			{field:'m3',title:'许可内容',sortable:false,width:100,align:'center'},
			{field:'m4',title:'有效期起',sortable:false,width:100,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');   
					}  
	            }
			},
			{field:'m5',title:'有效期止',sortable:false,width:100,align:'center',
				styler: function(value, row, index){
					var nowhm=(new Date()).getTime();
					if(nowhm>=value){
						return 'background-color:rgb(249, 156, 140);';
					}else{
						var cha=(value-nowhm)/1000/60/60/24;
						if(cha<=90) return 'background-color:rgb(255, 228, 141);';
					}
				},   
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');   
					}  
	            }
			}
		]],	
		onDblClickRow : function(index, row) {
				view(1);
		},
		checkOnSelect:false,
		selectOnCheck:false,
	});
}

function tzlist(){
	tzdg =$('#aqjg_dsf_tz_dg').datagrid({    
		method: "post",
		url:ctx+'/zyaqgl/tzzyry/list?dwid='+dwid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[    
		    {field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
			{field:'m1',title:'姓名',sortable:false,width:50},    
			{field:'m2',title:'性别',sortable:false,width:30,align:'center',
				formatter : function(value, row, index){
					if(value=='0') return value='男';
					if(value=='1') return value='女';}
			},
			{field:'m8',title:'类别',sortable:false,width:70,align:'center'},
			{field:'m3',title:'操作证号',sortable:false,width:100,align:'center'},
			{field:'m4',title:'身份证号',sortable:false,width:100,align:'center'},
			{field:'m5',title:'作业类型',sortable:false,width:70,align:'center'},
			{field:'m6',title:'到期日期',sortable:false,width:100,align:'center',
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');  
					}  
	            },styler: function(value, row, index){
					var nowhm=(new Date()).getTime();
					if(nowhm>=value){
						return 'background-color:rgb(249, 156, 140);';
					}else{
						var cha=(value-nowhm)/1000/60/60/24;
						if(cha<=90) return 'background-color:rgb(255, 228, 141);';
					}
				}
			},
			{field:'m7',title:'备注',sortable:false,width:100,align:'center'}
			]],
		onDblClickRow : function(index, row) {
			view(2);
		},
		checkOnSelect:false,
		selectOnCheck:false
	});
}

function fwlist(){
	fwdg =$('#aqjg_dsf_fw_dg').datagrid({    
		method: "post",
		url:ctx+'/zyaqgl/fwxm/list?dwid='+dwid, 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		scrollbarSize:5,
		singleSelect:true,
		striped:true,
		columns:[[   
			{field:'ID', title:'id',checkbox : true,width : 50,align : 'center'},
			{field:'m1', title:'项目类型',sortable : false,width : 100},
			{field:'m2', title:'项目名称',sortable : false,width : 100},
			{field:'m3', title:'服务项目内容',sortable : false,width : 100},
			{field:'m4', title:'项目负责人',sortable : false,width : 100},
			{field:'m5', title:'项目合同资金',sortable : false,width : 100},
			{field:'m6', title:'开始时间',sortable : false,width : 100,
					formatter:function(value,row,index){
				if(value!=null){
					var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');   
				}  
			}},
			{field:'m7', title:'结束时间',sortable : false,	width : 100,
				formatter:function(value,row,index){
					if(value!=null){
						var datetime = new Date(value);  
						 return datetime.format('yyyy-MM-dd');  
					}  
				}
			}  
		]],	
		onDblClickRow : function(index, row) {
			view(3);
		},
		checkOnSelect:false,
		selectOnCheck:false,
	});
}

//查看
function view(flag){
	var viewurl="";
	var title="";
	var row="";
	if(flag==1){
		row = zzdg.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/zzxx/view/";
		title="查看资质信息"
	}else if(flag==2){
		row = tzdg.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/tzzyry/view/";
		title="查看特种作业人员信息"
	}else if(flag==3){
		row = fwdg.datagrid('getSelected');
		viewurl=ctx+"/zyaqgl/fwxm/view/";
		title="查看服务项目信息"
	}
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView(title,viewurl+row.id,"700px", "350px","");
	
}
</script>
</body>
</html>