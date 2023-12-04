<%@include file="nav.jsp"%>

<div class="container">
<h2 class="card-header"> 출 석 부 </h2>
<div id = "attendList" class="card-body">

    <div class="d-flex justify-content-between">
        출 석 부 목 록
        <form action="" method="post" class="form-inline; d-flex justify-content-around" > <!--여기에서 select한 걸로 post 보낼 거니까-->
            <select class="form-select form-select-lg" style="margin-right: 2px">
                    <% for (int i=1; i<= Integer.parseInt(String.valueOf(request.getAttribute("totalSemester"))); i++) {%>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                <option>코알라 1기</option>
                <option>코알라 2기</option>
            </select>
            <select class="form-select form-select-lg" style="margin-right: 2px">
                <%--                    <% for (int i=1; i<= Integer.parseInt(String.valueOf(request.getAttribute("totalSemester"))); i++) {%>--%>
                <%--                        <option value="<%= i %>"><%= i %></option>--%>
                <%--                        <% } %>--%>
                <option>스터디1</option>
                <option>스터디2</option>
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