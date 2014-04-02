package com.packtpub.pf.blueprint.persistence.entity;

import com.packtpub.pf.blueprint.MovieType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai    <psramkumar@gmail.com>
 * Date: 3/6/14
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table
@Data
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private int rating;

    private boolean favorite;

    private String image;

    private boolean isImageUrl;

    private String movieUrl;

    private MovieType movieType;

    private boolean isPublic;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "MOVIE_TAGS",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tags> tags = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private User user;


    public void addToTags(Tags t){
         this.tags.add(t);
    }


}
