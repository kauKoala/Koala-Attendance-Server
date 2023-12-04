<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.demo.domain.Semester" %>
<%@ page import="java.util.List" %>
<%@include file="nav.jsp"%>

<script>
    var semesterList = "${semesterList}"
    console.log(semesterList)

    var studyList = "${studyList}"
    console.log('study:',studyList)

    function changeSemester(select) {
        var selectedSemester = select.value;
        console.log('화면 재렌더링', encodeURIComponent(selectedSemester))
        window.location.href = '/studylist?semesterName=' + encodeURIComponent(selectedSemester);
    }
</script>

<div class="container">
<h2 class="card-header"> 출 석 부 </h2>
<div id = "attendList" class="card-body">

    <div class="d-flex justify-content-between">
        출 석 부 목 록
        <form action="" method="post" class="form-inline; d-flex justify-content-around" > <!--여기에서 select한 걸로 post 보낼 거니까-->
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
            <input type="submit" value="선택" class="btn btn-outline-primary btn-sm" onclick=""/>
        </form>
    </div>
    <table id="attendList_list" class="table table-bordered">
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
            </tr>
        </thead>
        <tbody>

        </tbody>

    </table>
</div>
</div>