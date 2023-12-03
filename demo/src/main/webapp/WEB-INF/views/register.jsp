<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="nav.jsp"%>

<script>
        var message = "${message}";
        if (message){
            alert(message);
        }

        var datearr = [];
        function addStartDateField() {
            const startDateField = document.getElementById('startDate');
            const startDateValue = startDateField.value;

            console.log(startDateValue);

            if (startDateValue) {
                datearr.push(startDateValue);
                console.log(datearr);

                const weekList = document.getElementById('weekList');
                const listItem = document.createElement('li');
                listItem.textContent = startDateValue;
                const deleteButton = document.createElement('button');
                deleteButton.textContent = '삭제';
                deleteButton.onclick = function() {
                    const index = datearr.indexOf(startDateValue);
                    if (index !== -1) {
                        datearr.splice(index, 1);
                    }
                    weekList.removeChild(listItem);
                    console.log(datearr);
                };

                listItem.appendChild(deleteButton);
                weekList.appendChild(listItem);
            }
        }


</script>


<div class="container p-4 my-4 border">
    <h4 >학생 등록</h4>
    <div class="w-50 m-auto">
        <form action="/student-register"  method="post">
            <div class="form-group">
                <label>학생 이름</label>
                <input type="text" name="name" class="form-control" >
            </div>
            <div class="form-group">
                <label>백준 이름</label>
                <input type="text" name="baekjoonName" class="form-control" >
            </div>
            <div class="form-group">
                <label>티스토리 이름</label>
                <input type="text" name="tistoryName" class="form-control" >
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</div>

<div class="container p-4 my-4 border">
    <h4>스터디 등록</h4>
    <div class="w-50 m-auto">
        <form action="/study-register" method="post">
            <div class="form-group">
                <label>스터디 이름</label>
                <input type="text" name="name" class="form-control" >
            </div>
            <div class="form-group">
                <label>간단한 소개</label>
                <input type="text" name="description" class="form-control" >
            </div>
            <div class="form-group">
                <label>주차 추가</label>
                <div style="display: flex">
                <div id="startDateFields" style="margin-right:15px">
                    <input type="date" name="startDate" id="startDate" value="" max="9999-12-31">
                </div>
                <button type="button" onclick="addStartDateField()">시작일 추가</button>
                <ul id="weekList"></ul>
                </div>

            </div>

            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</div>