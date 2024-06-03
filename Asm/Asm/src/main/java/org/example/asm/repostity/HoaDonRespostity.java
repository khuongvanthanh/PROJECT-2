package org.example.asm.repostity;

import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.Ctsp;
import org.example.asm.entity.Hdct;
import org.example.asm.entity.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class HoaDonRespostity {
    Session session;

    public ArrayList<HoaDon> getHoaDon() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon ").list();
        session.close();
        return list;
    }

    public boolean add(HoaDon hd) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }


    public void delete(HoaDon hd) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(hd);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
        }
    }

    public HoaDon findByHD(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        HoaDon hd = (HoaDon) session.createQuery("from HoaDon where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return hd;
    }

    public int getCount() {
        int count = 0;
        try {
            session = HibernateUtils.getFACTORY().openSession();
            count = ((Number) session.createQuery("select count(*) from HoaDon").uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }



}
