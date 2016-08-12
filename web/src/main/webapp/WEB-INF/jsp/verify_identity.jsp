<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>身份认证 - 存证通可信数据保全服务平台</title>
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
<div style="width: 500px; text-align: center" class="center-block with-padding">
    <h1>身份认证</h1>
    <br>

    <ul class="nav nav-tabs nav-justified">
        <li id="individual-tab" class="active"><a href="#">个人用户</a></li>
        <li id="organization-tab"><a href="#">企业用户</a></li>
    </ul>

    <form id="individual-info-form" class="form-horizontal" role="form" action="?type=individual" method="post" style="margin-top: 20px" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-md-3 control-label">姓名:</label>
            <div class="col-md-9">
                <input type="text" name="name" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">身份证号:</label>
            <div class="col-md-9">
                <input type="text" name="idCardNo" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">身份证正面:</label>


            <div class="col-md-9 text-left">
                <label class="btn btn-default" title="上传正面照片">上传照片<input type="file" name="idCardFrontPic" style="display: none;" accept="image/*"></label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-9 col-md-offset-3">
                <img id="front-pic-preview" alt="" class="img-responsive" style="display: none;">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">身份证反面:</label>


            <div class="col-md-9 text-left">
                <label class="btn btn-default" title="上传反面照片">上传照片<input type="file" name="idCardBackPic" style="display: none;" accept="image/*"></label>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-9 col-md-offset-3">
                <img id="back-pic-preview" alt="" class="img-responsive" style="display: none;">
            </div>
        </div>

        <hr>

        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="提交">
            </div>
        </div>

    </form>


    <form id="organization-info-form" class="form-horizontal" role="form" action="?type=organization" method="post" style="margin-top: 20px; display: none;" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-md-3 control-label">企业名称:</label>
            <div class="col-md-9">
                <input type="text" name="name" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">工商注册号:</label>
            <div class="col-md-9">
                <input type="text" name="registrationNo" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">营业执照扫描件:</label>


            <div class="col-md-9 text-left">
                <label class="btn btn-default" title="上传扫描件">上传照片<input type="file" name="licensePic" style="display: none;" accept="image/*"></label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-9 col-md-offset-3">
                <img id="license-pic-preview" alt="" class="img-responsive" style="display: none;">
            </div>
        </div>

        <hr>

        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="提交">
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
    $(function () {
        $(".nav-tabs > li").click(function () {
           $(this).siblings(".active").removeClass("active");
            $(this).addClass("active");
        });

        $("#individual-tab").click(function () {
            $("#organization-info-form").hide();
            $("#individual-info-form").show();
        });

        $("#organization-tab").click(function () {
            $("#individual-info-form").hide();
            $("#organization-info-form").show();
        });

        $("input[name=idCardFrontPic]").change(function () {
            var f = this.files[0];

            var reader = new FileReader();
            reader.onload = function (e) {
                $("#front-pic-preview").attr("src", e.target.result).show();
            };
            reader.readAsDataURL(f);
        });

        $("input[name=idCardBackPic]").change(function () {
            var f = this.files[0];

            var reader = new FileReader();
            reader.onload = function (e) {
                $("#back-pic-preview").attr("src", e.target.result).show();
            };
            reader.readAsDataURL(f);
        });

        $("input[name=licensePic]").change(function () {
            var f = this.files[0];

            var reader = new FileReader();
            reader.onload = function (e) {
                $("#license-pic-preview").attr("src", e.target.result).show();
            };
            reader.readAsDataURL(f);
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
