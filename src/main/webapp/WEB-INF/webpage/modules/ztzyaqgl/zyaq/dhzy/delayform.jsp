<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业延期</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/dhzy/index.js?v=1.0"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/dhzy/delay"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><input id="M7" name="M7" class="easyui-datetimebox" value="${dhzy.m7 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><input id="M8" name="M8" class="easyui-datetimebox" value="${dhzy.m8 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<input type="hidden" name="ID" value="${dhzy.id}" />
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
var spzt="";
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

var $ = jQuery;

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
	    	else if(data=='has')
				parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '该作业已延期，无法再次延期！',shade: 0 ,time: 5000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});

$("#M7").datetimebox({
    onChange:function(date){ 
   		var ksdate = $("#M7").datetimebox("getValue");
   		if(!isToday(ksdate)){
			$("#M7").datetimebox("setValue","");
			layer.msg("作业时间必须为今天！", {
				time : 2000
			});
		}
        var jsdate = $("#M8").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M7").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M8").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M7").datetimebox("getValue");  
        var jsdate = $("#M8").datetimebox("getValue");
		if(!isToday(jsdate)){
			$("#M8").datetimebox("setValue","");
			layer.msg("作业时间必须为今天！", {
				time : 2000
			});
		}
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M8").datetimebox("setValue","");  
	            layer.msg("不能小于作业开始时间！", {
					time : 2000
				});
	        }
        }  
    }  
});

//判断动火等级
function fxdj(){
	var qk=$("#M4_1").combobox('getValue');
	if(qk=='无'){
		$("#M4").combobox('setValue','一级动火');
	}else{
		$("#M4").combobox('setValue','特殊动火');
	}
}

</script>
</body>
</html>