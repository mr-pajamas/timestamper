<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="head.jsp" %>
    <title>未通过企业信息审核 - 存证通可信数据保全服务平台</title>
</head>
<body>
<div class="center-block body-wrapper">

    <%@ include file="header.jsp" %>
<div style="width: 500px;" class="center-block with-padding">

    <h1 class="text-danger clearfix"><i class="fa fa-times-circle-o fa-3x pull-left" aria-hidden="true"></i>未通过企业信息审核！<br><small>驳回原因：${failReasons}<br>您需要完善贵方信息并重新递交审核</small></h1>
    <br>
    <div class="panel panel-default">
        <div class="panel-heading" style="height: 40px">企业信息</div>
        <div class="panel-body">
            <form id="organization-info-form" class="form-horizontal" role="form" action="?type=organization" method="post" style="margin-top: 20px;" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-md-3 control-label">企业名称:</label>
                    <div class="col-md-9">
                        <input type="text" name="name" class="form-control" value="${principal.value.name}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">工商注册号:</label>
                    <div class="col-md-9">
                        <input type="text" name="registrationNo" class="form-control" value="${principal.value.registrationNumber}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">营业执照扫描件:</label>


                    <div class="col-md-9 text-left">
                        <label class="btn btn-default" title="上传扫描件">上传照片<input type="file" name="licensePic" style="display: none;" accept="image/*"></label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-9 col-md-offset-3">
                        <img id="license-pic-preview" alt="" class="img-responsive" style="display: none;">
                    </div>
                </div>

                <hr>

                <div class="form-group">
                    <div class="col-md-4 col-md-offset-4">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" value="重新提交">
                    </div>
                </div>

            </form>
        </div>
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
    $(function () {
        $("input[name=licensePic]").change(function () {
            var f = this.files[0];

            var reader = new FileReader();
            reader.onload = function (e) {
                $("#license-pic-preview").attr("src", e.target.result).show();
            };
            reader.readAsDataURL(f);
        });
    });
</script>

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
