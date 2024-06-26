﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.controller.dto.HistoriesRes" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
            <select id="semesterName" class="form-select form-select-lg" style="margin-right: 2px" onchange="changeSemester(this)">
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
    <div>
        <strong>'-'로 표시된 history는 당시 데이터가 아직 기록되지 않았음을 의미합니다.</strong><br/>
        - 1주차 백준 현황은 7.3 부터 기록된 점 양해 부탁드립니다 :)<br/>
    </div>
    <%
        int max_week = 0;
        List<HistoriesRes> historyResList = (List<HistoriesRes>) request.getAttribute("historyResList");

        Map<String, Map<Integer, String>> studentMap_baekjoon = new HashMap<>(); // 이름:[주차, OX] // 백준
        Map<String, Map<Integer, String>> studentMap_tistory = new HashMap<>(); // 이름:[주차, OX] // 티스토리
        Map<Integer, Map<String, String>> weekMap = new HashMap<>(); // 몇 주차 : [시작 날짜, 끝 날짜]

        if (historyResList != null && !historyResList.isEmpty()) {
            for (HistoriesRes history : historyResList) {
                String studentName = history.getStudentName();
                int weekId = history.getWeekNum();
                LocalDate startDate = history.getStartDate();
                LocalDate endDate = history.getEndDate();
                int solved = history.getSolved();
                int written = history.getWritten();

                weekMap.put(weekId, new HashMap<>(Map.of("startDate", startDate.format(DateTimeFormatter.ofPattern("MM-dd")), "endDate", endDate.toString())));
                while (weekMap.size() < (int)request.getAttribute("max_week")) {
                    int nextWeekId = weekMap.size() + 1;
                    weekMap.put(nextWeekId, new HashMap<>(Map.of(
                            "startDate", "",
                            "endDate", ""
                    )));
                }
                System.out.println("weekMap: " + weekMap);

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
            <% for (Map.Entry<Integer, Map<String, String>> entry: weekMap.entrySet()){%>
                <th style="font-size: 13px;">
                    ~<%=entry.getValue().get("startDate")%></th>
            <%}%>
        </tr>
        </thead>
        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_baekjoon.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= (int)request.getAttribute("max_week"); week++) { %>
            <td><%= entry.getValue().getOrDefault(week, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>

    <table id="attendList_list_tistory" class="table table-bordered">
        <thead>
        <tr>
            <th>티스토리</th>
            <% for (Map.Entry<Integer, Map<String, String>> entry: weekMap.entrySet()){%>
            <th style="font-size: 13px;">
                ~<%=entry.getValue().get("startDate")%></th>
            <%}%>
        </tr>
        </thead>

        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_tistory.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= (int)request.getAttribute("max_week"); week++) { %>
            <td><%= entry.getValue().getOrDefault(week, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
    <div>
        <strong>&lt;백준 크롤링 데이터&gt;</strong><br/>
        백준 데이터의 경우 지난 주 대비 이번주에 '새롭게 푼' 문제의 수가 갱신됩니다.<br/>
        이전에 맞췄으나 다시 푼 문제의 경우 적용되지 않습니다.<br/>
    </div>
    <div>
        <strong>&lt;티스토리 데이터&gt;</strong><br/>
        티스토리 데이터의 경우 이번주에 게시글을 한 개라도 올렸다면 O로 체크됩니다.
    </div>

</div>

</div>
