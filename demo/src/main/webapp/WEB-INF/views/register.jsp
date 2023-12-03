<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="main.jsp"%>

<script>
    function popup() {
        var message = "${message}";
        alert(message);
    }
</script>

<div class="container p-4 my-4 border">
    <h4 >학생 등록</h4>
    <div class="w-50 m-auto">
        <form action="/register"  method="post" onsubmit="popup()">
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
        <form action="" method="post" onsubmit="popup()">
            <div class="form-group">
                <label>스터디 이름</label>
                <input type="text" name="study_name" class="form-control" >
            </div>
            <div class="form-group">
                <label>간단한 소개</label>
                <input type="text" name="description" class="form-control" >
            </div>
                <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</div>