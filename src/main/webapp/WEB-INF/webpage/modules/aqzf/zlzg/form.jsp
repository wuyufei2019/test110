<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>责令整改管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/zlzg/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
						<td class="width-30" colspan="3"><input type="text" id="ID2" name="ID2" value="${zlzg.ID2}" class="easyui-combobox"  style="height: 30px;width: 100%" data-options="readonly:'true', editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' "/></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-30" colspan="3" id="czwt">
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">整改完毕日期：</label></td>
					<td class="width-30" colspan="3">
						<input id="M3" name="M3" class="easyui-datebox" value="${zlzg.m3 }" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申诉政府：</label></td>
					<td class="width-30"><input type="text" id="M4" name="M4" class="easyui-textbox" value="${zlzg.m4 }"  data-options=" " style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">诉讼法院：</label></td>
					<td class="width-30"><input type="text" id="M5" name="M5" class="easyui-textbox" style="height: 30px; width: 100%;" value="${zlzg.m5}" data-options=" " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M6_1" name="M6_1" class="easyui-combobox" value="${zlzg.m6_1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M7_1" name="M7_1" class="easyui-textbox" style="height: 30px; width: 100%;" value="${zlzg.m7_1 }" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M6_2" name="M6_2" class="easyui-combobox" value="${zlzg.m6_2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M7_2" name="M7_2" class="easyui-textbox" style="height: 30px; width: 100%;" value="${zlzg.m7_2}" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30" ><input id="M8" type="text" name="M8" class="easyui-textbox" value="${zlzg.m8 }"  data-options=" " style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">检查日期：</label></td>
					<td class="width-30" >
						<input id="M1" name="M1" class="easyui-datebox" value="${zlzg.m1 }" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " />
					</td>
				</tr>
				
				<input type="hidden" name="ID1" value="${zlzg.ID1}" />	
				<input type="hidden" id="M2" name="M2" value="${zlzg.m2 }" />				
				<c:if test="${not empty zlzg.ID}">
					<input type="hidden" name="ID" value="${zlzg.ID}" />
					<input type="hidden" name="M0" value="${zlzg.m0}" />
					<input type="hidden" name="M9" value="${zlzg.m9}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${zlzg.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${zlzg.s3}" />
				</c:if>
				<tbody>
			</table>

		  	
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

//得到当前日期
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+2;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}  

function doSubmit(){
	$('#M6_1').combobox('setValue',$('#M6_1').combobox('getText'));
	$('#M6_2').combobox('setValue',$('#M6_2').combobox('getText'));
	$("#inputForm").submit(); 
}

var wfxwlist='${wfxwlist}';
    wfxw=JSON.parse(wfxwlist);  
var  $list= $("#czwt");
if ('${action}' == 'create') {
	$.each(wfxw, function(idx, obj) {
	    if(obj.ID==undefined){
	    	var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'"/>'+obj.m1+'</br>');	
			$list.append( $tdHtml );
	    }else{
		    var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'"/>'+obj.m1+'</br>');	
			$list.append( $tdHtml );
	    }
	});
}

if ('${action}' == 'update'){
	var allwfxw = ','+'${zlzg.m2}'+',';
	$.each(wfxw, function(idx, obj) {
		if(obj.ID==undefined){
			if(allwfxw.indexOf(','+obj.m1+',')!=-1){
				var $tdHtml = $('<input type="checkbox" checked="checked" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'"/>'+obj.m1+'</br>');	
				$list.append( $tdHtml );
			}else{
				var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.m1+'"/>'+obj.m1+'</br>');	
				$list.append( $tdHtml );
			}
		}else{
			if(allwfxw.indexOf(','+obj.ID+',')!=-1){
				var $tdHtml = $('<input type="checkbox" checked="checked" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'"/>'+obj.m1+'</br>');	
				$list.append( $tdHtml );
			}else{
				var $tdHtml = $('<input type="checkbox" onclick="addnr()" name="wfxwid" style="margin-bottom:2px;" value="'+obj.ID+'"/>'+obj.m1+'</br>');	
				$list.append( $tdHtml );
			}
		}
	});
}

function addnr() {
	var wfxwids = "";
	var m3 = '';
	$('input[name="wfxwid"]:checked').each(function(i){
	   if(0==i){
	       wfxwids = $(this).val();
	   }else{
	       wfxwids += (","+$(this).val());
	   }
	});
	$("#M2").val(wfxwids);
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
	
	if ('${action}' == 'create') {
	  	var currtime = new Date();
	  	$("#M3").datebox("setValue",myformatter(currtime)); 
	}
		  
	//下拉关联信息
	$("#M6_1").combobox({
		onSelect: function(){
			var id=$("#M6_1").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M7_1").textbox('setValue',data);
			}
		});
			
		}
	});

	$("#M6_2").combobox({
		onSelect: function(){
			var id=$("#M6_2").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M7_2").textbox('setValue',data);
			}
		});
		}
	});

});
</script>
</body>
</html>