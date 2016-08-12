<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


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
