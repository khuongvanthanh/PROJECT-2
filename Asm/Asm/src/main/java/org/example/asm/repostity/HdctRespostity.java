package org.example.asm.repostity;

import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.Ctsp;
import org.example.asm.entity.Hdct;
import org.example.asm.entity.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HdctRespostity {

    Session session;
    public ArrayList<Hdct> getHdct(Integer idHoaDon) {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<Hdct> list = (ArrayList<Hdct>) session.createQuery("from Hdct ").list();
        session.close();
        return list;
    }

    public boolean addHdct(Hdct hdct) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean updateHdct(Hdct hdct) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean deleteHdct(Hdct hdct) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public Hdct findByHdAndCtsp(int idHoaDon, int idCtsp) {
        session = HibernateUtils.getFACTORY().openSession();
        Hdct hdct = null;
        try {
            hdct = (Hdct) session.createQuery("from Hdct where hoaDon.id = :idHoaDon and ctsp.id = :idCtsp")
                    .setParameter("idHoaDon", idHoaDon)
                    .setParameter("idCtsp", idCtsp)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hdct;
    }
    public List<Hdct> findByHD(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        HoaDon hd = (HoaDon) session.createQuery("from HoaDon where id = :id_1").setParameter("id_1", id).getSingleResult();
        List<Hdct> hdctList = session.createQuery("from Hdct where hoaDon = :hoaDon_1").setParameter("hoaDon_1", hd).getResultList();
        session.close();
        return hdctList;
    }

}
