package org.example.asm.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm.entity.KhachHang;
import org.example.asm.repostity.KhachHangRespostity;
import org.example.asm.service.Method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "KhachHangSeServlet", value = {
        "/khachhang/index", "/khachhang/create", "/khachhang/store", "/khachhang/edit",
        "/khachhang/update","/khachhang/delete"
})
public class KhachHangSe extends HttpServlet implements Method {

    KhachHangRespostity khachhangRespostity = new KhachHangRespostity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/khachhang/index")) {
            show(req, resp);
        } else if (uri.contains("/khachhang/edit")) {
            edit(req, resp);
        } else if (uri.contains("/khachhang/create")) {
            create(req, resp);
        } else if (uri.contains("/khachhang/delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/khachhang/store")) {
            store(req, resp);
        } else if (uri.contains("/khachhang/update")) {
            update(req, resp);
        }
    }

    @Override
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/khachhang/index.jsp");
        ArrayList<KhachHang> listKH = khachhangRespostity.getKhachHang();
        req.setAttribute("listKH", listKH);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/khachhang/create.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KhachHang kh = new KhachHang();
        kh.setHoTen(req.getParameter("hoTen"));
        kh.setDiaChi(req.getParameter("diaChi"));
        kh.setSdt(req.getParameter("sdt"));
        kh.setTrangThai(req.getParameter("trangThai"));
        kh.setNgayTao(new Date());
        kh.setNgaySua(new Date());
        khachhangRespostity.add(kh);
        resp.sendRedirect("/khachhang/index");
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/khachhang/edit.jsp");
        KhachHang khachhang = khachhangRespostity.findByKH(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listKH", khachhang);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KhachHang kh = khachhangRespostity.findByKH(Integer.parseInt(req.getParameter("id")));
        kh.setHoTen(req.getParameter("hoTen"));
        kh.setDiaChi(req.getParameter("diaChi"));
        kh.setSdt(req.getParameter("sdt"));
        kh.setTrangThai(req.getParameter("trangThai"));
        kh.setNgayTao(new Date());
        kh.setNgaySua(new Date());
        khachhangRespostity.add(kh);
        resp.sendRedirect("/khachhang/index");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        KhachHang kh = khachhangRespostity.findByKH(Integer.parseInt(req.getParameter("id")));
        khachhangRespostity.delete(kh);
        resp.sendRedirect("/khachhang/index");
    }
}