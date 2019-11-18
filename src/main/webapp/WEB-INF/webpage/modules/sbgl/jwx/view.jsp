<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>

      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">设备基本信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">${sbgl.sbname }</td>
               <td class="width-15 active"><label class="pull-right">设备类别：</label></td>
               <td class="width-35" ><c:choose>
                <c:when test="${sbgl.sbtype  eq 'tzsb'}">特种设备</c:when> 
                <c:when test="${sbgl.sbtype  eq 'aqss'}">安全设施</c:when> 
                <c:when test="${sbgl.sbtype  eq 'scsb'}">生产设备</c:when> 
                </c:choose>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备规格：</label></td>
               <td class="width-35">${sbgl.specification }</td>
               <td class="width-15 active"><label class="pull-right">设备型号：</label></td>
               <td class="width-35">${sbgl.number }</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">统一编号：</label></td>
               <td class="width-35">${sbgl.unifynumber }</td>
               <td class="width-15 active"><label class="pull-right">使用部门：</label></td>
               <td class="width-35"><input value="${sbgl.userdept }"
                class="easyui-combobox" style="width: 100%;" readonly="true"
                data-options="valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson'"></td>
            </tr>
              <tr>
               <td class="width-15 active" align="center" colspan="4">检维修记录</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">操作人员：</label></td>
               <td class="width-35" >${sbgl.operator }</td>
               <td class="width-15 active"><label class="pull-right">检修类别：</label></td>
               <td class="width-35" >
               <c:choose>
                <c:when test="${sbgl.type  eq 1}">计划维修</c:when> 
                <c:when test="${sbgl.type  eq 2}">故障维修</c:when> 
                </c:choose>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">实际开工检修时间：</label></td>
               <td class="width-35" ><fmt:formatDate value="${sbgl.starttime}" /></td>
               <td class="width-15 active"><label class="pull-right">实际检修竣工时间：</label></td>
               <td class="width-35" ><fmt:formatDate value="${sbgl.endtime}" /> </td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">修理内容：</label></td>
               <td class="width-35" colspan="3" style="height: 50px;">${sbgl.content }</td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">工具及防护：</label></td>
               <td class="width-35" colspan="3">${sbgl.toolfence }</td>
            </tr>
             <tr>
               <td class="width-15 active" align="center" colspan="4">设试运行情况</td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">空负荷：</label></td>
               <td class="width-35" colspan="3">${sbgl.runcondk }</td>
            </tr>
             <tr>
              <td class="width-15 active"><label class="pull-right">带负荷：</label></td>
               <td class="width-35" colspan="3">${sbgl.runcondd }</td>
            </tr>
                <tr>
               <td class="width-15 active" align="center" colspan="4">检维修后交付手续</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">交付人：</label></td>
               <td class="width-35">${sbgl.jfr }</td>
               <td class="width-15 active"><label class="pull-right">接收人：</label></td>
               <td class="width-35">${sbgl.jsr }</td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">车间主管：</label></td>
               <td class="width-35">${sbgl.cjzg }</td>
               <td class="width-15 active"><label class="pull-right">安全主管：</label></td>
               <td class="width-35">${sbgl.aqzg }</td>
            </tr>
         </tbody>
      </table>
</body>
</html>