<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急预案信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/erm/yjyagl/index.js?v=1.7"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="erm_yjyagl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
        <c:if test="${usertype ne '1' }">
          <input id="cx_type_con" name="cx_type_con" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '类型 ',editable:false ,data: [{value:'1',text:'安监'},
																																		        {value:'2',text:'企业'}],
																																		        onLoadSuccess: function () { 
																																                 	$('#cx_type_con').combobox('setValue', '1');
																																				},
																																				onSelect:function(rec){
																																					if(rec.value=='1'){
																																						$('#divper').show();
																																						$('#divper2').show();
																																						search();
																																					}
																																					if(rec.value=='2'){
																																						$('#divper').hide();
																																						$('#divper2').hide();
																																						search();
																																					}
																																					
																																				}"/>
          </c:if>
		<input type="text" id="number" name="number" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号 '"/>  
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="refresh()" ><i class="fa fa-refresh"></i> 刷新</span>
	</div>
	</form>

</div>

<table id="erm_yjyagl_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>