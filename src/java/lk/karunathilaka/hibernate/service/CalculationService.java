/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.service;

import java.util.List;
import lk.karunathilaka.hibernate.eao.MovieEao;
import lk.karunathilaka.hibernate.eao.MovieEaoImpl;
import lk.karunathilaka.hibernate.entity.Movie;
import lk.karunathilaka.hibernate.entity.Review;

/**
 *
 * @author Hasitha
 */
public class CalculationService{
    
    public Double rateCal(Movie movie){
        int totRate = 0;
        Double rate = 0.0;
        int count = 0;
        MovieEao movieEao = new MovieEaoImpl();
        
        Movie movies = movieEao.getMoviesGenre(movie.getId());
        
        List<Review> reviews = movies.getReviews();
        
        for(Review r : reviews){
            if(r.getRate() != 100){
                totRate = totRate + r.getRate();
                count++;
            
            }
        
        }
        
        if(count != 0){
            rate = Double.valueOf(totRate)/ count;
        }
        
        return rate;
    }
    
}
