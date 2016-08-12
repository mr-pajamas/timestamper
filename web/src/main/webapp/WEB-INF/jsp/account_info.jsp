<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>帐号信息 - 存证通可信数据保全服务平台</title>
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
<div style="width: 400px; text-align: center" class="center-block with-padding">
    <h1>账号信息</h1>
    <br>
    <form class="form-horizontal" role="form" method="post" style="margin-top: 20px">
        <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label">邮箱:</label>
            <div class="col-md-6 col-xs-3">
                <input type="email" name="keywords" value="" class="form-control">
            </div>
            <div class="col-md-3 col-xs-3">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">已验证
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label">手机:</label>
            <div class="col-md-6 col-xs-6">
                <input type="email" name="keywords" value="" class="form-control">
            </div>
            <div class="col-md-2 col-xs-2">
                <input type="submit" class="btn btn-primary" value="验证手机">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 col-xs-3 control-label">新密码:</label>
            <div class="col-md-6 col-xs-6">
                <input type="email" name="keywords" value="" class="form-control">
            </div>
            <div class="col-md-2 col-xs-2">
                <input type="submit" class="btn btn-primary" value="修改密码">
            </div>
        </div>
    </form>
</div>
</div>
<div class="with-padding footer center-block">
    <div class="copyleft">
        <p>版权所有&#169;电子商务交易技术国家工程实验室 京ICP备15039571号-2</p>
    </div>
</div>
<script src="<s:url value='/dist/lib/jquery/jquery.js' />"></script>
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
