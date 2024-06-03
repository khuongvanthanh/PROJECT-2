<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Edit Size</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Chi tiết size</h2>
    <form action="/size/update?id=${listSize.id}" method="post">
        <p class="mb-3">Ngày sửa : ${listSize.ngaySua}</p>
        <div class="mb-3">
            <label class="form-label">Mã Sản Phẩm:</label>
            <input type="text" class="form-control" id="maSize" name="maSize" value="${listSize.maSize}">
        </div>
        <div class="mb-3">
            <label class="form-label">Tên Sản Phẩm:</label>
            <input type="text" class="form-control" id="tenSize" name="tenSize" value="${listSize.tenSize}">
        </div>
        <div class="mb-3">
            <label class="form-label">Trạng Thái:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="hoatDong" value="Inactive"
                       <c:if test="${listSize.trangThai == 'Inactive'}">checked</c:if>
                >
                <label class="form-check-label" for="hoatDong">Inactive</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" id="khongHoatDong" value="Activity"
                       <c:if test="${listSize.trangThai == 'Activity'}">checked</c:if>
                >
                <label class="form-check-label" for="khongHoatDong">Activity</label>
            </div>
        </div>


        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
