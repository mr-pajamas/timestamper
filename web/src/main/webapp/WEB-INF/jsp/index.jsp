<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>首页</title>
  <link href="<s:url value='/dist/css/zui.min.css' />" rel="stylesheet">
  <link href="<s:url value='/css/common.css' />" rel="stylesheet">
  <script src="<s:url value='/dist/lib/jquery/jquery.js' />"></script>
  <script src="<s:url value='/dist/js/zui.min.js' />"></script>
  <script src="<s:url value='/jquery.ajaxupload.js' />"></script>
  <script src="<s:url value='/sha256.js' />"></script>
  <script>
    $(function(){
      var width = document.body.clientWidth;
      $('#myNiceCarousel img').width(width);
      $( window ).resize(function() {
          var width = document.body.clientWidth;
          $('#myNiceCarousel img').width(width);
      });
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
  </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar">
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
      <span class="logotxt">存证通</span>
    </a>
  </div>

  <!-- 导航项目 -->
  <div class="collapse navbar-collapse navbar-collapse-example" style="margin-top: 16px; font-size: larger">
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

<div id="myNiceCarousel" class="carousel slide center-block" data-ride="carousel" style="margin-top: 76px">
  <!-- 圆点指示器 -->
  <!--<ol class="carousel-indicators">
    <li data-target="#myNiceCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myNiceCarousel" data-slide-to="1"></li>
    <li data-target="#myNiceCarousel" data-slide-to="2"></li>
  </ol>-->

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

  <h2>主要功能</h2>
  <div class="row main-function">
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="http://zui.sexy/docs/img/img1.jpg" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>有效</h3>
            <p>数据指纹存于全球最大区块链公共账本，全球用户共同鉴证</p>
        </div>
    </div>
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="http://zui.sexy/docs/img/img1.jpg" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>便捷</h3>
            <p>用户提交数据即可，数据存证一步搞定</p>
        </div>
    </div>
    <div class="col-xs-12 col-md-4 row">
        <div class="function-head col-md-6 col-xs-6 with-padding">
            <img src="http://zui.sexy/docs/img/img1.jpg" class="img-rounded" alt="圆角图片">
        </div>
        <div class="function-body col-md-6 col-xs-6 with-padding">
            <h3>安全</h3>
            <p>采用基于区块链的存证技术，数据指纹不可篡改</p>
        </div>
    </div>
  </div>

  <h2 style="margin-top: 30px">合作单位</h2>
  <div class="row party with-padding">
    <div class="col-md-5 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/kexin-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>可信平台</h3>
        <p>电子商务可信交易监管与公共服务平台是在国家工商行政总局、国家商务部支持下，有电子商务交易技术国家工程实验室、中国电子商务可信交易服务</p>
      </div>
    </div>
    <div class="col-md-7 col-xs-12 query-form with-padding">
      <form method="get" action="${s:mvcUrl('LCIC#listCreditInfos').arg(0, 'manufacturers').buildAndExpand(fn:split('',','))}">
      <%--<form method="get" action="credit-info/manufacturers">--%>
      <h3>企业查询</h3>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" name="q" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="submit">查询</button>
        </div>
      </div>
      </form>
      <h3>商品查询</h3>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button">查询</button>
        </div>
      </div>
    </div>
  </div>

  <div class="row party with-padding">
    <div class="col-md-5 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/zhongqian-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>众签电子合同</h3>
        <p>众签电子合同综合服务平台是在电子商务交易技术国家工程实验室指导下，有北京智汇信元信息技术有限公司建设运营的服务平台，
          平台致力于提供当前中国网络市场升级发展亟需的互联网合约服务</p>
      </div>
    </div>
    <div class="col-xs-12 col-md-7 query-form">
      <h3>电子合同查询</h3>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button">查询</button>
        </div>
      </div>
      <p>你也可以通过上传文件查询</p>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button">上传文件</button>
        </div>
      </div>
    </div>
  </div>

  <div class="row party with-padding" style="margin-bottom: 60px">
    <div class="col-md-5 col-xs-12 row">
      <div class="col-md-3 col-xs-3 logo">
        <img src="<s:url value='/image/ruihong-logo.png' />" class="img-rounded" alt="圆角图片">
      </div>
      <div class="col-md-9 col-xs-9">
        <h3>电子发票综合服务平台</h3>
        <p>电子发票我综合服务平台是由电子商务交易技术国家工程实验室指导、由东港股份有限公司建设和运营的中国领先的电子发票平台，
          平台主要为使用电子商务交易系统进行交易的商户开具电子发票提供安全的平台</p>
      </div>
    </div>
    <div class="col-md-7 col-xs-12 query-form with-padding">
      <h3>电子发票查询</h3>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button">查询</button>
        </div>
      </div>
      <p>你也可以通过上传文件查询</p>
      <div class="row">
        <div class="col-xs-12 col-md-10">
          <input type="text" class="form-control">
        </div>
        <div class="col-xs-12 col-md-2">
          <button class="btn btn-primary btn-block" type="button" id="uploadBtn">上传文件</button>
        </div>
      </div>
    </div>
  </div>

</div>

<div class="with-padding footer">
  <div class="row sitemap">
    <div class="col-md-3 col-xs-12">
      <img src="<s:url value='/image/lab-logo.png' />" class="img-rounded" alt="圆角图片">
    </div>
    <div class="col-md-3 col-xs-6">
      <p class="sitemap-head">产品与服务</p>
      <div class="sitemap-item">
        <p>服务1</p>
        <p>服务2</p>
      </div>
    </div>
    <div class="col-md-3 col-xs-6">
      <p class="sitemap-head">存证通</p>
      <div class="sitemap-item">
        <p>关于我们</p>
        <p>联系我们</p>
      </div>
    </div>
    <div class="col-md-3 col-xs-6">
      <p class="sitemap-head">客服热线</p>
      <div class="sitemap-item">
        <p>4000000</p>
        <p>每周一到周五</p>
      </div>
    </div>
  </div>
  <div class="copyleft">
    <p>版权所有&#169;电子商务交易技术国家工程实验室</p>
    <p>电话：010-6279429
      地址：北京市海淀区清华大学中央主楼
      邮编：100084</p>
    <p>ICP备案号：京ICP备15039571号-2</p>
  </div>
</div>


</body>
</html>
