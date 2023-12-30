<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="nav.jsp"%>


<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
    }
    .error-message {
        font-size: 24px;
        color: red;
    }
</style>

<div>
    <h1>앗 무언가 오류가 생겼어요!</h1>

    <div class="error-message">
        <p>다시 시도하시길 바랍니다..</p>
    </div>
</div>