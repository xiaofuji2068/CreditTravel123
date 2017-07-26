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
        session:${session};<br>
        session:${session.attributeNames};

        <%
            session.getAttributeNames();
            out.print("测试session\r\n");
            out.write("测试session");
            while (session.getAttributeNames().hasMoreElements()){
                String name = session.getAttributeNames().nextElement();
                out.write("\r\n");
                out.write("write:" + session.getAttribute(name).toString());
                out.print("print:" + session.getAttribute(name));
                out.println("println:" + session.getAttribute(name));
            }
        %>

</body>
</html>
