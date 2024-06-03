package org.example.asm.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.asm.entity.Ctsp;
import org.example.asm.entity.Hdct;
import org.example.asm.entity.HoaDon;
import org.example.asm.entity.KhachHang;
import org.example.asm.repostity.CtspRespostity;
import org.example.asm.repostity.HdctRespostity;
import org.example.asm.repostity.HoaDonRespostity;
import org.example.asm.repostity.KhachHangRespostity;
import org.example.asm.service.Method;

@WebServlet(name = "HoaDonServlet", value = {
        "/hoadon/index", "/hoadon/delete", "/hoadon/add", "/hoadon/edit",
        "/hoadon/create", "/hoadon/detail", "/hoadon/addHdct", "/hoadon/timkiem", "/hoadon/thanhtoan"
})
public class HoaDonSe extends HttpServlet implements Method {
    CtspRespostity ctspRespostity = new CtspRespostity();
    HoaDonRespostity hoaDonRespostity = new HoaDonRespostity();
    ArrayList<Hdct> listHDCT = new ArrayList<>();
    ArrayList<HoaDon> listHoaDon = new ArrayList<>();
    HdctRespostity hdctRespostity = new HdctRespostity();

    ArrayList<Ctsp> listChiTietSanPham = new ArrayList<>();

    KhachHangRespostity khachHangRespostity = new KhachHangRespostity();

    Double tongTien = Double.valueOf(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/hoadon/index")) {
            show(req, resp);
        } else if (uri.contains("/hoadon/delete")) {
            delete(req, resp);
        } else if (uri.equals("/hoadon/edit")) {
            this.hoaDonDetail(req, resp);
        } else if (uri.equals("/hoadon/create")) {
            create(req, resp);
        } else if (uri.equals("/hoadon/detail")) {
            hoaDonDetail(req, resp);
        } else if (uri.equals("/hoadon/addHdct")) {
            addHdct(req, resp);
        } else if (uri.equals("/hoadon/thanhtoan")) {
            thanhToan(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/hoadon/addHdct")) {
            addHdct(req, resp);
        } else if (uri.contains("/hoadon/add")) {
            store(req, resp);
        } else if (uri.equals("/hoadon/detail")) {
            hoaDonDetail(req, resp);
        }
    }


    @Override
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/hoadon/index.jsp");
        ArrayList<Ctsp> listCtsp = ctspRespostity.getCtsp();
        ArrayList<HoaDon> listHoaDon = hoaDonRespostity.getHoaDon();
        req.setAttribute("listCtsp", listCtsp);
        req.setAttribute("listHoaDon", listHoaDon);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("idProduct", req.getParameter("id"));
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentDataCount = hoaDonRespostity.getCount();

        if (currentDataCount >= 5) {
            return;
        }
        HoaDon hd = new HoaDon();
        hd.setTrangThai("Pending");
        hoaDonRespostity.add(hd);
        resp.sendRedirect("/hoadon/index");
    }


    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/hoadon/index.jsp");
        Integer idHoaDon = Integer.parseInt(req.getParameter("id"));
        HoaDon hoaDonDetail = hoaDonRespostity.getHoaDon().get(idHoaDon);
        listHDCT = hdctRespostity.getHdct(idHoaDon);
        tongTien = Double.valueOf(0);
        for (Hdct hoaDonChiTiet : listHDCT) {
            tongTien += hoaDonChiTiet.getTongTien();
        }
        req.setAttribute("tongTien", tongTien);
        req.setAttribute("listHDCT", listHDCT);
        req.setAttribute("hoaDonDetail", hoaDonDetail);
        req.setAttribute("chiTietSanPham", listChiTietSanPham);
        req.setAttribute("listHoaDon", listHoaDon);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hoaDonId = Integer.parseInt(req.getParameter("id"));
        HoaDon hoaDon = hoaDonRespostity.findByHD(hoaDonId);

        ArrayList<Hdct> hdctList = hdctRespostity.getHdct(hoaDonId);

        boolean stopUpdatingStock = false;

        for (Hdct hdct : hdctList) {
            Ctsp ctsp = hdct.getCtsp();
            int soLuongMua = hdct.getSoLuongMua();
            int soLuongTonHienTai = ctsp.getSoLuongTon();

            if (!stopUpdatingStock) {
                int soLuongTonMoi = soLuongTonHienTai + soLuongMua;
                ctsp.setSoLuongTon(soLuongTonMoi);
                ctspRespostity.updateCtsp(ctsp);
                break;
            }

            if (hdct.getHoaDon().getId() == hoaDonId) {
                stopUpdatingStock = true;
            }
        }

        hoaDonRespostity.delete(hoaDon);

        resp.sendRedirect("/hoadon/index");
    }

    private void hoaDonDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/hoadon/index.jsp");
        Integer idHoaDon = Integer.parseInt(request.getParameter("id"));
        HoaDon hd = hoaDonRespostity.findByHD(idHoaDon);
        HoaDon hoaDon = hoaDonRespostity.findByHD(idHoaDon);
        ArrayList<Ctsp> listCtsp = ctspRespostity.getCtsp();
        ArrayList<HoaDon> listHoaDon = hoaDonRespostity.getHoaDon();
        ArrayList<Hdct> listHDCT = new ArrayList<>();

        if (hd != null) {
            // Kiểm tra và lấy danh sách chi tiết hóa đơn cho hóa đơn chỉ định
            for (Hdct hdct : hdctRespostity.getHdct(idHoaDon)) {
                if (hdct.getHoaDon().getId().equals(idHoaDon)) {
                    listHDCT.add(hdct);
                }
            }

            if (!listHDCT.isEmpty()) {
                request.setAttribute("listHDCT", listHDCT);
            } else {
                request.setAttribute("message", "Không có sản phẩm nào cho hóa đơn này");
            }
        } else {
            request.setAttribute("message", "Không tìm thấy hóa đơn");
        }

        request.setAttribute("listCtsp", listCtsp);
        request.setAttribute("listHoaDon", listHoaDon);
        request.setAttribute("hoaDon", hoaDon);
        String sdt = request.getParameter("sdt");
        KhachHang khachHang = khachHangRespostity.findBySdt(sdt);
        if (khachHang != null) {
            String tenKhachHang = khachHang.getHoTen();
            request.setAttribute("tenKhachHang", tenKhachHang);
        } else {
            request.setAttribute("tenKhachHang", "Không tìm thấy khách hàng");
        }
        // Truyền giá trị sdt vào request để sử dụng trong trang kết quả tìm kiếm
        request.setAttribute("sdt", sdt);
        double tongTienCuoi = calculateTotalAmount(listHDCT);
        request.setAttribute("phone", sdt);
        request.setAttribute("tongTienCuoi", tongTienCuoi);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void addHdct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idHoaDon = Integer.parseInt(req.getParameter("id"));
        HoaDon hd = hoaDonRespostity.findByHD(idHoaDon);

        Integer idCtsp = Integer.parseInt(req.getParameter("idsp"));
        Ctsp ctsp = ctspRespostity.findByCtsp(idCtsp);

        Integer soLuongMua = Integer.parseInt(req.getParameter("soLuongMua"));

        if (ctsp != null && ctsp.getSoLuongTon() >= soLuongMua) {
            Hdct existingHdct = hdctRespostity.findByHdAndCtsp(hd.getId(), ctsp.getId());

            if (existingHdct != null) {
                // Nếu mục Hdct đã tồn tại, cập nhật số lượng mua và số lượng tồn kho
                int soLuongMuaCu = existingHdct.getSoLuongMua();
                int soLuongMuaMoi = soLuongMuaCu + soLuongMua;

                // Cập nhật số lượng tồn kho của sản phẩm
                int soLuongTonSauCapNhat = ctsp.getSoLuongTon() - (soLuongMuaMoi - soLuongMuaCu);
                if (soLuongTonSauCapNhat >= 0) {
                    ctsp.setSoLuongTon(soLuongTonSauCapNhat);

                    existingHdct.setSoLuongMua(soLuongMuaMoi);
                    existingHdct.setTongTien(existingHdct.getGiaBan() * soLuongMuaMoi);
                    hdctRespostity.updateHdct(existingHdct);
                } else {
                    req.setAttribute("error", "Không đủ số lượng tồn kho để cập nhật");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                    return;
                }
            } else {
                Hdct hdct = new Hdct();
                hdct.setHoaDon(hd);
                hdct.setCtsp(ctsp);
                hdct.setSoLuongMua(soLuongMua);
                hdct.setGiaBan(ctsp.getGiaBan());
                hdct.setTongTien(hdct.getGiaBan() * soLuongMua);
                hdct.setTrangThai(ctsp.getTrangThai());
                hdct.setNgayTao(new Date());
                hdct.setNgaySua(new Date());
                hdctRespostity.addHdct(hdct);

                int soLuongTonSauCapNhat = ctsp.getSoLuongTon() - soLuongMua;
                if (soLuongTonSauCapNhat >= 0) {
                    ctsp.setSoLuongTon(soLuongTonSauCapNhat);
                } else {
                    req.setAttribute("error", "Không đủ số lượng tồn kho để thêm mục mới");
                    req.getRequestDispatcher("/error.jsp").forward(req, resp);
                    return;
                }
            }

            ctspRespostity.updateCtsp(ctsp);

            List<Hdct> hdctList = hdctRespostity.findByHD(hd.getId());
            double tongTienCuoi = 0;
            for (Hdct hdct : hdctList) {
                tongTienCuoi += hdct.getTongTien();
            }

            req.setAttribute("tongTienCuoi", tongTienCuoi);
            req.getRequestDispatcher("/hoadon/detail?id=" + hd.getId()).forward(req, resp);
        } else {
            req.setAttribute("error", "Sản phẩm không tồn tại hoặc không đủ số lượng tồn kho");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private double calculateTotalAmount(ArrayList<Hdct> hdctList) {
        double totalAmount = 0;
        for (Hdct hdct : hdctList) {
            totalAmount += hdct.getTongTien();
        }
        return totalAmount;
    }

    public void thanhToan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hoaDonId = Integer.parseInt(req.getParameter("id"));
        HoaDon hoaDon = hoaDonRespostity.findByHD(hoaDonId);
        KhachHang kh = khachHangRespostity.findBySdt(req.getParameter("sdt"));
        if (hoaDon != null) {
            hoaDon.setTrangThai("Paid");
            hoaDon.setKhachHang(kh);
            hoaDon.setNgayTao(new Date());
            hoaDon.setSoDienThoai(req.getParameter("sdt"));
            hoaDon.setDiaChi(kh.getDiaChi());
            hoaDonRespostity.add(hoaDon);
            resp.sendRedirect("/hoadon/index");
        }
    }


}
