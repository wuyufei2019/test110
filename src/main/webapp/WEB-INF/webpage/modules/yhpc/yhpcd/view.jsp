<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查点</title>
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
				<tr>
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
					${yhpcd.qyname}
					 </td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">巡查点名称：</label></td>
					<td class="width-35">${yhpcd.name }</td>
					<td class="width-15 active"><label class="pull-right">绑定二维码：</label></td>
					<td class="width-35">
						${yhpcd.bindcontent }
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">绑定rfid：</label></td>
					<td class="width-35">${yhpcd.rfid }</td>
					<td class="width-15 active"><label class="pull-right">rfid卡批次代码：</label></td>
					<td class="width-35">${yhpcd.area }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">是否重大危险源</label></td>
					<td class="width-30">
						<c:if test="${yhpcd.iszdwxy == '1'}">
							是
						</c:if>
						<c:if test="${yhpcd.iszdwxy == '0'}">
							否
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">企业位置 ：</label></td><td colspan="3">
						<div id="yhpc_yhpcd_dlg_map_view" style="width:100%;height: 200px;"></div>
				</tr>
					
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" >
						<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;" >               
            				<div class="ftitle" style="color: red;">请在平面图上标注设施位置!</div>
							<div id="xfss_dlg_map" class="wrap" ><img style="width:100% " id="img1" alt=""></img></div>
        				</div>	
					</td>			
				</tr>

			</table>

		  	<tbody>
       </form>
 
 		<div id="yjzb_dlg" class="easyui-dialog" style="width:600px;height:450px;padding:10px 20px;text-align:center;"
                closed="true" >
            <div class="ftitle" style="color: red;">请在地图上标注厂区位置(点击地图后显示厂区经度、纬度)!</div>
			<table cellspacing="2px" cellpadding="0" width="100%" style="margin:auto;text-align:left;"> 
				<tr><td>
					<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ 
																													init_map(value);
																													}" />
				</td></tr>
				<tr><td>
					<div id="erm_yjzb_dlg_map" style="width:100%;height: 300px;"></div>
				</td></tr>
			</table>  
        <div id="yjzb_dlg-buttons" style="text-align:center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#yjzb_dlg').dialog('close')" style="width:90px">取消</a>
        </div>
        </div>
         <div id="bdnr">
               <table id="fxgk_bdnr_dg" ></table>
        </div>
<script type="text/javascript">
	var pointx='${yhpcd.lng}';
	var pointy='${yhpcd.lat}';
	var locx ='${yhpcd.x}';
	var locy ='${yhpcd.y}';
	var pmtpath='${yhpcd.pmt}';
	var url=pmtpath.split('||');

	function initImg(url){
		$("#img1").attr("src",url);
	}

	$(function(){ 		
	    $("#yhpc_yhpcd_dlg_map_view").show();
		init_map();
		initImg(url[0]);
		initMap1();
		
		dg=$('#fxgk_bdnr_dg').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/yhpcd/xjnrlist/'+'${yhpcd.id}',
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
			striped:true,
		    columns:[[    
		  	        {field:'dangerlevel',title:'隐患级别',width:50,
		  	        	formatter : function(value, row, index){
		  	        		if(value=="1") return value='一般';
		  	        		if(value=="2") return value='重大';
		  	        	}
		  	        },
		  	        {field:'checktitle',title:'检查单元',width:80 },  
		  	        {field:'content',title:'检查项目',width:150 },  
		    ]],
		    onLoadSuccess: function(){
		    },
		    onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
		    },
			checkOnSelect:false,
			selectOnCheck:false,
			});
	});
	
	//初始化地图
	function init_map(){
		//地图初期化
		initMap("yhpc_yhpcd_dlg_map_view",pointx,pointy,'','',1);
		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
	}

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