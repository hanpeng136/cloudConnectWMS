<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="source/js/jquery.min.js"></script>
</head>
<body>
<br/><br/><br/>
用户姓名：<input type="text" id="username" />
用户密码：<input type="text" id="password" />
<input type="button" value="推送用户信息" id="pushUser" />




<script type="text/javascript">
    $("#pushUser").click(function(){
        var data = {
            username : $("#username").val(),
            password : $("#password").val(),
        };
        ajaxDo("userPush.do",data);
    });

    function ajaxDo(url,data){
        $.ajax({
            url:url ,
            type: "post",
            dataType: "json",
            data: data,
            success:function(result){
                if(result.success){
                    var obj = JSON.stringify(result.data);
                    alert(obj);
                }else{
                    alert(result.msg);
                }
            }
        });
    }

</script>

</body>
</html>
