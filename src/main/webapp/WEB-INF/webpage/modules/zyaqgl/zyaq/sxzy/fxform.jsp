<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
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
	});
	</script>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/sxzy/${action}"  method="post" class="form-horizontal" >
     		<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析标准</strong></p><hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;" /> 
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">有毒有害介质：</label></td>
					<td class="width-30" >
						<input id="M29" name="M29" class="easyui-textbox" style="width: 100%;height: 30px;" value="" data-options="required:'true',validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">可燃气：</label></td>
					<td class="width-30" >
						<input id="M30" name="M30" class="easyui-textbox" style="width: 100%;height: 30px;" value="" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">氧含量：</label></td>
					<td class="width-30" >
						<input id="M31" name="M31" class="easyui-textbox" style="width: 100%;height: 30px;" value="" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>
				
				<input type="hidden" name="id" value="${sxzyid }" />
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;">
				<strong>分析数据</strong>
				<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加分析数据"><i class="fa fa-plus"></i> 添加分析数据</a>
			</p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			
			<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
				<tr >
					<td style="text-align: center;width: 18%;">有毒有害物资</td>
					<td style="text-align: center;width: 7%;">可燃气</td>
					<td style="text-align: center;width: 7%;">氧含量</td>
					<td style="text-align: center;width: 20%;">分析时间</td>
					<td style="text-align: center;width: 18%;">部位</td>
					<td style="text-align: center;width: 20%;">分析人</td>
					<td style="text-align: center;width: 12%;">操作</td>
				</tr>
			</table>	
						
       </form>
<script type="text/javascript">
var rwid=1;
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:30px" >'+
				'<td style="" align="center">'+
				'<input type="text" id="wz_'+rwid+'" name="wz" class="easyui-textbox" value=""  style="height: 30px;width: 141px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="krq_'+rwid+'" name="krq" class="easyui-textbox" value=""  style="height: 30px;width: 55px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="yhl_'+rwid+'" name="yhl" class="easyui-textbox" value=""  style="height: 30px;width: 55px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="fxsj_'+rwid+'" name="fxsj" class="easyui-textbox" value=""  style="height: 30px;width: 155px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="bw_'+rwid+'" name="bw" class="easyui-textbox" value=""  style="height: 30px;width: 140px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="czr_'+rwid+'" name="czr" class="easyui-textbox" value=""  style="height: 30px;width: 155px;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:78px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	$list.after( $li );
	
	$('#fxsj_'+rwid).datetimebox({  
		editable:false
	}); 
	
	$('#czr_'+rwid).combobox({  
		editable:true,
		url:'${ctx}/system/compuser/userjson',
		valueField:'id',
		textField:'text',
		panelHeight:'150px'
	});
	
	$('#wz_'+rwid).textbox({  
		required:'true',
		validType:'length[0,100]'
	}); 
	
	$('#krq_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	
	$('#yhl_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	
	$('#bw_'+rwid).textbox({  
		validType:'length[0,100]'
	});
 	
	rwid=rwid+1;
}
	
//删除指定行的数据
function removeTr(obj) {
	obj.remove();
}
</script>
</body>
</html>