/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Movie;

/**
 *
 * @author Hasitha
 */
public interface MovieEao {
    
    void creat(Movie movie);
    void saveOrUpdate(Movie movie);
    
    List<Movie> getAllMovies();
    Movie getMoviesGenre(int movieId);
    List<Movie> getMoviesByDirector(int directorId);
    List<Movie> getMoviesByActors (int actorId);
    
}
