<%--
  Created by IntelliJ IDEA.
  User: yinchenhao
  Date: 2018/5/4
  Time: 下午2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function(){
            $("input[name='username']").blur(function(){
                //定义的是请求的一些参数
                var url = "${pageContext.request.contextPath}/userCheckServlet";
                var params = {"username":$(this).val()};

                $.post(url , params , function (data) {
                    $("#showUserSpan").addClass("label");
                    if (data.flag){
                        $("#showUserSpan").addClass("label-success");
                        $("#showUserSpan").removeClass("label-danger");
                        $("#registerButton").removeProp("disabled");

                    }else{
                        $("#showUserSpan").addClass("label-danger");
                        $("#showUserSpan").removeClass("label-success");
                        $("#registerButton").prop("disabled" , "disabled");

                    }
                    $("#showUserSpan").text(data.message);
                },"json");
            });
        });
    </script>
</head>
<body>
    <div class="col-sm-6">
        <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名"/>
    </div>
    <div class="col-sm-4">
        <span id="showUserSpan"></span>
    </div>
    <%--
    <span id="showUserSpan" class="label label-success">用户名可用</span>
    <span id="showUserSpan  class="label label-danger">用户名不可用</span>
    --%>
    <div class="form-group">
        <div class="col -sm-offset-2">
            <input  type="submit" id="registerButton" disabled="disabled" width="100"
            value="注册" border="0" class="form-control"/>

        </div>
    </div>
</body>
</html>
