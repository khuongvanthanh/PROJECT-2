<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Danh sách Size</title>
</head>
<body>
<h2 class="text-center">Danh sách Size</h2>

<div class="container">
    <div class="d-flex justify-content-end mb-3">
        <a href="/size/create" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã Size</th>
            <th>Tên Size</th>
            <th>Trạng Thái</th>
            <th>Ngày Tạo</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="s" items="${listSize}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${s.maSize}</td>
                <td>${s.tenSize}</td>
                <td>${s.trangThai}</td>
                <td>${s.ngayTao}</td>
                <td class="mb-3">
                    <a href="/size/edit?id=${s.id}" class="btn btn-primary">Xem</a>
                    <a href="/size/delete?id=${s.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
