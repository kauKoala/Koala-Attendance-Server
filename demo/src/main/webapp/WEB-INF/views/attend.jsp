<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="nav.jsp"%>

<script>
    var studentList = "${studentList}"
    console.log("studentList:", studentList)
    var studyList = "${studyList}"
    console.log("studyList:",studyList)

    function checkOnlyOne(element) {

        const checkboxes
            = document.getElementsByName("study");

        checkboxes.forEach((cb) => {
            cb.checked = false;
        })

        element.checked = true;
    }

    var studentarr = [];
    function addStudent(studentName) {
        if (studentarr.includes(studentName)) {
            // 선택 취소 시 배열에서 제거
            const index = studentarr.indexOf(studentName);
            if (index !== -1) {
                studentarr.splice(index, 1);
            }
        } else {
            // 선택 시 배열에 추가
            studentarr.push(studentName);
        }
        document.getElementById('studentListInput').value = studentarr;
    }
</script>

<div class="container p-4 my-4 border">
    <h4 >스터디 참가 조정</h4>
    <div class="w-50 m-auto">
        <form action="/attend-api" method="post">
            <div style="display: flex; justify-content: space-between;" >
            <fieldset>
                <legend>학생 선택</legend>
                <c:forEach var="student" items="${studentList}">
                    <input type="checkbox" name="student" value="${student.id}" onclick="addStudent('${student.id}')"> ${student.name}<br>
                </c:forEach>
            </fieldset>
            <fieldset>
                <legend>스터디 선택</legend>
                <c:forEach var="study" items="${studyList}">
                    <input type="checkbox" name="study" value="${study.id}" onclick='checkOnlyOne(this)'>${study.name}<br>
                </c:forEach>
            </fieldset>
            </div>
            <input type="hidden" name="studentList" id="studentListInput">
            <button type="submit" name="action" value="post" class="btn btn-success">추 가</button>
            <button type="submit" name="action" value="delete" class="btn btn-success">삭 제</button>
        </form>
    </div>
</div>
