<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Danh sách Danh Mục</title>
</head>
<body>
<h2 class="text-center">Danh sách Danh Mục</h2>

<div class="container">
    <div class="d-flex justify-content-end mb-3">
        <a href="/danhMuc/create" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã Danh Mục</th>
            <th>Tên Danh Mục</th>
            <th>Trạng Thái</th>
            <th>Ngày Tạo</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sp" items="${listDM}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${sp.maDanhMuc}</td>
                <td>${sp.tenDanhMuc}</td>
                <td>${sp.trangThai}</td>
                <td>${sp.ngayTao}</td>
                <td class="mb-3">
                    <a href="/danhMuc/edit?id=${sp.id}" class="btn btn-primary">Xem</a>
                    <a href="/danhMuc/delete?id=${sp.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
