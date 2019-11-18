<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消防设备登记</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
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
				<input type="hidden" name="ID" value="${entity.id }" >
				<input type="hidden" name="ID1" value="${entity.id1}" />
				<input type="hidden" value="<fmt:formatDate value='${entity.s1 }' pattern='yyyy-MM-dd HH:mm:ss'/>" name="S1" >
				<input type="hidden" name="type" value="0" />
				<tr>
					<td class="width-15 active"><label class="pull-right">名称：</label></td>
				    <td class="width-35">
				    	<input type="text" id="category" name="name" value="${entity.name }" class="easyui-combobox" style="width: 100%;height: 30px;" 
				    		data-options="required:'true',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/xfsslb'"/>
				    
				    </td>
			         <td class="width-15 active"><label class="pull-right">所属上级:</label></td>
			         <td class="width-35"><input type="text" id="pid" name="pid" value="${entity.pid}" class="easyui-combotree" style="width:100%;height: 30px;" data-options="required:'required',url: '${ctx}/xfssgl/xfssdj/json'"/></td>
				</tr>
				
				<tr>
           		     <td class="width-15 active">	<label class="pull-right">设施图标:</label></td>
		         	 <td class="width-35" colspan="3"><input id="icon" name="icon"  style="width: 100%;height: 30px;" value="${entity.icon}"/></td>
          	    </tr>

				<tr>
           		     <td class="width-15 active"><label class="pull-right">备注：</label></td>
            		 <td class="width-35" colspan="3"><input name="M20" type="text" value="${entity.m20 }" class="easyui-textbox"
                		style="width: 100%;height: 60px;" data-options="multiline:true,validType:'length[0,250]' "></td>
          	    </tr>
			</tbody>
		</table>
      </form>
<script type="text/javascript">
	if(parent.parentPermId){
		$('#pid').val(parent.parentPermId);
	}else{
		$('#pid').val(0);
	}
	
	$(function(){ 
		if('${action}'=='xfssupdate'){
			var pmtpath='${xfssdj.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
		
		if('${action}'=='create'){
			var pmtpath='${xfssdj.pmt}';
			var url=pmtpath.split('||');
			initImg(url[0]);
		}
		
		//设施图标
		$('#icon').combobox({
			  valueField:'value',
			  textField:'text',
			  url:'${ctx}/xfssgl/xfssdj/geticons',
			  editable:false,
			     formatter:function(data){
			       return '<img class="item-img" width="50" height="50" src="${ctxStatic}/model/images/xfssgl/'+data.text+'"/><span class="item-text">'+data.value+'</span>';
			     },
		});
		
		function initImg(pmtpath){
			$("#img1").attr("src",pmtpath);
		}
	});	

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
	   	$("#category").val($("#category").combobox('getValue'));
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
		    	parent.categoryDg.treegrid('reload');
		    	parent.categoryDg.treegrid('clearChecked');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	
	});
	</script>
</body>
</html>