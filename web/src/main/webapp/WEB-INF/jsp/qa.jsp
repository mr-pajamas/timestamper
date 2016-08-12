<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>常见问题 - 存证通可信数据保全服务平台</title>
</head>
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
            <form class="navbar-form navbar-right" role="search">
                <button type="submit" class="btn btn-danger">登录 / 注册</button>
            </form>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 5px">
                <li><a href="#">服务价格</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
        </div><!-- END .navbar-collapse -->
    </nav>
--%>

    <%@ include file="header.jsp" %>
<div class="with-padding center-block" style="margin-top: 20px; width: 600px;">
    <div class="row qa-item">
        <div class="col-md-1 col-xs-1 tip">
            <i class="icon icon-chat"></i>
        </div>
        <div class="col-md-11 col-xs-11">
            <div class="qa-head">
                什么是区块链？
            </div>
            <div class="qa-body">
                区块链（Blockchain）是由节点参与的分布式数据库系统，其特点是不可更改、不可伪造。
            </div>
        </div>
    </div>
    <div class="row qa-item">
        <div class="col-md-1 col-xs-1 tip">
            <i class="icon icon-chat"></i>
        </div>
        <div class="col-md-11 col-xs-11">
            <div class="qa-head">
                什么是存证通？
            </div>
            <div class="qa-body">
                存证通是国内首个基于区块链技术的第三方可信数据存证服务平台，
                由电子商务交易技术国家工程实验室建设运营，平台为各类平台、企业、机构及个人提供便捷、可靠的数据存证和查验服务。
            </div>
        </div>
    </div>
    <div class="row qa-item">
        <div class="col-md-1 col-xs-1 tip">
            <i class="icon icon-chat"></i>
        </div>
        <div class="col-md-11 col-xs-11">
            <div class="qa-head">
                存证通存证的数据类型有限定么？
            </div>
            <div class="qa-body">
                没有限定，任何格式数据、文件均可通过存证通进行存证，包括电子商务市场主体基础信息、商品基础信息、电子发票、电子合同、图片、音乐、电影、文学创作等.
            </div>
        </div>
    </div>
    <div class="row qa-item">
        <div class="col-md-1 col-xs-1 tip">
            <i class="icon icon-chat"></i>
        </div>
        <div class="col-md-11 col-xs-11">
            <div class="qa-head">
                存证通收费么？
            </div>
            <div class="qa-body">
                收费。为保证更好、持续的为用户提供存证服务，我们需要收取少量费用用于平台运维。收费标准如下：单次购买5元一次；30元10次服务套餐；100元50次套餐。
            </div>
        </div>
    </div>
    <div class="row qa-item">
        <div class="col-md-1 col-xs-1 tip">
            <i class="icon icon-chat"></i>
        </div>
        <div class="col-md-11 col-xs-11">
            <div class="qa-head">
                存证通的存证证书会过期么？
            </div>
            <div class="qa-body">
                不会。存证通的存证证书永远有效。
            </div>
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
