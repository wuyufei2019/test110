<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消防设备登记</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}
$(function(){
   /*  var category = '${entity.id2}'; */
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});
	
	</script>
	
	<style type="text/css">
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }  
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
	</style>
	<style type="text/css">
 
</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/xfssgl/xfssdj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<input type="hidden" value="${entity.id }" name="ID">
				<input type="hidden" value="${entity.id1 }" name="ID1">
				<input type="hidden" value="${entity.pid }" name="pid" id="pid">
				<input type="hidden" value="${entity.icon }" name="icon" id="icon">
				<input type="hidden" value="<fmt:formatDate value='${entity.s1 }' pattern='yyyy-MM-dd HH:mm:ss'/>" name="S1" >
				<input type="hidden" name="type" value="1" />
				<tr >
				    <td class="width-15 active"><label class="pull-right">设施名称：</label></td>
					<td class="width-35">
						<input name="name"  class="easyui-textbox" value="${entity.name }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,50]']" />
					</td>
					<td class="width-15 active"><label class="pull-right">绑定二维码：</label></td>
					<td class="width-35">
						<input name="bindcontent" class="easyui-textbox" value="${xfssdj.bindcontent }" style="width: 100%;height: 30px;" readonly="readonly" data-options="validType:'length[0,100]'" />
					</td>
				</tr>

				<tr >

					<td class="width-15 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-35">
						<input name="ggxh" class="easyui-textbox" value="${entity.ggxh }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">生产厂商：</label></td>
					<td class="width-35">
						<input name=sccs  class="easyui-textbox" value="${entity.sccs }" style="width: 100%;height: 30px;"
								data-options="validType:['length[0,50]']" />
					</td>
				</tr>
				
				<tr>
				    <td class="width-15 active" ><label class="pull-right">换验周期：</label></td>
					<td class="width-35">
						<input name="cycle" class="easyui-combobox" value="${entity.cycle }" style="width: 100%;height: 30px;"
							 data-options=" editable:false ,panelHeight: 'auto',valueField: 'value',textField: 'text',data:[{value:'每天',text:'每天'},{value:'每周',text:'每周'},{value:'每月',text:'每月'},{value:'每季度',text:'每季度'},{value:'每半年',text:'每半年'},{value:'每年',text:'每年'}]" /></td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
				    <td class="width-35">
				    	<input type="text" id="state" name="state"  value="${entity.state }" class="easyui-combobox" style="width: 100%;height: 30px;" 
				    		data-options="editable:false ,valueField: 'value',textField: 'text', panelHeight: 'auto',data:[{value:'有效',text:'有效'},{value:'过期',text:'过期'},{value:'报废',text:'报废'}]"/>
				    
				    </td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label style="margin-left:19px" >x：</label>
						<input id="bis_map_c_x" name="x" value="${entity.x }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<label style="margin-left:19px">y：</label> 
						<input id="bis_map_c_y" name="y" value="${entity.y }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showpmt( )" style="width:60px;">定位</a></td>
				</tr>
				<tr>
              		    <td class="width-15 active"><label class="pull-right">备注：</label></td>
               			<td class="width-35" colspan="3"><input name="M20" type="text" value="${entity.m20 }" class="easyui-textbox"
                   			style="width: 100%;height: 60px;" data-options="multiline:true,validType:'length[0,250]' "></td>
           	   </tr>
			</tbody>
		</table>
       </form>
        <div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;display: none;" >               
            <div class="ftitle" style="color: red;">请在平面图上标注设施位置!</div>
			<div id="xfss_dlg_map" class="wrap" ><img style="width:100% " id="img1" alt=""></img></div>
        </div>	
<script type="text/javascript">
	
	$(function(){ 
		if('${action}'=='xfssupdate'){
			var pmtpath='${xfssdj.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
		
		if(usertype=='1'&&'${action}'=='create'){
			var pmtpath='${xfssdj.pmt}';
			var url=pmtpath.split('||');
			$("#pid").val('${pid}');
			initImg(url[0]);
		}
		
		function initImg(pmtpath){
			$("#img1").attr("src",pmtpath);
		}
		
		$('.wrap').click(function(e){
	        var offset=$('.wrap').offset();
	        var top=e.offsetY+"px";
	        var left=e.offsetX+"px";
	        $('.ball').remove();
	        $('.wrap').append('<span class="ball" style="top:'+top+';left:'+left+'"></span>');
	       	//计算x轴长度比例
	       	wh=$("#img1").width();
			var xp=(e.offsetX/wh).toFixed(4);
	       	//计算y轴长度比例
	       	wh=$("#img1").height();
			var yp=(e.offsetY/wh).toFixed(4);			
	        $("#bis_map_c_x").textbox("setValue",xp);//X坐标
			$("#bis_map_c_y").textbox("setValue",yp);//Y坐标
	    });
	});	
	//弹出平面图界面
	function showpmt(){		
		layer.open({
		    type: 1,  
		    area: ['100%', '100%'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#xfss_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    },
		    yes: function(index, layero){
		    	layer.close(index);
			  },
		    cancel: function(index){ 
	        }
		})
		if('${action}'=='xfssupdate'){
				init_Map();
		}
	}
	function init_Map(){
		var wh=$("#img1").width();
		var wh2=$("#img1").height();
		var x=$("#bis_map_c_x").val();
		var y=$("#bis_map_c_y").val();

		if(x!="" && y!=""){
	        var top=y*wh2+"px";
	        var left=x*wh+"px";
	        $('.ball').remove();
	        $('.wrap').append('<div class="ball" style="top:'+top+';left:'+left+'"></div>');
		}
	}
	
	</script>
</body>
</html>