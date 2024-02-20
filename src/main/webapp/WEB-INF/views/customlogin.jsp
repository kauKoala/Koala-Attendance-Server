<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="nav.jsp"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<main class="flex-shrink-0">
    <section class="py-5">
        <div class="container px-5">
            <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <h2 class="text-center mb-5">admin 계정 로그인</h2>
                        <c:if test="${empty pageContext.request.userPrincipal}">
                        <form action="/customlogin" method="post">
                            <div class="form-floating mb-3">
                                <label for="identifier">ID</label>
                                <input class="form-control" id="identifier" name="username" type="text" placeholder="username 입력">
                            </div>
                            <div class="form-floating mb-3">
                                <label for="password">Password</label>
                                <input class="form-control" id="password" name="password" type="password" placeholder="password 입력">

                            </div>
                            <div class="d-grid"><button class="btn btn-primary" id="sign-in-button" type="submit">로그인</button></div>
                        </form>
                        </c:if>
                        <c:if test="${not empty pageContext.request.userPrincipal}">
                            <form action="/logout" method="post">
                                <button type="submit">로그아웃</button>
                            </form>
                        </c:if>
                    </div>
                </div>

            </div>
        </div>
    </section>
</main>
</body>
</html>
