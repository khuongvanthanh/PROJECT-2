package org.example.asm.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm.entity.MauSac;
import org.example.asm.repostity.MauSacRespostity;
import org.example.asm.service.Method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "MauSacSeServlet", value = {
        "/mausac/index", "/mausac/create", "/mausac/store", "/mausac/edit",
        "/mausac/update","/mausac/delete"
})
public class MauSacSe extends HttpServlet implements Method {

    MauSacRespostity mausacRespostity = new MauSacRespostity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/mausac/index")) {
            show(req, resp);
        } else if (uri.contains("/mausac/edit")) {
            edit(req, resp);
        } else if (uri.contains("/mausac/create")) {
            create(req, resp);
        } else if (uri.contains("/mausac/delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/mausac/store")) {
            store(req, resp);
        } else if (uri.contains("/mausac/update")) {
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
        session.setAttribute("container", "/pages/mausac/index.jsp");
        ArrayList<MauSac> listMS = mausacRespostity.getMauSac();
        req.setAttribute("listMS", listMS);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/mausac/create.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MauSac ms = new MauSac();
        ms.setMaMau(req.getParameter("maMau"));
        ms.setTenMau(req.getParameter("tenMau"));
        ms.setTrangThai(req.getParameter("trangThai"));
        ms.setNgayTao(new Date());
        ms.setNgaySua(new Date());
        mausacRespostity.add(ms);
        resp.sendRedirect("/mausac/index");
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/mausac/edit.jsp");
        MauSac mausac = mausacRespostity.findByMauSac(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listMS", mausac);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MauSac ms = mausacRespostity.findByMauSac(Integer.parseInt(req.getParameter("id")));
        ms.setMaMau(req.getParameter("maMau"));
        ms.setTenMau(req.getParameter("tenMau"));
        ms.setTrangThai(req.getParameter("trangThai"));
        ms.setNgayTao(new Date());
        ms.setNgaySua(new Date());
        mausacRespostity.add(ms);
        resp.sendRedirect("/mausac/index");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MauSac ms = mausacRespostity.findByMauSac(Integer.parseInt(req.getParameter("id")));
        mausacRespostity.delete(ms);
        resp.sendRedirect("/mausac/index");
    }
}