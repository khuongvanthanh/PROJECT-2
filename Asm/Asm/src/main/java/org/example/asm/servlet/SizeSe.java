package org.example.asm.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.asm.entity.DanhMuc;
import org.example.asm.entity.Size;
import org.example.asm.repostity.SizeRespostity;
import org.example.asm.service.Method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SizeSeServlet", value = {

        "/size/index", "/size/create", "/size/store", "/size/edit",
        "/size/update", "/size/delete"
}

)
public class SizeSe extends HttpServlet implements Method {

    SizeRespostity sizeRespostity = new SizeRespostity();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/size/index")) {
            show(req, resp);
        } else if (uri.contains("/size/edit")) {
            edit(req, resp);
        } else if (uri.contains("/size/create")) {
            create(req, resp);
        } else if (uri.contains("/size/delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/size/store")) {
            store(req, resp);
        } else if (uri.contains("/size/update")) {
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
        session.setAttribute("container", "/pages/size/index.jsp");
        ArrayList<Size> listSize = sizeRespostity.getSize();
        req.setAttribute("listSize", listSize);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/size/create.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Size s = new Size();
        s.setMaSize(req.getParameter("maSize"));
        s.setTenSize(req.getParameter("tenSize"));
        s.setTrangThai(req.getParameter("trangThai"));
        s.setNgayTao(new Date());
        s.setNgaySua(new Date());
        sizeRespostity.add(s);
        resp.sendRedirect("/size/index");
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/size/edit.jsp");
        Size size = sizeRespostity.findBySize(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listSize", size);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Size s = sizeRespostity.findBySize(Integer.parseInt(req.getParameter("id")));
        s.setMaSize(req.getParameter("maSize"));
        s.setTenSize(req.getParameter("tenSize"));
        s.setTrangThai(req.getParameter("trangThai"));
        s.setNgayTao(new Date());
        s.setNgaySua(new Date());
        sizeRespostity.add(s);
        resp.sendRedirect("/size/index");
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Size dm = sizeRespostity.findBySize(Integer.parseInt(req.getParameter("id")));
        sizeRespostity.delete(dm);
        resp.sendRedirect("/size/index");
    }
}