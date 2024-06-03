<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
<script src="path/to/your/javascript/file.js"></script>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-7">
            <h2>Danh sách hoá đơn</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <td>STT</td>
                    <td>ID</td>
                    <td>Ten khach hang</td>
                    <td>Ngay tao</td>
                    <td>Trang Thai</td>
                    <td>Chuc nang</td>
                </tr>
                </thead>
                <tbody>
                <!-- Dùng JSTL để lặp qua danh sách hoá đơn -->
                <c:forEach items="${listHoaDon}" var="hd" varStatus="i">
                    <tr>
                        <td>${i.index}</td>
                        <td>${hd.id}</td>
                        <td>${hd.khachHang.hoTen}</td>
                        <td>${hd.ngayTao}</td>
                        <td>${hd.trangThai}</td>
                        <td>
                            <a href="/hoadon/detail?id=${hd.id}" class="btn btn-primary">Chon</a>
                            <a href="/hoadon/delete?id=${hd.id}" class="btn btn-danger">xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h2>Danh sách hoá đơn chi tiết</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>ID</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá bán</th>
                    <th>Tổng tiền</th>
                </tr>
                </thead>
                <tbody>
                <!-- Dùng JSTL để lặp qua danh sách hóa đơn chi tiết -->

                <c:forEach var="hdct" items="${listHDCT}" varStatus="i">
                    <tr>
                        <td>${i.index}</td>
                        <td>${hdct.id}</td>
                        <td>${hdct.ctsp.sanPham.tenSanPham}</td>
                        <td>${hdct.soLuongMua}</td>
                        <td>${hdct.giaBan}</td>
                        <td>${hdct.tongTien}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-5">
            <!-- Form tạo hóa đơn -->
            <h2>Tạo hoá đơn</h2>
            <form action="/hoadon/add" method="post">
                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control form-control-sm" id="sdt" name="sdt">
                    <input type="hidden" value="${phone}">
                </div>
                <a href="#" id="timKiemLink" class="btn btn-primary mb-3">Tìm kiếm</a>

                <script>
                    // Lắng nghe sự kiện khi người dùng nhấp vào liên kết "Tìm kiếm"
                    document.getElementById('timKiemLink').addEventListener('click', function (event) {
                        // Ngăn chặn hành vi mặc định của liên kết
                        event.preventDefault();

                        // Lấy giá trị của trường input có id là "sdt"
                        var sdtValue = document.getElementById('sdt').value;

                        // Tạo URL mới với giá trị của sdt được thêm vào
                        var newURL = '/hoadon/detail?id=${hoaDon.id}&sdt=' + encodeURIComponent(sdtValue);

                        // Chuyển hướng sang URL mới
                        window.location.href = newURL;
                    });
                </script>

                <div class="mb-3">
                    <label class="form-label">Tên Khách hàng</label>
                    <input type="text" class="form-control form-control-sm" id="hoten" name="hoten"
                           value="${tenKhachHang}" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">ID Hoá đơn</label>
                    <input type="text" class="form-control form-control-sm" name="idHoaDon" readonly
                           value="${hoaDon.id}">
                </div>

                <div class="mb-3">
                    <label class="form-label">Tổng tiền</label>
                    <input type="text" class="form-control form-control-sm" name="tongTienCuoi" readonly
                           value="${tongTienCuoi}">
                </div>
                <button class="btn btn-primary me-2">Tạo hoá đơn</button>
                <a href="/hoadon/thanhtoan?id=${hoaDon.id}&sdt=${phone}" class="btn btn-primary">Thanh toán</a>
            </form>
        </div>
    </div>

    <%---------------------------------------------------------------------------------------------------------------%>

    <div>
        <h2>Danh sách chi tiết sản phẩm</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>STT</th>
                <th>ID CTSP</th>
                <th>Tên sản phẩm</th>
                <th>Màu sắc</th>
                <th>Size</th>
                <th>Giá bán</th>
                <th>Số lượng tồn</th>
                <th>Trạng thái</th>
                <th>Chức năng</th>
            </tr>
            </thead>
            <tbody>


            <!-- Hiển thị giá tiền và tổng tiền đã được định dạng -->
            <c:forEach items="${listCtsp}" var="ctsp" varStatus="i">
                <tr>
                    <td>${i.index}</td>
                    <td>${ctsp.id}</td>
                    <td>${ctsp.sanPham.tenSanPham}</td>
                    <td>${ctsp.mauSac.tenMau}</td>
                    <td>${ctsp.size.tenSize}</td>
                    <td>${ctsp.giaBan}</td>
                    <td>${ctsp.soLuongTon}</td>
                    <td>${ctsp.trangThai}</td>
                    <td>
                        <!-- Sử dụng onclick để gọi hàm addHdct() khi người dùng nhấn nút "Chọn mua" -->
                        <button class="btn btn-primary" onclick="addHdct(${hoaDon.id}, ${ctsp.id})">Chọn mua</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
<script>
    function addHdct(idHoaDon, idsp) {
        // Hiển thị cửa sổ xác nhận và nhập số lượng
        var soLuongMua = prompt("Nhập số lượng mua:");

        // Kiểm tra nếu người dùng không nhập hoặc nhập số lượng không hợp lệ
        if (soLuongMua === null || isNaN(soLuongMua) || parseInt(soLuongMua) <= 0) {
            alert("Vui lòng nhập số lượng hợp lệ.");
            return;
        }

        // Hiển thị cửa sổ xác nhận cuối cùng trước khi thêm vào giỏ hàng
        var confirmMessage = "Bạn có chắc chắn muốn thêm " + soLuongMua + " sản phẩm vào giỏ hàng?";
        if (confirm(confirmMessage)) {
            // Nếu người dùng đồng ý, chuyển số lượng và id sản phẩm vào URL
            var url = "/hoadon/addHdct?id=" + idHoaDon + "&idsp=" + idsp + "&soLuongMua=" + soLuongMua;

            // Redirect đến URL mới
            window.location.href = url;
        }

        function formatPrice(price) {
            // Chuyển đổi giá tiền sang chuỗi và thêm dấu phẩy phân cách hàng nghìn
            return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    }


</script>

</body>
</html>
