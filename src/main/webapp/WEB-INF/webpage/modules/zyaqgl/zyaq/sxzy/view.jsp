<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间安全作业证信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" >${sxzy.m0 }</td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30" >${sxzy.sqr }</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" colspan="3">${sxzy.m1 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间所属单位：</label></td>
					<td class="width-30">${sxzy.m2 }</td>
					<td class="width-20 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-30">${sxzy.m3 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-30">${sxzy.m4 }</td>
					<td class="width-20 active"><label class="pull-right">空间内介质名称：</label></td>
					<td class="width-30">${sxzy.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m6 }" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m7 }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30" >${sxzy.zyfzr }</td>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-30" >${sxzy.zyr }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">监护人：</label></td>
					<td class="width-30" >${sxzy.jhr }</td>
					<td class="width-20 active"><label class="pull-right">rfid：</label></td>
					<td class="width-30" >${sxzy.m20 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="动火作业" disabled="disabled"/>动火作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="高处作业" disabled="disabled"/>高处作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="吊装作业" disabled="disabled"/>吊装作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="临时用电" disabled="disabled"/>临时用电</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="动土/断路作业" disabled="disabled"/>动土/断路作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="锁定" disabled="disabled"/>锁定</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="TSZY" style="margin-bottom: 6px;" value="盲板抽堵" disabled="disabled"/>盲板抽堵</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="易燃易爆物质" disabled="disabled"/>易燃易爆物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="坠落" disabled="disabled"/>坠落</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="腐蚀性物质" disabled="disabled"/>腐蚀性物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="蒸汽" disabled="disabled"/>蒸汽</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="高压气体/液体" disabled="disabled"/>高压气体/液体</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="有毒有害物质" disabled="disabled"/>有毒有害物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="高温/低温" disabled="disabled"/>高温/低温</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="触电" disabled="disabled"/>触电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="窒息" disabled="disabled"/>窒息</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="噪音" disabled="disabled"/>噪音</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="产生火花/静电" disabled="disabled"/>产生火花/静电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="旋转设备" disabled="disabled"/>旋转设备</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="机械伤害" disabled="disabled"/>机械伤害</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="粉尘" disabled="disabled"/>粉尘</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="不利天气" disabled="disabled"/>不利天气</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="淹没/埋没" disabled="disabled"/>淹没/埋没</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="辐射" disabled="disabled"/>辐射</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="交叉作业" disabled="disabled"/>交叉作业</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="WHBS" style="margin-bottom: 6px;" value="其他" disabled="disabled"/>其他</div>
					</td>
				</tr>
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析标准</strong></p><hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">有毒有害介质：</label></td>
					<td class="width-30" >${sxzy.m29 }</td>
					<td class="width-20 active"><label class="pull-right">可燃气：</label></td>
					<td class="width-30" >${sxzy.m30 }</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">氧含量：</label></td>
					<td class="width-30" >${sxzy.m31 }</td>
				</tr>
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析数据</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">分析人：</label></td>
					<td class="width-30" >${sxzy.fxr }</td>
				</tr>  
			</table>
			<table id="fxsj"></table> 
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30" >${sxzy.bzcsr }</td>
				</tr>  
			</table>
			<table id="aqcs"></table> 
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  	<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30" >${sxzy.ssjyr }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位意见：</label></td>
					<td class="width-30" colspan="3">${sxzy.m21 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位负责人：</label></td>
					<td class="width-30" >${sxzy.dwfzr }</td>
					<td class="width-20 active"><label class="pull-right">确认时间：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m22 }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批单位意见：</label></td>
					<td class="width-30" >${sxzy.m23 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批单位负责人：</label></td>
					<td class="width-30" >${sxzy.spr }</td>
					<td class="width-20 active"><label class="pull-right">审批时间：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m24 }" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">验收人：</label></td>
					<td class="width-30" >${sxzy.ysr }</td>
					<td class="width-20 active"><label class="pull-right">验收时间：</label></td>
					<td class="width-30" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sxzy.m25 }" /></td>
				</tr>
				</tbody>
			</table>
	</form>
<script type="text/javascript">
//涉及的其他特殊作业
var tszy = "${sxzy.m8}";
var tszyArr = tszy.split(",");
for(var i=0;i<tszyArr.length;i++){
	$("input[name='TSZY']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
}
//危害辨识
var whbs = "${sxzy.m9}";
var whbsArr = whbs.split(",");
for(var i=0;i<whbsArr.length;i++){
	$("input[name='WHBS']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
}

$(function(){
	var sxzyid='${sxzy.id}';
	var qrcsr='${sxzy.qrcsr}';
	var zt='${sxzy.zt}';
	dg=$('#fxsj').datagrid({    
		method: "post",
	    url:ctx+'/zyaqgl/sxzy/sxzyfxlist', 
	    queryParams:{'sxzyid':sxzyid},
	    fit : false,
		fitColumns : true,
		border : true,
		idField : 'id',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		striped:true,
	    columns:[[    
    			{field : 'm1',title : '有毒有害介质',sortable : false,width : 80,align : 'center'},
				{field : 'm2',title : '可燃气',sortable : false,width : 40,align : 'center'},
				{field : 'm3',title : '含氧量',sortable : false,width : 40,align : 'center'},
	        	{field : 'm4',title : '分析时间',sortable : false,width : 60,align : 'center',
					formatter : function(value, row, index) {
		              	if(value!=null&&value!='') {
		              		var datetime=new Date(value);
		              		return datetime.format('yyyy-MM-dd hh:mm:ss');
		              	}
		            }
	         	},
				{field : 'm5',title : '部位',sortable : false,width : 60,align : 'center'},
				{field : 'czr',title : '分析人',sortable : false,width : 40,align : 'center'}
	  			
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
	
	dg2=$('#aqcs').datagrid({    
		method: "post",
	    url:ctx+'/zyaqgl/sxzy/aqcslist?type=100'+'&id1='+sxzyid, 
	    fit : false,
		fitColumns : true,
		border : true,
		idField : 'id',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		singleSelect:true,
		checkOnSelect:false,
		selectOnCheck:false,
		striped:true,
	    columns:[[    
              	{field:'m1',title:'安全措施',width:100},
              	{field : 'z',title : '确认人',sortable : false,width : 20,align : 'center',
					formatter : function(value, row, index) {
		              	if(zt<4){
		              		return "未确认";
		              	}else{
		              		return qrcsr;
		              	}
		            }
	         	}   
	    ]],
	    onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    },
	    toolbar:''
	});
});
</script>
</body>
</html>