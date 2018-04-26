<%@ page import="bean.Product" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: yinchenhao
  Date: 2018/4/26
  Time: 上午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品详情</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/productEditServlet" method="post">
        <input type="hidden" name="pid" value="${product.pid}"/><br/>
        pname:<input type="text" name="pname" id="pname" value="${product.pname}"/><br/>
        market_price:<input type="text" name="market_price" value="${product.market_price}"/><br/>
        shop_price:<input type="text" name="shop_price" value="${product.shop_price}"/><br/>
        pimage:<input type="text" name="pimage" value="${product.pimage}"/><br/>
        pdate:<input type="text" name="pdate" value="${product.pdate}"/><br/>
        is_hot:<input type="text" name="is_hot" value="${product.is_hot}"/><br/>
        pdesc:<input type="text" name="pdesc" value="${product.pdesc}"/><br/>
        pflag:<input type="text" name="pflag" value="${product.pflag}"/><br/>
        <input type="hidden" name="cid" value="${product.cid}"/><br/>
        提交:<input type="submit" value="提交"/>
    </form>
</body>
</html>
