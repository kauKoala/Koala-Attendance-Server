﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.controller.dto.HistoriesRes" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="nav.jsp"%>

<script>
    var message = "${message}";
    if (message){
        alert(message);
    }

    var selectedSemester = "${selectedSemester}";
    console.log(selectedSemester)

    var studyList = "${studyList}"
    console.log('study:',studyList)

    var historyResList = "${historyResList}";
    console.log('historyList:', historyResList);

    function changeSemester(select) {
        var selectedSemester = select.value;
        window.location.href = '/studylist?semesterId=' +selectedSemester;
    }

    function selectStudy() {
        var selectedOption = document.getElementById("studyName").value;
        document.getElementById("selectedValue").value = selectedOption;
    }


</script>

<div class="container">
<h2 class="card-header"> 출 석 부 </h2>
<div id = "attendList" class="card-body">

    <div class="d-flex justify-content-between">
        출 석 부 목 록
        <form action="/history" method="get" class="form-inline; d-flex justify-content-around" >
            <select id="semesterName" class="form-select form-select-lg" style="margin-right: 2px" onclick="changeSemester(this)">
                <c:choose>
                    <c:when test="${not empty selectedSemester}">
                        <option value="${selectedSemester.id}">${selectedSemester.year} ${selectedSemester.semesterType}</option>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="semesterName" items="${semesterList}">
                            <option value="${semesterName.id}">${semesterName.year} ${semesterName.semesterType}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
            <select id="studyName" name="studyId" class="form-select form-select-lg" style="margin-right: 2px">
                <c:forEach var="studyName" items="${studyList}">
                    <option value="${studyName.id}">${studyName.name}</option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="selectedValue" value="studyName">
            <input type="submit" value="선택" class="btn btn-outline-primary btn-sm" />
        </form>
    </div>

    <%
        int max_week = 0;
        List<HistoriesRes> historyResList = (List<HistoriesRes>) request.getAttribute("historyResList");

        Map<String, Map<Integer, String>> studentMap_baekjoon = new HashMap<>(); // 이름:[주차, OX] // 백준
        Map<String, Map<Integer, String>> studentMap_tistory = new HashMap<>(); // 이름:[주차, OX] // 티스토리

        if (historyResList != null && !historyResList.isEmpty()) {
            for (HistoriesRes history : historyResList) {
                String studentName = history.getStudentName();
                int weekId = history.getWeekNum();
                int solved = history.getSolved();
                int written = history.getWritten();

                if (!studentMap_baekjoon.containsKey(studentName)) {
                    studentMap_baekjoon.put(studentName, new HashMap<>());
                }
                studentMap_baekjoon.get(studentName).put(weekId, String.valueOf(solved));

                if (!studentMap_tistory.containsKey(studentName)) {
                    studentMap_tistory.put(studentName, new HashMap<>());
                }
                studentMap_tistory.get(studentName).put(weekId, written > 0  ? "○" : "✗");
            }
        }
    %>

    <table id="attendList_list_baekjoon" class="table table-bordered">
        <thead>
        <tr>
            <th>백준</th>
            <c:forEach var="week" begin="1" end="${max_week}">
                <th>${week}주차</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_baekjoon.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= (int)request.getAttribute("max_week"); week++) { %>
            <td><%= entry.getValue().getOrDefault(week-1, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>

    <table id="attendList_list_tistory" class="table table-bordered">
        <thead>
        <tr>
            <th>티스토리</th>
            <c:forEach var="week" begin="1" end="${max_week}">
                <th>${week}주차</th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_tistory.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= (int)request.getAttribute("max_week"); week++) { %>
            <td><%= entry.getValue().getOrDefault(week-1, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div>
        2024 1~3월 겨울방학 학기 기준<br/>
        - 2주차 티스토리 작성 현황은 1월 9일~16일 데이터가 반영되었습니다.<br/>
        - 3주차 백준 및 티스토리 현황은 1월 16일 ~ 1월 23일 데이터가 반영되었습니다.
    </div>
</div>

</div>
