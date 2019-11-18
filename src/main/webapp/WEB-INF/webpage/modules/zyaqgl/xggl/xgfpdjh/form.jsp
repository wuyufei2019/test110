<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zyaqgl/xggl/xgfpdjh/index.js?v=1.1"></script>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/xgfpdjh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">相关方名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${xgfpdjh.id2 }" id="ID2" name="ID2" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/zyaqgl/xgfpdjh/xgfdwjson'" />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">评定日期：</label></td>
					<td class="width-30"><input id="SignedStartDate" name="M1" editable="false" class="easyui-datebox" value="${xgfpdjh.m1 }"
							style="width: 100%;height: 30px;" /></td>
					<td class="width-20 active"><label class="pull-right">评定主持人：</label></td>
					<td class="width-30">
					<input type="text" id="M3" name="M3" class="easyui-textbox" style="height: 30px; width: 100%;" value="${xgfpdjh.m3}" data-options="validType:'length[1,25]' "  />
					</td>
				</tr>

				<tr>
                    <td class="width-20 active"><label class="pull-right">合作项目清单：</label></td>
					<td class="width-30" colspan="3"><input name="M2" class="easyui-textbox" value="${xgfpdjh.m2 }"
							style="width: 100%;height: 60px;"
							data-options="validType:'length[0,100]',multiline:true" /></td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">评定人员：</label></td>
					<td class="width-35" colspan="3">
						<!-- 负责传值 -->
						<div id="pdryIDs">
							<input id="pdryids" type="hidden" name="pdryids" />
							<input id="pdryids2" type="hidden" name="pdryids2" />
							<div id="pdryList"></div>
							<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openpdrylist()" title="选择评定人员"><i class="fa fa-plus"></i> 选择评定人员</a>
						</div>
					</td>
				</tr>
				
				<c:if test="${not empty xgfpdjh.id}">
					<input type="hidden" name="ID" value="${xgfpdjh.id}" />
					<input type="hidden" name="ID1" value="${xgfpdjh.id1}" />
					<input type="hidden" name="ID2" value="${xgfpdjh.id2}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${xgfpdjh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${xgfpdjh.s3}" />
					<input type="hidden" name="M4" value="${xgfpdjh.m4}" />
					<input type="hidden" name="M5" value="${xgfpdjh.m5}" />
					<input type="hidden" name="M12" value="${xgfpdjh.m12}" />
				</c:if>

				<c:if test="${type eq 'sh'}">
				<input type="hidden" name="role" value="2" />
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30">							
							<c:choose>
								<c:when test="${xgfpdjh.m6=='0'}">
									<input type="radio" value="1" class="i-checks" name="M6" />同意
									<input type="radio" value="0" class="i-checks"  name="M6" checked="checked" />不同意
								</c:when>
								<c:otherwise>
									<input type="radio" value="1" class="i-checks" name="M6" checked="checked" />同意
									<input type="radio" value="0" class="i-checks"  name="M6" />不同意
								</c:otherwise>
							</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-datebox" editable="false" value="${xgfpdjh.m8 }"  />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" colspan="3"><input readonly="true" style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-textbox" value="${xgfpdjh.m7 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				</c:if>
				
				<c:if test="${type eq 'sp'}">
				<input type="hidden" name="role" value="3" />
				<tr>
					<td class="width-20 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-30">							
					       <c:choose>
								<c:when test="${xgfpdjh.m9=='0'}">
									<input type="radio" value="1" class="i-checks" name="M9" />同意
									<input type="radio" value="0" class="i-checks"  name="M9" checked="checked" />不同意
								</c:when>
								<c:otherwise>
									<input type="radio" value="1" class="i-checks" name="M9" checked="checked" />同意
									<input type="radio" value="0" class="i-checks"  name="M9" />不同意
								</c:otherwise>
							</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" id="M11" name="M11" class="easyui-datebox" value="${xgfpdjh.m11 }" editable="false" data-options="validType:['length[0,50]'] " />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" colspan="3"><input readonly="true" style="width: 100%;height: 30px;" id="M10" name="M10" class="easyui-textbox" value="${xgfpdjh.m10 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<input type="hidden" name="M8"
					value="<fmt:formatDate value="${xgfpdjh.m8}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="M6" value="${xgfpdjh.m6}" />
				<input type="hidden" name="M7" value="${xgfpdjh.m7}" />
				</c:if>		
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

function doSubmit(){
	var divtxt=$("#pdryList").text();
	if(divtxt==null||divtxt==''){
		layer.msg("请选择评定人员！",{time: 1000});
		return;
	}
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
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});

if ('${action}' == 'update') {
			var $list = $("#pdryList");
			var ids = '${idname}';
			var pdryids="";
			if (ids != null && ids != '' && ids != 'null') {
				var arry3 = ids.split(",");
				for (var i = 0; i < arry3.length-1; i++) {
					var arry4 = arry3[i].split("||");
					var $li = $("<div id=\"" +arry4[0]+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arry4[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removepdry('"
							+ arry4[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					pdryids += arry4[0] + ",";
					$list.append($li);
				}
			}
			$("#pdryids").val(pdryids);
			$("#pdryids2").val(pdryids);
}
</script>
</body>
</html>