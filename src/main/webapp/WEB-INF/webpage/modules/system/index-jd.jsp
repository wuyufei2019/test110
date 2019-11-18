<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>安全生产监管一体化平台</title>

	<%@ include file="/WEB-INF/webpage/include/head.jsp"%>
	<script src="${ctxStatic}/common/inspinia.js?v=3.2.0"></script>
	<script src="${ctxStatic}/common/contabs.js"></script> 

</head>

<body class="fixed-sidebar full-height-layout gray-bg fixed-nav ">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" style="height:64px;width:64px;" src="${ctxStatic}/model/images/systemUser.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"><shiro:principal property="name"/></strong></span>
                               <span class="text-muted text-xs block"><b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="${ctx}/system/user/updatePwd">修改密码</a>
                                </li>
                                <li><a class="J_menuItem" href="${ctx }/system/user/infor">个人资料</a>
                                </li>
                                
                                <li class="divider"></li>
                                <li><a href="${ctx}/a/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">Zh
                        </div>
                    </li>
     
                

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-fixed-top" role="navigation" style="margin-bottom: 0;background-image: url('${ctx }/static/model/images/banner_bg.png');">
                    <div class="navbar-header"> 
                    <span style="font-weight:bold;font-size:20px;color: #fff;line-height: 45px;margin-left: 20px;">企业智慧安全管理系统</span>
                    </div>
                    
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/a/home">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose"  data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx}/a/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            
            
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/a/home" frameborder="0" data-id="${ctx}/a/home" seamless></iframe>
            </div>
            
            
            <div class="footer">
                <div class="pull-left">江苏中安联科信息技术有限公司&nbsp;&nbsp;常州大学安全研究所&nbsp;  copyright &copy; 2017</div>
            </div>
        </div>
        <!--右侧部分结束-->
       
       
    </div>
</body>

<script type="text/javascript">
	  
</script>

</html>