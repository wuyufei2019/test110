<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加风险活动管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/data.js?v=1.1"></script>
</head>
<body>
<form id="inputForm"  action="${ctx}/fxpg/hazop/${action}" method="post" class="form-horizontal">
   <table id="rwtable" class="table table-bordered">
      <tbody>
         <tr>
            <td class="width-20 active"><label class="pull-right">引导词：</label></td>
            <td class="width-30"><input id="guidanceword" name="guidanceword" type="text" value="${entity.guidanceword }" class="easyui-combobox"
                  style="width: 100%;height: 30px;" data-options="required:true,valueField:'value', textField:'value', validType:'length[0,50]',data:[{'value':'无（NO）'},
                  {'value':'过多（MORE）'},{'value':'过少（LESS）'},{'value':'伴随（AS WELL AS）'},{'value':'部分（PART OF）'},{'value':'相反（REVERSE）'},
                  {'value':'异常（OTHER THAN）'},{'value':'超前（EARLY）'},{'value':'迟后（LATE）'},{'value':'过先（BEFORE）'},{'value':'过后（AFTER）'},] ">
            <td class="width-20 active"><label class="pull-right">要素：</label></td>
            <td class="width-30"><input name="factor" id="factor" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.factor }" data-options=" validType:'length[0,50]'" /></td>         
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">偏差：</label></td>
            <td class="width-30" colspan="3"><input name="deviation" id="deviation" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.deviation }" data-options=" validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">可能原因：</label></td>
            <td class="width-30" colspan="3"><input name="possiblereason" id="possiblereason" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.possiblereason }" data-options="multiline:true , validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">后果：</label></td>
            <td class="width-30" colspan="3"><input name="result" id="result" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.result }" data-options=" multiline:true ,validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">安全措施：</label></td>
            <td class="width-30" colspan="3"><input name="safetymeasure" id="safetymeasure" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.safetymeasure }" data-options="multiline:true , validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">注释：</label></td>
            <td class="width-30" colspan="3"><input name="note" id="note" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.note }" data-options="validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">建议安全措施：</label></td>
            <td class="width-30" colspan="3"><input name="suggestion" id="suggestion" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.suggestion }" data-options="multiline:true ,validType:'length[0,50]'" /></td>
         </tr>
         <tr>
            <td class="width-20 active"><label class="pull-right">执行人：</label></td>
            <td class="width-30" colspan="3"><input name="executor" id="executor" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.executor }" data-options="validType:'length[0,50]'" /></td>
         </tr>

            <input type="hidden" id="ID1" name="ID1" value="${entity.ID1}" />
         <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}" />
         </c:if>
      </tbody>
   </table>
 </form>
   <script type="text/javascript">
   var action='${action}';
   $(function(){
	   if(parent.riskid)
		   $("#ID1").val(parent.riskid);
   });
    function getDate(){
    	return new Date().getTime();
    }
    var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function doSubmit(){
    	if(parent.riskid){
    		$("#inputForm").submit();
    	 }
    	 else{
             	var isValid = $("#inputForm").form('validate');
             	if(!isValid)
             		return false;
             	var obj=$("#inputForm").serializeObject();
            		
             	if(action=='update'){
             		for(var index in parent.dgdata){
             			if(parent.dgdata[index].time==parent.time){
             				parent.dgdata.splice(index,1,Object.assign(obj,{"time":getDate()}));
             			}
             		}
             	}else if(action=='createact'){
             		parent.dgdata.push(Object.assign(obj,{"time":getDate()}));
             	}
             	var accordion=parent.$('#accordion');
             	accordion.accordion({height : 'auto'});//调整datagrid高度
         		if(!accordion.accordion('getPanel',0)){
         			//增加panel
               		accordion.accordion('add', {
               			title: '风险活动',
               			content: '<table id="fxhd_dg"></table>',
               			selected: true,
               		});
         		 }
         		parent.dg = parent.$('#fxhd_dg').datagrid(parent.data);
         	    parent.dg.datagrid("loadData",parent.dgdata);
         		parent.layer.close(layerindex);//关闭对话框。
    	 }
    }
	$(function() {
		var flag = true;
		$('#inputForm').form({
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (isValid && flag) {
					flag = false;
					$.jBox.tip("正在提交，请稍等...", 'loading', {
						opacity : 0
					});
					return true;
				}
				return false; // 返回false终止表单提交
			},
			success : function(data) {
				$.jBox.closeTip();
				if (data == 'success')
					parent.layer.open({
						icon : 1,
						title : '提示',
						offset : 'rb',
						content : '操作成功！',
						shade : 0,
						time : 2000
					});
				else
					parent.layer.open({
						icon : 2,
						title : '提示',
						offset : 'rb',
						content : '操作失败！',
						shade : 0,
						time : 2000
					});
				parent.d.datagrid('reload');
				parent.layer.close(layerindex);//关闭对话框。
			}
		});

	});
    
   </script>
</body>
</html>