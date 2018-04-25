<%--
  Created by IntelliJ IDEA.
  User: yinchenhao
  Date: 2018/4/24
  Time: 下午4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>转账界面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/accountServlet" method="post">
        <table border="1" width="400">
            <tr>
                <td>付款人:</td>
                <td><input type="text" name="payor" id="payor"/></td>
            </tr>
            <tr>
                <td>收款人:</td>
                <td><input type="text" name="payee" id="payee"/></td>
            </tr>
            <tr>
                <td>转账金额:</td>
                <td><input type="text" name="money" id="money"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" id="submit" value="提交"/>
                </td>
            </tr>
        </table>

    </form>

</body>
</html>
