package org.example.asm.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm.entity.DanhMuc;
import org.example.asm.entity.SanPham;
import org.example.asm.repostity.DanhMucRespostity;
import org.example.asm.repostity.SanPhamResponstity;
import org.example.asm.service.Method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SanPhamSeServlet", value = {"/sanpham/index", "/sanpham/create",
        "/sanpham/store", "/sanpham/edit", "/sanpham/update", "/sanpham/delete"})
public class SanPhamSe extends HttpServlet implements Method {

    SanPhamResponstity sanPhamResponstity = new SanPhamResponstity();
    DanhMucRespostity danhMucRespostity = new DanhMucRespostity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/sanpham/index")) {
            show(req, resp);
        } else if (uri.contains("/sanpham/edit")) {
            edit(req, resp);
        } else if (uri.contains("/sanpham/create")) {
            create(req, resp);
        }else if (uri.contains("/sanpham/delete")){
            delete(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/sanpham/store")) {
            store(req, resp);
        } else if (uri.contains("/sanpham/update")) {
            update(req, resp);
        }
    }


    @Override
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        session.setAttribute("container", "/pages/sanpham/index.jsp");
//        String s1 = req.getParameter("keyword");
//        String s2 = req.getParameter("status");
//        String s3 = req.getParameter("page");
//        String s4 = req.getParameter("limit");
//
//        String keyword = (s1 == null) ? "" : s1.trim();
//        String status = (s2 == null) ? "" : s2.trim();
//        int page = (s3 == null || s3.trim().isEmpty()) ? 1 : Integer.parseInt(s3);
//        int limit = (s4 == null || s4.trim().isEmpty()) ? 10 : Integer.parseInt(s4);
//        StringBuffer queryString = new StringBuffer("limit=" + limit);
//        List<SanPham> listPd = this.sanPhamResponstity.findAll(page, limit, keyword, status);
//        long totalPage = (this.sanPhamResponstity.countPage() / limit) + 1;
//        if (!keyword.isEmpty()) {
//            req.setAttribute("keyword", keyword);
//            queryString.append("&keyword=").append(keyword);
//        }
//        if (!status.isEmpty()) {
//            req.setAttribute("status", status);
//            queryString.append("&status=").append(status);
//        }
//        req.setAttribute("queryString", queryString);
//        req.setAttribute("totalPage", totalPage);
//        req.setAttribute("page", page);
//        req.setAttribute("queryString", queryString);
//        req.setAttribute("VIEW", "/pages/sanpham/index.jsp");
//        req.setAttribute("TITLE", "PRODUCT MANAGE");
//        req.setAttribute("listProduct", listPd);
//        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("container", "/pages/sanpham/index.jsp");
        String s1 = req.getParameter("keyword");
        String s2 = req.getParameter("status");
        String s3 = req.getParameter("page");
        String s4 = req.getParameter("limit");

        String keyword = (s1 == null) ? "" : s1.trim();
        String status = (s2 == null) ? "" : s2.trim();
        int page = (s3 == null || s3.trim().length() == 0) ? 1 : Integer.parseInt(s3);
        int limit = (s4 == null || s4.trim().length() == 0) ? 5 : Integer.parseInt(s4);

        SanPhamResponstity sanPhamResponstity = new SanPhamResponstity(); // Khởi tạo đối tượng SanPhamResponstity

        List<SanPham> listSP = sanPhamResponstity.findAll(page, limit, keyword, status); // Sử dụng phương thức findAll với các tham số truyền vào

        long totalPage = (sanPhamResponstity.getTotalAcount() / limit) + 1; // Tính toán tổng số trang

        req.setAttribute("listSP", listSP);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("page", page);
        req.setAttribute("keyword", keyword);
        req.setAttribute("status", status);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/sanpham/create.jsp");
        ArrayList<DanhMuc> listDM = danhMucRespostity.getDanhMuc();
        System.out.println(listDM);
        req.setAttribute("listDM", listDM);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SanPham sp = new SanPham();
        sp.setMaSanPham(req.getParameter("maSanPham"));
        sp.setTenSanPham(req.getParameter("tenSanPham"));
        sp.setTrangThai(req.getParameter("trangThai"));
        sp.setNgayTao(new Date());
        sp.setNgaySua(new Date());
        DanhMuc dm = sanPhamResponstity.findById(Integer.parseInt(req.getParameter("danhMuc")));
        sp.setDanhMuc(dm);
        sanPhamResponstity.add(sp);
        resp.sendRedirect("/sanpham/index");
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/sanpham/edit.jsp");
        SanPham sanPham = sanPhamResponstity.findBySP(Integer.parseInt(req.getParameter("id")));
        ArrayList<DanhMuc> listDM = danhMucRespostity.getDanhMuc();
        req.setAttribute("listDM", listDM);
        req.setAttribute("sanPham", sanPham);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SanPham sp = sanPhamResponstity.findBySP(Integer.parseInt(req.getParameter("id")));
        sp.setMaSanPham(req.getParameter("maSanPham"));
        sp.setTenSanPham(req.getParameter("tenSanPham"));
        sp.setTrangThai(req.getParameter("trangThai"));
        sp.setNgayTao(new Date());
        sp.setNgaySua(new Date());
        DanhMuc dm = sanPhamResponstity.findById(Integer.parseInt(req.getParameter("danhMuc")));
        sp.setDanhMuc(dm);
        sanPhamResponstity.add(sp);
        resp.sendRedirect("/sanpham/index");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SanPham sp = sanPhamResponstity.findBySP(Integer.parseInt(req.getParameter("id")));
        sanPhamResponstity.delete(sp);
        resp.sendRedirect("/sanpham/index");
    }
}