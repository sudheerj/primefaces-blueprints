package com.packtpub.pf.blueprint.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by psramkumar on 4/21/14.
 */

@Data
@Entity
@Table
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<PrintJobs> jobs = new HashSet<>();


}
