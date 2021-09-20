/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Genre;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class GenreEaoImpl implements GenreEao{
    
    SessionFactory sessionFactory;

    public GenreEaoImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public void create(Genre genre) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.persist(genre);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Genre> getAllGenre() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Genre g";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        
        List<Genre> genres = query.list();
        
        return genres;
    }

    @Override
    public List<Genre> getGenreByMovieId(int movieId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}
