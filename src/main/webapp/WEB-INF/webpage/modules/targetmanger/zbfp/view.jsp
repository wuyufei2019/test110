<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标分配管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.hr-line-dashed {
    border-top: 2px dashed #987cb9;
    color: #987cb9 ;
    height: 1px;
    margin: 7px 0;
}
</style>
</head>
<body>

      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
         	<tr>
               <td class="width-20 active"><label class="pull-right" style="width: 174px;text-align: right;">责任部门：</label></td>
               <td class="width-30">${map.deptname }</td>
               <td class="width-20 active"><label class="pull-right" style="width: 170px;text-align: right;">级别：</label></td>
               <td class="width-30"><c:choose>
                  <c:when test="${map.m3  eq '1'}">公司</c:when>
                  <c:when test="${map.m3  eq '2'}">部门</c:when>
                  <c:when test="${map.m3  eq '3'}">班组</c:when>
               </c:choose></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">年份：</label></td>
               <td class="width-30">${map.m1 }</td>
            </tr>
            
            <c:forEach items="${list }" var="target">
            <tr>
            	<td colspan="4" style="text-align: center;"><div class="hr-line-dashed"></div></td>
            </tr>
            <tr id="tr2">
               <td class="width-20 active"><label class="pull-right">目标指标：</label></td>
               <td class="width-80" colspan="3"><input id="t_id1" name="ID1" value="${target.id1 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                    readonly='true' data-options="panelHeight:'120',required:true,editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/target/zbsz/idjson'"></td>
            </tr>
            <tr>
				<td class='width-20 active' style="text-align: center;"><label>指标值：</label></td>
				<td class='width-30 active' style="text-align: center;"><label>预算（万元）：</label></td>
				<td class='width-20 active' style="text-align: center;"><label>责任人：</label></td>
				<td class='width-30 active' style="text-align: center;"><label>预计完成时间：</label></td>
			</tr>
            <tr>
				<td class='width-20' style="text-align: center;">${target.targetval }</td>
				<td class='width-30' style="text-align: center;">${target.m11 }</td>
				<td class='width-20' style="text-align: center;">${target.m12 }</td>
				<td class='width-30' style="text-align: center;">${target.m13}</td>
			</tr>
            <tr id="tr1">
               <td class="width-20 active"><label class="pull-right">制定人：</label></td>
               <td class="width-30">${target.m7 }</td>
               <td class="width-20 active"><label class="pull-right">审核人：</label></td>
               <td class="width-30">${target.m8 }</td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">批准人：</label></td>
               <td class="width-30">${target.m9 }</td>
               <td class="width-20 active"><label class="pull-right">批准日期：</label></td>
               <td class="width-30"><fmt:formatDate value="${target.m5}" pattern="yyyy-MM-dd"/></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">备注：</label></td>
               <td class="width-80" colspan="3">${target.m6 }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">安全目标责任书：</label></td>
               <td class="width-35" colspan="3">
                <c:if test="${not empty target.url}">
                <c:forTokens items="${target.url}" delims="," var="url" varStatus="urls">
                  <c:set var="urlna" value="${fn:split(url, '||')}" />
                  <div style="margin: 5px;"> 
                        <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
                     </div>
                </c:forTokens>
                </c:if>
               </td>
            </tr>
            </c:forEach>
            <%-- <tr>
               <td class="width-15 active"><label class="pull-right">目标指标：</label></td>
               <td class="width-35" ><input value="${target.ID1 }" class="easyui-combobox" style="width: 100%;"
                     data-options="readonly:true,valueField: 'id',textField: 'text',url:'${ctx}/target/zbsz/idjson'"></td>
               <td class="width-15 active"><label class="pull-right">指标值：</label></td>
               <td class="width-35">${target.targetval }</td>
            </tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">年月：</label></td>
				<td class="width-35">${target.m1 }</td>
				<td class="width-15 active"><label class="pull-right">预算（万元）：</label></td>
				<td class="width-35">${target.m11 }</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">责任人：</label></td>
				<td class="width-35">${target.m12 }</td>
				<td class="width-15 active"><label class="pull-right">预计完成时间：</label></td>
				<td class="width-35">${target.m13 }</td>
			</tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35"><input value="${target.ID3 }" class="easyui-combobox"  style="width: 100%;"
                    data-options="readonly:true,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"></td>
               <td class="width-15 active"><label class="pull-right">级别：</label></td>
               <td class="width-35"><c:choose>
                  <c:when test="${target.m3  eq '1'}">公司</c:when>
                  <c:when test="${target.m3  eq '2'}">部门</c:when>
                  <c:when test="${target.m3  eq '3'}">班组</c:when>
               </c:choose></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">制定人：</label></td>
               <td class="width-35" >${target.m7 }</td>
               <td class="width-15 active"><label class="pull-right">审核人：</label></td>
               <td class="width-35" >${target.m8 }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">批准人：</label></td>
               <td class="width-35" >${target.m9 }</td>
               <td class="width-15 active"><label class="pull-right">批准日期：</label></td>
               <td class="width-35" ><fmt:formatDate value="${target.m5}" pattern="yyyy-MM-dd"/></td>
            </tr>
            

            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3">${target.m6 }</td>
            </tr>
         
            <tr>
               <td class="width-15 active"><label class="pull-right">安全目标责任书：</label></td>
               <td class="width-35" colspan="3">
                <c:if test="${not empty target.url}">
                <c:forTokens items="${target.url}" delims="," var="url" varStatus="urls">
                  <c:set var="urlna" value="${fn:split(url, '||')}" />
                  <div style="margin: 5px;"> 
                        <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
                     </div>
                </c:forTokens>
                </c:if>
               </td>
            </tr> --%>

         </tbody>
      </table>
</body>

</html>