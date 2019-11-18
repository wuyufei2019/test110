<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/xgfpd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">相关方单位名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${xgfpd.pddw }"  style="width: 100%;height: 30px;" class="easyui-textbox" />
					</td>
				</tr>
               <c:forEach items="${pdcont }" var="item" step="2" varStatus="status">
				<tr>
					<td class="width-20 active"><label class="pull-right">${item.m1 }：</label></td>
					<td class="width-30">
                    <input id="M${status.index }" name="radio_sorce" class="easyui-numberbox" value="${item.point }"
							style="width: 25%;height: 30px;" data-options="validType:['ddPrice[0,100]']" />
                    <input name="set_id" type="hidden" value="${item.setid }" />
                    <input name="record_id" type="hidden" value="${item.rid }" />
					<input type="radio" class="i-checks" <c:if test="${item.point eq '95'}">checked="true"</c:if> name="radio_M${status.index }" value="95" style="width: 25%"; />优
					<input type="radio" class="i-checks" <c:if test="${item.point eq '85'}">checked="true"</c:if> name="radio_M${status.index }" value="85" style="width: 25%"; />良
					<input type="radio" class="i-checks" <c:if test="${item.point eq '75'}">checked="true"</c:if> name="radio_M${status.index }" value="75" style="width: 25%"; />中
					<input type="radio" class="i-checks" <c:if test="${item.point eq '60'}">checked="true"</c:if> name="radio_M${status.index }" value="60" style="width: 25%"; />差
					</td>
               <c:if test="${status.index+1<pdcont.size()}">
					<td class="width-20 active"><label class="pull-right">${pdcont[status.index+1].m1 }：</label></td>
					<td class="width-30">
					<input type="text" id="M${status.index+1 }" name="radio_sorce" class="easyui-numberbox" style="height: 30px; width: 25%;" value="${pdcont[status.index+1].point}" data-options="validType:['ddPrice[0,100]']"  />
                    <input name="set_id" type="hidden" value="${pdcont[status.index+1].setid }" />
                    <input name="record_id" type="hidden" value="${pdcont[status.index+1].rid }" />
					<input type="radio" class="i-checks" <c:if test="${pdcont[status.index+1].point eq '95'}">checked="true"</c:if> name="radio_M${status.index+1 }" name="radio_M${status.index+1 }" value="95" style="width: 25%"; />优
					<input type="radio" class="i-checks" <c:if test="${pdcont[status.index+1].point eq '85'}">checked="true"</c:if> name="radio_M${status.index+1 }" value="85" style="width: 25%"; />良
					<input type="radio" class="i-checks" <c:if test="${pdcont[status.index+1].point eq '75'}">checked="true"</c:if>name="radio_M${status.index+1 }" value="75" style="width: 25%"; />中
					<input type="radio" class="i-checks" <c:if test="${pdcont[status.index+1].point eq '60'}">checked="true"</c:if> name="radio_M${status.index+1 }" value="60" style="width: 25%"; />差
					</td>
               </c:if>
				</tr>
            </c:forEach>

				<tr>
					<td class="width-20 active"><label class="pull-right">分数计算：</label></td>
               		<td class="width-30" colspan="4" style="text-align:center;"><label>
                     	<a class='btn btn-success btn-xs' onclick='count()'>分数计算</a>
                  	</label>
                  	</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>总评：</label></td>
					<td class="width-28"><input id="M11" name="M11" editable="false" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgfpd.m11 }"  /></td>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>评定等级：</label></td>
					<td class="width-28"><input id="M12" name="M12" editable="false" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgfpd.m12 }" /></td>
				</tr>

				<c:if test="${not empty xgfpd.id}">
					<input type="hidden" name="ID" value="${xgfpd.id}" />
					<input type="hidden" name="ID1" value="${xgfpd.id1}" />
					<input type="hidden" name="ID2" value="${xgfpd.id2}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${xgfpd.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${xgfpd.s3}" />
				</c:if>

			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	


function isnull(count){
	if(count){
		return count;
	}else{
		return 0;
	}
}

function count(){
	//根据公式计算总分
	var points=$("[name='radio_sorce']");
	var count=0;
	$.each(points,function(index,item){
		count+=parseInt(isnull($(item).val()));
	});
	sum=count/points.length;
	var level="";
	if(sum>=90){
		level="优秀";
	}else if(sum>=80&&sum<90){
		level="良好";
	}else if(sum>=70&&sum<80){
		level="中等";
	}else if(sum<70){
		level="差";
	}
	//将总评和等级赋值
	$("#M11").textbox("setValue",Math.round(sum));
	$("#M12").textbox("setValue",level);
}

function doSubmit(){
	count();
	$("#inputForm").serializeObject();
	$("#inputForm").submit(); 
}

if('${action}'=='update'){
	/* if('${xgfpd.m1}'!=null&&'${xgfpd.m1}'!='')
		$("input[name='radio_M1'][value=${xgfpd.m1}]").attr("checked",true);
	if('${xgfpd.m2}'!=null&&'${xgfpd.m2}'!='')
		$("input[name='radio_M2'][value=${xgfpd.m2}]").attr("checked",true);
	if('${xgfpd.m3}'!=null&&'${xgfpd.m3}'!='')	
		$("input[name='radio_M3'][value=${xgfpd.m3}]").attr("checked",true);
	if('${xgfpd.m4}'!=null&&'${xgfpd.m4}'!='')
		$("input[name='radio_M4'][value=${xgfpd.m4}]").attr("checked",true);
	if('${xgfpd.m5}'!=null&&'${xgfpd.m5}'!='')	
		$("input[name='radio_M5'][value=${xgfpd.m5}]").attr("checked",true);
	if('${xgfpd.m6}'!=null&&'${xgfpd.m6}'!='')
		$("input[name='radio_M6'][value=${xgfpd.m6}]").attr("checked",true);
	if('${xgfpd.m7}'!=null&&'${xgfpd.m7}'!='')
		$("input[name='radio_M7'][value=${xgfpd.m7}]").attr("checked",true);
	if('${xgfpd.m8}'!=null&&'${xgfpd.m8}'!='')
		$("input[name='radio_M8'][value=${xgfpd.m8}]").attr("checked",true);
	if('${xgfpd.m9}'!=null&&'${xgfpd.m9}'!='')
		$("input[name='radio_M9'][value=${xgfpd.m9}]").attr("checked",true);
	if('${xgfpd.m10}'!=null&&'${xgfpd.m10}'!='')
		$("input[name='radio_M10'][value=${xgfpd.m10}]").attr("checked",true); */
}

$(function(){
	//radio的选中事件
	$('.i-checks').on('ifChecked', function score(event){   
	    var arry=event.target.name.split("_");//获取分数和id结合的字符串并分开 
	    $("#"+arry[1]).textbox("setValue",$(event.target).val());
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