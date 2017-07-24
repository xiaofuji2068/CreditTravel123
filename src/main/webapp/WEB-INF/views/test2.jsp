<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/6/23
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test return parameter</title>
</head>
<body>
<a href="">test return parameter</a>
后台返回的数据是：${data}

<br>
<label for="data">输入数据</label>
<input type="text" id="data" name="data" onchange="changeUrl(this)"><br>
<a id="test3" href="/href3/data=">测试页面三</a>
<script>
    function changeUrl(obj) {
        document.getElementById("test3").href = "/test/href3/data="+obj.value;
    }
</script>
get方法：<br>
<form id="form1" action="/test/href4" method="get">
   begin: <input type="text" name="t1"><br>
   end: <input type="text" name="t2"><br>
    <input type="submit" name="提交">
</form>
post方法：<br>
<form id="form2" action="/test/href4" method="post">
    begin: <input type="text" name="t1"><br>
    end: <input type="text" name="t2"><br>
    <input type="submit" name="提交">
</form>

</body>
</html>
