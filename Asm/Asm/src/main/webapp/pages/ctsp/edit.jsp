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
    <h2 class="text-center">Sửa sản phẩm</h2>
    <form action="/ctsp/update?id=${Ctsp.id}" method="post">

        <div class="mb-3">
            <label class="form-label">Màu sắc :</label>
            <select class="form-select" aria-label="Default select example" name="mauSac">
                <c:forEach items="${mauSac}" var="ms">
                    <option value="${ms.id}"
                            <c:if test="${ms.id == Ctsp.mauSac.id} ">selected</c:if>>${ms.tenMau}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Size :</label>
            <select class="form-select" aria-label="Default select example" name="size">
                <c:forEach items="${size}" var="s">
                    <option value="${s.id}" <c:if test="${s.id == Ctsp.size.id} ">selected</c:if>>${s.tenSize}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá bán :</label>
            <input type="text" class="form-control" name="giaBan" value="${Ctsp.giaBan}">
        </div>

        <div class="mb-3">
            <label class="form-label">Số lượng tồn</label>
            <input type="text" class="form-control" name="soLuongTon" value="${Ctsp.soLuongTon}">
        </div>

        <div class="mb-3">
            <label class="form-label">Trạng Thái:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Inactive"
                       <c:if test="${Ctsp.trangThai == 'Inactive'}">checked</c:if>
                >
                <label class="form-check-label">Inactive</label>
            </div>

            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="trangThai" value="Activity"
                       <c:if test="${Ctsp.trangThai == 'Activity'}">checked</c:if>
                >
                <label class="form-check-label">Activity</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
