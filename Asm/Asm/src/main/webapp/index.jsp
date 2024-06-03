<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<nav class="navbar navbar-expand " style="background-color: #ADD8E6;">
    <div class="container-fluid">
        <!-- Move the Account link to the left -->
        <a class="navbar-brand me-auto" href="#">Admin Dashboard</a>
        <div class="mx-auto">
        </div>

        <!-- Add margin between the Account link and the nav links -->
        <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item dropdown mb-3">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    Settings
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><a class="dropdown-item" href="#">Security</a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="#">Preferences</a></li>
                </ul>
            </li>
            <li class="nav-item mb-3">
                <a class="nav-link" href="#">Logout</a>
            </li>
        </ul>
    </div>
</nav>

<div class="d-flex">
    <div class="bg-light pt-3" style="width: 250px; min-height: calc(100vh - 66px);">
        <ul class="nav flex-column ">
            <li class="list-group-item dropdown text-center">
                <span class="dropdown-toggle" role="button" data-bs-toggle="dropdown"
                      aria-expanded="false">
                    Sản phẩm
                </span>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownProducts">
                    <li><a class="dropdown-item" href="/sanpham/index">Xem sản phẩm</a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="/danhMuc/index">Xem danh mục</a></li>
                    <li><a class="dropdown-item" href="/size/index">Xem size</a></li>
                    <li><a class="dropdown-item" href="/mausac/index">Xem màu</a></li>
                </ul>
            </li>

            <li class="list-group-item dropdown text-center">
                <span class="dropdown-toggle" role="button" data-bs-toggle="dropdown"
                      aria-expanded="false">
                    Khách hàng
                </span>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownProducts">
                    <li><a class="dropdown-item" href="/khachhang/index">Xem khách hàng</a></li>
                </ul>
            </li>

            <li class="list-group-item dropdown text-center">
                <span class="dropdown-toggle" role="button" data-bs-toggle="dropdown"
                      aria-expanded="false">
                    Hóa đơn
                </span>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownProducts">
                    <li><a class="dropdown-item" href="/hoadon/index">Xem hóa đơn</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="container">
        <div id="content">
            <c:choose>
                <c:when test="${not empty sessionScope.container}">
                    <jsp:include page="${sessionScope.container}"></jsp:include>
                </c:when>
                <c:otherwise>
                    <p>Không có nội dung để hiển thị</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

</body>
</html>
