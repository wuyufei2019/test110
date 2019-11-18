<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加风险活动管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/data.js?v=1.1"></script>
</head>
<body>
<form id="inputForm"  action="${ctx}/fxpg/jha/${action}" method="post" class="form-horizontal">
   <table id="rwtable" class="table table-bordered">
      <tbody>
         <tr>
            <td class="width-15 active"><label class="pull-right">工作步骤：</label></td>
            <td class="width-35" colspan="3"><input id="workstep" name="workstep" type="text" value="${entity.workstep }" class="easyui-textbox"
                  style="width: 100%;height: 30px;" data-options="required:true, validType:'length[0,250]' ">
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">潜在危害：</label></td>
            <td class="width-35" colspan="3"><input name="potentialhazard" id="potentialhazard" style="width: 100%;height: 50px;"
                  class="easyui-textbox" value="${entity.potentialhazard }" data-options="multiline:true ,validType:'length[0,250]'" /></td>
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
            <td class="width-15 active"><label class="pull-right">可能性：</label></td>
            <td class="width-35" colspan="3"><input name="possibility" id="possibility" style="width: 100%;height: 30px;" class="easyui-combobox"
                  value="${entity.possibility }"/></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">严重度：</label></td>
            <td class="width-35" colspan="3"><input name="severity" id="severity" style="width: 100%;height: 30px;" class="easyui-combogrid"
                  value="${entity.severity }" /></td>
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
   $(function(){
	   if(parent.riskid)
		   $("#ID1").val(parent.riskid);
	   $("#possibility").combobox({
		   required:'true',
		   editable: false,
		   panelHeight:'100',
		   valueField: 'level',
		   textField: 'standard',
		   data : possibility,
		   onSelect : function(rec){
			   var severity=$('#severity').combogrid("getValue");
			   if(severity){
				   calculation(severity,rec.level);
			   }
		   }
	   });
	   $('#severity').combogrid({
		    required:'true',
		    nowrap :false,
		    editable: false,
		    panelWidth:'100%',
		    panelHeight:'auto',
		    idField:'level',
		    textField:'text',
		    data: severity,
		    fitColumns :true,
		    scrollbarSize :0,
		    columns:[[
		    {field:'level',title:'等级',width:30},
		    {field:'law',title:'法律、法规及其他要求',width:110},
		    {field:'person',title:'人员',width:70},
		    {field:'propertyloss',title:'财产损失/万元',width:60},
		    {field:'shutdown',title:'停工',width:100},
		    {field:'companyimage',title:'公司形象',width:70}
		    ]],
		    onSelect : function(index){
				   var possibility=$('#possibility').combobox("getValue");
				   if(possibility){
					   calculation(severity[index].level,possibility);
				   }
			   }
		    });
   });
   //自动计算值
    function calculation(severity,possibility){
    	var product=severity*possibility;
    	$("#riskvalue").textbox("setValue",product);
    	for(var index in measure){
    		var m=measure[index];
    		if(product>=m.min&&product<=m.max){
    			$("#risklevel").textbox("setValue",m.level);
    			$("#improvemeasure").textbox("setValue",m.measure);
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