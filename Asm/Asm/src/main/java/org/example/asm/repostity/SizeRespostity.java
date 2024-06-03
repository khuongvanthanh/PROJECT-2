package org.example.asm.repostity;


import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.DanhMuc;
import org.example.asm.entity.Size;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SizeRespostity {
    Session session;
    public ArrayList<Size> getSize() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<Size> list = (ArrayList<Size>) session.createQuery("from Size ").list();
        session.close();
        return list;
    }
    public boolean add(Size s){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.saveOrUpdate(s);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean delete(Size s){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(s);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public Size findBySize(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        Size s = (Size) session.createQuery("from Size where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return s;
    }
}
