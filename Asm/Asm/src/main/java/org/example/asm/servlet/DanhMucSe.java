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
import org.example.asm.service.Method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "DanhMucSeServlet", value = {
        "/danhMuc/index", "/danhMuc/create", "/danhMuc/store", "/danhMuc/edit",
        "/danhMuc/update","/danhMuc/delete"
})
public class DanhMucSe extends HttpServlet implements Method {

    DanhMucRespostity danhMucRespostity = new DanhMucRespostity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/danhMuc/index")) {
            show(req, resp);
        } else if (uri.contains("/danhMuc/edit")) {
            edit(req, resp);
        } else if (uri.contains("/danhMuc/create")) {
            create(req, resp);
        } else if (uri.contains("/danhMuc/delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/danhMuc/store")) {
            store(req, resp);
        } else if (uri.contains("/danhMuc/update")) {
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
        session.setAttribute("container", "/pages/danhmuc/index.jsp");
        ArrayList<DanhMuc> listDM = danhMucRespostity.getDanhMuc();
        req.setAttribute("listDM", listDM);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/danhmuc/create.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(req.getParameter("maDanhMuc"));
        dm.setTenDanhMuc(req.getParameter("tenDanhMuc"));
        dm.setTrangThai(req.getParameter("trangThai"));
        dm.setNgayTao(new Date());
        dm.setNgaySua(new Date());
        danhMucRespostity.add(dm);
        resp.sendRedirect("/danhMuc/index");
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/danhmuc/edit.jsp");
        DanhMuc danhMuc = danhMucRespostity.findByDM(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listDM", danhMuc);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc dm = danhMucRespostity.findByDM(Integer.parseInt(req.getParameter("id")));
        dm.setMaDanhMuc(req.getParameter("maDanhMuc"));
        dm.setTenDanhMuc(req.getParameter("tenDanhMuc"));
        dm.setTrangThai(req.getParameter("trangThai"));
        dm.setNgayTao(new Date());
        dm.setNgaySua(new Date());
        danhMucRespostity.add(dm);
        resp.sendRedirect("/danhMuc/index");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DanhMuc dm = danhMucRespostity.findByDM(Integer.parseInt(req.getParameter("id")));
        danhMucRespostity.delete(dm);
        resp.sendRedirect("/danhMuc/index");
    }
}