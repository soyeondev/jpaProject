<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 2020-05-18
  Time: 오후 2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link  type="text/css"  href="/css/bootstrap.min.css" rel="stylesheet">
    <link  type="text/css"  href="/css/login.css" rel="stylesheet">
</head>
<body class="text-center">
    <form class="form-signin" name="loginForm" action="/login" method="POST">
        <h1 class="h3 mb-3 font-weight-normal">로그인</h1>
        <label for="loginId" class="sr-only">ID</label>
        <input type="text" id="loginId" class="form-control" name="loginId">
        <label for="loginPwd" class="sr-only">Password</label>
        <input type="password" id="loginPwd" class="form-control" name="loginPwd">
        <input type="submit" class="btn btn-lg btn-primary btn-block" id="login" value="로그인">
    </form>
</body>
</html>