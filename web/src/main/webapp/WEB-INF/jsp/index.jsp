<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <%@ include file="head.jsp" %>
  <title>首页 - 存证通可信数据保全服务平台</title>

  <script src="<s:url value='/dist/lib/jquery/jquery.js' />"></script>
  <script src="<s:url value='/dist/js/zui.min.js' />"></script>
  <script src="<s:url value='/jquery.ajaxupload.js' />"></script>
  <%--<script src="<s:url value='/sha256.js' />"></script>--%>

  <script src="<s:url value='/lib/cryptojs/sha1.js' />"></script>

  <script src="<s:url value='/lib/cryptojs/enc-base64-min.js' />"></script>
  <%--<script>
    $(function(){
      /*var width = document.body.clientWidth;
      $('#myNiceCarousel img').width(width);
      $( window ).resize(function() {
          var width = document.body.clientWidth;
          $('#myNiceCarousel img').width(width);
      });*/
      $.ajaxUploadSettings.name = 'uploads[]';
      $('#uploadBtn').ajaxUploadPrompt({
        beforeSend : function (ajax, data) {
          var reader = new FileReader();
          reader.onload = function() {
            alert(sha256_digest(this.result));
          }
          reader.readAsText(data.files[0]);
          return false;
        },
      });
    });
  </script>--%>
</head>
<body>
<div class="center-block body-wrapper">
  <nav class="navbar navbar-default navbar-fixed-top center-block" role="navigation" id="navbar">
    <!-- 导航头部 -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- 品牌名称或logo -->
      <a class="navbar-brand" href="<s:url value='/' />">
        <img src="<s:url value='/image/logo.png' />" alt="圆角图片">
        <!--<span class="logotxt">存证通可信数据存证服务平台</span>-->
      </a>
    </div>

    <!-- 导航项目 -->
    <div class="collapse navbar-collapse navbar-collapse-example" style="margin-top: 16px; font-size: larger">
      <!-- 右侧的导航项目 -->
      <c:choose>
        <c:when test="${principal eq null}">
          <form class="navbar-form navbar-right" role="search" action="<s:url value='/sign-in' />" method="get">
            <button type="submit" class="btn btn-danger">登录 / 注册</button>
          </form>
          <ul class="nav navbar-nav navbar-right" style="margin-right: 5px">
            <li><a href="<s:url value='/about-us' />">关于我们</a></li>
            <li><a href="<s:url value='/faq' />">常见问题</a></li>
          </ul>
        </c:when>
        <c:otherwise>
          <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">${principal.value.email eq null ? principal.value.mobile : principal.value.email} <b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                      <li><a href="<s:url value='/my-account' />">账号信息</a></li>
                    <li><a href="<s:url value='/my-identity' />">身份认证</a></li>
                      <li><a href="#">著作权证书</a></li>
                      <li><a href="#">账户明细</a></li>
                      <li><a href="<s:url value='/sign-out' />">登出</a></li>
                  </ul>
              </li>
          </ul>
          <c:if test="${principal.type ne 'UNVERIFIED'}">
            <form class="navbar-form navbar-right" role="search">
                <button class="btn btn-link" type="button"><i class="fa fa-file-text-o fa-lg" aria-hidden="true"></i>&nbsp;&nbsp;${principal.value.certificateCount} / ${principal.value.balance}</button>
            </form>
          </c:if>
        </c:otherwise>
      </c:choose>
    </div><!-- END .navbar-collapse -->
  </nav>

<div id="myNiceCarousel" class="carousel slide center-block" data-ride="carousel" style="margin-top: 76px">
  <!-- 圆点指示器 -->
  <ol class="carousel-indicators">
    <li data-target="#myNiceCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myNiceCarousel" data-slide-to="1"></li>
    <li data-target="#myNiceCarousel" data-slide-to="2"></li>
  </ol>

  <!-- 轮播项目 -->
  <div class="carousel-inner">
    <div class="item active">
      <img alt="First slide" src="<s:url value='/image/czt.jpg' />">
      <div class="carousel-caption">
        <h3></h3>
        <p></p>
      </div>
    </div>
    <div class="item">
        <img alt="Second slide" src="<s:url value='/image/rzpt.jpg' />">
        <div class="carousel-caption">
            <h3></h3>
            <p></p>
        </div>
    </div>
    <div class="item">
        <img alt="Third slide" src="<s:url value='/image/wfcg.jpg' />">
        <div class="carousel-caption">
            <h3></h3>
            <p></p>
        </div>
    </div>
  </div>

  <!-- 项目切换按钮 -->
  <!--<a class="left carousel-control" href="#myNiceCarousel" data-slide="prev">
    <span class="icon icon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#myNiceCarousel" data-slide="next">
    <span class="icon icon-chevron-right"></span>
  </a>-->
</div>

<div class="main-container with-padding">

  <%--<h2>主要功能</h2>--%>
  <div class="row main-function">
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="<s:url value='/images/youxiao0.png' />" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>有效</h3>
            <p>数据指纹存于全球最大区块链公共账本，全球用户共同鉴证</p>
        </div>
    </div>
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="<s:url value='/images/bianjie0.png' />" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>便捷</h3>
            <p>用户提交数据即可，数据存证一步搞定</p>
        </div>
    </div>
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="<s:url value='/images/anquan0.png' />" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>安全</h3>
            <p>采用基于区块链的存证技术，数据指纹不可篡改</p>
        </div>
    </div>
  </div>

  <h2 style="margin-top: 30px">合作平台</h2>
  <div class="row party with-padding">
    <div class="col-md-6 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/kexin-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>可信平台</h3>
        <p>电子商务可信交易监管与公共服务平台是在国家工商行政总局支持下，由电子商务交易技术国家工程实验室、中国电子商务可信交易服务中心建设运营的公共服务平台，旨在建设形成规范、真实、权威、唯一的电子商务市场企业及其商品基础信息数据库，提供企业及其商品基础信息查验、共享服务，提高电子商务市场可信度。</p>
      </div>
    </div>
    <div class="col-md-6 col-xs-12 query-form with-padding">
      <form method="get" action="${s:mvcUrl('LCIC#listCreditInfos').arg(0, 'manufacturers').buildAndExpand(fn:split('',','))}">
      <%--<form method="get" action="credit-info/manufacturers">--%>
      <h3>企业查验</h3>
      <div class="row">
        <div class="col-xs-12 col-md-9">
          <input type="text" name="q" class="form-control" placeholder="请输入企业名称，支持模糊检索">
        </div>
        <div class="col-xs-12 col-md-3">
          <button class="btn btn-primary btn-block" type="submit">查验</button>
        </div>
      </div>
      </form>
      <form method="get" action="${s:mvcUrl('LCIC#listCreditInfos').arg(0, 'products').buildAndExpand(fn:split('',','))}">
        <h3>商品查验</h3>
        <div class="row">
          <div class="col-xs-12 col-md-9">
            <input type="text" name="q" class="form-control" placeholder="请输入商品名称，支持模糊检索">
          </div>
          <div class="col-xs-12 col-md-3">
            <button class="btn btn-primary btn-block" type="submit">查验</button>
          </div>
        </div>
      </form>
    </div>
  </div>

  <div class="row party with-padding">
    <div class="col-md-6 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/zhongqian-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>众签电子合同服务平台</h3>
        <p>众签是在电子商务交易技术国家工程实验室指导下，由北京众签科技有限公司研发，为企业、机构、个人提供合同管理、在线签署、证据保全的电子合同服务平台。平台旨在通过安全、有效、便捷的电子合同全流程服务帮助用户提高合同签署效率，降低合同管理成本。</p>
      </div>
    </div>
    <div class="col-xs-12 col-md-6 query-form">
      <h3>电子合同查验</h3>
      <div class="row">
        <div class="col-xs-12 col-md-9">
          <input type="text" class="form-control" id="eContractInput" placeholder="请输入完整的电子合同编号">
        </div>
        <div class="col-xs-12 col-md-3">
          <button class="btn btn-primary btn-block" type="button" data-toggle="modal" id="eContractCheck" data-target="#myModal1">查验</button>
        </div>
      </div>
      <br>
      <div class="row">
        <div class="col-xs-12 col-md-9">
          您也可以上传电子合同文件查验
        </div>
        <div class="col-xs-12 col-md-3">
          <label class="btn btn-primary btn-block" title="上传文件" id="eContractChooser">上传文件<input type="file" name="" style="display: none;" accept="*/*"></label>
          <%--<button class="btn btn-primary btn-block" type="button">上传文件</button>--%>
        </div>
      </div>
    </div>
  </div>

  <div class="row party with-padding" style="margin-bottom: 60px">
    <div class="col-md-6 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/ruihong-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>电子发票综合服务平台</h3>
        <p>电子发票综合服务平台是由电子商务交易技术国家工程实验室指导、由东港股份有限公司建设运营的中国领先电子发票平台，平台主要为使用电子商务交易系统进行交易的商户开具电子发票提供安全、可靠的平台，其主要包括电子发票管理平台、电子发票前置服务平台和电子发票应用平台三部分。</p>
      </div>
    </div>
    <div class="col-md-6 col-xs-12 query-form with-padding">
      <h3>电子发票查验</h3>
      <div class="row">
        <div class="col-xs-12 col-md-9">
          <input type="text" class="form-control" id="eInvoiceInput" placeholder="请输入完整的电子发票编号">
        </div>
        <div class="col-xs-12 col-md-3">
          <button class="btn btn-primary btn-block" type="button" data-toggle="modal" id="eInvoiceCheck" data-target="#myModal2">查验</button>
        </div>
      </div>
      <%--<p>您也可以上传电子发票文件查验</p>--%>
      <br>
      <div class="row">
        <div class="col-xs-12 col-md-9">
          您也可以上传电子发票文件查验
        </div>
  <div class="col-xs-12 col-md-3">
    <label class="btn btn-primary btn-block" title="上传文件" id="eInvoiceChooser">上传文件<input type="file" name="" style="display: none;" accept="*/*"></label>
    <%--<button class="btn btn-primary btn-block" type="button">上传文件</button>--%>
  </div>
<%--        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button" id="uploadBtn">上传文件</button>
        </div>--%>
      </div>
    </div>
  </div>

</div>

<div class="with-padding footer">
  <div class="row sitemap">
    <div class="col-md-3 col-xs-12">
      <img src="<s:url value='/image/lab-logo.png' />" class="img-rounded" alt="圆角图片">
    </div>
    <div class="col-md-4 col-xs-6">
      <p class="sitemap-head">相关链接</p>
      <div class="sitemap-item">
        <p><a href="http://www.nelect.org">电子商务可信交易技术国家工程实验室</a></p>
        <p><a href="http://www.ecdata.org.cn/srv/">可信平台</a></p>
        <p><a href="http://www.51signing.com">众签电子合同服务平台</a></p>
        <p><a href="http://www.e-inv.cn/">电子发票综合服务平台</a></p>
      </div>
    </div>
    <div class="col-md-2 col-xs-6">
      <p class="sitemap-head">存证通</p>
      <div class="sitemap-item">
        <p><a href="<s:url value='/about-us' />">关于我们</a></p>
        <p><a href="<s:url value='/faq' />">常见问题</a></p>
      </div>
    </div>
    <div class="col-md-3 col-xs-6">
        <p class="sitemap-head">联系我们</p>
        <div class="sitemap-item">
            <p>电话：010-6279429</p>
            <p>地址：北京市海淀区清华大学中央主楼</p>

          <p>邮编：100084</p>
          <p>传真：010-62794291</p>
        </div>
    </div>
  </div>
  <div class="copyleft">
    <p>版权所有&#169;电子商务交易技术国家工程实验室 京ICP备15039571号-2</p>
  </div>
</div>
</div>
<div class="modal" id="myModal1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">查验结果</h4>
            </div>
            <div class="modal-body">
<%--                <p><i class="icon icon-check" style="color: green"></i>该电子合同已由<span class="modal-tip">北京众签科技有限公司</span>于<span class="modal-tip">yyyy年MM月dd日 hh:mm:ss</span>通过存证通可信数据存证服务平台进行存证。</p>
                <p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">566as5d5f6asdf</a></p>
                <p><i class="icon icon-times" style="color: red"></i>该电子合同未通过存证通可信数据存证服务平台存证。</p>--%>
              <p>载入中……</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="myModal2">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">查验结果</h4>
            </div>
            <div class="modal-body">
<%--                <p><i class="icon icon-check" style="color: green"></i>该电子发票已由<span class="modal-tip">东港股份有限公司</span>于<span class="modal-tip">yyyy年MM月dd日 hh:mm:ss</span>通过存证通可信数据存证服务平台进行存证。</p>
                <p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">566as5d5f6asdf</a></p>
                <p><i class="icon icon-times" style="color: red"></i>该电子发票未通过存证通可信数据存证服务平台存证。</p>--%>
              <p>载入中……</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script>
  $(function () {
    $("#myModal1").on('hide.zui.modal', function () {
      $(this).find(".modal-body").html("<p>载入中……</p>");
    });

    $("#eContractCheck").click(function () {
      $.get("<s:url value='/e-contract' />", {certNumber: $("#eContractInput").val()})
        .done(function (data) {
            $("#myModal1").find(".modal-body").html(
              '<p><i class="icon icon-check" style="color: green"></i>该电子合同已由<span class="modal-tip">' + data.prindipal + '</span>于<span class="modal-tip">' + data.registrationDate + '</span>通过存证通可信数据存证服务平台进行存证。</p>' +
              '<p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">' + data.digest + '</a></p>'
            );
        })
        .fail(function () {
            $("#myModal1").find(".modal-body").html(
              '<p><i class="icon icon-times" style="color: red"></i>该电子合同未通过存证通可信数据存证服务平台存证。</p>'
            );
        });
    });

    $("#eContractChooser input[type=file]").change(function (event) {
      $("#myModal1").modal();

      var $target = $(event.currentTarget);

      var f = $target[0].files[0];
      var reader = new FileReader();
      reader.onload = function(e) {

        var encoded = e.target.result.slice(e.target.result.indexOf(",") + 1);
        var words = CryptoJS.enc.Base64.parse(encoded);

        var checksum = CryptoJS.SHA1(words).toString();

        $.get("<s:url value='/e-contract' />", {checksum: checksum})
          .done(function (data) {
              $("#myModal1").find(".modal-body").html(
                '<p><i class="icon icon-check" style="color: green"></i>该电子合同已由<span class="modal-tip">' + data.principal + '</span>于<span class="modal-tip">' + data.registrationDate + '</span>通过存证通可信数据存证服务平台进行存证。</p>' +
                '<p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">' + data.digest + '</a></p>'
              );
          })
          .fail(function () {
              $("#myModal1").find(".modal-body").html(
                '<p><i class="icon icon-times" style="color: red"></i>该电子合同未通过存证通可信数据存证服务平台存证。</p>'
              );
          });
      };
      //reader.readAsText(f);
      //reader.readAsBinaryString(f);
      reader.readAsDataURL(f);
    });



    $("#myModal2").on('hide.zui.modal', function () {
      $(this).find(".modal-body").html("<p>载入中……</p>");
    });

    $("#eInvoiceCheck").click(function () {
      $.get("<s:url value='/e-invoice' />", {certNumber: $("#eInvoiceInput").val()})
        .done(function (data) {
            $("#myModal2").find(".modal-body").html(
              '<p><i class="icon icon-check" style="color: green"></i>该电子发票已由<span class="modal-tip">' + data.prindipal + '</span>于<span class="modal-tip">' + data.registrationDate + '</span>通过存证通可信数据存证服务平台进行存证。</p>' +
              '<p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">' + data.digest + '</a></p>'
            );
        })
        .fail(function () {
            $("#myModal2").find(".modal-body").html(
              '<p><i class="icon icon-times" style="color: red"></i>该电子发票未通过存证通可信数据存证服务平台存证。</p>'
            );
        });
    });

    $("#eInvoiceChooser input[type=file]").change(function (event) {
      $("#myModal2").modal();

      var $target = $(event.currentTarget);

      var f = $target[0].files[0];
      var reader = new FileReader();
      reader.onload = function(e) {
        var checksum = CryptoJS.SHA1(e.target.result).toString();

        $.get("<s:url value='/e-invoice' />", {checksum: checksum})
          .done(function (data) {
              $("#myModal2").find(".modal-body").html(
                '<p><i class="icon icon-check" style="color: green"></i>该电子发票已由<span class="modal-tip">' + data.prindipal + '</span>于<span class="modal-tip">' + data.registrationDate + '</span>通过存证通可信数据存证服务平台进行存证。</p>' +
                '<p><i class="icon icon-check" style="color: green"></i>数据指纹为<a href="#">' + data.digest + '</a></p>'
              );
          })
          .fail(function () {
              $("#myModal2").find(".modal-body").html(
                '<p><i class="icon icon-times" style="color: red"></i>该电子发票未通过存证通可信数据存证服务平台存证。</p>'
              );
          });
      };
      reader.readAsText(f);
    });
  });
</script>

</body>
</html>
