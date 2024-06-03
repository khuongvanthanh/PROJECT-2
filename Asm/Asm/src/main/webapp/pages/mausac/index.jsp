<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<html>
<head>
    <title>Danh sách Màu sắc</title>
</head>
<body>

<h2 class="text-center">Danh sách Màu sắc</h2>

<div class="container">
    <div class="d-flex justify-content-end mb-3">
        <a href="/mausac/create" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã màu</th>
            <th>Tên màu</th>
            <th>Trạng Thái</th>
            <th>Ngày Tạo</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ms" items="${listMS}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${ms.maMau}</td>
                <td>${ms.tenMau}</td>
                <td>${ms.trangThai}</td>
                <td>${ms.ngayTao}</td>
                <td class="mb-3">
                    <a href="/mausac/edit?id=${ms.id}" class="btn btn-primary">Xem</a>
                    <a href="/mausac/delete?id=${ms.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
