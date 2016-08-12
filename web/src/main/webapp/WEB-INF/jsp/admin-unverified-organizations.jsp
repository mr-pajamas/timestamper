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
  <title>企业认证 - 存证通可信数据保全服务平台</title>
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
        <li><a href="<s:url value='/admin/um' />">用户管理</a></li>
        <li><a href="<s:url value='/admin/unverified-individuals' />">个人身份认证</a></li>
        <li class="active"><a href="<s:url value='/admin/unverified-organizations' />">企业认证</a></li>
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
      <h1 class="page-header">待认证企业</h1>

      <!--<h2 class="sub-header">Section title</h2>-->
      <c:choose>
        <c:when test="${not empty users}">

          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>企业名称</th>
                  <th>工商注册号</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="user" items="${users}">
                  <tr>
                    <td>${user.value.name}</td>
                    <td>${user.value.registrationNumber}</td>
                    <td>
                      <a class="btn btn-info btn-xs" href="#" role="button">详情</a>
                      <a class="btn btn-success btn-xs" href="<s:url value='/admin/verify-user?type=organization&userId=${user.value.id}' />" role="button">通过认证</a>
                      <a class="btn btn-danger btn-xs" href="#" role="button" onclick="showModal('${user.value.id}'); return false;">驳回认证</a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </c:when>
        <c:otherwise>
          <p>未找到任何符合条件的企业</p>
        </c:otherwise>
      </c:choose>

    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="failModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>
        <h4 class="modal-title" id="failModalLabel">驳回认证</h4>
      </div>
      <div class="modal-body">
        <form action="<s:url value='/admin/verify-user?type=organization' />" method="post">
          <input type="hidden" name="userId" id="inputUserId">

          <div class="form-group">
            <label for="inputReasons" class="sr-only">驳回原因</label>
            <textarea class="form-control" rows="5" id="inputReasons" name="failReasons" placeholder="请输入驳回原因……"></textarea>
          </div>

          <button class="hidden" type="submit">驳回</button>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-danger">驳回</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<s:url value='/lib/bootstrap/js/bootstrap.min.js' />"></script>

<script>
  function showModal(userId) {
    $("#inputUserId").val(userId);
    $(".modal").modal();
  }

  $(function () {
    $(".modal-footer > .btn-danger").click(function () {
      $(this).parents(".modal").find("form").submit();
    });
  });
</script>
</body>
</html>
