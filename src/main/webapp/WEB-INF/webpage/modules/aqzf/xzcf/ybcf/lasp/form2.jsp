<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加立案审批表</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/lasp/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		    <tbody>
		    	<c:if test="${action eq 'updateSub'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">编号：</label></td>
						<td class="width-30"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${yle.number }" style="width: 100%;height: 30px;" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">案由：</label></td>
                 	<td class="width-30" colspan="3" >
						<input style="width:100%;height: 30px;"  id="ayname"name="ayname"  class="easyui-textbox" value="${yle.ayname }"
						data-options="required:'true',editable:true,validType:'length[0,250]'" />
				   </td>					
				</tr>
				<tr>
					<td class="width-20 active" ><label class="pull-right">案件来源:</label></td>
					<td class="width-30"  >
					<input id="casesource" name="casesource"  class="easyui-combobox" value="${yle.casesource }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/dict/json/ajly'" />
					</td>
					<td class="width-20 active"><label class="pull-right">立案时间：</label></td>
					<td class="width-30"><input name="filldate" id="filldate" class="easyui-datebox" value="${yle.filldate }" style="width: 100%;height: 30px;" /></td>	
				</tr>
		        <tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3"><input id="casename" name="casename" class="easyui-textbox"  value="${yle.casename }" style="width: 100%;height: 30px;" data-options="required:'true',multiline:true,validType:['length[0,100]'] "/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">当事人：</label></td>
					<td class="width-30" >
					<input name="dsperson"id="dsperson" value="${yle.dsperson }"   class="easyui-textbox" style="width: 100%;height: 30px;" data-options="readonly:'true'" />
					</td>
					
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="contact" name="contact" class="easyui-textbox" value="${yle.contact }" style="width: 100%;height: 30px;"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">法定代表人/负责人：</label></td>
					<td class="width-30"  ><input  id="legalperson"name="legalperson" class="easyui-textbox" value="${yle.legalperson }" style="width: 100%;height: 30px;"  /></td>
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">
					<input name="ybcode" type="text" id="ybcode" value="${yle.ybcode }"  class="easyui-textbox" style="width: 100%;height: 30px;"  data-options="validType:'ZIP'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30" colspan="3"><input id="dsaddress" name="dsaddress" class="easyui-textbox" value="${yle.dsaddress }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件基本状况：</label></td>
					<td class="width-30" colspan="3" id="ajqk"></td>
				</tr>
					<tr>
					<td class="width-20 active"><label class="pull-right">承办人意见：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="opinion" name="opinion" class="easyui-textbox" value="${yle.opinion }"  data-options="multiline:true , validType:'length[0,1000]'" style="width: 100%;height: 50px;" /></td>
				</tr>
				<tr>
					<td class="width-20 active" rowspan="2"><label class="pull-right">承办人：</label></td>
					<td class="width-30"  ><input  id="enforcer1"name="enforcer1" class="easyui-combobox" value="${yle.enforcer1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'142px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" /></td>
					<td class="width-20 active" rowspan="2"><label class="pull-right">证号：</label></td>
					<td class="width-30">
					<input name="identity1" id="identity1" value="${yle.identity1 }"  class="easyui-textbox" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr>
					<td class="width-20"  ><input  id="enforcer2"name="enforcer2" class="easyui-combobox" value="${yle.enforcer2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'142px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" /></td>
					<td class="width-30">
					<input name="identity2" id="identity2" value="${yle.identity2 }"   class="easyui-textbox" style="width: 100%;height: 30px;" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><input id="cbsj" name="cbsj" class="easyui-datebox" value="${yle.cbsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.fzshyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="fzshyj" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="fzshyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="fzshyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">法制审核人：</label></td>
					<td class="width-30"><input type="text" id="fzshr" name="fzshr" class="easyui-combobox" value="${yle.fzshr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">法制审核日期：</label></td>
					<td class="width-30"><input id="fzshsj" name="fzshsj" class="easyui-datebox" value="${yle.fzshsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.shyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="shyj" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="shyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${yle.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><input id="shsj" name="shsj" class="easyui-datebox" value="${yle.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yle.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30"><input type="text" name="spr" class="easyui-combobox" value="${yle.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><input id="spsj" name="spsj" class="easyui-datebox" value="${yle.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				 <tr>
					<td class="width-20 active" ><label class="pull-right">审批表附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_url">
					    <div id="urlfileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<input type="hidden" name="id1" value="${yle.id1}" />
				<input type="hidden" name="ID2" value="${yle.ID2}" />		
				<input type="hidden" id="casecondition" name="casecondition" value="${yle.casecondition}" />
				<c:if test="${not empty yle.ID}">
					<input type='hidden' name="S1" value="<fmt:formatDate value="${yle.s1}"/>"/>
					<input type="hidden" name="S3" value="${yle.s3}" />
					<input type="hidden" name="ID" value="${yle.ID}" />
					<input type="hidden" name="gzflag" value="${yle.gzflag}" />
					<input type="hidden" name="tzflag" value="${yle.tzflag}" />
					<input type="hidden" name="cfjdflag" value="${yle.cfjdflag}" />
					<input type="hidden" name="xwflag" value="${yle.xwflag}" />
					<input type="hidden" name="cbflag" value="${yle.cfjdflag}" />
					<input type="hidden" name="dcflag" value="${yle.dcflag}" />
					<input type="hidden" name="cgflag" value="${yle.cgflag}" />
					<input type="hidden" name="qzflag" value="${yle.qzflag}" />
					<input type="hidden" name="jaflag" value="${yle.jaflag}" />
					<input type="hidden" name="tempflag" value="${yle.tempflag}" />
					<input type="hidden" name="sdhznumber" value="${yle.sdhznumber}" />
					<input type="hidden" name="sbflag" value="${yle.sbflag}" />
                  	<input type="hidden" name="tlflag" value="${yle.tlflag}" />
                  	<input type="hidden" name="userid" value="${yle.userid}" />
				</c:if>
            </tbody>
		</table>
	
</form>
<script type="text/javascript">
var wfxwlist='${wfxwlist}';
    wfxw=JSON.parse(wfxwlist);  
var  $ajqklist= $("#ajqk");
if ('${action}' == 'createSub') {
	$.each(wfxw, function(idx, obj) {
		if(obj.ID==undefined){
	    	var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'||'+idx+'"/>'+obj.m1+'</br>');	
			$ajqklist.append( $tdHtml );
	    }else{
		    var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
			$ajqklist.append( $tdHtml );
	    }
	});
}

if ('${action}' == 'updateSub'){
	var allwfxw = ','+'${yle.casecondition}'+',';
	$.each(wfxw, function(idx, obj) {
		if(obj.ID==undefined){
			if(allwfxw.indexOf(','+obj.m1+',')!=-1){
				var $tdHtml = $('<input type="checkbox" checked="checked" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'||'+idx+'"/>'+obj.m1+'</br>');	
				$ajqklist.append( $tdHtml );
			}else{
				var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'||'+idx+'"/>'+obj.m1+'</br>');	
				$ajqklist.append( $tdHtml );
			}
		}else{
			if(allwfxw.indexOf(','+obj.ID+',')!=-1){
				var $tdHtml = $('<input type="checkbox" checked="checked" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
				$ajqklist.append( $tdHtml );
			}else{
				var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'||'+idx+'"/>'+obj.m1+'</br>');	
				$ajqklist.append( $tdHtml );
			}
		}
	});
}

function addnr() {
	var wfxwids = "";
	var opinion = "该行为涉嫌违反";
	$('input[name="wfxwid"]:checked').each(function(i){
	   if(0==i){
	       wfxwids = $(this).val().split("||")[0];
	       if(wfxw[$(this).val().split("||")[1]].m2 != null && wfxw[$(this).val().split("||")[1]].m2 != '' && wfxw[$(this).val().split("||")[1]].m2 != undefined){
	       		opinion += wfxw[$(this).val().split("||")[1]].m2;
	       }
	   }else{
	       wfxwids += (","+$(this).val().split("||")[0]);
	       if(wfxw[$(this).val().split("||")[1]].m2 != null && wfxw[$(this).val().split("||")[1]].m2 != '' && wfxw[$(this).val().split("||")[1]].m2 != undefined){
	       		opinion += ","+wfxw[$(this).val().split("||")[1]].m2;
	       }
	   }
	});
	$("#opinion").textbox("setValue",opinion+"，建议立案调查。");
	$("#casecondition").val(wfxwids);
}



$(function(){
  if('${action}'=='createSub'){
	 	var date=new Date();
	 	$('#filldate').datebox('setValue', format(new Date()));	
	 	$('#casesource').combobox('setValue', '执法检查');
	 }
});

$list = $('#urlfileList');//附件上传
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

//附件
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

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

// 文件上传成功 
fileuploader.on( 'uploadSuccess', function( file ,yle) {
$.jBox.closeTip();
	if(yle.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+yle.url+"')\">"+yle.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );
	    $list.append( $li );
		var newurl=yle.url+"||"+yle.fileName;	
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
		$('#uploader_ws_url').append( $input );
	}else{
		layer.msg(yle.message,{time: 3000});
	}
});

//下拉关联信息
$("#enforcer1").combobox({
	onSelect: function(){
			var id=$("#enforcer1").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#identity1").textbox('setValue',data);
			}
		});
	}
});
$("#enforcer2").combobox({
	onSelect: function(){
			var id=$("#enforcer2").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#identity2").textbox('setValue',data);
			}
		});
	}
});


//提交
function doSubmit(){
	$("#enforcer1").combobox("setValue",$("#enforcer1").combobox("getText"));
	$("#enforcer2").combobox("setValue",$("#enforcer2").combobox("getText"));
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
			return false; //返回false终止表单提交
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
</body>
</html>