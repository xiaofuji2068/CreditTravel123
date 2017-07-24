<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/7/10
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="login" action="user/TestSaveEntity" method="post">
    <input type="text" name="mobile"/><br>
    <input type="text" name="code"/><br>
    <input type="submit" value="submit"/>
</form>

<form id="count" action="user/TestQueryCode" method="post">
    <input type="text" name="mobile"/><br>
    <input type="submit" value="查询信息条数"/>
</form>

查询用户信息：<br>
<form id="count" action="user/UserAction_selectUser" method="post">
    <input type="text" name="mobile"/><br>
    <input type="submit" value="根据手机号查询用户信息"/>
</form>

获取验证码：<br>
<form id="count" action="user/UserAction/sms" method="post">
    <input type="text" name="mobile"/><br>
    <input type="text" name="code"/><br>
    <input type="submit" value="获取验证码"/>
</form>

注册：<br>
<form id="count" action="user/register" method="post">
    手机号：<input type="text" name="mobile"/><br>
    密码：<input type="text" name="password"/><br>
    验证码：<input type="text" name="code"/><br>
    <input type="submit" value="注册"/>
</form>

重置密码发送验证码：<br>
<form id="count" action="user/ChangPassword_sms" method="post">
    <input type="text" name="mobile"/><br>
    <input type="submit" value="获取验证码"/>
</form>

重置密码：<br>
<form id="count" action="user/resetPassword" method="post">
    手机号：<input type="text" name="mobile"/><br>
    密码：<input type="text" name="password"/><br>
    验证码：<input type="text" name="code"/><br>
    <input type="submit" value="注册"/>
</form>

添加昵称：<br>
<form id="count" action="user/newNickName" method="post">
    昵称：<input type="text" name="nickName"/><br>
    <input type="submit" value="提交昵称"/>
</form>

查询商户信息：<br>
<form id="count" action="merchant/queryMerchant" method="post">
    <input type="text" name="merName"/><br>
    <input type="text" name="code"/><br>
    <input type="submit" value="查询商户信息"/>
</form>

</body>
</html>
