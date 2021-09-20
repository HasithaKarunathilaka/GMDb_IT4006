/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.Date;
import java.util.List;
import lk.karunathilaka.hibernate.entity.Actor;
import lk.karunathilaka.hibernate.entity.BaseEntity;
import lk.karunathilaka.hibernate.entity.Director;
import lk.karunathilaka.hibernate.entity.Genre;
import lk.karunathilaka.hibernate.entity.Movie;
import lk.karunathilaka.hibernate.entity.Review;
import lk.karunathilaka.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Hasitha
 */
public class MovieEaoImpl implements MovieEao{
    
    SessionFactory sessionFactory;
    
    public MovieEaoImpl(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void creat(Movie movie) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        session.beginTransaction();
                
        session.persist(movie);
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public List<Movie> getMoviesByGenre(int genreId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<Movie> getMoviesByDirector(int directorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getMoviesByActors(int actorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllMovies() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String hql = "FROM Movie m";
        Session session = sessionFactory.openSession();
        
        Query query = session.createQuery(hql);
        
        List<Movie> movies = query.list();
        
        return movies;
    }

    @Override
    public Movie getMoviesGenre(int movieId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session session = sessionFactory.openSession();
        Movie movie = (Movie) session.get(Movie.class, movieId);
        
        List<Genre> genres = movie.getGenres();
        List<Actor> actors = movie.getActors();
        List<Director> directors = movie.getDirectors();
        List<Review> reviews = movie.getReviews();
        
        for (Genre g : genres) {
//             System.out.println(g.getGenreType());
        }
        for (Director d : directors) {

        }
        for (Actor a : actors) {

        }
        for (Review r : reviews) {

        }
        
        session.close();
        
        return movie;
    }
    
}
