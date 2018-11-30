<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>管理员登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" type="text/css" href="source/css/style.css"/>
    <script type="text/javascript" src="source/js/jquery.min.js"></script>
    <script src="source/js/verificationNumbers.js"></script>
    <script src="source/js/Particleground.js"></script>

    <style type="text/css">
        body {
            height: 100%;
            background: #213838;
            overflow: hidden;
        }
        canvas {
            z-index: -1;
            position: absolute;
            display: none;
        }
        h1 {
            color: #fff;
            text-align: center;
            font-weight: 100;
            margin-top: 40px;
        }
        #media {
            width: 280px;
            height: 250px;
            margin: 10px auto 0;
            border-radius: 30px;
            overflow: hidden;
        }
    </style>
</head>

<body>
<div style="float:right;margin-right:80px;margin-top:40px;"><a href="login.jsp"><img src="source/images/return.png" alt=""></a></div>
<form action="" method="post" style="margin-top:100px;">
    <dl class="admin_login">
        <dt>
            <strong>云通WMS</strong><strong style="margin-top:10px;">请将脸放摄像头前</strong>
        </dt>
        <div id="media">
            <video id="video" width="530" height="300" autoplay></video>
            <canvas id="canvas" width="400" height="300"></canvas>
        </div>
        <%--<dd style="margin-top:20px;" >--%>
            <%--<input type="button" onclick="query()" value="立即登录"--%>
                   <%--class="submit_btn"/>--%>
        <%--</dd>--%>


    </dl>
</form>
<script type="text/javascript">
    //var 是定义变量
    var video = document.getElementById("video"); //获取video标签
    var context = canvas.getContext("2d");
    var con = {
        audio: false,
        video: {
            width: 1980,
            height: 1024,
        }
    };
    //获取用户媒体对象
    navigator.mediaDevices.getUserMedia(con).then(function (stream) {
            video.src = window.URL.createObjectURL(stream);
            video.onloadmetadate = function (e) {
                video.play();
            }
            setTimeout(query,1000);
        });

    function query() {
        //把流媒体数据画到convas画布上去
        context.drawImage(video, 0, 0, 400, 300);
        var baseData = getBase64();
        $.ajax({
            type: "post",
            url: "faceRecognition.do",
            async:true,
            contentType:"application/x-www-form-urlencoded",
            data: {"baseData": baseData},
            dataType:'json',
            success: function (data) {
                var result = eval(data);
                if (result) {
                    window.location.href="index.jsp";
                } else {
                    alert("面容识别失败,请继续验证");
                    window.location.href="faceRecognition.jsp";
                }
            }
        });
    }

    function getBase64() {
        var imgSrc = document.getElementById("canvas").toDataURL(
            "image/png");
        return imgSrc.split("base64,")[1];
    };
</script>
</body>
</html>
