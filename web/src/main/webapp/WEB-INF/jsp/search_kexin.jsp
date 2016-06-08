<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>可信搜索结果</title>
  <link href="<s:url value='/dist/css/zui.min.css' />" rel="stylesheet">
  <link href="<s:url value='/css/common.css' />" rel="stylesheet">
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

<div class="main-container" style="margin-top: 76px; padding-top: 15px; min-height: 500px;">
  <h2>查询企业信息</h2>
  <form class="form-horizontal" action="" role="form" method="get">
    <div class="form-group" style="margin-top: 20px">
      <div class="col-md-11">
        <input type="text" name="q" class="form-control" value="${currentQuery}" placeholder="请输入查验ID或名称">
      </div>
      <div class="col-md-1">
        <button class="btn btn-block " type="submit">查询</button>
      </div>
    </div>
  </form>

  <c:forEach var="creditInfo" items="${creditInfos}">
    <div class="row kexin-item">
      <div class="col-md-9 item-detail">
        <h4>${creditInfo.name}</h4>
        <p>工商（或机构）注册号：95616236461234</p>
      </div>
      <div class="col-md-3 item-time">
        <h4>登记时间</h4>
        <p><fmt:formatDate value="creditInfo.registrationTime" pattern="yyyy-MM-dd HH:mm:ss"/></p>
      </div>
    </div>
  </c:forEach>

  <!--<div class="row kexin-item">
    <div class="col-md-9 item-detail">
      <h4>河南金药视频有限公司</h4>
      <p>工商（或机构）注册号：95616236461234</p>
    </div>
    <div class="col-md-3 item-time">
      <h4>登记时间</h4>
      <p>2016-04-16</p>
    </div>
  </div>

  <div class="row kexin-item">
    <div class="col-md-9 item-detail">
      <h4>河南金药视频有限公司</h4>
      <p>工商（或机构）注册号：95616236461234</p>
    </div>
    <div class="col-md-3 item-time">
      <h4>登记时间</h4>
      <p>2016-04-16</p>
    </div>
  </div>

  <div class="row kexin-item">
    <div class="col-md-9 item-detail">
      <h4>河南金药视频有限公司</h4>
      <p>工商（或机构）注册号：95616236461234</p>
    </div>
    <div class="col-md-3 item-time">
      <h4>登记时间</h4>
      <p>2016-04-16</p>
    </div>
  </div>

  <div class="row kexin-item">
    <div class="col-md-9 item-detail">
      <h4>河南金药视频有限公司</h4>
      <p>工商（或机构）注册号：95616236461234</p>
    </div>
    <div class="col-md-3 item-time">
      <h4>登记时间</h4>
      <p>2016-04-16</p>
    </div>
  </div>-->

  <div class="row" style="text-align: center; margin-top: 20px">
    <button class="btn btn-lg" type="button" style="width: 200px"><i class="icon icon-angle-down"></i> 显示更多</button>
  </div>

</div>

<div class="with-padding footer">
  <div class="row sitemap">
    <div class="col-md-3">
      <img src="<s:url value='/image/lab-logo.png' />" class="img-rounded" alt="圆角图片">
    </div>
    <div class="col-md-3">
      <p class="sitemap-head">产品与服务</p>
      <div class="sitemap-item">
        <p>服务1</p>
        <p>服务2</p>
      </div>
    </div>
    <div class="col-md-3">
      <p class="sitemap-head">存证通</p>
      <div class="sitemap-item">
        <p>关于我们</p>
        <p>联系我们</p>
      </div>
    </div>
    <div class="col-md-3">
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

<script src="<s:url value='/dist/lib/jquery/jquery.js' />"></script>
<script src="<s:url value='/dist/js/zui.min.js' />"></script>
</body>
</html>
