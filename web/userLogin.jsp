<%--
  Created by IntelliJ IDEA.
  User: yinchenhao
  Date: 2018/5/8
  Time: 下午1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆选项</title>
</head>
<body>
     <form action="${pageContext.request.contextPath}/userLoginServlet" method="post">
        用户名：<input type="text" name="username" id="username" />&nbsp;&nbsp;<br/>
        密码： <<input type="password" name="password" id="password" />&nbsp;&nbsp;<br/>
         下次是否选择自动登陆:<input type="checkbox" name="autoLogin"/><br/>
         提交：<input type="submit" value="提交"/>
     </form>
</body>
</html>
