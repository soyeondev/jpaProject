<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>공지사항</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="/css/dev.css" rel="stylesheet">
</head>
<body>
<!-- top (s) -->
<%@include file="/WEB-INF/jsp/top.jsp"%>
<!-- top (e) -->
<%@include file="/WEB-INF/jsp/left.jsp"%>

<div class="container-fluid">
    <div class="row">
        <main role="main" class="col-md-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h3">공지사항</h1>
            </div>
            <form action="/notice/list">
                <table class="table table-dark">
                    <colgroup>
                        <col width="10%">
                        <col width="10%">
                        <col width="">
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>제목</td>
                            <td><input type="text" id="searchVal" name="searchVal" class="form-control-sm" value=""></td>
                            <td><input type="submit" class="btn btn-sm btn-outline-warning" value="검색"></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <table class="table table-hover">
                <colgroup>
                    <col width="5%">
                    <col width="5%">
                    <col width="10%">
                    <col width="10%">
                    <col width="30%">
                    <col width="10%">
                </colgroup>
                <thead class="thead-light">
                    <th></th>
                    <th>#</th>
                    <th>타입</th>
                    <th>앱 노출</th>
                    <th>제목</th>
                    <th>등록자</th>
                    <th>등록일</th>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${noticeList.totalElements > 0}">
                            <c:forEach var="item" items="${noticeList.content}" varStatus="i">
                                <tr>
                                    <td><input type="checkbox" name="checkbox" value="${item.id}"></td>
                                    <td onclick="document.location.href='/notice/${item.id}'">${(noticeList.size * noticeList.number) + i.index+1}</td>
                                    <td onclick="document.location.href='/notice/${item.id}'">
                                        <c:if test="${item.showLevel == 1}">중요</c:if>
                                        <c:if test="${item.showLevel == 2}">상단</c:if>
                                        <c:if test="${item.showLevel == 3}">일반</c:if>
                                    </td>
                                    <td onclick="document.location.href='/notice/${item.id}'">${item.showYn}</td>
                                    <td onclick="document.location.href='/notice/${item.id}'">${item.subject}</td>
                                    <td onclick="document.location.href='/notice/${item.id}'">${item.adminName}</td>
                                    <td onclick="document.location.href='/notice/${item.id}'">
                                        <!-- parseDate 에서 format에 맞춰 String 형으로 변환 -->
                                        <fmt:parseDate value="${item.insertTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        <!-- formatDate 에서 format에 맞춰 Date 형으로 변환 -->
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${date}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7">데이터가 존재하지 않습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            <c:set var="totalPages" value="${noticeList.totalPages}"/>
            <c:set var="size" value="${noticeList.size}"/>
            <c:set var="number" value="${noticeList.number}"/>
            <c:set var="first" value="${noticeList.first}"/>
            <c:set var="last" value="${noticeList.last}"/>
            <c:set var="url" value="/notice/list"/>
            <c:set var="searchVal" value="${searchVal}"/>
            <c:set var="params" value="param"/>
            <c:set var="searchParams" value="none"/>
            <%@include file="/WEB-INF/jsp/pagination.jsp"%>
        </main>
    </div>
</div>
</body>
</html>
