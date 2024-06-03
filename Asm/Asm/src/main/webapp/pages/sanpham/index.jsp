<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<html>
<head>
    <title>Danh sách Sản Phẩm</title>
</head>
<body>

<h2 class="text-center">Danh sách Sản Phẩm</h2>

<div class="container">
    <div class="d-flex justify-content-end mb-3">
        <a href="/sanpham/create" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã Sản Phẩm</th>
            <th>Tên Sản Phẩm</th>
            <th>Trạng Thái</th>
            <th>Danh Mục</th>
            <th>Ngày Tạo</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sp" items="${listSP}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${sp.maSanPham}</td>
                <td>${sp.tenSanPham}</td>
                <td>${sp.trangThai}</td>
                <td>${sp.danhMuc.tenDanhMuc}</td>
                <td>${sp.ngayTao}</td>
                <td class="mb-3">
                    <a href="/ctsp/index?id=${sp.id}" class="btn btn-primary">Xem</a>
                    <a href="/sanpham/edit?id=${sp.id}" class="btn btn-primary">Update</a>
                    <a href="/sanpham/delete?id=${sp.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item ${page == 1 ? 'disabled' : ''}">
            <a class="page-link" href="/sanpham/index?page=${page - 1}&pageSize=${pageSize}">Previous</a>
        </li>
        <c:forEach begin="1" end="${endPages}" var="pageNum">
            <li class="page-item ${page == pageNum ? 'active' : ''}">
                <a class="page-link" href="/sanpham/index?page=${pageNum}&pageSize=${pageSize}">${pageNum}</a>
            </li>
        </c:forEach>
        <li class="page-item ${page == endPages ? 'disabled' : ''}">
            <a class="page-link" href="/sanpham/index?page=${page + 1}&pageSize=${pageSize}">Next</a>
        </li>
    </ul>
</nav>
</body>
</html>