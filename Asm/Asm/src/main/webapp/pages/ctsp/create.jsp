<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Form Sản Phẩm</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Thêm sản phẩm</h2>
    <form action="/ctsp/store?id=${idProduct}" method="post">

        <div class="mb-3">
            <label class="form-label">Màu sắc :</label>
            <select class="form-select" aria-label="Default select example" name="mauSac">
                <c:forEach items="${mauSac}" var="ms">
                    <option value="${ms.id}" >${ms.tenMau}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Size :</label>
            <select class="form-select" aria-label="Default select example" name="size">
                <c:forEach items="${size}" var="s">
                    <option value="${s.id}">${s.tenSize}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá bán :</label>
            <input type="text" class="form-control" name="giaBan">
        </div>

        <div class="mb-3">
            <label class="form-label">Số lượng tồn</label>
            <input type="text" class="form-control" name="soLuongTon">
        </div>

        <div class="mb-3">
            <label class="form-label">Trạng Thái:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Inactive">
                <label class="form-check-label">Inactive</label>
            </div>

            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Activity">
                <label class="form-check-label">Activity</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>
</body>
</html>
