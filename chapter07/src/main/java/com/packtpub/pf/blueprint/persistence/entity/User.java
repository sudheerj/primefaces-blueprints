package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ramkumar Pillai     <psramkumar@gmail.com>
 * Date: 3/8/14
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table
@Data
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatar = "no_image_available";

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Movie> movies = new HashSet<>();

}
