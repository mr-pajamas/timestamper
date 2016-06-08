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
  <title>可信平台存证</title>

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
      <h1>可信平台存证</h1>
    </div>
  </div>

  <div class="container">
    <form class="form-horizontal" action="" method="post" enctype="multipart/form-data">

      <div class="form-group">
        <label class="col-md-1 control-label">类别</label>
        <div class="col-md-11">
          <div class="radio radio-inline">
            <label>
              <input type="radio" name="type" value="MANUFACTURER" checked="checked"> 企业
            </label>
          </div>
          <div class="radio radio-inline">
            <label>
              <input type="radio" name="type" value="PRODUCT"> 商品
            </label>
          </div>
        </div>
      </div>

      <div class="form-group">
        <label for="inputCheckId" class="col-md-1 control-label">查验ID</label>
        <div class="col-md-11">
          <input type="text" class="form-control" id="inputCheckId" name="checkId" placeholder="查验ID">
        </div>
      </div>


      <div class="form-group">
        <label for="inputName" class="col-md-1 control-label">名称</label>
        <div class="col-md-11">
          <input type="text" class="form-control" id="inputName" name="name" placeholder="企业/商品名称">
        </div>
      </div>


      <div class="form-group">
        <label for="inputPrincipalName" class="col-md-1 control-label">主体名称</label>
        <div class="col-md-11">
          <input type="text" class="form-control" id="inputPrincipalName" name="principalName" placeholder="主体名称">
        </div>
      </div>

      <div class="form-group">
        <label for="inputDetails" class="col-md-1 control-label">详细信息</label>
        <div class="col-md-11">
          <textarea class="form-control" rows="5" id="inputDetails" name="details"></textarea>
        </div>
      </div>

      <div class="form-group">
        <div class="col-md-offset-1 col-md-11">
          <label class="btn btn-default" title="上传附件">上传附件<input type="file" name="attachment" style="display: none;" accept="*/*"></label>
        </div>
      </div>

      <hr>

      <div class="form-group">
        <div class="col-md-offset-1 col-md-11">
          <button type="submit" class="btn btn-primary btn-lg">提交存证</button>
        </div>
      </div>
    </form>
  </div>


  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="<s:url value='/lib/jquery/jquery.min.js' />"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="<s:url value='/lib/bootstrap/js/bootstrap.min.js' />"></script>
</body>
</html>
