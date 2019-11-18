<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>查看平面图信息</title>
<meta name="decorator" content="default" />		
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
	<style type="text/css">
    .ball {
    width: 10px;
    height: 10px;
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
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>

				<tr>
					<td colspan="3" >
						<div id="xfss_dlg" style="background-color:#F4F4F4;text-align:center;" >               
							<div id="xfss_dlg_map" class="wrap" style="margin:0 auto;width: 80%"><img style="" id="img1" width="100%" alt="" src="${pmturl}"></img></div>
        				</div>	
					</td>			
				</tr>
			<tbody>
		</table>
<script type="text/javascript">
	var data ='${pmt}';
	data=eval(data);
	
	$(function(){
		$("#img1").load(function() {
			for(var i=0;i<data.length;i++){
			var locx =data[i].locx;
			var locy =data[i].locy;
			var color=data[i].color;
			initMap(locx,locy,color);
			}
		})
		 
	});

 	function initMap(locx,locy,color){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var x=locx;
		var y=locy;
		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        if(color=="1"){
				$('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+';background-image:url(${ctxStatic}/model/images/fxgk/red.png)"></div>');
			}else if(color=="2"){
				$('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+';background-image:url(${ctxStatic}/model/images/fxgk/ora.png)"></div>');
			}else if(color=="3"){
				$('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+';background-image:url(${ctxStatic}/model/images/fxgk/yel.png)"></div>');
			}else if(color=="4"){
				$('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+';background-image:url(${ctxStatic}/model/images/fxgk/blu.png)"></div>');
			}
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}
</script>
	</body>
</html>