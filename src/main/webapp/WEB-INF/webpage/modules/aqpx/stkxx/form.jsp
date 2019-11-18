<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>试题库信息</title>
	<meta name="decorator" content="default"/>
		<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/stkxx/${action}"  method="post" class="form-horizontal" >
			

		  	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="ID2" value="${aqpxlist.ID2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'120px', required:'true',
								editable:false ,valueField: 'id',textField: 'text',url: '${ctx}/aqpx/kcxx/json'  "/></td>
					
					<td class="width-15 active"><label class="pull-right">题目类型：</label></td>
					<td class="width-35"><input name="M1" style="width: 100%;height: 30px;" class="easyui-combobox"
								value="${aqpxlist.m1 }" data-options="required:'true',panelHeight:'auto', 
								editable:false ,data: [
										{value:'1',text:'单选'},
								        {value:'2',text:'多选'},
								        {value:'3',text:'填空'},
								        {value:'4',text:'判断'} ],
								        onSelect: function(rec){
								       	if(rec.value=='3'){
								       		$('#stkxx_m3').textbox('setValue', '');
								       		$('#stkxx_m4').textbox('setValue', '');
								       		$('#stkxx_m5').textbox('setValue', '');
								       		$('#stkxx_m6').textbox('setValue', '');
								       		$('#stkxx_m7').textbox('setValue', '');
								       	}else if(rec.value=='4'){
								       		$('#stkxx_m3').textbox('setValue', '对');
								       		$('#stkxx_m4').textbox('setValue', '错');
								       		$('#stkxx_m5').textbox('setValue', '');
								       		$('#stkxx_m6').textbox('setValue', '');
								       		$('#stkxx_m7').textbox('setValue', '');}
								        } "/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">题干：</label></td>
					<td class="width-35" colspan="3"><input name="M2" required="true" style="width: 100%;height: 50px;" class="easyui-textbox"
								value="${aqpxlist.m2 }" data-options="required:'true',multiline:true" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">选项A：</label></td>
					<td class="width-35"><input name="M3" id="stkxx_m3" class="easyui-textbox"
								value="${aqpxlist.m3 }" style="width: 100%;height: 30px;" data-options="" /></td>
					<td class="width-15 active"><label class="pull-right">选项B：</label></td>
					<td class="width-35"><input name="M4" id="stkxx_m4" class="easyui-textbox"
								value="${aqpxlist.m4 }" style="width: 100%;height: 30px;" data-options="" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">选项C：</label></td>
					<td class="width-35"><div>
							<input name="M5" class="easyui-textbox"
								value="${aqpxlist.m5 }" id="stkxx_m5" style="width: 100%;height: 30px;" data-options="" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">选项D：</label></td>
					<td class="width-35"><input name="M6" class="easyui-textbox"
								value="${aqpxlist.m6 }" id="stkxx_m6" style="width: 100%;height: 30px;" data-options="" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">选项E：</label></td>
					<td class="width-35" colspan="3"><input name="M7" id="stkxx_m7" class="easyui-textbox"
								value="${aqpxlist.m7 }" style="width: 100%;height: 30px;" data-options="" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">答案：</label></td>
					<td class="width-35" colspan="3"><input name="M8" class="easyui-textbox"
								value="${aqpxlist.m8 }" style="width: 100%;height: 30px;" data-options="required:'true' " /></td>
				</tr>
					
				<tr></tr>
				
				<c:if test="${not empty aqpxlist.ID}">
					<input type="hidden" name="ID" value="${aqpxlist.ID}">
					<input type="hidden" name="ID1" value="${aqpxlist.ID1}">
					<input type="hidden" name="S1" value='<fmt:formatDate value="${aqpxlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />'>
					<input type="hidden" name="S3" value="${aqpxlist.s3}">
				</c:if>
			</tbody>
		</table>
		<p style="margin-top: 10px;color: red;font-size: 15px;"><strong>提示：多选题答案 按字母顺序连在一起填写即可。（如ABC）<br>如果是填空题用户答案和正确答案必须完全一样才能得分，请您出填空题时慎重考虑。 </strong></p>
       </form>
<script type="text/javascript">
var usertype=${usertype};
</script>
</body>
</html>