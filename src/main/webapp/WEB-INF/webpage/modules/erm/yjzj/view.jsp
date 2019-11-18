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
					<td class="width-35">${res.m1 }</td>
					<td class="width-15 active"><label class="pull-right">性别:</label></td>
		            <td class="width-35">男<input id="r1" type="radio"  name="M2" value="1" class="i-checks"/> 女<input id="r2" type="radio"  name="M2" value="0" class="i-checks"/><input id="hiddensex" type="hidden" value="${res.m2}" /></td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">出生年月：</label></td>
					<td class="width-35">${res.m3 }</td>
					<td class="width-15 active"><label class="pull-right">身份证号码：</label></td>
					<td class="width-35">${res.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">政治面貌：</label></td>
					<td class="width-35">${res.m5 }</td>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-35">${res.m7 }</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						${res.m6 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">毕业院校：</label></td>
					<td class="width-35">${res.m8 }</td>
					<td class="width-15 active"><label class="pull-right">最高学历：</label></td>
					<td class="width-35">${res.m9 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">工作年限：</label></td>
					<td class="width-35">${res.m10 }</td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35">${res.m11 }</td>
				</tr>
					
				<tr>
					<td class="width-15 active"><label class="pull-right">手机：</label></td>
					<td class="width-35">${res.m12 }</td>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35">${res.m13 }</td>
				</tr>	
					
				<tr>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35">${res.m14 }</td>
					<td class="width-15 active"><label class="pull-right">专业：</label></td>
					<td class="width-35">${res.m15 }</td>
				</tr>	
				
				<tr>
					<td class="width-15 active"><label class="pull-right">应急专长：</label></td>
					<td class="width-35">${res.m16 }</td>
					<td class="width-15 active"><label class="pull-right">专家类别：</label></td>
					<td class="width-35">${res.m17 }</td>
				</tr>	
					
				<tr>
					<td class="width-15 active"><label class="pull-right">电子邮件：</label></td>
					<td class="width-35" colspan="3">${res.m18 }</td>
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
					<td class="width-85" colspan="3" style="width: 100%;height: 80px;">
					${res.m20 }
					</td>
					
				</tr>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var ydlx = "${ydlx}";
	var ydlxArr = ydlx.split(",");
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M19']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	$("*[name='M19']").attr('disabled', 'true');
	
	val = $("#hiddensex").attr("value");
	if(val=="1"){
		$("input[name='M2'][value=1]").attr("checked",true); 
		}
	if(val=="0"){
		$("input[name='M2'][value=0]").attr("checked",true); 
		}
	$(function(){ 
		$(".erm_yjzj_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjzj_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
	});

	
</script>
</body>
</html>