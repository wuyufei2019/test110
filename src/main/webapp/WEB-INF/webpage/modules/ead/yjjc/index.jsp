<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故应急决策</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript" src="${ctxStatic}/model/js/ead/yjjc/index.js?v=1.3"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<style type="text/css">
	.BMap_cpyCtrl{ display:none; }  
    .anchorBL{ display:none;}   
    #dituContent label{max-width:none;}
    #yjzy_btn_panel button{ width: 100px;margin-bottom: 5px;}
    </style>
    <script type="text/javascript">
    
    $(function(){
    	initMap("dituContent");//创建和初始化地图
    	if(count==0){
    		layer.msg("请点击地图，选择事故发生点！",{time: 2000});
    	}
    	addMarker();
        //创建marker
        function addMarker(){
    		map.addEventListener("click", function(e){ 
    			count=count+1;
    		    pide=e;
    		    window.pide=pide;
    		    onloadacacount();
    		});
        }
  	  $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){
		  var citytq=remote_ip_info.city;
	    	$.ajax({
			type:'get',
			url:url = "http://wthrcdn.etouch.cn/weather_mini?city=" + citytq,
			scriptCharset: "gbk",
			dataType:'json',
			success: function(data){
				 var _w0 = data.data.forecast[0];
				global_weathertoday_fx=_w0.fengxiang;
			}
		});
	  });
    });
 
   
    </script>
</head>

 
<body>
<div id="yjzy_btn_panel" style="width: 100%; padding: 5px; display: none;">
<button  class="btn btn-info btn-sm" onclick="showJYLX()" >救援路线</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjzb','应急装备')" >应急装备</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjdw','应急队伍')" >应急队伍</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjwz','应急物资')" >应急物资</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_bncs','避难场所')" >避难场所</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjzj','应急专家')" >应急专家</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjyy','应急医院')" >应急医院</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjczjs','应急处置技术')" >应急处置技术</button><br/>
<button  class="btn btn-info btn-sm" onclick="showDataGridPanel('#yjjc_consequence_yjtxl','应急通讯录')" >应急通讯录</button><br/>
<button  class="btn btn-info btn-sm" onclick="yjfzword_word()" >生成决策文书</button>
<button  class="btn btn-info btn-sm" onclick="reset()" >重置</button>
</div>


<div id="yjjc_consequence_yjdw" title="应急队伍" style="height:100%;display: none;">
		<div id="yjjc_consequence_yjdw_list" style="width:100%;height:100%;  ">
			<div id="yjjc_consequence_yjdw_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjdw_searchFrom" >
	       	        <input id="yjjc_consequence_yjdw_dw_name" name="yjjc_consequence_yjdw_dw_name" class="easyui-textbox" data-options="width:150,prompt: '队伍名称'"/>
			        
		    
			        <a  class="btn btn-info btn-xs"  onclick="yjdw_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a  class="btn btn-info btn-xs"  onclick="yjdw_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>    
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjdw_dg"></table> 
		</div>
</div>


<div id="yjjc_consequence_yjzb" title="应急装备" style="height:100%;display: none;">
		<div id="yjjc_consequence_yjzb_list" style="width:100%;height:100%;">
			<div id="yjjc_consequence_yjzb_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjzb_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjzb_zb_name" class="easyui-textbox" data-options="width:150,prompt: '装备名称'"/>
			       
		        	<a class="btn btn-info btn-xs"  onclick="yjzb_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjzb_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>
			<table id="yjjc_consequence_yjzb_dg"></table> 
		</div>
</div>



<div id="yjjc_consequence_yjwz" title="应急物资" style="height:100%;display: none;">
		<div id="yjjc_consequence_yjwz_list" style="width:100%;height:100%;">
			<div id="yjjc_consequence_yjwz_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjwz_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjwz_wz_name" class="easyui-textbox" data-options="width:150,prompt: '物资名称'"/>
			        
		        	<a class="btn btn-info btn-xs"  onclick="yjwz_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjwz_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>
			<table id="yjjc_consequence_yjwz_dg"></table> 
		</div>
	</div>
	
<div id="yjjc_consequence_bncs" title="避难场所" style="height:100%;display: none;">
		<div id="yjjc_consequence_bncs_list" style="width:100%;height:100%;">
			<div id="yjjc_consequence_bncs_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_bncs_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_bncs_cs_name" class="easyui-textbox" data-options="width:150,prompt: '场所名称'"/>
			        
		        	<a class="btn btn-info btn-xs"  onclick="bncs_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="bncs_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>      
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_bncs_dg"></table> 
		</div>
	</div>

<div id="yjjc_consequence_yjzj" title="应急专家" style="height:100%;display: none;">
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

<div id="yjjc_consequence_yjyy" title="应急医院" style="height:100%;display: none;">
		<div id="yjjc_consequence_yjyy_list" style="width:100%;height:100%; ">
			<div id="yjjc_consequence_yjyy_tb" style="padding:5px;height:auto">
	        	<div>
	        	<form id="yjjc_consequence_yjyy_searchFrom" >
	       	        <input type="text" name="yjjc_consequence_yjyy_yy_name" class="easyui-textbox" data-options="width:150,prompt: '医院名称'"/>
			        
		        	<a class="btn btn-info btn-xs"  onclick="yjyy_cx()"><i class='fa fa-search'></i> 查询</a>    
			        <a class="btn btn-info btn-xs"  onclick="yjyy_cx_clear()"><i class='fa fa-refresh'></i> 清空</a>  
				</form>
	   
	        	</div>    
 			</div>

			<table id="yjjc_consequence_yjyy_dg"></table> 
		</div>
	</div>

<div id="yjjc_consequence_yjczjs" title="应急处置技术" style="height:100%;display: none;">
		<div id="yjjc_consequence_yjczjs_list" style="width:100%;height:100% ">
			 
			<table id="yjjc_consequence_yjczjs_dg" class="table table-bordered">
				<tr><td  style="width: 150px;">物质名称:</td><td id="yjjc_consequence_yjczjs_dg_t1" ></td></tr>
				<tr><td  >主要危险性:</td><td id="yjjc_consequence_yjczjs_dg_t2"  ></td></tr>
				<tr><td  >事故应急处置技术:</td><td id="yjjc_consequence_yjczjs_dg_t3"  ></td></tr>	
			</table>
		</div>
	</div>

<div id="yjjc_consequence_yjtxl" title="应急通讯录" style="height:100%;display: none;">
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
	
	
<div style="height:100%;width:100%;border:#ccc solid 1px;position:absolute;" id="dituContent"></div>
</body>
</html>