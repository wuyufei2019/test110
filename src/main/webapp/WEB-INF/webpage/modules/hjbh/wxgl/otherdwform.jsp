<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加外单位信息</title>
<meta name="decorator" content="default" />
</head>
<body>
<form id="inputForm"  action="${ctx}/hjbh/otherDw/${action}" method="post" class="form-horizontal">
   <table id="rwtable" class="table table-bordered">
      <tbody>
         <tr>
            <td class="width-15 active"><label class="pull-right">单位名称：</label></td>
            <td class="width-35" colspan="3"><input id="name" name="name" type="text" value="${entity.name }" class="easyui-textbox"
                  style="width: 100%;height: 30px;" data-options="required:true, validType:'length[0,250]' "></td>
             <td class="width-15 active"><label class="pull-right">单位所在地：</label></td>
            <td class="width-35" colspan="3"><input name="location" id="location" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.location }" data-options="" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">危险废物经营许可证号：</label></td>
            <td class="width-35" colspan="3"><input name="permit_num" id="permit_num" style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.permit_num }" data-options="multiline:true , validType:'length[0,250]'" /></td>
            <td class="width-15 active"><label class="pull-right">方法描述：</label></td>
            <td class="width-35" colspan="3"><input name="description" id="description" style="width: 100%;height: 30px;"
                  class="easyui-textbox" value="${entity.description }" data-options="" /></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">联系人：</label></td>
            <td class="width-35" colspan="3"><input name="contact_name" id="contact_name" data-options="required:true, validType:'length[0,250]' " style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.contact_name }"/></td>
            <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-35" colspan="3"><input name="contact_phone" id="contact_phone" data-options="required:true, validType:'mobile' " style="width: 100%;height: 30px;" class="easyui-textbox"
                  value="${entity.contact_phone }" /></td>
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
	   if(parent.riskid){
		   $("#ID1").val(parent.riskid);
	   }
		  
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
             	
             	if(action=='odwupdate'){
             		for(var index in parent.dgdata){
             			if(parent.dgdata[index].time==parent.time){
             				parent.dgdata.splice(index,1,Object.assign(obj,{"time":getDate()}));
             			}
             		}
             	}else if(action=='odwcreate'){
             		parent.dgdata.push(Object.assign(obj,{"time":getDate()}));
             	}
             	var accordion=parent.$('#accordion');
             	accordion.accordion({height : 'auto'});//调整datagrid高度
         		if(!accordion.accordion('getPanel',0)){
         			//增加panel
               		accordion.accordion('add', {
               			title: '外单位信息',
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