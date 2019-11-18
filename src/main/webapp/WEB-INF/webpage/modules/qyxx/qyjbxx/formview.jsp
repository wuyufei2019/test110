<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
<script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
<script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
}
</style>
</head>
<body>
   <table class="table table-bordered  dataTables-example dataTable no-footer" style="margin:0 auto;width:900px;">
      <tbody>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>企业名称：
               </label></td>
            <td colspan="3">${qylist.m1 }</td>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>是否构成重大危险源：
               </label></td>
            <td class="width-25"><c:if test="${qylist.m39 eq 0 }">否</c:if> <c:if test="${qylist.m39 eq 1 }">是</c:if></td>
            <td id="bis_qy_id_table_td_m40_td1" class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>重大危险源等级：
               </label></td>
            <td class="width-25" id="bis_qy_id_table_td_m40_td2"><c:choose>
                  <c:when test="${qylist.m40  eq '1'}">一级</c:when>
                  <c:when test="${qylist.m40  eq '2'}">二级</c:when>
                  <c:when test="${qylist.m40  eq '3'}">三级</c:when>
                  <c:when test="${qylist.m40  eq '4'}">四级</c:when>
               </c:choose></td>
         </tr>

         <tr>
            <td class="width-25 active"><label class="pull-right">企业风险等级：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m44  eq '1'}">红</c:when>
                  <c:when test="${qylist.m44  eq '2'}">橙</c:when>
                  <c:when test="${qylist.m44  eq '3'}">黄</c:when>
                  <c:when test="${qylist.m44  eq '4'}">蓝</c:when>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">规模情况：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m34  eq '1'}">规上</c:when>
                  <c:when test="${qylist.m34  eq '2'}">规下</c:when>
               </c:choose></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">安全监管等级：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m36_1 eq '1' }">A</c:when>
                  <c:when test="${qylist.m36_1  eq '2' }">B</c:when>
                  <c:when test="${qylist.m36_1  eq '3' }">C</c:when>
                  <c:when test="${qylist.m36_1  eq '4' }">D</c:when>
                  <c:when test="${qylist.m36_1  eq '5' }">未定级</c:when>
                  <c:otherwise></c:otherwise>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">属地安监机构：</label></td>
            <td class="width-25">${qylist.m8_6 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">监管分类：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m36  eq '1'}">工贸</c:when>
                  <c:when test="${qylist.m36  eq '2'}">化工</c:when>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>经济类型：
               </label></td>
            <td class="width-25"><input id="bis_qy_m10" class="easyui-combobox" name="M10" value="${qylist.m10 }"
                  style="width:220px;height:30px;" data-options="readonly:'true',valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/jjlx' " /></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">行业类别：</label></td>
            <td class="width-25"><input id="M12" class="easyui-combotree" name="M12" value="${qylist.m12 }" style="width:220px;height:30px;"
                  data-options="readonly:'true',method: 'get',url:'${ctx}/tcode/gbt/json'" /></td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>企业行政隶属关系：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m13  eq '1'}">中央级</c:when>
                  <c:when test="${qylist.m13  eq '2'}">省级</c:when>
                  <c:when test="${qylist.m13  eq '3'}">市、地区级</c:when>
                  <c:when test="${qylist.m13  eq '4'}">县级</c:when>
                  <c:when test="${qylist.m13  eq '5'}">街道、镇、乡级</c:when>
                  <c:when test="${qylist.m13  eq '6'}">街道</c:when>
                  <c:when test="${qylist.m13  eq '7'}">镇</c:when>
                  <c:when test="${qylist.m13  eq '8'}">乡</c:when>
                  <c:when test="${qylist.m13  eq '9'}">其他</c:when>
               </c:choose></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>企业状态：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m15  eq '1'}">有效</c:when>
                  <c:when test="${qylist.m15  eq '2'}">无效</c:when>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">标准化达标等级：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m18  eq '1'}">一级</c:when>
                  <c:when test="${qylist.m18  eq '2'}">二级</c:when>
                  <c:when test="${qylist.m18  eq '3'}">三级</c:when>
                  <c:when test="${qylist.m18  eq '4'}">达标</c:when>
                  <c:when test="${qylist.m18  eq '5'}">未创建</c:when>
               </c:choose></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>安全机构设置情况：
               </label></td>
            <td colspan="3"><c:choose>
                  <c:when test="${qylist.m27  eq '1'}">是</c:when>
                  <c:when test="${qylist.m27  eq '2'}">否</c:when>
               </c:choose> <label style="color: red;">（企业内部( 是/否 )有专门设置负责安全生产管理的部门）</label></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>是否涉及重点监管危化品：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m47  eq '1'}">是</c:when>
                  <c:when test="${qylist.m47  eq '0'}">否</c:when>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>是否涉及高危工艺：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m48  eq '1'}">是</c:when>
                  <c:when test="${qylist.m48  eq '0'}">否</c:when>
               </c:choose></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>是否涉及剧毒品生产或使用：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m49  eq '1'}">是</c:when>
                  <c:when test="${qylist.m49  eq '0'}">否</c:when>
               </c:choose></td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>营业执照类别：
               </label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m2_1  eq '01'}">企业法人营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '02'}">营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '03'}">个体工商户营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '04'}">个人独资企业营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '05'}">个人独资企业分支机构营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '06'}">合伙企业营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '07'}">合伙企业分支机构营业执照</c:when>
                  <c:when test="${qylist.m2_1  eq '99'}">其他</c:when>
               </c:choose></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">是否位于化工集中区：</label></td>
            <td class="width-25"><c:choose>
                  <c:when test="${qylist.m46  eq '1'}">是</c:when>
                  <c:when test="${qylist.m46  eq '0'}">否</c:when>
               </c:choose></td>
            <td id="m46_1_td1" class="width-25 active"><label class="pull-right">化工集中区名称：</label></td>
            <td id="m46_1_td2">${qylist.m46_1 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">母公司名称：</label></td>
            <td colspan="3">${qylist.m1_1 }</td>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">集团公司名称：</label></td>
            <td colspan="3">${qylist.m1_2 }</td>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">注册资金(万元)：</label></td>
            <td class="width-25">${qylist.m8_1 }</td>
            <td class="width-25 active"><label class="pull-right">年销售收入(万元)：</label></td>
            <td class="width-25">${qylist.m8_2 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">年利润(万元)：</label></td>
            <td class="width-25">${qylist.m8_3 }</td>
            <td class="width-25 active"><label class="pull-right">资产总额（万元）：</label></td>
            <td class="width-25">${qylist.m8_4 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">注册地址：</label></td>
            <td colspan="3">${qylist.m8 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">统一社会信用代码：</label></td>
            <td class="width-25">${qylist.m3_1 }</td>
            <td class="width-25 active"><label class="pull-right">占地面积（㎡）：</label></td>
            <td class="width-25">${qylist.m8_5 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">企业实际生产/经营地址：</label></td>
            <td colspan="3">${qylist.m33 }</td>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">企业官方网站地址：</label></td>
            <td colspan="3">${qylist.m33_1 }</td>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">成立时间：</label></td>
            <td class="width-25"><fmt:formatDate value="${qylist.m4}" pattern="yyyy-MM-dd"  /></td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>法定代表人：
               </label></td>
            <td class="width-25">${qylist.m5 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>行政区域：
               </label></td>
            <td colspan="3"><div id="bis_qy_province">
                  ${qylist.m11 }
                  <label style="margin-left: 10px;" for="M11">省</label>
                 ${qylist.m11_1 }
                  <label style="margin-left: 10px;" for="M11_1">市</label>
                  ${qylist.m11_2 }
                  <label style="margin-left: 10px;" for="M11_2">区</label>
                  ${qylist.m11_3 }
                  <label style="margin-left: 10px;" for="M11_3">镇或街道</label>
               </div></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>联系人：
               </label></td>
            <td class="width-25">${qylist.m6_1 }</td>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>联系电话：
               </label></td>
            <td class="width-25">${qylist.m6 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">联系微信号：</label></td>
            <td class="width-25">${qylist.m6_2 }</td>
            <td class="width-25 active"><label class="pull-right">联系QQ号：</label></td>
            <td class="width-25">${qylist.m6_3 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">电子邮件：</label></td>
            <td class="width-25">${qylist.m7 }</td>
            <td class="width-25 active"><label class="pull-right">单位传真：</label></td>
            <td class="width-25">${qylist.m33_2 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">
                  <font color="red">*</font>企业位置：
               </label></td>
            <td colspan="3" style="height:30px;line-height:30px;"><label>经度：</label>${qylist.m16 }<label> 纬度：</label> ${qylist.m17 }
         </tr>

         <tr>
            <td class="width-25 active"><label class="pull-right">主要负责人：</label></td>
            <td class="width-25">${qylist.m19 }</td>
            <td class="width-25 active"><label class="pull-right">主要负责人固定电话：</label></td>
            <td class="width-25">${qylist.m20 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">主要负责人移动电话：</label></td>
            <td class="width-25">${qylist.m21 }</td>
            <td class="width-25 active"><label class="pull-right">主要负责人电子邮箱：</label></td>
            <td class="width-25">${qylist.m22 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">安全负责人：</label></td>
            <td class="width-25">${qylist.m23 }</td>
            <td class="width-25 active"><label class="pull-right">安全负责人固定电话：</label></td>
            <td class="width-25">${qylist.m24 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">安全负责人移动电话：</label></td>
            <td class="width-25">${qylist.m25 }</td>
            <td class="width-25 active"><label class="pull-right">安全负责人电子邮箱：</label></td>
            <td class="width-25">${qylist.m26 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">邮政编码：</label></td>
            <td class="width-25">${qylist.m9 }</td>
            <td class="width-25 active"><label class="pull-right">从业人员数量：</label></td>
            <td class="width-25">${qylist.m28 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">特种作业人员数量：</label></td>
            <td class="width-25">${qylist.m29 }</td>
            <td class="width-25 active"><label class="pull-right">专职安全生产管理人员数：</label></td>
            <td class="width-25">${qylist.m30 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">专职应急管理人员数：</label></td>
            <td class="width-25">${qylist.m31 }</td>
            <td class="width-25 active"><label class="pull-right">兼职安全生产管理人员数：</label></td>
            <td class="width-25">${qylist.m30_1 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">注册安全工程师人员数：</label></td>
            <td class="width-25">${qylist.m32 }</td>
         </tr>
         <shiro:hasAnyRoles name="admin,superadmin">
         	<td class="width-25 active"><label class="pull-right">开通日期：</label></td>
            <td class="width-25"><fmt:formatDate value="${qylist.ktdate }" pattern="yyyy-MM-dd" /></td>
            <td class="width-25 active"><label class="pull-right">到期日期：</label></td>
            <td class="width-25"><fmt:formatDate value="${qylist.dqdate }" pattern="yyyy-MM-dd" /></td>
         </shiro:hasAnyRoles>
         <tr>
            <td class="width-25 active"><label class="pull-right">隐患排查治理制度（单文件）：</label></td>
            <td colspan="3"><c:if test="${not empty qylist.m37}">
                  <c:set var="url" value="${fn:split(qylist.m37, '||')}" />
                  <div style="margin-bottom: 10px;">
                     <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                  </div>
               </c:if></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">隐患排查治理计划（单文件）：</label></td>
            <td colspan="3"><c:if test="${not empty qylist.m38}">
                  <c:set var="url" value="${fn:split(qylist.m38, '||')}" />
                  <div style="margin-bottom: 10px;">
                     <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                  </div>
               </c:if></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">企业平面图（单文件）：</label></td>
            <td colspan="3"><c:if test="${not empty qylist.m33_3}">
                  <c:set var="url" value="${fn:split(qylist.m33_3, '||')}" />
                  <div style="margin-bottom: 10px;">
                     <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                  </div>
               </c:if></td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">企业风险四色图：</label></td>
            <td colspan="3">
                <c:if test="${not empty qylist.m33_4}">
                    <c:forTokens var="urlna" items="${qylist.m33_4}" delims=",">
                        <c:set var="url" value="${fn:split(urlna, '||')}" />
                        <div style="margin-bottom: 10px;">
                            <a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">经营范围：</label></td>
            <td colspan="3">${qylist.m14 }</td>
         </tr>
         <tr>
            <td class="width-25 active"><label class="pull-right">备注：</label></td>
            <td colspan="3">${qylist.m45 }</td>
         </tr>
      </tbody>
   </table>

</body>
</html>