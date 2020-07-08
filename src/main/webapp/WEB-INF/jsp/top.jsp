<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 2020-05-18
  Time: 오후 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
.navbar-collapse{
    padding-left: 90px;
    font-size : 18px;
}
</style>

<nav class="navbar-expand fixed-top navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="" width="30" height="30" class="d-inline-block align-top" alt="">
        <span class="px-2">관리자</span>
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item action">
                <a class="nav-link">학회관리</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="/master/admin/list">마스터관리자</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/master/notice/list?mainYn=Y">공지사항</a>
            </li>
        </ul>
    </div>
    <div class="navbar-right">
        <span class="navbar-text px-2"> ${sessionScope.sessionKey.name}(${sessionScope.sessionKey.loginId}) </span>
        <button class="btn btn-outline-warning btn-sm"><a onclick="location.href='/logout';">로그아웃</a></button>
    </div>
</nav>
</html>
