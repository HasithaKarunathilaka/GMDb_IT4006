/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Hasitha
 */
@Entity
@Table (name = "GENRE_TAB")
public class Genre implements Serializable {
    
    private int id;
    private String genreType;
    
    private List<Movie> movies;

    public Genre() {
    }


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }
    
    @ManyToMany
    @JoinTable (name = "MOVIE_GENRE_TAB",
                joinColumns = {@JoinColumn (name = "genreId")},
                inverseJoinColumns = {@JoinColumn (name = "movieId")})
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
    
}
