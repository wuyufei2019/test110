<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>查看管控对策</title>
<meta name="decorator" content="default" />
</head>
<body>
   <tbody>
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tr>
            <td class="width-20" colspan="4" style="text-align:center;font-size:16px;"><label><b>${row.m1}风险分级管控对策</b></label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;background-color:#d9e2f3;"><label>一、风险辨识</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;"><label>${row.m1}主要存在${row.red}个红色风险点，${row.orange}个橙色风险点，${row.yellow}个黄色风险点，${row.blue}个蓝色风险点，共${row.count}个风险点，主要存在的事故风险为${sglx}。该公司总风险等级为
            <c:choose>
                   <c:when test="${row.yanse eq 1}">
                       红
                   </c:when>
                   <c:when test="${row.yanse eq 2}">
                       橙
                   </c:when>
                   <c:when test="${row.yanse eq 3}">
                       黄
                   </c:when>
                   <c:when test="${row.yanse eq 4}">
                       蓝
                   </c:when>
         </c:choose>色。</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;background-color:#d9e2f3;"><label>二、管控分级</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;"><label>
                     1、红色风险点（${row.red}个）属于重大风险，由公司（厂）级、部室（车间级）、班组、岗位逐级管控，应立即整改，视具体情况决定是否停产整改，需要停产整改的，只有当风险降至可接受后，才能开始或继续工作。
                 <br> 2、橙色风险点（${row.orange}个）属于较大风险，由公司（厂）级、部室（车间级）、班组、岗位逐级管控，应制定建议改进措施进行控制管理。
                 <br> 3、黄色风险（${row.yellow}个）属于一般风险，由部室（车间级）、班组、岗位逐级管控，需要控制整改。
                 <br> 4、蓝色风险（${row.blue}个）属于低风险，由班组、岗位管控。</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;background-color:#d9e2f3;"><label>三、风险管控措施</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;"><label>风险管控措施主要包括工程技术措施、管理措施、培训教育措施、个体防护措施、应急处置措施等。 
              <br>（1）工艺装置类风险应采用安全屏护、报警、联锁、限位、安全泄放等工艺设备本身固有的控制措施和检查、检测、维保等常规的管控措施；
              <br>（2）作业类风险应采取制度、操作规程的完备性、管理流程合理性、作业环境可控性、作业对象完好状态及作业人员技术能力等方面管控措施；</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;background-color:#d9e2f3;"><label>四、其他</label></td>
         </tr>
         <tr>
            <td class="width-20" colspan="4" style="text-align:left;"><label>建议安监部门每年开展<c:choose>
                   <c:when test="${row.yanse eq 1}">
                      4
                   </c:when>
                   <c:when test="${row.yanse eq 2}">
                       2
                   </c:when>
                   <c:when test="${row.yanse eq 3}">
                      2
                   </c:when>
                   <c:when test="${row.yanse eq 4}">
                     1
                   </c:when>
         </c:choose>次安全监督工作</label></td>
         </tr>

         <tr>
         </tr>
      </table>
      </div>
   </tbody>
</body>
</html>