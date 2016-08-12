<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>身份信息审核通过 - 存证通可信数据保全服务平台</title>
</head>
<body>
<div class="center-block body-wrapper">

    <%@ include file="header.jsp" %>
<div style="width: 500px;" class="center-block with-padding">

    <h1 class="text-success clearfix"><i class="fa fa-check-circle-o fa-3x pull-left" aria-hidden="true"></i>身份信息审核通过！<br><small>恭喜！现在您可以使用存证服务了</small></h1>
    <br>
    <div class="panel panel-default">
        <div class="panel-heading" style="height: 40px">您的身份信息</div>
        <ul class="list-group">
          <li class="list-group-item clearfix">
            <h4 class="list-group-item-heading col-md-3">姓名</h4>
            <p class="list-group-item-text text-muted col-md-9">${principal.value.name}</p>
          </li>
          <li class="list-group-item clearfix">
            <h4 class="list-group-item-heading col-md-3">身份证号</h4>
            <p class="list-group-item-text text-muted col-md-9">${principal.value.idCardNumber}</p>
          </li>
        </ul>
    </div>
</div>
</div>
<div class="with-padding footer center-block">
    <div class="copyleft">
        <p>版权所有&#169;电子商务交易技术国家工程实验室 京ICP备15039571号-2</p>
    </div>
</div>
<script src="<s:url value='/dist/lib/jquery/jquery.js' />"></script>
<script src="<s:url value='/dist/js/zui.min.js' />"></script>

<script>
    $(function(){
        var height = document.documentElement.clientHeight;
        $('.body-wrapper').css("min-height", (height - 60)+"px");
        $( window ).resize(function() {
            var height = document.documentElement.clientHeight
            $('.body-wrapper').css("min-height", (height - 60)+"px");
        });
    })
</script>
</body>
</html>
