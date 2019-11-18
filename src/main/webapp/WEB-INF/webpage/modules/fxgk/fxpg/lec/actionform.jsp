<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加风险活动管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/data.js?v=1.1"></script>
</head>
<body>
<form id="inputForm"  action="${ctx}/fxpg/lec/${action}" method="post" class="form-horizontal">
   <table id="rwtable" class="table table-bordered">
      <tbody>
         <tr>
            <td class="width-15 active"><label class="pull-right">作业活动：</label></td>
            <td class="width-35" colspan="3"><input id="workaction" name="workaction" type="text" value="${entity.workaction }" class="easyui-textbox"
                  style="width: 100%;height: 30px;" data-options="required:true, validType:'length[0,250]' ">
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">潜在危害：</label></td>
            <td class="width-35" colspan="3"><input name="potentialhazard" id="potentialhazard" style="width: 100%;height: 50px;"
                  class="easyui-textbox" value="${entity.potentialhazard }" data-options=" multiline:true ,validType:'length[0,250]'" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">主要后果：</label></td>
            <td class="width-35" colspan="3"><input name="mainresult" id="mainresult" style="width: 100%;height: 50px;" class="easyui-textbox"
                  value="${entity.mainresult }" data-options="multiline:true , validType:'length[0,250]'" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">现有安全措施：</label></td>
            <td class="width-35" colspan="3"><input name="safetymeasure" id="safetymeasure" style="width: 100%;height: 50px;"
                  class="easyui-textbox" value="${entity.safetymeasure }" data-options="multiline:true , validType:'length[0,250]'" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">可能性(L)：</label></td>
            <td class="width-35" colspan="3"><input name="possibility" id="possibility" style="width: 100%;height: 30px;" class="easyui-combobox"
                  value="${entity.possibility }"/></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">暴露频率(E)：</label></td>
            <td class="width-35" ><input name="exposefrequency" id="exposefrequency" style="width: 100%;height: 30px;" class="easyui-combobox"
                  value="${entity.exposefrequency }"/></td>
            <td class="width-15 active"><label class="pull-right">严重度(C)：</label></td>
            <td class="width-35" ><input name="severity" id="severity" style="width: 100%;height: 30px;" class="easyui-combobox"
                  value="${entity.severity }" /></td>
         </tr>
         <tr>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">风险值：</label></td>
            <td class="width-35"><input name="riskvalue" id="riskvalue" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.riskvalue }" data-options="readonly:true" /></td>
            <td class="width-15 active"><label class="pull-right">风险等级：</label></td>
            <td class="width-35"><input name="risklevel" id="risklevel" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.risklevel }" data-options="readonly:true" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">改进措施：</label></td>
            <td class="width-35" colspan="3"><input name="improvemeasure" id="improvemeasure" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.improvemeasure }" data-options="validType:'length[0,250]'" /></td>
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
   var Lscore,Escore,Cscore;
   $(function(){
	   if (action == 'updateact') {
		   Lscore='${entity.possibility}';
           Escore='${entity.exposefrequency}';
           Cscore='${entity.severity}';
		   for(var index in lecL){
			   if(lecL[index].possibility=="${entity.possibility }"){
				   Lscore=lecL[index].score;
			   }
		   }
		   for(var index in lecE){
			   if(lecE[index].frequency=="${entity.exposefrequency }"){
				   Escore=lecE[index].score;
			   }
		   }
		   for(var index in lecC){
			   if(lecC[index].consequence=="${entity.severity }"){
				   Cscore=lecC[index].score;
			   }
		   }
		}
	   if(parent.riskid)
		   $("#ID1").val(parent.riskid);
	   $("#possibility").combobox({
		   required:'true',
		   editable: false,
		   panelHeight:'100',
		   valueField: 'score',
		   textField: 'possibility',
		   data : lecL,
		   onSelect : function(rec){
			   Lscore=rec.score;
			   //var severity=$('#severity').combobox("getValue");
			   //var exposefrequency=$('#exposefrequency').combobox("getValue");
			   if(Escore&&Cscore){
				   calculation(Lscore,Escore,Cscore);
			   }
		   }
	   });
	   $('#exposefrequency').combobox({
		   required:'true',
		   editable: false,
		   panelHeight:'100',
		   valueField: 'score',
		   textField: 'frequency',
		   data : lecE,
		   onSelect : function(rec){
			   Escore=rec.score;
			  // var severity=$('#severity').combobox("getValue");
			   //var possibility=$('#possibility').combobox("getValue");
			   if(Lscore&&Cscore){
				   calculation(Lscore,Escore,Cscore);
			   }
		   }
	   });
	   $('#severity').combobox({
		   required:'true',
		   editable: false,
		   panelHeight:'100',
		   valueField: 'score',
		   textField: 'consequence',
		   data : lecC,
		   onSelect : function(rec){
			   Cscore=rec.score;
			  //var possibility = $('#possibility').combobox("getValue");
			  //var exposefrequency = $('#exposefrequency').combobox("getValue");
			   if(Lscore&&Escore){
				   calculation(Lscore,Escore,Cscore);
			   }
		   }
	   });
   });
   //自动计算值
    function calculation(Lscore,Escore,Cscore){
    	var product=Lscore*Escore*Cscore;
    	$("#riskvalue").textbox("setValue",product);
    	for(var index in lecD){
    		var d=lecD[index];
    		if(product>=d.min&&product<d.max){
    			$("#risklevel").textbox("setValue",d.severity);
    			//$("#improvemeasure").textbox("setValue",d.severity);
    		}
    	}
    }
   
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