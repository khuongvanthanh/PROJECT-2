<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Form Sản Phẩm</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Thêm sản phẩm</h2>
    <form action="/sanpham/store" method="post" >
        <div class="mb-3">
            <label class="form-label">Mã Sản Phẩm:</label>
            <input type="text" class="form-control" id="maSanPham" name="maSanPham">
        </div>
        <div class="mb-3">
            <label class="form-label">Tên Sản Phẩm:</label>
            <input type="text" class="form-control" id="tenSanPham" name="tenSanPham">
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
        <div class="mb-3">
            <label class="form-label">Danh Mục:</label>
            <select class="form-select" aria-label="Default select example" name="danhMuc">
                <c:forEach items="${listDM}" var="x">
                    <option value="${x.id}">${x.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>
</body>
</html>
