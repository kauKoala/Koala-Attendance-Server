<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>출 석 부</title>

    <link rel="stylesheet" type="text/css" href="resources/css/list.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <script>

    </script>

</head>
<body>
<div id = "attendList">
    <div id = "attendList_title">
        출 석 부 목 록
    </div>
    <div id = "attendList_header">
        <div id="leftHeader">
            <form action="" method="post"> <!--여기에서 select한 걸로 post 보낼 거니까-->
                <select name="searchKey" class="selectField">
                    <% for (int i=1; i<= Integer.parseInt(String.valueOf(request.getAttribute("totalSemester"))); i++) {%>
                    <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
                <input type="button" value="검 색" class="btn2" onclick=""/>
            </form>
        </div>
    </div>
    <div id="attendList_list">
        <div>

        </div>

    </div>
</div>
</body>
</html>