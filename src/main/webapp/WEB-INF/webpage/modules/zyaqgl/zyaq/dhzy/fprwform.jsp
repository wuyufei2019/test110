<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/zyaqgl/dhzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${dhzy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'fprw'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${dhzy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${dhzy.m1 }" data-options="readonly:'true',validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${dhzy.m2 }" data-options="readonly:'true',validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火作业级别：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" name="M3" class="easyui-combobox " value="${dhzy.m3 }" data-options="readonly:'true',panelHeight:'auto', editable:false ,data: [{value:'全部',text:'全部'},
																																		        {value:'特殊动火',text:'特殊动火'},
																																		        {value:'一级动火',text:'一级动火'},
																																		        {value:'二级动火',text:'二级动火'}] " /></td>
					<td class="width-20 active"><label class="pull-right">动火方式：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" name="M4" class="easyui-combobox " value="${dhzy.m4 }" data-options="readonly:'true',panelHeight:'auto', editable:false ,data: [{value:'电焊',text:'电焊'},
																																		        {value:'气焊(割)',text:'气焊(割)'},
																																		        {value:'喷灯',text:'喷灯'},
																																		        {value:'电钻',text:'电钻'},
																																		        {value:'砂轮',text:'砂轮'}] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火地点：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-textbox" value="${dhzy.m5 }" data-options="readonly:'true',validType:['length[0,100]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">动火时间起：</label></td>
					<td class="width-30"><input name="M6" class="easyui-datebox" value="${dhzy.m6 }" style="width: 100%;height: 30px;" editable="false" readonly="readonly" /></td>
					<td class="width-20 active"><label class="pull-right">动火时间止：</label></td>
					<td class="width-30"><input name="M7" class="easyui-datebox" value="${dhzy.m7 }" style="width: 100%;height: 30px;" editable="false" readonly="readonly" /></td>
				</tr>

                <tr>
                    <td class="width-20 active"><label class="pull-right">动火作业负责人：</label></td>
                    <td class="width-30">
                        <input id="M8_id" name="M8_id" type="hidden" value="${dhzy.m8_id}" />
                        <input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-validatebox textbox" value="${dhzy.m8 }" data-options="editable:false ,validType:['length[0,250]'] " />
                    </td>
                    <td class="width-20 active"><label class="pull-right">动火人：</label></td>
                    <td class="width-30">
                        <input id="M9_id" name="M9_id" type="hidden" value="${dhzy.m9_id}" />
                        <input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-validatebox textbox" value="${dhzy.m9 }" data-options="editable:false ,validType:['length[0,250]']" />
                    </td>
                </tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">承包商名称：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" name="M29" class="easyui-combobox " value="${dhzy.m29 }"
															data-options="readonly:'true',panelHeight:'150px', editable:false ,valueField:'text',textField:'text',url:'${ctx}/zyaqgl/xgdw/getcbsjson',
								onSelect: function(row){
									$('[name=ID3]').val(row.id);
									console.log('id3:'+$('[name=ID3]').val());
								  }" /></td>
				</tr>

				<tr> 
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="进入受限空间" disabled="disabled"/>进入受限空间</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="高处作业" disabled="disabled"/>高处作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="吊装作业" disabled="disabled"/>吊装作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="临时用电" disabled="disabled"/>临时用电</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="动土/断路作业" disabled="disabled"/>动土/断路作业</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="锁定" disabled="disabled"/>锁定</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="盲板抽堵" disabled="disabled"/>盲板抽堵</div>
					 </td>
				</tr>
				
				<tr> 
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="易燃易爆物质" disabled="disabled"/>易燃易爆物质</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="坠落" disabled="disabled"/>坠落</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="腐蚀性物质" disabled="disabled"/>腐蚀性物质</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="蒸汽" disabled="disabled"/>蒸汽</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高压气体/液体" disabled="disabled"/>高压气体/液体</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="有毒有害物质" disabled="disabled"/>有毒有害物质</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高温/低温" disabled="disabled"/>高温/低温</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="触电" disabled="disabled"/>触电</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="窒息" disabled="disabled"/>窒息</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="噪音" disabled="disabled"/>噪音</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="产生火花/静电" disabled="disabled"/>产生火花/静电</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="旋转设备" disabled="disabled"/>旋转设备</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="机械伤害" disabled="disabled"/>机械伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="粉尘" disabled="disabled"/>粉尘</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="不利天气" disabled="disabled"/>不利天气</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="淹没/埋没" disabled="disabled"/>淹没/埋没</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="辐射" disabled="disabled"/>辐射</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="交叉作业" disabled="disabled"/>交叉作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="其他" disabled="disabled"/>其他</div>
																		                </td>
				</tr>
                <c:if test="${dhzy.m11_1 != null && dhzy.m11_1 != ''}">
                <tr>
                    <td class="width-20 active"><label class="pull-right">其他危害辨识：</label></td>
                    <td colspan="3"><input style="width: 100%;height: 30px;" id="M11_1" name="M11_1" class="easyui-textbox" value="${dhzy.m11_1}" data-options="readonly:'true'" />
                    </td>
                </tr>
                </c:if>
				</tbody>
			</table>	
			<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分配</strong></p><hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;" />
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >		
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m12">
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="filePicker"></div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全措施编制人：</label></td>
					<td class="width-30"><input value="${dhzy.bzcsr }" name="M15" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">安全措施确认人：</label></td>
					<td class="width-30"><input value="${dhzy.qrcsr }" name="M16" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">生产部门负责人：</label></td>
					<td class="width-30"><input value="${dhzy.m13 }" name="M13" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">监护人：</label></td>
					<td class="width-30"><input value="${dhzy.m17 }" name="M17" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">作业区域负责人：</label></td>
					<td class="width-30"><input value="${dhzy.m19 }" name="M19" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30"><input value="${dhzy.m21 }" name="M21" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全管理部门负责人：</label></td>
					<td class="width-30"><input value="${dhzy.m23 }" name="M23" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">动火审批人：</label></td>
					<td class="width-30"><input value="${dhzy.m24 }" name="M24" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">验收人：</label></td>
					<td class="width-30"><input value="${dhzy.m25 }" name="M25" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">申请单位负责人：</label></td>
					<td class="width-30"><input value="${dhzy.m22 }" name="M22" style="width: 100%;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					
				</tr>

				<c:if test="${not empty dhzy.id}">
					<input type="hidden" name="ID" value="${dhzy.id}" />
					<input type="hidden" name="ID1" value="${dhzy.id1}" />
					<input type="hidden" name="ID2" value="${dhzy.id2}" />
					<input type="hidden" name="ID3" value="${dhzy.id3}">
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dhzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dhzy.s3}" />
					<input type="hidden" name="zt" value="${dhzy.zt}" />
					<input type="hidden" name="M10" value="${dhzy.m10}" />
					<input type="hidden" name="M11" value="${dhzy.m11}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

var $ = jQuery,
$list2 = $('#m12fileList'); //文件上传

	if('${action}' == 'fprw'){
		var fjUrl = '${dhzy.m12}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_ws_m12').append( $input );
			}
		}
	}

function doSubmit(){
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}

$(function(){
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
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

    $(".easyui-combobox").combobox({
        onHidePanel : function() {
            var _options = $(this).combobox('options');
            var _data = $(this).combobox('getData');/* 下拉框所有选项 */
            var _value = $(this).combobox('getValue');/* 用户输入的值 */
            var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
            for (var i = 0; i < _data.length; i++) {
                if (_data[i][_options.valueField] == _value) {
                    _b=true;
                    break;
                }
            }
            if(!_b){
                $(this).combobox('setValue', '');
            }
        }
    });
});

var action = "${action}";
if(action=="fprw"){
	//特殊作业
	var tszy = "${dhzy.m10}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M10']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
	
	//危害辨识
	var whbs = "${dhzy.m11}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M11']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='M10']:checkbox").css("width","18px");
	$("input[name='M10']:checkbox").css("height","18px");
	$("input[name='M11']:checkbox").css("width","18px");
	$("input[name='M11']:checkbox").css("height","18px");
});


/*$('#M8').bind('click',function(){
    layer.open({
        type: 2,
        shift: 1,
        area: ['400px', '85%'],
        title: '人员选择',
        maxmin: false,
        content: '${ctx}/system/compuser/rydx?content='+$("#M8_id").val(),
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var idname=iframeWin.contentWindow.getidname();
            var arr1=idname.split(",");
            var names="";
            var ids="";
            for (var i = 0; i < arr1.length-1; i++) {
                var arr2=arr1[i].split("||");
                if(i == 0) {
                    names = arr2[1];
                    ids = arr2[0];
                }else {
                    names = names + ',' + arr2[1];
                    ids = ids + ',' + arr2[0];
                }
            }
            $("#M8").val(names);
            $("#M8_id").val(ids);
            layer.close(index);
        },
        cancel: function(index){
        }
    });
});*/

/*$('#M9').bind('click',function(){
    layer.open({
        type: 2,
        shift: 1,
        area: ['400px', '85%'],
        title: '人员选择',
        maxmin: false,
        content: '${ctx}/system/compuser/rydx?content='+$("#M9_id").val(),
        btn: ['确定', '关闭'],
        yes: function(index, layero){
            var body = layer.getChildFrame('body', index);
            var iframeWin = layero.find('iframe')[0];
            var idname=iframeWin.contentWindow.getidname();
            var arr1=idname.split(",");
            var names="";
            var ids="";
            for (var i = 0; i < arr1.length-1; i++) {
                var arr2=arr1[i].split("||");
                if(i == 0) {
                    names = arr2[1];
                    ids = arr2[0];
                }else {
                    names = names + ',' + arr2[1];
                    ids = ids + ',' + arr2[0];
                }
            }
            $("#M9").val(names);
            $("#M9_id").val(ids);
            layer.close(index);
        },
        cancel: function(index){
        }
    });
});*/
</script>
</body>
</html>