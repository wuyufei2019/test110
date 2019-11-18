<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检测报告管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
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
		    width：800px;
		}
	</style>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/occupharmreport/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${jcbg.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${jcbg.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">职业病危害因素类别：</label></td>
					<td class="width-30"><input id="bis_occupharmreport_m10" class="easyui-combobox"  style="width: 100%;height: 30px;" name="M10" value="${jcbg.m10 }" 
					data-options="panelHeight:'auto' ,required:'true', editable:true,data: [
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
								required:'true', editable:true, valueField: 'id',textField: 'text' "
								/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检测机构：</label></td>
					<td class="width-30"><input name="M1" class="easyui-textbox"  value="${jcbg.m1 }" style="width: 100%;height: 30px;" data-options="required:'true',validType:['length[0,50]'] "/></td>
					<td class="width-20 active"><label class="pull-right">检测日期：</label></td>
					<td class="width-30"><input id="bis_occupharmreport_form_mainform_m2" name="M2" type="text" class="easyui-datebox" value="<fmt:formatDate value="${jcbg.m2}" pattern="yyyy-MM-dd HH:mm:ss"  />" style="width: 100%;height: 30px;" data-options="required:'true'"/></td>
				</tr>
								<tr>
					<td class="width-20 active"><label class="pull-right">检测结果编号：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M3" value="${jcbg.m3 }" style="width: 100%;height: 30px;" data-options=" required:'true',validType:['length[0,25]']"/></td>
					<td class="width-20 active"><label class="pull-right">下次检测日期：</label></td>
					<td class="width-30"><input id="bis_occupharmreport_form_mainform_m4" name="M4" type="text" class="easyui-datebox" value="<fmt:formatDate value="${jcbg.m4}" pattern="yyyy-MM-dd HH:mm:ss"  />" style="width: 100%;height: 30px;"  data-options="required:'true',validType:'compareDate[\'#bis_occupharmreport_form_mainform_m2\',\'下次检测日期不能早于下次检测日期!\' ] ' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测结果：</label></td>
					<td class="width-85" colspan="3">
					<input name="M6" type="text" value="${jcbg.m6 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">采样地点：</label></td>
					<td class="width-30">
					<input name="M14" type="text" value="${jcbg.m14 }"   class="easyui-textbox" style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">平面图坐标：</label></td>
					<td colspan="3" style="height:30px;line-height:30px;">
						<label style="margin-left:19px" >x：</label>
						<input id="bis_map_c_x" name="M12" value="${jcbg.m12 }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<label style="margin-left:19px">y：</label> 
						<input id="bis_map_c_y" name="M13" value="${jcbg.m13 }" class="easyui-textbox" readonly="readonly" style="width:150px;height:30px;"/>
						<a class="btn btn-primary" onclick="showpmt( )" style="width:60px;">定位</a></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测报告：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_jcbg_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty jcbg.ID}">
					<input type="hidden" name="ID" value="${jcbg.ID}" />
					<input type="hidden" name="ID1" value="${jcbg.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${jcbg.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${jcbg.s3}" />
				</c:if>
				</tbody>
			</table>

		  	<div id="xfss_dlg" style="background-color:#F4F4F4;padding:10px 20px;text-align:center;display: none;" >               
            <div class="ftitle" style="color: red;">请在平面图上标注设施位置!</div>
        	<div id="xfss_dlg_map" class="wrap" style="margin:0 auto;width:800px"><img style="width:800px" id="img1" alt=""></img></div>
        </div>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	
	var $ = jQuery,
    $list = $('#m5fileList'); //图片上传
	
		
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker',
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
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_jcbg_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var fjUrl = '${jcbg.m5}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_jcbg_m5').append( $input );
			}
		}
	}
	
	if('${action}'=='update'){
		var pmtpath='${pmt}';
		var url=pmtpath.split('||');
		initImg(url[0]);
	}

	if(usertype=='1'&&'${action}'=='create'){
		var pmtpath='${pmt}';
		var url=pmtpath.split('||');
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
		    btn: ['关闭平面图'],
		    success: function(layero, index){
		    },
		    yes: function(index, layero){
		    	layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		})
		if('${action}'=='update')
		{
			initMap();
		}
	}
	
	function initMap(){
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
	if(usertype != 1){
		$("#_qyid").combobox({
			onSelect: function(rec){
				$.ajax({
				 	url:'${ctx}/wghgl/wgd/qypmt',
						data:{'qyid':rec.id},
						dataType:'text',
						type : 'POST',
						contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			 			success: function (data){
			 				var url=data.split('||');
			 				initImg(url[0]);
	         			}
			    });
			}
		});
	}
</script>
</body>
</html>