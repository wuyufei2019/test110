<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急专家管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yjzj/${action}"  method="post" class="form-horizontal" >
     		
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M1" class="easyui-textbox " value="${res.m1 }" data-options="required:'true',validType:['cczu_CHSENG','length[0,10]']"/></td>
					<td class="width-15 active"><label class="pull-right">性别:</label></td>
		            <td class="width-35">男<input id="r1" type="radio"  name="M2" value="1" class="i-checks"/> 女<input id="r2" type="radio"  name="M2" value="0" class="i-checks"/> <input id="hiddensex" type="hidden" value="${res.m2}" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">出生年月：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M3" class="easyui-datebox" value="${res.m3 }" data-options=""/></td>
					<td class="width-15 active"><label class="pull-right">身份证号码：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M4" class="easyui-textbox " value="${res.m4 }" data-options="validType:'idCode'"/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">政治面貌：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" class="easyui-combobox" name="M5" value="${res.m5 }" style="width: 100%;height: 30px;" data-options="
								editable:false ,data: [
								        {value:'中共党员',text:'中共党员 '},
								        {value:'中共预备党员',text:'中共预备党员 '},
								        {value:'共青团员',text:'共青团员 '},
								        {value:'民革党员',text:'民革党员'},
								        {value:'民盟盟员',text:'民盟盟员 '},
								        {value:'民建会员',text:'民建会员'},
								        {value:'民进会员',text:'民进会员'},
								        {value:'农工党党员',text:'农工党党员'},
								        {value:'致公党党员',text:'致公党党员 '},
								        {value:'九三学社社员',text:'九三学社社员'},
								        {value:'台盟盟员',text:'台盟盟员 '},
								        {value:'无党派民主人士',text:'无党派民主人士'},
								        {value:'群众（现称普通公民）',text:'群众（现称普通公民）'} ]
						    "/></td>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M7" class="easyui-textbox " value="${res.m7 }" data-options="validType:'length[0,25]'"/></td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" name="M6" class="easyui-textbox" value="${res.m6 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">毕业院校：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M8" class="easyui-textbox " value="${res.m8 }" data-options="validType:'length[0,25]'"/></td>
					<td class="width-15 active"><label class="pull-right">最高学历：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" class="easyui-combobox" name="M9" value="${res.m9 }" data-options="
								editable:false ,data: [
								        {value:'初中',text:'初中'},
								        {value:'高中',text:'高中'},
								        {value:'中专',text:'中专'},
								        {value:'大专',text:'大专'},
								        {value:'本科',text:'本科'},
								        {value:'硕士',text:'硕士'},
								        {value:'博士',text:'博士'} ]
						    "/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">工作年限：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M10" class="easyui-numberbox" value="${res.m10 }" data-options="min:0,max:9999"/></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M11" class="easyui-textbox" value="${res.m11 }" data-options="" /></td>
				</tr>
					
				<tr>
					<td class="width-15 active"><label class="pull-right">手机：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M12" class="easyui-textbox " value="${res.m12 }" data-options=""/></td>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M13" class="easyui-textbox" value="${res.m13 }" data-options="validType:'length[0,20]'" /></td>
				</tr>	
					
				<tr>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" class="easyui-combobox" name="M14" value="${res.m14 }" data-options="
								editable:false ,panelHeight:'auto' ,data: [
								        {value:'正高级',text:'正高级'},
								        {value:'副高级',text:'副高级'},
								        {value:'中级',text:'中级'},
								        {value:'初级',text:'初级'} ]
						    "/></td>
					<td class="width-15 active"><label class="pull-right">专业：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M15" class="easyui-textbox " value="${res.m15 }" data-options="validType:'length[0,20]'"/></td>
				</tr>	
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应急专长：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M16" class="easyui-textbox " value="${res.m16 }" data-options="validType:'length[0,100]'"/></td>
					<td class="width-15 active"><label class="pull-right">专家类别：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M17" class="easyui-combobox" value="${res.m17 }" data-options="editable:false ,data: [
								        {value:'化工类',text:'化工类'},
								        {value:'机械类',text:'机械类'},
								        {value:'电气类',text:'电气类'},
								        {value:'建筑类',text:'建筑类'},
								        {value:'工贸类',text:'工贸类'},
								        {value:'冶金类',text:'冶金类'},
								        {value:'检测类',text:'检测类'},
								        {value:'职业病危害',text:'职业病危害'},
								        {value:'其他',text:'其他'} ]
						    " /></td>
				</tr>	
					
				<tr>
					<td class="width-15 active"><label class="pull-right">电子邮件：</label></td>
					<td class="width-35" colspan="3"><input style="width: 100%;height: 30px;" name="M18" class="easyui-textbox " value="${res.m18 }" data-options="validType:'email'"/></td>
				</tr>		
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">应对事故类型：</label></td><td colspan="3"><input type="checkbox" id="ydlx1" name="M19" value="1" /><a style="cursor:default;" onclick="ydlxChange1()">物体打击</a>
																		                <input type="checkbox" id="ydlx2" name="M19" value="2" /><a style="cursor:default;" onclick="ydlxChange2()">车辆伤害</a>
																		                <input type="checkbox" id="ydlx3" name="M19" value="3" /><a style="cursor:default;" onclick="ydlxChange3()">机械伤害</a>
																		                <input type="checkbox" id="ydlx4" name="M19" value="4" /><a style="cursor:default;" onclick="ydlxChange4()">起重伤害</a>
																		                <input type="checkbox" id="ydlx5" name="M19" value="5" /><a style="cursor:default;" onclick="ydlxChange5()">触电</a><br/>
																		                <input type="checkbox" id="ydlx6" name="M19" value="6" /><a style="cursor:default;" onclick="ydlxChange6()">淹溺</a>
																		                <input type="checkbox" id="ydlx7" name="M19" value="7" /><a style="cursor:default;" onclick="ydlxChange7()">灼烫</a>
																		                <input type="checkbox" id="ydlx8" name="M19" value="8" /><a style="cursor:default;" onclick="ydlxChange8()">火灾</a>
																		                <input type="checkbox" id="ydlx9" name="M19" value="9" /><a style="cursor:default;" onclick="ydlxChange9()">高处坠落</a>
																		                <input type="checkbox" id="ydlx10" name="M19" value="10" /><a style="cursor:default;" onclick="ydlxChange10()">坍塌</a><br/>
																		                <input type="checkbox" id="ydlx11" name="M19" value="11" /><a style="cursor:default;" onclick="ydlxChange11()">冒顶片帮</a>
																		                <input type="checkbox" id="ydlx12" name="M19" value="12" /><a style="cursor:default;" onclick="ydlxChange12()">透水</a>
																		                <input type="checkbox" id="ydlx13" name="M19" value="13" /><a style="cursor:default;" onclick="ydlxChange13()">放炮</a>
																		                <input type="checkbox" id="ydlx14" name="M19" value="14" /><a style="cursor:default;" onclick="ydlxChange14()">火药爆炸</a>
																		                <input type="checkbox" id="ydlx15" name="M19" value="15" /><a style="cursor:default;" onclick="ydlxChange15()">瓦斯爆炸</a><br/>
																		                <input type="checkbox" id="ydlx16" name="M19" value="16" /><a style="cursor:default;" onclick="ydlxChange16()">锅炉爆炸</a>
																		                <input type="checkbox" id="ydlx17" name="M19" value="17" /><a style="cursor:default;" onclick="ydlxChange17()">容器爆炸</a>
																		                <input type="checkbox" id="ydlx18" name="M19" value="18" /><a style="cursor:default;" onclick="ydlxChange18()">其它爆炸</a>
																		                <input type="checkbox" id="ydlx19" name="M19" value="19" /><a style="cursor:default;" onclick="ydlxChange19()">中毒和窒息</a>
																		                <input type="checkbox" id="ydlx20" name="M19" value="20" /><a style="cursor:default;" onclick="ydlxChange20()">其它伤害</a></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M20" type="text" value="${res.m20 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
					
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="userid" value="${res.userid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
				<tbody>
			</table>
		  	
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

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

	var action = "${action}";
	var ydlx = "${ydlx}";
	var ydlxArr = ydlx.split(",");
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M19']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	if (action == 'create') {
		$("input[name='M2'][value=1]").attr("checked",true); 
		for(var i=1;i<=20;i++){
			$("input[name='M19']:checkbox[value='"+i+"']").attr('checked','true');
		}
	} else if (action == 'update') {
		$("input[name='M2'][value=${res.m2}]").attr("checked",true);
		url="/erm/yjzb/update";
	} 
	
	$(function(){ 
		$(".erm_yjzb_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjzb_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
	});
	
	function ydlxChange1(){
		$('#ydlx1').prop('checked',!$("#ydlx1").prop("checked"));
	}
	function ydlxChange2(){
		$('#ydlx2').prop('checked',!$("#ydlx2").prop("checked"));
	}
	function ydlxChange3(){
		$('#ydlx3').prop('checked',!$("#ydlx3").prop("checked"));
	}
	function ydlxChange4(){
		$('#ydlx4').prop('checked',!$("#ydlx4").prop("checked"));
	}
	function ydlxChange5(){
		$('#ydlx5').prop('checked',!$("#ydlx5").prop("checked"));
	}
	function ydlxChange6(){
		$('#ydlx6').prop('checked',!$("#ydlx6").prop("checked"));
	}
	function ydlxChange7(){
		$('#ydlx7').prop('checked',!$("#ydlx7").prop("checked"));
	}
	function ydlxChange8(){
		$('#ydlx8').prop('checked',!$("#ydlx8").prop("checked"));
	}
	function ydlxChange9(){
		$('#ydlx9').prop('checked',!$("#ydlx9").prop("checked"));
	}
	function ydlxChange10(){
		$('#ydlx10').prop('checked',!$("#ydlx10").prop("checked"));
	}
	function ydlxChange11(){
		$('#ydlx11').prop('checked',!$("#ydlx11").prop("checked"));
	}
	function ydlxChange12(){
		$('#ydlx12').prop('checked',!$("#ydlx12").prop("checked"));
	}
	function ydlxChange13(){
		$('#ydlx13').prop('checked',!$("#ydlx13").prop("checked"));
	}
	function ydlxChange14(){
		$('#ydlx14').prop('checked',!$("#ydlx14").prop("checked"));
	}
	function ydlxChange15(){
		$('#ydlx15').prop('checked',!$("#ydlx15").prop("checked"));
	}
	function ydlxChange16(){
		$('#ydlx16').prop('checked',!$("#ydlx16").prop("checked"));
	}
	function ydlxChange17(){
		$('#ydlx17').prop('checked',!$("#ydlx17").prop("checked"));
	}
	function ydlxChange18(){
		$('#ydlx18').prop('checked',!$("#ydlx18").prop("checked"));
	}
	function ydlxChange19(){
		$('#ydlx19').prop('checked',!$("#ydlx19").prop("checked"));
	}
	function ydlxChange20(){
		$('#ydlx20').prop('checked',!$("#ydlx20").prop("checked"));
	}
	
</script>
</body>
</html>