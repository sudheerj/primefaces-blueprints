package com.packtpub.pf.blueprint.controller;

import com.packtpub.pf.blueprint.MovieType;
import com.packtpub.pf.blueprint.persistence.entity.Comment;
import com.packtpub.pf.blueprint.persistence.entity.Movie;
import com.packtpub.pf.blueprint.persistence.entity.Tags;
import com.packtpub.pf.blueprint.persistence.entity.User;
import com.packtpub.pf.blueprint.service.DAOService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai  <psramkumar@gmail.com>
 * Date: 3/7/14
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class MovieController  implements Serializable {
    private static final Logger _log = Logger.getLogger(UserController.class);

    @Setter
    @Getter
    private User currentUser;
    DAOService ds = new DAOService();

    @PostConstruct
    public void createSamples() {

        User us = ds.validateUser("admin", "admin");

        Movie m = new Movie();
        m.setTitle("Mr. PeaBody");
        m.setDescription("Animated movie for the year of 2014");
        m.setRating(5);
        m.setReleaseDate(new Date());
        m.setImage("1.jpg");
        m.setUser(us);
        m.setMovieType(MovieType.YOUTUBE);
        m.setMovieUrl("http://www.youtube.com/v/KZnUr8lcqjo");
        ds.addOrUpdateMovie(m);

        m = new Movie();
        m.setTitle("June 27");
        m.setDescription("Movie narrates the real hero's of America");
        m.setRating(5);
        m.setReleaseDate(new Date());
        m.setImage("2.jpg");
        m.setFavorite(true);
        m.setUser(us);
        m.setMovieType(MovieType.QUICKTIME);
        m.setMovieUrl("http://www.tulumba.com/mp3/mogollar/yurudukdurmadan/track%2002.mp3");
        ds.addOrUpdateMovie(m);

        m = new Movie();
        m.setTitle("Captain America");
        m.setDescription("Movie narrates the real hero's of America");
        m.setRating(5);
        m.setReleaseDate(new Date());
        m.setFavorite(true);
        m.setImage("3.jpg");
        m.setUser(us);
        ds.addOrUpdateMovie(m);


        movies = ds.getAllMovies();
    }

    @Getter
    private List<Movie> movies = new ArrayList<>();

    @Getter
    @Setter
    private Movie selectedMovie;
    @Getter
    @Setter
    private Movie movie;
    @Getter
    @Setter
    private List<String> selectedTags;

    public List<String> complete(String query) {

        List<String> result = ds.getTagsStartWith(query);
        if(result == null || result.isEmpty()){
            result = new ArrayList<>();
            result.add(query);
        }
       return result;
    }

    public MovieType[] getMovieTypes(){
        return MovieType.values();
    }

    public void deleteMovie(Movie mov){

    }

    public List<Movie> getFavoriteMovies() {
        List<Movie> mvs = new ArrayList<>();
        for (Movie m : movies) {
            if (m.isFavorite()) {
                mvs.add(m);
            }
        }
        return mvs;
    }

    public void prepareNew() {
        movie = new Movie();
    }

    public void addOrUpdateMovie() {
//        if(selectedTags != null){
//            for (String tg: selectedTags){
//                Tags tgs = ds.getTagByName(tg);
//                if(tgs == null){
//                    tgs = new Tags(tg);
//                }
//                tgs.addToMovies(movie);
//                ds.addOrUpdateEntity(tgs);
//            }
//
//        }
        ds.addOrUpdateEntity(movie);
    }

    public List<Comment> getAllCommentsByMovie() {
        return ds.getAllCommentsByMovie(selectedMovie);
    }

    @Getter
    @Setter
    private String comment;

    public void saveOrUpdateComment(User u) {
        Comment c = new Comment();
        c.setUser(u);
        c.setMovie(selectedMovie);
        c.setComment(comment);
        c.setCreateDate(new Date());
        ds.addOrUpdateEntity(c);
        comment = "";
    }


}
