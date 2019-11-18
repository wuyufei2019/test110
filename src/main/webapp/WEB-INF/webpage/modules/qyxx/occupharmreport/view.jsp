<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检测报告管理</title>
	<meta name="decorator" content="default"/>
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
	    width:800px;
	}
	</style>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">职业病危害因素类别：</label></td>
					<td class="width-30"><input id="bis_occupharmreport_m10" class="easyui-combobox"  style="width: 100%;height: 30px;" name="M10" value="${jcbg.m10 }" 
					data-options="readonly:true,panelHeight:'auto' ,required:'true', editable:true,data: [
								        {value:'粉尘',text:'粉尘'},
								        {value:'化学因素',text:'化学因素'},
								        {value:'物理因素',text:'物理因素'},
								        {value:'放射性因素',text:'放射性因素'},
								        {value:'生物因素',text:'生物因素'},
								        {value:'其他因素',text:'其他因素'} ],
					loader:function (param, success, error) {
									if('${action}'=='update'){ 
								   var t1=  $('#bis_occupharmreport_m10').combobox('getText');
								   var url = '${ctx}/bis/occupharm/zybzd2/'+t1;
								$.ajax({
							           url:'${ctx}/bis/occupharm/zybzd2/'+t1,
							           dataType : 'json',
							           type : 'POST',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#bis_occupharmreport_m11').combobox('loadData', data); 
							           }
							     });
							     }
								 } 
								,
					onSelect: function(rec){
						
	       	        	$('#bis_occupharmreport_m11').combobox('clear');
	       	        	$('#bis_occupharmreport_m11').combobox('enable');
	       	        	
					    var url = '${ctx}/bis/occupharm/zybzd2/'+rec.text;
					    $('#bis_occupharmreport_m11').combobox('reload', url);
	       	        }
						    "/></td>
					<td class="width-20 active"><label class="pull-right">职业病危害因素名称：</label></td>
					<td class="width-30"><input id="bis_occupharmreport_m11" class="easyui-combobox" name="M11" value="${jcbg.m11 }" style="width: 100%;height: 30px;" data-options="
								readonly:true,required:'true', editable:true, valueField: 'id',textField: 'text' "
								/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检测机构：</label></td>
					<td class="width-30">${jcbg.m1 }</td>
					<td class="width-20 active"><label class="pull-right">检测日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${jcbg.m2 }"/></td>
				</tr>
								<tr>
					<td class="width-20 active"><label class="pull-right">检测结果编号：</label></td>
					<td class="width-30">${jcbg.m3 }</td>
					<td class="width-20 active"><label class="pull-right">下次检测日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${jcbg.m4 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测结果：</label></td>
					<td class="width-85" colspan="3">
					${jcbg.m6 }
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">采样地点：</label></td>
					<td class="width-30">
					${jcbg.m14 }
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">平面图坐标：</label></td>
					<td class="width-85" colspan="3" >
        				<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;" >               
							<div id="xfss_dlg_map" class="wrap" style="margin:0 auto;width:800px"><img style="width:800px" id="img1"></img></div>
        				</div>
					</td>			
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测报告：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty jcbg.m5}">
					 <c:forTokens items="${jcbg.m5}" delims="," var="url" varStatus="urls">
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

		  	
       </form>
	<script type="text/javascript">
		var locx = '${jcbg.m12}';
		var locy = '${jcbg.m13}';
		var pmtpath = '${pmt}';
		var url = pmtpath.split('||');

		function initImg(url) {
			$("#img1").width($('.wrap').width());
			$("#img1").attr("src", url);
		}
		
		$(function() {
			//初始化平面图
			initImg(url[0]);
			$("#img1").load(function() {
				initMap();
			});
		});
		
		function initMap(){
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