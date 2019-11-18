<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加危险废物产生记录</title>
<meta name="decorator" content="default" />
</head>
<body>
<form id="inputForm"  action="${ctx}/hjbh/wxfw/${action}" method="post" class="form-horizontal">
   <table id="rwtable" class="table table-bordered">
      <tbody>
         <tr>
            <td class="width-15 active"><label class="pull-right">废物名称：</label></td>
            <td class="width-35"><input id="trashid" name="trashid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${entity.trashid }"
               data-options="panelHeight:'100',required: true,valueField: 'id',textField: 'name',url:'${ctx}/hjbh/wxfwgl/wxfwjson'"/></td>
               <input type="hidden" id="trashname" name="trashname" />
            <td class="width-15 active"><label class="pull-right">废物类别：</label></td>
            <td class="width-35"><input id="trashkind" name="trashkind" class="easyui-textbox" readonly="true" style="width: 100%;height: 30px;" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">产生源：</label></td>
            <td class="width-35"><input name="resource" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.resource }"/></td>
            <td class="width-15 active"><label class="pull-right">产生量：</label></td>
            <td class="width-35"><input name="amount" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.amount }" data-options=" validType:'mone'"/></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">计量单位：</label></td>
            <td class="width-35"><input name="unit" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.unit }" /></td>
            <td class="width-15 active"><label class="pull-right">废物流向：</label></td>
            <td class="width-35"><input name="direction" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.direction }" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">外单位处置的企业：</label></td>
            <td class="width-35"><input name="m1" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.m1 }"/></td>
            <td class="width-15 active"><label class="pull-right">内部利用处理量：</label></td>
            <td class="width-35"><input name="m2" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.m2 }" data-options=" validType:'mone'"/></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">委托利用处理量：</label></td>
            <td class="width-35"><input name="m3" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.m3 }" data-options=" validType:'mone'"/></td>
            <td class="width-15 active"><label class="pull-right">累计贮存量：</label></td>
            <td class="width-35"><input name="m4" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.m4 }" data-options=" validType:'mone'"/></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">年度产生量：</label></td>
            <td class="width-35"><input name="m5" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.m5 }" data-options=" validType:'mone'"/></td>
         </tr>

            <input type="hidden" id="recordid" name="recordid" value="${entity.recordid}" />
         <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}" />
         </c:if>
      </tbody>
   </table>
 </form>
   <script type="text/javascript">
   var action='${action}';
   $(function(){
	   if(parent.RecodId)
		   $("#recordid").val(parent.RecodId);
	   $("#trashid").combobox({
		   onSelect : function(rec){
			   $("#trashname").val(rec.name);
			   $("#trashkind").textbox("setValue",rec.kind);
		   },	
		   onLoadSuccess :function (param, success, error) {
			   //应该有更好的方法优化，暂时先这样
			   if('${entity.trashid}'){
			   		$("#trashid").combobox("unselect", ${entity.trashid});
			  		$("#trashid").combobox("select", ${entity.trashid});
			   }
		   }
	   });
   });
   
    function getDate(){
    	return new Date().getTime();
    }
    var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function doSubmit(){
    	if(parent.RecodId){
    		$("#inputForm").submit();
    	 }
    	 else{
             	var isValid = $("#inputForm").form('validate');
             	if(!isValid)
             		return false;
             	var obj=$("#inputForm").serializeObject();
            		
             	if(action=='update'){
             		for(var index in parent.TableData){
             			if(parent.TableData[index].time==parent.TIME){
             				parent.TableData.splice(index,1,Object.assign(obj,{"time":getDate()}));
             			}
             		}
             	}else if(action=='createdetail'){
             		parent.TableData.push(Object.assign(obj,{"time":getDate()}));
             	}
             	var accordion=parent.$('#accordion');
             	accordion.accordion({height : 'auto'});//调整datagrid高度
         		if(!accordion.accordion('getPanel',0)){
         			//增加panel
               		accordion.accordion('add', {
               			title: '废物产生记录附表',
               			content: '<table id="detail_dg"></table>',
               			selected: true,
               		});
         		 }
         		parent.dg = parent.$('#detail_dg').datagrid(parent.DatagridSetting);
         	    parent.dg.datagrid("loadData",parent.TableData);
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