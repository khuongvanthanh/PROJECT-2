<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Thêm khách hàng</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Thêm khách hàng</h2>
    <form action="/khachhang/store" method="post" >
        <div class="mb-3">
            <label class="form-label">Họ Tên:</label>
            <input type="text" class="form-control" id="hoTen" name="hoTen">
        </div>
        <div class="mb-3">
            <label class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" id="diaChi" name="diaChi">
        </div>
        <div class="mb-3">
            <label class="form-label">Sdt</label>
            <input type="text" class="form-control" id="sdt" name="sdt">
        </div>
        <div class="mb-3">
            <label class="form-label">Trạng Thái:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="hoatDong" value="Inactive">
                <label class="form-check-label" for="hoatDong">Inactive</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="khongHoatDong" value="Activity">
                <label class="form-check-label" for="khongHoatDong">Activity</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>
</body>
</html>
