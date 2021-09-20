/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Actor;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class ActorEaoImpl implements ActorEao{
    
    SessionFactory sessionFactory;

    public ActorEaoImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public void creat(Actor actor) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.persist(actor);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Actor> getAllActors() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Actor a";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        
        List<Actor> actors = query.list();
        
        return actors;
    }
    
}
