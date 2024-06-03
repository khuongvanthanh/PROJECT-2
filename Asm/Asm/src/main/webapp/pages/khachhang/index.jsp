<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Danh sách Khách hàng</title>
</head>
<body>
<h2 class="text-center">Danh sách Khách hàng</h2>

<div class="container">
    <div class="d-flex justify-content-end mb-3">
        <a href="/khachhang/create" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Địa chỉ</th>
            <th>Sdt</th>
            <th>Trạng thái</th>
            <th>Ngày Tạo</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="kh" items="${listKH}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${kh.hoTen}</td>
                <td>${kh.diaChi}</td>
                <td>${kh.sdt}</td>
                <td>${kh.trangThai}</td>
                <td>${kh.ngayTao}</td>
                <td class="mb-3">
                    <a href="/khachhang/edit?id=${kh.id}" class="btn btn-primary">Xem</a>
                    <a href="/khachhang/delete?id=${kh.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
