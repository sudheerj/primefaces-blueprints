package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai     <psramkumar@gmail.com>
 * Date: 3/9/14
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table
@Data
public class Tags implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Movie> movies = new HashSet<>();

    public void addToMovies(Movie mo){
        this.movies.add(mo);
    }

    public Tags(String name){
       this.name = name;
    }

    public Tags(){

    }

}
