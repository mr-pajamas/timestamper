<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="head.jsp" %>
    <title>登录 - 存证通可信数据保全服务平台</title>
</head>
<body>
<div class="center-block body-wrapper">
<%--    <nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0px" id="navbar">
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
                <img src="<s:url value='/image/logo.png' />" alt="圆角图片">
                <!--<span class="logotxt">存证通可信数据存证服务平台</span>-->
            </a>
        </div>

        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example">
            <!-- 右侧的导航项目 -->
            <form class="navbar-form navbar-right" role="search">
                <button type="submit" class="btn btn-danger">登录 / 注册</button>
            </form>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 5px">
                <li><a href="#">服务价格</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
        </div><!-- END .navbar-collapse -->
    </nav>--%>

<%@ include file="header.jsp" %>
<div class="with-padding center-block" style="width: 500px; margin-top: 20px" id="regForm">
    <div class="panel panel-success">
        <div class="panel-heading" style="text-align: center; height: 40px">
        </div>
        <div class="panel-body">
            <form id="sign-in-form" class="form-horizontal" role="form" action="<s:url value='/sign-in' />" method="post" style="margin-top: 20px">
                <input type="hidden" name="email">
                <input type="hidden" name="mobile">
                <input type="hidden" name="password">
                <div class="form-group">
                    <label class="col-md-3 col-xs-3 control-label">用户名：</label>
                    <!--<div class="col-md-9 col-xs-9 has-error">-->

                    <div class="col-md-9 col-xs-9">
                        <input type="text" name="username" id="username" value="" class="form-control" placeholder="邮箱/手机号">
                        <!--<span class="warn-tip">邮箱地址不正确</span>-->
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 col-xs-3 control-label">密码：</label>
                    <div class="col-md-9 col-xs-9">
                        <input type="password" name="pwd" id="pwd" value="" class="form-control" placeholder="密码">
                        <span class="warn-tip">&nbsp;</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3 col-xs-3"></div>
                    <div class="col-md-6 col-xs-6">
                        <input type="submit" id="login" class="btn btn-primary btn-block" value="登录">
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="rememberMe" value="true" checked="">记住密码
                        </label>
                    </div>
                </div>
            </form>

            <p class="text-center"><a href="<s:url value='/sign-up' />">注册新帐号</a></p>
        </div>
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
<script src="<s:url value='/lib/cryptojs/sha256.js' />"></script>
<script>
    var emailOrMobile = undefined;
    $(function () {
       $("#username").change(function () {
           if (/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test($('#username').val())) {
               emailOrMobile = false;
               $('input[name=mobile]').val($('#username').val());
           } else if (/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test($('#username').val())) {
               emailOrMobile = true;
               $('input[name=email]').val($('#username').val());
           }
       })
    });

    $(function () {
        $("#sign-in-form").submit(function () {
            if ($(this).find("input[name=mobile]").val() || $(this).find("input[name=email]").val()) {
                if ($(this).find("input[name=pwd]").val()) {
                    $(this).find("input[name=password]").val(CryptoJS.SHA256($(this).find("input[name=pwd]").val()).toString());
                    return true;
                } else {
                    alert("请填写密码");
                }
            } else {
                alert("请填写正确的账户名");
            }
            return false;
        });
    });
</script>

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
