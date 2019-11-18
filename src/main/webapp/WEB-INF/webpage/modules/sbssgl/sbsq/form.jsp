<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>固定资产请购单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbsq/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">请购部门：</label></td>
					<td class="width-30" >
						<input id="m1" name="m1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbsq.m1 }" data-options="required:'true',panelHeight:'150px',valueField: 'id',textField: 'text',url:'${ctx }/system/department/deptjson'" />
					</td>
					<td class="width-20 active"><label class="pull-right">请购人：</label></td>
					<td class="width-30" >
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbsq.m2 }" data-options="required:'true',panelHeight:'150px',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">请购日期：</label></td>
					<td class="width-30" >
						<input id="m3" name="m3" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbsq.m3 }" data-options="required:'true',editable:false" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">请购资产明细：</label></td>
					<td class="width-80" colspan="3">
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbsq.m4 }" data-options="validType:'length[0,250]'" />
					</td>
				</tr>
				
			</tbody>
		</table>	
			
		<p style="margin-top: 10px;text-align: center;">
			<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加请购固定资产"><i class="fa fa-plus"></i> 添加请购固定资产</a>
		</p>
		
		<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 20%;">资产名称</td>
				<td style="text-align: center;width: 20%;">规格型号</td>
				<td style="text-align: center;width: 20%;">制造商/品牌</td>
				<td style="text-align: center;width: 10%;">数量</td>
				<td style="text-align: center;width: 10%;">预算（RMB）</td>
				<td style="text-align: center;width: 10%;">总价（RMB）</td>
				<td style="text-align: center;width: 10%;">操作</td>
			</tr>
		</table>	
		
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">合计（RMB）：</label></td>
					<td class="width-30" >
						<input id="m5" name="m5" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${sbsq.m5 }" data-options="required:'true',min:0,editable:false" />
					</td>
					<td style="width: 50%;"></td>
				</tr>  
				<%-- <tr >
					<td class="width-20 active"><label class="pull-right">供应商：</label></td>
					<td class="width-80" colspan="2">
						<input id="m6" name="m6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbsq.m6 }" data-options="validType:'length[0,100]'" />
					</td>
				</tr> --%>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">请购理由类别：</label></td>
					<td class="width-80" colspan="2">
					<!-- <input type="radio" name="m7" style="margin-bottom: 6px;" value="1"> 预算内
						 <input type="radio" name="m7" style="margin-bottom: 6px;margin-left: 20px;" value="2"> 预算外 -->
					<input type="checkbox" name="m7" style="margin-bottom: 6px;" value="2"> 预算外
                    </td>	
				</tr>
				<%-- <tr id="z1">
					<td class="width-20 active"><label class="pull-right">预算项目编号：</label></td>
					<td class="width-30"><input name="m8" id="m8" class="easyui-textbox" value="${sbsq.m8 }" style="width: 100%;height: 30px;" /></td>	
				</tr> --%>
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">预算外理由：</label></td>
					<td class="width-80" colspan="2">
						<input style="width:100%;height: 80px;" id="m9"name="m9"  class="easyui-textbox" value="${sbsq.m9 }" data-options="multiline:true,validType:'length[0,250]'" />
				   	</td>
				</tr>
				
			</tbody>
		</table>	
		
		<c:if test="${not empty sbsq.ID}">
			<input type="hidden" name="ID" value="${sbsq.ID}" />
			<input type="hidden" name="qyid" value="${sbsq.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbsq.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbsq.s3}" />
			<input type="hidden" name="m10" value="${sbsq.m10}" />
			<input type="hidden" name="m11" value="${sbsq.m11}" />
			<input type="hidden" name="m11" value="${sbsq.m12}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

var rwid=1;

//添加行
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:30px" >'+
				'<td style="" align="center">'+
				'<input type="text" id="zcmc_'+rwid+'" name="zcmc" class="easyui-textbox" value=""  style="height: 30px;width: 196px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="ggxh_'+rwid+'" name="ggxh" class="easyui-textbox" value=""  style="height: 30px;width: 196px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="zzs_'+rwid+'" name="zzs" class="easyui-textbox" value=""  style="height: 30px;width: 196px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="sl_'+rwid+'" name="sl" class="easyui-numberbox" value=""  style="height: 30px;width: 98px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="dj_'+rwid+'" name="dj" class="easyui-numberbox" value=""  style="height: 30px;width: 98px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="zj_'+rwid+'" name="zj" class="easyui-numberbox" value=""  style="height: 30px;width: 98px;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:97px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	$list.after( $li );
	startEasyui();
	rwid=rwid+1;
}

//修改页面初始化
if('${action}'=='update'){
	var rwlist = '${qgsblist}';
	nrList=JSON.parse(rwlist);  
	$.each(nrList, function(idx, obj) {
		var $list= $("#rwtable tr").eq(-1);
		var $li = $(
					'<tr style="height:30px" >'+
					'<td style="" align="center">'+
					'<input type="text" id="zcmc_'+rwid+'" name="zcmc" class="easyui-textbox" value="'+obj.m1+'"  style="height: 30px;width: 196px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="ggxh_'+rwid+'" name="ggxh" class="easyui-textbox" value="'+obj.m2+'"  style="height: 30px;width: 196px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="zzs_'+rwid+'" name="zzs" class="easyui-textbox" value="'+obj.m3+'"  style="height: 30px;width: 196px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="sl_'+rwid+'" name="sl" class="easyui-numberbox" value="'+obj.m4+'"  style="height: 30px;width: 98px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="dj_'+rwid+'" name="dj" class="easyui-numberbox" value="'+obj.m5+'"  style="height: 30px;width: 98px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="zj_'+rwid+'" name="zj" class="easyui-numberbox" value="'+obj.m6+'"  style="height: 30px;width: 98px;" />'+
					'</td>'+
					'<td align="center">'+
					'<button class="btn btn-info btn-sm" style="width:97px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
					'</td>'+
					'</tr>'
		            );
		$list.after( $li );
		startEasyui();
		rwid=rwid+1;
	});
}

//得出合计价格
function gettotal(){
	var zj = 0;
	$("input[name='zj']").each(  
		function(){  
			zj += parseInt($(this).val());  
		}  
	);
	$('#m5').numberbox('setValue',zj); 
}

//初始化控件
function startEasyui(){
	$('#zcmc_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	}); 
	
	$('#ggxh_'+rwid).textbox({  
		validType:'length[0,50]'
	});
	
	$('#zzs_'+rwid).textbox({  
		validType:'length[0,50]'
	});
	
	$('#sl_'+rwid).numberbox({  
		/* required:'true', */
		min:0,
		onChange: function(newValue,oldValue) {
			var num = $(this).attr("id").split("_")[1];
			var dj = $('#dj_'+num).numberbox('getValue');
			if(dj!=null&&dj!=''&&newValue!=null&&newValue!=''){
				$('#zj_'+num).numberbox('setValue',dj*newValue);
				gettotal();
			}
	    }
	});
	
	$('#dj_'+rwid).numberbox({  
		/* required:'true', */
		min:0,
		onChange: function(newValue,oldValue) {
			var num = $(this).attr("id").split("_")[1];
			var sl = $('#sl_'+num).numberbox('getValue');
			if(sl!=null&&sl!=''&&newValue!=null&&newValue!=''){
				$('#zj_'+num).numberbox('setValue',sl*newValue);
				gettotal();
			}
	    }
	});
	
	$('#zj_'+rwid).numberbox({  
		/* required:'true', */
		min:0,
		editable:false
	});
}
	
//删除指定行的数据
function removeTr(obj) {
	obj.remove();
	gettotal();
}

function doSubmit(){
	if(!checkcombobox('m1')){
		layer.open({title: '提示',offset: 'auto',content: '所选请购部门不存在！',shade: 0 ,time: 2000 });
		return;
	}
	if(!checkcombobox('m2')){
		layer.open({title: '提示',offset: 'auto',content: '所选请购人不存在！',shade: 0 ,time: 2000 });
		return;
	}
	$("#inputForm").submit(); 
}

//验证下拉框的值是否有效
function checkcombobox(id){
	var options = $("#"+id).combobox('options');  
 	var data = $("#"+id).combobox('getData');		/* 下拉框所有选项 */  
 	var value = $("#"+id).combobox('getValue');	/* 用户输入的值 */  
 	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
 	for (var i = 0; i < data.length; i++) {  
    	if (data[i][options.valueField] == value) {  
        	b=true;  
       	 	break;  
    	}  
	}
 	return b;
}

$(function(){
	if('${action}'=='create'){
		/* $('#z1').hide(); */
		$('#z2').hide();
	}
	
	if('${action}'=='update'){
		if('${sbsq.m7}'==2){
			$("input[name='m7']").get(0).checked = true;
			$('#z2').show();
		} else {
			$('#z2').hide();
		}
	}
	
	/* $(":radio").change(function() {
		var flag = $('input:radio:checked').val();
		if (flag == "1") {
			$('#z1').show();
			$('#z2').hide();
		}else if (flag == "2") {
			$('#z1').hide();
			$('#z2').show();
		}
	}); */
	
	$(":checkbox").change(function() {
		if ($('input:checkbox').attr('checked')) {
			$('#z2').show();
		} else {
			$('#m9').textbox('setValue', '');
			$('#z2').hide();
		}
		
	});
	
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
});
</script>
</body>
</html>