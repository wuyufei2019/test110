<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>错题订正</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        body{
            width: 100%;
            height: 100%;
            font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
            background-color:#A3E0FF
        }

        #nav {
            position: absolute;
            margin-left:100px;
            width: 150px;
            height: 120px;
            background-color:#FFFFFF;
            margin: 0 50px 0 29px ;
            font-size: 15px;
            font-weight: bold;
            padding: 15px 10px 20px 10px;
            -webkit-box-shadow: 0 0 12px rgba(0,20,31,.35);
            -moz-box-shadow: 0 0 12px rgba(0,20,31,.35);
            box-shadow: 0 0 12px rgba(0,20,31,.35);
        }

        #lx {
            font-size: 16px;
            font-weight: bold;
            color: #76889c;
            margin-bottom:20px;
            line-height: 32px
        }

        #bt {
            font-size: 15px;
            font-weight: bold;
            margin-bottom:20px;
            line-height: 32px
        }

        .aqpx_zxks_sj span{
            font-size: 14px;
            margin-top:48px;
            line-height:32px;
        }

        .aqpx_zxks_sj{
            margin-top:30px;
        }

        .aqpx_zxks_sj input{
            margin-left:20px;
        }

        button:hover{
            background-color:#ddf3ff ;
        }

        input[type="checkbox"]
        {
            width:18px;
            height:18px;
        }
    </style>
</head>
<body>
<div style="width: 100%;height:auto;text-align:center;" >
    <img src="${ctx}/static/model/images/aqpx/bg.png"/>
    <div id="nav" style="">
        <div>
            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="submitBtn()" title="提交" style="width:120px"><i class="fa fa-plus"></i> <span style="font-size: 14px">提交</span></button>
        </div>
        <div style="margin: 10px 0px 6px 0px;">
            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="cancelBtn()" title="放弃" style="width:120px"><i class="fa fa-trash-o"></i> <span style="font-size: 14px">放弃</span></button>
        </div>
    </div>
</div>
<div style="width: 100%;background-color:#A3E0FF " >
    <div id="content" style="width:900px;height:auto; margin: 0 auto;background-color: #FFFFFF; padding:10px 60px" closed="true">
        <div align="center" style="margin:30px"><h3 style="color: #17A8EE;font-size:24px">${kcname}</h3></div>
        <hr style="border-top:1px dashed #eeeeee; height:1px">
        <form id="aqpx_zxks_form_mainform"  novalidate>

            <c:if test="${stgz.m1>0}">
                <div id="lx">一、单项选择题(每题${stgz.m5}分，共${stgz.m1}题)</div>
                <c:set value="0" var="sum" />
                <c:forEach items="${list}" var="dx" varStatus="status">
                    <c:if test="${dx.m1=='1'}">
                        <c:set value="${sum + 1}" var="sum" />
                        <div class="aqpx_zxks_sj">
                            <div id="bt" >${sum}. &nbsp ${dx.m2}（）</div>
                            <span><input type="radio" value="A" name="dx_${dx.id}" class="i-checks" />A.${dx.m3}</span><br/>
                            <span><input type="radio" value="B" name="dx_${dx.id}" class="i-checks"  />B.${dx.m4}</span><br/>
                            <c:if test="${dx.m5 ne ''}">
                                <span><input type="radio" value="C" name="dx_${dx.id}" class="i-checks"  />C.${dx.m5}</span><br/>
                            </c:if>
                            <c:if test="${dx.m6 ne ''}">
                                <span><input type="radio" value="D" name="dx_${dx.id}" class="i-checks"  />D.${dx.m6}</span><br/>
                            </c:if>
                            <c:if test="${dx.m7 ne ''}">
                                <span><input type="radio" value="E" name="dx_${dx.id}" class="i-checks"  />E.${dx.m7}</span><br/>
                            </c:if>
                                <span id="dx_${dx.id}" hidden class="da" style="color: #fc1d31">正确答案：${dx.m8}</span>

                        </div>
                        <hr style="border-top:1px solid #eeeeee; height:1px">
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${stgz.m2>0}">
                <div id="lx">二、多选题(每题${stgz.m6}分，共${stgz.m2}题)</div>
                <c:set value="0" var="sum" />
                <c:forEach items="${list}" var="dsx" varStatus="status">
                    <c:if test="${dsx.m1=='2'}">
                        <c:set value="${sum + 1}" var="sum" />
                        <div class="aqpx_zxks_sj" >
                            <div id="bt">${sum}.&nbsp ${dsx.m2}（）</div>
                            <span><input type="checkbox" value="A" name="dsx_${dsx.id}" /> A.${dsx.m3}</span><br/>
                            <span><input type="checkbox" value="B" name="dsx_${dsx.id}" /> B.${dsx.m4}</span><br/>
                            <c:if test="${dsx.m5 ne ''}">
                                <span><input type="checkbox" value="C" name="dsx_${dsx.id}" /> C.${dsx.m5}</span><br/>
                            </c:if>
                            <c:if test="${dsx.m6 ne ''}">
                                <span><input type="checkbox" value="D" name="dsx_${dsx.id}" /> D.${dsx.m6}</span><br/>
                            </c:if>
                            <c:if test="${dsx.m7 ne ''}">
                                <span><input type="checkbox" value="E" name="dsx_${dsx.id}" /> E.${dsx.m7}</span><br/>
                            </c:if>
                                <span id="dsx_${dsx.id}" hidden class="da" style="color: #fc1d31">正确答案：${dsx.m8}</span>

                        </div>
                        <hr style="border-top:1px solid #eeeeee; height:1px">
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${stgz.m4>0}">
                <div  id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">三、判断题(每题${stgz.m8}分，共${stgz.m4}题)</div>
                <c:set value="0" var="sum" />
                <c:forEach items="${list}" var="pd" varStatus="status">
                    <c:if test="${pd.m1=='4'}">
                        <c:set value="${sum + 1}" var="sum" />
                        <div class="aqpx_zxks_sj" >
                            <div id="bt">${sum}.&nbsp ${pd.m2}</div>
                            <span><input type="radio" class="i-checks" value="A" name="pd_${pd.id}" />A.${pd.m3}</span>
                            <span><input type="radio" class="i-checks" value="B" name="pd_${pd.id}" />B.${pd.m4}</span><br/>
                            <span id="pd_${pd.id}" hidden class="da" style="color: #fc1d31">正确答案：${pd.m8}</span>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${stgz.m3>0}">
                <div id="lx" style="font-size: 16px;font-weight: bold;color: #76889c;margin-bottom:20px">四、填空题(每题${stgz.m7}分，共${stgz.m3}题)</div>
                <c:set value="0" var="sum" />
                <c:forEach items="${list}" var="tk" varStatus="status">
                    <c:if test="${tk.m1=='3'}">
                        <c:set value="${sum + 1}" var="sum" />
                        <div class="aqpx_zxks_sjtk" >
                            <div id="bt">${sum}.&nbsp ${tk.m2}</div>
                            答案：<input name="tk_${tk.id}" class="easyui-textbox" value="" style="width: 300px;height: 30px;" />

                             <span id="tk_${tk.id}" hidden class="da" style="color: #fc1d31">正确答案：${tk.m8}</span>

                        </div>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden"  name="planid"/>
        </form>
    </div>
</div>

<script type="text/javascript">
    var time=0;
    var lable;
    var pid=parent.planid;
    var submitFlag = true;
    var commitFlag = true;



    function submitact(){
        if (commitFlag) {
            commitFlag = false;
            $.post(ctx+"/aqpx/aqpxjl/submit",$("#aqpx_zxks_form_mainform").serialize(),function(data){
                $(".da").show();
                data.forEach(function (val, index, arr){
                    $("#"+val).css({"color":"#1ab394"})
                });
                console.log(data);

            });
        }
    }

    //提交考试
    function submitBtn(){
        if (submitFlag) {
            submitFlag = false;
            layer.open({
                area: [400, 200],
                title: '提示',
                maxmin: false,
                content: '确定要提交本次考试？' ,
                btn: ['确定','取消'],
                yes: function(index, layero){
                    $("input[name='planid']").val(pid);
                    layer.close(index);
                    submitact();
                },
                cancel: function(index){
                }
            });
            submitFlag = true;
            commitFlag = true;
        }
    }
    //取消考试
    function cancelBtn(){
        console.info($("#aqpx_zxks_form_mainform").serialize());
        layer.open({
            area: [400, 200],
            title: '提示',
            maxmin: false,
            content: '确定要放弃本次考试？' ,
            btn: ['确定','取消'],
            yes: function(index, layero){
                closelayer();
            },
            cancel: function(index){
            }
        });

    }

    function closelayer(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
</script>
</body>
</html>