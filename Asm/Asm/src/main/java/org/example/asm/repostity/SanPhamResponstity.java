package org.example.asm.repostity;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.asm.connect.HibernateUtils;
import org.example.asm.entity.DanhMuc;
import org.example.asm.entity.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamResponstity {
    Session session;

    public ArrayList<SanPham> getSanPham() {
        session = HibernateUtils.getFACTORY().openSession();
        ArrayList<SanPham> list = (ArrayList<SanPham>) session.createQuery("from SanPham ").list();
        session.close();
        System.out.println(list);
        return list;
    }

    public boolean add(SanPham sp) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }


    public boolean delete(SanPham sp) {
        session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(sp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public DanhMuc findById(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        DanhMuc dm = (DanhMuc) session.createQuery("from DanhMuc where id = :id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return dm;
    }

    public SanPham findBySP(int id) {
        session = HibernateUtils.getFACTORY().openSession();
        SanPham sp = (SanPham) session.createQuery("from SanPham where id = :id_1").setParameter("id_1", id).getSingleResult();
        System.out.println("findBySP :" + sp);
        session.close();
        return sp;
    }

public long getTotalAcount() { //689--> 69 trang
        Session session = HibernateUtils.getFACTORY().openSession();
        String count = "Select count(*) from SanPham ";
        Query countQuery = session.createQuery(count);
        return (long) countQuery.uniqueResult();
    }

    public List<SanPham> findAll(int page, int limit, String keyword, String status) {
        try {
            session = HibernateUtils.getFACTORY().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SanPham> criteria = builder.createQuery(SanPham.class);
            Root<SanPham> root = criteria.from(SanPham.class);
            criteria.select(root);

            if (keyword != null || status != null) {
                List<Predicate> predicates = new ArrayList<>();
                if (keyword != null) {
                    Predicate keywordPredicate = builder.or(
                            builder.like(root.get("maSanPham"), "%" + keyword + "%"),
                            builder.like(root.get("tenSanPham"), "%" + keyword + "%")
                    );
                    predicates.add(keywordPredicate);
                }
                if (status != null) {
                    Predicate statusPredicate = builder.like(root.get("trangThai"), "%" + status + "%");
                    predicates.add(statusPredicate);
                }
                criteria.where(builder.and(predicates.toArray(new Predicate[0])));
            }

            TypedQuery<SanPham> query = session.createQuery(criteria);
            List<SanPham> resultList = query.getResultList();

            int startIndex = (page - 1) * limit;
            int endIndex = Math.min(startIndex + limit, resultList.size());
            return resultList.subList(startIndex, endIndex);

        } catch (Exception exception) {
            throw exception;
        } finally {
            session.close();
        }
    }
}
