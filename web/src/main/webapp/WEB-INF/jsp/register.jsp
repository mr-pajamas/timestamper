<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册</title>
    <link href="<s:url value='/dist/css/zui.min.css' />" rel="stylesheet">
    <link href="<s:url value='/css/common.css' />" rel="stylesheet">
</head>
<body>
<div class="center-block body-wrapper">
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
                <img src="<s:url value='/image/logo.png' />" alt="圆角图片">
                <!--<span class="logotxt">存证通可信数据存证服务平台</span>-->
            </a>
        </div>

        <!-- 导航项目 -->
        <div class="collapse navbar-collapse navbar-collapse-example">
            <!-- 右侧的导航项目 -->
            <form class="navbar-form navbar-right" role="search">
                <button type="submit" class="btn btn-danger">登陆 / 注册</button>
            </form>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 5px">
                <li><a href="#">服务价格</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
        </div><!-- END .navbar-collapse -->
    </nav>
<div class="center-block" style="width: 500px; margin-top: 20px" id="regForm">
    <div class="panel panel-success">
        <div class="panel-heading" style="text-align: center; height: 40px">

        </div>
        <div class="panel-body">
            <form id="sign-up-form" class="form-horizontal" role="form" action="<s:url value='/sign-up' />" method="post" style="margin-top: 20px">
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
                        <input type="text" name="pwd" id="pwd" value="" class="form-control" placeholder="密码">
                        <span class="warn-tip">&nbsp;</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 col-xs-3 control-label">确认密码：</label>
                    <div class="col-md-9 col-xs-9">
                        <input type="text" name="passwordCheck" id="passwordCheck" value="" class="form-control" placeholder="重新输入密码">
                        <span class="warn-tip">&nbsp;</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 col-xs-3 control-label">验证码：</label>
                    <div class="col-md-6 col-xs-6">
                        <input type="text" name="verificationCode" id="verificationCode" value="" class="form-control">
                        <span class="warn-tip">&nbsp;</span>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <input type="button" id="resend" class="btn btn-primary btn-block" value="发送">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3 col-xs-3"></div>
                    <div class="col-md-9 col-xs-9">
                        <input type="submit" id="reg" class="btn btn-primary btn-block" value="注册">
                    </div>
                </div>
            </form>
            <p class="text-center"><a href="<s:url value='/sign-in' />">登录已有帐号</a></p>
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
<script src="<s:url value='/lib/cryptojs/pbkdf2.js' />"></script>
<script src="<s:url value='/lib/cryptojs/sha256.js' />"></script>

<script>
    var PBKDF2_KEY_SIZE = 256 / 32;
    var PBKDF2_ITERATIONS = 1000;

    function hash(code, salt) {
        var saltWords = CryptoJS.enc.Hex.parse(salt);

        var hash = CryptoJS.PBKDF2(code, saltWords, {keySize: PBKDF2_KEY_SIZE, iterations: PBKDF2_ITERATIONS});

        return hash.toString();
    }

    var lastVerifiedResult = '';
    var lastVerifiedSalt = '';
    var lastExpireTime;
    var intervalHandler;
    var countDown = 60;
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

    $(function(){
        $('#resend').click(function(){
            if (emailOrMobile !== undefined) {
                $(this).val("重新发送(60)");
                $(this).attr("disabled","disabled");
                sendVerifiyCode(emailOrMobile);
                clearInterval(intervalHandler);
                countDown = 60;
                intervalHandler = setInterval(function(){
                    if (countDown >= 0) {
                        countDown--;
                        $('#resend').val("重新发送("+countDown+")");
                    } else {
                        $('#resend').removeAttr("disabled");
                        $('#resend').val("发送");
                    }
                }, 1000);
            } else {
                alert("用户名非法，必须为邮箱地址或手机号码");
            }
        });
    });

    function sendVerifiyCode(emailOrMobile) {
        if (emailOrMobile) {
            $.getJSON("<s:url value='/email-verification' />", {email: $('#username').val()}, function (result) {
                if (result && result.codeHashSalt) {
                    lastVerifiedSalt = result.codeHashSalt;
                    lastVerifiedResult = result.codeHash;
                    lastExpireTime = new Date(result.expiration);
                } else {
                    alert("发送验证码请求失败 重新发送");
                }
            })
        } else {
            $.getJSON("<s:url value='/mobile-verification' />", {mobile: $('#username').val()}, function(result) {
                if (result && result.codeHashSalt) {
                    lastVerifiedSalt = result.codeHashSalt;
                    lastVerifiedResult = result.codeHash;
                    lastExpireTime = new Date(result.expiration);
                } else {
                    alert("发送验证码请求失败 重新发送");
                }
            });
        }
    }

    function checkVerifyCode() {
        var currentTime = new Date();
        if (currentTime > lastExpireTime) {
            alert('验证码已经失效 请重新发送验证请求');
            return;
        }
        var userInputCode = $('#verificationCode').val();
        var target = hash(userInputCode, lastVerifiedSalt);
        if (target === lastVerifiedResult) {
            alert('本地校验通过');
        }
    }
</script>
<script>
    $(function(){
        var height = document.documentElement.clientHeight;
        $('.body-wrapper').css("min-height", (height - 60)+"px");
        $( window ).resize(function() {
            var height = document.documentElement.clientHeight
            $('.body-wrapper').css("min-height", (height - 60)+"px");
        });
    });

    $(function () {
        $("#sign-up-form").submit(function () {
            if ($(this).find("input[name=username]").val()) {
                if ($(this).find("input[name=pwd]").val()) {
                    if ($(this).find("input[name=passwordCheck]").val() == $(this).find("input[name=pwd]").val()) {
                        if (new Date() <= lastExpireTime) {
                            if (hash($('#verificationCode').val(), lastVerifiedSalt) === lastVerifiedResult) {
                                $(this).find("input[name=password]").val(CryptoJS.SHA256($(this).find("input[name=pwd]").val()).toString());
                                return true;
                            } else {
                                alert("验证码错误");
                            }
                        } else {
                            alert('请获取验证码');
                        }
                    } else {
                        alert("两次密码输入不一致");
                    }
                } else {
                    alert("请填写密码");
                }
            } else {
                alert("请填写账户名");
            }
            return false;
        });
    });
</script>
</body>
</html>
