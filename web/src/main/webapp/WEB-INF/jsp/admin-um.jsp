<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
  <title>用户管理 - 存证通可信数据保全服务平台</title>
  <!-- Bootstrap -->
  <link href="<s:url value='/lib/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
  <link href="<s:url value='/css/admin.css' />" rel="stylesheet">
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="<s:url value='/lib/html5shiv/html5shiv.min.js' />"></script>
  <script src="<s:url value='/lib/respond/respond.min.js' />"></script>
  <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">存证通管理后台</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">你好，管理员</a></li>
      </ul>
      <!--
      <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="Search...">
      </form>
      -->
    </div>
  </div>
</nav>

<div class="container-fluid">
  <div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
      <!--
      <ul class="nav nav-sidebar">
        <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Reports</a></li>
        <li><a href="#">Analytics</a></li>
        <li><a href="#">Export</a></li>
      </ul>
      -->
      <ul class="nav nav-sidebar">
        <li><a href="<s:url value='/admin/dashboard' />">控制台</a></li>
        <li class="active"><a href="<s:url value='/admin/um' />">用户管理</a></li>
        <li><a href="<s:url value='/admin/unverified-individuals' />">个人身份认证</a></li>
        <li><a href="<s:url value='/admin/unverified-organizations' />">企业认证</a></li>
        <li><a href="#">文件管理</a></li>
      </ul>
      <!--
      <ul class="nav nav-sidebar">
        <li><a href="">Nav item again</a></li>
        <li><a href="">One more nav</a></li>
        <li><a href="">Another nav item</a></li>
      </ul>
      -->
    </div>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <!--
      <h1 class="page-header">Dashboard</h1>

      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4>Label</h4>
          <span class="text-muted">Something else</span>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4>Label</h4>
          <span class="text-muted">Something else</span>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4>Label</h4>
          <span class="text-muted">Something else</span>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4>Label</h4>
          <span class="text-muted">Something else</span>
        </div>
      </div>
      -->
      <h1 class="page-header">用户管理</h1>

      <!--<h2 class="sub-header">Section title</h2>-->
      <c:choose>
        <c:when test="${not empty users}">

          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>E-mail</th>
                  <th>手机</th>
                  <th>注册日期</th>
                  <th>认证状态</th>
                  <th>存证文档数</th>
                  <th>账户余额</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="user" items="${users}">
                  <tr>
                    <td>${user.value.email}</td>
                    <td>${user.value.mobile}</td>
                    <td><fmt:formatDate value="${user.value.registrationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                      <c:choose>
                        <c:when test="${user.type ne 'UNVERIFIED'}">
                          <c:choose>
                            <c:when test="${user.type eq 'INDIVIDUAL'}">认证个人</c:when>
                            <c:otherwise>认证企业</c:otherwise>
                          </c:choose>
                        </c:when>
                        <c:otherwise>未认证</c:otherwise>
                      </c:choose>
                    </td>
                    <td>
                      <c:choose>
                        <c:when test="${user.type ne 'UNVERIFIED'}">${user.value.certificateCount}</c:when>
                        <c:otherwise>-</c:otherwise>
                      </c:choose>
                    </td>
                    <td>
                      <c:choose>
                        <c:when test="${user.type ne 'UNVERIFIED'}">${user.value.balance}</c:when>
                        <c:otherwise>-</c:otherwise>
                      </c:choose>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </c:when>
        <c:otherwise>
          <p>未找到任何符合条件的用户</p>
        </c:otherwise>
      </c:choose>

    </div>
  </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<s:url value='/lib/bootstrap/js/bootstrap.min.js' />"></script>

</body>
</html>
