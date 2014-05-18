package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
public class Profile implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String gender;

    private String aboutme;

    private String avatar = "no_image_available";

    private String currentLocation;

    private boolean verified;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserPost> posts = new HashSet<>();

    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="personId"),
            inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private List<Profile> friends;

    @ManyToMany
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="personId")
    )
    private List<Profile> friendOf;

}
