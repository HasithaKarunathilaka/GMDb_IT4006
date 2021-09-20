/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Member;
import lk.karunathilaka.hibernate.entity.Review;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class MemberEaoImpl implements MemberEao{
    
    SessionFactory sessionFactory;
    
    public MemberEaoImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(Member member) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.persist(member);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(Member member) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.saveOrUpdate(member);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Member getMember(int memberId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        Member member = (Member) session.get(Member.class, memberId);
        
        List<Review> reviews = member.getReviews();
        
        for(Review r : reviews){
        
        }
        
        session.close();
        return member;
    }

    @Override
    public List<Member> getMemberByEmailAndPassword(String email, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Member m WHERE m.email = :member_email AND m.password = :member_password";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        query.setParameter("member_email", email);
        query.setParameter("member_password", password);
        
        List<Member> members = query.list();
        
        session.close();
        
        return members;
    }

    @Override
    public List<Member> getmemberByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Member m WHERE m.email = :member_email";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        query.setParameter("member_email", email);
        
        List<Member> members = query.list();
        
        session.close();
        
        return members;
    }
    
    
    
}
