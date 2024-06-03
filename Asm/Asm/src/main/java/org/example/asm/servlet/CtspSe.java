package org.example.asm.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.asm.entity.Ctsp;
import org.example.asm.entity.MauSac;
import org.example.asm.entity.SanPham;
import org.example.asm.entity.Size;
import org.example.asm.repostity.CtspRespostity;
import org.example.asm.repostity.MauSacRespostity;
import org.example.asm.repostity.SanPhamResponstity;
import org.example.asm.repostity.SizeRespostity;
import org.example.asm.service.Method;

@WebServlet(name = "CtspSeServlet", value = {
        "/ctsp/index", "/ctsp/create", "/ctsp/store",
        "/ctsp/edit", "/ctsp/update", "/ctsp/delete"
})
public class CtspSe extends HttpServlet implements Method {

    SanPhamResponstity sanPhamResponstity = new SanPhamResponstity();
    SizeRespostity sizeRespostity = new SizeRespostity();
    MauSacRespostity mauSacRespostity = new MauSacRespostity();

    CtspRespostity ctspRespostity = new CtspRespostity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/ctsp/index")) {
            show(req, resp);
        } else if (uri.contains("/ctsp/create")) {
            create(req, resp);
        } else if (uri.contains("/ctsp/edit")) {
            edit(req, resp);
        } else if (uri.contains("/ctsp/delete")) {
            delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/ctsp/store")) {
            store(req, resp);
        }else if(uri.contains("/ctsp/update")){
            update(req,resp);
        }
    }

    @Override
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Ctsp> ctsp = ctspRespostity.getCtsp();
        req.setAttribute("Ctsp", ctsp);
    }

    @Override
    public void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/ctsp/index.jsp");
        List<Ctsp> listCtsp = ctspRespostity.findByProductId(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listCtsp", listCtsp);
        SanPham listSP = sanPhamResponstity.findBySP(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("listSP", listSP);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<MauSac> ms = mauSacRespostity.getMauSac();
        ArrayList<Size> s = sizeRespostity.getSize();
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/ctsp/create.jsp");
        req.setAttribute("mauSac", ms);
        req.setAttribute("size", s);

        req.setAttribute("idProduct", req.getParameter("id"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ctsp addCtsp = new Ctsp();
        SanPham sp = this.ctspRespostity.findBySP(Integer.parseInt(req.getParameter("id")));
        MauSac mauSac = ctspRespostity.findByMauSac(Integer.parseInt(req.getParameter("mauSac")));
        Size size = ctspRespostity.findBySize(Integer.parseInt(req.getParameter("size")));
        addCtsp.setSanPham(sp);
        addCtsp.setMauSac(mauSac);
        addCtsp.setSize(size);
        addCtsp.setGiaBan(Double.parseDouble(req.getParameter("giaBan")));
        addCtsp.setSoLuongTon(Integer.parseInt(req.getParameter("soLuongTon")));
        addCtsp.setTrangThai(req.getParameter("trangThai"));

        addCtsp.setNgayTao(new Date());
        addCtsp.setNgaySua(new Date());

        ctspRespostity.add(addCtsp);

        // Redirecting
        resp.sendRedirect("/ctsp/index?id=" + sp.getId());
    }


    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("container");
        session.setAttribute("container", "/pages/ctsp/edit.jsp");
        try {
            Ctsp ctsp = ctspRespostity.findByCtsp(Integer.parseInt(req.getParameter("id")));


            if (ctsp != null) {
                ArrayList<MauSac> ms = mauSacRespostity.getMauSac();
                ArrayList<Size> s = sizeRespostity.getSize();
                req.setAttribute("Ctsp", ctsp);
                req.setAttribute("mauSac", ms);
                req.setAttribute("size", s);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                System.out.println("loi ko tim thay");
            }
        } catch (NumberFormatException | ServletException | IOException e) {

            e.printStackTrace();
            System.out.println("loi ngoai le");
        }
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ctsp addCtsp = ctspRespostity.findByCtsp(Integer.parseInt(req.getParameter("id")));
        addCtsp.setGiaBan(Double.parseDouble(req.getParameter("giaBan")));
        addCtsp.setSoLuongTon(Integer.parseInt(req.getParameter("soLuongTon")));
        addCtsp.setTrangThai(req.getParameter("trangThai"));

        addCtsp.setNgaySua(new Date());

        ctspRespostity.add(addCtsp);

        resp.sendRedirect("/ctsp/index?id=" + addCtsp.getSanPham().getId());
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ctsp addCtsp = ctspRespostity.findByCtsp(Integer.parseInt(req.getParameter("id")));
        ctspRespostity.deleteCtsp(addCtsp);
        resp.sendRedirect("/ctsp/index?id=" + addCtsp.getSanPham().getId());
    }
}