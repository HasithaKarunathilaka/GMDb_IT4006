/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Hasitha
 */
@Entity
@Table (name = "MOVIE_TAB")
public class Movie implements Serializable {
    private int id;
    private String name;
    private int runtime;
    
    private BaseEntity baseEntity; 
    private List<Genre> genres;
    private List<Director> directors;
    private List<Actor> actors;
    private List<Review> reviews;

    public Movie() {
        reviews = new ArrayList<>();
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "RUN_TIME")
    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Embedded
    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }

    @ManyToMany
    @JoinTable (name = "MOVIE_GENRE_TAB",
                joinColumns = {@JoinColumn (name = "movieId")},
                inverseJoinColumns = {@JoinColumn (name = "genreId")})
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @ManyToMany
    @JoinTable (name = "MOVIE_DIRECTOR_TAB",
                joinColumns = {@JoinColumn (name = "movieId")},
                inverseJoinColumns = {@JoinColumn (name = "directorId")})
    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    @ManyToMany
    @JoinTable (name = "MOVIE_ACTOR_TAB",
                joinColumns = {@JoinColumn (name = "movieId")},
                inverseJoinColumns = {@JoinColumn (name = "actorId")})
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @OneToMany (mappedBy = "movie", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    
    
}
