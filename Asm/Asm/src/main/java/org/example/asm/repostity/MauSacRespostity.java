package org.example.asm.repostity;



import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.MauSac;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MauSacRespostity {
    Session session;

    public ArrayList<MauSac> getMauSac(){
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<MauSac> list = (ArrayList<MauSac>) session.createQuery("from MauSac ").list();
        session.close();
        return list;
    }

    public boolean add(MauSac ms){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.saveOrUpdate(ms);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public boolean delete(MauSac ms){
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(ms);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public MauSac findByMauSac(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        MauSac ms = (MauSac) session.createQuery("from MauSac where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return ms;
    }
}
