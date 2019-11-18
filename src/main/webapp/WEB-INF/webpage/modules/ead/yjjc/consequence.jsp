<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故应急决策 -应急调度与处置 </title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript">
		var consequenceid='${consequenceid}';
	</script>
	<script type="text/javascript" src="${ctxStatic}/model/js/ead/yjjc/consequence.js"></script>
   <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<style type="text/css">
	.BMap_cpyCtrl{ display:none; }  
    .anchorBL{ display:none;}   
    #yjjc_consequence_jylx_map label{max-width:none;}
    </style>
</head>


<body>
<div id="yjjc_consequence" class="easyui-tabs" fit="true">
	<div id="yjjc_consequence_jqlx" title="救援路线" style="height:100%;">
		<div id="yjjc_consequence_jylx_map" style="width:100%;height:100% ">
		</div>
	</div>
	
	<div id="yjjc_consequence_yjdw" title="应急队伍" style="height:100%;">
		<div id="yjjc_consequence_yjdw_list" style="width:100%;height:35%;border-bottom: 5px solid rgb(149, 184, 231); ">
			<div id="yjjc_consequence_yjdw_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjdw_searchFrom" >
	       	        <input name="yjjc_consequence_yjdw_dw_name" class="easyui-textbox" data-options="width:150,prompt: '队伍名称'"/>
			        
			        <input name="yjjc_consequence_yjdw_dw_distance" class="easyui-combobox" data-options="
								width:150,prompt: '距离事发地',panelHeight:100,editable:false ,data: [
										{value:'3',text:'3KM以内'},
										{value:'4',text:'3-5KM'},
										{value:'5',text:'5KM以内'},
										{value:'6',text:'5-10KM'},
										{value:'10',text:'10KM以内'}] " />
			        <a  class="btn btn-info btn-xs"  onclick="yjdw_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a  class="btn btn-info btn-xs"  onclick="yjdw_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>    
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjdw_dg"></table> 
		</div>
		<div id="yjjc_consequence_yjdw_image" style="width:100%;height:60% ">
		</div>
	</div>

	<div id="yjjc_consequence_yjzb" title="应急装备" style="height:100%;">
		<div id="yjjc_consequence_yjzb_list" style="width:100%;height:35%;border-bottom: 5px solid rgb(149, 184, 231); ">
			<div id="yjjc_consequence_yjzb_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjzb_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjzb_zb_name" class="easyui-textbox" data-options="width:150,prompt: '装备名称'"/>
			        <input name="yjjc_consequence_yjzb_zb_distance" class="easyui-combobox" data-options="
								width:150,prompt: '距离事发地',panelHeight:100,editable:false ,data: [
										{value:'3',text:'3KM以内'},
										{value:'4',text:'3-5KM'},
										{value:'5',text:'5KM以内'},
										{value:'6',text:'5-10KM'},
										{value:'10',text:'10KM以内'}] " />
		        	<a class="btn btn-info btn-xs"  onclick="yjzb_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjzb_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjzb_dg"></table> 
		</div>
		<div id="yjjc_consequence_yjzb_image" style="width:100%;height:60% ">
		</div>
	</div>
	
	<div id="yjjc_consequence_yjwz" title="应急物资" style="height:100%;">
		<div id="yjjc_consequence_yjwz_list" style="width:100%;height:35%;border-bottom: 5px solid rgb(149, 184, 231); ">
			<div id="yjjc_consequence_yjwz_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjwz_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjwz_wz_name" class="easyui-textbox" data-options="width:150,prompt: '物资名称'"/>
			        <input name="yjjc_consequence_yjwz_wz_distance" class="easyui-combobox" data-options="
								width:150,prompt: '距离事发地',panelHeight:100,editable:false ,data: [
										{value:'3',text:'3KM以内'},
										{value:'4',text:'3-5KM'},
										{value:'5',text:'5KM以内'},
										{value:'6',text:'5-10KM'},
										{value:'10',text:'10KM以内'}] " />
		        	<a class="btn btn-info btn-xs"  onclick="yjwz_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjwz_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjwz_dg"></table> 
		</div>
		<div id="yjjc_consequence_yjwz_image" style="width:100%;height:60% ">
		</div>
	</div>
	
	<div id="yjjc_consequence_bncs" title="避难场所" style="height:100%;">
		<div id="yjjc_consequence_bncs_list" style="width:100%;height:35%;border-bottom: 5px solid rgb(149, 184, 231); ">
			<div id="yjjc_consequence_bncs_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_bncs_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_bncs_cs_name" class="easyui-textbox" data-options="width:150,prompt: '场所名称'"/>
			        <input name="yjjc_consequence_bncs_cs_distance" class="easyui-combobox" data-options="
								width:150,prompt: '距离事发地',panelHeight:100,editable:false ,data: [
										{value:'3',text:'3KM以内'},
										{value:'4',text:'3-5KM'},
										{value:'5',text:'5KM以内'},
										{value:'6',text:'5-10KM'},
										{value:'10',text:'10KM以内'}] " />
		        	<a class="btn btn-info btn-xs"  onclick="bncs_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="bncs_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_bncs_dg"></table> 
		</div>
		<div id="yjjc_consequence_bncs_image" style="width:100%;height:60%;overflow-y :auto ">
		</div>
	</div>

	<div id="yjjc_consequence_yjzj" title="应急专家" style="height:100%;">
		<div id="yjjc_consequence_yjzj_list" style="width:100%;height:100%;">
			<div id="yjjc_consequence_yjzj_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjzj_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjzj_zj_name" class="easyui-textbox" data-options="width:150,prompt: '姓名'"/>
		        	<a class="btn btn-info btn-xs"  onclick="yjzj_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjzj_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>  
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjzj_dg"></table> 
		</div>
	</div>

	<div id="yjjc_consequence_yjyy" title="应急医院" style="height:100%;">
		<div id="yjjc_consequence_yjyy_list" style="width:100%;height:35%;border-bottom: 5px solid rgb(149, 184, 231); ">
			<div id="yjjc_consequence_yjyy_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjyy_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjyy_yy_name" class="easyui-textbox" data-options="width:150,prompt: '医院名称'"/>
			        <input name="yjjc_consequence_yjyy_yy_distance" class="easyui-combobox" data-options="
								width:150,prompt: '距离事发地',panelHeight:100,editable:false ,data: [
										{value:'3',text:'3KM以内'},
										{value:'4',text:'3-5KM'},
										{value:'5',text:'5KM以内'},
										{value:'6',text:'5-10KM'},
										{value:'10',text:'10KM以内'}] " />
		        	<a class="btn btn-info btn-xs"  onclick="yjyy_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjyy_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>  
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjyy_dg"></table> 
		</div>
		<div id="yjjc_consequence_yjyy_image" style="width:100%;height:60% ">
		</div>
	</div>

	<div id="yjjc_consequence_yjczjs" title="应急处置技术" style="height:100%;">
		<div id="yjjc_consequence_yjczjs_list" style="width:100%;height:100% ">
			 
			<table id="yjjc_consequence_yjczjs_dg" class="table table-bordered">
				<tr><td  style="width: 150px;">物质名称:</td><td id="yjjc_consequence_yjczjs_dg_t1" ></td></tr>
				<tr><td  >主要危险性:</td><td id="yjjc_consequence_yjczjs_dg_t2"  ></td></tr>
				<tr><td  >事故应急处置技术:</td><td id="yjjc_consequence_yjczjs_dg_t3"  ></td></tr>	
			</table>
		</div>
	</div>

	<div id="yjjc_consequence_yjtxl" title="应急通讯录" style="height:100%;">
		<div id="yjjc_consequence_yjtxl_list" style="width:100%;height:100% ">
			<div id="yjjc_consequence_yjtxl_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjtxl_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjtxl_name" class="easyui-textbox" data-options="width:150,prompt: '姓名'"/>
		        	<a class="btn btn-info btn-xs"  onclick="yjtxl_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjtxl_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>  
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjtxl_dg"></table> 
		</div>
	</div>

	<div id="yjjc_consequence_yjfzword" title="生成应急辅助决策文书" style="height:100%;">
		<div id="yjjc_consequence_yjfzword_list" style="width:100%;height:100%;display:block;">
		<div style="padding: 5px 10px;float: left;margin: 3px 5px;" >
			<a class="btn btn-info btn-xs"  onclick="yjfzword_word()" style="width: 150px;padding: 5px;"><i class='fa fa-file-word-o'></i> 生成文书</a>   
		</div>
		</div>
		
		
		
	</div>
</div>
<script type="text/javascript">

$(function(){
	initMap("yjjc_consequence_jylx_map");//创建和初始化地图
	var data={"consequenceid":consequenceid};
	$('#yjjc_consequence').tabs({
		onSelect: function(title,index){
			if(index=='0'){
				//alert("选择救援路线");
			}else if(index=='1'){
				//应急队伍
				onloadyjdw();
				onloadyjdwmap(data);
				yjdw_cx_clear();
			}else if(index=='2'){
				//应急装备
				onloadyjzb();
				onloadyjzbmap(data);
				yjzb_cx_clear();
			}else if(index=='3'){
				//应急物资
				onloadyjwz();
				onloadyjwzmap(data);
				yjwz_cx_clear();
			}else if(index=='4'){
				//应急避难场所
				onloadbncs();
				onloadbncsmap(data);
				bncs_cx_clear();
			}else if(index=='5'){
				//应急专家
				onloadyjzj();
				yjzj_cx_clear();
			}else if(index=='6'){
				//应急医院
				onloadyjyy();
				onloadyjyymap(data);
				yjyy_cx_clear();
			}else if(index=='7'){
				//应急处置技术
				//onloadyjczjs();
				onloadyjczjsData();
				//yjczjs_cx_clear();
			}else if(index=='8'){
				//应急通讯录
				onloadyjtxl();
				yjtxl_cx_clear();
			}else if(index=='9'){
				//生成文书
			}
		}
	});
});

</script>
		
</body>
</html>