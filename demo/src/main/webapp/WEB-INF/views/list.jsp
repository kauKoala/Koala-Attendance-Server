<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.controller.dto.HistoriesRes" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="nav.jsp"%>

<script>
    var semesterList = "${semesterList}"
    console.log(semesterList)

    var studyList = "${studyList}"
    console.log('study:',studyList)

    var historyResList = "${historyResList}";
    console.log('historyList:', historyResList);

    function changeSemester(select) {
        var selectedSemester = select.value;
        console.log('화면 재렌더링', encodeURIComponent(selectedSemester))
        window.location.href = '/studylist?semesterName=' + encodeURIComponent(selectedSemester);
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
        <form action="/history" method="post" class="form-inline; d-flex justify-content-around" > <!--여기에서 select한 걸로 post 보낼 거니까-->
            <select id="semesterName" class="form-select form-select-lg" style="margin-right: 2px" onclick="changeSemester(this)">
                <c:choose>
                    <c:when test="${not empty selectedSemester}">
                        <option value="${selectedSemester}">${selectedSemester}</option>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="semesterName" items="${semesterList}">
                            <option value="${semesterName}">${semesterName}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
            <select id = "studyName" class="form-select form-select-lg" style="margin-right: 2px">
                <c:forEach var="studyName" items="${studyList}">
                    <option value="${studyName.name}">${studyName.name}</option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="selectedValue" value="studyName">
            <input type="submit" value="선택" class="btn btn-outline-primary btn-sm" onclick=""/>
        </form>
    </div>

    <%
        List<HistoriesRes> historyResList = (List<HistoriesRes>) request.getAttribute("historyResList");

        Map<String, Map<Integer, String>> studentMap_baekjoon = new HashMap<>(); // 이름:[주차, OX] // 백준
        Map<String, Map<Integer, String>> studentMap_tistory = new HashMap<>(); // 이름:[주차, OX] // 티스토리

        // historyResList가 null이 아니고 비어있지 않을 경우에만 맵 생성 로직 수행
        if (historyResList != null && !historyResList.isEmpty()) {
            for (HistoriesRes history : historyResList) {
                String studentName = history.getStudentName();
                int weekId = history.getWeekId();
                boolean isSolved = history.isSolved();
                boolean isWritten = history.isWritten();

                if (!studentMap_baekjoon.containsKey(studentName)) {
                    studentMap_baekjoon.put(studentName, new HashMap<>());
                }
                studentMap_baekjoon.get(studentName).put(weekId, isSolved ? "○" : "✗");

                if (!studentMap_tistory.containsKey(studentName)) {
                    studentMap_tistory.put(studentName, new HashMap<>());
                }
                studentMap_tistory.get(studentName).put(weekId, isWritten ? "○" : "✗");
            }
        }
    %>

    <table id="attendList_list_baekjoon" class="table table-bordered">
        <thead>
        <tr>
            <th>학생</th>
            <th>1주차</th>
            <th>2주차</th>
            <th>3주차</th>
            <th>4주차</th>
            <th>5주차</th>
            <th>6주차</th>
            <th>7주차</th>
            <th>8주차</th>

            <!-- 일단 하드코딩 -->
        </tr>
        </thead>
        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_baekjoon.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= 8; week++) { %>
            <td><%= entry.getValue().getOrDefault(week, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>

    <table id="attendList_list_tistory" class="table table-bordered">
        <thead>
        <tr>
            <th>학생</th>
            <th>1주차</th>
            <th>2주차</th>
            <th>3주차</th>
            <th>4주차</th>
            <th>5주차</th>
            <th>6주차</th>
            <th>7주차</th>
            <th>8주차</th>
            <!-- 일단 하드코딩 -->
        </tr>
        </thead>
        <tbody>
        <% for (Map.Entry<String, Map<Integer, String>> entry : studentMap_tistory.entrySet()) { %>
        <tr>
            <td><%= entry.getKey() %></td>
            <% for (int week = 1; week <= 8; week++) { %>
            <td><%= entry.getValue().getOrDefault(week, "-") %></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</div>