<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="nav.jsp"%>


<div class="container p-4 my-4 border">
    <h4 >스터디 참가 조정</h4>
    <div class="w-50 m-auto">
        <form action="/"  method="post" >
            <div style="display: flex; justify-content: space-between;" >
                <fieldset>
                <legend>학생 선택</legend>
                <c:forEach var="person" items="${peopleListFromDB}">
                    <input type="checkbox" name="person" value="${person.name}">${person.name}<br>
                </c:forEach>
                <input type="checkbox" name="person" value="John"> 전영서<br>
                <input type="checkbox" name="person" value="Emily"> 김두현<br>
                <input type="checkbox" name="person" value="Mike"> 박상신<br>
            </fieldset>
            <fieldset>
                <legend>스터디 선택</legend>
                <c:forEach var="study" items="${studyListFromDB}">
                    <input type="checkbox" name="study" value="${study.name}">${study.name}<br>
                </c:forEach>
                <input type="checkbox" name="study" value="Study 1"> 기초알고리즘 스터디<br>
                <input type="checkbox" name="study" value="Study 2"> 코딩테스트 스터디<br>
                <input type="checkbox" name="study" value="Study 3"> 모의테스트 스터디<br>
            </fieldset>
            </div>
            <button type="submit" name="action" value="post" class="btn btn-success">추 가</button>
            <button type="submit" name="action" value="delete" class="btn btn-success">삭 제</button>
        </form>
    </div>
</div>