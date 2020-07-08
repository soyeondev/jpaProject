<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>공지사항</title>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/bootstrap.js"></script>
    <link type="text/css" href="/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="/css/dev.css" rel="stylesheet">
</head>
<body>
<!-- top (s) -->
<%@include file="/WEB-INF/jsp/top.jsp"%>
<!-- top (e) -->
<%@include file="/WEB-INF/jsp/left.jsp"%>

    <div class="container-fluid">
        <div>
            <main role="main" class="col-md-10 px-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h3">공지사항 > 수정</h1>
                </div>
                <form action="/notice" method="post">
                    <input type="hidden" name="id" value="${notice.id}">
                    <input type="hidden" name="enabledYn" value="${notice.enabledYn}">
                    <table class="table table-striped table-bordered">
                        <colgroup>
                            <col width="15%">
                            <col width="">
                            <col width="40%">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>공지번호</th>
                            <td>${notice.id}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>공지타입</th>
                            <td>
                                <select>
                                    <option value="1" <c:if test="${notice.showLevel == 1}">selected</c:if>>중요</option>
                                    <option value="2" <c:if test="${notice.showLevel == 2}">selected</c:if>>상단</option>
                                    <option value="3" <c:if test="${notice.showLevel == 3}">selected</c:if>>일반</option>
                                </select>
                            </td>
                            <td>상단(공지상단), 일반</td>
                        </tr>
                        <tr>
                            <th>메인노출</th>
                            <td>
                                <input type="radio" name="mainYn" value="Y" <c:if test="${notice.mainYn == 'Y'}">checked</c:if>>노출
                                <input type="radio" name="mainYn" value="N" <c:if test="${notice.mainYn == 'N'}">checked</c:if>>노출안함
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>앱노출</th>
                            <td>
                                <input type="radio" name="showYn" value="Y" <c:if test="${notice.showYn == 'Y'}">checked</c:if>>노출
                                <input type="radio" name="showYn" value="N" <c:if test="${notice.showYn == 'N'}">checked</c:if>>노출안함
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>등록자<span class="text-danger">(필수)</span></th>
                            <td>
                                <input type="text" class="form-control" name="adminName" value="${notice.adminName}">
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>등록일</th>
                            <td>
                                <fmt:parseDate value="${notice.insertTime}" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${date}"/>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>제목<span class="text-danger">(필수)</span></th>
                            <td>
                                <input type="text" class="form-control-xxl" name="subject" value="${notice.subject}">
                            </td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <textarea rows="10" cols="100">${notice.contents}</textarea>
                    <input type="submit" class="btn btn-sm btn-outline-dark " value="저장">
                </form>
            </main>
        </div>
    </div>
</body>
</html>
