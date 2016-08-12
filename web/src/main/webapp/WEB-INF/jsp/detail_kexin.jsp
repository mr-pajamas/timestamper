<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>${typeName}详情 - 存证通可信数据保全服务平台</title>
</head>
<body>
<div class="center-block body-wrapper">
<%--
    <nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0px" id="navbar">
        <!-- 导航头部 -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- 品牌名称或logo -->
            <a class="navbar-brand" href="#">
                <img src="image/logo.png" alt="圆角图片">
                <span class="logotxt">存证通可信数据存证服务平台</span>
            </a>
        </div>

        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example">
            <!-- 右侧的导航项目 -->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="your/nice/url" class="dropdown-toggle" data-toggle="dropdown">测试用户 <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="your/nice/url">账号信息</a></li>
                        <li><a href="your/nice/url">著作权证书</a></li>
                        <li><a href="your/nice/url">账户明细</a></li>
                        <li><a href="your/nice/url">登出</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
                <button class="btn btn-link" type="button">证书 42</button>
                <button class="btn btn-link" type="button">余额 665234.54元</button>
            </form>
        </div><!-- END .navbar-collapse -->
    </nav>
--%>

    <%@ include file="header.jsp" %>
<div class="main-container" style="margin-top: 20px; padding-top: 15px;">
    <h2>${typeName}登记信息</h2>

    <div class="row kexin-detail" style="min-height: 400px">
        <table width="100%">
            <tr>
                <td>企业名称：</td>
                <td>河南静瑶视频有限公司</td>
            </tr>
            <tr>
                <td>工商（或机构）注册号：</td>
                <td>xxxx--xxxx(1234)</td>
            </tr>
            <tr>
                <td>负责人：</td>
                <td>李宇春</td>
            </tr>
            <tr>
                <td>注册机构：</td>
                <td>河南省工商局</td>
            </tr>
            <tr>
                <td>xxx：</td>
                <td>xxx</td>
            </tr>
            <tr>
                <td>区块链ID：</td>
                <td><a href="#">xxxx-x--x-x-x-x---x-x-x-x--x-x-x-x-x-</a></td>
            </tr>
        </table>
    </div>



    <div class="row" style="text-align: center; margin-top: 20px">
        <button class="btn btn-lg btn-primary" type="button" style="width: 180px">下载附件</button>
        <button class="btn btn-lg" type="button" style="margin-left: 40px; width: 180px">返回</button>
    </div>

</div>
</div>
<div class="with-padding footer center-block">
    <div class="copyleft">
        <p>版权所有&#169;电子商务交易技术国家工程实验室 京ICP备15039571号-2</p>
    </div>
</div>
<script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
<script src="<s:url value='/dist/js/zui.min.js' />"></script>
<script>
    $(function(){
        var height = document.documentElement.clientHeight;
        $('.body-wrapper').css("min-height", (height - 60)+"px");
        $( window ).resize(function() {
            var height = document.documentElement.clientHeight
            $('.body-wrapper').css("min-height", (height - 60)+"px");
        });
    })
</script>
</body>
</html>
