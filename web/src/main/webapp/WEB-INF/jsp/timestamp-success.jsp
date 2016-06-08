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
  <title>成功提交</title>

  <link rel="shortcut icon" href="<s:url value='/images/favicon.ico' />" type="image/vnd.microsoft.icon">

  <!-- Bootstrap -->
  <link href="<s:url value='/lib/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="<s:url value='/lib/html5shiv/html5shiv.min.js' />"></script>
  <script src="<s:url value='/lib/respond/respond.min.js' />"></script>
  <![endif]-->
</head>
<body>

  <div class="page-header">

    <div class="container">
      <h1>成功提交！</h1>
    </div>
  </div>

  <div class="container">

    <p class="lead">恭喜，材料提交成功！目前正在上传到区块链存证，请耐心等待</p>
    <p>数据指纹：<a href="https://www.blocktrail.com/tBTC/tx/${creditInfo.transactionId}">${creditInfo.digest}</a></p>

    <p><a class="btn btn-lg btn-primary" href="javascript:void(0)" onclick="history.back()" role="button">返回继续上传</a></p>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="<s:url value='/lib/bootstrap/js/bootstrap.min.js' />"></script>
</body>
</html>
