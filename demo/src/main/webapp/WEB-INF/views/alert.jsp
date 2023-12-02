<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="main.jsp"%>

<script>
    <%-- successMessage가 존재하면 해당 내용을 alert로 띄움 --%>
    <c:if test="${not empty successMessage}">
    alert("${successMessage}");
    </c:if>
    <c:if test="${not empty errorMessage}">
    alert("${errorMessage}");
    </c:if>
</script>