/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Admin;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class AdminEaoImpl implements AdminEao{
    
    SessionFactory sessionFactory;
    
    public AdminEaoImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(Admin admin) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.persist(admin);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(Admin admin) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        session.saveOrUpdate(admin);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Admin> getAdminByEmailAndPassword(String email, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Admin a WHERE a.email = :admin_email AND a.password = :admin_password";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        query.setParameter("admin_email", email);
        query.setParameter("admin_password", password);
        
        List<Admin> admins = query.list();
        
        session.close();
        
        return admins;
    }

    @Override
    public List<Admin> getAdminByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Admin a WHERE a.email = :admin_email";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        query.setParameter("admin_email", email);
        
        List<Admin> admins = query.list();
        
        session.close();
        
        return admins;
    }
}
