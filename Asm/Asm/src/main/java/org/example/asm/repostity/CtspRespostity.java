package org.example.asm.repostity;

import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CtspRespostity {

    Session session;

    public ArrayList<Ctsp> getCtsp() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<Ctsp> list = (ArrayList<Ctsp>) session.createQuery("from Ctsp ").list();
        session.close();
        return list;
    }

    public boolean add(Ctsp ctsp) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public List<Ctsp> findByProductId(int productId) {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<Ctsp> list = (ArrayList<Ctsp>) session.createQuery("from Ctsp WHERE sanPham.id = :id").setParameter("id", productId).getResultList();
        session.close();
        return list;
    }

    public MauSac findByMauSac(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        MauSac ms = (MauSac) session.createQuery("from MauSac where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return ms;
    }

    public Size findBySize(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        Size s = (Size) session.createQuery("from Size where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return s;
    }

    public SanPham findBySP(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        SanPham sp = (SanPham) session.createQuery("from SanPham where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return sp;
    }

    public Ctsp findByCtsp(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        Ctsp ctsp = (Ctsp) session.createQuery("from Ctsp where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return ctsp;
    }

    public void updateCtsp(Ctsp ctsp) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(ctsp); // Sử dụng update để cập nhật thông tin của sản phẩm
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteCtsp(Ctsp ctsp) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(ctsp); // Sử dụng update để cập nhật thông tin của sản phẩm
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }


        public static void main(String[] args) {
        Session session ;
            session = HibernateUtils.getFACTORY().openSession();
            Ctsp ctsp = session.get(Ctsp.class, 166);
            session.close();
            System.out.println(ctsp.getId());;
        }




}
