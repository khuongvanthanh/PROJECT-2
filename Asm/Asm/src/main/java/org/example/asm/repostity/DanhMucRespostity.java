package org.example.asm.repostity;

import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.DanhMuc;
import org.example.asm.entity.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DanhMucRespostity {
    Session session;

    public ArrayList<DanhMuc> getDanhMuc() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<DanhMuc> list = (ArrayList<DanhMuc>) session.createQuery("from DanhMuc ").list();
        session.close();
        return list;
    }

    public boolean add(DanhMuc dm){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.saveOrUpdate(dm);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean delete(DanhMuc dm){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(dm);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public DanhMuc findByDM(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        DanhMuc dm = (DanhMuc) session.createQuery("from DanhMuc where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return dm;
    }

}
