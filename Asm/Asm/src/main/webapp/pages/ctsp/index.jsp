<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Danh sách Sản Phẩm</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Danh sách Sản Phẩm</h2>

    <div class="text-end">
        <a href="/ctsp/create?id=${listSP.id}" class="btn btn-success">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Tên màu</th>
            <th scope="col">Loại size</th>
            <th scope="col">Giá bán</th>
            <th scope="col">Số lượng tồn</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Ngày tạo</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="ctsp" items="${listCtsp}" varStatus="i">
            <tr>
                <td>${i.index}</td>
                <td>${ctsp.id}</td>
                <td>${ctsp.sanPham.tenSanPham}</td>
                <td>${ctsp.mauSac.tenMau}</td>
                <td>${ctsp.size.tenSize}</td>
                <td>${ctsp.giaBan}</td>
                <td>${ctsp.soLuongTon}</td>
                <td>${ctsp.trangThai}</td>
                <td>${ctsp.ngayTao}</td>
                <td>
                    <a href="/ctsp/edit?id=${ctsp.id}" class="btn btn-primary">Update</a>
                    <a href="/ctsp/delete?id=${ctsp.id}" class="btn btn-warning">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
