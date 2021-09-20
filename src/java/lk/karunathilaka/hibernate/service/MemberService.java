/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import lk.karunathilaka.hibernate.eao.MemberEao;
import lk.karunathilaka.hibernate.eao.MemberEaoImpl;
import lk.karunathilaka.hibernate.eao.MovieEao;
import lk.karunathilaka.hibernate.eao.MovieEaoImpl;
import lk.karunathilaka.hibernate.eao.ReviewEaoImpl;
import lk.karunathilaka.hibernate.entity.Actor;
import lk.karunathilaka.hibernate.entity.Director;
import lk.karunathilaka.hibernate.entity.Genre;
import lk.karunathilaka.hibernate.entity.Member;
import lk.karunathilaka.hibernate.entity.Movie;
import lk.karunathilaka.hibernate.eao.ReviewEao;
import lk.karunathilaka.hibernate.entity.Review;

/**
 *
 * @author Hasitha
 */
public class MemberService {

    public static void memberHome(Member member) {
        KeyboardInput keyboardInput = new KeyboardInput();
        while (true) {

            System.out.println("Instructions ----> \nTo Obtain Servic, Please Type the Relevent Service ID then Press Enter");
            System.out.println("   1 - Movie List");
            System.out.println("   2 - Purchased Movie");
//            System.out.println("   3 - Add Director");
//            System.out.println("   4 - Add Actor");
//            System.out.println("   5 - List Registered Members");
            System.out.println("0000 - Logout");

            String inputValue = keyboardInput.inputString();

            if ("1".equals(inputValue)) {
                showMovieList(member);

            } else if ("2".equals(inputValue)) {
                getPurchasedMovies(member);

            }else if ("0000".equals(inputValue)) {
                System.out.println("Logout Success");
                UserService.initialiseSystem();

            } else {

            }
        }

    }

    public static void setMember() {

        KeyboardInput keyboardInput = new KeyboardInput();

        MemberEao memberEao = new MemberEaoImpl();

        Member member = new Member();
//        member.setUserId(1);
        System.out.println("Enter Your FULL NAME: ");
        String fullName = keyboardInput.inputString();
        member.setName(fullName);

        System.out.println("Enter Your EMAIL: ");
        String email = keyboardInput.inputString();
        member.setEmail(email);

        while (true) {
            System.out.println("Enter Your BIRTH DAY: (In DD-MM-YYYY Format)");
            Date dob = keyboardInput.inputDate();

            if (dob == null) {
                System.out.println("Invalied Date Format..! \nPlese Enter Date of Birth in DD-MM-YYYY format \nEx-: 21-05-2021");
            } else {
                member.setDob(dob);
                break;
            }
        }

        System.out.println("Enter Your PASSWORD: ");
        String password = keyboardInput.inputString();
        member.setPassword(password);

        System.out.println("Enter Your TELEPHONE: ");
        String tel = keyboardInput.inputString();
        member.setTelephone(tel);

        List<Member> members = memberEao.getmemberByEmail(member.getEmail());
        if (members.isEmpty()) {
            memberEao.create(member);
            System.out.println("Registered Successfully");
//            UserService.initialiseSystem();
        } else {
            System.out.println("Your Email is Already Registered..!");
//            UserService.initialiseSystem();
        }

        UserService.initialiseSystem();

    }

    public static List<Movie> getAllMovies() {
//        KeyboardInput keyboardInput = new KeyboardInput();

        MovieEao movieEao = new MovieEaoImpl();

        List<Movie> movies = movieEao.getAllMovies();

        return movies;
    }

    public static void showMovieList(Member member) {
        KeyboardInput keyboardInput = new KeyboardInput();

        List<Movie> movies = getAllMovies();

        if (movies.isEmpty()) {
            System.out.println("No Movies to show");
        } else {
            for (Movie m : movies) {
                System.out.println(m.getId() + " - " + m.getName());
            }
        }
        
        System.out.println("  00 - Back");
        System.out.println("0000 - Logout");
        System.out.println("Enter Movie ID to Show Details: ");
        
        int inputValue = keyboardInput.inputNumber();
        
        if(inputValue == 00){
            memberHome(member);
        }else if(inputValue == 0000){
            UserService.initialiseSystem();
        }else {
            
            for(Movie m : movies){
                if(m.getId() == inputValue){
                    showMovieDetails(m, member);
                    break;
                }
            
            }
//            showMovieDetails(member);
        }

    }
    
    public static void showMovieDetails(Movie movie, Member member){
        KeyboardInput keyboardInput = new KeyboardInput();
        
        MovieEao movieEao = new MovieEaoImpl();
        
        System.out.println("Movie Name: " + movie.getName());
        System.out.println("Movie Duration: " + movie.getRuntime());
        
        CalculationService calculationService = new CalculationService();
        Double rate = calculationService.rateCal(movie);
        System.out.println("Movie Rate: "+ rate);
        
        Movie movieGenres = movieEao.getMoviesGenre(movie.getId());
        List<Genre> genres = movieGenres.getGenres();
        System.out.print("Genres : ");
        
        for(Genre g : genres){
            System.out.print(g.getGenreType() + "   ");
        }
        System.out.println("");
        
        List<Director> directors = movieGenres.getDirectors();
        System.out.print("Directors : ");
        
        for(Director d : directors){
            System.out.print(d.getName() + "   ");
        }
        System.out.println("");
        
        List<Actor> actors = movieGenres.getActors();
        System.out.print("Actors: ");
        
        for(Actor a : actors){
            System.out.print(a.getName() + "   ");
        }
        System.out.println("");
        System.out.println("Do you want to Purchase this Movie?");
        System.out.println("   1 - Yes");
        System.out.println("   2 - NO");
        
        String inputValue = keyboardInput.inputString();
        
        if("1".equals(inputValue)){
            setPurchaseMovie(movie, member);
        }else {
            memberHome(member);
        }
    
    }
    
    public static void setPurchaseMovie(Movie movie, Member member){
//        KeyboardInput keyboardInput = new KeyboardInput();
        
        ReviewEao reviewEao = new ReviewEaoImpl();
        Review review = new Review();
        review.setRate(100);
        review.setMember(member);
        review.setMovie(movie);
        
        reviewEao.create(review);
        
        System.out.println("Movie Purchased Success...");
        
        showMovieList(member);
    }
    
    public static void getPurchasedMovies(Member member){
        KeyboardInput keyboardInput = new KeyboardInput();
        
        MemberEao memberEao = new MemberEaoImpl();
        
        Member memberReview = memberEao.getMember(member.getUserId());
        
        List<Review> reviews = memberReview.getReviews();
        
//        List<Movie> moviesAll = getAllMovies();
        
        for(Review r : reviews){
            
            Movie moviePurchased = r.getMovie();
            
            System.out.println(r.getId() + " - " + moviePurchased.getName());
            
        }
        
        System.out.println("  00 - Back");
        System.out.println("0000 - Logout");
        System.out.println("Enter ID to Rate Move: ");
        
        String inputValue = keyboardInput.inputString();
        
        if("00".equals(inputValue)){
            memberHome(member);
        
        }else if("0000".equals(inputValue)){
            UserService.initialiseSystem();
        }else{
            for(Review r : reviews){
                if(r.getId() == Integer.parseInt(inputValue)){
                    rateMove(r, member);
                    break;
                }
            
            }
            inputValue = "00";
            getPurchasedMovies(member);
        }
    
    }
    
    public static void rateMove(Review review, Member member){
        KeyboardInput keyboardInput = new KeyboardInput();
        
        ReviewEao reviewEao = new ReviewEaoImpl();
        
        System.out.println("Enter 0 to 10 Rate: ");
        int rate = keyboardInput.inputNumber();
        
        System.out.println("Enter Your Review about Movie: ");
        String reviewMember = keyboardInput.inputString();
        
        review.setRate(rate);
        review.setComment(reviewMember);
        
        reviewEao.saveOrUpdate(review);
        
    
    }

}
