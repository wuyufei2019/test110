<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看消防设备信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<style type="text/css">
    .ball {
    width: 10px;
    height: 10px;
    background: red;
    border-radius: 50%;
    position: absolute;
	} 
	.wrap{
    background: #ccc;
    position: relative;
	}
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }  
	</style>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
				    <td class="width-15 active"><label class="pull-right">设施名称：</label></td>
					<td class="width-35" colspan="3">
						<input name="name"  class="easyui-textbox" value="${entity.name }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,50]']" readonly="readonly"/>
					</td>
				</tr>

				<tr >

					<td class="width-15 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-35">
						<input name="ggxh" class="easyui-textbox" value="${entity.ggxh }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" readonly="readonly"/>
					</td>
					<td class="width-15 active" ><label class="pull-right">换验周期：</label></td>
					<td class="width-35" colspan="3">
						<input name="cycle" class="easyui-combobox" value="${entity.cycle }" style="width: 100%;height: 30px;"
							 data-options=" editable:false ,panelHeight: 'auto',valueField: 'value',textField: 'text',data:[{value:'有效',text:'有效'},{value:'过期',text:'过期'},{value:'报废',text:'报废'}]" readonly="readonly"/></td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
				    <td class="width-35">
				    	<input type="text" id="state" name="state"  value="${entity.state }" class="easyui-combobox" style="width: 100%;height: 30px;" 
				    		data-options="editable:false ,valueField: 'value',textField: 'text', panelHeight: 'auto',data:[{value:'每月',text:'每月'},{value:'每季',text:'每季'},{value:'每半年',text:'每半年'}]" readonly="readonly"/>
				    
				    </td>
				    <td class="width-15 active"><label class="pull-right">生产厂商：</label></td>
					<td class="width-35">
						<input name=sccs  class="easyui-textbox" value="${entity.sccs }" style="width: 100%;height: 30px;"
								data-options="validType:['length[0,50]']" readonly="readonly"/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" >
						<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;" >               
            				<div class="ftitle" style="color: red;">平面图上标注为该设施位置</div>
							<div id="xfss_dlg_map" class="wrap" ><img style="width:100% " id="img1" alt=""></img></div>
        				</div>	
					</td>
				</tr>
				<tr>
              		    <td class="width-15 active"><label class="pull-right">备注：</label></td>
               			<td class="width-35" colspan="3"><input name="M3" type="text" value="${entity.m20 }" class="easyui-textbox"
                   			style="width: 100%;height: 60px;" data-options="multiline:true,validType:'length[0,250]' " readonly="readonly"></td>
           	   </tr>

			</table>

		  	<tbody>
       </form>
 
         <div id="bdnr">
               <table id="fxgk_bdnr_dg" ></table>
        </div>
<script type="text/javascript">
	var locx ='${entity.x}';
	var locy ='${entity.y}';
	var pmtpath='${entity.pmt}';
	var url=pmtpath.split('||');

	function initImg(url){
		$("#img1").attr("src",url);
	}

	$(function(){ 		
		initImg(url[0]);
		initMap1();
	});
	
	function initMap1(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		
		var x=locx;
		var y=locy;
		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}
</script>
</body>
</html>