package org.example.asm.repostity;

import jakarta.persistence.NoResultException;
import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class KhachHangRespostity {
    Session session;

    public ArrayList<KhachHang> getKhachHang() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<KhachHang> list = (ArrayList<KhachHang>) session.createQuery("from KhachHang ").list();
        session.close();
        return list;
    }

    public boolean add(KhachHang kh) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean delete(KhachHang kh) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public KhachHang findByKH(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        KhachHang kh = (KhachHang) session.createQuery("from KhachHang where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return kh;
    }

    public KhachHang findBySdt(String sdt) {
        session = HibernateUtils.getFACTORY().openSession();
        try {
            KhachHang kh = (KhachHang) session.createQuery("from KhachHang where sdt = :sdt_1")
                    .setParameter("sdt_1", sdt)
                    .uniqueResult();
            session.close();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;


        }
    }
}


