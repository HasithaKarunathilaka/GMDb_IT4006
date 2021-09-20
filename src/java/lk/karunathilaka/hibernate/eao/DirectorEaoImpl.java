/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Director;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class DirectorEaoImpl implements DirectorEao{
    
    SessionFactory sessionFactory;

    public DirectorEaoImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public void creat(Director director) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.persist(director);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Director> getAllDirectors() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Director d";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        
        List<Director> directors = query.list();
        
        return directors;
    }
    
}
