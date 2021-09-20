/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lk.karunathilaka.hibernate.eao.ActorEao;
import lk.karunathilaka.hibernate.eao.ActorEaoImpl;
import lk.karunathilaka.hibernate.eao.AdminEao;
import lk.karunathilaka.hibernate.eao.AdminEaoImpl;
import lk.karunathilaka.hibernate.eao.DirectorEao;
import lk.karunathilaka.hibernate.eao.DirectorEaoImpl;
import lk.karunathilaka.hibernate.eao.GenreEao;
import lk.karunathilaka.hibernate.eao.GenreEaoImpl;
import lk.karunathilaka.hibernate.eao.MovieEao;
import lk.karunathilaka.hibernate.eao.MovieEaoImpl;
import lk.karunathilaka.hibernate.entity.Actor;
import lk.karunathilaka.hibernate.entity.Admin;
import lk.karunathilaka.hibernate.entity.BaseEntity;
import lk.karunathilaka.hibernate.entity.Director;
import lk.karunathilaka.hibernate.entity.Genre;
import lk.karunathilaka.hibernate.entity.Movie;

/**
 *
 * @author Hasitha
 */
public class AdminService {

    public static void adminHome(Admin admin) {
        KeyboardInput keyboardInput = new KeyboardInput();

        while (true) {

            System.out.println("Instructions ----> \nTo Obtain Servic, Please Type the Relevent Service ID then Press Enter");
            System.out.println("   1 - Add Movie");
            System.out.println("   2 - Add Genre");
            System.out.println("   3 - Add Director");
            System.out.println("   4 - Add Actor");
            System.out.println("   5 - List Registered Members");
            System.out.println("0000 - Logout");
            
            String inputValue = keyboardInput.inputString();
            
            if("1".equals(inputValue)){
                setMovie(admin);
            
            }else if("2".equals(inputValue)){
                setGenre();
            
            }else if("3".equals(inputValue)){
                setDirector();
            
            }else if("4".equals(inputValue)){
                setActor();
            
            }else if("5".equals(inputValue)){
            
            }else if("0000".equals(inputValue)){
                System.out.println("Logout Success");
                UserService.initialiseSystem();
            
            }else {
            
            }
        }

    }

    public static void setAdmin() {

        AdminEao adminEao = new AdminEaoImpl();

        Admin admin = new Admin();
        admin.setName("admin2");
        admin.setEmail("admin@12.com");

        String dob = "18-12-1995";
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

        try {
            admin.setDob(sd.parse(dob));
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }

        admin.setPassword("12");
        admin.setState("active");

        List<Admin> admins = adminEao.getAdminByEmail(admin.getEmail());
        if (admins.isEmpty()) {
            adminEao.create(admin);
        } else {
            System.out.println("Your Email is already Registered...!");
        }

    }

    public static void setMovie(Admin admin) {
        KeyboardInput keyboardInput = new KeyboardInput();
        
//        int[] inputGenres = {2};
//        int[] inputDirectors = {1, 2};
//        int[] inputActors = {1, 2};
        String createdBy = admin.getName();

        MovieEao movieEao = new MovieEaoImpl();

        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setCreatedBy(createdBy);
        baseEntity.setCreatedDate(new Date());

        Movie movie = new Movie();
        
        System.out.println("Enter Movie NAME: ");
        String movieName = keyboardInput.inputString();
        movie.setName(movieName);
        
        System.out.println("Enter Movie Duration: (In Miniutes)");
        int duration = keyboardInput.inputNumber();
        movie.setRuntime(duration);
        
        movie.setBaseEntity(baseEntity);

        List<Genre> genres = getAllGenre();
        
        System.out.println("Total Number of Genres are " + genres.size());
        
        int inputValue = 0;
        while(true){
            System.out.println("How many Genres you want add this movie? ");
            inputValue = keyboardInput.inputNumber();
            
            if(inputValue <= genres.size()){
                break;
            }else{
                System.out.println("You can not enter more than " + genres.size() + " number of Genres");
                System.out.println("If you want to add new genre, Go to Admin Home and Add Genre");
        
            }
        }
        
        for(Genre g : genres){            
            System.out.println(g.getId() + " - " + g.getGenreType());
        }
        
        int[] inputGenres = new int[inputValue];
        
        for(int i = 0; i < inputValue; i++){
            System.out.println("Enter Id of Genre " + (i+1));
            int value = keyboardInput.inputNumber();
            inputGenres[i] = value;
        }
        
        List<Genre> selectedGenres = new ArrayList<>();

        for (int data : inputGenres) {
            for (Genre genre : genres) {
                if (data == genre.getId()) {
                    selectedGenres.add(genre);
                    break;
                }

            }

        }

        movie.setGenres(selectedGenres);

        List<Director> directors = getAllDirectors();
        
        System.out.println("Total Number of Directors are " + directors.size());
        
        int numberOfDir = 0;
        while(true){
            System.out.println("How many Directors you want add this movie? ");
            numberOfDir = keyboardInput.inputNumber();
            
            if(numberOfDir <= directors.size()){
                break;
            }else{
                System.out.println("You can not enter more than " + directors.size() + " number of Directors");
                System.out.println("If you want to add new Director, Go to Admin Home and Add Director");
        
            }
        }
        
        for(Director d : directors){            
            System.out.println(d.getId() + " - " + d.getName());
        }
        
        int[] inputDirectors = new int[numberOfDir];
        
        for(int i = 0; i < numberOfDir; i++){
            System.out.println("Enter Id of Director " + (i+1));
            int value = keyboardInput.inputNumber();
            inputDirectors[i] = value;
        }
        
        List<Director> selectedDirectors = new ArrayList<>();

        for (int data : inputDirectors) {
            for (Director director : directors) {
                if (data == director.getId()) {
                    selectedDirectors.add(director);
                    break;
                }

            }

        }

        movie.setDirectors(selectedDirectors);

        List<Actor> actors = getAllActors();
        
        System.out.println("Total Number of Actors are " + actors.size());
        
        int numberOfAct = 0;
        while(true){
            System.out.println("How many Actors you want add this movie? ");
            numberOfAct = keyboardInput.inputNumber();
            
            if(numberOfAct <= actors.size()){
                break;
            }else{
                System.out.println("You can not enter more than " + actors.size() + " number of Actors");
                System.out.println("If you want to add new Actor, Go to Admin Home and Add Actor");
        
            }
        }
        
        for(Actor a : actors){            
            System.out.println(a.getId() + " - " + a.getName());
        }
        
        int[] inputActors = new int[numberOfAct];
        
        for(int i = 0; i < numberOfAct; i++){
            System.out.println("Enter Id of Actor " + (i+1));
            int value = keyboardInput.inputNumber();
            inputActors[i] = value;
        }
        
        List<Actor> selectedActors = new ArrayList<>();

        for (int data : inputActors) {
            for (Actor actor : actors) {
                if (data == actor.getId()) {
                    selectedActors.add(actor);
                    break;
                }

            }

        }

        movie.setActors(selectedActors);
        movieEao.creat(movie);

    }

    public static void setGenre() {
        KeyboardInput keyboardInput = new KeyboardInput();
        
        GenreEao genreEao = new GenreEaoImpl();
        Genre genre = new Genre();
        
        System.out.println("Enter GENRE NAME: ");
        String genreType = keyboardInput.inputString();
        genre.setGenreType(genreType);
        
        genreEao.create(genre);

    }

    public static void setDirector() {
        KeyboardInput keyboardInput = new KeyboardInput();
        
        DirectorEao directorEao = new DirectorEaoImpl();
        Director director = new Director();
        
        System.out.println("Enter DIRECTOR NAME: ");
        String name = keyboardInput.inputString();
        director.setName(name);
        
        System.out.println("Enter DIRECTOR EMAIL: ");
        String email = keyboardInput.inputString();
        director.setEmail(email);
        
        while (true) {
            System.out.println("Enter DIRECTOR BIRTH DAY: (In DD-MM-YYYY Format)");
            Date dob = keyboardInput.inputDate();
            
            if (dob == null) {
                System.out.println("Invalied Date Format..! \nPlese Enter Date of Birth in DD-MM-YYYY format \nEx-: 21-05-2021");
            } else {
                director.setDob(dob);
                break;
            }
        }
        
        directorEao.creat(director);

    }

    public static void setActor() {
        KeyboardInput keyboardInput = new KeyboardInput();
        
        ActorEao actorEao = new ActorEaoImpl();
        Actor actor = new Actor();
        
        System.out.println("Enter ACTOR NAME: ");
        String name = keyboardInput.inputString();
        actor.setName(name);
        
        System.out.println("Enter ACTOR EMAIL: ");
        String email = keyboardInput.inputString();
        actor.setEmail(email);
        
        while (true) {
            System.out.println("Enter ACTOR BIRTH DAY: (In DD-MM-YYYY Format)");
            Date dob = keyboardInput.inputDate();
            
            if (dob == null) {
                System.out.println("Invalied Date Format..! \nPlese Enter Date of Birth in DD-MM-YYYY format \nEx-: 21-05-2021");
            } else {
                actor.setDob(dob);
                break;
            }
        }
        
        actorEao.creat(actor);

    }

    public static List<Genre> getAllGenre() {
        GenreEao genreEao = new GenreEaoImpl();
        List<Genre> genres = genreEao.getAllGenre();

//        for (Genre g : genres) {
//            System.out.println(g.getId() + " - " + g.getGenreType());
//
//        }

        return genres;

    }

    public static List<Director> getAllDirectors() {
        DirectorEao directorEao = new DirectorEaoImpl();
        List<Director> directors = directorEao.getAllDirectors();

//        for (Director d : directors) {
//            System.out.println(d.getId() + " - " + d.getName());
//
//        }

        return directors;
    }

    public static List<Actor> getAllActors() {
        ActorEao actorEao = new ActorEaoImpl();
        List<Actor> actors = actorEao.getAllActors();

//        for (Actor a : actors) {
//            System.out.println(a.getId() + " - " + a.getName());
//
//        }

        return actors;
    }

}
