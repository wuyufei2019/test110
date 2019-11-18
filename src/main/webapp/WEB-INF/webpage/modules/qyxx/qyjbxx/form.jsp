<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
<script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
uploadImgFlag=false;//是否上传图片
	
function doSubmit(){
	if(uploadImgFlag==false){
		layer.open({title: '提示',offset: 'auto',content: '未上传企业平面图，请上传！',shade: 0 ,time: 2000 });
		return;
	}
 	$("#inputForm").submit(); 
}
$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		$("#sheng").attr("disabled",false);
				$("#shi").attr("disabled",false);
				$("#xian").attr("disabled",false);
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
</script>
<style type="text/css">
	.BMap_cpyCtrl{ display:none; }  
	.anchorBL{ display:none;}   
</style>
</head>
<body>
	<div>
			<form id="inputForm" action="${ctx}/bis/qyjbxx/${action}" class="form-horizontal" method="post">
				<input type="hidden" name="ID" value="${qylist.ID}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID1" value="${qylist.ID1}" />
				<input type="hidden" name="cjz" value="${qylist.cjz}" />
				<input type="hidden" name="fid" value="${qylist.fid}" />
				<input type="hidden" name="isbloc" value="${qylist.isbloc}" />
				<input type="hidden" name="qylogo" value="${qylist.qylogo}" />
				<table class="table table-bordered  dataTables-example dataTable no-footer" style="margin:0 auto;width:900px;">
					
					<tbody>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>企业名称：</label></td>
							<td colspan="3"><input id="qyname_m1" name="M1" class="easyui-textbox" value="${qylist.m1 }" style="width:658px;height:30px;"
								data-options="required:'true' " /></td>
						</tr>
						  
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>是否构成重大危险源：</label></td>
							<td class="width-28"><input id="bis_qy_id_table_td_input_m39" name="M39"
								class="easyui-combobox" value="${qylist.m39 }"
								style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'}],
								        onSelect: function(rec){
								        	if(rec.value=='0'){
								        		 $('#bis_qy_id_table_td_m40').combobox({ required:false });
								        		 $('#bis_qy_id_table_td_m40_td1').hide();
								        		 $('#bis_qy_id_table_td_m40_td2').hide();
								        	}
								        	if(rec.value=='1'){
								        		$('#bis_qy_id_table_td_m40').combobox({ required:true });
								        		$('#bis_qy_id_table_td_m40_td1').show();
								        		$('#bis_qy_id_table_td_m40_td2').show();
								        	}
								        }
						    " /></td>
							<td id="bis_qy_id_table_td_m40_td1" class="width-22 active"><label class="pull-right" ><font color="red">*</font>重大危险源等级：</label></td>
							<td class="width-28" id="bis_qy_id_table_td_m40_td2"><input
								id="bis_qy_id_table_td_m40" name="M40" class="easyui-combobox"
								value="${qylist.m40 }" style="width:220px;height:30px;"
								data-options="
							panelHeight:100,editable:false, required:'true',data: [
										{value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'}]
						    " /></td>
						</tr>
						<!--
						<tr>
							<td class="width-22 active"><label class="pull-right">物料风险等级：</label></td>
							<td class="width-28"><input name="M41" class="easyui-combobox"
								value="${qylist.m41 }" style="width:220px;height:30px;"
								data-options="
					panelHeight:80,editable:false ,data: [
										{value:'A',text:'A'},
								        {value:'B',text:'B'},
								        {value:'C',text:'C'}]
						    " /></td>
							<td class="width-22 active"><label class="pull-right">工艺风险等级：</label></td>
							<td class="width-28"><input name="M42" class="easyui-combobox"
								value="${qylist.m42 }" style="width:220px;height:30px;"
								data-options="
					panelHeight:80,editable:false ,data: [
										{value:'A',text:'A'},
								        {value:'B',text:'B'},
								        {value:'C',text:'C'}]
						    " /></td>
						</tr>-->
						<tr>
						<!--  
							<td class="width-22 active"><label class="pull-right">物料储量风险等级：</label></td>
							<td class="width-28"><input name="M43" class="easyui-combobox"
								value="${qylist.m43 }" style="width:220px;height:30px;"
								data-options="
					panelHeight:80,editable:false ,data: [
										{value:'A',text:'A'},
								        {value:'B',text:'B'},
								        {value:'C',text:'C'}]
						    " /></td>-->
							<td class="width-22 active"><label class="pull-right">企业风险等级：</label></td>
							<td class="width-28"><input name="qyfxdjxs" class="easyui-combobox"
								value="${qylist.m44 }" style="width:220px;height:30px;"
								data-options=" 
					panelHeight:80,editable:false ,disabled:true,data: [
										{value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'}]
						    " /></td>
													<td class="width-22 active"><label class="pull-right">规模情况：</label></td>
							<td class="width-28"><input  name="M34" class="easyui-combobox"
								value="${qylist.m34 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,data: [
										{value:'1',text:'规上'},
										{value:'2',text:'规下'}] " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">安全监管等级：</label></td>
							<td class="width-28"><input name="M36_1" class="easyui-combobox"
								value="${qylist.m36_1 }" style="width:220px;height:30px;"
								data-options="
					panelHeight:120,editable:false ,data: [
										{value:'1',text:'A'},
								        {value:'2',text:'B'},
								        {value:'3',text:'C'},
								        {value:'4',text:'D'},
								        {value:'5',text:'未定级'}]
						    " /></td>
							<td class="width-22 active"><label class="pull-right">属地安监机构：</label></td>
							<td class="width-28"><input id="M8_6" name="M8_6" style="width:220px;height:30px;" class="easyui-textbox" value="${qylist.m8_6 }"
								data-options="validType:'length[1,100]' " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">监管分类：</label></td>
							<td class="width-28"><input id="M36" class="easyui-combobox" style="width:220px;height:30px;" name="M36" value="${qylist.m36 }" data-options="required:'true',panelHeight:'auto' ,data:[{value:'1',text:'工贸'},{value:'2',text:'化工'},{value:'9',text:'其他'}]"/></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>经济类型：</label></td>
							<td class="width-28"><input id="bis_qy_m10" class="easyui-combobox"
								name="M10" value="${qylist.m10 }" style="width:220px;height:30px;"
								data-options="panelHeight:200,editable:false ,valueField: 'id',required:'true',textField: 'text',url:'${ctx}/tcode/dict/jjlx' " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">行业类别：</label></td>
							<td class="width-28"><input id="M12" class="easyui-combotree" name="M12" value="${qylist.m12 }"
								style="width: 220px;height:30px;"data-options=" method: 'get',url:'${ctx}/tcode/gbt/json'"/></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>企业行政隶属关系：</label></td>
							<td class="width-28"><input name="M13" class="easyui-combobox"
								value="${qylist.m13 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:100,editable:false ,required:'true',data: [
										{value:'1',text:'中央级'},
										{value:'2',text:'省级'},
										{value:'3',text:'市、地区级'},
										{value:'4',text:'县级'},
										{value:'5',text:'街道、镇、乡级'},
										{value:'6',text:'街道'},
										{value:'7',text:'镇'},
										{value:'8',text:'乡'},
										{value:'9',text:'其他'}] " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>企业状态：</label></td>
							<td class="width-28"><input name="M15" class="easyui-combobox"
								value="${qylist.m15 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',data: [
										{value:'1',text:'有效'},
										{value:'2',text:'无效'}] " /></td>
							<td class="width-22 active"><label class="pull-right">标准化达标等级：</label></td>
							<td class="width-28"><input name="M18" class="easyui-combobox"
								value="${qylist.m18 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:100,editable:false ,data: [
										{value:'1',text:'一级'},
										{value:'2',text:'二级'},
										{value:'3',text:'三级'},
										{value:'4',text:'达标'},
										{value:'5',text:'未创建'}] " /></td>
						</tr>
						<tr>

							<!--  
							<td class="width-22 active"><label class="pull-right">企业规模：</label></td>
							<td class="width-28"><input name="M35" class="easyui-combobox"
								value="${qylist.m35 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:100,editable:false ,data: [
										{value:'1',text:'微型'},
										{value:'2',text:'小型'},
										{value:'3',text:'中型'},
										{value:'4',text:'大型'}] " /></td>
							-->
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>安全机构设置情况：</label></td>
							<td colspan="3"><input id="M27" name="M27" class="easyui-combobox"
								value="${qylist.m27 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',required:'true',data: [
										{value:'1',text:'是'},
										{value:'2',text:'否'}],
										 onLoadSuccess:function(){ 
								        if (action == 'create') {
								        	$('#M27').combobox('setValue', '1');
								        	}
								        }
										 " /><label
								style="color: red;">（企业内部( 是/否 )有专门设置负责安全生产管理的部门）</label></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>是否涉及重点监管危化品：</label></td>
							<td class="width-28"><input name="M47" class="easyui-combobox"
								value="${qylist.m47 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',data: [
										{value:'1',text:'是'},
										{value:'0',text:'否'}] " /></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>是否涉及高危工艺：</label></td>
							<td class="width-28"><input name="M48" class="easyui-combobox"
								value="${qylist.m48 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',data: [
										{value:'1',text:'是'},
										{value:'0',text:'否'}] " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>是否涉及剧毒品生产或使用：</label></td>
							<td class="width-28"><input name="M49" class="easyui-combobox"
								value="${qylist.m49 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,required:'true',data: [
										{value:'1',text:'是'},
										{value:'0',text:'否'}] " /></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>营业执照类别：</label></td>
							<td class="width-28"><input name="M2_1" class="easyui-combobox"
								value="${qylist.m2_1 }" style="width:220px;height:30px;"
								data-options="
					panelHeight:200,editable:false ,required:'true',data: [
										{value:'01',text:'企业法人营业执照'},
								        {value:'02',text:'营业执照'},
								        {value:'03',text:'个体工商户营业执照'},
								        {value:'04',text:'个人独资企业营业执照'},
								        {value:'05',text:'个人独资企业分支机构营业执照'},
								        {value:'06',text:'合伙企业营业执照'},
								        {value:'07',text:'合伙企业分支机构营业执照'},
								        {value:'99',text:'其他'}]
						    " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">是否位于化工集中区：</label></td>
							<td class="width-28"><input id="M46" name="M46" class="easyui-combobox"
								value="${qylist.m46 }" style="width:220px;height:30px;"
								data-options="
								panelHeight:50,editable:false ,data: [
										{value:'1',text:'是'},
										{value:'0',text:'否'}],
										 onLoadSuccess:function(){ 
								        if (action == 'create') {
								        	$('#M46').combobox('setValue', '1');
								        	}
								        },
										onSelect: function(rec){
								        	if(rec.value=='0'){
								        		 $('#M46_1').validatebox({ required:false });
								        		 $('#M46_1').val('');
								        		 $('#m46_1_td1').hide();
								        		 $('#m46_1_td2').hide();
								        	}
								        	if(rec.value=='1'){
								        		$('#M46_1').validatebox({ required:true });
								        		$('#m46_1_td1').show();
								        		$('#m46_1_td2').show();
								        	}
								        } " /></td>
							<td id="m46_1_td1" class="width-22 active"><label class="pull-right">化工集中区名称：</label></td>
							<td id="m46_1_td2"><input id="M46_1" name="M46_1"
								class="easyui-textbox easyui-validatebox" style="width:220px;height:30px;" value="${qylist.m46_1 }"
								data-options="validType:'length[1,100]' " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">母公司名称：</label></td>
							<td colspan="3"><input name="M1_1"
								class="easyui-textbox " value="${qylist.m1_1 }"
								style="width:658px;height:30px;" data-options="validType:'length[1,100]'" /></td>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">集团公司名称：</label></td>
							<td colspan="3"><input name="M1_2"
								class="easyui-textbox " value="${qylist.m1_2 }"
								style="width:658px;height:30px;" data-options="validType:'length[1,100]'" /></td>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">注册资金(万元)：</label></td>
							<td class="width-28"><input name="M8_1" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m8_1 }" data-options="validType:'mone'" /></td>
							<td class="width-22 active"><label class="pull-right">年销售收入(万元)：</label></td>
							<td class="width-28"><input name="M8_2" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m8_2 }" data-options="validType:'mone'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">年利润(万元)：</label></td>
							<td class="width-28"><input name="M8_3" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m8_3 }"
								data-options="validType:'number'" /></td>
							<td class="width-22 active"><label class="pull-right">资产总额（万元）：</label></td>
							<td class="width-28"><input name="M8_4" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m8_4 }"
								data-options="validType:'mone'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">注册地址：</label></td>
							<td colspan="3"><input name="M8" class="easyui-textbox"
								value="${qylist.m8 }" style="width:658px;height:30px;"
								data-options="validType:'length[1,100]'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">统一社会信用代码：</label></td>
							<td class="width-28"><input name="M3_1" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m3_1 }" data-options="validType:'length[1,100]'" /></td>
							<td class="width-22 active"><label class="pull-right">占地面积（㎡）：</label></td>
							<td class="width-28"><input name="M8_5" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m8_5 }" data-options="validType:'mone'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">企业实际生产/经营地址：</label></td>
							<td colspan="3"><input name="M33" class="easyui-textbox easyui-validatebox"
								value="${qylist.m33 }" style="width:658px;height:30px;"
								data-options="validType:'length[1,500]'" /></td>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">企业官方网站地址：</label></td>
							<td colspan="3"><input name="M33_1" class="easyui-textbox" value="${qylist.m33_1 }"
								style="width:220px;height:30px;" data-options="validType:'length[1,500]'" /></td>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">成立时间：</label></td>
							<td class="width-28"><input name="M4" class="easyui-datebox"
								value="<fmt:formatDate value="${qylist.m4}"/>"
								style="width:220px;height:30px;" data-options="editable:false" /></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>法定代表人：</label></td>
							<td class="width-28"><input name="M5" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m5 }" data-options="required:'true',validType:'length[1,100]'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>行政区域：</label></td>
							<td colspan="3"><div id="bis_qy_province">								
									<select style="width: 110px;" id="sheng" name="M11" class="form-control" > </select> 
									<label style="margin-left: 10px;" for="M11">省</label>	
									<select style="margin-left: 10px;width: 110px;" id="shi" name="M11_1" class="form-control"  > </select>
									<label style="margin-left: 10px;" for="M11_1">市</label>
									<select style="margin-left: 10px;width: 110px;" id="xian" name="M11_2" class="form-control" > </select>
									<label style="margin-left: 10px;" for="M11_2">区</label>
									<select style="margin-left: 10px;width: 130px;" id="xiang" name="M11_3" class="form-control" data-options="required:'true'"> </select>
									<label style="margin-left: 10px;" for="M11_3">镇或街道</label>
								</div></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>联系人：</label></td>
							<td class="width-28"><input name="M6_1" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m6_1 }" data-options="required:'true',validType:'length[1,100]' " /></td>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>联系电话：</label></td>
							<td class="width-28"><input name="M6" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m6 }" data-options="required:'true',validType:'mobileAndTel' " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">联系微信号：</label></td>
							<td class="width-28"><input name="M6_2" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m6_2 }" data-options="validType:'length[1,100]' " /></td>
							<td class="width-22 active"><label class="pull-right">联系QQ号：</label></td>
							<td class="width-28"><input name="M6_3" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m6_3 }" data-options="validType:'length[1,100]' " /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">电子邮件：</label></td>
							<td class="width-28"><input name="M7" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m7 }" data-options="validType:'email' " /></td>
							<td class="width-22 active"><label class="pull-right">单位传真：</label></td>
							<td class="width-28"><input name="M33_2" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m33_2 }" data-options="validType:'length[1,200]'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>企业位置：</label></td>
							<td colspan="3" style="height:30px;line-height:30px;">
								<label>经度：</label>
								<input id="bis_map_c_x" name="M16" value="${qylist.m16 }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:150px;height:30px;"/>
								<label>纬度：</label> 
								<input id="bis_map_c_y" name="M17" value="${qylist.m17 }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:150px;height:30px;"/>
								<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td>
						</tr>

						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>主要负责人：</label></td>
							<td class="width-28"><input name="M19" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m19 }" data-options="required:'true',validType:'length[1,100]'" /></td>
							<td class="width-22 active"><label class="pull-right">主要负责人固定电话：</label></td>
							<td class="width-28"><input name="M20" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m20 }" data-options="validType:'tel'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>主要负责人移动电话：</label></td>
							<td class="width-28"><input name="M21" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m21 }" data-options="required:'true',validType:'mobile'" /></td>
							<td class="width-22 active"><label class="pull-right">主要负责人电子邮箱：</label></td>
							<td class="width-28"><input name="M22" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m22 }" data-options="validType:'email'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>安全负责人：</label></td>
							<td class="width-28"><input name="M23" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m23 }" data-options="required:'true',validType:'length[1,100]'" /></td>
							<td class="width-22 active"><label class="pull-right">安全负责人固定电话：</label></td>
							<td class="width-28"><input name="M24" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m24 }" data-options="validType:'tel'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right"><font color="red">*</font>安全负责人移动电话：</label></td>
							<td class="width-28"><input name="M25" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m25 }" data-options="required:'true',validType:'mobile'" /></td>
							<td class="width-22 active"><label class="pull-right">安全负责人电子邮箱：</label></td>
							<td class="width-28"><input name="M26" style="width:220px;height:30px;" class="easyui-textbox"
								value="${qylist.m26 }" data-options="validType:'email'" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">邮政编码：</label></td>
							<td class="width-28"><input name="M9" class="easyui-textbox"
								value="${qylist.m9 }" data-options="validType:'ZIP'" style="width:220px;height:30px;"/></td>
							<td class="width-22 active"><label class="pull-right">从业人员数量：</label></td>
							<td class="width-28"><input name="M28" class="easyui-numberbox"
								value="${qylist.m28 }" style="width:220px;height:30px;" data-options="min:0" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">特种作业人员数量：</label></td>
							<td class="width-28"><input name="M29" class="easyui-numberbox"
								value="${qylist.m29 }" style="width:220px;height:30px;" data-options="min:0" /></td>
							<td class="width-22 active"><label class="pull-right">专职安全生产管理人员数：</label></td>
							<td class="width-28"><input name="M30" class="easyui-numberbox"
								value="${qylist.m30 }" style="width:220px;height:30px;" data-options="min:0" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">专职应急管理人员数：</label></td>
							<td class="width-28"><input name="M31" class="easyui-numberbox"
								value="${qylist.m31 }" style="width:220px;height:30px;" data-options="min:0" /></td>
							<td class="width-22 active"><label class="pull-right">兼职安全生产管理人员数：</label></td>
							<td class="width-28"><input name="M30_1" class="easyui-numberbox"
								value="${qylist.m30_1 }" style="width:220px;height:30px;"
								data-options="min:0" /></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">注册安全工程师人员数：</label></td>
							<td class="width-28"><input name="M32" class="easyui-numberbox"
								value="${qylist.m32 }" style="width:220px;height:30px;" data-options="min:0" /></td>
							<td class="width-22 active"><label class="pull-right">所属网格区域：</label></td>
							<td class="width-28"><input name="ID2" value="${qylist.ID2}"  class="easyui-combotree" style="width:220px;height: 30px;" data-options="required:'true',url:'${ctx}/system/admin/xzqy/xzqyjson',method: 'get',panelHeight:180"/></td>
						</tr>
						<shiro:hasAnyRoles name="admin,superadmin">
							<tr>
								<td class="width-22 active"><label class="pull-right">开通日期：</label></td>
								<td class="width-28"><input name="ktdate" class="easyui-datebox"
									value="${qylist.ktdate }" style="width:220px;height:30px;" data-options="" /></td>
								<td class="width-22 active"><label class="pull-right">到期日期：</label></td>
								<td class="width-28"><input name="dqdate" value="${qylist.dqdate}"  class="easyui-datebox"  style="width:220px;height:30px;" data-options=""/></td>
							</tr>
						</shiro:hasAnyRoles>
						<tr>
							<td class="width-22 active"><label class="pull-right">隐患排查治理制度（单文件）：</label></td>
							<td colspan="3">
							<input type="hidden" id="M37" name="M37" value="${qylist.m37}" />
							<div id="uploader_qy_m7">
								    <div id="m37fileList" class="uploader-list" >
									    <c:if test="${not empty qylist.m37}">
										<c:set var="url" value="${fn:split(qylist.m37, '||')}" />
											<div  style="margin-bottom: 10px;">
											<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
											</div>
										</c:if>
								    </div>
								    <div id="insertfilebtM37">选择文件</div>
							</div>
							
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">隐患排查治理计划（单文件）：</label></td>
							<td colspan="3">
							<input type="hidden" id="M38" name="M38" value="${qylist.m38}" />
							<div id="uploader_qy_m38">
								    <div id="m38fileList" class="uploader-list " >
									    <c:if test="${not empty qylist.m38}">
										<c:set var="url" value="${fn:split(qylist.m38, '||')}" />
											<div  style="margin-bottom: 10px;">
											<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
											</div>
										</c:if>
								    </div>
								    <div id="insertfilebtM38">选择文件</div>
							</div>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">企业平面图（单文件）：</label></td>
							<td colspan="3">
								<input type="hidden" id="M33_3" name="M33_3" value="${qylist.m33_3}" />
								<div id="uploader_qy_m33_3">
								    <div id="m33_3fileList" class="uploader-list" >
									    <c:if test="${not empty qylist.m33_3}">
										<c:set var="url" value="${fn:split(qylist.m33_3, '||')}" />
											<div  style="margin-bottom: 10px;">
											<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
											</div>
										</c:if>
								    </div>
								    <div id="imagePickerm33_3">选择图片</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">企业照片（首页）：</label></td>
							<td colspan="3">
								<input type="hidden" id="qyimg" name="qyimg" value="${qylist.qyimg}" />
								<div id="uploader_qyimg">
								    <div id="qyimg_fileList" class="uploader-list" >
									    <c:if test="${not empty qylist.qyimg}">
										<c:set var="url" value="${fn:split(qylist.qyimg, '||')}" />
											<div  style="margin-bottom: 10px;">
											<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${qylist.qyimg}')">企业照片</a>
											</div>
										</c:if>
								    </div>
								    <div id="imagePickerqyimg">选择图片</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">经营范围：</label></td>
							<td colspan="3"><input name="M14"  class="easyui-textbox" style="width: 658px;height:80px;" value="${qylist.m14 }" data-options="multiline:true,validType:'length[1,500]'"/></td>
						</tr>
						<tr>
							<td class="width-22 active"><label class="pull-right">公司介绍：</label></td>
							<td colspan="3"><input name="M45"  class="easyui-textbox"style="width: 658px;height:80px;"value="${qylist.m45 }" data-options="multiline:true" /></td>
						</tr>
					</tbody>
				</table>
				 <input type="hidden" name="M44" value="${qylist.m44 }" />
			</form>
	</div>



	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注企业位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
	</div>

	<script type="text/javascript">
	var action = "${action}";
	
	var bmx_x="";
	var bmy_y="";
	var locx ='${qylist.m16}';
	var locy ='${qylist.m17}';
	var url="";
	// 添加修改区分
	
	
	$(function(){
		$.fn.validatebox.defaults.rules.remote.message = '企业名称已存在！';
		if (action == 'create') {
			url="${ctx}/bis/qyjbxx/create/";
			$("#M8_6").val("靖江市安监局");
			//企业名称校验
			$('#qyname_m1').textbox({    
			    required: true,    
			    validType:{
			    	length:[1,100],
			    	remote:["${ctx}/bis/qyjbxx/checkName","qyName"]
			    }
			}); 
		} else if (action == 'update') {
			if('${qylist.m39 }'=='0'){
	   		 	$('#bis_qy_id_table_td_m40').combobox({ required:false });
				$('#bis_qy_id_table_td_m40_td1').hide();
	   		 	$('#bis_qy_id_table_td_m40_td2').hide();

			}
			if('${qylist.m39 }'=='1'){
		   		$('#bis_qy_id_table_td_m40').combobox({ required:true });
				$('#bis_qy_id_table_td_m40_td1').show();
	   		 	$('#bis_qy_id_table_td_m40_td2').show();
			}
			if('${qylist.m46 }'=='1'){
				$('#m46_1_td1').show();
	   		 	$('#m46_1_td2').show();
			}else{
				$('#m46_1_td1').hide();
	   		 	$('#m46_1_td2').hide();
			}
			url="${ctx}/bis/qyjbxx/update/";
			//企业名称校验
			$('#qyname_m1').textbox({    
			    required: true,    
			    validType:{
			    	length:[1,100],
			    	remote:["${ctx}/bis/qyjbxx/checkName?qyid=${qylist.ID}","qyName"]
			    }
			}); 
			isuploadImg();
		} 
		
		
		var M11 = "${qylist.m11 }";
		var M11_1 = "${qylist.m11_1 }";
		var M11_2 = "${qylist.m11_2 }";
		var M11_3 = "${qylist.m11_3 }";
		var sM11="江苏",sM11_1=" ",sM11_2=" ",sM11_3=" ";
		if(M11==null||M11==""){
		    M11 = sM11;
		}
		if(M11_1==null||M11_1==""){
			M11_1 = sM11_1;
		}
		if(M11_2==null||M11_2==""){
			M11_2 = sM11_2;
		}
		if(M11_3==null||M11_3==""){
			M11_3 = sM11_3;
		}
		initLocation({sheng_val:M11,shi_val:M11_1,xian_val:M11_2,xiang_val:M11_3});
		$("#sheng").attr("disabled",true);
		//$("#shi").attr("disabled",true);
		//$("#xian").attr("disabled",true);
	});
	
		
	function addmap(searchcon){	
		initMap("bis_enterprise_dlg_map",locx,locy);
		map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		local.search(searchcon);
		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		
		map.addEventListener("click", function(e){	
			locx=e.point.lng;
			locy=e.point.lat;
			var now_point =  new BMap.Point(e.point.lng, e.point.lat );
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		});
		
	}
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '400px'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#enterprise_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    	addmap("");
		    },
		    yes: function(index, layero){
		    	$("#bis_map_c_x").textbox("setValue", locx);//经度
				$("#bis_map_c_y").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	
	function exin(){
		$.post('${ctx}/bis/qyjbxx/exin',"",function(data){
			$.messager.show({ title : "提示",msg: data, position: "bottomRight" });
			$("#bis_ckxx_add_div").window('destroy');
			dg.datagrid('reload');
		});
	}
	
	
	var $ = jQuery,
    
	//============================ 企业平面图上传配置start ================================
	$list = $('#m33_3fileList'); 
	var uploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=image',
	    pick: {
	    	id:'#imagePickerm33_3',
	    	multiple : false,
	    },
	    duplicate :true,
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
	    },
	    compress :{
	        width: 1200,
	        height: 1200,
	        quality: 90,
	        allowMagnify: false,
	        crop: false,
	        preserveHeaders: false,
	        noCompressIfLarger: false,
	        compressSize: 1024*50
	    }
	});
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	uploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
					"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            "</div>"
		            );

		    $list.html( $li );	
			var newurl=res.url+"||"+res.fileName;			
			$('#M33_3').val( newurl );
			uploadImgFlag=true;
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	//============================ 企业平面图上传配置end ================================
		
	//============================ 隐患排查治理制度上传配置start ================================
	$list2 = $('#m37fileList'); 
	var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=file',
	    pick: {
	    	id:'#insertfilebtM37',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            "</div>"
		            );		    
		    $list2.html( $li );
			var newurl=res.url+"||"+res.fileName;
			$('#M37').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	//============================ 隐患排查治理制度上传配置end ================================
		
	//============================ 隐患排查治理计划上传配置start ================================	
	$list3 = $('#m38fileList'); 
	var fileuploader2 = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=file',		
	    pick: {
	    	id:'#insertfilebtM38',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader2.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	fileuploader2.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            "</div>"
		            );		    
		    $list3.html( $li );
			var newurl=res.url+"||"+res.fileName;
			$('#M38').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	//============================ 隐患排查治理计划上传配置end ================================
		
	//判断是否上传企业平面图
	function isuploadImg(){
		var img=$("input[name='M33_3']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}	
	
	
	//============================ 企业照片上传配置start ================================
	$list4 = $('#qyimg_fileList'); 
	var uploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=image',
	    pick: {
	    	id:'#imagePickerqyimg',
	    	multiple : false,
	    },
	    duplicate :true,
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
	    },
	    compress :{
	        width: 1200,
	        height: 1200,
	        quality: 90,
	        allowMagnify: false,
	        crop: false,
	        preserveHeaders: false,
	        noCompressIfLarger: false,
	        compressSize: 1024*50
	    }
	});
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	uploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
					"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            "</div>"
		            );

		    $list4.html( $li );	
			var newurl=res.url;			
			$('#qyimg').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	//============================ 企业照片图上传配置end ================================
</script>

</body>
</html>