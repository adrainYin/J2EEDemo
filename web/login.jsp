<%--
  Created by IntelliJ IDEA.
  User: yinchenhao
  Date: 2018/4/23
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆验证界面</title>
</head>
<body>
<% //pageContext.setAttribute("name" , "显示pageContext的demo成功！！！");
   session.setAttribute("name" , "session");
   request.setAttribute("name" , "request");
   application.setAttribute("name" , "application");%>
<%request.getParameterValues()%>
<%response.setHeader();%>
    <!--在这里定义了一个el表达式，可以直接获取键为name的内容
    作用域范围：依次从page,request,session,application获得数据
    若均没有此数据则返回null-->
<p>这是一个简单的测试demo = ${name}</p>
<form action="../userServlet" method="post">
    用户名:<input type="text" id="username" name="username" /><br/>
    密码：<input type="password" id="password" name="password" /><br/>
    请输入验证码：<input type="text" name="checkcode" id="checkcode" />&nbsp;<br/>
    <input type="submit" id="submit" name="submit" value="提交"><br/>

    <div class="col-sm-5">
        <img id="imgId" onclick="changeImg()" style="cursor:pointer;"
             src="${pageContext.request.contextPath}/verifyCodeServlet" />&nbsp;&nbsp;
        <a href="javascript:void(0)" onclick="changeImg()">换一张</a>
        <script type="text/javascript">
            function changeImg() {
                $("#imgId").attr("src","${pageContext.request.contextPath}/verifyCodeServlet?t="+new Date());
            }
        </script>


    </div>
</form>
</body>
</html>
