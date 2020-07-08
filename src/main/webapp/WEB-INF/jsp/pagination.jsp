<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--페이지 그룹이 5일 때--%>
<fmt:parseNumber var="page" integerOnly="true" value="${(number/5)}"/>

<%--한번에 보여주는 페이지 그룹 숫자--%>
<c:set var="pagePer" value="5"/>

<%--그룹 시작 숫자--%>
<c:set var="startPage" value="${page*pagePer+1}"/>

<%--그룹 마지막 숫자--%>
<c:set var="endPage" value="${startPage+pagePer -1}"/>

<%--마지막 그룹일때
 맨 마지막 숫자가 토탈 숫자보다 더 크면 마지막 숫자를 토탈 숫자에 넣는다.
--%>
<c:if test="${endPage > totalPages}">
    <c:set var="endPage" value="${totalPages}"/>
</c:if>

<ul class="pagination justify-content-center">
    <li class="page-item"><a class="page-link" href="${url}?page=0&size=${size}&searchVal=${searchVal}&${params}=${searchParams}&${params1}=${searchParams1}"><<</a></li>
        <c:if test="${!first}">
            <li class="page-item"><a class="page-link" href="${url}?page=${number-1}&size=${size}&searchVal=${searchVal}&${params}=${searchParams}&${params1}=${searchParams1}"><</a></li>
        </c:if>
    <c:forEach begin="${startPage}" end="${endPage}" var="i" step="1">
        <li class="page-item <c:if test="${number == i-1}">active </c:if>"><a class="page-link" href="${url}?page=${i-1}&size=${size}&searchVal=${searchVal}&${params}=${searchParams}&${params1}=${searchParams1}">${i}</a></li>
    </c:forEach>
    <c:if test="${!last}">
        <li class="page-item"><a class="page-link" href="${url}?page=${number-1}&size=${size}&searchVal=${searchVal}&${params}=${searchParams}&${params1}=${searchParams1}">></a></li>
    </c:if>
    <li class="page-item"><a class="page-link" href="${url}?page=${number-1}&size=${size}&searchVal=${searchVal}&${params}=${searchParams}&${params1}=${searchParams1}">>></a></li>
</ul>