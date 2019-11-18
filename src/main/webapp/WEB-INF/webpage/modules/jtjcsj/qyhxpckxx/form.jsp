<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业化学品仓库信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript">
        var usertype =${usertype};

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        var validateForm;

        function doSubmit() {
            $("#inputForm").submit();
        }

        $(function () {
            var flag = true;
            $('#inputForm').form({
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid && flag) {
                        flag = false;
                        $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                        return true;
                    }
                    return false;	// 返回false终止表单提交
                },
                success: function (data) {
                    $.jBox.closeTip();
                    if (data == 'success')
                        parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                    else
                        parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                }
            });

        });

    </script>
</head>
<body>

<form id="inputForm" action="${ctx}/jtjcsj/qyhxpckxx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qyhxpckxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qyhxpckxxxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
            <td class="width-30" colspan="3">
                <input name="equipcode" class="easyui-combobox" value="${qyhxpckxx.equipcode }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'120px',required:'true',valueField: 'text',textField: 'text',url:'${ctx}/jtjcsj/sbxx/equipCodeJson'"/>
            </td>
		</tr>
		
        <tr>
            <td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
            <td class="width-30">
                <input name="prodcellid" class="easyui-textbox" value="${qyhxpckxx.prodcellid }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,32]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
            <td class="width-30">
                <input name="hazardcode" class="easyui-combobox" value="${qyhxpckxx.hazardcode }" style="width: 100%;height: 30px;"
                 		data-options="editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/bis/hazard/hazardCodeJson'"/>
            </td>
        </tr>
        
        
 		<tr>
            <td class="width-20 active"><label class="pull-right">化学品仓库名称：</label></td>
            <td class="width-30">
                <input name="chmstorname" class="easyui-textbox" value="${qyhxpckxx.chmstorname }" style="width: 100%;height: 30px;"
                 		data-options="required:'true',validType:'length[0,100]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">化学品仓库用途：</label></td>
            <td class="width-30">
                <input name="chmstoruse" class="easyui-combobox" value="${qyhxpckxx.chmstoruse }" style="width: 100%;height: 30px;"
                 		data-options="panelHeight:'auto',data:[{value:'H4601',text:'原料'},{value:'H4602',text:'辅助原料'},{value:'H4603',text:'中间产品'},{value:'H4604',text:'终端产品'}]"/>
            </td>
		</tr>
		
	 	<tr>
            <td class="width-20 active"><label class="pull-right">面积（㎡）或容积：</label></td>
            <td class="width-30">
                <input name="areavol" class="easyui-textbox" value="${qyhxpckxx.areavol }" style="width: 100%;height: 30px;"
                 		data-options="validType:['number','length[0,15]']"/>
            </td>
            <td class="width-20 active"><label class="pull-right">存放物体形态：</label></td>
            <td class="width-30">
                <input name="matterform" class="easyui-combobox" value="${qyhxpckxx.matterform }" style="width: 100%;height: 30px;"
                 		data-options="panelHeight:'auto',data:[{value:'H4701',text:'液态'},{value:'H4702',text:'气态'},{value:'H4703',text:'固态'}]"/>
            </td>
		</tr>
		
		
		<tr>
            <td class="width-20 active"><label class="pull-right">设计使用年限：</label></td>
            <td class="width-30">
                <input name="deslifespan" class="easyui-textbox" value="${qyhxpckxx.deslifespan }" style="width: 100%;height: 30px;"
                 		data-options="validType:['number','length[0,15]']"/>
            </td>
            <td class="width-20 active"><label class="pull-right">竣工时间：</label></td>
			<td class="width-30"><input name="completime" class="easyui-datebox" value="<fmt:formatDate value="${qyhxpckxx.completime}"/>" style="width:100%;height:30px;" data-options="editable:false" /></td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">库房形式：</label></td>
            <td class="width-30" colspan="3">
                <input name="storform" class="easyui-textbox" value="${qyhxpckxx.storform }" style="width: 100%;height: 80px;"
                 		data-options="multiline:true,validType:'length[0,250]'"/>
            </td>			
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">库房结构：</label></td>
            <td class="width-30" colspan="3">
                <input name="storstructure" class="easyui-textbox" value="${qyhxpckxx.storstructure }" style="width: 100%;height: 80px;"
                 		data-options="multiline:true,validType:'length[0,250]'"/>
            </td>			
		</tr>
		
		<tr>
			<td class="width-15 active"><label class="pull-right">经纬度：</label></td>
			<td colspan="3">
				<span><label>经度</label>
				<input id="longitude" name="longitude" value="${qyhxpckxx.longitude}" class="easyui-textbox" readonly="readonly" data-options="" style="width:100px;height:30px;"/>
				<label>纬度</label> 
				<input id="latitude" name="latitude" value="${qyhxpckxx.latitude }" class="easyui-textbox" readonly="readonly" data-options="" style="width:100px;height:30px;"/>
				<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></span></td>
		</tr>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">仓库管理员姓名：</label></td>
            <td class="width-30">
                <input name="storemanname" class="easyui-textbox" value="${qyhxpckxx.storemanname }" style="width: 100%;height: 30px;"
                 		data-options="validType:'length[0,25]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-30">
                <input name="linkmode" class="easyui-textbox" value="${qyhxpckxx.linkmode }" style="width: 100%;height: 30px;"
                 		data-options="validType:['number','length[0,50]']"/>
            </td>
		</tr>
		
		
        <c:if test="${not empty qyhxpckxx.id}">
            <input type="hidden" name="ID" value="${qyhxpckxx.id}"/>
            <input type="hidden" name="qyid" value="${qyhxpckxx.qyid}"/>
            <input type="hidden" name="creator" value="${qyhxpckxx.creator}"/>
            <input type="hidden" name="companycode" value="${qyhxpckxx.companycode}"/>
            <input type="hidden" name="districtcode" value="${qyhxpckxx.districtcode}"/>
            <input type="hidden" name="parkid" value="${qyhxpckxx.parkid}"/>
            <input type="hidden" name="chmstorid" value="${qyhxpckxx.chmstorid}"/>
            <input type="hidden" name="createtime"
                   value="<fmt:formatDate value="${qyhxpckxx.createtime}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="status" value="${qyhxpckxx.status}"/>
        </c:if>
        </tbody>
    </table>
</form>
	    <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
		</div>
<script type="text/javascript">
    
var locx ='${qyhxpckxx.longitude}';
var locy ='${qyhxpckxx.latitude}';

//弹出地图界面
function showMapXY( ){
	layer.open({
	    type: 1,  
	    area: ['500px', '400px'],
	    title: '标注坐标',
        maxmin: true, 
        shift: 1,
        shade :0,
	    content: $('#enterprise_dlg'),
	    btn: ['确定', '关闭'],
	    success: function(layero, index){
	    	addmap("");
	    },
	    yes: function(index, layero){
	    	$("#longitude").textbox("setValue", locx);//经度
			$("#latitude").textbox("setValue", locy);//纬度
			layer.close(index);
		  },
		  cancel: function(index){ 
	       }
	});
}

function addmap(searchcon){	
	initMap("bis_enterprise_dlg_map",locx,locy);
	map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map}
	});
	local.search(searchcon);
	var marker = new BMap.Marker(point); //创建marker对象
	map.addOverlay(marker); //在地图中添加marker
	
	map.addEventListener("click", function(e){	
		locx=e.point.lng;
		locy=e.point.lat;
		var now_point =  new BMap.Point(e.point.lng, e.point.lat );
		marker.setPosition(now_point);//设置覆盖物位置
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	});
	
}
    
</script>
</body>
</html>