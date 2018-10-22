<%--
  Created by IntelliJ IDEA.
  User: zhennan
  Date: 2018/7/22
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${!empty serverhost}">
        <c:set value="${serverhost}" var="serverhost"/>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${!empty csshost}">
        <c:set value="${csshost}" var="fcss"/>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${!empty jshost}">
        <c:set value="${jshost}" var="fjs"/>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<script src="<c:url value='${fjs}/assets/js/jquery.min.js'/>"></script>
<script src="<c:url value='${fjs}/js/ieCommon.js'/>"></script>




