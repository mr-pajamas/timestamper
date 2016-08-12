<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>后台登录 - 存证通可信数据保全服务平台</title>

  <link rel="shortcut icon" href="<s:url value='/images/favicon.ico' />" type="image/vnd.microsoft.icon">

  <!-- Bootstrap -->
  <link href="<s:url value='/lib/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">

  <style type="text/css">
    body {
      padding-top: 40px;
      padding-bottom: 40px;
      background-color: #eee;
    }

    .form-signin {
      max-width: 330px;
      padding: 15px;
      margin: 0 auto;
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
      margin-bottom: 20px;
    }
    .form-signin .checkbox {
      font-weight: normal;
    }
    .form-signin .form-control {
      position: relative;
      height: auto;
      -webkit-box-sizing: border-box;
         -moz-box-sizing: border-box;
              box-sizing: border-box;
      padding: 10px;
      font-size: 16px;
    }
    .form-signin .form-control:focus {
      z-index: 2;
    }
    .form-signin input[type="password"] {
      margin-bottom: 10px;
      border-top-left-radius: 0;
      border-top-right-radius: 0;
    }
  </style>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="<s:url value='/lib/html5shiv/html5shiv.min.js' />"></script>
  <script src="<s:url value='/lib/respond/respond.min.js' />"></script>
  <![endif]-->
</head>
<body>

  <div class="container">

    <form class="form-signin" method="post">
      <h2 class="form-signin-heading">后台登录</h2>
      <label for="inputPassword" class="sr-only">密码</label>
      <input type="password" id="inputPassword" name="password" class="form-control" placeholder="管理员密码" required autofocus>
      <div class="checkbox">
        <label>
          <input type="checkbox" value="remember-me"> 保持登录状态
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

  </div> <!-- /container -->

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="<s:url value='/lib/bootstrap/js/bootstrap.min.js' />"></script>
</body>
</html>
