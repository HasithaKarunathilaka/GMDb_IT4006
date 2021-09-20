/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.eao;

import java.util.List;
import lk.karunathilaka.hibernate.entity.Genre;

/**
 *
 * @author Hasitha
 */
public interface GenreEao {
    
    void create(Genre genre);
    List<Genre> getAllGenre();
    
    List<Genre> getGenreByMovieId(int movieId);
}
