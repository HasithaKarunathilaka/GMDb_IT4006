/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import lk.karunathilaka.hibernate.entity.Purchase;
import lk.karunathilaka.hibernate.entity.Review;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class ReviewEaoImpl implements ReviewEao{
    
    SessionFactory sessionFactory;
    
    public ReviewEaoImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(Review review) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.save(review);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(Review review) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.update(review);
        
        session.getTransaction().commit();
        session.close();
    }
    
}
