<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/6/28
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"
            type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/sms.js"
            type="text/javascript"></script>
</head>
<body>
<form id="login" action="user/register" method="get">
    <input id="mobileId" type="text" name="mobile"/><br>
    <span id="jbPhoneTip">
    <input type="text" id="SmsCheckCode" name="code" /><br>
    </span>



    <input type="button" id="btnSendCode"
                 name="btnSendCode" value="免费获取验证码" onclick="sendMessage()" />
    <span id="SmsCheckCodeTip"></span>
    <input type="submit" value="注册"/>


</form>
</body>
</html>
